package br.com.aceleradev.java.centraldeerros.service;

import br.com.aceleradev.java.centraldeerros.model.User;
import br.com.aceleradev.java.centraldeerros.repository.UserRepository;
import br.com.aceleradev.java.centraldeerros.response.CredentialsResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean hasAccount(CredentialsResponse credentialsResponse) {
        Optional<User> existingAccount = userRepository.findByLogin(credentialsResponse.getLogin());
        if(existingAccount.isPresent()) {
            return passwordEncoder.matches(credentialsResponse.getPassword(), existingAccount.get().getPassword());
        }
        return false;
    }

    public Optional<User> findById(Long id)  {
        return this.userRepository.findById(id);
    }

    public User findByEmail(String email)  {
        return this.userRepository.findByEmail(email);
    }

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
