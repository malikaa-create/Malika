package com.DAO;

import com.data.PostgresDB;
import com.models.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO {

    // CREATE
    public void create(Artist artist) throws SQLException {
        String sql = "INSERT INTO artist(name, genre, country, formed_year, is_active, popularity_score) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, artist.getName());
            ps.setString(2, artist.getGenre());
            ps.setString(3, artist.getCountry());
            ps.setInt(4, artist.getFormedYear());
            ps.setBoolean(5, artist.isActive());
            ps.setInt(6, artist.getPopularityScore());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    artist.setArtistId(rs.getInt(1));
                }
            }
        }
    }

    // READ ALL
    public List<Artist> readAll() throws SQLException {
        List<Artist> artists = new ArrayList<>();
        String sql = "SELECT * FROM artist ORDER BY name";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                artists.add(new Artist(
                        rs.getInt("artist_id"),
                        rs.getString("name"),
                        rs.getString("genre"),
                        rs.getString("country"),
                        rs.getInt("formed_year"),
                        rs.getBoolean("is_active"),
                        rs.getInt("popularity_score")
                ));
            }
        }
        return artists;
    }

    // READ BY ID
    public Artist readById(int artistId) throws SQLException {
        String sql = "SELECT * FROM artist WHERE artist_id = ?";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, artistId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Artist(
                            rs.getInt("artist_id"),
                            rs.getString("name"),
                            rs.getString("genre"),
                            rs.getString("country"),
                            rs.getInt("formed_year"),
                            rs.getBoolean("is_active"),
                            rs.getInt("popularity_score")
                    );
                }
            }
        }
        return null;
    }

    // FILTER BY GENRE
    public List<Artist> filterByGenre(String genre) throws SQLException {
        List<Artist> artists = new ArrayList<>();
        String sql = "SELECT * FROM artist WHERE LOWER(genre) = LOWER(?) ORDER BY popularity_score DESC";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, genre);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    artists.add(new Artist(
                            rs.getInt("artist_id"),
                            rs.getString("name"),
                            rs.getString("genre"),
                            rs.getString("country"),
                            rs.getInt("formed_year"),
                            rs.getBoolean("is_active"),
                            rs.getInt("popularity_score")
                    ));
                }
            }
        }
        return artists;
    }

    // UPDATE
    public boolean update(Artist artist) throws SQLException {
        String sql = "UPDATE artist SET name=?, genre=?, country=?, formed_year=?, " +
                "is_active=?, popularity_score=? WHERE artist_id=?";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, artist.getName());
            ps.setString(2, artist.getGenre());
            ps.setString(3, artist.getCountry());
            ps.setInt(4, artist.getFormedYear());
            ps.setBoolean(5, artist.isActive());
            ps.setInt(6, artist.getPopularityScore());
            ps.setInt(7, artist.getArtistId());

            return ps.executeUpdate() > 0;
        }
    }

    // DELETE
    public boolean deleteById(int artistId) throws SQLException {
        String sql = "DELETE FROM artist WHERE artist_id = ?";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, artistId);
            return ps.executeUpdate() > 0;
        }
    }

    // CUSTOM - Update popularity score
    public boolean updatePopularity(int artistId, int newScore) throws SQLException {
        String sql = "UPDATE artist SET popularity_score = ? WHERE artist_id = ?";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, newScore);
            ps.setInt(2, artistId);
            return ps.executeUpdate() > 0;
        }
    }
}

