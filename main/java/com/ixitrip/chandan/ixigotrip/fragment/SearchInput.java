package com.ixitrip.chandan.ixigotrip.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ixitrip.chandan.ixigotrip.MainActivity;
import com.ixitrip.chandan.ixigotrip.R;
import com.ixitrip.chandan.ixigotrip.adapter.SearchPalceAdapter;
import com.ixitrip.chandan.ixigotrip.background.IntentString;
import com.ixitrip.chandan.ixigotrip.internetComm.SearchAsyncTask;
import com.ixitrip.chandan.ixigotrip.response.SearchResponse;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;


public class SearchInput extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "from";
    private static final String ARG_PARAM2 = "param2";
    Activity act;
    private String textchanged;
    // TODO: Rename and change types of parameters
    private String from;
    private String mParam2;
    ListView lv;
    Location location;
    Context ctx;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    Handler mHandler;
    SearchResponse searchResponse;
    EditText sechinput;
    private LocationManager locationManager;
    private String provider;

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
            from = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView  =inflater.inflate(R.layout.fragment_search_input, container, false);
        Button frombtn=(Button)rootView.findViewById(R.id.close);
        sechinput=(EditText)rootView.findViewById(R.id.editText3) ;
       lv=(ListView)rootView.findViewById(R.id.ListView) ;
        lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String upper="Select Your City";;
                String lower="Select Traveling City";
                if(from.equals("0")) {

                    lower = searchResponse.allocateLists.get(position).address;

                }else {
                    upper= searchResponse.allocateLists.get(position).address;
                }
                Log.d("selectedaddress",""+upper);
                Intent in=new Intent(IntentString.MAIN_ACTIVITY);
                in.setFlags(in.FLAG_ACTIVITY_NEW_TASK | in.FLAG_ACTIVITY_CLEAR_TASK);
                in.putExtra("upper",""+upper);
                in.putExtra("lower",""+lower);
                getActivity().finish();
                startActivity(in);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            checkLocationPermission();
//        }
//        locationManager = (LocationManager) act.getSystemService(ctx.LOCATION_SERVICE);
//
//        Criteria criteria = new Criteria();
//        provider = locationManager.getBestProvider(criteria, true);
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (ContextCompat.checkSelfPermission(act,
//                    Manifest.permission.ACCESS_FINE_LOCATION)
//                    == PackageManager.PERMISSION_GRANTED) {
//                buildGoogleApiClient();
//
//            }
//        }
//        if(checkWriteExternalPermission("android.permission.ACCESS_FINE_LOCATION"))
////        {
//            Log.d("provider","a"+provider);
//            location = locationManager.getLastKnownLocation(provider);
//            Log.d("provider",location+"a"+provider);
//        }else
//        {
//            Log.d("providerelse","a"+provider);
//            location=null;
//        }


//        mylocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("click","a"+from);
//                if(from.equals("0"))
//                {
//                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
//                    builder1.setMessage("You can't select my Location For Destination. ");
//                    builder1.setCancelable(true);
//
//                    builder1.setPositiveButton(
//                            "Ok",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//
//                                    dialog.cancel();
//                                }
//                            });
//
//                    AlertDialog alert11 = builder1.create();
//                    alert11.show();
//                }else
//                {
//                    if (location != null) {
//                        //System.out.println("Provider " + provider + " has been selected.");
//                        try {
//                            onLocationChanged(location);
//                        }catch (Exception e)
//                        {
//
//                        }
//                    }
//                }
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
          getActivity().finish();
            }
        });

        return rootView;
    }
    @Override
    public void onConnectionSuspended(int i) {

    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(act,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
           // LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, act);
        }

    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(ctx)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(act,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(act,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(act,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(act,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }
//    private boolean checkWriteExternalPermission(String str)
//    {
//
//        //String permission = "android.permission.ACCESS_FINE_LOCATION";
//        int res = ctx.checkCallingOrSelfPermission(str);
//        return (res == PackageManager.PERMISSION_GRANTED);
//    }
    @Override
    public void onLocationChanged(Location location) {
        //You had this as int. It is advised to have Lat/Loing as double.
        mLastLocation = location;
//        if (mCurrLocationMarker != null) {
//            mCurrLocationMarker.remove();
//        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
       // mCurrLocationMarker = mMap.addMarker(markerOptions);

//        //move map camera
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
        //stop location updates
//        if (mGoogleApiClient != null) {
//            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, act);
//        }
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        Log.d("address", lng+"a" + lat);
        Geocoder geoCoder = new Geocoder(ctx, Locale.getDefault());
        StringBuilder builder = new StringBuilder();
        try {
            List<Address> address = geoCoder.getFromLocation(lat, lng, 1);
            int maxLines = address.get(0).getMaxAddressLineIndex();
            for (int i = 0; i < maxLines; i++) {
                String addressStr = address.get(0).getAddressLine(i);
                builder.append(addressStr);
                builder.append(" ");
            }

            String fnialAddress = builder.toString(); //This is the complete address.
            sechinput.setText(fnialAddress);
            Log.d("address", "a" + fnialAddress);

        } catch (IOException e) {
            // Handle IOException
        } catch (NullPointerException e) {
            // Handle NullPointerException
        }
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {


    }
    @Override
    public void onProviderEnabled(String provider) {
//        Toast.makeText(this, "Enabled new provider " + provider,
//                Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onProviderDisabled(String provider) {
//        Toast.makeText(this, "Disabled provider " + provider,
//                Toast.LENGTH_SHORT).show();
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

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Log.d("click the station","yes"+position);
                            String upper="Select Your City";;
                            String lower="Select Traveling City";
                            if(from.equals("0")) {

                                lower = searchResponse.allocateLists.get(position).address;

                            }else {
                                upper= searchResponse.allocateLists.get(position).address;
                            }
                            Log.d("selectedaddress",""+upper);
                            Intent in=new Intent(IntentString.SEARCH_ACTVITY);
                            in.setFlags(in.FLAG_ACTIVITY_NEW_TASK | in.FLAG_ACTIVITY_CLEAR_TASK);
                            in.putExtra("upper",""+upper);
                            in.putExtra("lower",""+lower);
                            getActivity().finish();
                          //  getActivity().startActivity(in);
                        }
                    });

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
