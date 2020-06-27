package com.example.kirovtheaters;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kirovtheaters.model.Theater;

public class MainActivity extends AppCompatActivity {

    private String[] theatersNames;
    private String[] theatersVk;
    private String[] theatersSites;
    private String[] theatersPhones;
    private String[] theatersAddresses;
    private String[] theatersTroupes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void CardClick(View view) {

        theatersNames = getResources().getStringArray(R.array.theaters_names);
        theatersVk = getResources().getStringArray(R.array.theaters_vk);
        theatersSites = getResources().getStringArray(R.array.theaters_sites);
        theatersPhones = getResources().getStringArray(R.array.theaters_phones);
        theatersAddresses = getResources().getStringArray(R.array.theaters_addresses);
        theatersTroupes = getResources().getStringArray(R.array.theaters_troupes);

        Theater theater = new Theater();

        int curId = view.getId();
        int number = curId == R.id.spassky_theater ? 0 : curId == R.id.drama_theater ? 1 : 2;

        int imageId = number == 0 ? R.drawable.spassky_theatre : number == 1 ? R.drawable.drama_theatre : R.drawable.dolls_theatre;

        theater.setImageId(imageId);
        theater.setName(theatersNames[number]);
        theater.setAddress(theatersAddresses[number]);
        theater.setSite(theatersSites[number]);
        theater.setTroupe(theatersTroupes[number]);
        theater.setVk(theatersVk[number]);
        theater.setPhone(theatersPhones[number]);

        Intent intent = new Intent(this, TheaterActivity.class);
        intent.putExtra("theater", theater);
        startActivity(intent);
    }
}
