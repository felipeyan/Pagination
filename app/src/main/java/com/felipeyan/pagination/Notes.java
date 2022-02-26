package com.felipeyan.pagination;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class Notes {
    Context context;

    public Notes(Context context) {
        this.context = context;
    }

    public View.OnClickListener noteClick(int id) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Pressed item: " + id, Toast.LENGTH_SHORT).show();
            }
        };
    }
}
