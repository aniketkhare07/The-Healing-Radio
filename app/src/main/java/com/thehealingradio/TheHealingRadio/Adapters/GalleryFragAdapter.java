package com.thehealingradio.TheHealingRadio.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.thehealingradio.TheHealingRadio.DataClasses.ImageData;
import com.thehealingradio.TheHealingRadio.ImageActivity;
import com.thehealingradio.TheHealingRadio.R;

import java.util.List;

public class GalleryFragAdapter extends RecyclerView.Adapter<GalleryFragAdapter.GalleryFragViewAdapter> {

    private Context context;
    private List<ImageData> images;

    public GalleryFragAdapter(Context context, List<ImageData> images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public GalleryFragViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_frag, parent, false);
        return new GalleryFragAdapter.GalleryFragViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryFragViewAdapter holder, int position) {
        ImageData item = images.get(position);

        Picasso.get().load(item.getImage()).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImageActivity.class);
                intent.putExtra("image", item.getImage());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class GalleryFragViewAdapter extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public GalleryFragViewAdapter(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

}
