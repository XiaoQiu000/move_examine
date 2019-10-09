package com.qiu.move_examine.presenter.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiu.move_examine.R;
import com.qiu.move_examine.common.base.BaseRcAdapterEx;
import com.qiu.move_examine.common.bean.CarBean;
import com.qiu.move_examine.common.bean.PersonBean;
import com.qiu.move_examine.common.bean.ThingsBean;
import com.qiu.move_examine.common.utils.PicassoUtils;

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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        MessageBean bean = getItems().get(i);
        switch (bean.getTargetType()) {
            case "人":
                PersonBean person = bean.getPerson();
                holder.nameTv.setText("姓名：" + person.getPerName());
                holder.featureTv.setText("性别：" + person.getPerSex() + "  " + person.getPerFigure());
                holder.elseTv.setText("身份证号：" + person.getPerIdNo());
                break;
            case "车":
                CarBean car = bean.getCar();
                holder.nameTv.setText("品牌：" + car.getBrand());
                holder.featureTv.setText("颜色：" + car.getColor());
                holder.elseTv.setText("车牌号：" + car.getCarNo());
                break;
            case "物":
                ThingsBean things = bean.getThings();
                holder.nameTv.setText("物品：" + things.getItems());
                holder.featureTv.setText("颜色：" + things.getColor());
                holder.elseTv.setText("形状：" + things.getShape());
                break;
            default:
                break;
        }
        if (bean.isRead()) {
            holder.readIv.setVisibility(View.INVISIBLE);
        } else {
            holder.readIv.setVisibility(View.VISIBLE);
        }
        byte [] input = Base64.decode(bean.getCover(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(input, 0, input.length);
        holder.coverIv.setImageBitmap(bitmap);
//        PicassoUtils.getinstance().loadImage(context, bean.getCover(), holder.coverIv, R.mipmap.default_image);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView coverIv, readIv;
        private TextView nameTv, featureTv, elseTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            coverIv = itemView.findViewById(R.id.coverIv);
            readIv = itemView.findViewById(R.id.readIv);
            nameTv = itemView.findViewById(R.id.nameTv);
            featureTv = itemView.findViewById(R.id.featureTv);
            elseTv = itemView.findViewById(R.id.elseTv);
        }
    }


    public static class MessageBean {
        private int id;
        private String targetType;//目标类型:01人02车03物
        private String monitorType;//布控类型:01抓捕02拦截03通知
        private String cover;//图片
        private String pushTime;

        private PersonBean person;//人
        private CarBean car;//车
        private ThingsBean things;//物

        private boolean isRead;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTargetType() {
            return targetType;
        }

        public void setTargetType(String targetType) {
            this.targetType = targetType;
        }

        public String getMonitorType() {
            return monitorType;
        }

        public void setMonitorType(String monitorType) {
            this.monitorType = monitorType;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }


        public String getPushTime() {
            return pushTime;
        }

        public void setPushTime(String pushTime) {
            this.pushTime = pushTime;
        }

        public PersonBean getPerson() {
            return person;
        }

        public void setPerson(PersonBean person) {
            this.person = person;
        }

        public CarBean getCar() {
            return car;
        }

        public void setCar(CarBean car) {
            this.car = car;
        }

        public ThingsBean getThings() {
            return things;
        }

        public void setThings(ThingsBean things) {
            this.things = things;
        }

        public boolean isRead() {
            return isRead;
        }

        public void setRead(boolean read) {
            isRead = read;
        }
    }

}
