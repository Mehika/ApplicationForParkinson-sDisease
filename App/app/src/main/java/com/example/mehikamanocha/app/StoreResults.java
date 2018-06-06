package com.example.mehikamanocha.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mehika.manocha on 13/03/2018.
 */

public class StoreResults extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Results";
    public static final String TABLE_NAME = "FingerTapping" +
            "";
    public static final String COL_1 = "TITLE";
    public static final String COL_2 = "DATE";
    public static final String COL_3 = "ID";
    public static final String COL_4 = "CONTENT";
    public static final String COL_5 = "AGE";


    public StoreResults(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,TITLE TEXT,DATE TEXT,CONTENT TEXT)");
    }

    public Cursor Get_Note(SQLiteDatabase db) {
        Cursor cursor;
        String[] projections = {COL_1, COL_2, COL_3, COL_4};
        cursor = db.query(TABLE_NAME, projections, null, null, null, null, null);
        return cursor;
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    /**
     * This method is called whenever the user wants to create a new note
     *
     * @param Title The title of the note the user wants to save
     * @param Date  The date on which the note is saved
     * @param note  The content of the note the user wants to save
     * @return a boolean is returned representing whether the data was saved or not
     */
    public boolean Insert_Data(String Title, String Date, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, Title);
        contentValues.put(COL_2, Date);
        contentValues.put(COL_4, String.valueOf(note));
        contentValues.put(COL_5, String.valueOf(note));
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method is called whenever the user wants to edit an already existing note
     *
     * @param old_id      The id of the note remains the same, and is used to retrieve the note from db
     * @param new_title   The new title the user wants to put in the note
     * @param new_date    The new date when the user updated the note
     * @param new_content The new content the user wants to put in the note
     */
    public void Update_Note(long old_id, String new_title, String new_date, String new_content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COL_1, new_title);
        args.put(COL_2, new_content);
        args.put(COL_4, new_date);
        db.update(TABLE_NAME, args, COL_3 + "= ?", new String[]{String.valueOf(old_id)});
        db.close();
    }

    /**
     * This method is called when the user wants to delete a note
     *
     * @param id
     */
    public void Delete_Note(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String select = COL_3 + " LIKE ?";
        System.out.println("Deleting the id: " + id);
        String[] select_args = {id};
        db.delete(TABLE_NAME, select, select_args);
    }
}
