package com.DAO;

import com.data.PostgresDB;
import com.models.Playlist;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {

    // CREATE
    public void create(Playlist playlist) throws SQLException {
        String sql = "INSERT INTO playlist(name, description, created_date, is_public, " +
                "total_duration, song_count) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, playlist.getName());
            ps.setString(2, playlist.getDescription());
            ps.setDate(3, Date.valueOf(playlist.getCreatedDate()));
            ps.setBoolean(4, playlist.isPublic());
            ps.setInt(5, playlist.getTotalDuration());
            ps.setInt(6, playlist.getSongCount());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    playlist.setPlaylistId(rs.getInt(1));
                }
            }
        }
    }

    // READ ALL
    public List<Playlist> readAll() throws SQLException {
        List<Playlist> playlists = new ArrayList<>();
        String sql = "SELECT * FROM playlist ORDER BY created_date DESC";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                playlists.add(extractPlaylistFromResultSet(rs));
            }
        }
        return playlists;
    }

    // READ BY ID
    public Playlist readById(int playlistId) throws SQLException {
        String sql = "SELECT * FROM playlist WHERE playlist_id = ?";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlistId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return extractPlaylistFromResultSet(rs);
                }
            }
        }
        return null;
    }

    // READ PUBLIC PLAYLISTS
    public List<Playlist> readPublic() throws SQLException {
        List<Playlist> playlists = new ArrayList<>();
        String sql = "SELECT * FROM playlist WHERE is_public = true ORDER BY song_count DESC";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                playlists.add(extractPlaylistFromResultSet(rs));
            }
        }
        return playlists;
    }

    // ADD SONG TO PLAYLIST
    public boolean addSong(int playlistId, int songId, int position) throws SQLException {
        String sql = "INSERT INTO playlist_songs(playlist_id, song_id, position) VALUES (?, ?, ?)";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            ps.setInt(2, songId);
            ps.setInt(3, position);

            return ps.executeUpdate() > 0;
        }
    }

    // REMOVE SONG FROM PLAYLIST
    public boolean removeSong(int playlistId, int songId) throws SQLException {
        String sql = "DELETE FROM playlist_songs WHERE playlist_id = ? AND song_id = ?";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            ps.setInt(2, songId);

            return ps.executeUpdate() > 0;
        }
    }

    // GET SONGS IN PLAYLIST
    public List<Integer> getSongsInPlaylist(int playlistId) throws SQLException {
        List<Integer> songIds = new ArrayList<>();
        String sql = "SELECT song_id FROM playlist_songs WHERE playlist_id = ? ORDER BY position";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlistId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    songIds.add(rs.getInt("song_id"));
                }
            }
        }
        return songIds;
    }

    // UPDATE
    public boolean update(Playlist playlist) throws SQLException {
        String sql = "UPDATE playlist SET name=?, description=?, is_public=?, " +
                "total_duration=?, song_count=? WHERE playlist_id=?";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, playlist.getName());
            ps.setString(2, playlist.getDescription());
            ps.setBoolean(3, playlist.isPublic());
            ps.setInt(4, playlist.getTotalDuration());
            ps.setInt(5, playlist.getSongCount());
            ps.setInt(6, playlist.getPlaylistId());

            return ps.executeUpdate() > 0;
        }
    }

    // DELETE
    public boolean deleteById(int playlistId) throws SQLException {
        String sql = "DELETE FROM playlist WHERE playlist_id = ?";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            return ps.executeUpdate() > 0;
        }
    }

    // Helper method
    private Playlist extractPlaylistFromResultSet(ResultSet rs) throws SQLException {
        return new Playlist(
                rs.getInt("playlist_id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getDate("created_date").toLocalDate(),
                rs.getBoolean("is_public"),
                rs.getInt("total_duration"),
                rs.getInt("song_count")
        );
    }
}