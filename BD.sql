/* CREACION DE LAS TABLAS */

CREATE SCHEMA futbol;

CREATE TYPE futbol.modalidad AS ENUM
  ('ESTANDAR', 'SOLIDARIO', 'CONDICIONAL');


CREATE TABLE futbol.PERSONA (
  persona_id  serial NOT NULL,
  nombre      varchar(50) NOT NULL,
  email       varchar(50) NOT NULL,
  apodo       varchar(30) NOT NULL,
  fecha_nac   date NOT NULL,
  /* Keys */
  CONSTRAINT "PERSONA_Index01"
    PRIMARY KEY (persona_id)
);


CREATE TABLE futbol.JUGADOR (
  jugador_id  serial NOT NULL,
  persona_id  integer NOT NULL,
  handicap    double precision NOT NULL,
  /* Keys */
  CONSTRAINT "JUGADOR_pkey"
    PRIMARY KEY (jugador_id),
  /* Foreign keys */
  CONSTRAINT "Foreign_key01"
    FOREIGN KEY (persona_id)
    REFERENCES futbol.PERSONA(persona_id)
);


CREATE TABLE futbol.ADMINISTRADOR (
  administrador_id  serial NOT NULL,
  persona_id        integer NOT NULL,
  /* Keys */
  CONSTRAINT "ADMINISTRADOR_pkey"
    PRIMARY KEY (administrador_id),
  /* Foreign keys */
  CONSTRAINT "Foreign_key01"
    FOREIGN KEY (persona_id)
    REFERENCES futbol.PERSONA(persona_id)
);

CREATE TABLE futbol.PARTIDO (
  partido_id        serial NOT NULL,
  administrador_id  integer NOT NULL,
  cupo              integer NOT NULL DEFAULT 10,
  fecha_hora        timestamp WITHOUT TIME ZONE NOT NULL,
  lugar             varchar(50) NOT NULL,
  confirmado        boolean DEFAULT true,
  /* Keys */
  CONSTRAINT "PARTIDO_pkey"
    PRIMARY KEY (partido_id),
  /* Foreign keys */
  CONSTRAINT "Foreign_key01"
    FOREIGN KEY (administrador_id)
    REFERENCES futbol.ADMINISTRADOR(administrador_id)
);

CREATE TABLE futbol.PROPUESTA (
  persona_id  integer NOT NULL,
  partido_id  integer NOT NULL,
  motivo      varchar(70),
  fecha_hora  timestamp WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  estado      boolean DEFAULT true,
  modalidad   futbol.modalidad,
  /* Keys */
  CONSTRAINT "PROPUESTA_pkey"
    PRIMARY KEY (persona_id, partido_id),
  /* Foreign keys */
  CONSTRAINT "Foreign_key01"
    FOREIGN KEY (persona_id)
    REFERENCES futbol.PERSONA(persona_id), 
  CONSTRAINT "Foreign_key02"
    FOREIGN KEY (partido_id)
    REFERENCES futbol.PARTIDO(partido_id)
);

CREATE TABLE futbol.AMIGOS_X_PERSONA (
  persona_id  integer NOT NULL,
  amigo_id    integer NOT NULL,
  /* Keys */
  CONSTRAINT "AMIGOS_X_PERSONA_pkey"
    PRIMARY KEY (persona_id, amigo_id),
  /* Foreign keys */
  CONSTRAINT "Foreign_key01"
    FOREIGN KEY (persona_id)
    REFERENCES futbol.PERSONA(persona_id), 
  CONSTRAINT "Foreign_key02"
    FOREIGN KEY (amigo_id)
    REFERENCES futbol.PERSONA(persona_id)
);

CREATE TABLE futbol.CALIFICACION (
  jugador_calificado_id  integer NOT NULL,
  partido_id             integer NOT NULL,
  jugador_califica_id    integer NOT NULL,
  critica                varchar(70) NOT NULL,
  nota                   integer NOT NULL,
  /* Keys */
  CONSTRAINT "CALIFICACION_pkey"
    PRIMARY KEY (jugador_calificado_id, partido_id, jugador_califica_id),
  /* Foreign keys */
  CONSTRAINT "Foreign_key01"
    FOREIGN KEY (jugador_calificado_id)
    REFERENCES futbol.JUGADOR(jugador_id), 
  CONSTRAINT "Foreign_key02"
    FOREIGN KEY (partido_id)
    REFERENCES futbol.PARTIDO(partido_id), 
  CONSTRAINT "Foreign_key03"
    FOREIGN KEY (jugador_califica_id)
    REFERENCES futbol.JUGADOR(jugador_id)
);

