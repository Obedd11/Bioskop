package Entities;

public class DetailTrans_Entity {
    private Film_Entity filmEntity;
    private User_Entity userEntity;

    public DetailTrans_Entity(Film_Entity filmEntity, User_Entity userEntity) {
        this.filmEntity = filmEntity;
        this.userEntity = userEntity;
    }

    public Film_Entity getFilmEntity() {
        return filmEntity;
    }

    public User_Entity getUserEntity() {
        return userEntity;
    }

    public void setFilmEntity(Film_Entity filmEntity) {
        this.filmEntity = filmEntity;
    }

    public void setUserEntity(User_Entity userEntity) {
        this.userEntity = userEntity;
    }
}
