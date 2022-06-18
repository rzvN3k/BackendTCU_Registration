package tcu.backend.registration.service;

import lombok.Getter;

@Getter
public class RegistrationPinGenerationResponse {

    private String apiResponse;

    private String carVin;

    private String registrationPIN;

    private String pinExpireDate;

    RegistrationPinGenerationResponse(String apiResponse, String carVin, String registrationPIN, String pinExpireDate) {
        this.apiResponse = apiResponse;
        this.carVin = carVin;
        this.registrationPIN = registrationPIN;
        this.pinExpireDate = pinExpireDate;
    }
}
