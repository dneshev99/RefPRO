package com.example.refpro.refpromobile;

import android.app.DownloadManager;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by user on 19.11.2017 г..
 */

public class Info extends Fragment {

    View createView;
    TextView competition, date, time, teams;
    int match_id;
    String key;
    boolean canCreate = true;
    MatchInfo match;
    Button create;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        createView = inflater.inflate(R.layout.match_info, container, false);


        //region INITIALIZE
        LinearLayout layout = (LinearLayout) createView.findViewById(R.id.info_layout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //Every Title will have Top Margin of 120dp
        params.setMargins(0, 120, 0, 0);

        //GET current match ID and GET the information about match with it's unique key
        SharedPreferences mPrefs = this.getActivity().getPreferences(MODE_PRIVATE);
        match_id = mPrefs.getInt("match_id", 0);
        key = Integer.toString(match_id) + "_match";

        Gson gson = new Gson();
        String json;

        json = mPrefs.getString(key, "");
        match = gson.fromJson(json, MatchInfo.class);

        //GET information about current match and SET as Text
        competition = (TextView) createView.findViewById(R.id.info_name);
        date = (TextView) createView.findViewById(R.id.info_date);
        time = (TextView) createView.findViewById(R.id.info_time);
        teams = (TextView) createView.findViewById(R.id.info_teams);

        competition.setText(match.getCompetition());
        date.setText(match.getDate());
        time.setText(match.getTime());
        teams.setText(match.getTeams());
        //endregion

        //Create form for every player
        CreateForms(match.getPlayers(), layout, "Home player", 100);

        SetTitle(layout, params, "Home substitutions");
        CreateForms(match.getSubs(), layout, "Home substitute", 200);

        SetTitle(layout, params, "Away players");
        CreateForms(match.getPlayers(), layout, "Away player", 300);

        SetTitle(layout, params, "Away substitutes");
        CreateForms(match.getSubs(), layout, "Away player", 400);

        /////

        Button create = (Button) createView.findViewById(R.id.send);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int counter = 0; counter < match.getPlayers(); counter++) {

                    EditText check;
                    check = (EditText) createView.findViewById(counter + 100);

                    if (check.getText().toString().length() < 1) {

                        canCreate = false;
                        check.setError("Empty field!!!");
                    }
                    else {

                        //match.add(check.getText().toString());
                    }
                }
            }
        });

        return createView;
    }


    public void SetTitle(LinearLayout layout, LinearLayout.LayoutParams params, String text) {

        TextView field = new TextView(this.getActivity());
        field.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        field.setText(text);
        field.setTextColor(Color.parseColor("#000000"));
        field.setTextSize(24);
        field.setTypeface(null, Typeface.BOLD);
        layout.addView(field, params);
    }

    public void CreateForms(int range, LinearLayout layout, String text, int IDIncrease) {

        for (int counter = 0; counter < range; counter++) {

            EditText form = new EditText(this.getActivity());
            form.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            form.setHint(text + " " + (counter + 1));
            form.setId(counter + IDIncrease);
            form.setGravity(Gravity.CENTER);

            layout.addView(form);
        }
    }

    /*@Override
    public void onClick(View v) {

        for (int counter = 0; counter < match.getPlayers(); counter++) {

            EditText check;
            check = (EditText) createView.findViewById(counter+100);

            if (check.getText().toString().length() < 1) {

                canCreate = false;
                check.setError("Empty field!!!");
            }
            else {

                match.add(check.getText().toString());
            }
        }

       /* for (int counter = 0; counter < match.getSubs(); counter++) {

            EditText check;
            check = (EditText) createView.findViewById(counter+200);

            if (check.getText() == null) {

                canCreate = false;
                check.setError("Empty field!!!");
            }
            else {

                match.home_subs.add(check.getText().toString());
            }
        }

        for (int counter = 0; counter < match.getPlayers(); counter++) {

            EditText check;
            check = (EditText) createView.findViewById(counter+300);

            if (check.getText() == null) {

                canCreate = false;
                check.setError("Empty field!!!");
            }
            else {

                match.away_players.add(check.getText().toString());
            }
        }

        for (int counter = 0; counter < match.getSubs(); counter++) {

            EditText check;
            check = (EditText) createView.findViewById(counter+400);

            if (check.getText() == null) {

                canCreate = false;
                check.setError("Empty field!!!");
            }
            else {

                match.away_subs.add(check.getText().toString());
            }
        }*/


        /*if (!canCreate) {

            canCreate = true;
            match.home_players.clear();
            match.away_players.clear();
            match.home_subs.clear();
            match.away_subs.clear();
        }
        else {

            Toast.makeText(getActivity(), match.home_players.get(3),
                    Toast.LENGTH_LONG).show();
        }
    }*/
}
