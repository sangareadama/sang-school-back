CREATE SEQUENCE utilisateur_id_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE utilisateur
(
    id INT8 PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenoms VARCHAR(100) NOT NULL,
    email VARCHAR(255),
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(100) NOT NULL,
    statut VARCHAR(255)
);
