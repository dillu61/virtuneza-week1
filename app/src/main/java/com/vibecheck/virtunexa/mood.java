package com.vibecheck.virtunexa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class mood extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mood);

        SharedPreferences sp = getSharedPreferences("mooddata", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        //creating toolbar
        Toolbar tool=findViewById(R.id.tool);
        setSupportActionBar(tool);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //creating recycle view
        RecyclerView emotion=findViewById(R.id.recycleemotion);
        emotion.setLayoutManager(new GridLayoutManager(this,2));
        /*adding data*/
        Vector<emotionmix> data=new Vector<>();
        data.add(new emotionmix("Happy",R.drawable.smile));
        data.add(new emotionmix("Sad",R.drawable.sad));
        data.add(new emotionmix("Angry",R.drawable.angry));
        data.add(new emotionmix("Peace",R.drawable.peace));
        data.add(new emotionmix("Bored",R.drawable.bored));
        data.add(new emotionmix("shock",R.drawable.shocked));
        data.add(new emotionmix("Surprise",R.drawable.surprised));
          Adapteremotions objemotions=new Adapteremotions(getApplicationContext(),data);
          emotion.setAdapter(objemotions);
          //saving emotion by clicking button
        Button save=findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=objemotions.getposition();
                if(position==-1)
                    Toast.makeText(getApplicationContext(),"select a mood",Toast.LENGTH_SHORT).show();
                else {

                    //adding data to storage

                    int i = sp.getInt("temp", 0);


                    editor.putInt("image" + i, data.get(position).img);
                    editor.putString("name" + i, data.get(position).emoji);
                    //for time
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH) + 1; // Month is 0-based, so we add 1
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    editor.putString("date"+i,day+"/"+month+"/"+year);
                    Date currentTime = new Date();
                    String formattedTime = timeFormat.format(currentTime);
                    editor.putString("time" + i, formattedTime);
                    editor.putInt("temp", i + 1);
                    editor.apply();
                    //Log.e("value at mood","temp"+sp.getInt("temp",500));
                    Toast.makeText(getApplicationContext(), sp.getString("name"+i,null)+" mood saved", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==R.id.history)
        {
            SharedPreferences sp = getSharedPreferences("mooddata", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();

            if(sp.getInt("temp",-1)<=0) {
                Toast.makeText(this, "there is no history", Toast.LENGTH_SHORT).show();
                editor.clear();
                editor.apply();
            }
            else {
                Intent historyintent = new Intent(getApplicationContext(), historylayout.class);
                startActivity(historyintent);
            }

        }
        else
        {
            if (item.getItemId() == android.R.id.home) {
                // When the back button is clicked, finish the activity and exit
                finish(); // This will close the current activity
                return true;
            }
        }

        return super.onOptionsItemSelected(item);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items from the XML file
        getMenuInflater().inflate(R.menu.menumood,menu);
        return true;
    }
}