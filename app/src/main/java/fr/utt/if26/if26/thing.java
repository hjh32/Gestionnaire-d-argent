package fr.utt.if26.if26;
import java.io.Serializable;
public class thing implements Serializable {
    private String date;
    private String description;
    private String prix;
    private String cat;

    public thing(String cat,String date, String description, String prix) {
        this.cat = cat;
        this.date = date;
        this.description = description;
        this.prix = prix;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getPrix() {
        return prix;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String toString() {
        return "thing{" +
                "cat=" + cat + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", prix='" + prix + '\'' +
                '}';
    }

}
