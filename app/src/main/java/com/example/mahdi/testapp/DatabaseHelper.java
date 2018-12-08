package com.example.mahdi.testapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.mahdi.testapp.Model.definition;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String Database_Name ="myglosary.db";
    public static final String TABLE_NAME = "Vocab_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "word";
    public static final String COL3 = "meaning";


    //public static final String TABLE_NAME = "Vocab_table";
    //public static final String COL1 = "ID";
    //public static final String COL2 = "Word";
    //public static final String COL3 = "Meaning";



    public DatabaseHelper(Context context) {
        super(context, Database_Name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            String CreateTable = "CREATE TABLE " + TABLE_NAME + "(" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ COL2 + " TEXT,"+ COL3 + " TEXT)";
            db.execSQL(CreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    // function get All meanings (all*)

    public List<definition> getDefinitions()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        // Make sure this names are your column names in database
        String [] sqlSelect = {"ID", "word", "meaning"};
        String tableName = TABLE_NAME;
        qb.setTables(tableName);

        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<definition> result = new ArrayList<>();

        if(cursor.moveToFirst())
        {
            do{
                definition  def = new definition();
                def.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                def.setWord(cursor.getString(cursor.getColumnIndex("word")));
                def.setMeaning(cursor.getString(cursor.getColumnIndex("meaning")));
                result.add(def);
            }
            while (cursor.moveToNext());
        }
        return result;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getItemID(String word){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 +" = '" + word + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor MeaningID(String meaning){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL3   +" = '" + meaning + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public void updateWord(String newword, int id, String oldword){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newword + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldword + "'";
        db.execSQL(query);
    }
    public void updateMeaing(String newMeaning, int id, String oldMeaning){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL3 +
                " = '" + newMeaning + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL3 + " = '" + oldMeaning + "'";
        db.execSQL(query);
    }
    public void deleteMeaning(int id, String meaning){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL3 + " = '" + meaning + "'";
        db.execSQL(query);
    }
    public void deleteName(int id, String word){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + word + "'";
        db.execSQL(query);
    }

    // function get All Definition's (class) word
    public List<String> getWord(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        // Make sure this names are your column names in database
        String [] sqlSelect = { "word"};
        String tableName = TABLE_NAME;
        qb.setTables(tableName);

        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<String> result = new ArrayList<>();

        if(cursor.moveToFirst())
        {
            do{

                result.add(cursor.getString(cursor.getColumnIndex("word")));
            }
            while (cursor.moveToNext());
        }
        return result;
    }

    // function get all definitions by Word
    public List<definition> getDefinitionByWord(String wordParameter)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        // Make sure this names are your column names in database
        String [] sqlSelect = {"ID", "word", "meaning"};
        String tableName = TABLE_NAME;
        qb.setTables(tableName);

        //This query will select * from database where Word is Like "%pattern%"
        Cursor cursor = qb.query(db,sqlSelect,"word LIKE ?",new String[]{"%"+wordParameter+"%"},null,null,null);
        List<definition> result = new ArrayList<>();

        if(cursor.moveToFirst())
        {
            do{
                definition  def = new definition();
                def.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                def.setWord(cursor.getString(cursor.getColumnIndex("word")));
                def.setMeaning(cursor.getString(cursor.getColumnIndex("meaning")));
                result.add(def);
            }
            while (cursor.moveToNext());
        }
        return result;

    }
}
