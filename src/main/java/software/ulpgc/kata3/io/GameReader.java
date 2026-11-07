package software.ulpgc.kata3.io;

import software.ulpgc.kata3.domain.Game;

import java.util.List;

public interface GameReader {
    List<Game> Games readAll();
}
