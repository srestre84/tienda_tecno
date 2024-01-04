package com.tecnologia.foro.controller;

import com.tecnologia.foro.dto.DatosAutenticarUsuario;
import com.tecnologia.foro.dto.DatosTokenJWT;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AuthControllerTest {

    @Autowired
    private JacksonTester<DatosTokenJWT> jwtJacksonTester;
    @Autowired
    private JacksonTester<DatosAutenticarUsuario> usuarioJacksonTester;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should return 403 forbidden")
    void autenticarUsuarioIncorrecto() throws Exception {

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJacksonTester.write(new DatosAutenticarUsuario("cony121", "1234356")).getJson()))
                .andExpect(status().isForbidden());


    }

    @Test
    @DisplayName("Should return 200 ok + json body")
    void autenticarUsuarioCorrecto() throws Exception {

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJacksonTester.write(new DatosAutenticarUsuario("cony123", "123456")).getJson()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.jwtToken").exists());
    }
}