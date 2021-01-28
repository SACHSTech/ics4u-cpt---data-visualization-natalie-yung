package basic;

import basic.VideoGame;
import basic.VideoGameRepo;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner s = new Scanner(System.in);
    private static VideoGameRepo videoGameRepo;

    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        videoGameRepo = new VideoGameRepo();
        final File f = new File(videoGameRepo.getClass().getResource("dataset.csv").toURI());

        if (videoGameRepo.loadCSV(f)) {
            System.out.println("Data loaded");
        } else {
            System.out.println("Cannot load data");
            System.exit(1);
        }

        while (true) {
            printMenu();
            processInput();
        }
    }

    private static void printMenu() {
        System.out.println("\n\nWhat operation you want to perform?");
        System.out.println("type. 0 to exit");
        System.out.println("type. 1 to show all entries inside the dataset");
        System.out.println("type. 2 to search by name");
        System.out.println("type. 3 to print sorted values");
        System.out.println("type. 4 to print filtered values (by column name)");
        System.out.println("type. 5 to print stats ");
        System.out.print("\n> ");
    }

    private static void processInput() {
        try {
            switch (Integer.parseInt(s.nextLine())) {
                case 0: {
                    System.exit(0);
                    break;
                }
                case 1: {
                    final List<String> sortable = videoGameRepo.sortable();
                    for (String s : sortable) {
                        System.out.print(s+"\t");
                    }
                    for (VideoGame videoGame : videoGameRepo.getVideoGames()) {
                        System.out.println(videoGame.toString());
                    }
                    break;
                }
                case 2: {
                    System.out.print("Name: ");
                    final String name = s.nextLine();
                    final VideoGame byName = videoGameRepo.findByName(name);
                    if (byName == null) {
                        System.out.println("The query has no result");
                    } else System.out.println(byName.toString());
                    break;
                }
                case 3: {
                    System.out.println("Available columns:");
                    videoGameRepo.sortable().forEach(System.out::println);
                    System.out.print("Column name: ");
                    final String name = s.nextLine();
                    final List<VideoGame> byName = videoGameRepo.sortByColumn(name);
                    if (!byName.isEmpty()) {
                        for (VideoGame videoGame : byName) {
                            System.out.println(videoGame.toString());
                        }
                    } else System.out.println("No results for "+name);
                    break;
                }
                case 4: {
                    System.out.println("Available columns:");
                    videoGameRepo.sortable().forEach(System.out::println);
                    System.out.print("Column name: ");
                    final String name = s.nextLine();
                    final List<String> byColumn = videoGameRepo.filter(name);
                    for (String s1 : byColumn) {
                        System.out.println(s1);
                    }
                    break;
                }
                case 5: {
                    System.out.println("Stats: ");
                    System.out.println(videoGameRepo.stringifyStats(videoGameRepo.stats(videoGameRepo.getVideoGames())));
                    break;
                }
                default: break;
            }
        } catch (Exception ignore) {}
    }
}

