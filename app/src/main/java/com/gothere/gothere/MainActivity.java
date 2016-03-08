package com.gothere.gothere;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gothere.gothere.adapters.RVAdapter;
import com.gothere.gothere.business.BusinessActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.acitivity_recycler);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        List<BusinessActivity> activities;

        activities = new ArrayList<>();
        activities.add(new BusinessActivity("Cinemark Patio Paulista", "R$89,90", R.drawable.logo));
        activities.add(new BusinessActivity("Nadar Pelado", "R$0", R.drawable.logo));
        activities.add(new BusinessActivity("Ficar em casa", "R$0", R.drawable.logo));

        RVAdapter adapter = new RVAdapter(activities);
        mRecyclerView.setAdapter(adapter);


    }

}
