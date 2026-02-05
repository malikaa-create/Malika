package com.DTO;

import java.time.LocalDate;

public class PlaylistDTO {
    private int playlistId;
    private String name;
    private String description;
    private LocalDate createdDate;
    private boolean isPublic;
    private int totalDuration;
    private int songCount;

    // Default constructor
    public PlaylistDTO() {}

    // Full constructor
    public PlaylistDTO(int playlistId, String name, String description,
                       LocalDate createdDate, boolean isPublic,
                       int totalDuration, int songCount) {
        this.playlistId = playlistId;
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
        this.isPublic = isPublic;
        this.totalDuration = totalDuration;
        this.songCount = songCount;
    }

    // Getters and Setters
    public int getPlaylistId() { return playlistId; }
    public void setPlaylistId(int playlistId) { this.playlistId = playlistId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDate createdDate) { this.createdDate = createdDate; }

    public boolean isPublic() { return isPublic; }
    public void setPublic(boolean aPublic) { isPublic = aPublic; }

    public int getTotalDuration() { return totalDuration; }
    public void setTotalDuration(int totalDuration) { this.totalDuration = totalDuration; }

    public int getSongCount() { return songCount; }
    public void setSongCount(int songCount) { this.songCount = songCount; }
}
