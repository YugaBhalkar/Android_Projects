package com.example.demoapp.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoapp.R;
import com.example.demoapp.RoomDatabase.Artical;

import java.util.List;

public class PostFragment extends Fragment {

    View v;
    private RecyclerView recyclerView;
    List<Artical> postList;

    public PostFragment() {
    }

    public PostFragment(List<Artical> postList) {
        this.postList = postList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.post_fragment, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.postList);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), postList, true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }

}
