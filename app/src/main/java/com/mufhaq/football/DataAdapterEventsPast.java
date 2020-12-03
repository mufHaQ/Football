package com.mufhaq.football;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapterEventsPast extends RecyclerView.Adapter<DataAdapterEventsPast.DatakuViewHolder>{
    private ArrayList<ModelEventsPast> dataList;
    private DataAdapterEventsPast.Callback callback;
    View viewku;
    int posku;

    interface Callback {
        void onClick(int position);
        void test();
    }

    public DataAdapterEventsPast(ArrayList<ModelEventsPast> dataList, Callback callback) {
        this.callback = callback;
        this.dataList = dataList;
        Log.d("makanan", "MahasiswaAdapter: "+dataList.size()+"");
    }

    @Override
    public DatakuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_events_past, parent, false);
        return new DataAdapterEventsPast.DatakuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DatakuViewHolder holder, final int position) {
        holder.home.setText(dataList.get(position).getHome());
        holder.away.setText(dataList.get(position).getAway());
        holder.time.setText(dataList.get(position).getTime());
        holder.date.setText(dataList.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class DatakuViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        private TextView home, away, time, date;
        CardView card;
        ImageView ivprofile;

        public DatakuViewHolder(View itemView) {
            super(itemView);
            viewku=itemView;
            card = (CardView) itemView.findViewById(R.id.cardEvent);
            home = (TextView) itemView.findViewById(R.id.homeTeamPast);
            away = (TextView) itemView.findViewById(R.id.awayTeamPast);
            time = (TextView) itemView.findViewById(R.id.time);
            date = (TextView) itemView.findViewById(R.id.date);

            itemView.setOnCreateContextMenuListener(this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClick(getAdapterPosition());
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//            MenuItem Edit = menu.add(Menu.NONE, 1, 1, "Edit");
//            MenuItem Delete = menu.add(Menu.NONE, 2, 2, "Delete");
//            posku=getAdapterPosition();
//            Edit.setOnMenuItemClickListener(onEditMenu);
//            Delete.setOnMenuItemClickListener(onEditMenu);
        }

    }
}
