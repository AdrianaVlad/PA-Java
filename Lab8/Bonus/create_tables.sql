DROP TABLE albums CASCADE CONSTRAINTS;
DROP TABLE artists CASCADE CONSTRAINTS;
DROP TABLE genres CASCADE CONSTRAINTS;
DROP TABLE album_genre CASCADE CONSTRAINTS;
DROP TABLE playlists CASCADE CONSTRAINTS;
DROP TABLE playlist_albums CASCADE CONSTRAINTS;
CREATE TABLE artists (
    id INT NOT NULL PRIMARY KEY,
    name VARCHAR2(100)
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
    title VARCHAR2(100),
    artist_id INT NOT NULL,
    CONSTRAINT fk_albums_artist FOREIGN KEY (artist_id) REFERENCEs artists(id)
)
/

CREATE TABLE album_genre (
    album_id INT NOT NULL,
    genre_id INT NOT NULL,
    CONSTRAINT fk_album_genre_album_id FOREIGN KEY (album_id) REFERENCES albums(id),
    CONSTRAINT fk_album_genre_genre_id FOREIGN KEY (genre_id) REFERENCES genres(id)
)
/

CREATE TABLE playlists (
    id INT NOT NULL PRIMARY KEY,
    name VARCHAR2(50),
    creation_timestamp TIMESTAMP
)
/

CREATE TABLE playlist_albums(
    playlist_id INT NOT NULL,
    album_id INT NOT NULL,
    CONSTRAINT fk_playlist_albums_album_id FOREIGN KEY (album_id) REFERENCES albums(id),
    CONSTRAINT fk_playlist_albums_playlist_id FOREIGN KEY (playlist_id) REFERENCES playlists(id)
)
/
COMMIT;