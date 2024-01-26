package Models.Data;

import Entities.DetailTrans_Entity;
import Entities.Film_Entity;
import Entities.User_Entity;
import Models.JSON.ModelJSON;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DetailTrans_Model {
    private static final String FILE_NAME = "src/Database/DetailTrans.json";
    private static ArrayList<DetailTrans_Entity> detailTrans_entities = new ArrayList<>();
    private static ModelJSON<DetailTrans_Entity> modelJSON = new ModelJSON<>(FILE_NAME);

    public static void initialData() {
        detailTrans_entities = modelJSON.readFromFile(getTypeToken());
        if (detailTrans_entities.isEmpty()) {
            Film_Entity filmEntity = new Film_Entity("Horor", "Horor 2", "16.00", "Studio 2", true);
            User_Entity userEntity = new User_Entity("user1");
            detailTrans_entities.add(new DetailTrans_Entity(filmEntity, userEntity));
            modelJSON.writeToFile(detailTrans_entities);
        }
    }

    public static void create(Object data) {
        detailTrans_entities = modelJSON.readFromFile(getTypeToken());
        detailTrans_entities.add((DetailTrans_Entity) data);
        modelJSON.writeToFile(detailTrans_entities);
    }

    public static DetailTrans_Entity getIndex(int index) {
        detailTrans_entities = modelJSON.readFromFile(getTypeToken());
        return detailTrans_entities.get(index);
    }

    public static ArrayList<DetailTrans_Entity> read() {
        return modelJSON.readFromFile(getTypeToken());
    }

    private static Type getTypeToken() {
        return new TypeToken<ArrayList<DetailTrans_Entity>>() {
        }.getType();
    }
}
