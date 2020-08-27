CREATE TABLE person(
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    real_name VARCHAR(100) NOT NULL,
    date_of_birth DATE NOT NULL
);