package com.vibecheck.virtunexa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class historylayout extends AppCompatActivity {
    RecyclerView rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_historylayout);
        Vector<historygroup> data=new Vector<>();
        Toolbar tool=findViewById(R.id.historytool);
        setSupportActionBar(tool);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView rec=findViewById(R.id.historyrecycle);
        rec.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences sp=getSharedPreferences("mooddata",MODE_PRIVATE);
        int[] count={0,0,0,0,0,0,0};
        int i=sp.getInt("temp",0)-1;
        int high=0;
       // Log.e("from history","i"+i);
        int index=-1;
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if(!sp.getString("date"+i,null).matches(day+".*"))
        {
            SharedPreferences.Editor editor=sp.edit();
            editor.clear();
            editor.apply();
        }
        while(i>=0)
        {
            if(sp.getString("name"+i,null)=="Happy")
                count[0]++;
            else if(sp.getString("name"+i,null)=="Sad")
                count[1]++;
            else if(sp.getString("name"+i,null)=="Angry")
                    count[2]++;
            else if(sp.getString("name"+i,null)=="Peace")
                count[3]++;
            else if(sp.getString("name"+i,null)=="Bored")
                count[4]++;
            else if(sp.getString("name"+i,null)=="shock")
                count[5]++;
            else
                count[6]++;

            for(int j=0;j<count.length;j++)
            {
                if(count[j]>high) {
                    high = count[j];
                    index=j;
                }
            }
            data.add(new historygroup(sp.getInt("image"+i,0),sp.getString("name"+i,null),sp.getString("time"+i,"null"),sp.getString("date"+i,null)));
            i--;
        }
        ImageView overalimg=findViewById(R.id.overallimg);
        TextView overaltxt=findViewById(R.id.overaltxt);
        switch (index)
        {
            case 0:
                overalimg.setImageResource(R.drawable.smile);
                overaltxt.setText("you are in happy mood for most of the time.");
                break;
            case 1:
                overalimg.setImageResource(R.drawable.sad);
                overaltxt.setText("you are in sad mood for most of the time.");
                break;
            case 2:
                overalimg.setImageResource(R.drawable.angry);
                overaltxt.setText("you are in angry mood for most of the time.");
                break;
            case 3:
                overalimg.setImageResource(R.drawable.peace);
                overaltxt.setText("you are in peace mood for most of the time.");
                break;
            case 4:
                overalimg.setImageResource(R.drawable.bored);
                overaltxt.setText("you are in dull mood for most of the time.");
                break;
            case 5:
                overalimg.setImageResource(R.drawable.shocked);
                overaltxt.setText("you have experienced more shocking things.");
                break;
            case 6:
                overalimg.setImageResource(R.drawable.surprised);
                overaltxt.setText("you have surpised most of the time.");
                break;
            default:
                overalimg.setVisibility(View.GONE);
                overaltxt.setText("No history");

        }

        historyadapter adap=new historyadapter(data);
        rec.setAdapter(adap);



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home) {
            onBackPressed();

        }
        if(item.getItemId()==R.id.clear)
        {
            SharedPreferences sp=getSharedPreferences("mooddata",MODE_PRIVATE);
            SharedPreferences.Editor editor=sp.edit();
            editor.clear();
            editor.apply();
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items from the XML file
        getMenuInflater().inflate(R.menu.menuclear,menu);
        return true;
    }

}