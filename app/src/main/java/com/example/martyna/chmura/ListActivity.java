package com.example.martyna.chmura;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference databaseRef;
    private ArrayList<String> groceryList = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListView ListViewGroceries = (ListView) findViewById(R.id.groceryList);

        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReference("groceries");

        // Set the ArrayAdapter to the ListView
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, groceryList);
        ListViewGroceries.setAdapter(arrayAdapter);

        // Attach a ChildEventListener to the teacher database, so we can retrieve the teacher entries
        databaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                // Get the value from the DataSnapshot and add it to the teachers' list
                AddItemActivity.Grocery singleGrocery = dataSnapshot.getValue(AddItemActivity.Grocery.class);
                String singleGroceryString = String.valueOf(singleGrocery);
                groceryList.add(singleGroceryString);

                // Notify the ArrayAdapter that there was a change
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ListViewGroceries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, AddItemActivity.class);

                intent.putExtra("id", id);

                // Launch the {@link AddItemActivity} to display the data for the current item.
                startActivity(intent);
            }
        });
    }

    public void goToAddNew(View v){
        Intent intent_add = new Intent(this, AddItemActivity.class);
        startActivity(intent_add);
    }



}
