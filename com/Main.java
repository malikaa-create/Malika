package com;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SongDAO dao = new SongDAO();

        while (true) {
            System.out.println("\n=== MUSIC LIBRARY ===");
            System.out.println("1. Показать песни");
            System.out.println("2. Добавить песню");
            System.out.println("3. Обновить длительность");
            System.out.println("4. Удалить песню");
            System.out.println("5. Обновить артиста");
            System.out.println("0. Выход");
            System.out.print("Выбор: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                dao.findAll().forEach(System.out::println);

            } else if (choice == 2) {
                System.out.print("Название песни: ");
                String title = sc.nextLine();

                System.out.print("Артист: ");
                String artist = sc.nextLine();

                System.out.print("Длительность (сек): ");
                int duration = sc.nextInt();
                sc.nextLine();

                dao.save(new Song(title, artist, duration));

            } else if (choice == 3) {
                System.out.print("ID: ");
                int id = sc.nextInt();

                System.out.print("Новая длительность (сек): ");
                int duration = sc.nextInt();
                sc.nextLine();

                dao.updateDurationById(id, duration);

            } else if (choice == 4) {
                System.out.print("ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                dao.deleteById(id);


            } else if (choice == 5) {
                System.out.print("ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Новый артист: ");
                String artist = sc.nextLine();

                dao.updateArtistById(id, artist);

            } else if (choice == 0) {
                break;
            }
        }

        sc.close();
    }
}