CREATE TABLE futbol.INFRACCION (
  infraccion_id  serial NOT NULL,
  motivo         varchar(70) NOT NULL,
  momento        timestamp WITHOUT TIME ZONE NOT NULL,
  jugador_id     integer NOT NULL,
  partido_id     integer NOT NULL,
  /* Keys */
  CONSTRAINT "INFRACCION_pkey"
    PRIMARY KEY (infraccion_id),
  /* Foreign keys */
  CONSTRAINT "Foreign_key01"
    FOREIGN KEY (jugador_id)
    REFERENCES futbol.JUGADOR(jugador_id), 
  CONSTRAINT "Foreign_key02"
    FOREIGN KEY (partido_id)
    REFERENCES futbol.PARTIDO(partido_id)
);

CREATE TABLE futbol.INSCRIPCION (
  inscripcion_id       serial NOT NULL PRIMARY KEY,
  jugador_id           integer NOT NULL,
  estado               boolean NOT NULL DEFAULT true,
  partido_id           integer NOT NULL,
  jugador_remplazo_id  integer,
  /* Foreign keys */
  CONSTRAINT "Foreign_key01"
    FOREIGN KEY (jugador_id)
    REFERENCES futbol.JUGADOR(jugador_id), 
  CONSTRAINT "Foreign_key02"
    FOREIGN KEY (partido_id)
    REFERENCES futbol.PARTIDO(partido_id), 
  CONSTRAINT "Foreign_key03"
    FOREIGN KEY (jugador_remplazo_id)
    REFERENCES futbol.JUGADOR(jugador_id)
);

CREATE TABLE futbol.INSCRIPCION_X_PARTIDO (
  inscripcion_id  serial NOT NULL,
  partido_id      integer NOT NULL,
  equipo          integer NOT NULL,
  /* Keys */
  CONSTRAINT "INSCRIPCION_X_PARTIDO_pkey"
    PRIMARY KEY (inscripcion_id, partido_id),
  /* Checks */
  CONSTRAINT "Check01"
    CHECK (equipo = ANY (ARRAY[0, 1, 2])),
  /* Foreign keys */
  CONSTRAINT "Foreign_key01"
    FOREIGN KEY (inscripcion_id)
    REFERENCES futbol.INSCRIPCION(inscripcion_id), 
  CONSTRAINT "Foreign_key02"
    FOREIGN KEY (partido_id)
    REFERENCES futbol.PARTIDO(partido_id)
);

/* CARGA DE DATOS */

INSERT INTO futbol.PERSONA (persona_id, nombre, email, apodo, fecha_nac) VALUES
  (1, 'Cecilia', 'chechu@gmail.com', 'chechu', '1982-07-29'),
  (2, 'Federico', 'fede tux@hotmail.com', 'fede tux', '1988-04-15'),
  (3, 'Pablo', 'baby on board@yahoo.com', 'baby on board', '1968-11-03'),
  (4, 'Ezequiel', 'ente lujurioso@yahoo.com', 'ente lujurioso', '1985-05-11'),
  (5, 'Jorge', 'pollera@gmail.com', 'pollera', '1969-12-02'),
  (6, 'Sofia', 'linda@yahoo.com', 'linda', '1989-07-10'),
  (7, 'Laura', 'no se@hotmail.com', 'no se', '1960-09-04'),
  (8, 'Matilde', 'hola@yahoo.com', 'hola', '1998-12-30'),
  (9, 'Carolina', 'hincha@yahoo.com', 'hincha', '1976-12-14'),
  (10, 'Matias', 'forro@hotmail.com', 'forro', '1982-09-09'),
  (11, 'Maximiliano', 'cheeky@yahoo.com', 'cheeky', '1988-06-27'),
  (12, 'Carlos', 'carlito@yahoo.com', 'carlito', '1973-07-31'),
  (13, 'Rodolfo', 'roudolf@gmail.com', 'roudolf', '1965-02-19'),
  (14, 'Roberto', 'robert@yahoo.com', 'robert', '1992-01-11'),
  (15, 'Paula', 'carnes@yahoo.com', 'carnes', '1992-01-09'),
  (16, 'Pablo', 'pabli@hotmail.com', 'pabli', '1977-08-17'),
  (17, 'Florencia', 'florchu@yahoo.com', 'florchu', '1973-03-10'),
  (18, 'Agustina', 'agu@hotmail.com', 'agu', '1983-06-23'),
  (19, 'Joaquin', 'joaco@gmail.com', 'joaco', '1983-04-09'),
  (20, 'Agostina', 'agos@yahoo.com', 'agos', '1987-12-26'),
  (21, 'Georgina', 'georgi@yahoo.com', 'georgi', '1961-09-04'),
  (22, 'Carla', 'car@hotmail.com', 'car', '1990-08-26'),
  (23, 'Melisa', 'meli@hotmail.com', 'meli', '1987-05-30'),
  (24, 'Martina', 'marta@gmail.com', 'marta', '1981-11-04'),
  (25, 'Martin', 'tuto@hotmail.com', 'tuto', '1960-09-09'),
  (26, 'Lorenzo', 'lorenzo@yahoo.com', 'lorenzo', '1977-06-29'),
  (27, 'Juan', 'juan@gmail.com', 'juan', '1987-12-04'),
  (28, 'Ignacio', 'nacho@hotmail.com', 'nacho', '1988-04-01'),
  (29, 'Pedro', 'pepe@gmail.com', 'pepe', '1985-04-04'),
  (30, 'Tomas', 'tomi@hotmail.com', 'tomi', '1963-03-28');
