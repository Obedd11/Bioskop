package Controllers;

import Entities.Film_Entity;
import Models.Data.Film_Model;

import java.util.ArrayList;

public class Film_Controller {

    public void initialData()
    {
        Film_Model.initialData();
    }


    public void create(String genre, String judul, String jamTayang, String studio, boolean tayang) {
        Film_Entity filmEntity=new Film_Entity();
        filmEntity.setGenre(genre);
        filmEntity.setJudul(judul);
        filmEntity.setJamTayang(jamTayang);
        filmEntity.setStudio(studio);
        filmEntity.setTayang(tayang);
        Film_Model.create(filmEntity);
    }
    public void delete(int index) {
        Film_Model.delete(index);
    }


    public Film_Entity getIndex(int index) {
        return Film_Model.read().get(index);
    }


    public ArrayList<Film_Entity> read() {
        return Film_Model.read();
    }

    public void update(int index, String genre, String judul, String jamTayang, String studio, boolean tayang) {
        Film_Entity filmEntity=new Film_Entity();
        filmEntity.setGenre(genre);
        filmEntity.setJudul(judul);
        filmEntity.setJamTayang(jamTayang);
        filmEntity.setStudio(studio);
        filmEntity.setTayang(tayang);
        Film_Model.update( index,filmEntity);

    }
}
