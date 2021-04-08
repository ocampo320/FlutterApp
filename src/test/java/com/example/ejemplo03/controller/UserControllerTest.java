package com.example.ejemplo03.controller;

import com.example.ejemplo03.model.Usuario;
import com.example.ejemplo03.services.PersonaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(controllers = UserController.class)
@ActiveProfiles("test")
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonaService userService;

    @Autowired
    private ObjectMapper objectMapper;
    private List<Usuario> userList;

    @BeforeEach
    void setUp() {
        this.userList = new ArrayList<>();
        this.userList.add(new Usuario(
                1,
                "deivi",
                "lopez",
                "deivi320@hotmail.com",
                "2220718",
                "la sierra"));
        this.userList.add(new Usuario(
                2,
                "deivi",
                "lopez",
                "deivi320@hotmail.com",
                "2220718",
                "la sierra"));
        this.userList.add(new Usuario(
                3,
                "deivi",
                "lopez",
                "deivi320@hotmail.com",
                "2220718",
                "la sierra"));


    }
    @Test
    void shouldFetchAllUsers() throws Exception {

        given(userService.getUsers()).willReturn(userList);

        this.mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(userList.size())));
    }

    @Test
    void shouldFetchOneUserById() throws Exception {
        final int userId = 1;
        final Usuario user = new Usuario(
                3,
                "deivi",
                "lopez",
                "deivi320@hotmail.com",
                "2220718",
                "la sierra");

        given(userService.getUserById(userId)).willReturn(user);

        this.mockMvc.perform(get("/user/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.emailUsuario", is(user.getEmailUsuario())))
                .andExpect(jsonPath("$.nombeUsuario", is(user.getNombeUsuario())));
    }
    @Test
    void shouldCreateNewUser() throws Exception {
        given(userService.add(any(Usuario.class))).willAnswer((invocation) -> invocation.getArgument(0));

        Usuario user = new  Usuario(
                3,
                "deivi",
                "lopez",
                "deivi320@hotmail.com",
                "2220718",
                "la sierra");

        this.mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombeUsuario", is(user.getNombeUsuario())))
                .andExpect(jsonPath("$.apellidoUsuario", is(user.getApellidoUsuario())))
                .andExpect(jsonPath("$.emailUsuario", is(user.getEmailUsuario())))
                .andExpect(jsonPath("$.telefonoUsuario", is(user.getTelefonoUsuario())))
                .andExpect(jsonPath("$.direccionUsuario", is(user.getDireccionUsuario())))
        ;
    }
    @Test
    void shouldDeleteUser() throws Exception {
        int userId = 1;
        Usuario user = new   Usuario(
                3,
                "deivi",
                "lopez",
                "deivi320@hotmail.com",
                "2220718",
                "la sierra");
        given(userService.getUserById(userId)).willReturn(user);
        doNothing().when(userService).delete(user.getIdUsuario());

        this.mockMvc.perform(delete("/user/{id}", user.getIdUsuario()))
                .andExpect(status().isOk());


    }

}