INSERT INTO futbol.JUGADOR (jugador_id, persona_id, handicap) VALUES
  (1, 5, 10),
  (2, 2, 9),
  (3, 10, 8),
  (4, 1, 7),
  (5, 3, 6),
  (6, 4, 5),
  (7, 7, 1),
  (8, 6, 2),
  (9, 8, 3),
  (10, 9, 4),
  (11, 11, 10),
  (12, 13, 10),
  (13, 12, 9),
  (14, 14, 8),
  (15, 18, 7),
  (16, 17, 6),
  (17, 16, 5),
  (18, 15, 4),
  (19, 19, 3),
  (20, 23, 1),
  (21, 22, 1),
  (22, 21, 0),
  (23, 20, 0),
  (24, 24, 1),
  (25, 28, 2);
INSERT INTO futbol.ADMINISTRADOR (administrador_id, persona_id) VALUES
  (1, 30);
INSERT INTO futbol.AMIGOS_X_PERSONA (persona_id, amigo_id) VALUES
  (1, 2),
  (1, 3),
  (1, 4),
  (1, 5),
  (15, 27),
  (15, 28),
  (15, 29),
  (15, 30),
  (2, 3),
  (5, 6),
  (4, 10),
  (15, 8),
  (16, 8),
  (8, 9),
  (10, 27),
  (10, 28),
  (10, 29),
  (2, 7),
  (2, 8),
  (3, 30);
INSERT INTO futbol.PARTIDO (partido_id, administrador_id, cupo, fecha_hora, lugar, confirmado) VALUES
  (1, 1, 10, '2014-09-26 09:00:00', 'Lugano', true),
  (2, 1, 10, '2014-11-15 00:00:00', 'Villa Crespo', true),
  (3, 1, 10, '2015-02-03 00:00:00', 'Barracas', false),
  (4, 1, 10, '2014-12-28 00:00:00', 'Adrogué', false),
  (5, 1, 10, '2014-10-12 00:00:00', 'Flores', false),
  (6, 1, 10, '2015-08-15 00:00:00', 'Palermo', false);
INSERT INTO futbol.INSCRIPCION (inscripcion_id, jugador_id, estado, partido_id, jugador_remplazo_id) VALUES
  (2, 2, true, 1, 1),
  (3, 3, true, 1, 1),
  (4, 4, true, 1, 1),
  (5, 5, true, 1, 1),
  (6, 6, true, 1, 1),
  (7, 7, true, 1, 1),
  (8, 8, true, 1, 1),
  (9, 9, true, 1, 1),
  (10, 10, true, 1, 1),
  (1, 1, true, 1, 1);
INSERT INTO futbol.INSCRIPCION_X_PARTIDO (inscripcion_id, partido_id, equipo) VALUES
  (1, 1, 1),
  (2, 1, 1),
  (3, 1, 1),
  (4, 1, 1),
  (5, 1, 1),
  (6, 1, 2),
  (7, 1, 2),
  (8, 1, 2),
  (9, 1, 2),
  (10, 1, 2);
INSERT INTO futbol.CALIFICACION (jugador_calificado_id, partido_id, jugador_califica_id, critica, nota) VALUES
  (2, 1, 1, 'mal', 3),
  (3, 1, 1, 'mal', 1),
  (4, 1, 1, 'bien', 8),
  (5, 1, 1, 'bien', 6),
  (6, 1, 1, 'mal', 5),
  (7, 1, 1, 'mal', 3),
  (2, 1, 3, 'mal', 5),
  (1, 1, 3, 'bien', 10),
  (3, 1, 3, 'bien', 8),
  (4, 1, 3, 'mal', 1),
  (1, 1, 10, 'bien', 6),
  (2, 1, 10, 'mal', 3),
  (3, 1, 10, 'bien', 10),
  (4, 1, 10, 'bien', 10),
  (5, 1, 10, 'mal', 2),
  (6, 1, 10, 'mal', 5),
  (7, 1, 10, 'bien', 7),
  (8, 1, 10, 'mal', 2),
  (9, 1, 10, 'bien', 7),
  (1, 1, 2, 'mal', 3),
  (10, 1, 2, 'mal', 1),
  (1, 1, 9, 'mal', 4),
  (2, 1, 9, 'bien', 7),
  (3, 1, 8, 'mal', 3),
  (4, 1, 8, 'bien', 8),
  (5, 1, 7, 'bien', 6),
  (7, 1, 6, 'bien', 9),
  (6, 1, 7, 'bien', 6),
  (8, 1, 5, 'mal', 4),
  (9, 1, 5, 'bien', 10),
  (10, 1, 5, 'mal', 4);
