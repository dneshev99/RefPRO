package com.example.refpro.refpromobile;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

public class Create extends Fragment implements View.OnClickListener{

    View createView;
    int match_id;
    String key;
    //final Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

    boolean canCreate = true;
    EditText competition, venue, players, subs, lenght, home, away, homeabbr, awayabbr, time, date;
    Button create;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        createView = inflater.inflate(R.layout.create, container, false);
        SharedPreferences mPrefs = this.getActivity().getPreferences(MODE_PRIVATE);
        match_id = mPrefs.getInt("ID", 0);
        key = Integer.toString(match_id) + "_match";

        competition = (EditText) createView.findViewById(R.id.competition);
        venue = (EditText) createView.findViewById(R.id.venue);
        players = (EditText) createView.findViewById(R.id.players);
        subs = (EditText) createView.findViewById(R.id.subs);
        lenght = (EditText) createView.findViewById(R.id.lenght);
        home = (EditText) createView.findViewById(R.id.home);
        away = (EditText) createView.findViewById(R.id.away);
        homeabbr = (EditText) createView.findViewById(R.id.homeabbr);
        awayabbr = (EditText) createView.findViewById(R.id.awayabbr);
        time = (EditText) createView.findViewById(R.id.time);
        date = (EditText) createView.findViewById(R.id.date);

        create = (Button) createView.findViewById(R.id.createMatch);

        create.setOnClickListener(this);

        return createView;
    }

    @Override
    public void onClick(View v) {

        int subsInt = 0, playersInt = 0, lenghtInt = 0;

        if (competition.getText().toString().length() < 1 || competition.getText().toString().length() > 100) {

            competition.setError("Competition name must be between 1 and 100 characters");
            canCreate = false;
        }

// date napravi

        if (venue.getText().toString().length() < 1 || venue.getText().toString().length() > 50) {

            venue.setError("Venue must be between 1 and 50 characters");
            canCreate = false;
        }

        if (home.getText().toString().length() < 1 || home.getText().toString().length() > 30) {

            home.setError("Home team name must be between 1 and 30 characters");
            canCreate = false;
        }

        if (away.getText().toString().length() < 1 || away.getText().toString().length() > 30) {

            away.setError("Away team name must be between 1 and 30 characters");
            canCreate = false;
        }

        if (homeabbr.getText().toString().length() < 1 || homeabbr.getText().toString().length() > 5) {

            homeabbr.setError("Home team abbreviation must be between 1 and 5 characters");
            canCreate = false;
        }

        if (awayabbr.getText().toString().length() < 1 || awayabbr.getText().toString().length() > 5) {

            awayabbr.setError("Away team abbreviation must be between 1 and 30 characters");
            canCreate = false;
        }

        if (time.getText().toString().isEmpty()) {

            canCreate = false;
        }

        if (date.getText().toString().isEmpty()) {

            canCreate = false;
        }


        String toInt = players.getText().toString().trim();

        if (toInt.length() != 0) {

            playersInt = Integer.parseInt(toInt);

            if (playersInt < 8 || playersInt > 15) {

                players.setError("Players must be between 8 and 15");
                canCreate = false;
            }
        }
        else
            players.setError("Players must be between 8 and 15");

        toInt = subs.getText().toString().trim();

        if (toInt.length() != 0) {

            subsInt = Integer.parseInt(toInt);

            if (subsInt < 3 || subsInt > 12) {

                subs.setError("Substitutes must be between 3 and 12");
                canCreate = false;
            }
        }
        else
            subs.setError("Substitutes must be between 3 and 12");

        toInt = lenght.getText().toString().trim();

        if (toInt.length() != 0) {

            lenghtInt = Integer.parseInt(toInt);

            if (lenghtInt < 30 || lenghtInt > 55) {

                lenght.setError("Half lenght must be between 30 and 55 minutes");
                canCreate = false;
            }
        }
        else
            lenght.setError("Half lenght must be between 30 and 55 minutes");

        if (!canCreate) {

            canCreate = true;
        }
        else {

            MatchInfo match = new MatchInfo(
                    competition.getText().toString(),
                    venue.getText().toString(),
                    home.getText().toString(),
                    away.getText().toString(),
                    homeabbr.getText().toString(),
                    awayabbr.getText().toString(),
                    time.getText().toString(),
                    playersInt,
                    subsInt,
                    lenghtInt,
                    date.getText().toString() );

            SharedPreferences mPrefs = this.getActivity().getPreferences(MODE_PRIVATE);

            SharedPreferences.Editor prefsEditor = mPrefs.edit();
            Gson gson = new Gson();
            String json = gson.toJson(match);
            prefsEditor.putString(key, json);
            prefsEditor.commit();

            match_id++;
            //SharedPreferences id = this.getActivity().getSharedPreferences(MODE_PRIVATE);
            //SharedPreferences.Editor editor = id.edit();
            prefsEditor = mPrefs.edit();
            prefsEditor.putInt("ID", match_id);
            prefsEditor.commit();

            Toast.makeText(getActivity(), "New match created successfully!!!",
                    Toast.LENGTH_LONG).show();

            com.example.refpro.refpromobile.Menu menu = new com.example.refpro.refpromobile.Menu();
            android.app.FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, menu).commit();
        }
    }
}
