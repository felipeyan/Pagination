package com.felipeyan.pagination;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Data {
    public ArrayList<ArrayList<String>> dataArrays = new ArrayList<>();
    public int dataCount, pagesCount, valuesLeft;
    Context context;

    public Data(Context context) {
        this.context = context;
    }

    public ArrayList<ArrayList<String>> getDataArrays() {
        return dataArrays;
    }

    public void setDataArrays(ArrayList<ArrayList<String>> dataArrays) {
        this.dataArrays = dataArrays;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public int getValuesLeft() {
        return valuesLeft;
    }

    public void setValuesLeft(int valuesLeft) {
        this.valuesLeft = valuesLeft;
    }

    public ArrayList<ArrayList<String>> getJSON() {
        ArrayList<String> ids = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();

        try {
            JSONObject object = new JSONObject(getDataFromAsset());
            JSONArray array = object.getJSONArray("users");

            for (int i = 0; i < array.length(); i++) {
                JSONObject userData = array.getJSONObject(i);
                ids.add(userData.getString("id"));
                names.add(userData.getString("name"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        dataArrays.add(ids);
        dataArrays.add(names);
        return dataArrays;
    }

    private String getDataFromAsset() {
        String json = null;

        try {
            InputStream inputStream = context.getAssets().open("data.json");
            int file = inputStream.available();
            byte[] bufferData = new byte[file];
            inputStream.read(bufferData);
            inputStream.close();
            json = new String(bufferData, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }
}
