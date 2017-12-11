package com.example.martyna.chmura;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

public class AddItemActivity extends AppCompatActivity {

    EditText addNameEditText;
    EditText addPriceEditText;
    EditText addQuantityEditText;

    TextView test;


    private int groceryID;
    private String name;
    private String price;
    private String quantity;
    private FirebaseDatabase database;
    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        addNameEditText = (EditText) findViewById(R.id.addNewName);
        addPriceEditText = (EditText) findViewById(R.id.addNewPrice);
        addQuantityEditText = (EditText) findViewById(R.id.addNewQuantity);

        test = (TextView) findViewById(R.id.testdb);

        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReference("groceries");

        ValueEventListener groceryListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Grocery grocery = dataSnapshot.getValue(Grocery.class);
                test.setText((CharSequence) grocery);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };

        databaseRef.addValueEventListener(groceryListener);

    }

    public void saveItemRow(View v) {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        int groceryID = ItemID.getID();
        String groceryName = addNameEditText.getText().toString().trim();
        String groceryPrice = addPriceEditText.getText().toString().trim();
        String groceryQuantity = addQuantityEditText.getText().toString().trim();


        // Write a message to the database
        writeNewGrocery(groceryID, groceryName, groceryPrice, groceryQuantity);

    }

    @IgnoreExtraProperties
    public static class Grocery {

        public String name;
        public String price;
        public String quantity;

        public Grocery() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public Grocery(String name, String price, String quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

    }



    private void writeNewGrocery(int groceryID, String name, String price, String quantity) {
        Grocery grocery = new Grocery(name, price, quantity);
        databaseRef.push().setValue(grocery);



    }






}