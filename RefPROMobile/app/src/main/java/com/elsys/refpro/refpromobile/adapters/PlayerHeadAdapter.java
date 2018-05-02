package com.elsys.refpro.refpromobile.adapters;


import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;

import com.elsys.refpro.refpromobile.R;
import com.elsys.refpro.refpromobile.dto.PlayerDTO;
import com.elsys.refpro.refpromobile.listeners.PlayerHeadTouchListener;
import com.jackandphantom.circularimageview.CircleImage;

import java.util.List;

public class PlayerHeadAdapter extends ArrayAdapter<PlayerDTO> {

    private Context mContext;
    private List<PlayerDTO> players;
    public PlayerHeadAdapter(Context c, List<PlayerDTO> players) {
        super(c, R.layout.activity_info_teams_drawer, players);
        mContext = c;
        this.players=players;
    }

    public  static class PlayerHeadViewHolder {
        public PlayerDTO player;
        CircleImage imageView;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PlayerHeadViewHolder holder=null;
        CircleImage imageView;
        if(convertView==null){
            holder=new PlayerHeadViewHolder();
            View v=convertView.inflate(mContext,R.layout.player_head,null);
            imageView = (CircleImage) v.findViewById(R.id.head);
            holder.imageView= (CircleImage) imageView.findViewById(R.id.head);
            convertView=imageView;
            convertView.setTag(holder);
        }else{
            imageView= (CircleImage) convertView;
            holder= (PlayerHeadViewHolder) convertView.getTag();
        }
        imageView.setImageResource(R.drawable.maradona);
        int width = 80;
        int height = 80;
//        GridLayout.LayoutParams parms = new GridLayout.LayoutParams();
//
//        imageView.setLayoutParams(parms);
//        imageView.getLayoutParams().height=160;
//        imageView.getLayoutParams().height=160;
        View v =convertView.inflate(mContext,R.layout.player_head,null);
        holder.player=players.get(position);

        imageView.setOnTouchListener(new PlayerHeadTouchListener());
        return imageView;
    }



    public void refreshAdapterOnUIThread (){
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }

    public List<PlayerDTO> getItems(){
        return this.players;
    }
}
