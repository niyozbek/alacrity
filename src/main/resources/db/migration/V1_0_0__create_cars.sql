CREATE TABLE cars
(
    car_id   BIGSERIAL PRIMARY KEY,
    make  varchar(45) NOT NULL,
    price     DECIMAL(12, 2) NOT NULL,
    year_of_manufacture INTEGER NOT NULL
);

CREATE INDEX cars_idx_make on cars (make);

