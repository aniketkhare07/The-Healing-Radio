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
import com.thehealingradio.TheHealingRadio.BlogActivity;
import com.thehealingradio.TheHealingRadio.DataClasses.BlogData;
import com.thehealingradio.TheHealingRadio.R;

import java.util.List;

public class BlogFragAdapter extends RecyclerView.Adapter<BlogFragAdapter.BlogFragViewAdapter> {

    private Context context;
    private List<BlogData> list;

    public BlogFragAdapter(Context context, List<BlogData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public BlogFragAdapter.BlogFragViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.blog_frag, parent, false);
        return new BlogFragAdapter.BlogFragViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogFragAdapter.BlogFragViewAdapter holder, int position) {

        BlogData item = list.get(position);


        holder.title.setText(item.getTitle());
        holder.time.setText(item.getTime());
        holder.date.setText(item.getDate());
        try {
            if (item.getImage() != null)
                Picasso.get().load(item.getImage()).into(holder.image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.blog_frag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BlogActivity.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("blog", item.getBlog());
                intent.putExtra("image", item.getImage());
                intent.putExtra("date", item.getDate());
                intent.putExtra("time", item.getTime());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BlogFragViewAdapter extends RecyclerView.ViewHolder {

        private TextView title, date, time;
        private ImageView image;
        private LinearLayoutCompat blog_frag;

        public BlogFragViewAdapter(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            image = itemView.findViewById(R.id.image);
            blog_frag = itemView.findViewById(R.id.blog_frag);

        }
    }
}
