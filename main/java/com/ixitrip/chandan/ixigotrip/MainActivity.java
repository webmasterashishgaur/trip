package com.ixitrip.chandan.ixigotrip;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;

import com.ixitrip.chandan.ixigotrip.fragment.HotelFragment;
import com.ixitrip.chandan.ixigotrip.fragment.MapFragment;
import com.ixitrip.chandan.ixigotrip.fragment.MoreFragment;
import com.ixitrip.chandan.ixigotrip.fragment.SearchFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        ViewPager viewPager=(ViewPager)findViewById(R.id.viewpager);
        if(viewPager!=null)
        {
            setUpViewPager(viewPager);
        }
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void setUpViewPager(ViewPager viewPager)
    {
        Adapter adapter=new Adapter(getSupportFragmentManager());
        adapter.addFragment( SearchFragment.newInstance("abc","bcd"),"Search");
        adapter.addFragment( HotelFragment.newInstance("abc","bcd"),"Hotel");
        adapter.addFragment( MapFragment.newInstance("abc","bcd"),"Map");
        adapter.addFragment( MoreFragment.newInstance("abc","bcd"),"More");
        viewPager.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }
    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();
        public Adapter(FragmentManager fm) {
            super(fm);
        }
        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }
        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
        @Override
        public int getCount() {
            return mFragments.size();
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
