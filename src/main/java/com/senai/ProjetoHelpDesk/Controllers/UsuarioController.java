package com.senai.ProjetoHelpDesk.Controllers;

import com.senai.ProjetoHelpDesk.DTO.UsuarioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @GetMapping()
	public ResponseEntity<Object> listarUsuarios()  {
        List<UsuarioDTO> listaUsuarios = new ArrayList<>();
        return ResponseEntity.status(HttpStatus.OK).body(listaUsuarios);
    }
    @PostMapping()
	public ResponseEntity<Object> criarUsuario(@RequestBody @Validated UsuarioDTO usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @PutMapping("/{email}")
    public ResponseEntity<Object> atualizarUsuario(@PathVariable String email, @RequestBody @Validated UsuarioDTO usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @DeleteMapping("/{email}")
	public ResponseEntity<Object> excluirUsuario(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK).body(new Object());
    }


}
