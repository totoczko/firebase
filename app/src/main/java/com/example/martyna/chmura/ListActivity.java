package com.example.martyna.chmura;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListView groceryList = (ListView) findViewById(R.id.groceryList);

        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReference("groceries");
    }

    public void goToAddNew(View v){
        Intent intent_add = new Intent(this, AddItemActivity.class);
        startActivity(intent_add);
    }



}
