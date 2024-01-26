package Models.Data;

import Entities.Film_Entity;
import Models.JSON.ModelJSON;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Film_Model {

    private static final String FILE_NAME = "src/Database/Films.json"; // Ganti dengan nama file yang sesuai
    private static ArrayList<Film_Entity> films = new ArrayList<>();
    private static ModelJSON<Film_Entity> modelJSON = new ModelJSON<>(FILE_NAME);

    public static void initialData() {
        films = modelJSON.readFromFile(getTypeToken());
        if (films.isEmpty()) {
            films.add(new Film_Entity("Horor", "Horor 1", "14.00", "Studio 1", true));
            films.add(new Film_Entity("Horor", "Horor 2", "16.00", "Studio 2", true));
            films.add(new Film_Entity("Horor", "Horor 3", "18.00", "Studio 3", false));
            modelJSON.writeToFile(films);
        }
    }

    public static void create(Object data) {
        films = modelJSON.readFromFile(getTypeToken());
        films.add((Film_Entity) data);
        modelJSON.writeToFile(films);
    }

    public static void update(int index, Object data) {
        films = modelJSON.readFromFile(getTypeToken());
        films.set(index, (Film_Entity) data);
        modelJSON.writeToFile(films);
    }

    public static void delete(int index) {
        films = modelJSON.readFromFile(getTypeToken());
        films.remove(index);
        modelJSON.writeToFile(films);
    }

    public static Film_Entity getIndex(int index) {
        films = modelJSON.readFromFile(getTypeToken());
        return films.get(index);
    }

    public static ArrayList<Film_Entity> read() {
        return modelJSON.readFromFile(getTypeToken());
    }

    private static Type getTypeToken() {
        return new TypeToken<ArrayList<Film_Entity>>() {
        }.getType();
    }
}
