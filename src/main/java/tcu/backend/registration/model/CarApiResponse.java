package tcu.backend.registration.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CarApiResponse {

    private Boolean status;

    private String apiResponse;

    private String dateOfProcessing;

    private CarInformations carInformations;

}
