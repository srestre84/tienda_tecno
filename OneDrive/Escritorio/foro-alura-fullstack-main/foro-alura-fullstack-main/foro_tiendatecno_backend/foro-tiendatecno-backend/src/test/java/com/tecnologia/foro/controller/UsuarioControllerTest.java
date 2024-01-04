package com.tecnologia.foro.controller;

import com.tecnologia.foro.dto.DatosRegistroUsuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class UsuarioControllerTest {

    @Autowired
    private UsuarioController usuarioController;
    @Autowired
    private JacksonTester<DatosRegistroUsuario> JacksonRegistro;
    @Autowired
    private MockMvc mockMvc;

//    @Test
//    @DisplayName("Should return a 201 created")
//    void registrarUsuarioSuccess() throws Exception {
//
//        mockMvc.perform(post("/usuarios")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JacksonRegistro.write(new DatosRegistroUsuario(
//                        "Constanza Melina",
//                        "cony123",
//                        "conym@mail.com",
//                        "123456"
//                )).getJson()))
//                .andExpect(status().isCreated());
//    }

    @Test
    @DisplayName("Should return a 400 bad request error for using invalid arguments")
    void registrarUsuarioNotCorrectEmail() throws Exception {

        mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JacksonRegistro.write(new DatosRegistroUsuario(
                        "Javiera Maza",
                        "javi123",
                        "conym",
                        "123456"
                )).getJson()))
                .andExpect(status().isBadRequest());
    }
}