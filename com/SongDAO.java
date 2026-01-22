package com;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO {

    private final String url = "jdbc:postgresql://127.0.0.1:5432/java";
    private final String user = "postgres";
    private final String password = "malika";

    public void save(Song songObj) {
        try {
            Connection c = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO songs (title, artist, duration) VALUES (?, ?, ?)"
            );
            ps.setString(1, songObj.getTitle());
            ps.setString(2, songObj.getArtist());
            ps.setInt(3, songObj.getDuration());
            ps.executeUpdate();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Song> findAll() {
        List<Song> songs = new ArrayList<>();
        try {
            Connection c = DriverManager.getConnection(url, user, password);
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT id, title, artist, duration FROM songs");

            while (rs.next()) {
                songs.add(new Song(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("artist"),
                        rs.getInt("duration")
                ));
            }

            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return songs;
    }

    public void updateDurationById(int id, int duration) {
        try {
            Connection c = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = c.prepareStatement(
                    "UPDATE songs SET duration = ? WHERE id = ?"
            );
            ps.setInt(1, duration);
            ps.setInt(2, id);
            ps.executeUpdate();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateArtistById(int id, String artist) {
        try {
            Connection c = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = c.prepareStatement(
                    "UPDATE songs SET artist = ? WHERE id = ?"
            );
            ps.setString(1, artist);
            ps.setInt(2, id);
            ps.executeUpdate();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        try {
            Connection c = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = c.prepareStatement("DELETE FROM songs WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
