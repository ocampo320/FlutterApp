package com.example.ejemplo03.repository;

import com.example.ejemplo03.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepositorio extends JpaRepository<Usuario, Integer> {
}
