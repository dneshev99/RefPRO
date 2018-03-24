package com.elsys.refpro.refpromobile.activities;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.elsys.refpro.refpromobile.adapters.PlayerHeadAdapter;
import com.elsys.refpro.refpromobile.adapters.PlayersAdapter;
import com.elsys.refpro.refpromobile.adapters.PlayersAdapterAssigned;
import com.elsys.refpro.refpromobile.application.DIApplication;
import com.elsys.refpro.refpromobile.database.LocalDatabase;
import com.elsys.refpro.refpromobile.R;
import com.elsys.refpro.refpromobile.dto.MatchUpdateDTO;
import com.elsys.refpro.refpromobile.dto.PlayerDTO;
import com.elsys.refpro.refpromobile.http.handlers.PlayersHandler;
import com.elsys.refpro.refpromobile.models.Player;
import com.elsys.refpro.refpromobile.services.FirebaseService;
import com.elsys.refpro.refpromobile.http.HttpDetails;
import com.elsys.refpro.refpromobile.services.UpdateMatchService;
import com.elsys.refpro.refpromobile.dto.NotificationDTO;
import com.jackandphantom.circularimageview.CircleImage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
    LocalDatabase db;
    List<PlayerDTO> homePlayers = new ArrayList<>();
    List<PlayerDTO> awayPlayers = new ArrayList<>();
    List<PlayerDTO> homeSubs = new ArrayList<>();
    List<PlayerDTO> awaySubs = new ArrayList<>();

    int counter = 0;

    ProgressBar loading;

    @Inject
    PlayersHandler playersHandler;
    final ArrayList<PlayerDTO> homePlayersAssignedDtos = new ArrayList<>();
    final ArrayList<PlayerDTO> a = new ArrayList<PlayerDTO>();
    class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View receiverView, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:

                    break;
                case DragEvent.ACTION_DROP:
                    if(receiverView instanceof ImageView){
                        // Dropped, reassign View to ViewGroup
                        CircleImage draggedView = (CircleImage) event.getLocalState();
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

                    if(receiverView instanceof ConstraintLayout){
                        CircleImage img = (CircleImage) event.getLocalState();
                         img.setVisibility(View.VISIBLE);
                    }

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                   // v.setBackgroundDrawable(normalShape);
                    break;

                default:
//                    CircleImage img = (CircleImage) event.getLocalState();
//                    img.setVisibility(View.VISIBLE);
                    break;
            }
            return true;
        }
    }

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

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ((DIApplication) this.getActivity().getApplicationContext()).getApplicationComponent().inject(this);
        createView = inflater.inflate(R.layout.activity_info_v2, container, false);
        GridView playersDrawer = (GridView) createView.findViewById(R.id.right_drawer);
       ConstraintLayout c = (ConstraintLayout) createView.findViewById(R.id.awayTeamTeam);


        a.add(new PlayerDTO(1,"22"));
        a.add(new PlayerDTO(1,"24"));
        a.add(new PlayerDTO(1,"21"));
        a.add(new PlayerDTO(1,"25"));
        a.add(new PlayerDTO(1,"27"));
        a.add(new PlayerDTO(1,"29"));
        a.add(new PlayerDTO(1,"200"));
        a.add(new PlayerDTO(1,"256"));


        PlayerHeadAdapter adapter = new PlayerHeadAdapter(this.getActivity(), a);
        playersDrawer.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        createView.findViewById(R.id.circleImage).setOnDragListener(new MyDragListener());
        createView.findViewById(R.id.circleImage12).setOnDragListener(new MyDragListener());
        c.setOnDragListener(new MyDragListener());


        db = new LocalDatabase(this.getActivity());
        final Cursor data = db.getRow(match_id);
        data.moveToFirst();

        Button b = (Button) createView.findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = ((ConstraintLayout) createView.findViewById(R.id.awayTeamTeam)).getChildCount();
                for(int i=0;i<count;i++){
                    Toast.makeText(getContext(), "Count"+((PlayerDTO)((ConstraintLayout) createView.findViewById(R.id.awayTeamTeam)).getChildAt(i).getTag()).getShirtName(), Toast.LENGTH_LONG).show();
                }

            }
        });

        //playersHandler.setHomePlayersForDrawer("Team Gosho",playersDrawer);
        return createView;
    }

    @Nullable

    public View onCreateView_old(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ((DIApplication)this.getActivity().getApplicationContext()).getApplicationComponent().inject(this);

        createView = inflater.inflate(R.layout.activity_info, container, false);
        loading = (ProgressBar) createView.findViewById(R.id.progressBar);
        ListView playersDrawer = (ListView) createView.findViewById(R.id.right_drawer);
        ListView homePlayersAssigned = (ListView) createView.findViewById(R.id.home_players_assigned);
        homePlayersAssigned.setOnDragListener(new MyDragListener());

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

        playersHandler.setHomePlayersForDrawer(data.getString(3),playersDrawer);
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
