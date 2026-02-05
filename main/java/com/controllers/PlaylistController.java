package com.controllers;

import com.DAO.PlaylistDAO;
import com.DTO.PlaylistDTO;
import com.models.Playlist;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    private final PlaylistDAO playlistDAO = new PlaylistDAO();

    // GET all playlists
    @GetMapping
    public List<PlaylistDTO> getAll() throws SQLException {
        List<Playlist> playlists = playlistDAO.readAll();
        return convertToDTO(playlists);
    }

    // GET public playlists
    @GetMapping("/public")
    public List<PlaylistDTO> getPublic() throws SQLException {
        List<Playlist> playlists = playlistDAO.readPublic();
        return convertToDTO(playlists);
    }

    // GET playlist by ID
    @GetMapping("/{id}")
    public ResponseEntity<PlaylistDTO> getById(@PathVariable int id) throws SQLException {
        Playlist playlist = playlistDAO.readById(id);

        if (playlist == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(convertToDTO(playlist));
    }

    // GET songs in playlist
    @GetMapping("/{id}/songs")
    public List<Integer> getSongs(@PathVariable int id) throws SQLException {
        return playlistDAO.getSongsInPlaylist(id);
    }

    // POST create playlist
    @PostMapping
    public ResponseEntity<?> create(@RequestBody PlaylistDTO dto) throws SQLException {
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Name is required");
        }

        Playlist playlist = new Playlist(
                0,
                dto.getName(),
                dto.getDescription(),
                dto.getCreatedDate(),
                dto.isPublic(),
                dto.getTotalDuration(),
                dto.getSongCount()
        );

        playlistDAO.create(playlist);
        dto.setPlaylistId(playlist.getPlaylistId());

        return ResponseEntity.ok(dto);
    }

    // POST add song to playlist
    @PostMapping("/{playlistId}/songs/{songId}")
    public ResponseEntity<?> addSong(@PathVariable int playlistId,
                                     @PathVariable int songId,
                                     @RequestParam(defaultValue = "0") int position) throws SQLException {
        boolean added = playlistDAO.addSong(playlistId, songId, position);

        if (!added) {
            return ResponseEntity.badRequest().body("Failed to add song");
        }

        return ResponseEntity.ok().build();
    }

    // DELETE remove song from playlist
    @DeleteMapping("/{playlistId}/songs/{songId}")
    public ResponseEntity<?> removeSong(@PathVariable int playlistId,
                                        @PathVariable int songId) throws SQLException {
        boolean removed = playlistDAO.removeSong(playlistId, songId);

        if (!removed) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    // PUT update playlist
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody PlaylistDTO dto) throws SQLException {
        if (dto.getPlaylistId() != id) {
            return ResponseEntity.badRequest().body("ID mismatch");
        }

        Playlist playlist = new Playlist(
                dto.getPlaylistId(),
                dto.getName(),
                dto.getDescription(),
                dto.getCreatedDate(),
                dto.isPublic(),
                dto.getTotalDuration(),
                dto.getSongCount()
        );

        boolean updated = playlistDAO.update(playlist);

        if (!updated) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dto);
    }

    // DELETE playlist
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) throws SQLException {
        boolean deleted = playlistDAO.deleteById(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // Helper methods
    private List<PlaylistDTO> convertToDTO(List<Playlist> playlists) {
        List<PlaylistDTO> dtos = new ArrayList<>();
        for (Playlist p : playlists) {
            dtos.add(convertToDTO(p));
        }
        return dtos;
    }

    private PlaylistDTO convertToDTO(Playlist playlist) {
        return new PlaylistDTO(
                playlist.getPlaylistId(),
                playlist.getName(),
                playlist.getDescription(),
                playlist.getCreatedDate(),
                playlist.isPublic(),
                playlist.getTotalDuration(),
                playlist.getSongCount()
        );
    }
}