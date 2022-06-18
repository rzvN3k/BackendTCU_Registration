package tcu.backend.registration.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tcu.backend.registration.model.RegistrationPIN;

import java.util.List;

public interface RegistrationPINRepository extends CrudRepository<RegistrationPIN, Long> {

    @Query("FROM RegistrationPIN registration WHERE registration.registrationCarVin = :registrationCarVin")
    List<RegistrationPIN> findByCarVin(@Param("registrationCarVin") String registrationCarVin);

}
