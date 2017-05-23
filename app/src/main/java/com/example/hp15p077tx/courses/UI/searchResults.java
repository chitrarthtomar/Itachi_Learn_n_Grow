package com.example.hp15p077tx.courses.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.example.hp15p077tx.courses.Models.content_itemlist;
import com.example.hp15p077tx.courses.Models.content_of_topic;
import com.example.hp15p077tx.courses.R;
import com.example.hp15p077tx.courses.adapters.content_Adapter;

import java.util.ArrayList;

public class searchResults extends AppCompatActivity implements content_Adapter.ItemClickCallback{
    private RecyclerView recyclerView;
    private content_Adapter contentAdapter;
    private ArrayList listData;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        //action bar
        linearLayout = (LinearLayout)findViewById(R.id.back_button);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //done action bar
        Intent i = getIntent();
        String query = i.getStringExtra("Query");
        listData = (ArrayList) content_of_topic.searchListData(query);

        recyclerView = (RecyclerView)findViewById(R.id.search_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contentAdapter = new content_Adapter(content_of_topic.searchListData(query),this);
        recyclerView.setAdapter(contentAdapter);
        contentAdapter.setItemClickCallback(this);

    }

    @Override
    public void onItemClick(int p) {
        content_itemlist item = (content_itemlist) listData.get(p);
        Intent intent =new Intent(this,Youtube.class);
        intent.putExtra("Link",item.getTopicLink());
        startActivity(intent);
    }

    @Override
    public void onSecondaryIconClick(int p) {

    }
}
