package com.elsys.refpro.refpromobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalDatabase extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "activity_info";
    private static final String COL1 = "ID";
    private static final String COL2 = "competition";
    private static final String COL3 = "venue";
    private static final String COL4 = "home";
    private static final String COL5 = "away";
    private static final String COL6 = "date";
    private static final String COL7 = "time";
    private static final String COL8 = "players";
    private static final String COL9 = "substitutes";
    private static final String COL10 = "length";
    private static final String COL11 = "MongoID";

    public LocalDatabase(Context context) {
        super(context, TABLE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 +  " TEXT, "
                + COL3 +  " TEXT, " + COL4 +  " TEXT, " + COL5 +  " TEXT, " + COL6 +  " TEXT, " + COL7 +  " TEXT, "
                + COL8 +  " TEXT, " + COL9 +  " TEXT, " + COL10 +  " TEXT, " + COL11 + " TEXT)";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String competition, String venue, String home, String away, String date, String time, int players, int substitutes,
                           int length, String MongoID) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, competition);
        contentValues.put(COL3, venue);
        contentValues.put(COL4, home);
        contentValues.put(COL5, away);
        contentValues.put(COL6, date);
        contentValues.put(COL7, time);
        contentValues.put(COL8, players);
        contentValues.put(COL9, substitutes);
        contentValues.put(COL10, length);
        contentValues.put(COL11, MongoID);

        long result = db.insert(TABLE_NAME, null, contentValues);

        return result != -1;
    }

    public Cursor getData() {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL8 + " ASC";
        return db.rawQuery(query, null);
    }

    public void delete(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'";
        db.execSQL(query);
    }

    public Cursor getRow(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL1 + " = '" + id + "'";
        return db.rawQuery(query, null);
    }
}
