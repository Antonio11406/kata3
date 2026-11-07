package software.ulpgc.kata3.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import software.ulpgc.kata3.domain.Game;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonGameSerializer implements GameSerializer{
    private final File file;

    public JsonGameSerializer(File file) {
        this.file = file;
    }

    @Override
    public void serialize(List<Game> games) throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Map<String, Object> finalOutput = new HashMap<>();
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("total_registro", games.size());
        metadata.put("fehca_generacion", LocalDate.now().toString());
        finalOutput.put("resumen", metadata);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(finalOutput, writer);
        }
    }
}
