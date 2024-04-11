package com.senai.ProjetoHelpDesk.Controllers;

import com.senai.ProjetoHelpDesk.DTO.UsuarioDTO;
import com.senai.ProjetoHelpDesk.Padroes.RespostaDTO;
import com.senai.ProjetoHelpDesk.Services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
	public ResponseEntity<Object> listarUsuarios()  {
        List<UsuarioDTO> listaUsuarios = usuarioService.obterUsuarios();

        if (listaUsuarios.isEmpty()) {
            RespostaDTO resposta = new RespostaDTO().erroNotFound("A lista de usuários está vazia.");
            return ResponseEntity.status(resposta.getHttpStatus()).body(resposta);
        }

        return ResponseEntity.status(HttpStatus.OK).body(listaUsuarios);
    }
    @PostMapping()
	public ResponseEntity<RespostaDTO> criarUsuario(@RequestBody @Validated UsuarioDTO usuario) {
        RespostaDTO resposta = usuarioService.inserirUsuario(usuario);
        return ResponseEntity.status(resposta.getHttpStatus()).body(resposta);
    }

    @PutMapping("/{email}")
    public ResponseEntity<RespostaDTO> atualizarUsuario(@PathVariable String email, @RequestBody @Validated UsuarioDTO usuario) {
        RespostaDTO resposta = usuarioService.atualizarUsuario(email, usuario);
        return ResponseEntity.status(resposta.getHttpStatus()).body(resposta);
    }

    @DeleteMapping("/{email}")
	public ResponseEntity<RespostaDTO> excluirUsuario(@PathVariable String email) {
        RespostaDTO resposta = usuarioService.excluirUsuario(email);
        return ResponseEntity.status(resposta.getHttpStatus()).body(resposta);
    }


}
