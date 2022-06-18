package tcu.backend.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tcu.backend.registration.model.CarApiResponse;
import tcu.backend.registration.model.CarInformations;
import tcu.backend.registration.model.RegistrationPIN;
import tcu.backend.registration.repository.RegistrationPINRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationCarClient regCarClient;

    @Autowired
    private RegistrationPINRepository pinRepository;

    private static final int REGISTRATION_MIN_PIN_VALUE = 100000;

    private static final int REGISTRATION_MAX_PIN_VALUE = 999999;

    private static final HashMap<RegistrationErrorCodes, String> apiResponseMapping;
    static {
        apiResponseMapping = new HashMap<>();
        apiResponseMapping.put(RegistrationErrorCodes.REGISTRATION_PIN_GENERATION_SUCCESS, "Registration PIN was generated with success!");
        apiResponseMapping.put(RegistrationErrorCodes.REGISTRATION_CAR_NOT_FOUND, "VIN was not found in cars database!");
        apiResponseMapping.put(RegistrationErrorCodes.REGISTRATION_DATABASE_GENERAL_ERROR, "Error occurred when database operation was performed!");
    }

    @Override
    public RegistrationPinGenerationResponse generateRegistrationPIN(String carVIN) {

        System.out.println("Registration: Check if the car exists, VIN: " + carVIN);

        CarInformations carInformations = new CarInformations();
        carInformations.setCarVin(carVIN);

        CarApiResponse responseFromCarMicro = regCarClient.getCarInformations(carInformations);

        if(responseFromCarMicro.getCarInformations() == null) {
            System.out.println("Registration: VIN " + carVIN + " not found. Return failed response to client.");
            return new RegistrationPinGenerationResponse(apiResponseMapping.get(RegistrationErrorCodes.REGISTRATION_CAR_NOT_FOUND),
                                                         carVIN,
                                                         null,
                                                         null);
        } else {
            System.out.println("Registration: Car found. Check the registration status");
            if(responseFromCarMicro.getCarInformations().getCarRegistrationStatus()) {
                System.out.println("Registration: Car already registered, aborting");
            } else {
                System.out.println("Registration: Car not registered, prepare to generate PIN.");

                //generate random 6 digits PIN
                ThreadLocalRandom tlr = ThreadLocalRandom.current();
                int randomPIN = tlr.nextInt(REGISTRATION_MIN_PIN_VALUE, REGISTRATION_MAX_PIN_VALUE + 1);
                String generatedPIN = String.valueOf(randomPIN);

                //compute the creation and expiration date
                String stringCreateDate = "";
                String stringExpireDate = "";
                LocalDateTime createDate = LocalDateTime.now();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                stringCreateDate = createDate.format(dateFormatter);

                LocalDateTime expireDate = createDate.plusMinutes(createDate.getMinute());
                stringExpireDate = expireDate.format(dateFormatter);

                //save to the DB
                RegistrationPIN registrationDBEntry = new RegistrationPIN(carVIN, generatedPIN, stringCreateDate, stringExpireDate);
                pinRepository.save(registrationDBEntry);

                //return the response to the user
                return new RegistrationPinGenerationResponse(apiResponseMapping.get(RegistrationErrorCodes.REGISTRATION_PIN_GENERATION_SUCCESS),
                                                             carVIN,
                                                             generatedPIN,
                                                             stringExpireDate);
            }
        }
        return null;
    }

}
