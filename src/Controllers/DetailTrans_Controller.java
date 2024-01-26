package Controllers;

import Entities.DetailTrans_Entity;
import Entities.Film_Entity;
import Entities.User_Entity;
import Models.Data.DetailTrans_Model;

import java.util.ArrayList;

public class DetailTrans_Controller {

    public void inititalData()
    {
        DetailTrans_Model.initialData();
    }
    public static void create(Film_Entity filmEntity, User_Entity userEntity){
        DetailTrans_Entity detailTransaksiEntity = new DetailTrans_Entity(filmEntity, userEntity);
        detailTransaksiEntity.setFilmEntity(filmEntity);
        detailTransaksiEntity.setUserEntity(userEntity);
        DetailTrans_Model.create(detailTransaksiEntity);
    }

    public ArrayList<DetailTrans_Entity> read() {
        return DetailTrans_Model.read();
    }
}
