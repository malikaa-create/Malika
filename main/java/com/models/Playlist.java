package com.models;

import java.time.LocalDate;

public class Playlist {
    private int playlistId;
    private String name;
    private String description;
    private LocalDate createdDate;
    private boolean isPublic;
    private int totalDuration;
    private int songCount;

    // Constructor
    public Playlist(int playlistId, String name, String description,
                    LocalDate createdDate, boolean isPublic, int totalDuration, int songCount) {
        this.playlistId = playlistId;
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
        this.isPublic = isPublic;
        this.totalDuration = totalDuration;
        this.songCount = songCount;
    }

    // Getters
    public int getPlaylistId() { return playlistId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public LocalDate getCreatedDate() { return createdDate; }
    public boolean isPublic() { return isPublic; }
    public int getTotalDuration() { return totalDuration; }
    public int getSongCount() { return songCount; }

    // Setters
    public void setPlaylistId(int playlistId) { this.playlistId = playlistId; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setCreatedDate(LocalDate createdDate) { this.createdDate = createdDate; }
    public void setPublic(boolean aPublic) { isPublic = aPublic; }
    public void setTotalDuration(int totalDuration) { this.totalDuration = totalDuration; }
    public void setSongCount(int songCount) { this.songCount = songCount; }

    public String getFormattedDuration() {
        int minutes = totalDuration / 60;
        int hours = minutes / 60;
        minutes = minutes % 60;
        if (hours > 0) {
            return String.format("%dh %dm", hours, minutes);
        }
        return String.format("%dm", minutes);
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + playlistId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", created=" + createdDate +
                ", public=" + isPublic +
                ", duration=" + getFormattedDuration() +
                ", songs=" + songCount +
                '}';
    }
}
