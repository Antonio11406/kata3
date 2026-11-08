package software.ulpgc.kata3.io;

import software.ulpgc.kata3.domain.Game;
import software.ulpgc.kata3.domain.Genre;
import software.ulpgc.kata3.domain.Platform;

import java.io.*;
import java.util.List;
import java.util.Objects;

public class UrlGameReader implements UrlReader{
    private final File file;

    public UrlGameReader(File file) {
        this.file = file;
    }

    @Override
    public List<Game> readAll() {
        try (InputStream is = new FileInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            return reader.lines()
                    .skip(1)
                    .map(this::toGame)
                    .filter(Objects::nonNull)
                    .toList();
        } catch (Exception e) {
        }
    }

    private Game toGame(String lines) {
        return toGame(lines.split(","));
    }

    private Game toGame(String [] fields){
        return new Game(fields[1].trim(), platformOf(fields[2].trim()), Integer.parseInt(fields[3].trim()), genreOf(fields[4].trim()));
    }

    private Genre genreOf(String trim) {
        return Genre.valueOf(trim);
    }

    private Platform platformOf(String trim) {
        return Platform.valueOf(trim);
    }

}
