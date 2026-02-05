package com.models;

public class Artist {
    private int artistId;
    private String name;
    private String genre;
    private String country;
    private int formedYear;
    private boolean isActive;
    private int popularityScore;

    // Constructor
    public Artist(int artistId, String name, String genre, String country,
                  int formedYear, boolean isActive, int popularityScore) {
        this.artistId = artistId;
        this.name = name;
        this.genre = genre;
        this.country = country;
        this.formedYear = formedYear;
        this.isActive = isActive;
        this.popularityScore = popularityScore;
    }

    // Getters
    public int getArtistId() { return artistId; }
    public String getName() { return name; }
    public String getGenre() { return genre; }
    public String getCountry() { return country; }
    public int getFormedYear() { return formedYear; }
    public boolean isActive() { return isActive; }
    public int getPopularityScore() { return popularityScore; }

    // Setters
    public void setArtistId(int artistId) { this.artistId = artistId; }
    public void setName(String name) { this.name = name; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setCountry(String country) { this.country = country; }
    public void setFormedYear(int formedYear) { this.formedYear = formedYear; }
    public void setActive(boolean active) { isActive = active; }
    public void setPopularityScore(int popularityScore) { this.popularityScore = popularityScore; }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + artistId +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", country='" + country + '\'' +
                ", year=" + formedYear +
                ", active=" + isActive +
                ", popularity=" + popularityScore +
                '}';
    }
}
