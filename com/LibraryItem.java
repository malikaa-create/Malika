package com;

public abstract class LibraryItem {
    protected String name;

    public LibraryItem(String name) {
        this.name = name;
    }

    // типо "расчёт" — для плейлиста это общая длительность
    public abstract int totalDuration();

    @Override
    public String toString() {
        return "Элемент: " + name;
    }
}
