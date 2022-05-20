package com.example.esp8266;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.esp8266.ui.main.SectionsPagerAdapter;
import com.example.esp8266.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
//        FirebaseDatabase database=FirebaseDatabase.getInstance();
//        DatabaseReference databaseReference= database.getReference("test/json/DateTime");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String value = snapshot.getValue().toString();
//                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//                    NotificationChannel channel=new NotificationChannel("count","channel",NotificationManager.IMPORTANCE_DEFAULT);
//                    channel.setDescription("This is channel");
//                    NotificationManager manager=  getSystemService(NotificationManager.class);
//                    manager.createNotificationChannel(channel);
//                }
//                NotificationCompat.Builder builder= new NotificationCompat.Builder(MainActivity.this, "count")
//                        .setContentTitle("Warning!")
//                        .setAutoCancel(true)
//                        .setContentText("Phát hiện chuyển động lúc " + value);
//                NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(MainActivity.this);
//                notificationManagerCompat.notify(getID(), builder.build());
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }
    private int getID(){
        return (int) new Date().getTime();
    }
}