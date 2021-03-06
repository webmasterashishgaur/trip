package com.ixitrip.chandan.ixigotrip.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ixitrip.chandan.ixigotrip.MainActivity;
import com.ixitrip.chandan.ixigotrip.R;
import com.ixitrip.chandan.ixigotrip.background.IntentString;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class SearchFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "Select Your City";
    private static final String ARG_PARAM2 = "Select Traveling City";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Activity act;
    TextView frombtn;
    TextView tobtn;

    public SearchFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView  =inflater.inflate(R.layout.fragment_search, container, false);
        frombtn=(TextView) rootView.findViewById(R.id.textView3);
       tobtn=(TextView) rootView.findViewById(R.id.textView2);
        Button searchbtn=(Button) rootView.findViewById(R.id.bSearch);

        frombtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(IntentString.SEARCH_ACTVITY);
                in.putExtra("from","1");
                startActivity(in);
            }
        });
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent in=new Intent(IntentString.SEARCH_ACTVITY);
//                in.putExtra("from","1");
//                startActivity(in);
            }
        });
        tobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(IntentString.SEARCH_ACTVITY);
                in.putExtra("from","0");
                startActivity(in);
            }
        });
        frombtn.setText(""+ARG_PARAM1);
        tobtn.setText(""+ARG_PARAM2);
        return rootView;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        act=(Activity) context;
//        frombtn.setText(""+MainActivity.UPPERSELECTEDPLACE);
//        frombtn.setText(""+MainActivity.LOWERSELECTEDPLACE);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
