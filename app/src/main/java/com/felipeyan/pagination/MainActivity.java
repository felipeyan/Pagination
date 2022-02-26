package com.felipeyan.pagination;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Data data;
    Pages pages;
    RecyclerView recyclerNotes, recyclerPages;

    ArrayList<ArrayList<String>> dataArrays = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerNotes = findViewById(R.id.recycler_notes);
        recyclerPages= findViewById(R.id.recycler_pages);

        data = new Data(this);
        pages = new Pages(this);

        dataArrays = data.getJSON();
        setDataProperties();
        displayNotes(0, pages.LIMIT_PER_PAGE);
    }

    public void setDataProperties() {
        data.setDataCount(dataArrays.get(0).size());
        data.setPagesCount((int) Math.floor(data.getDataCount() / pages.LIMIT_PER_PAGE));
        data.setValuesLeft(data.getDataCount() % pages.LIMIT_PER_PAGE);
    }

    public void displayNotes(int start, int end) {
        recyclerNotes.setAdapter(new Adapter(this, "notes", arraySubList(start, end)));
        if (dataArrays.get(0).size() > pages.LIMIT_PER_PAGE) pages.displayPages();
    }

    public ArrayList<ArrayList<String>> arraySubList(int start, int end) {
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
        arrayLists.add(new ArrayList<>(dataArrays.get(0).subList(start, end)));
        arrayLists.add(new ArrayList<>(dataArrays.get(1).subList(start, end)));
        return arrayLists;
    }
}