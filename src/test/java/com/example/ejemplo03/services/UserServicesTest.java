package com.example.ejemplo03.services;


import com.example.ejemplo03.model.Usuario;
import com.example.ejemplo03.repository.PersonaRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
public class UserServicesTest {
  @Mock
  private PersonaRepositorio personaRepositorio;

  @InjectMocks
  private PersonaService personaService;

  @Test
  void shouldSavedUserSuccessFully() {
    final Usuario user = new Usuario(
      1,
      "deivi",
      "lopez",
      "deivi320@hotmail.com",
      "2220718",
      "la sierra");

    given(personaRepositorio.save(user)).willAnswer(invocation -> invocation.getArgument(0));


    Usuario savedUser = personaService.add(user);

    assertThat(savedUser).isNotNull();

    verify(personaRepositorio).save(any(Usuario.class));
  }
  @Test
  void shouldReturnFindAll() {
    List<Usuario> datas = new ArrayList();
    datas.add(new Usuario(
      1,
      "deivi",
      "lopez",
      "deivi320@hotmail.com",
      "2220718",
      "la sierra"));
    datas.add(new Usuario(
      2,
      "deivi",
      "lopez",
      "deivi320@hotmail.com",
      "2220718",
      "la sierra"));
    datas.add(new Usuario(
      3,
      "deivi",
      "lopez",
      "deivi320@hotmail.com",
      "2220718",
      "la sierra"));

    given(personaRepositorio.findAll()).willReturn(datas);

    List<Usuario> expected = personaService.getUsers();

    assertEquals(expected, datas);
  }
  @Test
  void findUserById(){
    final int id = 1;
    final Usuario user =new Usuario(
      3,
      "deivi",
      "lopez",
      "deivi320@hotmail.com",
      "2220718",
      "la sierra");

    given(personaRepositorio.findById(id)).willReturn(Optional.of(user));

    final Usuario expected  =personaService.getUserById(id);

    assertThat(expected).isNotNull();

  }

  @Test
  void shouldBeDelete() {
    final int userId=1;

    personaService.delete(userId);
    personaService.delete(userId);

    verify(personaRepositorio, times(2)).deleteById(userId);
  }

  @Test
  void updateUser() {
    final Usuario user =new Usuario(
      3,
      "deivi",
      "lopez",
      "deivi320@hotmail.com",
      "2220718",
      "la sierra");

    given(personaRepositorio.save(user)).willReturn(user);

    final Usuario expected = personaService.edit(user);

    assertThat(expected).isNotNull();

    verify(personaRepositorio).save(any(Usuario.class));
  }
}
