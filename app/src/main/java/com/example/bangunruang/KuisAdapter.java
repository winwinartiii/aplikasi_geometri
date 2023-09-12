package com.example.bangunruang;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KuisAdapter extends RecyclerView.Adapter<KuisAdapter.KuisViewHolder> {

    private final List<KuisItem> kuisList;

    public KuisAdapter(List<KuisItem> kuisList) {
        this.kuisList = kuisList;
    }

    public List<KuisItem> getKuisList() {
        return kuisList;
    }

    @NonNull
    @Override
    public KuisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_kuis, parent, false);
        return new KuisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KuisViewHolder holder, int position) {
        final KuisItem kuisItem = kuisList.get(position);

        if (kuisItem.getAnswerId() == 0) {
            holder.rgKuis.clearCheck();
        }

        if (kuisItem.getAnswerId() == 1) {
            holder.rb1.setChecked(true);
        }

        if (kuisItem.getAnswerId() == 2) {
            holder.rb2.setChecked(true);
        }

        if (kuisItem.getAnswerId() == 3) {
            holder.rb3.setChecked(true);
        }

        if (kuisItem.getAnswerId() == 4) {
            holder.rb4.setChecked(true);
        }

        holder.tvNumber.setText(String.valueOf(position + 1));
        holder.tvQuestion.setText(kuisItem.getQuestion());
        holder.rb1.setText(kuisItem.getOptions().get(0));
        holder.rb2.setText(kuisItem.getOptions().get(1));
        holder.rb3.setText(kuisItem.getOptions().get(2));
        holder.rb4.setText(kuisItem.getOptions().get(3));

        holder.rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kuisItem.setAnswerId(1);
            }
        });

        holder.rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kuisItem.setAnswerId(2);
            }
        });

        holder.rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kuisItem.setAnswerId(3);
            }
        });

        holder.rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kuisItem.setAnswerId(4);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kuisList.size();
    }

    static class KuisViewHolder extends RecyclerView.ViewHolder {

        TextView tvNumber;
        TextView tvQuestion;
        RadioGroup rgKuis;
        RadioButton rb1;
        RadioButton rb2;
        RadioButton rb3;
        RadioButton rb4;

        public KuisViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvQuestion = itemView.findViewById(R.id.tvQuestion);
            rgKuis = itemView.findViewById(R.id.rgKuis);
            rb1 = itemView.findViewById(R.id.rb1);
            rb2 = itemView.findViewById(R.id.rb2);
            rb3 = itemView.findViewById(R.id.rb3);
            rb4 = itemView.findViewById(R.id.rb4);
        }
    }

}
