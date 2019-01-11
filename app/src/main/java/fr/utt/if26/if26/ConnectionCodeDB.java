package fr.utt.if26.if26;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class ConnectionCodeDB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "connectionCode.db"; // nom du fichier pour la base
    private static final String TABLE_CONNECTIONCODE = "connectioncode"; // nom de la table
    private static final String ATTRIBUT_CODE = "code"; // liste des attributs
    private static ConnectionCodeDB instance;
    private static SQLiteDatabase db;

    public static ConnectionCodeDB getInstance(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        if(instance == null) {
            instance = new ConnectionCodeDB(context, name, factory, version);
            db = instance.getWritableDatabase();
        }
        return instance;
    }

    private ConnectionCodeDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String table_create =
                "CREATE TABLE IF NOT EXISTS " + TABLE_CONNECTIONCODE +
                        "(" +
                        ATTRIBUT_CODE + " TEXT" +
                        ")";
        db.execSQL(table_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addCode(String code) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(code.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        ContentValues values = new ContentValues();
        values.put(ATTRIBUT_CODE, sb.toString());
        db.insert(TABLE_CONNECTIONCODE, null, values);
    }

    public boolean hasCode() {
        long count = DatabaseUtils.queryNumEntries(db, TABLE_CONNECTIONCODE);
        return count == 0 ? false : true;
    }

    public String getCode() {
        String query = "SELECT " + ATTRIBUT_CODE + " FROM " + TABLE_CONNECTIONCODE;
        Cursor cursor = db.rawQuery(query, null);
        String code = null;
        while(cursor.moveToNext()) {
            code = cursor.getString(0);
        }
        return code;
    }
}
