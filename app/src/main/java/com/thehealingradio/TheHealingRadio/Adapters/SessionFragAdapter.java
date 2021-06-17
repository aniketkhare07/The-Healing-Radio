package com.thehealingradio.TheHealingRadio.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.thehealingradio.TheHealingRadio.DataClasses.SessionData;
import com.thehealingradio.TheHealingRadio.R;
import com.thehealingradio.TheHealingRadio.SessionActivity;

import java.util.List;

public class SessionFragAdapter extends RecyclerView.Adapter<SessionFragAdapter.SessionFragViewAdapter> {

    private Context context;
    private List<SessionData> list;

    public SessionFragAdapter(Context context, List<SessionData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SessionFragAdapter.SessionFragViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.session_frag, parent, false);
        return new SessionFragAdapter.SessionFragViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SessionFragAdapter.SessionFragViewAdapter holder, int position) {
        SessionData item = list.get(position);

        holder.sessiontitle.setText(item.getTitle());
        holder.sessiondate.setText(item.getDate());
        holder.sessiontime.setText(item.getTime());

        try {
            Picasso.get().load(item.getThumb()).into(holder.sessionThumbnail);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.session_frag.setOnClickListener(new View.OnClickListener() {
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
        return list.size();
    }

    public class SessionFragViewAdapter extends RecyclerView.ViewHolder {

        private ImageView sessionThumbnail;
        private LinearLayoutCompat session_frag;
        private TextView sessiontitle, sessiondate, sessiontime;

        public SessionFragViewAdapter(@NonNull View itemView) {
            super(itemView);

            session_frag = itemView.findViewById(R.id.session_frag);
            sessionThumbnail = itemView.findViewById(R.id.sessionThumbnail);
            sessiontitle = itemView.findViewById(R.id.sessiontitle);
            sessiondate = itemView.findViewById(R.id.sessiondate);
            sessiontime = itemView.findViewById(R.id.sessiontime);

        }
    }
}
