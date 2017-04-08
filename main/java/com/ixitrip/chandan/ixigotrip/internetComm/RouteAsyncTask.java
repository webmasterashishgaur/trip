package com.ixitrip.chandan.ixigotrip.internetComm;

import android.os.AsyncTask;
import android.util.Log;

import com.ixitrip.chandan.ixigotrip.background.RouteList;
import com.ixitrip.chandan.ixigotrip.background.SearchList;
import com.ixitrip.chandan.ixigotrip.response.RouteResponse;
import com.ixitrip.chandan.ixigotrip.response.SearchResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by chandan on 4/8/2017.
 */

public class RouteAsyncTask extends AsyncTask<String,Integer,RouteResponse> {
    String responseStr;



    @Override
    protected RouteResponse doInBackground(String... params)
    {
        // TODO Auto-generated method stub

        responseStr = postDataGetStream(params);
        RouteResponse response = null;


        try
        {

            response = parse(responseStr);
        }
        catch(Exception e){
            e.printStackTrace();
        }


        return response;
    }

    @Override
    protected void onPostExecute(RouteResponse response)
    {

    }

    @Override
    protected void onProgressUpdate(Integer... progress)
    {
    }
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();
            Log.d("downloadUrl", data.toString());
            // br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
//            iStream.close();
//            urlConnection.disconnect();
        }
        return data;
    }
    public String postDataGetStream(String... param)
    {
        String data = "";
        try {
            data= downloadUrl("http://build2.ixigo.com/api/v2/a2b/modes?apiKey=ixicode!2$&originCityId="+param[0]+"&destinationCityId="+param[1]);
            System.out.print("chand"+data);
        }catch (IOException e)
        {
            Log.d("exception here",""+e.toString());
        }
        return data;
    }


    public RouteResponse parse(String response) {
        RouteResponse response1=null;
        ArrayList<String> itemList = new ArrayList<>();
        ArrayList<ArrayList<String>> routedetail = new ArrayList<>();
        String orgin="";
        String destination="";
        String price="";
        String   fastest="";
        String time="";
        String id="";
        String address="";
        try {

            JSONObject reader = new JSONObject(response);
            JSONObject data=reader.getJSONObject("data");
         //   for(int i=0;i<data.length();i++) {
                Log.d("serchres", "" + response);
              //  JSONObject jsonObject=data.getJSONObject(i);
                // JSONArray search = jsonObject.getJSONArray("data");
                //Log.d("total order output","lll");
                // itemList.add(new AllocateList("id", "lastid", "orderDate", "deliveryDate", "price", "status"));

                //  for (int j = 0; j < search.length(); j++) {
                // JSONObject orderData = search.getJSONObject(i);

                orgin = data.getString("originName");
             //   address = jsonObject.getString("address");
                destination = data.getString("destinationName");
                itemList.add(orgin);
                itemList.add(destination);
//                state = jsonObject.getString("st");
//                xid = jsonObject.getString("xid");
//                id = jsonObject.getString("_id");
//                url = jsonObject.getString("url");
                JSONArray route=data.getJSONArray("routes");
                for(int j=0;j<route.length();j++)
                {
                    ArrayList<String> arrayList=new ArrayList<>();

                    JSONObject jsonObjectroute=route.getJSONObject(j);
                    price = jsonObjectroute.getString("price");
                    time = jsonObjectroute.getString("time");
                    fastest = jsonObjectroute.getString("fastestDuration");
                    arrayList.add(price);
                    arrayList.add(time);
                    arrayList.add(fastest);
                    routedetail.add(arrayList);
                }
                response1= new RouteResponse(new RouteList(itemList,routedetail));

                // }
          //  }


        } catch (Exception e) {
            Log.d("hhhhhhh", "parse ");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return response1;
    }
}
