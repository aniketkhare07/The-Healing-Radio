package com.thehealingradio.TheHealingRadio.ui.blog;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.thehealingradio.TheHealingRadio.Adapters.BlogAdapter;
import com.thehealingradio.TheHealingRadio.Adapters.BlogFragAdapter;
import com.thehealingradio.TheHealingRadio.DataClasses.BlogData;
import com.thehealingradio.TheHealingRadio.R;

import java.util.ArrayList;
import java.util.List;

public class BlogFragment extends Fragment {

    private RecyclerView blogrecycle;

    private BlogFragAdapter adapterb;
    private List<BlogData> listb;
    private LinearLayoutManager manager;

    private DatabaseReference reference;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blog, container, false);

        reference = FirebaseDatabase.getInstance().getReference().child("Blog");

        blogrecycle = view.findViewById(R.id.blogrecycle);

        getBlogs();


        return view;
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

                for(int k = 0; k<listb.size(); k++){
                    if(j<0){
                        break;
                    }
                    BlogData item = listb.get(j);
                    list.add(item);
                    j--;
                }
                adapterb = new BlogFragAdapter(getContext(), list);
                adapterb.notifyDataSetChanged();
                blogrecycle.setHasFixedSize(true);
                manager = new LinearLayoutManager(getContext());
                blogrecycle.setLayoutManager(manager);
                blogrecycle.setAdapter(adapterb);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}