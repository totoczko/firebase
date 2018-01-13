package com.example.martyna.chmura;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListActivity extends parentActivity {

    private FirebaseDatabase database;
    private DatabaseReference databaseRef;
    private ArrayList<Grocery> groceryList = new ArrayList<>();
    private groceryAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        final ListView ListViewGroceries = (ListView) findViewById(R.id.groceryList);

        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReference("groceries");

        // Set the ArrayAdapter to the ListView
        arrayAdapter = new groceryAdapter(this, android.R.layout.simple_list_item_1, groceryList);
        ListViewGroceries.setAdapter(arrayAdapter);

        // Attach a ChildEventListener to the teacher database, so we can retrieve the teacher entries
        databaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                // Get the value from the DataSnapshot and add it to the teachers' list
                Grocery singleGrocery = dataSnapshot.getValue(Grocery.class);
                groceryList.add(singleGrocery);

                // Notify the ArrayAdapter that there was a change
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                for (Grocery removedGrocery : groceryList) {

                if (key.equals(removedGrocery.getId())) {
                    groceryList.remove(removedGrocery);
                    arrayAdapter.notifyDataSetChanged();

                    break;
                }
}
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
                Grocery currentGrocery = groceryList.get(position);
                intent.putExtra("groceryObject", currentGrocery);

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
