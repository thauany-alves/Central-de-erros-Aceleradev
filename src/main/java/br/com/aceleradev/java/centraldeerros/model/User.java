package br.com.aceleradev.java.centraldeerros.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Size(max = 50)
    private String login;

    @Email(message = "Email inválido")
    @NotNull
    @Size(max = 100)
    private String email;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private String password;


    @CreatedDate
    @CreationTimestamp
    private LocalDateTime createAt;

    @JsonCreator
    public User(@NotNull @Size(max = 50)@JsonProperty("login") String login, @NotNull @Size(max = 50) @JsonProperty("password") String password, @Email @NotNull @Size(max = 100) @JsonProperty("email") String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public User() {
    }



    public String getPassword() {
        return this.password;
    }

    public String getLogin() {
        return this.login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}

