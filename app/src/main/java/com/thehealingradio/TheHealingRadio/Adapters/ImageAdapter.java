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
import com.thehealingradio.TheHealingRadio.BlogActivity;
import com.thehealingradio.TheHealingRadio.DataClasses.ImageData;
import com.thehealingradio.TheHealingRadio.ImageActivity;
import com.thehealingradio.TheHealingRadio.R;

import java.util.List;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewAdapter> {

    private Context context;
    private List<ImageData> list;

    private String category, key, url;


    public ImageAdapter(Context context, List<ImageData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ImageViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.image_card, parent, false);
        return new ImageViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewAdapter holder, int position) {
        ImageData item = list.get(position);


        try {
            Picasso.get().load(item.getImage()).into(holder.healimg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.healimg.setOnClickListener(new View.OnClickListener() {
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
        if (list.size() > 5) {
            return 5;
        } else {
            return list.size();
        }
    }

    public class ImageViewAdapter extends RecyclerView.ViewHolder {

        private ImageView healimg;

        public ImageViewAdapter(@NonNull View itemView) {
            super(itemView);

            healimg = itemView.findViewById(R.id.healimg);

        }
    }
}
