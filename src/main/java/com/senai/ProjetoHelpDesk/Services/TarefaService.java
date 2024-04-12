package com.senai.ProjetoHelpDesk.Services;

import com.senai.ProjetoHelpDesk.DTO.TarefaDTO;
import com.senai.ProjetoHelpDesk.DTO.UsuarioDTO;
import com.senai.ProjetoHelpDesk.DTO.ViewTarefaDTO;
import com.senai.ProjetoHelpDesk.Models.TarefaModel;
import com.senai.ProjetoHelpDesk.Models.UsuarioModel;
import com.senai.ProjetoHelpDesk.Padroes.RespostaDTO;
import com.senai.ProjetoHelpDesk.Repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    UsuarioService usuarioService;

    public List<ViewTarefaDTO> obterTarefas() {
        return converterLista(tarefaRepository.findAll());
    }

    public RespostaDTO inserirTarefa(TarefaDTO tarefa) {
        RespostaDTO resposta = tarefaValida(tarefa);

        if (resposta.getStatus() == RespostaDTO.Status.ERRO) {
            return resposta;
        }

        UsuarioModel tarefaUsuario = usuarioService.obterUsuario(tarefa.getUsuarioEmail());
        if (tarefaUsuario.usuarioEstaVazio()) {
            return new RespostaDTO().erroNotFound("Usuário da tarefa não encontrado.");
        }

        tarefaRepository.save(new TarefaModel(tarefa, tarefaUsuario));
        return new RespostaDTO().sucesso("Tarefa inserida com sucesso.");
    }

    public boolean atualizarTarefa(TarefaDTO tarefa) {
        return true;
    }

    public boolean excluirTarefa(Long tarefaId) {
        return true;
    }

    private RespostaDTO tarefaValida(TarefaDTO tarefa) {

        if (!nomeValido(tarefa.getNome())) {
            return new RespostaDTO().erroConflict("Insira o nome para a tarefa.");
        }

        if (!descricaoValida(tarefa.getDescricao())) {
            return new RespostaDTO().erroConflict("Insira uma descrição para a tarefa.");
        }

        if (!dataValida(tarefa.getDataAgendamento())) {
            return new RespostaDTO().erroConflict("Data deve ser hoje ou maior que hoje.");
        }

        if (!statusValido(tarefa.getStatus())) {
            return new RespostaDTO().erroConflict("Insira um status válido para a tarefa.");
        }

        if(!emailValido(tarefa.getUsuarioEmail())) {
            return new RespostaDTO().erroConflict("Informe um email de usuário válido.");
        }

        return new RespostaDTO(RespostaDTO.Status.SUCESSO);
    }

    private boolean nomeValido(String nome) {
        return !nome.isEmpty();
    }

    private boolean descricaoValida(String descricao) {
        return !descricao.isEmpty();
    }

    private boolean dataValida(Date data) {

        return !data.before(Calendar.getInstance().getTime());
    }

    private boolean statusValido(Integer status) {
        return !(status.hashCode() > (TarefaModel.Status.values().length - 1) || status.hashCode() < 0);
    }

    private boolean emailValido(String email) {
        String regex = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private List<ViewTarefaDTO> converterLista(List<TarefaModel> listaTarefasModel) {
        return listaTarefasModel.stream().map(ViewTarefaDTO::new).collect(Collectors.toList());
    }

}
