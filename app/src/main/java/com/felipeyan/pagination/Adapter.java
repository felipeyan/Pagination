package com.felipeyan.pagination;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    private static final String NOTES_RV = "notes";
    private static final String PAGES_RV = "pages";

    Pages pages;
    Context context;
    String origin;
    ArrayList<ArrayList<String>> arrays;

    public Adapter(Context context, String origin, ArrayList<ArrayList<String>> arrays) {
        this.context = context;
        this.origin = origin;
        this.arrays = arrays;
        pages = ((MainActivity) context).pages;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = null;

        switch (origin) {
            case NOTES_RV:
                view = inflater.inflate(R.layout.item_note, parent, false);
                break;
            case PAGES_RV:
                view = inflater.inflate(R.layout.item_page, parent, false);
                break;
        }

        return new Holder(Objects.requireNonNull(view));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        switch (origin) {
            case NOTES_RV:
                AppCompatTextView noteText = holder.itemView.findViewById(R.id.note_text);
                LinearLayout noteLayout = holder.itemView.findViewById(R.id.note_layout);
                Notes notes = new Notes(context);

                int noteId = Integer.parseInt(getArrayByName("ids").get(position));

                noteText.setText(getArrayByName("texts").get(position));
                noteLayout.setOnClickListener(notes.noteClick(noteId));
                break;
            case PAGES_RV:
                AppCompatTextView pageText = holder.itemView.findViewById(R.id.page_text);
                LinearLayout pageLayout = holder.itemView.findViewById(R.id.page_layout);

                pageText.setText(getArrayByName("pages").get(position));
                pageLayout.setOnClickListener(pages.pageClick(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (origin.equals(NOTES_RV)) {
            return getArrayByName("ids").size();
        } else {
            return getArrayByName("pages").size();
        }
    }

    public static class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public ArrayList<String> getArrayByName(String name) {
        ArrayList<String> array = new ArrayList<>();

        switch (origin) {
            case NOTES_RV:
                switch (name) {
                    case "ids":
                        array = arrays.get(0);
                        break;
                    case "texts":
                        array = arrays.get(1);
                        break;
                }
                break;
            case PAGES_RV:
                switch (name) {
                    case "pages":
                        array = arrays.get(0);
                        break;
                }
                break;
        }

        return array;
    }
}
