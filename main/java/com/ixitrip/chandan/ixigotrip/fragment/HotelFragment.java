package com.ixitrip.chandan.ixigotrip.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ixitrip.chandan.ixigotrip.R;
import com.ixitrip.chandan.ixigotrip.adapter.RouteAdapter;
import com.ixitrip.chandan.ixigotrip.adapter.SearchPalceAdapter;
import com.ixitrip.chandan.ixigotrip.internetComm.RouteAsyncTask;
import com.ixitrip.chandan.ixigotrip.response.RouteResponse;

public class HotelFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RouteResponse response=null;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Handler mhHandler;
   ListView lv;
    Context ctx;
    public HotelFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static HotelFragment newInstance(String param1, String param2) {
        HotelFragment fragment = new HotelFragment();
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
    Runnable route=new Runnable() {
        @Override
        public void run() {

            RouteAsyncTask routeAsyncTask=new RouteAsyncTask();
            routeAsyncTask.execute("1058783","1066691");
            try{
                response=routeAsyncTask.get();
            }catch (Exception e)
            {

            }
            mhHandler.post(new Runnable() {
                @Override
                public void run() {
                    RouteAdapter searchPalceAdapter=new RouteAdapter(ctx,response.allocateLists.arrayList,response.allocateLists.route);
                    lv.setAdapter(searchPalceAdapter);
                }
            });
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView  =inflater.inflate(R.layout.fragment_hotel, container, false);
        lv=(ListView)rootView.findViewById(R.id.ListView) ;
        new Thread(route).start();
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mhHandler=new Handler();
        ctx=context;
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
