package com.example.hp15p077tx.courses.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp15p077tx.courses.Models.content_itemlist;
import com.example.hp15p077tx.courses.R;

import java.util.List;

/**
 * Created by HP 15 P077 TX on 13-04-2017.
 */

public class content_Adapter extends RecyclerView.Adapter<content_Adapter.t_holder>{
    private List<content_itemlist> listData;
    private LayoutInflater inflater;


    private ItemClickCallback itemClickCallback;

    public interface ItemClickCallback {
        void onItemClick(int p);
        void onSecondaryIconClick(int p);
    }

    public void setItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    public content_Adapter(List<content_itemlist> listData, Context c){
        inflater = LayoutInflater.from(c);
        this.listData = listData;
    }


    @Override
    public t_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.contents, parent, false);
        return new t_holder(view);
    }

    @Override
    public void onBindViewHolder(t_holder holder, int position) {
        content_itemlist item = listData.get(position);
        holder.title.setText(item.getSerialNo() +" "+item.getTopicName());
    }

    @Override
    public int getItemCount() {
        return listData.size();

    }

    class t_holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView title;
        private View container;
        public t_holder(View itemView) {
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.chapter_name);
            //We'll need the container later on when we add an View.OnClickListener
            container = itemView.findViewById(R.id.cont_id);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.cont_id)
            {
                itemClickCallback.onItemClick(getAdapterPosition());
            } else {
                itemClickCallback.onSecondaryIconClick(getAdapterPosition());
            }
        }
    }
}
