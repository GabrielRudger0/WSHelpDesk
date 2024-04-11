package com.senai.ProjetoHelpDesk.Services;

import com.senai.ProjetoHelpDesk.DTO.UsuarioDTO;
import com.senai.ProjetoHelpDesk.Models.UsuarioModel;
import com.senai.ProjetoHelpDesk.Padroes.RespostaDTO;
import com.senai.ProjetoHelpDesk.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> obterUsuarios() {
        return converterLista(usuarioRepository.findAll());
    }

    public RespostaDTO inserirUsuario(UsuarioDTO usuario) {
        RespostaDTO resposta = usuarioValido(usuario);

        if (resposta.getStatus() == RespostaDTO.Status.ERRO) {
            return resposta;
        }

        if (existeUsuario(usuario.getEmail())) {
            return resposta.erroConflict("Já existe usuário.");
        }

        usuarioRepository.save(new UsuarioModel(usuario));
        return resposta.sucesso("Usuário inserido com sucesso.");
    }

    public RespostaDTO atualizarUsuario(String email, UsuarioDTO usuario) {
        RespostaDTO resposta = usuarioValido(usuario);

        if (resposta.getStatus() == RespostaDTO.Status.ERRO) {
            return resposta;
        }

        if(!existeUsuario(email)) {
            return new RespostaDTO().erroNotFound("Usuário não encontrado.");
        }

        usuarioRepository.save(new UsuarioModel(obterUsuario(email).getId(), usuario.getNome(), usuario.getEmail()));
        return new RespostaDTO().sucesso("Usuário alterado com sucesso.");
    }

    @Transactional
    public RespostaDTO excluirUsuario(String email) {
        if (!existeUsuario(email)) {
            return new RespostaDTO().erroNotFound("Usuário não encontrado.");
        }

        usuarioRepository.deleteByEmail(email);
        return new RespostaDTO().sucesso("Usuário excluído com sucesso.");
    }

    public UsuarioModel obterUsuario(String email) {
        Optional<UsuarioModel> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent()) {
            return usuario.get();
        }
        return new UsuarioModel();
    }

    private List<UsuarioDTO> converterLista(List<UsuarioModel> listaUsuarioModel) {
        return listaUsuarioModel.stream().map(UsuarioDTO::new).collect(Collectors.toList());
    }

    private boolean existeUsuario(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    private RespostaDTO usuarioValido(UsuarioDTO usuario) {
        if (!nomeValido(usuario.getNome())) {
            return new RespostaDTO().erroConflict("Insira o nome para o usuário.");
        }

        if(!emailValido(usuario.getEmail())) {
            return new RespostaDTO().erroConflict("Informe um email válido");
        }

        return new RespostaDTO(RespostaDTO.Status.SUCESSO);
    }

    private boolean nomeValido(String nome) {
        if (nome.isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean emailValido(String email) {
        String regex = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
