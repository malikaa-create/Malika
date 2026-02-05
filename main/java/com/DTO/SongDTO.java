package com.DTO;

public class SongDTO {
    private int songId;
    private String title;
    private int artistId;
    private String artistName; // For display purposes
    private String album;
    private int durationSeconds;
    private int releaseYear;
    private String genre;
    private int playsCount;
    private double rating;

    // Default constructor
    public SongDTO() {}

    // Full constructor
    public SongDTO(int songId, String title, int artistId, String artistName,
                   String album, int durationSeconds, int releaseYear,
                   String genre, int playsCount, double rating) {
        this.songId = songId;
        this.title = title;
        this.artistId = artistId;
        this.artistName = artistName;
        this.album = album;
        this.durationSeconds = durationSeconds;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.playsCount = playsCount;
        this.rating = rating;
    }

    // Getters and Setters
    public int getSongId() { return songId; }
    public void setSongId(int songId) { this.songId = songId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getArtistId() { return artistId; }
    public void setArtistId(int artistId) { this.artistId = artistId; }

    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) { this.artistName = artistName; }

    public String getAlbum() { return album; }
    public void setAlbum(String album) { this.album = album; }

    public int getDurationSeconds() { return durationSeconds; }
    public void setDurationSeconds(int durationSeconds) { this.durationSeconds = durationSeconds; }

    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public int getPlaysCount() { return playsCount; }
    public void setPlaysCount(int playsCount) { this.playsCount = playsCount; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
}
