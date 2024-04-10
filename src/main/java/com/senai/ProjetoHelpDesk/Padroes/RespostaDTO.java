package com.senai.ProjetoHelpDesk.Padroes;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
public class RespostaDTO {

    private HttpStatus httpStatus;

    private Status status;

    private String mensagem;

    public RespostaDTO() {
    }

    public RespostaDTO(HttpStatus httpStatus, Status status, String mensagem) {
        this.httpStatus = httpStatus;
        this.status = status;
        this.mensagem = mensagem;
    }

    public RespostaDTO(Status status) {
        this.status = status;
    }


    public RespostaDTO erroNotFound(String mensagemErro) {
        return new RespostaDTO(HttpStatus.NOT_FOUND, Status.ERRO, mensagemErro);
    }

    public RespostaDTO erroConflict(String mensagemErro) {
        return new RespostaDTO(HttpStatus.CONFLICT, Status.ERRO, mensagemErro);
    }

    public RespostaDTO erroBadRequest(String mensagemErro) {
        return new RespostaDTO(HttpStatus.BAD_REQUEST, Status.ERRO, mensagemErro);
    }

    public RespostaDTO sucesso(String mensagemSucesso) {
        return new RespostaDTO(HttpStatus.OK, Status.SUCESSO, mensagemSucesso);
    }


    public enum Status {
        SUCESSO, ERRO
    }

}
