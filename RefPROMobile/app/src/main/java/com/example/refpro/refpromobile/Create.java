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
    boolean canCreate = true;
    EditText competition, venue, players, subs, lenght, home, away, homeabbr, awayabbr, time, date;
    Button create;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        createView = inflater.inflate(R.layout.create, container, false);

        // region INITIALIZE
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

        //endregion

        return createView;
    }

    @Override
    public void onClick(View v) {

        int subsInt = 0, playersInt = 0, lenghtInt = 0;

        CheckStringParameters(competition, 100, "Competition");
        CheckStringParameters(venue, 50, "Venue");
        CheckStringParameters(home, 30, "Home team");
        CheckStringParameters(away, 30, "Away team");
        CheckStringParameters(awayabbr, 5, "Away team abbreviation");
        CheckStringParameters(homeabbr, 5, "Home team abbreviation");

        if (time.getText().toString().isEmpty()) {

            canCreate = false;
        }

        if (date.getText().toString().isEmpty()) {

            canCreate = false;
        }

        playersInt = CheckIntParameters(players, 8, 15, "Players");
        subsInt = CheckIntParameters(subs, 3, 12, "Substitutes");
        lenghtInt = CheckIntParameters(lenght, 30, 55, "Half lenght");

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

            //Put match information Object(MatchInfo.java) in SharedPreferences with unique key ({id_num}_match)
            SharedPreferences mPrefs = this.getActivity().getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = mPrefs.edit();
            Gson gson = new Gson();
            String json = gson.toJson(match);
            prefsEditor.putString(key, json);
            prefsEditor.commit();

            //Increase match_id with 1 and save to SharedPreferences
            match_id++;
            prefsEditor = mPrefs.edit();
            prefsEditor.putInt("ID", match_id);
            prefsEditor.commit();

            //Create match and go to MENU Fragment
            Toast.makeText(getActivity(), "New match created successfully!!!",
                    Toast.LENGTH_LONG).show();

            com.example.refpro.refpromobile.Menu menu = new com.example.refpro.refpromobile.Menu();
            android.app.FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, menu).commit();
        }
    }

    public void CheckStringParameters(EditText field, int length, String text) {

        if (field.getText().toString().length() < 1 || field.getText().toString().length() > length) {

            field.setError(text + " name must be between 1 and " + length +  " characters");
            canCreate = false;
        }
    }

    public int CheckIntParameters(EditText field, int min, int max, String text) {

        String toInt = field.getText().toString().trim();
        int value = 0;

        if (toInt.length() != 0) {

            value = Integer.parseInt(toInt);

            if (value < min || value > max) {

                field.setError(text + " must be between " + min + " and " + max);
                canCreate = false;
            }
        }
        else {
            field.setError(text + " must be between " + min + " and " + max);
            canCreate = false;
        }

        return value;
    }
}
