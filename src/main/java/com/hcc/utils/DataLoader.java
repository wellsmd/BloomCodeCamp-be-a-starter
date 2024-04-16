package com.hcc.utils;

import com.hcc.entities.Authority;
import com.hcc.entities.User;
import com.hcc.repositories.AuthorityRepository;
import com.hcc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepo;

    @Autowired
    AuthorityRepository authRepo;

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
        loadAuthorityData();
    }

    private void loadUserData() {
        System.out.println("UserRepo count is: " + userRepo.count());
        if (userRepo.count() == 0) {
            PasswordEncoder pwencoder = new BCryptPasswordEncoder();
            String pw = pwencoder.encode("verysecurepassword");
            User tweedledee = new User(LocalDate.now(), "tweedledee", pw);
            userRepo.save(tweedledee);
            User tweedledum = new User(LocalDate.now(), "tweedledum", pw);
            userRepo.save(tweedledum);
        }
    }

    private void loadAuthorityData() {
        System.out.println("AuthRepo count is: " + authRepo.count());
        if (authRepo.count() == 0) {
            Authority learner = new Authority("ROLE_LEARNER", userRepo.findByUsername("tweedledee").get());
            authRepo.save(learner);
            Authority reviewer = new Authority("ROLE_REVIEWER", userRepo.findByUsername("tweedledum").get());
            authRepo.save(reviewer);
        }
    }

}