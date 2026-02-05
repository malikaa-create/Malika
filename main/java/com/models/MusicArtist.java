package com.models;

public class MusicArtist extends Artist {
    private int albumCount;
    private String recordLabel;

    public MusicArtist(int artistId, String name, String genre, String country,
                       int formedYear, boolean isActive, int popularityScore,
                       int albumCount, String recordLabel) {
        super(artistId, name, genre, country, formedYear, isActive, popularityScore);
        this.albumCount = albumCount;
        this.recordLabel = recordLabel;
    }

    public int getAlbumCount() { return albumCount; }
    public void setAlbumCount(int albumCount) { this.albumCount = albumCount; }

    public String getRecordLabel() { return recordLabel; }
    public void setRecordLabel(String recordLabel) { this.recordLabel = recordLabel; }

    @Override
    public String toString() {
        return super.toString() + " MusicArtist{" +
                "albums=" + albumCount +
                ", label='" + recordLabel + '\'' +
                '}';
    }
}