set foreign_key_checks = 0;

-- delete all rows
truncate table korisnik; 
truncate table administrator;
truncate table profesor;
truncate table student;
truncate table dokument; 
truncate table eracun;
truncate table uplata;
truncate table predmet; 
truncate table predaje;
truncate table pohadja;
truncate table tip_obaveze; 
truncate table obaveza;

set foreign_key_checks = 1;

-- korisnik
INSERT INTO `korisnik` VALUES
(1,11223331,'0112223341','pera@localhost','Pera','pera','pera','Perinci',11221,'Peric','Milentija Popovica 16'),
(2,11223332,'0112223342','mika@localhost','Mika','mika','mika','Mikinci',11222,'Becic','Genget 11'),
(3,11223333,'0112223343','zika@localhost','Zika','zika','zika','Zikinci',11223,'Jovanovic','Cigamala 4'),
(4,11223334,'0112223344','marko@localhost','Marko','marko','marko','Markovci',11224,'Karan','Soric 202'),
(5,11223335,'0112223345','steva@localhost','Steva','steva','steva','Stevinci',11225,'Pecic','Kecari 16/19'),
(6,11223336,'0112223346','rale@localhost','Rale','rale','rale','Ralinci',11226,'Cvrkalj','Cukovci 54');

-- administrator
INSERT INTO `administrator` VALUES (1,1); -- pera

-- profesor
INSERT INTO `profesor` VALUES
(1,'Doktor',2),
(2,'Asistent',3);

-- eracun
INSERT INTO `eracun`VALUES
(1,150.00),
(2,500.00),
(3,1000.00);

-- uplata
INSERT INTO `uplata` VALUES
(1, null, 50.00, 'prijava ispita',1),
(2, null, 50.00, 'prijava ispita',1),
(3, null, 50.00, 'prijava ispita',1),
(4, null, 100.00, 'prijava ispita',2),
(5, null, 400.00, 'prijava ispita',2),
(6, null, 200.00, 'prijava ispita',3),
(7, null, 300.00, 'prijava ispita',3),
(8, null, 500.00, 'overa semestra',3);

-- student
INSERT INTO `student` VALUES
(1,'1/16',1,4),
(2,'2/16',2,5),
(3,'3/16',3,6);

-- dokument
INSERT INTO `dokument` VALUES
(1,'Svedocanstvo','#','svedocanstvo',1),
(2,'Kopija licne karte','#','legitimacija',2),
(3,'Index','#','index',2),
(4,'Podaci o roditeljima','#','biografija',3),
(5,'Knjizica lekarska','#','zdravlje',3),
(6,'Vozacka dozvola','#','vozacka',3);


-- predmet
INSERT INTO `predmet` VALUES
(1,'eObrazovanje','tehnologije i platforme'),
(2,'eUprava','tehnologije i platforme');

-- predaje
INSERT INTO `predaje` VALUES
(1,'Predavanja','profesor',1,1),
(2,'Vezbe','asistent',1,2),
(3,'Predavanja','profesor',2,2);

-- pohadja
INSERT INTO `pohadja` VALUES
(1,1,1),
(2,1,2),
(3,1,3),
(4,2,1);


-- tip obaveze
INSERT INTO `tip_obaveze` VALUES
(1, null, 'test', 'test tipa obaveze', 1),
(2, null, 'ispit', 'zavrsni ispit', 1);


-- obaveza
INSERT INTO `obaveza` VALUES
(1, null, null, null, 1, 1),
(2, 0, 0, false, 2, 1),
(3, 8, 80, false, 3, 1),
(4, null, null, null, 1, 2),
(5, 0, 0, false, 2, 2),
(6, 8, 80, false, 3, 2);
