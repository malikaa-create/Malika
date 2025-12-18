public class Playlist {
    private String name;
    private Song song;

    public Playlist(String name, Song song) {
        this.name = name;
        this.song = song;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public void displayPlaylist() {
        System.out.println("Playlist: " + name);
        song.displaySong();
    }
}


