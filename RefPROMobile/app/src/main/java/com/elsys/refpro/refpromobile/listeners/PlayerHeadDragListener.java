package com.elsys.refpro.refpromobile.listeners;

import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.elsys.refpro.refpromobile.adapters.PlayerHeadAdapter;
import com.elsys.refpro.refpromobile.dto.PlayerDTO;
import com.elsys.refpro.refpromobile.dto.TeamDTO;

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

                    PlayerHeadAdapter adapter = (PlayerHeadAdapter) owner.getAdapter();
                    PlayerDTO assignedPlayer = ((PlayerHeadAdapter.PlayerHeadViewHolder) draggedView.getTag()).player;
                    TeamDTO teamDTO = (TeamDTO) relativeLayoutTerrain.getTag();

                    if(teamDTO==null && assignedPlayer==null ){
                        Log.d(this.getClass().getName(),"TeamDto or AssignedPlayerDto is null");
                        ((ImageView) event.getLocalState()).setVisibility(View.VISIBLE);
                        return false;
                    }

                    if(teamDTO.getId().equalsIgnoreCase(assignedPlayer.getTeamId())){
                        adapter.remove(assignedPlayer);
                        adapter.notifyDataSetChanged();
                        receiverView.setTag(assignedPlayer);
                        receiverViewImage.setImageDrawable(draggedView.getDrawable());
                        relativeLayoutTerrain.refreshDrawableState();
                    }else{
                        Toast.makeText(receiverView.getContext(), "Can not assign player to the opposite team",
                                Toast.LENGTH_SHORT).show();
                        ((ImageView) event.getLocalState()).setVisibility(View.VISIBLE);
                        return false;
                    }

                }

                break;
            case DragEvent.ACTION_DRAG_ENDED:
                break;

            default:break;
        }
        return true;
    }
}
