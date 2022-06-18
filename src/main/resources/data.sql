CREATE TABLE registrationpin (
  registration_pin_id BIGINT NOT NULL,
   registration_pin VARCHAR(6) NULL,
   registration_car_vin VARCHAR(255) NULL,
   registration_pin_create_date VARCHAR(255) NULL,
   registration_pin_expire_date VARCHAR(255) NULL,
   CONSTRAINT pk_registrationpin PRIMARY KEY (registration_pin_id)
);