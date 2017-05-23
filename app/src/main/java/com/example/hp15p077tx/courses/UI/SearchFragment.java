package com.example.hp15p077tx.courses.UI;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.hp15p077tx.courses.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    Button button;
    EditText editText;
    String query;
    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v= inflater.inflate(R.layout.fragment_search_fragment, container, false);
        editText=(EditText)v.findViewById(R.id.editText);
        button = (Button)v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = editText.getText().toString();
                Intent intent = new Intent(getContext(),searchResults.class);
                intent.putExtra("Query", query);
                startActivity(intent);
            }
        });
        return v;
    }

}
