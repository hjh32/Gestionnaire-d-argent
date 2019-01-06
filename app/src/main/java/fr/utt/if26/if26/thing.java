package fr.utt.if26.if26;
import java.io.Serializable;
public class thing implements Serializable {
    private int id;
    private int year;
    private int month;
    private int day;
    private String description;
    private double prix;
    private String cat;

    public thing(int id, int year, int month, int day, String cat, String description, double prix) {
        this.cat = cat;
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.description = description;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

}
