package com.example.refpro.refpromobile;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by user on 12.11.2017 г..
 */

public class Menu extends Fragment {

    View menuView;
    int match_id;
    MatchInfo match;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        menuView = inflater.inflate(R.layout.menu, container, false);

        //region INITIALIZE
        final SharedPreferences mPrefs = this.getActivity().getPreferences(MODE_PRIVATE);
        LinearLayout layout = (LinearLayout) menuView.findViewById(R.id.menu_layout);

        //Get last saved match's ID
        match_id = mPrefs.getInt("ID", 0);

        Gson gson = new Gson();
        String json, key;

        //endregion

        for (int counter = 0; counter < match_id; counter++) {

            //GET the information about match with it's unique key
            key = Integer.toString(counter) + "_match";
            final String deleteKey = key;

            if (mPrefs.contains(key)) {

                json = mPrefs.getString(key, "");
                match = gson.fromJson(json, MatchInfo.class);

                //CREATE Button for this match
                final Button fixture = new Button(this.getActivity());
                fixture.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                fixture.setId(counter);
                fixture.setText(match.getTeamsAbbr());

                final int id = counter;

                fixture.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //GET the ID of the last clicked match
                        SharedPreferences.Editor prefsEditor = mPrefs.edit();
                        prefsEditor.putInt("match_id", id);
                        prefsEditor.commit();

                        //CREATE AlertDialog with options to OPEN or DELETE current match
                        AlertDialog.Builder alert = new AlertDialog.Builder(menuView.getContext());
                        alert.setMessage("Do you want to Open match information or to Delete current match?").setCancelable(true)
                                .setPositiveButton("Open", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        Info info = new com.example.refpro.refpromobile.Info();
                                        android.app.FragmentManager fragmentManager = getFragmentManager();
                                        fragmentManager.beginTransaction().replace(R.id.content_frame, info).commit();
                                    }
                                })
                                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        SharedPreferences.Editor prefsEditor = mPrefs.edit();
                                        prefsEditor.remove(deleteKey);
                                        prefsEditor.commit();

                                        Toast.makeText(getActivity(), "Match deleted successfully!!!",
                                                Toast.LENGTH_LONG).show();

                                        Menu menu = new com.example.refpro.refpromobile.Menu();
                                        android.app.FragmentManager fragmentManager = getFragmentManager();
                                        fragmentManager.beginTransaction().replace(R.id.content_frame, menu).commit();
                                    }
                                });

                        AlertDialog al = alert.create();
                        al.setTitle("Open/Delete");
                        alert.show();
                    }
                });

                layout.addView(fixture);
            }

        }

        return menuView;
    }
}
