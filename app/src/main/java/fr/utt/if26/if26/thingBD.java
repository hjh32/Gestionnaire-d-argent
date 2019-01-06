package fr.utt.if26.if26;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class thingBD extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "things.db"; // nom du fichier pour la base
    private static final String TABLE_THINGS = "things"; // nom de la table
    private static final String ATTRIBUT_CATEGORIE = "categorie"; // liste des attributs
    private static final String ATTRIBUT_DATE = "date";
    private static final String ATTRIBUT_DESCRIPTION = "description";
    private static final String ATTRIBUT_PRIX = "prix";
    private static thingBD instance;
    private static SQLiteDatabase db;

    public static thingBD getInstance(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        if(instance == null) {
            instance = new thingBD(context, name, factory, version);
            db = instance.getWritableDatabase();
        }
        return instance;
    }

    private thingBD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String table_create =
                "CREATE TABLE " + TABLE_THINGS +
                        "(" +
                        "ID int PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                        ATTRIBUT_CATEGORIE + " TEXT, " +
                        ATTRIBUT_DATE + " TEXT, " +
                        ATTRIBUT_DESCRIPTION + " TEXT, " +
                        ATTRIBUT_PRIX + " TEXT" +
                        ")";
        db.execSQL(table_create);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addThing(thing t) {
        ContentValues values = new ContentValues();
        values.put(ATTRIBUT_CATEGORIE, t.getCat());
        values.put(ATTRIBUT_DATE, t.getDate());
        values.put(ATTRIBUT_DESCRIPTION, t.getDescription());
        values.put(ATTRIBUT_PRIX, t.getPrix());
        db.insert(TABLE_THINGS, null, values);
    }


    public void initData() {
        List<thing> things = getAllThings();
    }


    public void delThing(thing t) {

    }


    public void updateThing(thing t) {

    }


    public thing getThing(String key) {
        return null;
    }


    public int countThing() {
        return 0;
    }

    public static void delThing(String ID) {
        db.delete(TABLE_THINGS, "ID=?", new String[]{ID});
    }


    public void updateThing(String ID) {
        //db.update(TABLE_THINGS, values,  "ID = ?", new String[] { "The Da Vinci Code" });
    }


    public thing getThings(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        int id = Integer.parseInt(ID);
        String query = "SELECT " + ATTRIBUT_CATEGORIE+ ", " + ATTRIBUT_DATE + ", " + ATTRIBUT_DESCRIPTION + ", " + ATTRIBUT_PRIX +
                " FROM " + TABLE_THINGS + " WHERE ID='" + id + "'";
        Cursor cursor = db.rawQuery(query, null);
        thing t = null;
        while(cursor.moveToNext()) {
            t = new thing(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        }
        return t;
    }



    public ArrayList<thing>  getAllThings() {
        String query = "SELECT " + ATTRIBUT_CATEGORIE + ", " + ATTRIBUT_DATE + ", " + ATTRIBUT_DESCRIPTION + ", " + ATTRIBUT_PRIX +
                " FROM " + TABLE_THINGS;
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<thing> things = new ArrayList<>();
        while(cursor.moveToNext()) {
            thing t = new thing(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            things.add(t);
        }
        return things;
    }
}
