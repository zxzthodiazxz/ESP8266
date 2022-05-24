package com.example.esp8266;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

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
    public  String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        FirebaseDatabase database=FirebaseDatabase.getInstance("https://esp8266-67ac6-default-rtdb.firebaseio.com/");
        DatabaseReference databaseReference= database.getReference("test/DateTime");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                value=snapshot.getValue().toString();
                createNotificationChannel();
                CreateNotify createNotify=new CreateNotify(value);
                createNotify.start();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel";
            String description = "This is channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    private int getID() {
        return (int) new Date().getTime();
    }
    public class CreateNotify extends Thread{
        String dayTime = null;
        CreateNotify(String a){
            dayTime=a;
        }
        @Override
        public void run() {
            Notification notification=new NotificationCompat.Builder(MainActivity.this, "CHANNEL_ID")
                    .setSmallIcon(R.drawable.ic_baseline_warning_24)
                    .setContentTitle("Warning!")
                    .setContentText("Phát hiện chuyển động vào lúc " + dayTime)
                    .setAutoCancel(true)
                    .build();
            NotificationManagerCompat notificationManager= NotificationManagerCompat.from(MainActivity.this);
            notificationManager.notify(1,notification);
        }
    }
}