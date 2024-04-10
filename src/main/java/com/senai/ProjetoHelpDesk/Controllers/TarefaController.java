package com.senai.ProjetoHelpDesk.Controllers;

import com.senai.ProjetoHelpDesk.DTO.TarefaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @GetMapping()
	public ResponseEntity<Object> obterTarefas() {
        return ResponseEntity.status(HttpStatus.OK).body(new Object());
    }

    @PostMapping()
	public ResponseEntity<Object> criarTarefa(@RequestBody @Validated TarefaDTO tarefa) {
        return ResponseEntity.status(HttpStatus.OK).body(new Object());
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
