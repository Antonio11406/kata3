package software.ulpgc.kata3.io;

import software.ulpgc.kata3.domain.Game;
import software.ulpgc.kata3.domain.Genre;
import software.ulpgc.kata3.domain.Platform;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FileGameReader implements GameReader {
    private final File file;
    private int discard;

    public FileGameReader(File file) {

        this.file = file;
        this.discard = 0;
    }

    @Override
    public List<Game> readAll() throws FileNotFoundException {
        try (InputStream is = new FileInputStream(file)) {
            return loadFrom(is);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private List<Game> loadFrom(InputStream is) {
        return new BufferedReader(new InputStreamReader(is)).lines().skip(1)
                .map(this::toGame).filter(Objects::nonNull).toList();
    }

    private Game toGame(String s) {
        return toGame(s.split(","));
    }

    private Game toGame(String[] split) {
        try{
            return new Game(split[1].trim(), platformOf(split[2].trim()), Integer.parseInt(split[3].trim()), genreOf(split[4].trim()));
        }catch (Exception e){
            discard++;
            return null;
        }
    }

    private Platform platformOf(String s) {
        return Platform.valueOf(s);
    }

    private Genre genreOf(String s) {
        return Genre.valueOf(s);
    }

    public int getDiscard() {
        return discard;
    }
}
