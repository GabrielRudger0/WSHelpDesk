package com.senai.ProjetoHelpDesk.Repository;

import com.senai.ProjetoHelpDesk.Models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    public Optional<UsuarioModel> findByEmail(String email);

    public boolean existsByEmail(String email);

    public void deleteByEmail(String email);

}
