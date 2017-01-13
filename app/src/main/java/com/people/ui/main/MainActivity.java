package com.people.ui.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.people.R;
import com.people.ui.adapter.ViewPagerAdapter;
import com.people.ui.fragment.FragmentOne;
import com.people.ui.fragment.FragmentThree;
import com.people.ui.fragment.FragmentTwo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ArrayList<Fragment> fm_list=new ArrayList<>();
    private ArrayList<String> fm_tab=new ArrayList<>();
    private ViewPagerAdapter fAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        initView();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        //初始化数据  tabLayout
        initData();
    }

    private void initData() {
        //添加Fragment
        fm_list.add(new FragmentOne());
        fm_list.add(new FragmentTwo());
        fm_list.add(new FragmentThree());
        //添加tabLayout标签
        fm_tab.add("日报");
        fm_tab.add("专程");
        fm_tab.add("微信");
        //设置tabLayout模式
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        mTabLayout.addTab(mTabLayout.newTab().setText(fm_tab.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(fm_tab.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(fm_tab.get(2)));
        //设置适配器
        fAdapter = new ViewPagerAdapter(this.getSupportFragmentManager(),fm_list,fm_tab);
        //viewpager加载adapter
        mViewPager.setAdapter(fAdapter);
        //TabLayout加载viewpager
        mTabLayout.setupWithViewPager(mViewPager);



    }

    //初始化控件
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp_FindFragment_pager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_FindFragment_title);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //设置Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("首页");
        setSupportActionBar(toolbar);
        //设置悬浮按钮
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            Toast.makeText(this, "发布模块", Toast.LENGTH_SHORT).show();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

            Toast.makeText(this, "全民TV", Toast.LENGTH_SHORT).show();
        }  else if (id == R.id.nav_share) {

            Toast.makeText(this, "设置 ", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_send) {

            Toast.makeText(this, "关于", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
