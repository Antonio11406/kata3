package software.ulpgc.kata3.io;

import software.ulpgc.kata3.domain.Game;
import software.ulpgc.kata3.domain.Genre;
import software.ulpgc.kata3.domain.Platform;

import javax.print.DocFlavor;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class UrlGameReader implements UrlReader{
    private final String urlLink;

    public UrlGameReader(String url) {
        this.urlLink = url;
    }

    @Override
    public List<Game> readAll() throws MalformedURLException {
        URL url = new URL(urlLink);
        try (InputStream is = url.openStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            return reader.lines()
                    .skip(1)
                    .map(this::toGame)
                    .filter(Objects::nonNull)
                    .toList();
        } catch (Exception e) {
            return null;
        }
    }

    private Game toGame(String lines) {
        return toGame(lines.split(","));
    }

    private Game toGame(String [] fields) {
        try {
            return new Game(fields[1].trim(), platformOf(fields[2].trim()), Integer.parseInt(fields[3].trim()), genreOf(fields[4].trim()));
        }catch (Exception e){ return null;}
    }

    private Genre genreOf(String trim) {
        return Genre.valueOf(trim);
    }

    private Platform platformOf(String trim) {
        return Platform.valueOf(trim);
    }

}
