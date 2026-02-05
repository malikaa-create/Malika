package com.DTO;

public class ArtistDTO {
    private int artistId;
    private String name;
    private String genre;
    private String country;
    private int formedYear;
    private boolean isActive;
    private int popularityScore;

    // Default constructor
    public ArtistDTO() {}

    // Parameterized constructor
    public ArtistDTO(int artistId, String name, String genre, String country,
                     int formedYear, boolean isActive, int popularityScore) {
        this.artistId = artistId;
        this.name = name;
        this.genre = genre;
        this.country = country;
        this.formedYear = formedYear;
        this.isActive = isActive;
        this.popularityScore = popularityScore;
    }

    // Getters and Setters
    public int getArtistId() { return artistId; }
    public void setArtistId(int artistId) { this.artistId = artistId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public int getFormedYear() { return formedYear; }
    public void setFormedYear(int formedYear) { this.formedYear = formedYear; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public int getPopularityScore() { return popularityScore; }
    public void setPopularityScore(int popularityScore) { this.popularityScore = popularityScore; }
}
