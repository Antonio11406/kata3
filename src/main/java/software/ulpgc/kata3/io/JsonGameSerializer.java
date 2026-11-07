package software.ulpgc.kata3.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import software.ulpgc.kata3.domain.Game;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonGameSerializer implements GameSerializer{
    private final File file;
    private int discard;

    public JsonGameSerializer(File file, int discard) {
        this.file = file;
        this.discard = discard;
    }

    @Override
    public void serialize(List<Game> games) throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Map<String, Object> finalOutput = new HashMap<>();
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("total_registros_guardados", games.size());
        metadata.put("total_registros_rechazados", discard);
        metadata.put("fecha_generacion", java.time.LocalDate.now().toString());
        finalOutput.put("resumen", metadata);
        finalOutput.put("videojuegos", games);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(finalOutput, writer);
        }
    }
}