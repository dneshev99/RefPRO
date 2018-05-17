package com.elsys.refpro.refpromobile.adapters;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.elsys.refpro.refpromobile.R;
import com.elsys.refpro.refpromobile.activities.MatchInfoActivity;
import com.elsys.refpro.refpromobile.activities.MenuActivity;
import com.elsys.refpro.refpromobile.http.RetrofitHookBack;
import com.elsys.refpro.refpromobile.http.handlers.MatchHandler;
import com.elsys.refpro.refpromobile.models.Item;

import org.apache.commons.lang3.SerializationUtils;

import java.util.List;

import javax.inject.Inject;

public class MatchItemAdapter extends RecyclerView.Adapter<MatchItemAdapter.MatchItemViewHolder> {

    private List<Item> data;
    private Context mContext;
    private FragmentManager fragmentManager;
    private MatchHandler matchHandler;

    public void setMatchHandler(MatchHandler matchHandler) {
        this.matchHandler = matchHandler;
    }

    public MatchItemAdapter(List<Item> data, Context context, FragmentManager fragmentManager) {
        //super(context, R.layout.match_row, data);
        this.data = data;
        this.mContext=context;
        this.fragmentManager = fragmentManager;

    }

    @Override
    public MatchItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View matchRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_row,parent,false);
       matchRow.setOnClickListener(new MatchItemOnClick());
       MatchItemViewHolder holder = new MatchItemViewHolder(matchRow);
       matchRow.setTag(holder);
       return holder;
    }

    @Override
    public void onBindViewHolder(MatchItemViewHolder holder, int position) {
        Item matchData = data.get(position);
        holder.awayTeamName.setText(matchData.getAway());
        holder.matchInfoDbId = matchData.getDbId();
        holder.matchInfoMongoId = matchData.getMongoId();
        holder.homeTeamName.setText(matchData.getHome());
        holder.date.setText(" Date: " + matchData.getDate());
        holder.time.setText(" Time: " + matchData.getTime());
        holder.venue.setText(" Venue: " + matchData.getVenue());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MatchItemViewHolder extends RecyclerView.ViewHolder{
        public TextView homeTeamName,awayTeamName,venue,date,time;
        public int matchInfoDbId;
        public String matchInfoMongoId;
        public MatchItemViewHolder(View itemView) {
            super(itemView);
            this.awayTeamName = (TextView) itemView.findViewById(R.id.away_team_name);
            this.homeTeamName =(TextView) itemView.findViewById(R.id.home_team_name);
            this.venue =(TextView) itemView.findViewById(R.id.venue);
            this.date =(TextView) itemView.findViewById(R.id.date);
            this.time =(TextView) itemView.findViewById(R.id.time);
        }
    }




    private class MatchItemOnClick implements View.OnClickListener {

        @Override
        public void onClick(final View v) {

            AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
            alert.setMessage(R.string.alert_text).setCancelable(true)
                    .setPositiveButton(R.string.open, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            final ProgressDialog loadingDialog = ProgressDialog.show(v.getContext(), "", "Fetching server data!", true);
                            loadingDialog.setCancelable(false);
                            String mongoId = ((MatchItemViewHolder)v.getTag()).matchInfoMongoId;
                            matchHandler.getMatchInfoById(mongoId, new RetrofitHookBack() {
                                @Override
                                public void executeCallBack(Object... objects) {
                                    loadingDialog.dismiss();
                                    MatchInfoActivity matchInfoActivity = new MatchInfoActivity();
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("matchId", ((MatchItemViewHolder)v.getTag()).matchInfoDbId);
                                    // SerializationUtils.serialize()
                                    matchInfoActivity.setArguments(bundle);
                                    fragmentManager.beginTransaction().replace(R.id.content_frame, matchInfoActivity).commit();
                                    Log.d("click","yes");
                                }

                                @Override
                                public void executeErrorCallBack(Object... objects) {
                                    loadingDialog.dismiss();
                                    Toast.makeText(v.getContext(), "Connecting to server failed",Toast.LENGTH_SHORT).show();
                                }
                            });
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
