package com.dev.parkapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.dev.parkapi.dto.UsuarioCreateDto;
import com.dev.parkapi.dto.UsuarioResponseDto;
import com.dev.parkapi.exception.ErrorMessage;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UsuarioIT {

    @Autowired
    WebTestClient testClient;
    
    @Test
    public void criaUsuario_ComUsernameESenhaValidos_RetornarUsuarioCriadoComStatus201(){

        UsuarioResponseDto responseBody = testClient
            .post()
            .uri("/api/v1/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioCreateDto("tas@gmail.com", "123456"))
            .exchange()
            .expectStatus().isCreated()
            .expectBody(UsuarioResponseDto.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getUsername()).isEqualTo("tas@gmail.com");
        org.assertj.core.api.Assertions.assertThat(responseBody.getRole()).isEqualTo("CLIENTE");
    }

    @Test
    public void criaUsuario_ComUsernameInvalido_RetornarMensagemError422(){
        //Teste1
        ErrorMessage responseBody = testClient
            .post()
            .uri("/api/v1/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioCreateDto("", "123456"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatusCod()).isEqualTo(422);

        //Teste2
        responseBody = testClient
            .post()
            .uri("/api/v1/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioCreateDto("tas@", "123456"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatusCod()).isEqualTo(422);

        //Teste3
        responseBody = testClient
            .post()
            .uri("/api/v1/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioCreateDto("tas@gmail", "123456"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatusCod()).isEqualTo(422);
    }

    @Test
    public void criaUsuario_ComSenhaInvalida_RetornarMensagemError422(){
        //Teste1
        ErrorMessage responseBody = testClient
            .post()
            .uri("/api/v1/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioCreateDto("las@gmail.com", ""))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatusCod()).isEqualTo(422);

        //Teste2
        responseBody = testClient
            .post()
            .uri("/api/v1/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioCreateDto("las@gmail.com", "123"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatusCod()).isEqualTo(422);

        //Teste3
        responseBody = testClient
            .post()
            .uri("/api/v1/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioCreateDto("las@gmail.com", "123456789101112"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatusCod()).isEqualTo(422);
    }

    @Test
    public void criaUsuario_ComUsernameRepetido_RetornarMensagemError409(){

        ErrorMessage responseBody = testClient
            .post()
            .uri("/api/v1/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioCreateDto("xas@gmail.com", "123456"))
            .exchange()
            .expectStatus().isEqualTo(409)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatusCod()).isEqualTo(409);
    }
}
