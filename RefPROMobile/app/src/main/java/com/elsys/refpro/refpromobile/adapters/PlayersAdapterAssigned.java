package com.elsys.refpro.refpromobile.adapters;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.elsys.refpro.refpromobile.R;
import com.elsys.refpro.refpromobile.dto.PlayerDTO;

import java.util.List;


public class PlayersAdapterAssigned extends ArrayAdapter<PlayerDTO> {
    private List<PlayerDTO> dataSet;
    Context mContext;

    public PlayersAdapterAssigned(List<PlayerDTO> players, Context context){
        super(context, R.layout.activity_info_teams_drawer,players);
        this.dataSet=players;
        this.mContext=context;
    }

    private static class ViewHolder {
        TextView playerName;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        PlayerDTO player = getItem(position);
        ViewHolder viewHolder;
        final View result;
        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.activity_info_teams_drawer, parent, false);
            viewHolder.playerName= (TextView) convertView.findViewById(R.id.playerName);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.playerName.setText(player.getShirtName());

        return convertView;
    }

    public PlayerDTO getPlayerByName(String shirtName){
        for(PlayerDTO playerDTO:dataSet){
            if(playerDTO.getShirtName().equals(shirtName)){
                return playerDTO;
            }
        }
        return null;
    }

    public final class PlayerTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        v);
                v.startDrag(null, shadowBuilder, v, 0);
                v.setVisibility(View.INVISIBLE);
                DrawerLayout draweer = (DrawerLayout) v.getParent().getParent();
                draweer.closeDrawers();

                return true;
            } else {
                return false;
            }
        }
    }

}
