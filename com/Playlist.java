package com;

public class Playlist extends LibraryItem {

    private int songsCount;
    private int avgDuration; // сек

    public Playlist(String name, int songsCount, int avgDuration) {
        super(name);
        this.songsCount = songsCount;
        this.avgDuration = avgDuration;
    }

    @Override
    public int totalDuration() {
        return songsCount * avgDuration;
    }

    @Override
    public String toString() {
        return "Плейлист: " + name +
                "\nПесен: " + songsCount +
                "\nСредняя длительность: " + avgDuration + " сек" +
                "\nОбщая длительность: " + totalDuration() + " сек";
    }
}
