--Verifica si existen las tablas
--De ser así, las elimina.
DROP TABLE IF EXISTS calificacion;
DROP TABLE IF EXISTS comentario;
DROP TABLE IF EXISTS marcador;
DROP TABLE IF EXISTS tema;
DROP TABLE IF EXISTS informador;
DROP TABLE IF EXISTS comentarista;
DROP TABLE IF EXISTS administrador;


CREATE TABLE comentarista(
	correo text NOT NULL, 
	nombre text NOT NULL,
	contrasenia text NOT NULL,
	estado boolean NOT NULL,
	PRIMARY KEY (correo)	
);

CREATE TABLE administrador(
	correo text NOT NULL, 
	nombre text NOT NULL,
	contrasenia text NOT NULL,
	PRIMARY KEY (correo)	
);

CREATE TABLE informador(
	correo text NOT NULL, 
	nombre text NOT NULL,
	contrasenia text NOT NULL,
	PRIMARY KEY (correo)	
);

CREATE TABLE tema(
	nombre text NOT NULL, 
	color text NOT NULL,
	PRIMARY KEY (nombre),
	correo_informador text REFERENCES informador (correo)
);

CREATE TABLE marcador(
	id_marcador serial NOT NULL, 
	longitud double precision NOT NULL,
	latitud double precision NOT NULL,
	descripcion text NOT NULL,
	datos_utiles text NOT NULL,
	PRIMARY KEY (id_marcador),
	nombre_tema text REFERENCES tema (nombre) --ON DELETE CASCADE
);

CREATE TABLE comentario(
	texto text NOT NULL,
	id_marcador integer REFERENCES marcador (id_marcador), --ON DELETE CASCADE,
	correo_comentarista text REFERENCES comentarista (correo), --ON DELETE CASCADE
	PRIMARY KEY (id_marcador, correo_comentarista)
);

CREATE TABLE calificacion(
	calificacion integer NOT NULL,
	FOREIGN KEY (id_marcador, correo_comentarista) REFERENCES comentario (id_marcador, correo_comentarista), 
	correo_calificador text REFERENCES comentarista (correo),
	id_marcador integer NOT NULL,
	correo_comentarista text NOT NULL,
	PRIMARY KEY (id_marcador, correo_comentarista, correo_calificador)
);

