package com.ixitrip.chandan.ixigotrip.adapter;

import android.content.Context;
import android.util.Log;
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

public class SearchPalceAdapter extends BaseAdapter {

    ArrayList<SearchList> searchLists;
    LayoutInflater inflater;
    Context ctx;

    public SearchPalceAdapter(Context ctx,ArrayList<SearchList> searchLists) {
        this.searchLists = searchLists;

        this.ctx = ctx;
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
        final itemHolder mViewHolder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.search_place_adapter,parent,false);
            mViewHolder = new itemHolder(convertView);
            convertView.setTag(mViewHolder);

        }else{
            mViewHolder =(itemHolder) convertView.getTag();
        }
        mViewHolder.itemName.setText(searchLists.get(position).countryName);
    //    String image = lname.get(position).toLowerCase();
     //   String imageId = image.replace("&","");

//        String imgpath= InternetURL.dev+"img/web-images/"+limg.get(position) ;
//        Log.d("imageNames",limg.get(position) + "--"+imgpath);
//        Picasso.with(ctx).load(imgpath).placeholder(R.drawable.alternateimage).into( mViewHolder.img);
        //int resID = ctx.getResources().getIdentifier(imageId, "drawable",ctx.getPackageName());
        // mViewHolder.img.setBackgroundResource(resID);
        return convertView;
    }

    private class itemHolder{
        TextView itemName;
        ImageView img;

        public itemHolder(View item) {
            itemName = (TextView) item.findViewById(R.id.textView244);
            img = (ImageView) item.findViewById(R.id.imageView41);
        }
    }
}
