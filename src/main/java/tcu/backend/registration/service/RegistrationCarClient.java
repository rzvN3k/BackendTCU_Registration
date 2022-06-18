package tcu.backend.registration.service;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tcu.backend.registration.model.CarApiResponse;
import tcu.backend.registration.model.CarInformations;

import java.util.List;

@FeignClient(value = "car", url = "http://localhost:8082")
public interface RegistrationCarClient {

    @RequestMapping(method = RequestMethod.POST, value = "/car/getCarInfo")
    CarApiResponse getCarInformations(@RequestBody CarInformations carInfos);

}
