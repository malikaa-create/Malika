import java.util.*;

class Playlist {
    private String name;
    private List<Song> songs = new ArrayList<>();

    public Playlist(String name) {
        this.name = name;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    // Filtering
    public void filterByArtist(String artistName) {
        for (Song s : songs) {
            if (s.getArtist().getName().equalsIgnoreCase(artistName)) {
                System.out.println(s);
            }
        }
    }

    // Sorting
    public void sortByDuration() {
        songs.sort(Comparator.comparingInt(Song::getDuration));
    }

    // Searching
    public Song searchByTitle(String title) {
        for (Song s : songs) {
            if (s.getTitle().equalsIgnoreCase(title)) {
                return s;
            }
        }
        return null;
    }

    public void display() {
        for (Song s : songs) {
            System.out.println(s);
        }
    }
}