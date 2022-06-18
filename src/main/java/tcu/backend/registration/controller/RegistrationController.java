package tcu.backend.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tcu.backend.registration.service.RegistrationCarClient;
import tcu.backend.registration.service.RegistrationPinGenerationResponse;
import tcu.backend.registration.service.RegistrationService;
import tcu.backend.registration.service.RegistrationServiceImpl;

import java.security.Principal;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationServiceImpl registrationServiceImpl;

//    @PreAuthorize("hasAuthority('ROLE_USER')")
//    @GetMapping("/user")
//    public String user(Model model, Principal principal) {
//
//        System.out.println("Client connected");
//
//        UserDetails currentUser = (UserDetails) ((Authentication) principal).getPrincipal();
//        model.addAttribute("username", currentUser.getUsername());
//        return "user";
//    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/generateRegistrationPIN")
    public RegistrationPinGenerationResponse triggerRegistrationProcess(@RequestBody String carVin) {

        RegistrationPinGenerationResponse pinGenerationResponse = registrationServiceImpl.generateRegistrationPIN(carVin);

        return pinGenerationResponse;
    }
}
