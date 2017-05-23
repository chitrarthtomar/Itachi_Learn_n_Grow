package com.example.hp15p077tx.courses.Models;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import com.example.hp15p077tx.courses.adapters.MyApplication;
import com.example.hp15p077tx.courses.R;
/**
 * Created by HP 15 P077 TX on 09-04-2017.
 * This class will provide topics and other data
 */

public class Topics {

    private static SharedPreferences sharedPreferences;
    private static final String[] titles  = {"Python", "C++", "Java"} ;
    private static final int[] icons = {R.drawable.python_logo, R.drawable.cpp_logo, R.drawable.java};
    private static final String[] dat  = {"python_chapter", "cpp_chapter", "java_chapter"} ;
    public static List <ListItems> getListData() {
        List <ListItems> data = new ArrayList <>();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());

        for (int i = 0; i  < titles.length && i  < icons.length; i++) {
                ListItems item = new ListItems();
                item.setImageResId(icons[i]);
                item.setTitle(titles[i]);
            int siz = sharedPreferences.getInt(dat[i], 12);
                item.setSize(siz);
                data.add(item);
            }
        return data;
    }
}
