package com.example.mahdi.testapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditMeaning extends AppCompatActivity {

        private static final String TAG = "EditDataActivity";

        private Button btnSave,btnDelete;
        private EditText editable_item;

        DatabaseHelper mDatabaseHelper;

        private String selectedmeaning;
        private int selectedID;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit_meaning);
            btnSave = (Button) findViewById(R.id.butsave);
            btnDelete = (Button) findViewById(R.id.butdelete);
            editable_item = (EditText) findViewById(R.id.etsave);
            mDatabaseHelper = new DatabaseHelper(this);


            Intent intent = getIntent();

            //now get the itemID we passed as an extra
            selectedID = intent.getIntExtra("id",-1);

            //now get the name we passed as an extra
            selectedmeaning = intent.getStringExtra("Meaning");



            //set the text to show the current selected name
            editable_item.setText(selectedmeaning);

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String item = editable_item.getText().toString();
                    if(!item.equals("")){
                        mDatabaseHelper.updateMeaing(item,selectedID,selectedmeaning);
                        Toast.makeText(getApplicationContext(),"Meaning Updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ViewMeanings.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),"You must enter a Word Meaning", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDatabaseHelper.deleteMeaning(selectedID,selectedmeaning);
                    editable_item.setText("");
                    Toast.makeText(getApplicationContext(),"Deleted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ViewMeanings.class);
                    startActivity(intent);
                }
            });

        }

    }



