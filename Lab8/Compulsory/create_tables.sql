DROP TABLE albums CASCADE CONSTRAINTS;
DROP TABLE artists CASCADE CONSTRAINTS;
DROP TABLE genres CASCADE CONSTRAINTS;
DROP TABLE album_genre CASCADE CONSTRAINTS;
CREATE TABLE artists (
    id INT NOT NULL PRIMARY KEY,
    name VARCHAR2(30)
)
/
CREATE TABLE genres (
    id INT NOT NULL PRIMARY KEY,
    name VARCHAR2(20)
)
/
CREATE TABLE albums (
    id INT NOT NULL PRIMARY KEY,
    release_year NUMBER,
    title VARCHAR2(30),
    id_artist INT NOT NULL,
    CONSTRAINT fk_albums_artist FOREIGN KEY (id_artist) REFERENCEs artists(id)
)
/

CREATE TABLE album_genre (
    id_album INT NOT NULL,
    id_genre INT NOT NULL,
    CONSTRAINT fk_album_genre_id_album FOREIGN KEY (id_album) REFERENCES albums(id),
    CONSTRAINT fk_album_genre_id_genre FOREIGN KEY (id_genre) REFERENCES genres(id)
)
/
COMMIT;