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
public class MessageAdapter extends BaseRcAdapterEx<MessageAdapter.MessageBean, MessageAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    public MessageAdapter(Context context) {
        super(context);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_message, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        MessageBean bean = getItems().get(i);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    public static class MessageBean {
        private Long noticeId;

        private boolean isRead;

        public Long getNoticeId() {
            return noticeId;
        }

        public void setNoticeId(Long noticeId) {
            this.noticeId = noticeId;
        }

        public boolean isRead() {
            return isRead;
        }

        public void setRead(boolean read) {
            isRead = read;
        }
    }
}
