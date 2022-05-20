package com.example.esp8266.ui.main;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.esp8266.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class TemperatureFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root=inflater .inflate(R.layout.fragment_temperature,container,false);
        final TextView textView=root.findViewById(R.id.temperatureTextView);
        final ProgressBar progressBar=  root.findViewById(R.id.temperatureProgressBar);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference= database.getReference("test/temperature");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value=snapshot.getValue().toString();
                textView.setText(value+"°C");
                progressBar.setProgress(Math.round(Float.parseFloat(value)));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
        return root;
    }
    private int getID(){
        return (int) new Date().getTime();
    }
}