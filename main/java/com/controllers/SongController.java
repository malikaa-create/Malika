package com.controllers;

import com.DAO.SongDAO;
import com.DTO.SongDTO;
import com.models.Song;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongDAO songDAO = new SongDAO();

    // GET all songs
    @GetMapping
    public List<SongDTO> getAll() throws SQLException {
        List<Song> songs = songDAO.readAll();
        return convertToDTO(songs);
    }

    // GET song by ID
    @GetMapping("/{id}")
    public ResponseEntity<SongDTO> getById(@PathVariable int id) throws SQLException {
        Song song = songDAO.readById(id);

        if (song == null) {
            return ResponseEntity.notFound().build();
        }

        SongDTO dto = convertToDTO(song);
        return ResponseEntity.ok(dto);
    }

    // GET songs by artist
    @GetMapping("/artist/{artistId}")
    public List<SongDTO> getByArtist(@PathVariable int artistId) throws SQLException {
        List<Song> songs = songDAO.readByArtist(artistId);
        return convertToDTO(songs);
    }

    // GET songs by genre
    @GetMapping("/genre/{genre}")
    public List<SongDTO> getByGenre(@PathVariable String genre) throws SQLException {
        List<Song> songs = songDAO.filterByGenre(genre);
        return convertToDTO(songs);
    }

    // GET songs filtered by min rating
    @GetMapping("/filter")
    public List<SongDTO> filterByRating(@RequestParam double minRating) throws SQLException {
        List<Song> songs = songDAO.filterByMinRating(minRating);
        return convertToDTO(songs);
    }

    // GET top songs by plays
    @GetMapping("/top")
    public List<SongDTO> getTopSongs() throws SQLException {
        List<Song> songs = songDAO.readAllSortedByPlays();
        return convertToDTO(songs);
    }

    // POST create song
    @PostMapping
    public ResponseEntity<?> create(@RequestBody SongDTO dto) throws SQLException {
        if (dto.getTitle() == null || dto.getTitle().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Title is required");
        }

        if (dto.getArtistId() <= 0) {
            return ResponseEntity.badRequest().body("Valid artist ID required");
        }

        Song song = new Song(
                0, // Auto-generated
                dto.getTitle(),
                dto.getArtistId(),
                dto.getAlbum(),
                dto.getDurationSeconds(),
                dto.getReleaseYear(),
                dto.getGenre(),
                dto.getPlaysCount(),
                dto.getRating()
        );

        songDAO.create(song);
        dto.setSongId(song.getSongId());

        return ResponseEntity.ok(dto);
    }

    // PUT update song
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody SongDTO dto) throws SQLException {
        if (dto.getSongId() != id) {
            return ResponseEntity.badRequest().body("ID mismatch");
        }

        Song song = new Song(
                dto.getSongId(),
                dto.getTitle(),
                dto.getArtistId(),
                dto.getAlbum(),
                dto.getDurationSeconds(),
                dto.getReleaseYear(),
                dto.getGenre(),
                dto.getPlaysCount(),
                dto.getRating()
        );

        boolean updated = songDAO.update(song);

        if (!updated) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dto);
    }

    // PATCH update rating
    @PatchMapping("/{id}/rating")
    public ResponseEntity<?> updateRating(@PathVariable int id, @RequestParam double rating) throws SQLException {
        if (rating < 0 || rating > 10) {
            return ResponseEntity.badRequest().body("Rating must be between 0 and 10");
        }

        boolean updated = songDAO.updateRating(id, rating);

        if (!updated) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    // POST increment plays
    @PostMapping("/{id}/play")
    public ResponseEntity<?> incrementPlays(@PathVariable int id) throws SQLException {
        boolean updated = songDAO.incrementPlays(id);

        if (!updated) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    // DELETE song
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) throws SQLException {
        boolean deleted = songDAO.deleteById(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // Helper methods
    private List<SongDTO> convertToDTO(List<Song> songs) {
        List<SongDTO> dtos = new ArrayList<>();
        for (Song s : songs) {
            dtos.add(convertToDTO(s));
        }
        return dtos;
    }

    private SongDTO convertToDTO(Song song) {
        return new SongDTO(
                song.getSongId(),
                song.getTitle(),
                song.getArtistId(),
                null, // Artist name - can be populated via join if needed
                song.getAlbum(),
                song.getDurationSeconds(),
                song.getReleaseYear(),
                song.getGenre(),
                song.getPlaysCount(),
                song.getRating()
        );
    }
}