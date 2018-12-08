package com.example.mahdi.testapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        EditWords();
    }
    public void EditWords(){
        Button btnSave = (Button) findViewById(R.id.butsave);
        Button btnDelete = (Button) findViewById(R.id.butdelete);
        final EditText editable_item = (EditText) findViewById(R.id.etsave);
        final DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);


        Intent intent = getIntent();
        //now get the itemID we passed as an extra
        final int selectedID = intent.getIntExtra("id",-1); //NOTE: -1 is just the default value
        //now get the name we passed as an extra
        final String selectedword = intent.getStringExtra("name");
        //set the text to show the current selected name
        editable_item.setText(selectedword);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_item.getText().toString();
                if(!item.equals("")){
                    mDatabaseHelper.updateWord(item,selectedID,selectedword);
                    Toast.makeText(getApplicationContext(),"Word Updated!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ViewContents.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"You must enter a Word",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedID,selectedword);
                editable_item.setText("");
                Toast.makeText(getApplicationContext(),"Word Deleted",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ViewContents.class);
                startActivity(intent);
            }
        });
    }
}
