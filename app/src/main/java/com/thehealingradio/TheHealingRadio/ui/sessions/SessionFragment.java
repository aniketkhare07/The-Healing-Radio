package com.thehealingradio.TheHealingRadio.ui.sessions;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thehealingradio.TheHealingRadio.Adapters.SessionAdapter;
import com.thehealingradio.TheHealingRadio.Adapters.SessionFragAdapter;
import com.thehealingradio.TheHealingRadio.Adapters.SongFragAdapter;
import com.thehealingradio.TheHealingRadio.DataClasses.SessionData;
import com.thehealingradio.TheHealingRadio.DataClasses.SongData;
import com.thehealingradio.TheHealingRadio.R;

import java.util.ArrayList;
import java.util.List;

public class SessionFragment extends Fragment {

    private RecyclerView Sessionrecycle, topSongs;

    private Spinner category;

    private List<SessionData> lists;
    private List<SongData> list;
    private SessionFragAdapter adapters;
    private SongFragAdapter adapter;
    private LinearLayoutManager manager;

    String[] items = {"Previous Sessions", "Healing Top Playlist"};
    private static final String session = "Previous Sessions", playlist = "Healing Top Playlist";
    private ArrayAdapter arrayAdapter;

    private DatabaseReference reference, dbref;
    private String imageCat, white = "@color/white";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_session, container, false);

        reference = FirebaseDatabase.getInstance().getReference().child("Sessions");
        dbref = FirebaseDatabase.getInstance().getReference().child("Playlist");

        Sessionrecycle = view.findViewById(R.id.Sessionrecycle);
        topSongs = view.findViewById(R.id.topSongs);
        category = view.findViewById(R.id.category);

        arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.spinner_items, R.layout.color_spinner);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        category.setAdapter(arrayAdapter);

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageCat = category.getSelectedItem().toString();

                switch (imageCat) {

                    case session:
                        topSongs.setVisibility(View.GONE);
                        Sessionrecycle.setVisibility(View.VISIBLE);
                        getSession();
                        break;
                    case playlist:
                        Sessionrecycle.setVisibility(View.GONE);
                        topSongs.setVisibility(View.VISIBLE);
                        getSongs();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + imageCat);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getSongs();

        return view;
    }

    private void getSongs() {

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    SongData data = dataSnapshot.getValue(SongData.class);
                    list.add(data);
                }
                List<SongData> listp = new ArrayList<>();
                int j = list.size() - 1;

                for (int k = 0; k < list.size(); k++) {
                    if (j < 0) {
                        break;
                    }
                    SongData item = list.get(j);
                    listp.add(item);
                    j--;
                }
                adapter = new SongFragAdapter(getContext(), listp);
                adapter.notifyDataSetChanged();
                topSongs.setHasFixedSize(true);
                manager = new LinearLayoutManager(getContext());
                topSongs.setLayoutManager(manager);
                topSongs.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getSession() {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lists = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    SessionData data = dataSnapshot.getValue(SessionData.class);
                    lists.add(data);
                }
                List<SessionData> list = new ArrayList<>();
                int j = lists.size() - 1;

                for (int k = 0; k < lists.size(); k++) {
                    if (j < 0) {
                        break;
                    }
                    SessionData item = lists.get(j);
                    list.add(item);
                    j--;
                }
                adapters = new SessionFragAdapter(getContext(), list);
                adapters.notifyDataSetChanged();
                Sessionrecycle.setHasFixedSize(true);
                manager = new LinearLayoutManager(getContext());
                Sessionrecycle.setLayoutManager(manager);
                Sessionrecycle.setAdapter(adapters);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}