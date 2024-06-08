DROP DATABASE IF EXISTS MovieDB;
CREATE DATABASE MovieDB;

USE MovieDB;

CREATE TABLE Director (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE Actor (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE Movie (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    directorId INT,
    year INT,
    FOREIGN KEY (directorId) REFERENCES Director(id)
);

CREATE TABLE Movie_Actor (
    movieId INT,
    actorId INT,
    PRIMARY KEY (movieId, actorId),
    FOREIGN KEY (movieId) REFERENCES Movie(id) ON DELETE CASCADE,
    FOREIGN KEY (actorId) REFERENCES Actor(id)
);

INSERT INTO Director (id, name) VALUES 
(1, 'Christopher Nolan'),
(2, 'Steven Spielberg'),
(3, 'Quentin Tarantino'),
(4, 'James Cameron'),
(5, 'Peter Jackson'),
(6, 'Martin Scorsese'),
(7, 'Ridley Scott'),
(8, 'David Fincher'),
(9, 'Alfred Hitchcock'),
(10, 'Stanley Kubrick');

INSERT INTO Actor (id, name) VALUES 
(1, 'Leonardo DiCaprio'),
(2, 'Brad Pitt'),
(3, 'Johnny Depp'),
(4, 'Tom Hanks'),
(5, 'Morgan Freeman'),
(6, 'Robert De Niro'),
(7, 'Matt Damon'),
(8, 'Christian Bale'),
(9, 'Harrison Ford'),
(10, 'Scarlett Johansson'),
(11, 'Natalie Portman'),
(12, 'Samuel L. Jackson'),
(13, 'Al Pacino'),
(14, 'Denzel Washington'),
(15, 'Tom Cruise'),
(16, 'Keanu Reeves'),
(17, 'Emma Stone'),
(18, 'Jennifer Lawrence'),
(19, 'Hugh Jackman'),
(20, 'Michael Fassbender'),
(21, 'Charlize Theron'),
(22, 'Kate Winslet'),
(23, 'Mark Ruffalo'),
(24, 'Benedict Cumberbatch'),
(25, 'Ryan Reynolds'),
(26, 'Chris Hemsworth'),
(27, 'Anne Hathaway'),
(28, 'Amy Adams'),
(29, 'Gal Gadot'),
(30, 'Chris Evans');

INSERT INTO Movie (id, title, directorId, year) VALUES 
(1, 'Inception', 1, 2010),
(2, 'Jurassic Park', 2, 1993),
(3, 'Pulp Fiction', 3, 1994),
(4, 'Avatar', 4, 2009),
(5, 'The Lord of the Rings', 5, 2001),
(6, 'The Wolf of Wall Street', 6, 2013),
(7, 'Gladiator', 7, 2000),
(8, 'Fight Club', 8, 1999),
(9, 'Psycho', 9, 1960),
(10, 'The Shining', 10, 1980);

INSERT INTO Movie_Actor (movieId, actorId) VALUES 
(1, 1), (1, 8), (1, 2),
(2, 9), (2, 5), (2, 4),
(3, 2), (3, 12), (3, 6),
(4, 19), (4, 29), (4, 10),
(5, 13), (5, 11), (5, 3),
(6, 1), (6, 2), (6, 6),
(7, 7), (7, 20), (7, 21),
(8, 2), (8, 7), (8, 24),
(9, 13), (9, 14), (9, 15),
(10, 16), (10, 27), (10, 28);