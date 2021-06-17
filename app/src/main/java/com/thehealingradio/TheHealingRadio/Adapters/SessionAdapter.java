package com.thehealingradio.TheHealingRadio.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.thehealingradio.TheHealingRadio.BlogActivity;
import com.thehealingradio.TheHealingRadio.DataClasses.SessionData;
import com.thehealingradio.TheHealingRadio.R;
import com.thehealingradio.TheHealingRadio.SessionActivity;

import java.util.List;

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.SessionViewAdapter> {

    private Context context;
    private List<SessionData> list;


    public SessionAdapter(Context context, List<SessionData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SessionViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.session_card, parent, false);
        return new SessionViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SessionViewAdapter holder, int position) {
        SessionData item = list.get(position);

        holder.session_title.setText(item.getTitle());
        holder.session_date.setText(item.getDate());
        holder.session_time.setText(item.getTime());

        try {
            Picasso.get().load(item.getThumb()).into(holder.session_image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.sessionPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SessionActivity.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("url", item.getUrl());
                intent.putExtra("thumb", item.getThumb());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list.size() > 5) {
            return 5;
        } else {
            return list.size();
        }
    }

    public class SessionViewAdapter extends RecyclerView.ViewHolder {

        private LinearLayoutCompat sessionPlay;
        private ImageView session_image;
        private TextView session_title, session_date, session_time;


        public SessionViewAdapter(@NonNull View itemView) {
            super(itemView);

            sessionPlay = itemView.findViewById(R.id.sessionPlay);
            session_title = itemView.findViewById(R.id.session_title);
            session_image = itemView.findViewById(R.id.session_image);
            session_date = itemView.findViewById(R.id.session_date);
            session_time = itemView.findViewById(R.id.session_time);

        }
    }
}
