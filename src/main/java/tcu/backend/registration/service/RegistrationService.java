package tcu.backend.registration.service;

import org.springframework.stereotype.Component;

@Component
public interface RegistrationService {

    RegistrationPinGenerationResponse generateRegistrationPIN(String carVIN);

}
