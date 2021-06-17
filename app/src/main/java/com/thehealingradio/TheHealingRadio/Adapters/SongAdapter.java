package com.thehealingradio.TheHealingRadio.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.thehealingradio.TheHealingRadio.DataClasses.SongData;
import com.thehealingradio.TheHealingRadio.R;
import com.thehealingradio.TheHealingRadio.SessionActivity;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewAdapter> {

    private Context context;
    private List<SongData> list;

    public SongAdapter(Context context, List<SongData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SongViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_card, parent, false);
        return new SongAdapter.SongViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewAdapter holder, int position) {

        SongData item = list.get(position);

        holder.songName.setText(item.getTitle());
        holder.songArtist.setText(item.getArtist());

        try {
            Picasso.get().load(item.getThumb()).into(holder.song_image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.song_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SessionActivity.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("artist", item.getArtist());
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

    public class SongViewAdapter extends RecyclerView.ViewHolder {

        private ImageView song_image;
        private TextView songName, songArtist;
        private LinearLayoutCompat song_card;

        public SongViewAdapter(@NonNull View itemView) {
            super(itemView);

            song_image = itemView.findViewById(R.id.song_image);
            songName = itemView.findViewById(R.id.songName);
            songArtist = itemView.findViewById(R.id.songArtist);
            song_card = itemView.findViewById(R.id.song_card);
        }
    }
}
