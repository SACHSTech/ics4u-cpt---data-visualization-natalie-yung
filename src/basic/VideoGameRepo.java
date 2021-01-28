package basic;

import basic.VideoGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Allows you to perform operations with VideoGame.cvs
 */
public class VideoGameRepo {
    private final List<VideoGame> videoGames = new LinkedList<>();
    private final int s = 2;

    public void add(VideoGame game) {
        videoGames.add(game);
    }

    public void remove(VideoGame game) {
        videoGames.remove(game);
    }

    public List<String> sortable() {
        return Arrays.stream(VideoGame.class.getFields()).map(Field::getName).collect(Collectors.toList());
    }

    /**
     * Sorts the list in ascending order.
     * @param columnName The name of the column
     * @return The sorted list
     */
    public List<VideoGame> sortByColumn(String columnName) {
        try {
            final Field field = VideoGame.class.getField(columnName);
            if (field.getType().isPrimitive())
                videoGames.sort((o1, o2) -> {
                    try {
                        return Integer.compare(field.getInt(o1), field.getInt(o2));
                    } catch (IllegalAccessException ignore) {
                        return 0;
                    }
                });
            else videoGames.sort((o1, o2) -> {
                try {
                    return ((String) field.get(o1)).compareTo(((String) field.get(o2)));
                } catch (IllegalAccessException ignore) {
                    return 0;
                }
            });
        } catch (NoSuchFieldException ignore) {
            System.err.println("Error");
        }

        return videoGames;
    }

    /**
     * Filters a VideoGame based on the column name
     * @param columnName The filter name
     * @return The resulting List
     */
    public List<String> filter(String columnName) {
        final List<String> result = new ArrayList<>();
        try {
            final Field field = VideoGame.class.getField(columnName);
            for (VideoGame videoGame : videoGames) {
                String s = (String) field.get(videoGame);
                result.add(s);
            }
            return result;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return List.of();
        }
    }

    /**
     * Finds a particular video game by its name
     * @param name The game's name
     * @return The {@link VideoGame}
     */
    public VideoGame findByName(String name) {
        for (VideoGame videoGame : videoGames) {
            if (videoGame.name.equals(name))
                return videoGame;
        }
        return null;
    }

    /**
     * Calculates standard deviation and other parameters from a List of games
     * @param games The games list
     * @return The stats array
     */
    public double[] stats(List<VideoGame> games) {
        final double[] stats = new double[15];
        Arrays.fill(stats, 0);
        for (VideoGame videoGame : videoGames) {
            stats[0] += videoGame.globalSales;
            stats[1] += videoGame.otherSales;
        }
        for (int i = 0; i < stats.length; i++) {
            stats[i] /= s;
        }
        return stats;
    }

    /**
     * Transforms the stats method output into a printable string
     * @param stats The stats array
     * @return A printable stats String
     */
    public String stringifyStats(double... stats) {
        return "Standard dev. " + "Average global sells: " + stats[0] +
                "Average other sells: " + stats[1];
    }

    /**
     * Loads the CSV file in
     * @param f The .CSV file
     * @return TRUE if at least 75% entries has been loaded
     * @throws FileNotFoundException E-404
     */
    public boolean loadCSV(final File f) throws FileNotFoundException {
        if (f.exists() && !f.isDirectory()) {
            final Scanner scanner = new Scanner(f).useDelimiter("\n");
            scanner.next();
            int total = 0;
            while (scanner.hasNext()) {
                total++;
                final String next = scanner.next();
                final String[] split = next.split(",", -1);
                try {
                    if (split.length >= 11) {
                        videoGames.add(
                                new VideoGame(
                                        split[0],
                                        split[1],
                                        Double.parseDouble(split[2].equals("N/A")?"0":split[2]),
                                        split[3],
                                        split[4],
                                        Double.parseDouble(split[5].equals("tbd")?"0":split[5].isBlank()?"0":split[5]),
                                        Double.parseDouble(split[6].equals("tbd")?"0":split[6].isBlank()?"0":split[6]),
                                        Double.parseDouble(split[7].equals("tbd")?"0":split[7].isBlank()?"0":split[7]),
                                        Double.parseDouble(split[8].equals("tbd")?"0":split[8].isBlank()?"0":split[8]),
                                        Double.parseDouble(split[9].equals("tbd")?"0":split[9].isBlank()?"0":split[9]),
                                        Double.parseDouble(split[10].equals("tbd")?"0":split[10].isBlank()?"0":split[10]),
                                        Double.parseDouble(split[11].equals("tbd")?"0":split[11].isBlank()?"0":split[11]),
                                        Double.parseDouble(split[12].equals("tbd")?"0":split[12].isBlank()?"0":split[12]),
                                        split[13],
                                        split[14]
                                )
                        );
                    }
                    else {
                        videoGames.add(
                                new VideoGame(
                                        split[0],
                                        split[1],
                                        Double.parseDouble(split[2].equals("N/A")?"0":split[2]),
                                        split[3],
                                        split[4],
                                        Double.parseDouble(split[5].equals("tbd")?"0":split[5].isBlank()?"0":split[5]),
                                        Double.parseDouble(split[6].equals("tbd")?"0":split[6].isBlank()?"0":split[6]),
                                        Double.parseDouble(split[7].equals("tbd")?"0":split[7].isBlank()?"0":split[7]),
                                        Double.parseDouble(split[8].equals("tbd")?"0":split[8].isBlank()?"0":split[8]),
                                        Double.parseDouble(split[9].equals("tbd")?"0":split[9].isBlank()?"0":split[9]),
                                        0, 0, 0,
                                        "",
                                        ""
                                )
                        );
                    }
                } catch (NumberFormatException ignore) { }
            }
            System.out.println("Loaded ["+videoGames.size()+"/"+total+"]\nMalformed ["+(total-videoGames.size())+"]");
            return (total-videoGames.size())/100<75;
        }
        return false;
    }

    public List<VideoGame> getVideoGames() {
        return videoGames;
    }
}