INSERT INTO futbol.INFRACCION (infraccion_id, motivo, momento, jugador_id, partido_id) VALUES
  (1, 'mala', '2014-09-26 09:00:00', 1, 1),
  (2, 'falta grave', '2014-09-26 09:00:00', 2, 1),
  (3, 'patada ninja', '2014-09-26 09:00:00', 3, 1),
  (4, 'piña', '2014-09-26 09:00:00', 4, 1),
  (5, 'codazo al ojo', '2014-09-26 09:00:00', 5, 1),
  (6, 'sucio', '2014-09-26 09:00:00', 6, 1),
  (7, 'piña a la cara', '2014-09-26 09:00:00', 7, 1),
  (8, 'patada al estomago', '2014-09-26 09:00:00', 8, 1),
  (9, 'piña a la pera', '2014-09-26 09:00:00', 9, 1),
  (10, 'patada al hombro', '2014-09-26 09:00:00', 10, 1),
  (11, 'piña', '2014-09-26 09:00:00', 1, 1),
  (12, 'patada', '2014-09-26 09:00:00', 2, 1),
  (13, 'otra', '2014-09-26 09:00:00', 1, 1),
  (14, 'otra', '2014-09-26 09:00:00', 1, 1),
  (15, 'otra', '2014-09-26 09:00:00', 2, 1),
  (16, 'otra', '2014-09-26 09:00:00', 2, 1),
  (17, 'otra', '2014-09-26 09:00:00', 3, 1),
  (18, 'otra', '2014-09-26 09:00:00', 3, 1),
  (19, 'otra', '2014-09-26 09:00:00', 3, 1),
  (20, 'otra', '2014-09-26 09:00:00', 3, 1);

/* CREACION DE VISTAS, TRIGGERS Y FUNCIONES */


/* Punto a */

CREATE VIEW futbol.JugadoresMalos 
AS 
SELECT * FROM futbol.JUGADOR WHERE handicap <= 5;

/* Pruebas
SELECT * FROM futbol.JugadoresMalos
*/

/* Punto b */

CREATE VIEW futbol.JugadoresTraicioneros 
AS 
SELECT * FROM futbol.JUGADOR JJ
WHERE 
(SELECT COUNT(*) FROM futbol.INFRACCION II WHERE JJ.jugador_id = II.jugador_id) > 3;

/* Pruebas
SELECT * FROM futbol.JugadoresTraicioneros
*/ 

/* Punto c */

CREATE VIEW futbol.JugadoresQuePodrianMejorar
AS
SELECT VV.* FROM futbol.JugadoresMalos VV, futbol.PERSONA PP 
WHERE
(VV.persona_id = PP.persona_id)
AND
date_part('year',age(PP.fecha_nac)) < 25;

/* Pruebas
SELECT * FROM futbol.JugadoresQuePodrianMejorar
*/

/* Punto d */

CREATE FUNCTION futbol.DarDeBaja(idJug integer, idPar integer)
RETURNS void AS $$
BEGIN
  UPDATE futbol.INSCRIPCION SET estado = false WHERE jugador_id = idJug AND partido_id = idPar;
END;
$$ LANGUAGE plpgsql;

/* Pruebas
select futbol.darDeBaja(10,1)
select futbol.darDeBaja(1,1)
SELECT * FROM futbol.INSCRIPCION
*/

/* Punto e */

CREATE FUNCTION futbol.AgregarInfraccionFuncion()
RETURNS trigger AS $AgregarInfraccionFuncion$
BEGIN
         INSERT INTO futbol.INFRACCION
         VALUES((SELECT MAX(infraccion_id) FROM futbol.INFRACCION)+1,'se dio de baja sin remplazo',current_timestamp,NEW.jugador_id, NEW.partido_id);
    RETURN NEW;
END;
$AgregarInfraccionFuncion$ LANGUAGE plpgsql;

CREATE TRIGGER AgregarInfraccion AFTER UPDATE
ON futbol.INSCRIPCION
FOR EACH ROW 
WHEN ((OLD.estado IS DISTINCT FROM NEW.estado) AND (NEW.estado = false) AND (NEW.jugador_remplazo_id IS NULL))
EXECUTE PROCEDURE futbol.AgregarInfraccionFuncion();

/* Pruebas
select count(*) from futbol.INFRACCION
select count(*) from futbol.INSCRIPCION
update futbol.INSCRIPCION set estado = false, jugador_remplazo_id = null
select count(*) from futbol.INFRACCION
*/
