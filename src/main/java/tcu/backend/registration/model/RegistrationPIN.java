package tcu.backend.registration.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class RegistrationPIN {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "registration_pin_id")
    private long registrationPinId;

    @Column(name = "registration_pin")
    private String registrationPin;
    @Column(name = "registration_car_vin")
    private String registrationCarVin;

    @Column(name = "registration_pin_create_date")
    private String registrationPinCreateDate;

    @Column(name = "registration_pin_expire_date")
    private String registrationPinExpireDate;

    public RegistrationPIN(String registrationCarVin, String registrationPin, String registrationPinCreateDate, String registrationPinExpireDate) {
        this.registrationPin = registrationPin;
        this.registrationCarVin = registrationCarVin;
        this.registrationPinCreateDate = registrationPinCreateDate;
        this.registrationPinExpireDate = registrationPinExpireDate;
    }
}
