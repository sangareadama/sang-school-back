CREATE SEQUENCE personnel_seq START WITH 10 INCREMENT BY 50;

CREATE TABLE personnel
(
    id INT8 PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenoms VARCHAR(100) NOT NULL,
    email VARCHAR(255),
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(100) NOT NULL,
    statut VARCHAR(255),
    age INT4
);