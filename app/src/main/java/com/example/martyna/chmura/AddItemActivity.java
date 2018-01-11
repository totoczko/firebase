package com.example.martyna.chmura;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItemActivity extends parentActivity {

    EditText addNameEditText;
    EditText addPriceEditText;
    EditText addQuantityEditText;

    TextView test;

    private String groceryID;
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

        Intent i = getIntent();
        Grocery groceryObject = i.getParcelableExtra("groceryObject");
        // getting attached intent data
        if(groceryObject != null){
            String intentID = groceryObject.getId();
            if(intentID != null){
                addNameEditText.setText(groceryObject.name);
                addPriceEditText.setText(groceryObject.price);
                addQuantityEditText.setText(groceryObject.quantity);
            }
        }

        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReference("groceries");

    }

    public void saveItemRow(View v) {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
//        String groceryID = String.valueOf(ItemID.getID());
        String groceryName = addNameEditText.getText().toString().trim();
        String groceryPrice = addPriceEditText.getText().toString().trim();
        String groceryQuantity = addQuantityEditText.getText().toString().trim();

        Intent i = getIntent();
        Grocery groceryObject = i.getParcelableExtra("groceryObject");
        // getting attached intent data
        if(groceryObject != null){
            String intentID = groceryObject.getId();
            if(intentID != null){
                //updating an item
                groceryID = intentID;
                updateGrocery(groceryName, groceryPrice, groceryQuantity);
            }
        }else{
            // creating new item
            writeNewGrocery(groceryName, groceryPrice, groceryQuantity);
        }
    }

    private void writeNewGrocery(String name, String price, String quantity) {
        //new product ID from firebase database
        String groceryID = databaseRef.push().getKey();

        //create and save new product
        Grocery grocery = new Grocery(groceryID, name, price, quantity);
        databaseRef.child(groceryID).setValue(grocery);
    }


    private void updateGrocery(String name, String price, String quantity){
        databaseRef.child(groceryID).child("name").setValue(name);
        databaseRef.child(groceryID).child("price").setValue(price);
        databaseRef.child(groceryID).child("quantity").setValue(quantity);
    }

    public void deleteGrocery(View v){
        Intent i = getIntent();
        Grocery groceryObject = i.getParcelableExtra("groceryObject");
        // getting attached intent data
        if(groceryObject != null){
            String intentID = groceryObject.getId();
            if(intentID != null){
                //updating an item
                groceryID = intentID;
                databaseRef.child(groceryID).removeValue();
            }
        }
    }

}