package com.example.ejemplo03.services;

import com.example.ejemplo03.model.Usuario;
import com.example.ejemplo03.repository.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonaService {

    @Autowired
    private PersonaRepositorio repositorio;


    public List<Usuario> getUsers() {
        return repositorio.findAll();
    }

    public Usuario add(Usuario p) {
        return repositorio.save(p);

    }

    public Usuario edit(Usuario user) {
        return repositorio.save(user);
    }

    public void delete(int id) {
        repositorio.deleteById(id);
    }

    public Usuario getUserById(int id) {
        return repositorio.findById(id).orElse(null);
    }


}
