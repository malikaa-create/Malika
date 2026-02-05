package com.DAO;

import com.data.PostgresDB;
import com.models.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO {

    // CREATE
    public void create(Song song) throws SQLException {
        String sql = "INSERT INTO song(title, artist_id, album, duration_seconds, " +
                "release_year, genre, plays_count, rating) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, song.getTitle());
            ps.setInt(2, song.getArtistId());
            ps.setString(3, song.getAlbum());
            ps.setInt(4, song.getDurationSeconds());
            ps.setInt(5, song.getReleaseYear());
            ps.setString(6, song.getGenre());
            ps.setInt(7, song.getPlaysCount());
            ps.setDouble(8, song.getRating());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    song.setSongId(rs.getInt(1));
                }
            }
        }
    }

    // READ ALL
    public List<Song> readAll() throws SQLException {
        List<Song> songs = new ArrayList<>();
        String sql = "SELECT * FROM song ORDER BY title";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                songs.add(extractSongFromResultSet(rs));
            }
        }
        return songs;
    }

    // READ BY ID
    public Song readById(int songId) throws SQLException {
        String sql = "SELECT * FROM song WHERE song_id = ?";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, songId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return extractSongFromResultSet(rs);
                }
            }
        }
        return null;
    }

    // READ BY ARTIST
    public List<Song> readByArtist(int artistId) throws SQLException {
        List<Song> songs = new ArrayList<>();
        String sql = "SELECT * FROM song WHERE artist_id = ? ORDER BY release_year DESC";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, artistId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    songs.add(extractSongFromResultSet(rs));
                }
            }
        }
        return songs;
    }

    // FILTER BY GENRE
    public List<Song> filterByGenre(String genre) throws SQLException {
        List<Song> songs = new ArrayList<>();
        String sql = "SELECT * FROM song WHERE LOWER(genre) = LOWER(?) ORDER BY rating DESC";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, genre);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    songs.add(extractSongFromResultSet(rs));
                }
            }
        }
        return songs;
    }

    // FILTER BY MIN RATING
    public List<Song> filterByMinRating(double minRating) throws SQLException {
        List<Song> songs = new ArrayList<>();
        String sql = "SELECT * FROM song WHERE rating >= ? ORDER BY rating DESC";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, minRating);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    songs.add(extractSongFromResultSet(rs));
                }
            }
        }
        return songs;
    }

    // READ SORTED BY PLAYS
    public List<Song> readAllSortedByPlays() throws SQLException {
        List<Song> songs = new ArrayList<>();
        String sql = "SELECT * FROM song ORDER BY plays_count DESC LIMIT 100";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                songs.add(extractSongFromResultSet(rs));
            }
        }
        return songs;
    }

    // UPDATE
    public boolean update(Song song) throws SQLException {
        String sql = "UPDATE song SET title=?, artist_id=?, album=?, duration_seconds=?, " +
                "release_year=?, genre=?, plays_count=?, rating=? WHERE song_id=?";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, song.getTitle());
            ps.setInt(2, song.getArtistId());
            ps.setString(3, song.getAlbum());
            ps.setInt(4, song.getDurationSeconds());
            ps.setInt(5, song.getReleaseYear());
            ps.setString(6, song.getGenre());
            ps.setInt(7, song.getPlaysCount());
            ps.setDouble(8, song.getRating());
            ps.setInt(9, song.getSongId());

            return ps.executeUpdate() > 0;
        }
    }

    // UPDATE RATING
    public boolean updateRating(int songId, double newRating) throws SQLException {
        String sql = "UPDATE song SET rating = ? WHERE song_id = ?";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, newRating);
            ps.setInt(2, songId);
            return ps.executeUpdate() > 0;
        }
    }

    // INCREMENT PLAYS
    public boolean incrementPlays(int songId) throws SQLException {
        String sql = "UPDATE song SET plays_count = plays_count + 1 WHERE song_id = ?";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, songId);
            return ps.executeUpdate() > 0;
        }
    }

    // DELETE
    public boolean deleteById(int songId) throws SQLException {
        String sql = "DELETE FROM song WHERE song_id = ?";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, songId);
            return ps.executeUpdate() > 0;
        }
    }

    // Helper method to extract Song from ResultSet
    private Song extractSongFromResultSet(ResultSet rs) throws SQLException {
        return new Song(
                rs.getInt("song_id"),
                rs.getString("title"),
                rs.getInt("artist_id"),
                rs.getString("album"),
                rs.getInt("duration_seconds"),
                rs.getInt("release_year"),
                rs.getString("genre"),
                rs.getInt("plays_count"),
                rs.getDouble("rating")
        );
    }
}