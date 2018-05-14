package com.elsys.refpro.refpromobile.adapters;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.elsys.refpro.refpromobile.R;
import com.elsys.refpro.refpromobile.dto.PlayerDTO;
import com.elsys.refpro.refpromobile.listeners.PlayerHeadTouchListener;


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
        ImageView imageView;
        TextView playerHeadText;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PlayerHeadViewHolder holder=null;
        ImageView imageView;
        View resultView=null;
        if(convertView==null ){
            holder=new PlayerHeadViewHolder();
            resultView=convertView.inflate(mContext,R.layout.player_head,null);
            imageView = (ImageView) resultView.findViewById(R.id.head);
            holder.imageView= (ImageView) imageView.findViewById(R.id.head);
            holder.playerHeadText = (TextView) resultView.findViewById(R.id.playerHeadText);
            convertView=imageView;
            convertView.setTag(holder);
        }else{
            imageView= (ImageView) convertView;
            holder= (PlayerHeadViewHolder) convertView.getTag();
            resultView = convertView;
            if(imageView.getVisibility()==View.INVISIBLE){
                imageView .setVisibility(View.VISIBLE);
            }
        }



        int width = 80;
        int height = 80;
//        GridLayout.LayoutParams parms = new GridLayout.LayoutParams();
//
//        imageView.setLayoutParams(parms);
//        imageView.getLayoutParams().height=160;
//        imageView.getLayoutParams().height=160;

        holder.player=players.get(position);
        if(holder.player.getPlayerId()!=null){
            File directory = mContext.getFilesDir();
            File file = new File(directory, holder.player.getPlayerId());
            if(file.exists()){
                RequestOptions options = new RequestOptions();
                options.override(100,100);
                options.circleCrop();
                Glide.with(mContext).applyDefaultRequestOptions(options).load(file.getAbsolutePath()).into(imageView);
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

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public void remove(@Nullable PlayerDTO object) {
       players.remove(object);
       notifyDataSetChanged();
    }



    @Nullable
    @Override
    public PlayerDTO getItem(int position) {
        return players.get(position);
    }
}
