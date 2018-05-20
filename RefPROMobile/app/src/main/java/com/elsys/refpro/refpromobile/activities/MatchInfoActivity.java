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
    String homeTeamName,awayTeamName, mongoId;
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


    private ConstraintLayout awayTeamLayout=null;
    private ConstraintLayout homeTeamLayout=null;



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

    private void setDragListenerToIcons(ViewGroup viewGroup){
        for(int i=0;i<viewGroup.getChildCount();i++){
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

        matchHandler.getMatchInfoById(mongoId,homeTeamLayout,awayTeamLayout);


//        //String mongoId=data.getString(10);
//        private static final String COL4 = "home";
//        private static final String COL5 = "away";
        PlayerHeadAdapter adapter = new PlayerHeadAdapter(this.getActivity(), homePlayers);
        playersDrawer.setAdapter(adapter);

        PlayerHeadAdapter adapterAway = new PlayerHeadAdapter(this.getActivity(), awayPlayers);
        playersDrawerAway.setAdapter(adapterAway);


        playersHandler.setPlayersByTeamNameForAdapter(homeTeamName,adapter);
        playersHandler.setPlayersByTeamNameForAdapter(awayTeamName,adapterAway);



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

                    if (dto != null && i < 11) {
                        assignedAwayPlayers.add(dto);
                    }
                    else if( dto!=null && i>=11){
                        assignedAwaySubs.add(dto);
                    }
                }

                for (int i = 0; i < homeTeamLayout.getChildCount(); i++) {
                    PlayerDTO dto = (PlayerDTO) homeTeamLayout.getChildAt(i).getTag();
                    if (dto == null) {
                       //TODO
                        continue;
                    }
                    if ( i < 11) {
                        assignedHomePlayers.add(dto);
                    }
                    else if( dto!=null && i>=11){
                        assignedHomeSubs.add(dto);
                    }
                }

                MatchUpdateDTO matchInfoUpdateDto = new MatchUpdateDTO();
                matchInfoUpdateDto.setMatchId(mongoId);
                matchInfoUpdateDto.setPlayersAway(assignedAwayPlayers);
                matchInfoUpdateDto.setPlayersHome(assignedHomePlayers);
                matchInfoUpdateDto.setSubsAway(assignedAwaySubs);
                matchInfoUpdateDto.setSubsHome(assignedHomeSubs);
                matchHandler.updateMatchInfoById(mongoId,matchInfoUpdateDto);
                //|| assignedHomeSubs.size()<7 || assignedAwaySubs.size()<7
                if(1==1 || assignedHomePlayers.size()<=11 || assignedAwayPlayers.size()<=11 ){
                    Toast.makeText(v.getContext(), "Missing team members",Toast.LENGTH_SHORT).show();

                }else{

                }
            }
        });

        return createView;
    }


    public  void getHomeAndAwayTeamNames(int match_id){

        db = new LocalDatabase(this.getActivity());
        final Cursor data = db.getRow(match_id);
        data.moveToFirst();
        homeTeamName =data.getString(3) ;
        awayTeamName =data.getString(4);
        mongoId = data.getString(10);
    }

    @Nullable
    public View onCreateView_old(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ((DIApplication)this.getActivity().getApplicationContext()).getApplicationComponent().inject(this);

        createView = inflater.inflate(R.layout.activity_info, container, false);
        loading = (ProgressBar) createView.findViewById(R.id.progressBar);
        ListView playersDrawer = (ListView) createView.findViewById(R.id.right_drawer);
        ListView homePlayersAssigned = (ListView) createView.findViewById(R.id.home_players_assigned);
        homePlayersAssigned.setOnDragListener(new PlayerHeadDragListener());

        PlayersAdapterAssigned homePlayersAdapter = new PlayersAdapterAssigned(homePlayersAssignedDtos,this.getActivity()
        );
        homePlayersAssigned.setAdapter(homePlayersAdapter);


        SharedPreferences preferences;
        preferences = getActivity().getSharedPreferences("RefPRO" , 0);
        final String WatchFCMToken = preferences.getString("WatchFCMToken", "N/A");




        //region INITIALIZE
        LinearLayout layout = (LinearLayout) createView.findViewById(R.id.info_layout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //Every Title will have Top Margin of 120dp
        params.setMargins(0, 120, 0, 0);

        //GET current match ID and GET the information about match with it's unique key
        SharedPreferences mPrefs = this.getActivity().getPreferences(MODE_PRIVATE);
        match_id = mPrefs.getInt("matchId", 0);

        db = new LocalDatabase(this.getActivity());
       final Cursor data = db.getRow(match_id);

       data.moveToFirst();


        //GET information about current match and SET as Text
        competition = (TextView) createView.findViewById(R.id.matchNameInformation);
        date = (TextView) createView.findViewById(R.id.dateInformation);
        time = (TextView) createView.findViewById(R.id.timeInformation);
        teams = (TextView) createView.findViewById(R.id.teamsInformation);
        player_title = (TextView) createView.findViewById(R.id.playerTypeText);

        data.moveToFirst();

        competition.setText(data.getString(1));
        date.setText(data.getString(5));
        time.setText(data.getString(6));
        teams.setText("" + data.getString(3) + " vs. " + data.getString(4));

       // playersHandler.setPlayersByTeamNameForAdapter(data.getString(3),homePlayersAdapter);
        //endregion

        number = (EditText) createView.findViewById(R.id.playerNumberForm);
        name = (EditText) createView.findViewById(R.id.playerNameForm);

        final Button create = (Button) createView.findViewById(R.id.sendButton);
        create.setEnabled(false);

        final Button add = (Button) createView.findViewById(R.id.addPlayerButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.getText().toString().isEmpty() || number.getText().toString().isEmpty()) {

                    number.setError(getResources().getString(R.string.empty_field));
                    name.setError(getResources().getString(R.string.empty_field));
                } else {

                    int player_number = Integer.parseInt(number.getText().toString());
                    String player_name = name.getText().toString();

                    if (counter < Integer.parseInt(data.getString(7)) && counter < 100) {

                        PlayerDTO player = new PlayerDTO(player_number, player_name);
                        homePlayers.add(player);
                        counter++;
                        if (counter == Integer.parseInt(data.getString(7))) {
                            counter = 100;
                            player_title.setText(getResources().getString(R.string.away_players));
                        }
                    } else if (counter < (Integer.parseInt(data.getString(7)) + 100) && counter < 200) {

                        PlayerDTO player = new PlayerDTO(player_number, player_name);
                        awayPlayers.add(player);
                        counter++;
                        if (counter == (Integer.parseInt(data.getString(7)) + 100)) {
                            counter = 200;
                            player_title.setText(getResources().getString(R.string.home_subs));
                        }
                    } else if (counter < (Integer.parseInt(data.getString(8)) + 200) && counter < 300) {

                        PlayerDTO player = new PlayerDTO(player_number, player_name);
                        homeSubs.add(player);
                        counter++;
                        if (counter == (Integer.parseInt(data.getString(8)) + 200)) {
                            counter = 300;
                            player_title.setText(getResources().getString(R.string.away_subs));
                        }
                    } else if (counter < (Integer.parseInt(data.getString(8)) + 300) && counter < 400) {

                        PlayerDTO player = new PlayerDTO(player_number, player_name);
                        awaySubs.add(player);
                        counter++;
                        if (counter == (Integer.parseInt(data.getString(8))+ 300)) {

                            add.setEnabled(false);
                            create.setEnabled(true);
                        }
                    }

                    Toast.makeText(getActivity(), "Player added",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


        final SharedPreferences finalPreferences = preferences;
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Match started",
                        Toast.LENGTH_LONG).show();

                final String token = finalPreferences.getString("token", "N/A");

                OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder()
                                .addHeader("Authorization", token)
                                .build();
                        Log.d("Na sasho tokena", token);
                        return chain.proceed(newRequest);
                    }
                }).build();

                Retrofit retrofit = new Retrofit.Builder()
                        .client(client)
                        .baseUrl(HttpDetails.getRetrofitUrl())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();


                UpdateMatchService service = retrofit.create(UpdateMatchService.class);

                loading.setVisibility(View.VISIBLE);
                create.setVisibility(View.GONE);

                String matchId = data.getString(12);

                MatchUpdateDTO body = new MatchUpdateDTO(matchId, homePlayers, homeSubs, awayPlayers, awaySubs);

                Log.d("D", body.getMatchId());

                service.update(body).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        if (response.isSuccessful()) {

                            loading.setVisibility(View.GONE);
                            create.setVisibility(View.VISIBLE);

                            db.delete(match_id);

                            Toast.makeText(getActivity(), R.string.delete_success,
                                    Toast.LENGTH_LONG).show();

                            OkHttpClient firebaseClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                                @Override
                                public okhttp3.Response intercept(Chain chain) throws IOException {
                                    Request newRequest = chain.request().newBuilder()
                                            .addHeader("Content-Type","application/json")
                                            .addHeader("Authorization","key=AAAAjh3KD7U:APA91bHcDRfM4Vk96KnYf2TA_AagYbyB2Y23iRvahJEuF5mgsooL--JN7FYN4UPyisZVizN5lIB5Jl768AqiDc0ex_vbZtfg9qNxJHT8n91I8nt2t94UYjx6uJrViLps0d7jC7dB-m1k")
                                            .build();
                                    return chain.proceed(newRequest);
                                }
                            }).build();


                            Retrofit firebase = new Retrofit.Builder()
                                    .client(firebaseClient)
                                    .baseUrl("https://fcm.googleapis.com")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();


                            NotificationDTO notificationDTO = new NotificationDTO();

                            notificationDTO.setRecipient(WatchFCMToken);
                            notificationDTO.addData("id", data.getString(12));

                            FirebaseService firebaseService = firebase.create(FirebaseService.class);

                            firebaseService.sendNotification(notificationDTO).enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    Log.d("Firebase AAA", response.message());
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Log.d("Firebase AAA ERROR" , t.getMessage());
                                }
                            });
                        }
                    }


                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        loading.setVisibility(View.GONE);
                        create.setVisibility(View.VISIBLE);
                    }
                });

                MenuActivity menu = new MenuActivity();
                android.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, menu).commit();
            }
        });


        return createView;
    }
}
