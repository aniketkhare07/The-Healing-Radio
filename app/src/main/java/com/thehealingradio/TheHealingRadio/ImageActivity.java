package com.thehealingradio.TheHealingRadio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class ImageActivity extends AppCompatActivity {

    private PhotoView imageView;

    private String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imageView = findViewById(R.id.imageView);

        image = getIntent().getStringExtra("image");

        try {
            if(image != null)
                Picasso.get().load(image).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}