   CREATE DATABASE bddJavaTp;
   
   USE bddJavaTp;
   
   CREATE TABLE resultados (
        nroPartido INT PRIMARY KEY AUTO_INCREMENT,
        ronda INT,
        equipo1 VARCHAR(30),
        cantGoles1 INT,
        cantGoles2 INT,
        equipo2 VARCHAR(30)
);


CREATE TABLE pronosticos (
    equipo1 VARCHAR(255),
    gana1 VARCHAR(255),
    empata VARCHAR(255),
    gana2 VARCHAR(255),
    equipo2 VARCHAR(255),
    participante VARCHAR(255),
    nPartido INT,
    ronda INT,
    constraint fk_np foreign key(nPartido) references resultados(nroPartido)
);


INSERT INTO resultados (nroPartido, ronda, equipo1, cantGoles1, cantGoles2, equipo2)
VALUES
    (1, 1, "Argentina", 1, 2, "Arabia Saudita"),
	(2, 1, "Polonia", 0, 0, "México"),
    (3, 1, "Argentina", 2, 0, "México"),
	(4, 1, "Arabia Saudita", 0, 2, "Polonia");
    
INSERT INTO pronosticos (equipo1, gana1, empata, gana2, equipo2, participante, nPartido, ronda)
VALUES 
    ("Argentina", "x", "", "", "Arabia Saudita", "Mariana", 1, 1),
    ("Polonia", "", "x", "", "Mexico", "Mariana", 2, 1),
    ("Argentina", "x", "", "", "Mexico", "Mariana", 3, 2),
    ("Arabia Saudita", "", "", "x", "Polonia", "Mariana", 4, 2),
    ("Argentina", "x", "", "", "Arabia Saudita", "Pedro", 1, 1),
    ("Polonia", "", "", "x", "Mexico", "Pedro", 2, 1),
    ("Argentina", "x", "", "", "Mexico", "Pedro", 3, 2),
    ("Arabia Saudita", "", "x", "", "Polonia", "Pedro", 4, 2);


CREATE TABLE participantes (
      nombre VARCHAR(30) PRIMARY KEY,
      puntos INT
      );
      
      
SELECT * FROM participantes;


DELETE FROM participantes;
      
      

  