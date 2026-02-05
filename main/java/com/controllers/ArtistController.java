package com.controllers;

import com.DAO.ArtistDAO;
import com.DTO.ArtistDTO;
import com.models.Artist;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    private final ArtistDAO artistDAO = new ArtistDAO();

    // GET all artists
    @GetMapping
    public List<ArtistDTO> getAll() throws SQLException {
        List<Artist> artists = artistDAO.readAll();
        List<ArtistDTO> dtos = new ArrayList<>();

        for (Artist a : artists) {
            dtos.add(new ArtistDTO(
                    a.getArtistId(),
                    a.getName(),
                    a.getGenre(),
                    a.getCountry(),
                    a.getFormedYear(),
                    a.isActive(),
                    a.getPopularityScore()
            ));
        }
        return dtos;
    }

    // GET artist by ID
    @GetMapping("/{id}")
    public ResponseEntity<ArtistDTO> getById(@PathVariable int id) throws SQLException {
        Artist artist = artistDAO.readById(id);

        if (artist == null) {
            return ResponseEntity.notFound().build();
        }

        ArtistDTO dto = new ArtistDTO(
                artist.getArtistId(),
                artist.getName(),
                artist.getGenre(),
                artist.getCountry(),
                artist.getFormedYear(),
                artist.isActive(),
                artist.getPopularityScore()
        );

        return ResponseEntity.ok(dto);
    }

    // GET artists by genre
    @GetMapping("/genre/{genre}")
    public List<ArtistDTO> getByGenre(@PathVariable String genre) throws SQLException {
        List<Artist> artists = artistDAO.filterByGenre(genre);
        List<ArtistDTO> dtos = new ArrayList<>();

        for (Artist a : artists) {
            dtos.add(new ArtistDTO(
                    a.getArtistId(),
                    a.getName(),
                    a.getGenre(),
                    a.getCountry(),
                    a.getFormedYear(),
                    a.isActive(),
                    a.getPopularityScore()
            ));
        }
        return dtos;
    }

    // POST create artist
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ArtistDTO dto) throws SQLException {
        // Validation
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Name is required");
        }

        Artist artist = new Artist(
                0, // Will be auto-generated
                dto.getName(),
                dto.getGenre(),
                dto.getCountry(),
                dto.getFormedYear(),
                dto.isActive(),
                dto.getPopularityScore()
        );

        artistDAO.create(artist);
        dto.setArtistId(artist.getArtistId());

        return ResponseEntity.ok(dto);
    }

    // PUT update artist
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody ArtistDTO dto) throws SQLException {
        if (dto.getArtistId() != id) {
            return ResponseEntity.badRequest().body("ID mismatch");
        }

        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Name is required");
        }

        Artist artist = new Artist(
                dto.getArtistId(),
                dto.getName(),
                dto.getGenre(),
                dto.getCountry(),
                dto.getFormedYear(),
                dto.isActive(),
                dto.getPopularityScore()
        );

        boolean updated = artistDAO.update(artist);

        if (!updated) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(dto);
    }

    // PATCH update popularity
    @PatchMapping("/{id}/popularity")
    public ResponseEntity<?> updatePopularity(@PathVariable int id, @RequestParam int score) throws SQLException {
        if (score < 0 || score > 100) {
            return ResponseEntity.badRequest().body("Score must be between 0 and 100");
        }

        boolean updated = artistDAO.updatePopularity(id, score);

        if (!updated) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    // DELETE artist
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) throws SQLException {
        boolean deleted = artistDAO.deleteById(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}