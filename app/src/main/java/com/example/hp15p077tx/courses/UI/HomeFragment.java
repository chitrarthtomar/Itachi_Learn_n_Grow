package com.example.hp15p077tx.courses.UI;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp15p077tx.courses.Models.ListItems;
import com.example.hp15p077tx.courses.Models.Topics;
import com.example.hp15p077tx.courses.R;
import com.example.hp15p077tx.courses.adapters.topic_Adapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements topic_Adapter.ItemClickCallback{

    private RecyclerView recyclerView;
    private topic_Adapter topicAdapter;
    private ArrayList listData;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        Context context = getContext();
        listData = (ArrayList)Topics.getListData();
        recyclerView = (RecyclerView)v.findViewById(R.id.curr_topic);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        topicAdapter = new topic_Adapter(Topics.getListData(),context,"home");
        recyclerView.setAdapter(topicAdapter);
        topicAdapter.setItemClickCallback(this);
        return v;
    }

    @Override
    public void onItemClick(int p) {
        ListItems item = (ListItems) listData.get(p);
        Intent intent =new Intent(getContext(),chapterList.class);
        intent.putExtra("topic",item.getTitle());
        startActivity(intent);
    }

    @Override
    public void onSecondaryIconClick(int p) {

    }
}
