package com.senai.ProjetoHelpDesk.DTO;

import com.senai.ProjetoHelpDesk.Models.TarefaModel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.Date;

@Data
public class TarefaDTO {

    private Long id;

    private String nome;

    private String descricao;

    private String dataAgendamento;

    private Integer status;

    private String usuarioEmail;

    public TarefaDTO() {
    }
}
