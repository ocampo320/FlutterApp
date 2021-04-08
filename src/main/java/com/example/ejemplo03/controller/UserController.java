

package com.example.ejemplo03.controller;

import com.example.ejemplo03.model.Usuario;
import com.example.ejemplo03.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/user"})
public class UserController {

    @Autowired
    PersonaService service;

    @GetMapping
    public List<Usuario>listar(){
        return service.getUsers();
    }


  @PostMapping()
  public Usuario addUser(@RequestBody Usuario user) {

    return service.add(user);
  }
  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable int id){
    service.delete(id);
  }

  @GetMapping("/{id}")
  public Usuario getUserById(@PathVariable int id) {
    return service.getUserById(id);

  }
}

