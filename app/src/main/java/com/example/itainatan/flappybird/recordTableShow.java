package com.example.itainatan.flappybird;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class recordTableShow extends Activity {

    private TableLayout recoredTable;
    private TextView nameColumn,scoreColumn,numColumn;
    private TableRow row;
    private ArrayList<listOfRecord> myList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_table_show);

        recoredTable = findViewById(R.id.recoredTableID);
        recoredTable.setColumnStretchable(0,true);
        recoredTable.setGravity(Gravity.CENTER);

        myList = new ArrayList<>();
        row = new TableRow(this);

        nameColumn = new TextView(this);
        scoreColumn = new TextView(this);
        numColumn = new TextView(this);

        nameColumn.setTextSize(17);
        nameColumn.setGravity(Gravity.CENTER);
        scoreColumn.setTextSize(17);
        scoreColumn.setGravity(Gravity.CENTER);
        numColumn.setTextSize(17);
        numColumn.setGravity(Gravity.CENTER);

        nameColumn.setText("Name");
        nameColumn.setTextColor(Color.RED);
        scoreColumn.setText("Score");
        scoreColumn.setTextColor(Color.RED);
        numColumn.setText("Position");
        numColumn.setTextColor(Color.RED);

        row.addView(nameColumn);
        row.addView(scoreColumn);
        row.addView(numColumn);
        recoredTable.addView(row,0);


        SharedPreferences prefs = getSharedPreferences(gameOver.MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("JSON", "");
        Gson gson = new Gson();

        if(restoredText != "") {

            listOfRecord[] list = gson.fromJson(restoredText, listOfRecord[].class);
            for (int k = 0; k < list.length; k++) {
                myList.add(list[k]);
            }
            Collections.sort(myList, new Sortbyroll());
            for(int i=0;i<myList.size();i++){

                row = new TableRow(this);
                nameColumn = new TextView(this);
                scoreColumn = new TextView(this);
                numColumn = new TextView(this);

                nameColumn.setTextSize(17);
                nameColumn.setGravity(Gravity.CENTER);

                scoreColumn.setTextSize(17);
                scoreColumn.setGravity(Gravity.CENTER);

                numColumn.setTextSize(17);
                numColumn.setGravity(Gravity.CENTER);

                nameColumn.setText(myList.get(i).name);
                scoreColumn.setText(String.valueOf(myList.get(i).score));
                numColumn.setText(String.valueOf(i+1)+"#");

                row.addView(nameColumn);
                row.addView(scoreColumn);
                row.addView(numColumn);

                recoredTable.addView(row,i+1);
                recoredTable.setGravity(Gravity.CENTER);
            }
        }














    }

}
class Sortbyroll implements Comparator<listOfRecord>
{
    // Used for sorting in ascending order of
    // roll number
    public int compare(listOfRecord a, listOfRecord b)
    {
        return b.score - a.score;
    }
}