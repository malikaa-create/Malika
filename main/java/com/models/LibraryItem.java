package com.models;

import java.time.LocalDateTime;

public class LibraryItem {
    private int itemId;
    private int songId;
    private int userId;
    private boolean isFavorite;
    private LocalDateTime lastPlayed;
    private int playCount;

    // Constructor
    public LibraryItem(int itemId, int songId, int userId, boolean isFavorite,
                       LocalDateTime lastPlayed, int playCount) {
        this.itemId = itemId;
        this.songId = songId;
        this.userId = userId;
        this.isFavorite = isFavorite;
        this.lastPlayed = lastPlayed;
        this.playCount = playCount;
    }

    // Getters and Setters
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public int getSongId() { return songId; }
    public void setSongId(int songId) { this.songId = songId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }

    public LocalDateTime getLastPlayed() { return lastPlayed; }
    public void setLastPlayed(LocalDateTime lastPlayed) { this.lastPlayed = lastPlayed; }

    public int getPlayCount() { return playCount; }
    public void setPlayCount(int playCount) { this.playCount = playCount; }

    @Override
    public String toString() {
        return "LibraryItem{" +
                "itemId=" + itemId +
                ", songId=" + songId +
                ", userId=" + userId +
                ", favorite=" + isFavorite +
                ", lastPlayed=" + lastPlayed +
                ", playCount=" + playCount +
                '}';
    }
}
