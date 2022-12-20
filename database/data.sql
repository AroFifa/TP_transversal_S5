-- Utilisateur
INSERT INTO Utilisateur (email,mdp) 
VALUES
('rakoto@gmail.com','mdp');
INSERT INTO Utilisateur (email,mdp) 
VALUES
('randria@gmail.com','mdp');

-- Categorie
INSERT INTO Categorie_avion (id,categorie)
VALUES 
(1,'ctg1'),
(2,'ctg2'),
(3,'ctg3'),
(4,'ctg4'),
(5,'ctg5'),
(6,'ctg6'),
(7,'ctg7'),
(8,'ctg8'),
(9,'ctg9');

-- Marque
INSERT INTO Marque_avion (id,marque)
VALUES 
(1,'marque1'),
(2,'marque2'),
(3,'marque3'),
(4,'marque4'),
(5,'marque5'),
(6,'marque6'),
(7,'marque7'),
(8,'marque8'),
(9,'marque9');

-- Modele
INSERT INTO Modele_avion (id,id_marque,modele)
VALUES 
(1,1,'m1 mod1'),
(2,1,'m1 mod2'),
(3,1,'m1 mod3'),
(4,2,'m2 mod1'),
(5,2,'m2 mod2'),
(6,3,'m3 mod1'),
(7,5,'m5 mod6'),
(8,8,'m8 mod6'),
(9,6,'m6 mod1');


-- Avion

INSERT INTO Avion (id,matricule,id_categorie,id_modele,annee) VALUES
(1,'#av1531',1,3,1856),
(2,'#dv1531',3,6,1951),
(3,'#vv1531',5,9,1856),

(4,'#av1512',1,2,1951),
(5,'#vv1536',6,4,1923),
(6,'#dv1533',9,6,2001);

-- Payement assurance

insert into payement_assurance (id_avion,date_payement,date_expiration)
values 
(1,'2017-12-15','2018-12-16'),
(1,'2018-12-17','2019-12-18'),
(1,'2019-12-19','2020-12-20'),
(1,'2019-12-19','2020-12-20'),
(1,'2020-12-22','2021-12-22'),
(1,'2021-12-25','2023-02-15');

insert into payement_assurance (id_avion,date_payement,date_expiration)
values 
(2,'2017-12-15','2018-12-16'),
(2,'2018-12-17','2019-12-18'),
(2,'2019-12-19','2020-12-20'),
(2,'2019-12-19','2020-12-20'),
(2,'2020-12-22','2021-12-22'),
(2,'2021-12-25','2023-01-15');

insert into payement_assurance (id_avion,date_payement,date_expiration)
values 
(4,'2022-12-15','2023-02-26'),
(5,'2022-12-17','2023-03-18'),
(6,'2022-12-19','2023-04-20'),
(2,'2022-12-19','2023-03-20');


insert into parcours(id_avion,date,debut_km,fin_km) VALUES
(1,'2021-12-19',500,10000),
(1,'2021-12-20',10000,13000),
(1,'2021-12-25',13000,19000),
(1,'2021-12-26',19000,20000),
(1,'2022-02-05',20000,23000);
