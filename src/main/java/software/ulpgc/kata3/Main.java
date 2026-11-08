package software.ulpgc.kata3;

import software.ulpgc.kata3.domain.Game;
import software.ulpgc.kata3.io.JsonGameSerializer;
import software.ulpgc.kata3.io.UrlGameReader;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        String inputUrl = "https://raw.githubusercontent.com/Antonio11406/kata3/main/texto.csv";
        File outputFile = new File("games_output.json");

        UrlGameReader reader = new UrlGameReader(inputUrl);
        List<Game> games = reader.readAll();
        JsonGameSerializer serializer = new JsonGameSerializer(outputFile);
        try {
            // Pasamos ambos datos al serializador
            serializer.serialize(games);
            System.out.println("Proceso completado. Válidos: " + games.size());
        } catch (Exception e) {
            System.err.println("Error al serializar el JSON: " + e.getMessage());
        }
    }
}
