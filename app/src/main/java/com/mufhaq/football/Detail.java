package com.mufhaq.football;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Detail extends AppCompatActivity {

    Realm realm;
    RealmHelper realmHelper;
    ModelFootballRealm clubModel;


    Bundle extras;
    String title;
    String alternate;
    String country;
    String date;
    String description;
    String path;
    String idteam;

    ImageView teamBadges;
    TextView teamDesc, teamName, teamAlternate, teamYear, teamCountry;
    Button bBookmark;

    private RecyclerView recyclerView;
    private DataAdapterEventsLast dataAdapterEventsLast;
    private ArrayList<ModelEventsLast> DataArrayList; //kit add kan ke adapter
    TextView tvnodata;
    ProgressDialog dialog;

    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recyclerView = (RecyclerView) findViewById(R.id.rvdataDetail);
        tvnodata = (TextView) findViewById(R.id.tvnodataDetail);
        dialog = new ProgressDialog(Detail.this);
        tvnodata.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        //addData();
        addDataOnline();



        if (extras != null){
            id = extras.getInt("id");
        }

        extras = getIntent().getExtras();
        teamName = (TextView)findViewById(R.id.teamNAmeDetail);
        teamDesc = (TextView)findViewById(R.id.teamDesc);
        teamBadges = (ImageView) findViewById(R.id.teamBadgesDetail);
        teamAlternate = (TextView) findViewById(R.id.teamAlternateName);
        teamYear = (TextView) findViewById(R.id.formedYear);
        teamCountry = (TextView) findViewById(R.id.teamCountryDetail);
        bBookmark = (Button) findViewById(R.id.bBookmark);
        teamDesc.setMovementMethod(new ScrollingMovementMethod());

        if (extras != null) {
            title = extras.getString("namaClub");
            idteam = extras.getString("idTeam");
            description = extras.getString("deskripsiClub");
            path = extras.getString("logoClub");
            date = extras.getString("formedYear");
            alternate = extras.getString("alternateTeamName");
            country = extras.getString("country");

            teamCountry.setText(country);
            teamAlternate.setText(alternate);
            teamYear.setText(date);
            teamName.setText(title);
            teamDesc.setText(description);

            Glide.with(Detail.this)
                    .load(path)
//                    .override(Target.SIZE_ORIGINAL)
                    .apply(new RequestOptions().override(400))
                    .placeholder(R.mipmap.ic_launcher)
                    .into(teamBadges);
            // and get whatever type user account id is
        }

        //Set up Realm
        Realm.init(Detail.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);


        bBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clubModel = new ModelFootballRealm();
                clubModel.setDesc(description);
                clubModel.setAlternate(alternate);
                clubModel.setJudul(title);
                clubModel.setPath(path);
                clubModel.setYear(date);
                clubModel.setCountry(country);

                realmHelper = new RealmHelper(realm);
                realmHelper.save(clubModel);
            }
        });

    }

    void addDataOnline(){
        //kasih loading
        dialog.setMessage("Sedang memproses data");
        dialog.show();
        // background process
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/eventslast.php?id="+id)
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d("hasiljson", "onResponse: " + response.toString());
                        //jika sudah berhasil debugm lanjutkan code dibawah ini
                        DataArrayList = new ArrayList<>();
                        ModelEventsLast modelku;
                        try {
                            Log.d("hasiljson", "onResponse: " + response.toString());
                            JSONArray jsonArray = response.getJSONArray("results");
                            Log.d("hasiljson2", "onResponse: " + jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                modelku = new ModelEventsLast();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                modelku.setId(jsonObject.getInt("idEvent"));
                                modelku.setStrEvent(jsonObject.getString("strEvent"));
                                modelku.setHomeScore(jsonObject.getString("intHomeScore"));
                                modelku.setAwayScore(jsonObject.getString("intAwayScore"));
                                modelku.setHome(jsonObject.getString("strHomeTeam"));
                                modelku.setAway(jsonObject.getString("strAwayTeam"));
                                modelku.setDate(jsonObject.getString("dateEvent"));
                                modelku.setTime(jsonObject.getString("strTime"));
                                DataArrayList.add(modelku);
                            }
                            //untuk handle click
                            dataAdapterEventsLast = new DataAdapterEventsLast(DataArrayList, new DataAdapterEventsLast.Callback() {

                                @Override
                                public void onClick(int position) {

                                }

                                @Override
                                public void test() {

                                }
                            });
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Detail.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(dataAdapterEventsLast);
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        }

                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("errorku", "onError errorCode : " + error.getErrorCode());
                        Log.d("errorku", "onError errorBody : " + error.getErrorBody());
                        Log.d("errorku", "onError errorDetail : " + error.getErrorDetail());
                    }
                });
    }
}