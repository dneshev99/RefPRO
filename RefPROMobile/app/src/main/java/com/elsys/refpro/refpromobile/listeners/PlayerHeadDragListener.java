package com.elsys.refpro.refpromobile.listeners;

import android.support.constraint.ConstraintLayout;
import android.view.DragEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.elsys.refpro.refpromobile.adapters.PlayerHeadAdapter;
import com.elsys.refpro.refpromobile.dto.PlayerDTO;

public class PlayerHeadDragListener implements View.OnDragListener {

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
                if(receiverView instanceof ImageView){
                    // Dropped, reassign View to ViewGroup
                    ImageView draggedView = (ImageView) event.getLocalState();
                    ImageView receiverViewImage = (ImageView) receiverView;

                    GridView owner = (GridView) draggedView.getParent();
                    ConstraintLayout relativeLayoutTerrain = (ConstraintLayout) receiverView.getParent();

                    int draggedOverIndex = relativeLayoutTerrain.indexOfChild(receiverView);

                    PlayerHeadAdapter adapter = (PlayerHeadAdapter) owner.getAdapter();
                    PlayerDTO assignedPlayer = ((PlayerHeadAdapter.PlayerHeadViewHolder) draggedView.getTag()).player;
                    adapter.remove(assignedPlayer);
                    adapter.notifyDataSetChanged();


                    receiverView.setTag(assignedPlayer);
                    receiverViewImage.setImageDrawable(draggedView.getDrawable());
                    relativeLayoutTerrain.refreshDrawableState();
                }

                break;
            case DragEvent.ACTION_DRAG_ENDED:
                break;

            default:break;
        }
        return true;
    }
}
