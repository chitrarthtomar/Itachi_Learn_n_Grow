package com.example.hp15p077tx.courses.UI;


import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp15p077tx.courses.Models.ListItems;
import com.example.hp15p077tx.courses.Models.Topics;
import com.example.hp15p077tx.courses.R;
import com.example.hp15p077tx.courses.adapters.MyApplication;
import com.example.hp15p077tx.courses.adapters.topic_Adapter;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopicFragment extends Fragment implements topic_Adapter.ItemClickCallback {

    private RecyclerView recyclerView;
    private topic_Adapter topicAdapter;
    private ArrayList listData;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor ;


    public TopicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_topic, container, false);
        Context context = getContext();
        listData = (ArrayList)Topics.getListData();
        recyclerView = (RecyclerView)v.findViewById(R.id.topic_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        topicAdapter = new topic_Adapter(Topics.getListData(),context,"topic");
        recyclerView.setAdapter(topicAdapter);
        topicAdapter.setItemClickCallback(this);
        return v;
    }

    @Override
    public void onItemClick(int p) {
        ListItems item = (ListItems) listData.get(p);
        Intent intent =new Intent(getContext(),chapterList.class);
        intent.putExtra("topic",item.getTitle());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        editor = sharedPreferences.edit();

        editor.putString(item.getTitle().toLowerCase(),"STARTED");
        editor.apply();
        //Log.d(TAG, "onItemClick: ");
        startActivity(intent);
    }

    @Override
    public void onSecondaryIconClick(int p) {

    }
}
