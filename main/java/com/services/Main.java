package com.services;

import com.algorithms.SongSorter;
import com.DAO.ArtistDAO;
import com.DAO.SongDAO;
import com.DAO.PlaylistDAO;
import com.models.Artist;
import com.models.Song;
import com.models.Playlist;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static void printArtists(List<Artist> artists) {
        if (artists == null || artists.isEmpty()) {
            System.out.println("No artists found.");
            return;
        }
        for (Artist a : artists) System.out.println(a);
    }

    private static void printSongs(List<Song> songs) {
        if (songs == null || songs.isEmpty()) {
            System.out.println("No songs found.");
            return;
        }
        for (Song s : songs) System.out.println(s);
    }

    private static void printPlaylists(List<Playlist> playlists) {
        if (playlists == null || playlists.isEmpty()) {
            System.out.println("No playlists found.");
            return;
        }
        for (Playlist p : playlists) System.out.println(p);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArtistDAO artistDAO = new ArtistDAO();
        SongDAO songDAO = new SongDAO();
        PlaylistDAO playlistDAO = new PlaylistDAO();

        int choice;
        do {
            System.out.println("\n=== MUSIC LIBRARY CRUD MENU ===");
            System.out.println("Artists:");
            System.out.println("  1) Show all artists (READ)");
            System.out.println("  2) Add artist (CREATE)");
            System.out.println("  3) Update artist popularity (UPDATE)");
            System.out.println("  4) Delete artist by ID (DELETE)");
            System.out.println("  5) Filter artists by genre");
            System.out.println("Songs:");
            System.out.println("  6) Show all songs (READ)");
            System.out.println("  7) Add song (CREATE)");
            System.out.println("  8) Update song rating (UPDATE)");
            System.out.println("  9) Delete song by ID (DELETE)");
            System.out.println(" 10) Filter songs by genre");
            System.out.println(" 11) Filter songs by minimum rating");
            System.out.println(" 12) Show top songs by plays");
            System.out.println(" 13) Play song (increment plays)");
            System.out.println("Playlists:");
            System.out.println(" 14) Show all playlists (READ)");
            System.out.println(" 15) Create playlist (CREATE)");
            System.out.println(" 16) Delete playlist (DELETE)");
            System.out.println(" 17) Add song to playlist");
            System.out.println(" 18) Remove song from playlist");
            System.out.println(" 19) View songs in playlist");
            System.out.println("Algorithms:");
            System.out.println(" 20) Sort songs by rating");
            System.out.println(" 21) Sort songs by plays");
            System.out.println(" 22) Sort songs by duration");
            System.out.println("  0) Exit");
            System.out.print("Choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Enter a number: ");
                sc.nextLine();
            }
            choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    // ========== ARTISTS ==========
                    case 1:
                        printArtists(artistDAO.readAll());
                        break;

                    case 2: {
                        System.out.print("Artist name: ");
                        String name = sc.nextLine();

                        System.out.print("Genre: ");
                        String genre = sc.nextLine();

                        System.out.print("Country: ");
                        String country = sc.nextLine();

                        System.out.print("Formed year: ");
                        int year = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Is active (true/false): ");
                        boolean active = sc.nextBoolean();
                        sc.nextLine();

                        System.out.print("Popularity score (0-100): ");
                        int popularity = sc.nextInt();
                        sc.nextLine();

                        Artist artist = new Artist(0, name, genre, country, year, active, popularity);
                        artistDAO.create(artist);
                        System.out.println("Artist added with ID: " + artist.getArtistId());
                        break;
                    }

                    case 3: {
                        System.out.print("Artist ID: ");
                        int id = sc.nextInt();

                        System.out.print("New popularity score: ");
                        int score = sc.nextInt();
                        sc.nextLine();

                        boolean ok = artistDAO.updatePopularity(id, score);
                        System.out.println(ok ? "Artist updated." : "Artist not found.");
                        break;
                    }

                    case 4: {
                        System.out.print("Artist ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        boolean ok = artistDAO.deleteById(id);
                        System.out.println(ok ? "Artist deleted." : "Artist not found.");
                        break;
                    }

                    case 5: {
                        System.out.print("Genre: ");
                        String genre = sc.nextLine();

                        printArtists(artistDAO.filterByGenre(genre));
                        break;
                    }

                    // ========== SONGS ==========
                    case 6:
                        printSongs(songDAO.readAll());
                        break;

                    case 7: {
                        System.out.print("Song title: ");
                        String title = sc.nextLine();

                        System.out.print("Artist ID: ");
                        int artistId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Album: ");
                        String album = sc.nextLine();

                        System.out.print("Duration (seconds): ");
                        int duration = sc.nextInt();

                        System.out.print("Release year: ");
                        int year = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Genre: ");
                        String genre = sc.nextLine();

                        System.out.print("Rating (0-10): ");
                        double rating = sc.nextDouble();
                        sc.nextLine();

                        Song song = new Song(0, title, artistId, album, duration, year, genre, 0, rating);
                        songDAO.create(song);
                        System.out.println("Song added with ID: " + song.getSongId());
                        break;
                    }

                    case 8: {
                        System.out.print("Song ID: ");
                        int id = sc.nextInt();

                        System.out.print("New rating: ");
                        double rating = sc.nextDouble();
                        sc.nextLine();

                        boolean ok = songDAO.updateRating(id, rating);
                        System.out.println(ok ? "Song rating updated." : "Song not found.");
                        break;
                    }

                    case 9: {
                        System.out.print("Song ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        boolean ok = songDAO.deleteById(id);
                        System.out.println(ok ? "Song deleted." : "Song not found.");
                        break;
                    }

                    case 10: {
                        System.out.print("Genre: ");
                        String genre = sc.nextLine();

                        printSongs(songDAO.filterByGenre(genre));
                        break;
                    }

                    case 11: {
                        System.out.print("Minimum rating: ");
                        double minRating = sc.nextDouble();
                        sc.nextLine();

                        printSongs(songDAO.filterByMinRating(minRating));
                        break;
                    }

                    case 12:
                        printSongs(songDAO.readAllSortedByPlays());
                        break;

                    case 13: {
                        System.out.print("Song ID to play: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        boolean ok = songDAO.incrementPlays(id);
                        System.out.println(ok ? "Song played! Play count incremented." : "Song not found.");
                        break;
                    }

                    // ========== PLAYLISTS ==========
                    case 14:
                        printPlaylists(playlistDAO.readAll());
                        break;

                    case 15: {
                        System.out.print("Playlist name: ");
                        String name = sc.nextLine();

                        System.out.print("Description: ");
                        String desc = sc.nextLine();

                        System.out.print("Is public (true/false): ");
                        boolean isPublic = sc.nextBoolean();
                        sc.nextLine();

                        Playlist playlist = new Playlist(0, name, desc, LocalDate.now(), isPublic, 0, 0);
                        playlistDAO.create(playlist);
                        System.out.println("Playlist created with ID: " + playlist.getPlaylistId());
                        break;
                    }

                    case 16: {
                        System.out.print("Playlist ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        boolean ok = playlistDAO.deleteById(id);
                        System.out.println(ok ? "Playlist deleted." : "Playlist not found.");
                        break;
                    }

                    case 17: {
                        System.out.print("Playlist ID: ");
                        int playlistId = sc.nextInt();

                        System.out.print("Song ID: ");
                        int songId = sc.nextInt();

                        System.out.print("Position: ");
                        int position = sc.nextInt();
                        sc.nextLine();

                        boolean ok = playlistDAO.addSong(playlistId, songId, position);
                        System.out.println(ok ? "Song added to playlist." : "Failed to add song.");
                        break;
                    }

                    case 18: {
                        System.out.print("Playlist ID: ");
                        int playlistId = sc.nextInt();

                        System.out.print("Song ID: ");
                        int songId = sc.nextInt();
                        sc.nextLine();

                        boolean ok = playlistDAO.removeSong(playlistId, songId);
                        System.out.println(ok ? "Song removed from playlist." : "Failed to remove song.");
                        break;
                    }

                    case 19: {
                        System.out.print("Playlist ID: ");
                        int playlistId = sc.nextInt();
                        sc.nextLine();

                        List<Integer> songIds = playlistDAO.getSongsInPlaylist(playlistId);
                        if (songIds.isEmpty()) {
                            System.out.println("Playlist is empty.");
                        } else {
                            System.out.println("Songs in playlist: " + songIds);
                        }
                        break;
                    }

                    // ========== ALGORITHMS ==========
                    case 20: {
                        System.out.println("Sorting songs by rating...");
                        List<Song> songs = songDAO.readAll();
                        List<Song> sorted = SongSorter.sortByRating(songs);
                        printSongs(sorted);
                        break;
                    }

                    case 21: {
                        System.out.println("Sorting songs by plays...");
                        List<Song> songs = songDAO.readAll();
                        List<Song> sorted = SongSorter.sortByPlays(songs);
                        printSongs(sorted);
                        break;
                    }

                    case 22: {
                        System.out.println("Sorting songs by duration...");
                        List<Song> songs = songDAO.readAll();
                        List<Song> sorted = SongSorter.sortByDuration(songs);
                        printSongs(sorted);
                        break;
                    }

                    case 0:
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
                e.printStackTrace();
            }
        } while (choice != 0);

        sc.close();
    }
}