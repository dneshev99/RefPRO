package com.elsys.refpro.refpromobile.adapters;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.DrawerLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.elsys.refpro.refpromobile.R;
import com.elsys.refpro.refpromobile.dto.PlayerDTO;
import com.elsys.refpro.refpromobile.listeners.PlayerHeadTouchListener;
import com.jackandphantom.circularimageview.CircleImage;

import java.io.File;
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
        TextView playerHeadText;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PlayerHeadViewHolder holder=null;
        CircleImage imageView;
        TextView playerHeadText;
        if(convertView==null){
            holder=new PlayerHeadViewHolder();
            View v=convertView.inflate(mContext,R.layout.player_head,null);
            imageView = (CircleImage) v.findViewById(R.id.head);
            holder.imageView= (CircleImage) imageView.findViewById(R.id.head);
            holder.playerHeadText = (TextView) v.findViewById(R.id.playerHeadText);
            convertView=imageView;
            convertView.setTag(holder);
        }else{
            imageView= (CircleImage) convertView;
            holder= (PlayerHeadViewHolder) convertView.getTag();
        }


        int width = 80;
        int height = 80;
//        GridLayout.LayoutParams parms = new GridLayout.LayoutParams();
//
//        imageView.setLayoutParams(parms);
//        imageView.getLayoutParams().height=160;
//        imageView.getLayoutParams().height=160;
        View v =convertView.inflate(mContext,R.layout.player_head,null);
        holder.player=players.get(position);
        if(holder.player.getPlayerId()!=null){
            File directory = mContext.getFilesDir();
            File file = new File(directory, holder.player.getPlayerId());
            if(file.exists()){
                GlideApp.with(this).load("http://goo.gl/gEgYUd").into(imageView);
                Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                imageView.setImageBitmap(myBitmap);
            }

        }
        holder.playerHeadText.setText(holder.player.getShirtName());

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

    public static Bitmap decodeSampledBitmapFromFile(String fileAbsolutePath,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        //BitmapFactory.decodeFile(fileAbsolutePath);

        // Calculate inSampleSize
        options.inSampleSize = 3;
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(fileAbsolutePath, options);
    }

    public List<PlayerDTO> getItems(){
        return this.players;
    }
}
