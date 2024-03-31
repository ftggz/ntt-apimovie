USE movies;

INSERT INTO movies (title) VALUES ('The Lord of the Rings');
INSERT INTO movies (title) VALUES ('Pulp Fiction');
INSERT INTO movies (title) VALUES ('Forrest Gump');
INSERT INTO movies (title) VALUES ('Kill Bill');
INSERT INTO movies (title) VALUES ('The Godfather');
INSERT INTO movies (title) VALUES ('The Shawshank Redemption');

INSERT INTO genres (genre_name) VALUES ('Crime');
INSERT INTO genres (genre_name) VALUES ('Thriller');
INSERT INTO genres (genre_name) VALUES ('Romance');
INSERT INTO genres (genre_name) VALUES ('Adventure');
INSERT INTO genres (genre_name) VALUES ('Fantasy');
INSERT INTO genres (genre_name) VALUES ('Animation');
INSERT INTO genres (genre_name) VALUES ('Horror');

INSERT INTO actors (name) VALUES ('Morgan Freeman');
INSERT INTO actors (name) VALUES ('John Travolta');
INSERT INTO actors (name) VALUES ('Uma Thurman');
INSERT INTO actors (name) VALUES ('Marlon Brando');
INSERT INTO actors (name) VALUES ('Al Pacino');
INSERT INTO actors (name) VALUES ('Tom Hanks');

INSERT INTO studios (name, country) VALUES ('Disney', 'Estados Unidos');
INSERT INTO studios (name, country) VALUES ('20th Century Fox', 'Estados Unidos');
INSERT INTO studios (name, country) VALUES ('Miramax', 'Estados Unidos');
INSERT INTO studios (name, country) VALUES ('Studio Ghibli', 'Japão');
INSERT INTO studios (name, country) VALUES ('Warner Bros.', 'Estados Unidos');
INSERT INTO studios (name, country) VALUES ('Columbia Pictures', 'Estados Unidos');

INSERT INTO directors (name) VALUES ('Quentin Tarantino');
INSERT INTO directors (name) VALUES ('Francis Ford Coppola');
INSERT INTO directors (name) VALUES ('Martin Scorsese');
INSERT INTO directors (name) VALUES ('Steven Spielberg');
INSERT INTO directors (name) VALUES ('Christopher Nolan');
INSERT INTO directors (name) VALUES ('Hayao Miyazaki');

INSERT INTO users (name, username, email, password) VALUES ('Ricardo', 'rick', 'ricardo@email.com', 'ricardo1010');
INSERT INTO users (name, username, email, password) VALUES ('Juliana', 'juju', 'juju@email.com', 'juju1010');
INSERT INTO users (name, username, email, password) VALUES ('João', 'joao', 'joao@email.com', 'joao1010');
INSERT INTO users (name, username, email, password) VALUES ('Fernando', 'ftg', 'fernando@gmail.com', 'ftg1010');
INSERT INTO users (name, username, email, password) VALUES ('Felipe', 'flc', 'felipeflc@gmail.com', 'flc1010');


INSERT INTO franchises (name) VALUES ('Harry Potter');
INSERT INTO franchises (name) VALUES ('Star Wars');
INSERT INTO franchises (name) VALUES ('Indiana Jones');
INSERT INTO franchises (name) VALUES ('Jurassic Park');

INSERT INTO streamings (name, url) VALUES ('HBO Max', 'www.hbomax.com');
INSERT INTO streamings (name, url) VALUES ('Disney+', 'www.disneyplus.com');
INSERT INTO streamings (name, url) VALUES ('Hulu', 'www.hulu.com');
INSERT INTO streamings (name, url) VALUES ('Peacock', 'https://www.peacocktv.com');