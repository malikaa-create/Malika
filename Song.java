public class Song {
    private String title;
    private int duration;
    private Artist artist;

    public Song(String title, int duration, Artist artist) {
        this.title = title;
        this.duration = duration;
        this.artist = artist;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void displaySong() {
        System.out.println(
                "Song: " + title + ", Duration: " + duration + " sec, Artist: " + artist.getName()
        );
    }

}
