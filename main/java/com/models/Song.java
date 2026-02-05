package com.models;

public class Song {
    private int songId;
    private String title;
    private int artistId;
    private String album;
    private int durationSeconds;
    private int releaseYear;
    private String genre;
    private int playsCount;
    private double rating;

    // Constructor
    public Song(int songId, String title, int artistId, String album,
                int durationSeconds, int releaseYear, String genre,
                int playsCount, double rating) {
        this.songId = songId;
        this.title = title;
        this.artistId = artistId;
        this.album = album;
        this.durationSeconds = durationSeconds;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.playsCount = playsCount;
        this.rating = rating;
    }

    // Getters
    public int getSongId() { return songId; }
    public String getTitle() { return title; }
    public int getArtistId() { return artistId; }
    public String getAlbum() { return album; }
    public int getDurationSeconds() { return durationSeconds; }
    public int getReleaseYear() { return releaseYear; }
    public String getGenre() { return genre; }
    public int getPlaysCount() { return playsCount; }
    public double getRating() { return rating; }

    // Setters
    public void setSongId(int songId) { this.songId = songId; }
    public void setTitle(String title) { this.title = title; }
    public void setArtistId(int artistId) { this.artistId = artistId; }
    public void setAlbum(String album) { this.album = album; }
    public void setDurationSeconds(int durationSeconds) { this.durationSeconds = durationSeconds; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setPlaysCount(int playsCount) { this.playsCount = playsCount; }
    public void setRating(double rating) { this.rating = rating; }

    public String getFormattedDuration() {
        int minutes = durationSeconds / 60;
        int seconds = durationSeconds % 60;
        return String.format("%d:%02d", minutes, seconds);
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + songId +
                ", title='" + title + '\'' +
                ", artistId=" + artistId +
                ", album='" + album + '\'' +
                ", duration=" + getFormattedDuration() +
                ", year=" + releaseYear +
                ", genre='" + genre + '\'' +
                ", plays=" + playsCount +
                ", rating=" + rating +
                '}';
    }
}
