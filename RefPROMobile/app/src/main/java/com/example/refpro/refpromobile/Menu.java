package com.example.refpro.refpromobile;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 12.11.2017 г..
 */

public class Menu extends Fragment {

    View menuView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        menuView = inflater.inflate(R.layout.menu, container, false);
        return menuView;
    }
}
