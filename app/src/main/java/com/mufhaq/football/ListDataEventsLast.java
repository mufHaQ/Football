//package com.mufhaq.football;
//
//import android.app.ProgressDialog;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.TextView;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.androidnetworking.AndroidNetworking;
//import com.androidnetworking.common.Priority;
//import com.androidnetworking.error.ANError;
//import com.androidnetworking.interfaces.JSONObjectRequestListener;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
//public class ListDataEventsLast extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private DataAdapterEventsLast dataAdapterEventsLast;
//    private ArrayList<ModelEventsLast> DataArrayList; //kit add kan ke adapter
//    TextView tvnodata;
//    ProgressDialog dialog;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_data);
//        recyclerView = (RecyclerView) findViewById(R.id.rvdataDetail);
//        tvnodata = (TextView) findViewById(R.id.tvnodataDetail);
//        dialog = new ProgressDialog(ListDataEventsLast.this);
//        tvnodata.setVisibility(View.GONE);
//        recyclerView.setVisibility(View.VISIBLE);
//        //addData();
//        addDataOnline();
//    }
//
//    void addDataOnline(){
//        //kasih loading
//        dialog.setMessage("Sedang memproses data");
//        dialog.show();
//        // background process
//        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/eventslast.php?id="+id)
//                .setTag("test")
//                .setPriority(Priority.LOW)
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        // do anything with response
//                        Log.d("hasiljson", "onResponse: " + response.toString());
//                        //jika sudah berhasil debugm lanjutkan code dibawah ini
//                        DataArrayList = new ArrayList<>();
//                        ModelEventsLast modelku;
//                        try {
//                            Log.d("hasiljson", "onResponse: " + response.toString());
//                            JSONArray jsonArray = response.getJSONArray("results");
//                            Log.d("hasiljson2", "onResponse: " + jsonArray.toString());
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                modelku = new ModelEventsLast();
//                                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                                modelku.setId(jsonObject.getInt("idEvent"));
//                                modelku.setHomeScore(jsonObject.getString("intHomeScore"));
//                                modelku.setAwayScore(jsonObject.getString("intAwayScore"));
//                                modelku.setHome(jsonObject.getString("strHomeTeam"));
//                                modelku.setAway(jsonObject.getString("strAwayTeam"));
//                                modelku.setDate(jsonObject.getString("dateEvent"));
//                                modelku.setTime(jsonObject.getString("strTime"));
//                                DataArrayList.add(modelku);
//                            }
//                            //untuk handle click
//                            dataAdapterEventsLast = new DataAdapterEventsLast(DataArrayList, new DataAdapterEventsLast.Callback() {
//                                @Override
//                                public void onClick(int position) {
//
//                                }
//
//                                @Override
//                                public void test() {
//
//                                }
//                            });
//                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListDataEventsLast.this);
//                            recyclerView.setLayoutManager(layoutManager);
//                            recyclerView.setAdapter(dataAdapterEventsLast);
//                            if (dialog.isShowing()) {
//                                dialog.dismiss();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            if (dialog.isShowing()) {
//                                dialog.dismiss();
//                            }
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(ANError error) {
//                        // handle error
//                        Log.d("errorku", "onError errorCode : " + error.getErrorCode());
//                        Log.d("errorku", "onError errorBody : " + error.getErrorBody());
//                        Log.d("errorku", "onError errorDetail : " + error.getErrorDetail());
//                    }
//                });
//    }
//}
