package com.ixitrip.chandan.ixigotrip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.ixitrip.chandan.ixigotrip.fragment.SearchInput;

public class SearchActivity extends AppCompatActivity {
    public static int fragment;
    String from;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        fragment=R.id.myfrag;
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        from=getIntent().getStringExtra("from");
        getFragmentManager().beginTransaction().replace(fragment, SearchInput.newInstance(""+from,"abc")).commit();
    }
}
