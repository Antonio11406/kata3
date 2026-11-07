package software.ulpgc.kata3;

import software.ulpgc.kata3.domain.Game;
import software.ulpgc.kata3.io.FileGameReader;
import software.ulpgc.kata3.io.JsonGameSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // 1. Definir los archivos de entrada y salida
        File inputFile = new File("games.csv");
        File outputFile = new File("games_output.json");

        // 2. Usar el Reader (que internamente have el parsing) para obtener los objetos
        FileGameReader reader = new FileGameReader(inputFile);
        List<Game> games = reader.readAll();

        // 3. Usar el Serializer para escribir la salida JSON
        JsonGameSerializer serializer = new JsonGameSerializer(outputFile);

        try {
            serializer.serialize(games);
            System.out.println("JSON generado exitosamente con " + games.size() + " registros.");
        } catch (Exception e) {
            System.err.println("Error al serializar el JSON: " + e.getMessage());
        }
    }
}
