package com.elsys.refpro.refpromobile.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.elsys.refpro.refpromobile.database.DataBase;
import com.elsys.refpro.refpromobile.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Create extends Fragment implements View.OnClickListener{

    View createView;
    boolean canCreate = true;
    EditText competition, venue, players, subs, lenght, home, away, homeabbr, awayabbr, time, date;
    Button create;
    DataBase db;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        createView = inflater.inflate(R.layout.create_fragment, container, false);

        // region INITIALIZE
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

        //endregiond
        db = new DataBase(this.getActivity());

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

            time.setError("Wrong time format (HH:MM)");
            canCreate = false;
        }
        else {

            String check = time.getText().toString();

            char [] elements = check.toCharArray();

            if (Integer.parseInt(String.valueOf(elements[0])) > 2 || (Integer.parseInt(String.valueOf(elements[1])) > 3 && Integer.parseInt(String.valueOf(elements[0])) == 2)
                    || Integer.parseInt(String.valueOf(elements[3])) > 5 || elements.length != 5) {

                time.setError("Wrong time format (HH:MM)");
                canCreate = false;
            }
        }

        if (date.getText().toString().isEmpty() || date.getText().toString().length() != 10)   {

            date.setError("Wrong date format (YYYY-MM-DD)");
            canCreate = false;
        }
        else {

            final String DATE_FORMAT = "yyyy-MM-dd";
            String check = date.getText().toString();

            try {

                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                df.parse(check);

            } catch (ParseException e) {

                date.setError("Wrong date format (YYYY-MM-DD)");
                canCreate = false;
            }
        }

        playersInt = CheckIntParameters(players, 8, 15, "Players");
        subsInt = CheckIntParameters(subs, 3, 12, "Substitutes");
        lenghtInt = CheckIntParameters(lenght, 30, 55, "Half lenght");

        if (!canCreate) {

            canCreate = true;
        }
        else {

            boolean insertData = db.addData(competition.getText().toString(), venue.getText().toString(),
                    home.getText().toString(), away.getText().toString(), homeabbr.getText().toString()
            , awayabbr.getText().toString(), date.getText().toString(), time.getText().toString(),
                    playersInt, subsInt, lenghtInt);

            if (insertData)
                Toast.makeText(getActivity(), R.string.create_success,
                        Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getActivity(), R.string.error,
                        Toast.LENGTH_LONG).show();


            Menu menu = new Menu();
            android.app.FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, menu).commit();
        }
    }


    public void CheckStringParameters(EditText field, int length, String text) {

        if (field.getText().toString().length() < 1 || field.getText().toString().length() > length) {

            field.setError(getResources().getString(R.string.create_error));
            canCreate = false;
        }
    }

    public int CheckIntParameters(EditText field, int min, int max, String text) {

        String toInt = field.getText().toString().trim();
        int value = 0;

        if (toInt.length() != 0) {

            value = Integer.parseInt(toInt);

            if (value < min || value > max) {

                field.setError(getResources().getString(R.string.create_error));
                canCreate = false;
            }
        }
        else {
            field.setError(getResources().getString(R.string.create_error));
            canCreate = false;
        }

        return value;
    }
}
