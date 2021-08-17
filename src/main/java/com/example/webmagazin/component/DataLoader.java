package com.example.webmagazin.component;

import com.example.webmagazin.entity.User;
import com.example.webmagazin.entity.enam.RoleName;
import com.example.webmagazin.repository.RoleRepository;
import com.example.webmagazin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataLoader implements CommandLineRunner {
    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")){
            userRepository.save(new User(
                    "+998933119525",
                    passwordEncoder.encode("12345"),
                    "Shohruh",
                    Collections.singletonList(roleRepository.findByRoleName(RoleName.ROLE_ADMIN))));


        }
    }
}
