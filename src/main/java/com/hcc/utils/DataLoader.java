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
        if (userRepo.count() == 0) {
            PasswordEncoder pwencoder = new BCryptPasswordEncoder();
            String pw = pwencoder.encode("verysecurepassword");
            User john = new User(LocalDate.now(), "john", pw);
            userRepo.save(john);
            User paul = new User(LocalDate.now(), "paul", pw);
            userRepo.save(paul);
            User george = new User(LocalDate.now(), "george", pw);
            userRepo.save(george);
            User ringo = new User(LocalDate.now(), "ringo", pw);
            userRepo.save(ringo);
        }
    }

    private void loadAuthorityData() {
        if (authRepo.count() == 0) {
            Authority learner = new Authority("ROLE_LEARNER", userRepo.findByUsername("john").get());
            authRepo.save(learner);
            Authority reviewer = new Authority("ROLE_REVIEWER", userRepo.findByUsername("ringo").get());
            authRepo.save(reviewer);
        }
    }
}