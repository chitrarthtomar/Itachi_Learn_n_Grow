package com.example.hp15p077tx.courses.Models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.hp15p077tx.courses.adapters.MyApplication.*;

/**
 * Created by HP 15 P077 TX on 20-04-2017.
 */

public class content_of_topic {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static List<String> titles;
    private static List<String> links;
    Context context = getContext();

    public String loadJSONFromAsset(String file) {
        String json = null;
        try {

            InputStream is = context.getAssets().open(file);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
    public static List<content_itemlist> getListData(String topic) {
        List <content_itemlist> data = new ArrayList<>();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = sharedPreferences.edit();
        titles = new ArrayList<>();
        links = new ArrayList<>();
        if(topic.equalsIgnoreCase("Python"))
        {
            content_of_topic cn = new content_of_topic();
            String all = cn.loadJSONFromAsset("python_data.txt");
            String[] alls = all.split("#@#");

            for(int i=0;i<alls.length;i++)
            {
                if(alls[i].length()==0){continue;}
                String[] top = alls[i].split(":");
                titles.add(top[0]);
                 links.add(top[1]);
            }
            editor.putInt("python_chapter",titles.size()-1);
            editor.putString("PYTHON","Started");
        }
        else if(topic.equalsIgnoreCase("C++")){
            content_of_topic cn = new content_of_topic();
            String all = cn.loadJSONFromAsset("cpp_data.txt");
            String[] alls = all.split("#@#");

            for(int i=0;i<alls.length;i++)
            {
                if(alls[i].length()==0){continue;}
                String[] top = alls[i].split(":");
                titles.add(top[0]);
                links.add(top[1]);
            }
            editor.putInt("cpp_chapter",titles.size());
            editor.putString("CPP","Started");
        }
        else if(topic.equalsIgnoreCase("Java")) {
            content_of_topic cn = new content_of_topic();
            String all = cn.loadJSONFromAsset("java_data.txt");
            String[] alls = all.split("#@#");

            for (int i = 0; i < alls.length; i++) {
                if (alls[i].length() == 0) {
                    continue;
                }
                String[] top = alls[i].split(":");
                titles.add(top[0]);
                links.add(top[1]);
            }
            editor.putInt("java_chapter",titles.size()-1);
            editor.putString("JAVA","Started");
        }
        else
        {
            content_of_topic cn = new content_of_topic();
            String all = cn.loadJSONFromAsset("python_data.txt");
            String[] alls = all.split("#@#");

            for(int i=0;i<alls.length;i++)
            {
                if(alls[i].length()==0){continue;}
                String[] top = alls[i].split(":");
                titles.add(top[0]);
                links.add(top[1]);
            }
            editor.putInt("python_chapter",titles.size()-1);
            editor.putString("PYTHON","Started");
        }
        editor.apply();
        int ab=titles.size();
        for (int i = 0; i  < titles.size() && i  < links.size(); i++) {
            content_itemlist item = new content_itemlist();
            item.setSerialNo((i+1)+":");
            item.setTopicName(titles.get(i));
            item.setTopicLink(links.get(i));
            data.add(item);
        }
        return data;
    }
    public static List<content_itemlist> searchListData(String query) {
        List <content_itemlist> data = new ArrayList<>();
        titles = new ArrayList<>();
        links = new ArrayList<>();

        content_of_topic cn = new content_of_topic();
        content_of_topic cn1 = new content_of_topic();
        content_of_topic cn2 = new content_of_topic();
        String all = cn.loadJSONFromAsset("python_data.txt");
        String[] alls = all.split("#@#");

        for(int i=0;i<alls.length;i++)
        {
            if(alls[i].length()==0){continue;}
            String[] top = alls[i].split(":");
            titles.add("Python: " + top[0]);
            links.add(top[1]);
        }

        String call = cn1.loadJSONFromAsset("cpp_data.txt");
        String[] calls = call.split("#@#");

        for(int j=0;j<calls.length;j++)
        {
            if(calls[j].length()==0){continue;}
            String[] top = calls[j].split(":");
            titles.add("C++   : " + top[0]);
            links.add(top[1]);
        }
        String jall = cn2.loadJSONFromAsset("java_data.txt");
        String[] jalls = jall.split("#@#");

        for(int k=0;k<jalls.length;k++)
        {
            if(jalls[k].length()==0){continue;}
            String[] top = jalls[k].split(":");
            titles.add("Java  : " + top[0]);
            links.add(top[1]);
        }
        for (int i = 0; i  < titles.size() && i  < links.size(); i++) {
            if(titles.get(i).substring(7).toLowerCase().contains(query.toLowerCase())) {
                content_itemlist item = new content_itemlist();
                item.setSerialNo("*");
                item.setTopicName(titles.get(i));
                item.setTopicLink(links.get(i));
                data.add(item);
            }
        }
        return data;
    }

}
