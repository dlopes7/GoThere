package com.gothere.gothere;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.gothere.gothere.adapters.RVAdapter;
import com.gothere.gothere.business.BusinessActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


public class MainActivity extends Activity {

    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        List<BusinessActivity> activities;

        activities = new ArrayList<>();
        activities.add(new BusinessActivity("Cinemark Patio Paulista", "R$89,90", R.drawable.cinema));
        activities.add(new BusinessActivity("Nadar Pelado", "R$0", R.drawable.praia));
        activities.add(new BusinessActivity("Ficar em casa", "R$0", R.drawable.casa));
        activities.add(new BusinessActivity("Comprar a Petrobras", "R$6,50", R.drawable.cinema));
        activities.add(new BusinessActivity("Comprar 5 Dólares", "R$620,35", R.drawable.praia));
        activities.add(new BusinessActivity("Ir para a Disney", "R$16000,95", R.drawable.cinema));
        activities.add(new BusinessActivity("Rolê na paulista", "-R$50,00", R.drawable.casa));
        activities.add(new BusinessActivity("Jantar Romântico Terraço Itália", "R$900,00", R.drawable.praia));

        RVAdapter adapter = new RVAdapter(activities);
        mRecyclerView.setAdapter(adapter);

    }

}
