package com.cda.rico.controllers.security; // Declaración del paquete donde se encuentra la clase

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class AuthResponseDto {
    private UserDetails user; // Declaración de un campo para almacenar los detalles del usuario autenticado
    private String token; // Declaración de un campo para almacenar el token de autenticación

    // Constructor de la clase que recibe los detalles del usuario y el token como parámetros
    public AuthResponseDto(UserDetails user, String token) {
        this.user = user;   // Inicialización del campo de detalles del usuario
        this.token = token; // Inicialización del campo de token
    }
}
