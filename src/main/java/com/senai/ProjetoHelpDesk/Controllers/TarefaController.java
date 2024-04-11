package com.senai.ProjetoHelpDesk.Controllers;

import com.senai.ProjetoHelpDesk.DTO.TarefaDTO;
import com.senai.ProjetoHelpDesk.Padroes.RespostaDTO;
import com.senai.ProjetoHelpDesk.Services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    TarefaService tarefaService;

    @GetMapping()
	public ResponseEntity<Object> obterTarefas() {
        return ResponseEntity.status(HttpStatus.OK).body(new Object());
    }

    @PostMapping()
	public ResponseEntity<RespostaDTO> criarTarefa(@RequestBody @Validated TarefaDTO tarefa) {
        RespostaDTO resposta = tarefaService.inserirTarefa(tarefa);
        return ResponseEntity.status(resposta.getHttpStatus()).body(resposta);
    }

    @PutMapping("/{id}")
	public ResponseEntity<Object> atualizarTarefa(@PathVariable int id, @RequestBody @Validated TarefaDTO tarefa) {
        return ResponseEntity.status(HttpStatus.OK).body(new Object());
    }

    @DeleteMapping("/{id}")
	public ResponseEntity<Object> excluirTarefa(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(new Object());
    }


}
