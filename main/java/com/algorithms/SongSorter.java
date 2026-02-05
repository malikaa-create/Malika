package com.algorithms;

import com.models.Song;
import java.util.ArrayList;
import java.util.List;

public class SongSorter {

    // Bubble sort by rating (descending)
    public static List<Song> sortByRating(List<Song> songs) {
        List<Song> sorted = new ArrayList<>(songs);
        int n = sorted.size();

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (sorted.get(j).getRating() < sorted.get(j + 1).getRating()) {
                    Song temp = sorted.get(j);
                    sorted.set(j, sorted.get(j + 1));
                    sorted.set(j + 1, temp);
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        return sorted;
    }

    // Sort by plays (descending)
    public static List<Song> sortByPlays(List<Song> songs) {
        List<Song> sorted = new ArrayList<>(songs);
        int n = sorted.size();

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (sorted.get(j).getPlaysCount() < sorted.get(j + 1).getPlaysCount()) {
                    Song temp = sorted.get(j);
                    sorted.set(j, sorted.get(j + 1));
                    sorted.set(j + 1, temp);
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        return sorted;
    }

    // Sort by duration (ascending)
    public static List<Song> sortByDuration(List<Song> songs) {
        List<Song> sorted = new ArrayList<>(songs);
        int n = sorted.size();

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (sorted.get(j).getDurationSeconds() > sorted.get(j + 1).getDurationSeconds()) {
                    Song temp = sorted.get(j);
                    sorted.set(j, sorted.get(j + 1));
                    sorted.set(j + 1, temp);
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        return sorted;
    }
}