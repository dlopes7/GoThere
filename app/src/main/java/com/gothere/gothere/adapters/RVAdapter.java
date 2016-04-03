package com.gothere.gothere.adapters;


import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gothere.gothere.R;
import com.gothere.gothere.models.Item;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ActivityViewHolder>{

    List<Item> items;

    public RVAdapter(List<Item> items){
        this.items = items;
    }



    public static class ActivityViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        CardView cvL;
        TextView nomeItem;
        TextView descricaoItem;
        TextView fornecedor;
        ImageView fotoItem;

        ActivityViewHolder(View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.cv);
            cv.setOnClickListener(new CardView.OnClickListener(){
                @Override public void onClick(View v) {
                    cvL = (CardView)v;
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Log.i("David", "" + cvL);
                    }
                }
            });

            nomeItem = (TextView)itemView.findViewById(R.id.nome_item);
            descricaoItem = (TextView)itemView.findViewById(R.id.descricao_item);
            fotoItem = (ImageView)itemView.findViewById(R.id.foto_item);
            fornecedor = (TextView)itemView.findViewById(R.id.fornecedor);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ActivityViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        ActivityViewHolder avh = new ActivityViewHolder(v);
        return avh;
    }

    @Override
    public void onBindViewHolder(ActivityViewHolder itemsHolder, int i) {
        itemsHolder.nomeItem.setText(items.get(i).getProduto());
        itemsHolder.descricaoItem.setText("R$" + items.get(i).getValor_unitario() + " - " + items.get(i).getDescricao());
        itemsHolder.fotoItem.setImageResource(R.drawable.casa);
        //TODO Lógica do fornecedor
        itemsHolder.fornecedor.setText("David");
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}