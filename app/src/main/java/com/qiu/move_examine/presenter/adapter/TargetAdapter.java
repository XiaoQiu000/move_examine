package com.qiu.move_examine.presenter.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qiu.move_examine.R;
import com.qiu.move_examine.common.base.BaseRcAdapterEx;

/**
 * @author Mr.Qiu
 */
public class TargetAdapter extends BaseRcAdapterEx<TargetAdapter.TargetBean, TargetAdapter.MyViewHolder> {
    private LayoutInflater inflater;

    public TargetAdapter(Context context) {
        super(context);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_target, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        TargetBean bean = getItems().get(i);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    public static class TargetBean {
    }
}
