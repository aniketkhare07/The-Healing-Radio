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
import com.thehealingradio.TheHealingRadio.DataClasses.SongData;
import com.thehealingradio.TheHealingRadio.R;
import com.thehealingradio.TheHealingRadio.SessionActivity;

import java.util.List;

public class SongFragAdapter extends RecyclerView.Adapter<SongFragAdapter.SongFragViewAdapter> {

    private Context context;
    private List<SongData> list;

    public SongFragAdapter(Context context, List<SongData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SongFragViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_frag, parent, false);
        return new SongFragAdapter.SongFragViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongFragViewAdapter holder, int position) {

        SongData item = list.get(position);

        holder.songtitle.setText(item.getTitle());
        holder.artist.setText(item.getArtist());

        try {
            Picasso.get().load(item.getThumb()).into(holder.songThumbnail);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.song_frag.setOnClickListener(new View.OnClickListener() {
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

    public class SongFragViewAdapter extends RecyclerView.ViewHolder {

        private ImageView songThumbnail;
        private LinearLayoutCompat song_frag;
        private TextView songtitle, artist;

        public SongFragViewAdapter(@NonNull View itemView) {
            super(itemView);

            songThumbnail = itemView.findViewById(R.id.songThumbnail);
            song_frag = itemView.findViewById(R.id.song_frag);
            songtitle = itemView.findViewById(R.id.songtitle);
            artist = itemView.findViewById(R.id.artist);
        }
    }
}
