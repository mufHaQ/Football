package com.mufhaq.football;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListDataEventsPast extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DataAdapterEventsPast dataAdapterEventsPast;
    private ArrayList<ModelEventsPast> DataArrayList; //kit add kan ke adapter
    TextView tvnodata;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        recyclerView = (RecyclerView) findViewById(R.id.rvdata);
        tvnodata = (TextView) findViewById(R.id.tvnodata);
        dialog = new ProgressDialog(ListDataEventsPast.this);
        tvnodata.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        //addData();
        addDataOnline();
    }

    void addDataOnline(){
        //kasih loading
        dialog.setMessage("Sedang memproses data");
        dialog.show();
        // background process
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328")
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
                        ModelEventsPast modelku;
                        try {
                            Log.d("hasiljson", "onResponse: " + response.toString());
                            JSONArray jsonArray = response.getJSONArray("events");
                            Log.d("hasiljson2", "onResponse: " + jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                modelku = new ModelEventsPast();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                modelku.setId(jsonObject.getInt("idEvent"));
                                modelku.setSeason(jsonObject.getString("strSeason"));
                                modelku.setHome(jsonObject.getString("strHomeTeam"));
                                modelku.setAway(jsonObject.getString("strAwayTeam"));
                                modelku.setDate(jsonObject.getString("dateEvent"));
                                modelku.setTime(jsonObject.getString("strTime"));
                                DataArrayList.add(modelku);
                            }
                            //untuk handle click
                            dataAdapterEventsPast = new DataAdapterEventsPast(DataArrayList, new DataAdapterEventsPast.Callback() {
                                @Override
                                public void onClick(int position) {

                                }

                                @Override
                                public void test() {

                                }
                            });
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListDataEventsPast.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(dataAdapterEventsPast);
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