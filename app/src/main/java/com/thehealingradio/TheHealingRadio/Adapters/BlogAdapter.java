package com.thehealingradio.TheHealingRadio.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.thehealingradio.TheHealingRadio.BlogActivity;
import com.thehealingradio.TheHealingRadio.DataClasses.BlogData;
import com.thehealingradio.TheHealingRadio.ImageActivity;
import com.thehealingradio.TheHealingRadio.R;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.BlogViewAdapter> {

    private Context context;
    private List<BlogData> list;


    public BlogAdapter(Context context, List<BlogData> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public BlogViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.blog_card, parent, false);
        return new BlogViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogViewAdapter holder, int position) {
        BlogData item = list.get(position);


        holder.blog_title.setText(item.getTitle());
        try {
            if (item.getImage() != null)
                Picasso.get().load(item.getImage()).into(holder.blog_image);
        } catch (Exception e) {
            e.printStackTrace();
        }


        holder.blog_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BlogActivity.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("blog", item.getBlog());
                intent.putExtra("image", item.getImage());
                intent.putExtra("date", item.getDate());
                intent.putExtra("time", item.getTime());
                context.startActivity(intent);
//                Toast.makeText(context, "dikhega bhai ruk ja zara", Toast.LENGTH_SHORT).show();
            }
        });
        holder.blog_image.setOnClickListener(new View.OnClickListener() {
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

    public class BlogViewAdapter extends RecyclerView.ViewHolder {

        private TextView blog_title;
        private ImageView blog_image;

        public BlogViewAdapter(@NonNull View itemView) {
            super(itemView);

            blog_title = itemView.findViewById(R.id.blog_title);
            blog_image = itemView.findViewById(R.id.blog_image);

        }
    }
}
