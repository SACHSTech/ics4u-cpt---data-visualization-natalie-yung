package basic;

/**
 * Represents a video game
 */
public class VideoGame implements Comparable<String>{
    public String name, platform, genre, publisher, developer, rating; // Rating could also be represented as Enum
    public double year, NA_Sales, EU_Sales, JP_Sales, otherSales, globalSales, criticScore, userScore, userCount;

    public VideoGame() { }

    public VideoGame(String name, String platform, double year, String genre, String publisher, double NA_Sales, double EU_Sales, double JP_Sales, double otherSales, double globalSales, double criticScore, double userScore, double userCount, String developer, String rating) {
        this.name = name;
        this.platform = platform;
        this.genre = genre;
        this.publisher = publisher;
        this.developer = developer;
        this.rating = rating;
        this.year = year;
        this.NA_Sales = NA_Sales;
        this.EU_Sales = EU_Sales;
        this.JP_Sales = JP_Sales;
        this.otherSales = otherSales;
        this.globalSales = globalSales;
        this.criticScore = criticScore;
        this.userScore = userScore;
        this.userCount = userCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public double getYear() {
        return year;
    }

    public void setYear(double year) {
        this.year = year;
    }

    public double getNA_Sales() {
        return NA_Sales;
    }

    public void setNA_Sales(double NA_Sales) {
        this.NA_Sales = NA_Sales;
    }

    public double getEU_Sales() {
        return EU_Sales;
    }

    public void setEU_Sales(double EU_Sales) {
        this.EU_Sales = EU_Sales;
    }

    public double getJP_Sales() {
        return JP_Sales;
    }

    public void setJP_Sales(double JP_Sales) {
        this.JP_Sales = JP_Sales;
    }

    public double getOtherSales() {
        return otherSales;
    }

    public void setOtherSales(double otherSales) {
        this.otherSales = otherSales;
    }

    public double getGlobalSales() {
        return globalSales;
    }

    public void setGlobalSales(double globalSales) {
        this.globalSales = globalSales;
    }

    public double getCriticScore() {
        return criticScore;
    }

    public void setCriticScore(double criticScore) {
        this.criticScore = criticScore;
    }

    public double getUserScore() {
        return userScore;
    }

    public void setUserScore(double userScore) {
        this.userScore = userScore;
    }

    public double getUserCount() {
        return userCount;
    }

    public void setUserCount(double userCount) {
        this.userCount = userCount;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        prettify(sb, sb.append(name), sb.append(platform), sb.append(genre), sb.append(publisher), sb.append(developer), sb.append(rating), year);
        prettify(sb, sb.append(NA_Sales), sb.append(EU_Sales), sb.append(JP_Sales), sb.append(otherSales), sb.append(globalSales), sb.append(criticScore), userScore);
        sb.append(userCount).append('\t');
        return sb.toString();
    }

    private void prettify(StringBuffer sb, StringBuffer append, StringBuffer append2, StringBuffer append3, StringBuffer append4, StringBuffer append5, StringBuffer append6, double year) {
        append.append('\t');
        append2.append('\t');
        append3.append('\t');
        append4.append('\t');
        append5.append('\t');
        append6.append('\t');
        sb.append(year).append('\t');
    }

    @Override
    public int compareTo(String o) {
        return o.compareTo(this.name);
    }
}
