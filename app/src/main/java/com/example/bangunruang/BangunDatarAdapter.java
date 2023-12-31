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


public class BangunDatarAdapter extends RecyclerView.Adapter<BangunDatarAdapter.BangunDatarViewHolder> {

    private final Context mCtx;
    private final List<BangunDatarItem> bangunDatarList;

    public BangunDatarAdapter(Context mCtx, List<BangunDatarItem> bangunDatarList){
        this.mCtx = mCtx;
        this.bangunDatarList = bangunDatarList;
    }

    @NonNull
    @Override
    public BangunDatarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.item_bangun_datar, null);
        return new BangunDatarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BangunDatarViewHolder holder, @SuppressLint("RecyclerView") final int position){
        final BangunDatarItem bangunDatarItem = bangunDatarList.get(position);

        holder.textViewBangunDatar.setText(bangunDatarItem.getNamaBangunDatar());
        holder.imageBangunDatar.setImageResource(bangunDatarItem.getThumbBangunDatar());

        holder.cardViewBangunDatar.setOnClickListener(view -> {
            Intent intent = new Intent(mCtx, MateriBangunDatar.class);
            intent.putExtra("namaBangunDatar", bangunDatarList.get(position).getNamaBangunDatar());
            intent.putExtra("thumbBangunDatar", bangunDatarList.get(position).getThumbBangunDatar());
            intent.putExtra("whiteThumbBangunDatar", bangunDatarList.get(position).getWhiteThumbBangunDatar());
            intent.putExtra("descBangunDatar", bangunDatarList.get(position).getDescBangunDatar());
            intent.putExtra("kelilingBangunDatar", bangunDatarList.get(position).getKelilingBangunDatar());
            intent.putExtra("luasBangunDatar", bangunDatarList.get(position).getLuasBangunDatar());
            intent.putExtra("rumusBangunDatar", bangunDatarList.get(position).getRumusBangunDatar());

            mCtx.startActivity(intent);
        });
    }

    @Override
    public int getItemCount(){
        return bangunDatarList.size();
    }


    static class BangunDatarViewHolder extends RecyclerView.ViewHolder{

        TextView textViewBangunDatar;
        ImageView imageBangunDatar;
        CardView cardViewBangunDatar;

        public BangunDatarViewHolder(View itemView) {
            super(itemView);

            textViewBangunDatar = itemView.findViewById(R.id.textViewBangunDatar);
            imageBangunDatar = itemView.findViewById(R.id.imageBangunDatar);
            cardViewBangunDatar = itemView.findViewById(R.id.cardViewBangunDatar);
        }

    }
}
