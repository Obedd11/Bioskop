package Entities;

public class Film_Entity {
    private String genre;
    private String judul;
    private String jamTayang;
    private String studio;
    private boolean tayang;

    public Film_Entity(String genre, String judul, String jamTayang, String studio, boolean tayang) {

        this.genre = genre;
        this.judul = judul;
        this.jamTayang = jamTayang;
        this.studio = studio;
        this.tayang = tayang;
    }

    public Film_Entity() {

    }


    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getJudul() {
        return judul;
    }
    public void setJudul(String judul) {
        this.judul = judul;
    }
    public String getJamTayang() {
        return jamTayang;
    }
    public void setJamTayang(String jamTayang) {
        this.jamTayang = jamTayang;
    }
    public String getStudio() {
        return studio;
    }
    public void setStudio(String studio) {
        this.studio = studio;
    }
    public boolean isTayang() {
        return tayang;
    }
    public void setTayang(boolean tayang) {
        this.tayang = tayang;
    }

}
