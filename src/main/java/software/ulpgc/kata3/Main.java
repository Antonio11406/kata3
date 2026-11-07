package software.ulpgc.kata3;

import software.ulpgc.kata3.domain.Game;
import software.ulpgc.kata3.io.FileGameReader;
import software.ulpgc.kata3.io.JsonGameSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("games.csv");
        File outputFile = new File("games_output.json");

        FileGameReader reader = new FileGameReader(inputFile);
        List<Game> games = reader.readAll();

        int descartados = reader.getDiscard();

        JsonGameSerializer serializer = new JsonGameSerializer(outputFile, descartados);

        try {
            serializer.serialize(games);
            System.out.println("Proceso completado. Válidos: " + games.size() + " | Descartados: " + descartados);
        } catch (Exception e) {
            System.err.println("Error al serializar el JSON: " + e.getMessage());
        }
    }
}
