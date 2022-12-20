-- CREATE DATABASE tp_transversal_s5;
-- \c tp_transversal_s5


create table utilisateur
(
    id        serial
        primary key,
    email     varchar(80) not null
        unique,
    mdp       varchar(80) not null
);


create table Categorie_avion
(
    id   serial
        primary key,
    categorie varchar(35) not null
        unique
);


create table Marque_avion
(
    id     serial
        primary key,
    marque varchar(35) not null
        unique
);


create table Modele_avion
(
    id        serial
        primary key,
    id_marque integer     not null
        references Marque_avion(id),
    modele     varchar(30) not null
);



create table Avion
(
    id         serial
        primary key,
    matricule varchar(35) unique not null,
    id_categorie    integer not null
        REFERENCES Categorie_avion(id),
    id_modele  integer not null
        references modele_avion(id),
    annee      smallint not null,
    image      varchar(80)
);


create table parcours
(
    id          serial
        primary key,
    id_avion integer not null
        references Avion(id),
    date        date not null default now(),
    debut_km    integer
        constraint parcours_debut_km_check
            check (debut_km >= 0),
    fin_km      integer
        constraint parcours_fin_km_check
            check (fin_km > 0)
);


create table token
(
    id              serial
        primary key,
    email           varchar(80) not null,
    mdp             varchar(80) not null,
    token       text not null,
    id_utilisateur       integer
        references Utilisateur(id),
    creation_date        timestamp not null default now(),
    expiration_date timestamp not null
);

CREATE TABLE Payement_assurance(
    id serial primary key,
    id_avion integer REFERENCES Avion(id) not null,
    date_payement date not null default now(),
    date_expiration date not null check(date_expiration>date_payement)
);

