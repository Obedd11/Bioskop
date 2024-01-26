package Models.Data;

import Entities.User_Entity;
import Models.JSON.ModelJSON;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class User_Model {
    private static final String FILE_NAME = "src/Database/Users.json"; // Change the file path as needed
    private static ArrayList<User_Entity> users = new ArrayList<>();
    private static ModelJSON<User_Entity> modelJSON = new ModelJSON<>(FILE_NAME);

    public static void initialData() {
        users = modelJSON.readFromFile(getTypeToken());
        if (users.isEmpty()) {
            users.add(new User_Entity("user1", "admin@gmail.com", "password1", "admin"));
            users.add(new User_Entity("user2", "user1@gmail.com", "password2", "user"));
            modelJSON.writeToFile(users);
        }
    }

    public static User_Entity getIndex(int index) {
        users = modelJSON.readFromFile(getTypeToken());
        return users.get(index);
    }

    public static ArrayList<User_Entity> read() {
        return modelJSON.readFromFile(getTypeToken());
    }

    private static Type getTypeToken() {
        return new TypeToken<ArrayList<User_Entity>>() {
        }.getType();
    }
}
