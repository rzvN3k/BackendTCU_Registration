package tcu.backend.registration.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Setter
@Getter
@ToString
public class CarInformations {

    private String carVin;

    private String carModel;

    private Date carReleaseDate;

    private String carColour;

    private Short carNumberOfDoors;

    private Integer carEnginePower;

    private Integer carCylindricalCapacity;

    private Boolean carMappedToAccount;

    private String carSecurityMappingKey;

    private String carOwnerEmail;

    private Boolean carRegistrationStatus;
}
