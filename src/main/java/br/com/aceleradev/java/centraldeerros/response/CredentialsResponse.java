package br.com.aceleradev.java.centraldeerros.response;

import lombok.Data;


import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class CredentialsResponse implements Serializable {


    @NotNull(message = "Informe login")
    private String login;

    @NotNull(message = "Informe um password")
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
