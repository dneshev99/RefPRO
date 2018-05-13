package com.elsys.refpro.refpromobile.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.elsys.refpro.refpromobile.R;
import com.elsys.refpro.refpromobile.activities.MatchInfoActivity;
import com.elsys.refpro.refpromobile.activities.MenuActivity;
import com.elsys.refpro.refpromobile.models.Item;

import java.util.List;

public class MatchItemAdapter extends RecyclerView.Adapter<MatchItemAdapter.MatchItemViewHolder> {

    private List<Item> data;
    Context mContext;

    public MatchItemAdapter(List<Item> data, Context context) {
        //super(context, R.layout.match_row, data);
        this.data = data;
        this.mContext=context;

    }

    @Override
    public MatchItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View matchRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_row,parent,false);
       return new MatchItemViewHolder(matchRow);
    }

    @Override
    public void onBindViewHolder(MatchItemViewHolder holder, int position) {
        Item matchData = data.get(position);
        holder.awayTeamName.setText(matchData.getAway());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MatchItemViewHolder extends RecyclerView.ViewHolder{
        public TextView homeTeamName,awayTeamName,venue,date,time;
        public MatchItemViewHolder(View itemView) {
            super(itemView);
            this.awayTeamName = (TextView) itemView.findViewById(R.id.away_team_name);
        }
    }



//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        Item dataModel = data.get(position);
//
//        TextView homeTeamName;
//        TextView awayTeamName;
//        TextView venue;
//        TextView date;
//        TextView time;
//
//
//        if (convertView == null) {
//
//            LayoutInflater inflater = LayoutInflater.from(mContext);
//            convertView = inflater.inflate(R.layout.match_row, parent, false);
//
//            homeTeamName = (TextView) convertView.findViewById(R.id.home_team_name);
//            awayTeamName = (TextView) convertView.findViewById(R.id.away_team_name);
//
//            venue = (TextView) convertView.findViewById(R.id.venue);
//            date = (TextView) convertView.findViewById(R.id.date);
//            time = (TextView) convertView.findViewById(R.id.time);
//
//            homeTeamName.setText(dataModel.getHome());
//            awayTeamName.setText(dataModel.getAway());
//            venue.setText("Venue: " + dataModel.getVenue());
//            date.setText("Date: " + dataModel.getDate());
//            time.setText("Time: " + dataModel.getTime());
//
//            //convertView.setTag(dataModel.getId());
//        }
//
//        return convertView;
//    }


    private class MatchItemOnClick implements View.OnClickListener {

        @Override
        public void onClick(final View v) {

            AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
            alert.setMessage(R.string.alert_text).setCancelable(true)
                    .setPositiveButton(R.string.open, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

//                            MatchInfoActivity matchInfoActivity = new MatchInfoActivity();
//                            android.app.FragmentManager fragmentManager = ((MenuActivity)v.getContext()).getFragmentManager();
//                            Bundle bundle = new Bundle();
//                            bundle.putInt("matchId", id);
//                            matchInfoActivity.setArguments(bundle);
//                            fragmentManager.beginTransaction().replace(R.id.content_frame, matchInfoActivity).commit();
                        }
                    })
                    .setNegativeButton(R.string.delete, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

            AlertDialog al = alert.create();
            al.setTitle(R.string.options);
            alert.show();
        }
    }

}
