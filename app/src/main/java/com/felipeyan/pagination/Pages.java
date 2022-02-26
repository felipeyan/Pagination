package com.felipeyan.pagination;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Pages {
    int LIMIT_PER_PAGE = 20;

    Data data;
    Context context;

    public Pages(Context context) {
        this.context = context;
        this.data = ((MainActivity) context).data;
    }

    public void displayPages() {
        ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
        ArrayList<String> pagesNum = new ArrayList<>();

        int pagesCount = data.getPagesCount();
        int valuesLeft = data.getValuesLeft();

        if (valuesLeft != 0) pagesCount += 1;

        for (int i = 1; i <= pagesCount; i++) pagesNum.add(String.valueOf(i));

        arrayList.add(pagesNum);
        RecyclerView recyclerPages = ((MainActivity) context).findViewById(R.id.recycler_pages);
        recyclerPages.setAdapter(new Adapter(context, "pages", arrayList));
    }

    public View.OnClickListener pageClick(int num) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int start = num * LIMIT_PER_PAGE;
                int end = (num * LIMIT_PER_PAGE) + LIMIT_PER_PAGE;

                Toast.makeText(context, "Selected page: " + (num + 1), Toast.LENGTH_SHORT).show();
                ((MainActivity) context).displayNotes(start, Math.min(end, data.getDataCount()));
            }
        };
    }
}
