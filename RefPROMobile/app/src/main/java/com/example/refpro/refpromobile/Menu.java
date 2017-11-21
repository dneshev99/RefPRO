package com.example.refpro.refpromobile;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by user on 12.11.2017 г..
 */

public class Menu extends Fragment {

    View menuView;
    TextView string;

    int match_id;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        menuView = inflater.inflate(R.layout.menu, container, false);

        SharedPreferences mPrefs = this.getActivity().getPreferences(MODE_PRIVATE);

        match_id = mPrefs.getInt("ID", 0);

        string = (TextView) menuView.findViewById(R.id.textView7);

        Gson gson = new Gson();
        String json;
        MatchInfo match;
        String key;

        for (int counter = 0; counter < match_id; counter++) {

            key = Integer.toString(counter) + "_match";

            json = mPrefs.getString(key, "");
            match = gson.fromJson(json, MatchInfo.class);
            string.setText(match.getCompetition());
            //string.setText(key);
        }


        return menuView;
    }
}
