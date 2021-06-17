package com.thehealingradio.TheHealingRadio.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thehealingradio.TheHealingRadio.Adapters.BlogAdapter;
import com.thehealingradio.TheHealingRadio.Adapters.ImageAdapter;
import com.thehealingradio.TheHealingRadio.Adapters.SessionAdapter;
import com.thehealingradio.TheHealingRadio.Adapters.SongAdapter;
import com.thehealingradio.TheHealingRadio.DataClasses.BlogData;
import com.thehealingradio.TheHealingRadio.DataClasses.ImageData;
import com.thehealingradio.TheHealingRadio.DataClasses.QuoteData;
import com.thehealingradio.TheHealingRadio.DataClasses.SessionData;
import com.thehealingradio.TheHealingRadio.DataClasses.SongData;
import com.thehealingradio.TheHealingRadio.LiveRadio;
import com.thehealingradio.TheHealingRadio.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.widget.LinearLayout.HORIZONTAL;

public class HomeFragment extends Fragment {

    private RecyclerView healingBlogs, healingImages, healingSessions, todaysTrending;

    private List<BlogData> listb;
    private List<ImageData> listi;
    private List<SessionData> lists;
    private List<SongData> listso;

    private BlogAdapter adapterb;
    private ImageAdapter adapteri;
    private ImageView liveRadio;
    private SessionAdapter adapters;
    private SongAdapter adapterso;

    private LinearLayoutManager manager,manager2, manager3, manager4;

    private DatabaseReference reference, dbref, ref, db, songs;
    
    private TextView quote, quoter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        reference = FirebaseDatabase.getInstance().getReference().child("Blog");
        dbref = FirebaseDatabase.getInstance().getReference().child("Gallery").child("Healing Images");
        ref = FirebaseDatabase.getInstance().getReference().child("Sessions");
        db = FirebaseDatabase.getInstance().getReference().child("Quote");
        songs = FirebaseDatabase.getInstance().getReference().child("Playlist");

        healingBlogs = view.findViewById(R.id.healingBlogs);
        healingImages = view.findViewById(R.id.healingImages);
        healingSessions = view.findViewById(R.id.healingSessions);
        todaysTrending = view.findViewById(R.id.todaysTrending);
        quote = view.findViewById(R.id.quote);
        quoter = view.findViewById(R.id.quoter);

        liveRadio = view.findViewById(R.id.liveRadio);
        
        updateQuote();
        getBlogs();
        getImages();
        getSession();
        getSongs();

        liveRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LiveRadio.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void updateQuote() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                QuoteData data = null;
                try {
                    data = snapshot.getValue(QuoteData.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                assert data != null;
                quote.setText(data.getQuote());
                quoter.setText(data.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Something went Wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void getBlogs() {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listb = new ArrayList<>();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    BlogData data = dataSnapshot.getValue(BlogData.class);
                    listb.add(data);
                }
                List<BlogData> list = new ArrayList<>();
                int j=listb.size()-1;

                for(int k = 0; k<5; k++){
                    if(j<0){
                        break;
                    }
                    BlogData item = listb.get(j);
                    list.add(item);
                    j--;
                }
                adapterb = new BlogAdapter(getContext(), list);
                adapterb.notifyDataSetChanged();
                healingBlogs.setHasFixedSize(true);
                manager = new LinearLayoutManager(getContext());
                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                healingBlogs.setLayoutManager(manager);
                healingBlogs.setAdapter(adapterb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getImages() {

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listi = new ArrayList<>();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    ImageData data = dataSnapshot.getValue(ImageData.class);
                    listi.add(data);
                }
                List<ImageData> list = new ArrayList<>();
                int j=listi.size()-1;

                for(int k = 0; k<5; k++){
                    if(j<0){
                        break;
                    }
                    ImageData item = listi.get(j);
                    list.add(item);
                    j--;
                }
                adapteri = new ImageAdapter(getContext(), list);
                adapteri.notifyDataSetChanged();
                healingImages.setHasFixedSize(true);
                manager2 = new LinearLayoutManager(getContext());
                manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
                healingImages.setLayoutManager(manager2);
                healingImages.setAdapter(adapteri);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSession() {

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lists = new ArrayList<>();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    SessionData data = dataSnapshot.getValue(SessionData.class);
                    lists.add(data);
                }
                List<SessionData> list = new ArrayList<>();
                int j=lists.size()-1;

                for(int k = 0; k<5; k++){
                    if(j<0){
                        break;
                    }
                    SessionData item = lists.get(j);
                    list.add(item);
                    j--;
                }
                adapters = new SessionAdapter(getContext(), list);
                adapters.notifyDataSetChanged();
                healingSessions.setHasFixedSize(true);
                manager3 = new LinearLayoutManager(getContext());
                manager3.setOrientation(LinearLayoutManager.HORIZONTAL);
                healingSessions.setLayoutManager(manager3);
                healingSessions.setAdapter(adapters);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSongs() {

        songs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listso = new ArrayList<>();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    SongData data = dataSnapshot.getValue(SongData.class);
                    listso.add(data);
                }
                List<SongData> list = new ArrayList<>();
                int j=listso.size()-1;

                for(int k = 0; k<3; k++){
                    if(j<0){
                        break;
                    }
                    SongData item = listso.get(j);
                    list.add(item);
                    j--;
                }
                adapterso = new SongAdapter(getContext(), list);
                adapterso.notifyDataSetChanged();
                healingSessions.setHasFixedSize(true);
                manager4 = new LinearLayoutManager(getContext());
                todaysTrending.setLayoutManager(manager4);
                todaysTrending.setAdapter(adapterso);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}