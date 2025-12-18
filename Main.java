public class Main {
    public static void main(String[] args) {

        Artist artist1 = new Artist("Adele", "Pop");
        Artist artist2 = new Artist("The Weeknd", "R&B");

        Song song1 = new Song("Love Song", 300, artist1);
        Song song2 = new Song("Blinding Lights", 200, artist2);

        Playlist playlist1 = new Playlist("My Favorites", song1);
        Playlist playlist2 = new Playlist("Top Hits", song2);

        playlist1.displayPlaylist();
        System.out.println();
        playlist2.displayPlaylist();

        System.out.println("\nComparison:");
        if (song1.getDuration() > song2.getDuration()) {
            System.out.println(song1.getTitle() + " is longer than " + song2.getTitle());
        } else {
            System.out.println(song2.getTitle() + " is longer than " + song1.getTitle());
        }
    }
}

