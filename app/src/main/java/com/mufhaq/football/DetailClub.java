package com.mufhaq.football;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailClub extends AppCompatActivity {

    Realm realm;
    RealmHelper realmHelper;
    ModelDataFavourite clubModel;


    Bundle extras;
    String title;
    String date;
    String description;
    String path;
    String id;

    TextView teamName;
    ImageView teamBadges;
    TextView teamDesc;
    Button bBookmark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_club);
        extras = getIntent().getExtras();
        teamName = (TextView)findViewById(R.id.teamNAmeDetail);
        teamDesc = (TextView)findViewById(R.id.teamDesc);
        teamBadges = (ImageView) findViewById(R.id.teamBadgesDetail);
        bBookmark = (Button) findViewById(R.id.bBookmark);
        teamDesc.setMovementMethod(new ScrollingMovementMethod());

        if (extras != null) {
            title = extras.getString("namaClub");
            id = extras.getString("id");
            description = extras.getString("deskripsiClub");
            path = extras.getString("logoClub");
            teamName.setText(title);
            teamDesc.setText(description);
            Glide.with(DetailClub.this)
                    .load(path)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(teamBadges);
            // and get whatever type user account id is
        }

        //Set up Realm
        Realm.init(DetailClub.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);


        bBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clubModel = new ModelDataFavourite();
                clubModel.setTeamDesc(description);
                clubModel.setTeamName(title);
                clubModel.setBadge(path);
                clubModel.setTeamYear(date);

                realmHelper = new RealmHelper(realm);
                realmHelper.save(clubModel);
            }
        });

    }
}