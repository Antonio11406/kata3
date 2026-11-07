package software.ulpgc.kata3.domain;

public class Game {
    private String name;
    private Platform platform;
    private int year;
    private Genre genre;

    public Game(String name, Platform platform, int year, Genre genre){
        this.name = name;
        this.platform = platform;
        this.year = year;
        this.genre = genre;
    }
}
