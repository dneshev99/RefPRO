package com.elsys.refpro.refpromobile.listeners;

import android.view.DragEvent;
import android.view.View;

import com.jackandphantom.circularimageview.CircleImage;

public class TerrainDragListener  implements View.OnDragListener {

    @Override
    public boolean onDrag(View receiverView, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:
                CircleImage img = (CircleImage) event.getLocalState();
                img.setVisibility(View.VISIBLE);
                break;

            default:
                break;
        }
        return true;
    }
}