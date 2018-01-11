package com.example.martyna.chmura;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Martyna on 2018-01-11.
 */

public class groceryAdapter extends ArrayAdapter<Grocery> {
    public groceryAdapter(Context context, int resource, List<Grocery> objects) {
        super(context, resource, objects);
    }
}
