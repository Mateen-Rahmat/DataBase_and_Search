package com.example.mahdi.testapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DataManagement extends AppCompatActivity {

    SQLiteDatabase db;
    SQLiteOpenHelper OpenHelper;
    Button Add, EditWords, EditMeaning, Viewall;
    EditText Word, Meaning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_management);
        mainfuntion();
    }

    public void mainfuntion(){

        OpenHelper = new DatabaseHelper(this);
        db = OpenHelper.getWritableDatabase();

        Add = findViewById(R.id.button);
        Word = findViewById(R.id.etword);
        Meaning = findViewById(R.id.etmeaning);
        EditWords = findViewById(R.id.butwords);
        EditMeaning = findViewById(R.id.butmeaning);
        Viewall = findViewById(R.id.butall);

        EditWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewContents.class);
                startActivity(intent);
            }
        });

        EditMeaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewMeanings.class);
                startActivity(intent);

            }
        });
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = Word.getText().toString();
                String meaning = Meaning.getText().toString();


                if (Word.length() != 0){
                    insertdata(word, meaning);
                    Word.setText("");
                    Meaning.setText("");
                    Toast.makeText(getApplicationContext(),"Successful", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"NOT Successful", Toast.LENGTH_SHORT).show();
                }
            }

        });
        Viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewAll.class);
                startActivity(intent);
            }
        });

    }
    public void insertdata(String words, String meaning){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL2,words);
        contentValues.put(DatabaseHelper.COL3,meaning);
        long result = db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);

    }
}
