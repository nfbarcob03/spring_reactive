
CREATE TABLE pelicula (
    id INT PRIMARY KEY AUTO_INCREMENT,
    idomdb VARCHAR(20),
    nombre VARCHAR(100)
);

CREATE TABLE horario (
  id INT PRIMARY KEY AUTO_INCREMENT,
  idPelicula INT,
  horaInicio VARCHAR(20),
  fechaInicio DATE,
  fechaFin DATE
);

CREATE TABLE calificacion (
  id INT PRIMARY KEY AUTO_INCREMENT,
  idPelicula INT,
  calificacion DOUBLE,
  usuario VARCHAR(20)
);

ALTER TABLE horario ADD FOREIGN KEY (idPelicula) REFERENCES pelicula (id);

ALTER TABLE calificacion ADD FOREIGN KEY (idPelicula) REFERENCES pelicula (id);

-- Insertar datos
INSERT INTO pelicula (id, idomdb, nombre) VALUES
(1, 'tt0232500', 'The Fast and the Furious'),
(2, 'tt0322259', '2 Fast 2 Furious'),
(3, 'tt0463985', 'The Fast and the Furious: Tokyo Drift'),
(4, 'tt1013752', 'Fast & Furious'),
(5, 'tt1596343', 'Fast Five'),
(6, 'tt1905041', 'Fast & Furious 6'),
(7, 'tt2820852', 'Furious 7'),
(8, 'tt4630562', 'The Fate of the Furious'),
(9, 'tt5433138', 'F9: The Fast Saga');

-- Insertar calificaciones para las películas
INSERT INTO calificacion (id, idPelicula, calificacion, usuario) VALUES
(1, 1, 4.5, 'usuario1'),
(2, 2, 3.8, 'usuario2'),
(3, 3, 4.2, 'usuario3'),
(4, 4, 4.0, 'usuario4'),
(5, 5, 4.7, 'usuario5'),
(6, 6, 3.5, 'usuario6'),
(7, 7, 4.9, 'usuario7'),
(8, 8, 4.1, 'usuario8'),
(9, 9, 4.6, 'usuario9');


-- Insertar horarios para las películas
INSERT INTO horario (id, idPelicula, horaInicio, fechaInicio, fechaFin) VALUES
(1, 1, '18:00', '2023-01-01', '2023-01-15'),
(2, 2, '20:30', '2023-02-01', '2023-02-15'),
(3, 3, '15:45', '2023-03-01', '2023-03-15'),
(4, 4, '17:15', '2023-04-01', '2023-04-15'),
(5, 5, '19:45', '2023-05-01', '2023-05-15'),
(6, 6, '14:00', '2023-06-01', '2023-06-15'),
(7, 7, '22:00', '2023-07-01', '2023-07-15'),
(8, 8, '16:30', '2023-08-01', '2023-08-15'),
(9, 9, '21:15', '2023-09-01', '2023-09-15');