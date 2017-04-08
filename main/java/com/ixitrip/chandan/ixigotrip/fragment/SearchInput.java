package com.ixitrip.chandan.ixigotrip.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ixitrip.chandan.ixigotrip.MainActivity;
import com.ixitrip.chandan.ixigotrip.R;
import com.ixitrip.chandan.ixigotrip.adapter.SearchPalceAdapter;
import com.ixitrip.chandan.ixigotrip.internetComm.SearchAsyncTask;
import com.ixitrip.chandan.ixigotrip.response.SearchResponse;

import java.util.concurrent.ExecutionException;


public class SearchInput extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Activity act;
    private String textchanged;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ListView lv;
    Context ctx;
    Handler mHandler;
    SearchResponse searchResponse;

    public SearchInput() {
        // Required empty public constructor
    }


    public static SearchInput newInstance(String param1, String param2) {
        SearchInput fragment = new SearchInput();
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
        View rootView  =inflater.inflate(R.layout.fragment_search_input, container, false);
        Button frombtn=(Button)rootView.findViewById(R.id.close);
        EditText sechinput=(EditText)rootView.findViewById(R.id.editText3) ;
       lv=(ListView)rootView.findViewById(R.id.ListView) ;
////        sechinput.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                    Log.d("backbutton", "is pressed");
//                    return true;
//                }
//                if ((keyCode == KeyEvent.KEYCODE_BACK)) {
//                    Log.d("backbutton", "is pressed");
//                }
//                return false;
//            }
//        });
        sechinput.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() >= 2) {
                    textchanged=s.toString();

                    new Thread(search).start();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        frombtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.getFragmentManager().beginTransaction().replace(MainActivity.fragment, SearchFragment.newInstance("abc","abc")).commit();
            }
        });
        return rootView;
    }

    Runnable search=new Runnable() {
        @Override
        public void run() {
//            try {
//                searchResponse.allocateLists.clear();
//            }catch (NullPointerException e)
//            {
//
//            }

            SearchAsyncTask searchAsyncTask=new SearchAsyncTask();
            searchAsyncTask.execute(textchanged);
            try{
                searchResponse=searchAsyncTask.get();
            }catch (InterruptedException e)
            {

            }catch (ExecutionException ex)
            {

            }
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    SearchPalceAdapter searchPalceAdapter=new SearchPalceAdapter(ctx,searchResponse.allocateLists);
                    lv.setAdapter(searchPalceAdapter);
                }
            });
        }
    };
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        act=(Activity)context;
        ctx=context;
        mHandler=new Handler();
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
