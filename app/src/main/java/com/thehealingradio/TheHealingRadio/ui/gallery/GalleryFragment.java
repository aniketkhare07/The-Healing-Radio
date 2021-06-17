package com.thehealingradio.TheHealingRadio.ui.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thehealingradio.TheHealingRadio.Adapters.GalleryFragAdapter;
import com.thehealingradio.TheHealingRadio.Adapters.ImageAdapter;
import com.thehealingradio.TheHealingRadio.DataClasses.ImageData;
import com.thehealingradio.TheHealingRadio.R;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    RecyclerView healRecycle, eventRecycle, otherRecycle;
    GalleryFragAdapter adapter;
    List<ImageData> list1, list2, list3;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        healRecycle = view.findViewById(R.id.healRecycle);
        eventRecycle = view.findViewById(R.id.eventRecycle);
        otherRecycle = view.findViewById(R.id.otherRecycle);

        reference = FirebaseDatabase.getInstance().getReference().child("Gallery");

        getHealImages();
        getEventImages();
        getOtherImages();

        return view;
    }

    private void getHealImages() {

    reference.child("Healing Images").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            list1 = new ArrayList<>();
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                ImageData data = dataSnapshot.getValue(ImageData.class);
                list1.add(data);
            }
            List<ImageData> list = new ArrayList<>();
            int j = list1.size() - 1;
            for (int k = 0; k <list1.size(); k++) {
                if (j < 0) {
                    break;
                }
                ImageData item = list1.get(j);
                list.add(item);
                j--;
            }

            adapter = new GalleryFragAdapter(getContext(), list);
            healRecycle.setLayoutManager(new GridLayoutManager(getContext(), 3));
            healRecycle.setAdapter(adapter);
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
    }

    private void getEventImages() {

    reference.child("Event").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            list2 = new ArrayList<>();
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                ImageData data = dataSnapshot.getValue(ImageData.class);
                list2.add(data);
            }
            List<ImageData> list = new ArrayList<>();
            int j = list2.size() - 1;
            for (int k = 0; k <list2.size(); k++) {
                if (j < 0) {
                    break;
                }
                ImageData item = list2.get(j);
                list.add(item);
                j--;
            }

            adapter = new GalleryFragAdapter(getContext(), list);
            eventRecycle.setLayoutManager(new GridLayoutManager(getContext(), 3));
            eventRecycle.setAdapter(adapter);
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
    }

    private void getOtherImages() {

    reference.child("Others").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            list3 = new ArrayList<>();
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                ImageData data = dataSnapshot.getValue(ImageData.class);
                list3.add(data);
            }
            List<ImageData> list = new ArrayList<>();
            int j = list3.size() - 1;
            for (int k = 0; k <list3.size(); k++) {
                if (j < 0) {
                    break;
                }
                ImageData item = list3.get(j);
                list.add(item);
                j--;
            }

            adapter = new GalleryFragAdapter(getContext(), list);
            otherRecycle.setLayoutManager(new GridLayoutManager(getContext(), 3));
            otherRecycle.setAdapter(adapter);
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
    }
}