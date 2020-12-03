package com.mufhaq.football;

import android.net.wifi.hotspot2.pps.HomeSp;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class DataAdapterEventsLast extends RecyclerView.Adapter<DataAdapterEventsLast.DatakuViewHolder>{
    private ArrayList<ModelEventsLast> dataList;
    private DataAdapterEventsLast.Callback callback;
    View viewku;
    int posku;

    interface Callback {
        void onClick(int position);
        void test();
    }

    public DataAdapterEventsLast(ArrayList<ModelEventsLast> dataList, Callback callback) {
        this.callback = callback;
        this.dataList = dataList;
        Log.d("makanan", "MahasiswaAdapter: "+dataList.size()+"");
    }

    @Override
    public DatakuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_events_last, parent, false);
        return new DataAdapterEventsLast.DatakuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DatakuViewHolder holder, final int position) {
        holder.time.setText(dataList.get(position).getTime());
        holder.date.setText(dataList.get(position).getDate());
        holder.homeScore.setText(dataList.get(position).getHomeScore());
        holder.awayScore.setText(dataList.get(position).getAwayScore());
        holder.event.setText(dataList.get(position).getStrEvent());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class DatakuViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        private TextView time, date, awayScore, homeScore, event;
        CardView card;

        public DatakuViewHolder(View itemView) {
            super(itemView);
            viewku=itemView;
            card = (CardView) itemView.findViewById(R.id.cardEventLast);
            time = (TextView) itemView.findViewById(R.id.timeLast);
            date = (TextView) itemView.findViewById(R.id.dateLast);
            awayScore = (TextView) itemView.findViewById(R.id.awayScore);
            homeScore = (TextView) itemView.findViewById(R.id.homeScore);
            event = (TextView) itemView.findViewById(R.id.event);

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
