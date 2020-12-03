package com.mufhaq.football;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import org.w3c.dom.Text;

public class DetailFavourite extends AppCompatActivity {

    Bundle extras;
    String id, title, description, date, path, alternate, country;

    TextView judul, desc, countryFav, yearFav, alternateFav;
    ImageView ivposter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favourite);
        judul = (TextView) findViewById(R.id.teamNAmeDetailFav);
        desc = (TextView) findViewById(R.id.teamDescFav);
        countryFav = (TextView) findViewById(R.id.teamCountryDetailFav);
        yearFav = (TextView) findViewById(R.id.formedYearFav);
        alternateFav = (TextView) findViewById(R.id.teamAlternateNameFav);
        ivposter = (ImageView) findViewById(R.id.teamBadgesDetailFav);
        extras = getIntent().getExtras();
        desc.setMovementMethod(new ScrollingMovementMethod());


        if (extras != null) {
            title = extras.getString("name");
            id = extras.getString("id");
            description = extras.getString("desc");
            path = extras.getString("badge");
            date = extras.getString("year");
            alternate = extras.getString("alternate");
            country = extras.getString("country");

            judul.setText(title);
            desc.setText(description);
            countryFav.setText(country);
            alternateFav.setText(alternate);
            yearFav.setText(date);

            Glide.with(DetailFavourite.this)
                    .load(path)
//                    .override(Target.SIZE_ORIGINAL)
                    .apply(new RequestOptions().override(400))
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivposter);
            // and get whatever type user account id is
        }
    }
}