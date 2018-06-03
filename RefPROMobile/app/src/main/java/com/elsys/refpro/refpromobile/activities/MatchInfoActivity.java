package com.elsys.refpro.refpromobile.activities;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import com.bumptech.glide.request.target.ViewTarget;
import com.elsys.refpro.refpromobile.adapters.PlayerHeadAdapter;
import com.elsys.refpro.refpromobile.adapters.PlayersAdapterAssigned;
import com.elsys.refpro.refpromobile.application.DIApplication;
import com.elsys.refpro.refpromobile.database.LocalDatabase;
import com.elsys.refpro.refpromobile.R;
import com.elsys.refpro.refpromobile.dto.MatchUpdateDTO;
import com.elsys.refpro.refpromobile.dto.PlayerDTO;
import com.elsys.refpro.refpromobile.http.handlers.MatchHandler;
import com.elsys.refpro.refpromobile.http.handlers.PlayersHandler;
import com.elsys.refpro.refpromobile.listeners.PlayerHeadDragListener;
import com.elsys.refpro.refpromobile.listeners.TerrainDragListener;
import com.elsys.refpro.refpromobile.services.FirebaseService;
import com.elsys.refpro.refpromobile.http.HttpDetails;
import com.elsys.refpro.refpromobile.services.UpdateMatchService;
import com.elsys.refpro.refpromobile.dto.NotificationDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class MatchInfoActivity extends Fragment {

    View createView;
    TextView competition, date, time, teams, player_title;
    EditText number, name;
    int match_id;
    String homeTeamName, awayTeamName, mongoId;
    LocalDatabase db;
    List<PlayerDTO> homePlayers = new ArrayList<>();
    List<PlayerDTO> awayPlayers = new ArrayList<>();
    List<PlayerDTO> homeSubs = new ArrayList<>();
    List<PlayerDTO> awaySubs = new ArrayList<>();

    int counter = 0;

    ProgressBar loading;

    @Inject
    PlayersHandler playersHandler;
    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    MatchHandler matchHandler;

    final ArrayList<PlayerDTO> homePlayersAssignedDtos = new ArrayList<>();
    final ArrayList<PlayerDTO> allPlayersForDrawer = new ArrayList<PlayerDTO>();


    private ConstraintLayout awayTeamLayout = null;
    private ConstraintLayout homeTeamLayout = null;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void setDragListenerToIcons(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).setOnDragListener(new PlayerHeadDragListener());
            //viewGroup.getChildAt(i).setOnTouchListener(new TerrainHeadTouchListener());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        ((DIApplication) this.getActivity().getApplicationContext()).getApplicationComponent().inject(this);
        ButterKnife.bind(this.getActivity());
        createView = inflater.inflate(R.layout.activity_info_v2, container, false);


        GridView playersDrawer = (GridView) createView.findViewById(R.id.right_drawer);
        GridView playersDrawerAway = (GridView) createView.findViewById(R.id.right_drawer_away);
        awayTeamLayout = (ConstraintLayout) createView.findViewById(R.id.awayTeam);
        homeTeamLayout = (ConstraintLayout) createView.findViewById(R.id.homeTeam);
        // homeTeamLayout.setTag();
        int matchId = this.getArguments().getInt("matchId");
        getHomeAndAwayTeamNames(matchId);

        matchHandler.getMatchInfoById(mongoId, homeTeamLayout, awayTeamLayout);


//        //String mongoId=data.getString(10);
//        private static final String COL4 = "home";
//        private static final String COL5 = "away";
        PlayerHeadAdapter adapter = new PlayerHeadAdapter(this.getActivity(), homePlayers);
        playersDrawer.setAdapter(adapter);

        PlayerHeadAdapter adapterAway = new PlayerHeadAdapter(this.getActivity(), awayPlayers);
        playersDrawerAway.setAdapter(adapterAway);


        playersHandler.setPlayersByTeamNameForAdapter(homeTeamName, adapter);
        playersHandler.setPlayersByTeamNameForAdapter(awayTeamName, adapterAway);


        awayTeamLayout.setOnDragListener(new TerrainDragListener());
        homeTeamLayout.setOnDragListener(new TerrainDragListener());
        setDragListenerToIcons(awayTeamLayout);
        setDragListenerToIcons(homeTeamLayout);

        FloatingActionButton fab = (FloatingActionButton) createView.findViewById(R.id.button2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<PlayerDTO> assignedHomePlayers = new ArrayList<PlayerDTO>();
                ArrayList<PlayerDTO> assignedAwayPlayers = new ArrayList<PlayerDTO>();
                ArrayList<PlayerDTO> assignedHomeSubs = new ArrayList<PlayerDTO>();
                ArrayList<PlayerDTO> assignedAwaySubs = new ArrayList<PlayerDTO>();

                List<ConstraintLayout> layouts = new LinkedList<>();
                layouts.add(awayTeamLayout);
                layouts.add(homeTeamLayout);


                for (int i = 0; i < awayTeamLayout.getChildCount(); i++) {
                    PlayerDTO dto = (PlayerDTO) awayTeamLayout.getChildAt(i).getTag();
                    if (dto == null) {
                        //TODO
                        continue;
                    }
                    if (i < 11) {
                        assignedAwayPlayers.add(dto);
                    } else if (i >= 11) {
                        assignedAwaySubs.add(dto);
                    }
                }

                for (int i = 0; i < homeTeamLayout.getChildCount(); i++) {
                    PlayerDTO dto = (PlayerDTO) homeTeamLayout.getChildAt(i).getTag();
                    if (dto == null) {
                        //TODO
                        continue;
                    }
                    if (i < 11) {
                        assignedHomePlayers.add(dto);
                    } else if (i >= 11) {
                        assignedHomeSubs.add(dto);
                    }
                }

                MatchUpdateDTO matchInfoUpdateDto = new MatchUpdateDTO();
                matchInfoUpdateDto.setMatchId(mongoId);
                matchInfoUpdateDto.setPlayersAway(assignedAwayPlayers);
                matchInfoUpdateDto.setPlayersHome(assignedHomePlayers);
                matchInfoUpdateDto.setSubsAway(assignedAwaySubs);
                matchInfoUpdateDto.setSubsHome(assignedHomeSubs);
                matchHandler.updateMatchInfoById(mongoId, matchInfoUpdateDto);
                //|| assignedHomeSubs.size()<7 || assignedAwaySubs.size()<7
                if (1 == 1 || assignedHomePlayers.size() <= 11 || assignedAwayPlayers.size() <= 11) {
                    Toast.makeText(v.getContext(), "Missing team members", Toast.LENGTH_SHORT).show();

                } else {

                }
            }
        });

        return createView;
    }


    public void getHomeAndAwayTeamNames(int match_id) {

        db = new LocalDatabase(this.getActivity());
        final Cursor data = db.getRow(match_id);
        data.moveToFirst();
        homeTeamName = data.getString(3);
        awayTeamName = data.getString(4);
        mongoId = data.getString(10);
    }
}