package com.example.kirovtheaters;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kirovtheaters.adapter.ActorAdapter;
import com.example.kirovtheaters.model.Actor;
import com.example.kirovtheaters.model.Theater;
import com.example.kirovtheaters.service.JSoupActorService;

import java.io.IOException;
import java.util.ArrayList;

public class ActorsActivity extends AppCompatActivity {

    class ActorsTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                JSoupActorService actorService = JSoupActorService.getInstance(theater);
                String[] theatersNames = getResources().getStringArray(R.array.theaters_names);

                actors = theater.getName().equals(theatersNames[0]) ?
                        actorService.getSpasskyTheaterActorsList() :
                        theater.getName().equals(theatersNames[1]) ?
                                actorService.getDramaTheaterActorsList() :
                                actorService.getDollTheaterActorsList();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            ProgressBar progressBar = findViewById(R.id.progress);
            progressBar.setVisibility(View.INVISIBLE);
            ActorAdapter actorAdapter = new ActorAdapter(actors);
            actorRecyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));
            actorRecyclerView.setAdapter(actorAdapter);
        }
    }

    private TextView toolbarTextView;
    private RecyclerView actorRecyclerView;
    private ArrayList<Actor> actors;
    private Theater theater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actors);

        toolbarTextView = findViewById(R.id.theater_info);
        actorRecyclerView = findViewById(R.id.actors_recycle_view);

        Bundle intent = getIntent().getExtras();
        theater = (Theater)(intent != null ? intent.getSerializable("theater") : null);
        toolbarTextView.setText(theater.getName());
        new ActorsTask().execute();
    }
}
