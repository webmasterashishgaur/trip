package com.ixitrip.chandan.ixigotrip.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ixitrip.chandan.ixigotrip.R;
import com.ixitrip.chandan.ixigotrip.background.SearchList;

import java.util.ArrayList;

/**
 * Created by chandan on 4/8/2017.
 */

public class RouteAdapter extends BaseAdapter {

    ArrayList<ArrayList<String>> route;
    LayoutInflater inflater;
    ArrayList<ArrayList<String>> arr;
    ArrayList<String> searchLists;
    Context ctx;

    public RouteAdapter(Context ctx,ArrayList<String> searchLists,ArrayList<ArrayList<String>> route) {
        this.searchLists = searchLists;

        this.ctx = ctx;
        this.route = route;
        //  Log.d("sizeeeeee", lname.size() + "");
        inflater =  LayoutInflater.from(this.ctx);
    }

    @Override
    public int getCount() {
        return searchLists.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final itemHolderRoute mViewHolder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.route_adapter,parent,false);
            mViewHolder = new itemHolderRoute(convertView);
            convertView.setTag(mViewHolder);

        }else{
            mViewHolder =(itemHolderRoute) convertView.getTag();
        }
        mViewHolder.itemName.setText(searchLists.get(position));
        //    String image = lname.get(position).toLowerCase();
        //   String imageId = image.replace("&","");

//        String imgpath= InternetURL.dev+"img/web-images/"+limg.get(position) ;
//        Log.d("imageNames",limg.get(position) + "--"+imgpath);
//        Picasso.with(ctx).load(imgpath).placeholder(R.drawable.alternateimage).into( mViewHolder.img);
        //int resID = ctx.getResources().getIdentifier(imageId, "drawable",ctx.getPackageName());
        // mViewHolder.img.setBackgroundResource(resID);
        return convertView;
    }

    private class itemHolderRoute{
        TextView itemName;
        ImageView img;

        public itemHolderRoute(View item) {
            itemName = (TextView) item.findViewById(R.id.textView244);
            img = (ImageView) item.findViewById(R.id.imageView41);
        }
    }
}
