package com.amitupadhyay.ait.approach;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.amitupadhyay.ait.flowingdrawer_core.FlowingView;
import com.amitupadhyay.ait.flowingdrawer_core.LeftDrawerLayout;


public class MainActivity extends AppCompatActivity implements MyMenuFragment.AmitCallbackWhenDrawerOpens{

    private RecyclerView rvFeed;
    private LeftDrawerLayout mLeftDrawerLayout;

    MyMenuFragment mMenuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();


        mLeftDrawerLayout = (LeftDrawerLayout) findViewById(R.id.id_drawerlayout);
        rvFeed = (RecyclerView) findViewById(R.id.rvFeed);

        FragmentManager fm = getSupportFragmentManager();
        mMenuFragment = (MyMenuFragment) fm.findFragmentById(R.id.id_container_menu);
        FlowingView mFlowingView = (FlowingView) findViewById(R.id.sv);
        if (mMenuFragment == null) {
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment = new MyMenuFragment()).commit();
        }
        mLeftDrawerLayout.setFluidView(mFlowingView);
        mLeftDrawerLayout.setMenuFragment(mMenuFragment);
        setupFeed();


    }


    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLeftDrawerLayout.toggle();
                //mMenuFragment.navigationView.setNavigationItemSelectedListener(navlistner);
            }
        });
    }


    private void setupFeed() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        rvFeed.setLayoutManager(linearLayoutManager);

        FeedAdapter feedAdapter = new FeedAdapter(this);
        rvFeed.setAdapter(feedAdapter);
        feedAdapter.updateItems();
    }

    NavigationView.OnNavigationItemSelectedListener navlistner=new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {
            int id = menuItem.getItemId();
            Intent i;
            //noinspection SimplifiableIfStatement
            mLeftDrawerLayout.closeDrawer();
            switch (id)
            {
                case R.id.menu_feed:
                    Toast.makeText(MainActivity.this, "Menu Feed", Toast.LENGTH_SHORT).show();
                    break;
            }

            return false;
        }
    };

    @Override
    public void onBackPressed() {
        if (mLeftDrawerLayout.isShownMenu()) {
            mLeftDrawerLayout.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void drawerIsOpen() {
        mMenuFragment.navigationView.setNavigationItemSelectedListener(navlistner);
    }
}
