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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

        Date data = formatarData(tarefa.getDataAgendamento());
        if (usuarioPossuiAgendaParaData(data, usuarioService.obterUsuario(tarefa.getUsuarioEmail()))) {
            return new RespostaDTO().erroConflict("Usuário já possui agenda para a data informada.");
        }

        tarefaRepository.save(new TarefaModel(tarefa, formatarData(tarefa.getDataAgendamento()), tarefaUsuario));
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
            return new RespostaDTO().erroConflict("Informe uma data válida.");
        }

//        if ((!data.after(dataAtual()))) {
//            return new RespostaDTO().erroConflict("Data não pode ser menor que hoje.");
//        }

        if (!statusValido(tarefa.getStatus())) {
            return new RespostaDTO().erroConflict("Insira um status válido para a tarefa.");
        }

        if(!emailValido(tarefa.getUsuarioEmail())) {
            return new RespostaDTO().erroConflict("Informe um email de usuário válido.");
        }

        return new RespostaDTO(RespostaDTO.Status.SUCESSO);
    }

    private boolean usuarioPossuiAgendaParaData(Date data, UsuarioModel usuario) {
        List<TarefaModel> listaTarefasUsuarioBD = tarefaRepository.findAllByUsuario(usuario);
        for (TarefaModel tarefa : listaTarefasUsuarioBD) {
            if(tarefa.getDataAgendamento().compareTo(data) == 0) {
                return true;
            }
        }
        return false;
    }

    private Date formatarData(String stringData) {
        SimpleDateFormat formatoddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");

        try {
            return formatoddMMyyyy.parse(stringData);
        } catch (ParseException e) {
            return null;
        }

    }

    private boolean nomeValido(String nome) {
        return !nome.isEmpty();
    }

    private boolean descricaoValida(String descricao) {
        return !descricao.isEmpty();
    }

    private boolean dataValida(String stringData) {
        String regex = "^(0[1-9]|[1-2][0-9]|[3][0-1])/(0[1-9]|1[0-2])/(\\d{4})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringData);
        return matcher.matches();
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
