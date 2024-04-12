package com.senai.ProjetoHelpDesk.Controllers;

import com.senai.ProjetoHelpDesk.DTO.TarefaDTO;
import com.senai.ProjetoHelpDesk.DTO.ViewTarefaDTO;
import com.senai.ProjetoHelpDesk.Padroes.RespostaDTO;
import com.senai.ProjetoHelpDesk.Services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    TarefaService tarefaService;

    @GetMapping()
	public ResponseEntity<Object> obterTarefas() {
        List<ViewTarefaDTO> lista = tarefaService.obterTarefas();
        if (lista.isEmpty()) {
            RespostaDTO resposta = new RespostaDTO().erroNotFound("Lista vazia de tarefas.");
            return ResponseEntity.status(resposta.getHttpStatus()).body(resposta);
        }
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @PostMapping()
	public ResponseEntity<RespostaDTO> criarTarefa(@RequestBody @Validated TarefaDTO tarefa) {
        RespostaDTO resposta = tarefaService.inserirTarefa(tarefa);
        return ResponseEntity.status(resposta.getHttpStatus()).body(resposta);
    }

    @PutMapping("/{id}")
	public ResponseEntity<RespostaDTO> atualizarTarefa(@PathVariable Long id, @RequestBody @Validated TarefaDTO tarefa) {
        RespostaDTO resposta = tarefaService.atualizarTarefa(id, tarefa);
        return ResponseEntity.status(resposta.getHttpStatus()).body(resposta);
    }

    @DeleteMapping("/{id}")
	public ResponseEntity<RespostaDTO> excluirTarefa(@PathVariable Long id) {
        RespostaDTO resposta = tarefaService.excluirTarefa(id);
        return ResponseEntity.status(resposta.getHttpStatus()).body(resposta);
    }


}
