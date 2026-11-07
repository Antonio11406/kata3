package software.ulpgc.kata3.io;

import software.ulpgc.kata3.domain.Game;

import java.util.List;

public interface GameSerializer {
    void serialize(List<Game> games) throws  Exception
}
