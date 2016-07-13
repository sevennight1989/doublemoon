package com.huazhu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.huazhu.main.BasePagerAdapter;
import com.huazhu.main.OrderFragment;
import com.huazhu.main.PublicFragment;
import com.huazhu.share.ShareFragment;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.utils.Log;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import shanyao.tabpagerindictor.TabPageIndicator;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private TabPageIndicator mInDicator;
    private ViewPager mViewPager;
    private List<Fragment> mList;
    private BasePagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

    }

    private void initView() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
            }
        });
        mInDicator = (TabPageIndicator) findViewById(R.id.indicator);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mAdapter = new BasePagerAdapter(getSupportFragmentManager());
        setFragments();
        setTitles();
        mViewPager.setAdapter(mAdapter);
        mInDicator.setViewPager(mViewPager);
        setTabPagerIndicator();

    }

    private void setTabPagerIndicator() {
        mInDicator.setIndicatorMode(TabPageIndicator.IndicatorMode.MODE_WEIGHT_NOEXPAND_SAME);
        mInDicator.setDividerColor(Color.parseColor("#00bbcf"));
        mInDicator.setDividerPadding(10);
        mInDicator.setIndicatorColor(Color.parseColor("#43A44b"));
        mInDicator.setTextColorSelected(Color.parseColor("#43A44b"));
        mInDicator.setTextColor(Color.parseColor("#797979"));
        mInDicator.setTextSize(40);
    }

    private void setFragments() {
        mList = new ArrayList<>();
        mList.add(OrderFragment.newInstance(0));
        mList.add(PublicFragment.newInstance(1));
        mAdapter.setFragments(mList);
    }

    private void setTitles() {
        mAdapter.setTitles(getResources().getStringArray(R.array.titles));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_share) {

            ShareFragment shareFragment = new ShareFragment();
            shareFragment.show(getSupportFragmentManager(),"shareFragment");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** attention to this below ,must add this**/
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        Log.d("result","onActivityResult");
    }
}
