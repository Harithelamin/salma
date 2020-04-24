package com.salma.config;

import com.salma.model.Role;
import com.salma.model.User;
import com.salma.repository.RoleRepository;
import com.salma.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    @Autowired
    UserRepository users;

    @Autowired
    RoleRepository roles;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (this.users.findAll().isEmpty())
        {
            //To Insert an admin for one time
            this.users.save(User.builder()
            .username("admin")
            .password(this.passwordEncoder.encode("123456"))
            .roles(Arrays.asList("ADMIN"))
            .email("admin@salma.com")
            .build()
            );
            //To insert Role Admin
            Role admin= new Role();
            admin.setRoleName("ADMIN");
            this.roles.save(admin);

            //To insert Role Doctor
            Role doctor= new Role();
            doctor.setRoleName("DOCTOR");
            this.roles.save(doctor);

            //To insert Role Labatory
            Role labatory= new Role();
            labatory.setRoleName("LABORATORY");
            this.roles.save(labatory);


        }
        log.debug("printing all users...");
        this.users.findAll().forEach(u -> log.debug(" User :" + u.toString()));
    }
}
