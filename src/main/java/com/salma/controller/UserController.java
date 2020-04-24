package com.salma.controller;


import com.salma.config.MessageResponse;
import com.salma.config.ResourceNotFoundException;
import com.salma.model.Role;
import com.salma.model.User;
import com.salma.repository.RoleRepository;
import com.salma.repository.UserRepository;
import com.salma.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@CrossOrigin(origins = "*", maxAge = 20)
@RequestMapping("/auth")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository users;

    @Autowired
    RoleRepository roles;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody AuthenticationRequest data)throws Exception{

        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(username, this.users.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password");
        }
    }
    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @PostMapping("/registerUser")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User data) throws ResourceNotFoundException {
        //check if patient is new, or old
        if (data.getId()!=null){
            User currentUser = users.findByEmail(data.getUsername());
            currentUser.setPassword(passwordEncoder.encode(data.getPassword()));
            currentUser.setRoles(data.getRoles());
            users.save(currentUser);
            return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
        }

        if (users.existsByUsername(data.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Username is already registered!"));
        }

        if (users.existsByEmail(data.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Email is already in registered!"));
        }

        if (data.getConfirmPassword()!= data.getConfirmPassword()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Passwords do not match!"));
        }

        // Create new user's account
        User user =new User();
        user.setUsername(data.getUsername());
        user.setEmail(data.getEmail());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setRoles(data.getRoles());
        users.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    //Roles
    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roles.findAll();
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){
        return users.findAll();
    }
}
