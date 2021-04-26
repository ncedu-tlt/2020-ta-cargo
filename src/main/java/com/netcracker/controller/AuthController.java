package com.netcracker.controller;

import com.netcracker.model.AuthRequestDTO;
import com.netcracker.model.Car;
import com.netcracker.model.Client;
import com.netcracker.model.Role;
import com.netcracker.repository.ClientRepository;
import com.netcracker.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private ClientRepository clientRepository;
    private JwtTokenProvider jwtTokenProvider;
    private PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                          ClientRepository clientRepository,
                          JwtTokenProvider jwtTokenProvider,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.clientRepository = clientRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authentication(@RequestBody AuthRequestDTO request){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            Client client = clientRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
            String token = jwtTokenProvider.createToken(request.getEmail(), client.getRole().name());

            Car car = client.getCar();
            String canDrive;
            if(car != null) {canDrive = "true";
            }else canDrive = "false";

            Map<Object, Object> response = new HashMap<>();
            response.put("email", request.getEmail());
            response.put("token", token);
            response.put("driver", canDrive);
            response.put("id", client.getUserId());
            response.put("role", client.getRole());

            return ResponseEntity.ok(response);
        }catch (AuthenticationException e){
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/reg")
    public ResponseEntity<?> reg(@RequestBody Client client){
        Client regClient = new Client();
        regClient.setLastName(client.getLastName());
        regClient.setFirstName(client.getFirstName());
        regClient.setMiddleName(client.getMiddleName());
        regClient.setPhone(client.getPhone());
        regClient.setEmail(client.getEmail());
        regClient.setRole(Role.USER);
        regClient.setPassword(passwordEncoder.encode(client.getPassword()));
        clientRepository.saveAndFlush(regClient);
        return null;
    }

}
