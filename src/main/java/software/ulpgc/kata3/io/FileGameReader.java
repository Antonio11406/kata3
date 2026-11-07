package software.ulpgc.kata3.io;

import software.ulpgc.kata3.domain.Game;
import software.ulpgc.kata3.domain.Genre;
import software.ulpgc.kata3.domain.Platform;

import java.io.*;
import java.util.List;
import java.util.Objects;

public class FileGameReader implements GameReader{
    private final File file;

    public FileGameReader(File file) {
        this.file = file;
    }


    @Override
    public List<Game> readAll(){
        InputStream is = new FileInputStream(file);
        return loadFrom(is);
    }

    private List<Game> loadFrom(InputStream is){
        return new BufferedReader(new InputStreamReader(is))
                .lines().skip(1).map(this::toGame)
                .filter(Objects::nonNull).toList();
    }

    private Game toGame(String lines){
        return toGame(lines.split(","));
    }

    private Game toGame(String[] fields){
        return new Game(fields[1].trim(), platformOf(fields[2]), Integer.parseInt(fields[3]), genreOf(fields[4]));
    }

    private Genre genreOf(String field) {
        return Genre.valueOf(field.toUpperCase());
    }

    private Platform platformOf(String field) {
        return Platform.valueOf(field.toUpperCase());
    }

}
