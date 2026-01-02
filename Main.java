import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Artist artist1 = new MusicArtist("Adele");
        Artist artist2 = new MusicArtist("The Weeknd");

        Song s1 = new Song("Hello", 300, artist1);
        Song s2 = new Song("Easy On Me", 290, artist1);
        Song s3 = new Song("Blinding Lights", 200, artist2);

        Playlist playlist = new Playlist("My Music");
        playlist.addSong(s1);
        playlist.addSong(s2);
        playlist.addSong(s3);

        System.out.println("\nAll songs:");
        playlist.display();

        System.out.println("\nSorted by duration:");
        playlist.sortByDuration();
        playlist.display();

        System.out.println("\nSearch song title:");
        String title = scanner.nextLine();
        Song found = playlist.searchByTitle(title);
        System.out.println(found != null ? found : "Song not found");

        System.out.println("\nFilter by artist:");
        String artistName = scanner.nextLine();
        playlist.filterByArtist(artistName);
    }
}