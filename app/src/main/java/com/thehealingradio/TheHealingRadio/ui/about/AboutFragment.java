package com.thehealingradio.TheHealingRadio.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.thehealingradio.TheHealingRadio.R;

public class AboutFragment extends Fragment {

    private ImageView instagram;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        instagram = view.findViewById(R.id.instagram);

        instagram.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/the_healing_radio/"));
                    startActivity(intent);
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/the_healing_radio/")));
                }

            }
        });

        return view;
    }
}