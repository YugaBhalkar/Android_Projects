package com.example.demoapp.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoapp.R;
import com.example.demoapp.RoomDatabase.Artical;
import com.example.demoapp.RoomDatabase.ArticalDataBase;

import java.util.List;

public class FavFragment extends Fragment {

    View v;
    private RecyclerView recyclerView;
    List<Artical> postList;
    ArticalDataBase articalDataBase;

    public FavFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fav_fragment, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.favList);
        articalDataBase = ArticalDataBase.getInstance(getActivity());
        articalDataBase.articalDao().getFavArticals("2").observe(getActivity(), new Observer<List<Artical>>() {
            @Override
            public void onChanged(List<Artical> articals) {
                postList = articals;
                RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), postList, false);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(recyclerViewAdapter);
                recyclerViewAdapter.setArticals();
            }
        });

        return v;
    }

}
