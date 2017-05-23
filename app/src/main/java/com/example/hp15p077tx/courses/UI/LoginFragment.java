package com.example.hp15p077tx.courses.UI;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hp15p077tx.courses.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import static android.content.Context.MODE_PRIVATE;


public class LoginFragment extends Fragment {

    private Button l,r,lo;
    LinearLayout lin,lout;
    EditText ed1,ed2;
    SharedPreferences mySharedPreferences ;
    SharedPreferences.Editor editor;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v =  inflater.inflate(R.layout.fragment_login, container, false);
        l= (Button)v.findViewById(R.id.login);
        r = (Button)v.findViewById(R.id.register);
        lo = (Button)v.findViewById(R.id.logout_button);
        ed1 = (EditText)v.findViewById(R.id.name);
        ed2 = (EditText)v.findViewById(R.id.pass);
        lin = (LinearLayout)v.findViewById(R.id.login_layout);
        lout = (LinearLayout)v.findViewById(R.id.logout_layout);

        mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = mySharedPreferences.edit();
        String name = mySharedPreferences.getString("Flag", "default value");
        boolean a=name.toString().equals("True");
        if(a) {
            ViewGroup.LayoutParams params = lin.getLayoutParams();
            params.height = 0;
            lin.setLayoutParams(params);
            ViewGroup.LayoutParams param = lout.getLayoutParams();
            param.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            lout.setLayoutParams(param);
        }
        else {
            ViewGroup.LayoutParams param = lin.getLayoutParams();
            param.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            lin.setLayoutParams(param);
            ViewGroup.LayoutParams params = lout.getLayoutParams();
            params.height = 0;
            lout.setLayoutParams(params);
        }
        l.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String json = null;
                if ((ed1.getText().toString().trim().length() >0 && ed2.getText().toString().trim().length()>0)) {
                    try {

                        FileInputStream fis = new FileInputStream(new File(getActivity().getFilesDir().getAbsolutePath() + "/log.txt"));
                        int size = fis.available();

                        byte[] buffer = new byte[size];

                        fis.read(buffer);
                        fis.close();

                        json = new String(buffer, "UTF-8");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    String[] parts = json.split("#@#");
                    String q = ed1.getText().toString() + ":" + ed2.getText().toString();
                    int flag = 0;
                    for (String item : parts) {
                        if (item.contains(q)) {
                            flag = 1;
                            Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();



                            String j=" ";
                            try {

                                FileInputStream fis = new FileInputStream(new File(getActivity().getFilesDir().getAbsolutePath() + "/"+ed1.getText().toString()+".txt"));
                                int size = fis.available();

                                byte[] buffer = new byte[size];

                                fis.read(buffer);
                                fis.close();

                                j = new String(buffer, "UTF-8");
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            if(j.trim().length()>0) {
                                String[] j1 = j.split(",");
                                for (String i : j1) {
                                    if (i.contains("python") && i.contains("STARTED")) {
                                        editor.putString("python", "STARTED");
                                    } else if (i.contains("c++") && i.contains("STARTED")) {
                                        editor.putString("c++", "STARTED");
                                    } else if (i.contains("java") && i.contains("STARTED")) {
                                        editor.putString("java", "STARTED");
                                    }
                                }
                            }
                            editor.putString("Flag","True");
                            editor.putString("User",ed1.getText().toString());
                            editor.apply();
                            ViewGroup.LayoutParams params = lin.getLayoutParams();
                            params.height = 0;
                            lin.setLayoutParams(params);
                            ViewGroup.LayoutParams param = lout.getLayoutParams();
                            param.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                            lout.setLayoutParams(param);
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();


                        }
                    }

                    if (flag == 0) {
                        Toast.makeText(getContext(), "Login Unsuccessful, Try registering", Toast.LENGTH_LONG).show();
                        ed2.setText("");
                        r.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((ed1.getText().toString().trim().length() >0 && ed2.getText().toString().trim().length()>0)) {
                    try {

                        FileOutputStream fileout = getContext().openFileOutput("log.txt", MODE_PRIVATE);
                        OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                        String s = "#@#" + ed1.getText().toString() + ":" + ed2.getText().toString() + "#@#";
                        outputWriter.write(s);
                        outputWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getContext(), "Registration Successful", Toast.LENGTH_LONG).show();
                }
            }
        });
        lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup.LayoutParams param = lin.getLayoutParams();
                param.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                lin.setLayoutParams(param);
                ViewGroup.LayoutParams params = lout.getLayoutParams();
                params.height = 0;
                lout.setLayoutParams(params);
                editor.putString("Flag","False");

                Toast.makeText(getContext(), "Logout Successful", Toast.LENGTH_LONG).show();

                try {
                        String fname= mySharedPreferences.getString("User","cde")+".txt";
                        FileOutputStream fileout = getContext().openFileOutput(fname, MODE_PRIVATE);
                        OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                        String s = "python:"+mySharedPreferences.getString("python","NOT") + ",c++:"+mySharedPreferences.getString("c++","NOT") + ",java:"+mySharedPreferences.getString("java","NOT");
                        outputWriter.write(s);
                        outputWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                editor.putString("python","NOT");
                editor.putString("c++","NOT");
                editor.putString("java","NOT");
                editor.apply();

            }
        });
        return v;

    }


}
