
insert into utilisateur values (default, 'u1@email.com', 'u1'),
    (default, 'u2@email.com', 'u2'),
    (default, 'u3@email.com', 'u3'),
    (default, 'u4@email.com', 'u4'),
    (default, 'u5@email.com', 'u5');

INSERT INTO Utilisateur (email,mdp) 
VALUES
('rakoto@gmail.com','mdp');
INSERT INTO Utilisateur (email,mdp) 
VALUES
('randria@gmail.com','mdp');

insert into Categorie_avion values(default, 'FEEDERS'),
    (default, 'REGIONAL FREIGHTERS'),
    (default, 'LONG RANGE FREIGHTERS'),
    (default, 'LARGE FREIGHTERS');

insert into Marque_avion values(default, 'Boeing'),
    (default, 'Airbus'),
    (default, 'Bombardier'),
    (default, 'Embraer'),
    (default, 'ATR'),
    (default, 'Fokker');

insert into Modele_avion values(default, 1, 'A-300'),
    (default, 1, 'A-300'),
    (default, 1, 'A-319'),
    (default, 1, 'A-320'),
    (default, 1, 'A-380'),
    (default, 2, 'CHALLENGER 350'),
    (default, 2, 'CHALLENGER 3500'),
    (default, 2, 'CHALLENGER 650'),
    (default, 2, 'GLOBAL 5500'),
    (default, 2, 'GLOBAL 6500'),
    (default, 3, 'Legcy 650'),
    (default, 3, 'Legcy 500)'),
    (default, 3, 'E-JETS'),
    (default, 3, 'E-JETS E2'),
    (default, 3, 'E175-E2'),
    (default, 4, 'ATR 42'),
    (default, 4, 'ATR 72'),
    (default, 4, 'ATR 42-500'),
    (default, 4, 'ATR 42-600'),
    (default, 4, 'ATR 42-600S'),
    (default, 5, 'FOKKER 100'),
    (default, 5, 'FOKKER F50'),
    (default, 5, 'FOKKER 70'),
    (default, 5, 'FOKKER 27'),
    (default, 5, 'FOKKER 50');

insert into Avion values(default, 'A-300Cpn', 1, 1, 1992),
        (default, 'A-319Cpn', 1, 1, 1995),
        (default, 'A-320Cpn', 1, 1, 1996),
        (default, 'A-380Cpn', 1, 1, 1998),
        (default, 'CLNGR-350', 2, 2, 2020),
        (default, 'CLNGR-3500', 2, 2, 2009),
        (default, 'CLNGR-650', 2, 2, 2016),
        (default, 'GBL-5500', 2, 2, 2013),
        (default, 'GBL-6500', 2, 2, 2019),
        (default, 'LG-650', 3, 3, 2002),
        (default, 'LG-500', 3, 3, 1991),
        (default, 'EJTS-01', 3, 3, 2002),
        (default, 'EJTS-175', 3, 3, 2015),
        (default, 'ART-42', 2, 4, 1999),
        (default, 'ATR-72', 2, 4, 2002),
        (default, 'ATR-425', 2, 4, 2005),
        (default, 'FK-100', 2, 5, 2001),
        (default, 'FK-70', 4, 4, 2006),
        (default, 'FK-27', 4, 4, 2012),
        (default, 'FK-50', 4, 4, 2017);

        
insert into parcours values(default, 1,'2019-06-12', 1019, 1320),
    (default, 4,'2010-12-02', 2044, 3000),
    (default, 3,'2010-12-10', 10034, 10920),
    (default, 1,'2011-01-02', 1320, 1720), 
    (default, 5,'2011-01-12', 200012, 201012),
    (default, 13,'2012-02-05', 70945, 71413),
    (default, 1,'2014-03-21', 1019, 1320),
    (default, 13,'2014-06-23', 71413, 71913),
    (default, 4,'2015-07-30', 3000, 4043),
    (default, 10,'2015-08-21', 13190, 14209),
    (default, 11,'2015-09-23', 1019, 1520),
    (default, 15,'2015-11-25', 201012, 203050), 
    (default, 16,'2016-03-20', 34195, 45623);

    
insert into token values(default, 'u1@email.com', '1231', 'aa', 1, '2022-12-12:12:00:00', '2023-12-20:13:00:00'),
    (default, 'u2@email.com', '1232', 'ab', 4, '2022-12-12:12:00:00', '2023-12-12:13:00:00'),
    (default, 'u3@email.com', '1233', 'ac', 3, '2022-12-12:12:00:00', '2023-12-12:13:00:00'),
    (default, 'u4@email.com', '1234', 'ad', 4, '2022-12-12:12:00:00', '2023-12-12:13:00:00'),
    (default, 'u5@email.com', '1235', 'ae', 5, '2022-12-12:12:00:00', '2023-12-12:13:00:00');

    
insert into Payement_assurance values(default, 1, '2022-06-10', '2023-06-10'),
    (default, 2, '2019-12-29', '2022-12-29'),
    (default, 3, '2018-04-10', '2022-06-10'),
    (default, 4, '2022-05-10', '2023-06-10'),
    (default, 5, '2022-06-10', '2023-06-10'),
    (default, 6, '2022-06-10', '2023-06-10'),
    (default, 7, '2022-06-10', '2023-06-10'),
    (default, 8, '2022-06-10', '2023-06-10'),
    (default, 9, '2022-06-10', '2023-06-10'),
    (default, 10, '2022-06-10', '2023-06-10'),
    (default, 11, '2022-06-10', '2023-06-10'),
    (default, 12, '2022-06-10', '2023-06-10'),
    (default, 13, '2022-06-10', '2023-06-10'),
    (default, 14, '2022-06-10', '2023-06-10'),
    (default, 15, '2022-06-10', '2023-06-10'),
    (default, 16, '2022-06-10', '2023-06-10'),
    (default, 17, '2022-06-10', '2023-06-10'),
    (default, 18, '2022-06-10', '2023-06-10'),
    (default, 19, '2022-06-10', '2023-06-10'),
    (default, 20, '2022-06-10', '2023-06-10');