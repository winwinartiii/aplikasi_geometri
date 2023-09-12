package com.example.bangunruang;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NilaiAdapter extends RecyclerView.Adapter<NilaiAdapter.NilaiViewHolder> {

    private List<NilaiItem> nilaiList;

    public NilaiAdapter(List<NilaiItem> nilaiList) {
        this.nilaiList = nilaiList;
    }

    @NonNull
    @Override
    public NilaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_nilai, parent, false);
        return new NilaiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NilaiViewHolder holder, int position) {
        final NilaiItem nilaiItem = nilaiList.get(position);

        holder.tvName.setText(nilaiItem.getName());
        holder.tvClass.setText(nilaiItem.getClassName());
        holder.tvScore.setText(String.valueOf(nilaiItem.getScore()));
    }

    @Override
    public int getItemCount() {
        return nilaiList.size();
    }

    static class NilaiViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvClass;
        TextView tvScore;

        public NilaiViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvClass = itemView.findViewById(R.id.tvClass);
            tvScore = itemView.findViewById(R.id.tvScore);
        }
    }
}
