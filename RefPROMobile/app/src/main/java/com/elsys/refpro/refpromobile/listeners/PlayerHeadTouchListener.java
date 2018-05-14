package com.elsys.refpro.refpromobile.listeners;

import android.support.v4.widget.DrawerLayout;
import android.view.MotionEvent;
import android.view.View;

public final class PlayerHeadTouchListener implements View.OnTouchListener {

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                    v);
            v.startDrag(null, shadowBuilder, v, 0);
            v.setVisibility(View.INVISIBLE);
            DrawerLayout drawer = (DrawerLayout) v.getParent().getParent().getParent();
            drawer.closeDrawers();

            return true;
        } else {
            return false;
        }
    }
}