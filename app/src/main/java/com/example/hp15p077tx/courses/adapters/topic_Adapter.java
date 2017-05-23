package com.example.hp15p077tx.courses.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp15p077tx.courses.Models.ListItems;
import com.example.hp15p077tx.courses.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP 15 P077 TX on 09-04-2017.
 */

public class topic_Adapter extends RecyclerView.Adapter<topic_Adapter.t_holder>{

    private List<ListItems> listData;
    private LayoutInflater inflater;
    String where;
    SharedPreferences sharedPreferences;


    private ItemClickCallback itemClickCallback;

    public interface ItemClickCallback {
        void onItemClick(int p);
        void onSecondaryIconClick(int p);
    }

    public void setItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    public topic_Adapter(List<ListItems> listData, Context c,String where){
        inflater = LayoutInflater.from(c);
        this.where = where;
        List<ListItems> listData2 = new ArrayList<>();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        boolean a;
        boolean b;
        int ab=5;
        for (ListItems item:listData) {
            ab++;
            a=where.equalsIgnoreCase("home");
            b= sharedPreferences.getString(item.getTitle().toLowerCase(),"NOT").equalsIgnoreCase("STARTED");
            String bq=sharedPreferences.getString(item.getTitle(),"NOT");
            //Toast.makeText(MyApplication.getContext(),item.getTitle().toLowerCase()+bq,Toast.LENGTH_SHORT).show();
            if(a&&b) {
                listData2.add(item);
            }
            else if(where.equalsIgnoreCase("topic")) {
                listData2.add(item);
            }
        }

        this.listData = listData2;

    }


    @Override
    public t_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.topics, parent, false);
        return new t_holder(view);
    }

    @Override
    public void onBindViewHolder(t_holder holder, int position) {
        ListItems item = listData.get(position);

        holder.title.setText(item.getTitle());
        holder.icon.setImageResource(item.getImageResId());
        holder.chapters.setText(item.getSize());

    }

    @Override
    public int getItemCount() {
        return listData.size();

    }

    class t_holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView title;
        private ImageView icon;
        private TextView chapters;
        private View container;
        public t_holder(View itemView) {
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.topic_name);
            icon = (ImageView)itemView.findViewById(R.id.topic_icon);
            chapters=(TextView)itemView.findViewById(R.id.topic_chapter);
            //We'll need the container later on when we add an View.OnClickListener
            container = itemView.findViewById(R.id.root_id);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.root_id)
            {
                itemClickCallback.onItemClick(getAdapterPosition());
            } else {
                itemClickCallback.onSecondaryIconClick(getAdapterPosition());
            }
        }
    }
}
