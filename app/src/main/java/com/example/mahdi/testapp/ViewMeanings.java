package com.example.mahdi.testapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewMeanings extends AppCompatActivity {



        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_meanings);
            ViewEditableMeanings();
        }

        private void ViewEditableMeanings() {
            ListView mListView = findViewById(R.id.listview);
            final DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);

            Cursor data = mDatabaseHelper.getData();
            ArrayList<String> listData = new ArrayList<>();
            while(data.moveToNext()){
                listData.add(data.getString(2));
            }
            ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
            mListView.setAdapter(adapter);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    String meaning = adapterView.getItemAtPosition(i).toString();
                    Cursor data = mDatabaseHelper.MeaningID(meaning); //get the id associated with that name
                    int itemID = -1;
                    while(data.moveToNext()){
                        itemID = data.getInt(0);
                    }
                    if(itemID > -1){
                        Intent editScreenIntent = new Intent(ViewMeanings.this, EditMeaning.class);
                        editScreenIntent.putExtra("id",itemID);
                        editScreenIntent.putExtra("Meaning",meaning);
                        startActivity(editScreenIntent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"No ID associated with that name",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
}
