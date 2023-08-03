package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private TextView humidityTextView, temperatureTextView, moistureTextView, pumpStatusTextView, humStatusTextView, humtStatusTextView, tempStatusTextView, statStatusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        humidityTextView = findViewById(R.id.humidity);
        temperatureTextView = findViewById(R.id.temperature);
        moistureTextView = findViewById(R.id.soilmouisture);
        pumpStatusTextView = findViewById(R.id.pumpstatus);
        humStatusTextView = findViewById(R.id.kethum);
        tempStatusTextView = findViewById(R.id.ketemp);
        humtStatusTextView = findViewById(R.id.ketsoil);
        statStatusTextView = findViewById(R.id.status);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("siram");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String tempdata = dataSnapshot.child("temp").getValue().toString();
                String humdata = dataSnapshot.child("hum").getValue().toString();
                String soildata = dataSnapshot.child("soil").getValue().toString();
                String ketemp = dataSnapshot.child("ketemp").getValue().toString();
                String kethum = dataSnapshot.child("kethum").getValue().toString();
                String ketsoil = dataSnapshot.child("ketsoil").getValue().toString();
                String status = dataSnapshot.child("status").getValue().toString();
                String pompa = dataSnapshot.child("pompa").getValue().toString();
                temperatureTextView.setText(tempdata);
                humidityTextView.setText(humdata);
                moistureTextView.setText(soildata);
                humStatusTextView.setText(kethum);
                humtStatusTextView.setText(ketsoil);
                tempStatusTextView.setText(ketemp);
                statStatusTextView.setText(status);
                pumpStatusTextView.setText(pompa);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
}
