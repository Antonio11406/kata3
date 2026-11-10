package software.ulpgc.kata3.io;

import software.ulpgc.kata3.domain.Game;

import java.net.MalformedURLException;
import java.util.List;

public interface UrlReader {
    List<Game> readAll() throws MalformedURLException;
}
