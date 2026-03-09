package com.alura.forohub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Este método es clave: buscará en la tabla un registro que coincida con el login
    UserDetails findByLogin(String login);
}