package com.heweather.widgetdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AddedTopViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> datas;
    private Context context;
    private ClickItemListener clickItemListener;

    public AddedTopViewAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_add_view, null, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        myViewHolder.tvAddView.setText(datas.get(i));
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItemListener.onItemClick(i, datas.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void refreshData(List<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvAddView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAddView = itemView.findViewById(R.id.tv_add_view);
        }
    }

    public void setClickItemListener(ClickItemListener clickItemListener) {
        this.clickItemListener = clickItemListener;
    }

    public interface ClickItemListener {
        void onItemClick(int position, String data);
    }
}
