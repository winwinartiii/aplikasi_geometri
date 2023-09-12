package com.example.bangunruang;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



public class BangunRuangAdapter extends RecyclerView.Adapter<BangunRuangAdapter.BangunRuangViewHolder> {

    private final Context mCtx;
    private final List<BangunRuangItem> bangunRuangList;

    public BangunRuangAdapter(Context mCtx, List<BangunRuangItem> bangunRuangList){
        this.mCtx = mCtx;
        this.bangunRuangList = bangunRuangList;
    }

    @NonNull
    @Override
    public BangunRuangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.item_bangun_ruang, null);
        return new BangunRuangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BangunRuangViewHolder holder, @SuppressLint("RecyclerView") final int position){
        final BangunRuangItem bangunRuangItem = bangunRuangList.get(position);

        holder.textViewBangunRuang.setText(bangunRuangItem.getNamaBangunRuang());
        holder.imageBangunRuang.setImageResource(bangunRuangItem.getThumbBangunRuang());

        holder.cardViewBangunRuang.setOnClickListener(view -> {
            Intent intent = new Intent(mCtx, MateriBangunRuang.class);
            intent.putExtra("namaBangunRuang", bangunRuangList.get(position).getNamaBangunRuang());
            intent.putExtra("thumbBangunRuang", bangunRuangList.get(position).getThumbBangunRuang());
            intent.putExtra("whiteThumbBangunRuang", bangunRuangList.get(position).getWhiteThumbBangunRuang());
            intent.putExtra("descBangunRuang", bangunRuangList.get(position).getDescBangunRuang());
            intent.putExtra("luasBangunRuang", bangunRuangList.get(position).getLuasBangunRuang());
            intent.putExtra("volumeBangunRuang", bangunRuangList.get(position).getVolumeBangunRuang());
            intent.putExtra("rumusBangunRuang", bangunRuangList.get(position).getRumusBangunRuang());

            mCtx.startActivity(intent);
        });
    }

    @Override
    public int getItemCount(){
        return bangunRuangList.size();
    }


    static class BangunRuangViewHolder extends RecyclerView.ViewHolder{

        TextView textViewBangunRuang;
        ImageView imageBangunRuang;
        CardView cardViewBangunRuang;

        public BangunRuangViewHolder(View itemView) {
            super(itemView);

            textViewBangunRuang = itemView.findViewById(R.id.textViewBangunRuang);
            imageBangunRuang = itemView.findViewById(R.id.imageBangunRuang);
            cardViewBangunRuang = itemView.findViewById(R.id.cardViewBangunRuang);
        }

    }
}
