package com.gothere.gothere.adapters;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gothere.gothere.R;
import com.gothere.gothere.business.BusinessActivity;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ActivityViewHolder>{

    List<BusinessActivity> activities;

    public RVAdapter(List<BusinessActivity> activities){
        this.activities = activities;
    }



    public static class ActivityViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;

        ActivityViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personAge = (TextView)itemView.findViewById(R.id.person_age);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    @Override
    public ActivityViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        ActivityViewHolder avh = new ActivityViewHolder(v);
        return avh;
    }

    @Override
    public void onBindViewHolder(ActivityViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(activities.get(i).getName());
        personViewHolder.personAge.setText(activities.get(i).getPlace());
        personViewHolder.personPhoto.setImageResource(activities.get(i).getPhotoId());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}