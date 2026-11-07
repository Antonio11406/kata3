package software.ulpgc.kata3.io;

import software.ulpgc.kata3.domain.Game;
import software.ulpgc.kata3.domain.Genre;
import software.ulpgc.kata3.domain.Platform;

import java.io.*;
import java.util.List;
import java.util.Objects;

public class FileGameReader implements GameReader {
    private final File file;

    public FileGameReader(File file) {
        this.file = file;
    }

    @Override
    public List<Game> readAll() {
        InputStream is = new FileInputStream(file);
        return LoadFrom(is);
    }

    private List<Game> LoadFrom(InputStream is) {
        return new BufferedReader(new InputStreamReader(is)).lines().skip(1)
                .map(this::toGame).filter(Objects::nonNull).toList();
    }

    private Game toGame(String s) {
        return toGame(s.split(","));
    }

    private Game toGame(String[] split) {
        try{
            return new Game(split[1], genreOf(split[2]), Integer.parseInt(split[3]), platformOf(split[4]));
        }catch (Exception e){
            return null;
        }
    }

    private Platform platformOf(String s) {
        return Platform.valueOf(s);
    }

    private Genre genreOf(String s) {
        return Genre.valueOf(s);
    }
}
