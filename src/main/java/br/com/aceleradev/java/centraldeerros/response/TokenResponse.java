package br.com.aceleradev.java.centraldeerros.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class TokenResponse implements Serializable {

    private String token;

    public TokenResponse(String token) {
        this.token = token;
    }
}
