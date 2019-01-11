package fr.utt.if26.if26;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ThingBD extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "things.db"; // nom du fichier pour la base
    private static final String TABLE_THINGS = "things"; // nom de la table
    private static final String ATTRIBUT_ID = "id"; // liste des attributs
    private static final String ATTRIBUT_CATEGORIE = "categorie";
    private static final String ATTRIBUT_YEAR = "year";
    private static final String ATTRIBUT_MONTH = "month";
    private static final String ATTRIBUT_DAY = "day";
    private static final String ATTRIBUT_DESCRIPTION = "description";
    private static final String ATTRIBUT_PRIX = "prix";
    private static ThingBD instance;
    private static SQLiteDatabase db;

    public static ThingBD getInstance(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        if(instance == null) {
            instance = new ThingBD(context, name, factory, version);
            db = instance.getWritableDatabase();
        }
        return instance;
    }

    private ThingBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String table_create =
                "CREATE TABLE IF NOT EXISTS " + TABLE_THINGS +
                        "(" +
                        ATTRIBUT_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                        ATTRIBUT_CATEGORIE + " TEXT, " +
                        ATTRIBUT_YEAR + " INTEGER, " +
                        ATTRIBUT_MONTH + " INTEGER, " +
                        ATTRIBUT_DAY + " INTEGER, " +
                        ATTRIBUT_DESCRIPTION + " TEXT, " +
                        ATTRIBUT_PRIX + " REAL" +
                        ")";
        db.execSQL(table_create);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addThing(Thing t) {
        ContentValues values = new ContentValues();
        values.put(ATTRIBUT_CATEGORIE, t.getCat());
        values.put(ATTRIBUT_YEAR, t.getYear());
        values.put(ATTRIBUT_MONTH, t.getMonth());
        values.put(ATTRIBUT_DAY, t.getDay());
        values.put(ATTRIBUT_DESCRIPTION, t.getDescription());
        values.put(ATTRIBUT_PRIX, t.getPrix());
        db.insert(TABLE_THINGS, null, values);
    }


    public void delThing(int ID) {
        db.delete(TABLE_THINGS, "ID=" + ID, null);
    }

    public Thing getThings(String ID) {
        int id = Integer.parseInt(ID);
        String query = "SELECT " + ATTRIBUT_ID + ", " + ATTRIBUT_YEAR + ", " + ATTRIBUT_MONTH + ", " + ATTRIBUT_DAY + ", " + ATTRIBUT_CATEGORIE + ", " + ATTRIBUT_DESCRIPTION + ", " + ATTRIBUT_PRIX +
                " FROM " + TABLE_THINGS + " WHERE id=" + id;
        Cursor cursor = db.rawQuery(query, null);
        Thing t = null;
        while(cursor.moveToNext()) {
            t = new Thing(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getDouble(6));
        }
        return t;
    }



    public ArrayList<Thing>  getAllThings(int year, int month) {
        String query = "SELECT " + ATTRIBUT_ID + ", " + ATTRIBUT_YEAR + ", " + ATTRIBUT_MONTH + ", " + ATTRIBUT_DAY + ", " + ATTRIBUT_CATEGORIE + ", " + ATTRIBUT_DESCRIPTION + ", " + ATTRIBUT_PRIX +
                " FROM " + TABLE_THINGS + " WHERE " + ATTRIBUT_CATEGORIE + "<>'revenue' AND year=" + year + " AND month=" + month + " ORDER BY day DESC";
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Thing> things = new ArrayList<>();
        while(cursor.moveToNext()) {
            Thing t = new Thing(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getDouble(6));
            things.add(t);
        }
        return things;
    }

    public double getRevenue(int year, int month) {
        double revenue = 0;
        String query = "SELECT " + ATTRIBUT_PRIX +
                " FROM " + TABLE_THINGS + " WHERE year=" + year + " AND month =" + month + " AND categorie='revenue'";
        Cursor cursor = db.rawQuery(query, null);
        while(cursor.moveToNext()) {
            revenue = cursor.getDouble(0);
        }
        return revenue;
    }

    public void updateRevenue(int year, int month, double prix) {
        ContentValues values = new ContentValues();
        values.put(ATTRIBUT_PRIX, prix);
        db.update(TABLE_THINGS, values, ATTRIBUT_YEAR + "=" + year + " AND " + ATTRIBUT_MONTH + "=" + month + " AND " + ATTRIBUT_CATEGORIE + "='revenue'", null);
    }

    public double calcuteDepenses(int year, int month) {
        List<Thing> things = getAllThings(year, month);
        double depenses = 0;
        for (Thing t:
             things) {
            depenses = depenses + t.getPrix();
        }
        return depenses;
    }
}
