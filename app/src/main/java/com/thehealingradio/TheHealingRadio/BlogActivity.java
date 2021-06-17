package com.thehealingradio.TheHealingRadio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BlogActivity extends AppCompatActivity {

    private String title, blog, image, date, time;

    private ImageView bImage;
    private TextView bcontent, btitle, bdate, btime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        bImage = findViewById(R.id.bImage);
        bcontent = findViewById(R.id.bcontent);
        btitle = findViewById(R.id.btitle);
        bdate = findViewById(R.id.bdate);
        btime = findViewById(R.id.btime);


        title = getIntent().getStringExtra("title");
        blog = getIntent().getStringExtra("blog");
        image = getIntent().getStringExtra("image");
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");

        try {
            if(image != null)
                Picasso.get().load(image).into(bImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        btitle.setText(title);
        bcontent.setText(blog);
        bdate.setText(date);
        btime.setText(time);

        bImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BlogActivity.this, ImageActivity.class);
                intent.putExtra("image", image);
                startActivity(intent);
            }
        });

    }
}