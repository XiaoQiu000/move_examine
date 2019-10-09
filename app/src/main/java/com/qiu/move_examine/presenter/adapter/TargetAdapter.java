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
import android.widget.LinearLayout;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        TargetBean bean = getItems().get(i);
        switch (bean.getTargetType()) {
            case "人":
                holder.personLayout.setVisibility(View.VISIBLE);
                holder.carLayout.setVisibility(View.GONE);
                holder.thingLayout.setVisibility(View.GONE);
                PersonBean person = bean.getPerson();
                holder.perNameTv.setText("姓名：" + person.getPerName());
                holder.perFeatureTv.setText(person.getPerSex() + "    " + person.getPerFigure());
                holder.perCardNoTv.setText("身份证：" + person.getPerIdNo());
                break;
            case "车":
                holder.personLayout.setVisibility(View.GONE);
                holder.carLayout.setVisibility(View.VISIBLE);
                holder.thingLayout.setVisibility(View.GONE);
                CarBean car = bean.getCar();
                holder.carNameTv.setText("品牌：" + car.getBrand());
                holder.carColorTv.setText(car.getColor());
                holder.carNumberTv.setText("车牌：" + car.getCarNo());
                break;
            case "物":
                holder.personLayout.setVisibility(View.GONE);
                holder.carLayout.setVisibility(View.GONE);
                holder.thingLayout.setVisibility(View.VISIBLE);
                ThingsBean things = bean.getThings();
                holder.thingNameTv.setText("物品：" + things.getItems());
                holder.thingFeatureTv.setText(things.getColor() + "   " + things.getShape());
                break;
            default:
                break;
        }
        holder.targetTypeTv.setText(bean.getMonitorType());
        switch (bean.getMonitorType()) {
            case "抓捕":
                holder.targetTypeTv.setBackgroundResource(R.drawable.shape_text_arrest);
                break;
            case "拦截":
                holder.targetTypeTv.setBackgroundResource(R.drawable.shape_text_intercept);
                break;
            case "通知":
                holder.targetTypeTv.setBackgroundResource(R.drawable.shape_text_notice);
                break;
            default:
                break;
        }
        byte [] input = Base64.decode(bean.getCover(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(input, 0, input.length);
        holder.coverIv.setImageBitmap(bitmap);
//        PicassoUtils.getinstance().loadImage(context, bean.getCover(), holder.coverIv, R.mipmap.default_image);
        holder.involveCaseTv.setText(bean.getInvolveCase() == null ? "" : bean.getInvolveCase());
        holder.descriptionTv.setText("特征描述：" + (bean.getCharacterDescription() == null ? "" : bean.getCharacterDescription()));
        holder.timeTv.setText(bean.getPushTime() == null ? "" : bean.getPushTime());
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView coverIv;
        private TextView targetTypeTv, involveCaseTv, descriptionTv, timeTv;
        private LinearLayout personLayout, carLayout, thingLayout;
        private TextView perNameTv, perFeatureTv, perCardNoTv;
        private TextView carNameTv, carColorTv, carNumberTv;
        private TextView thingNameTv, thingFeatureTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            coverIv = itemView.findViewById(R.id.coverIv);
            targetTypeTv = itemView.findViewById(R.id.targetTypeTv);
            involveCaseTv = itemView.findViewById(R.id.involveCaseTv);
            descriptionTv = itemView.findViewById(R.id.descriptionTv);
            timeTv = itemView.findViewById(R.id.timeTv);
            personLayout = itemView.findViewById(R.id.personLayout);
            carLayout = itemView.findViewById(R.id.carLayout);
            thingLayout = itemView.findViewById(R.id.thingLayout);
            perNameTv = itemView.findViewById(R.id.perNameTv);
            perFeatureTv = itemView.findViewById(R.id.perFeatureTv);
            perCardNoTv = itemView.findViewById(R.id.perCardNoTv);
            carNameTv = itemView.findViewById(R.id.carNameTv);
            carColorTv = itemView.findViewById(R.id.carColorTv);
            carNumberTv = itemView.findViewById(R.id.carNumberTv);
            thingNameTv = itemView.findViewById(R.id.thingNameTv);
            thingFeatureTv = itemView.findViewById(R.id.thingFeatureTv);
        }
    }


    public static class TargetBean {
        private String id;
        private String monitorType;
        private String targetType;
        private String cover;
        private String involveCase;
        private String characterDescription;
        private String pushTime;

        private PersonBean person;//人
        private CarBean car;//车
        private ThingsBean things;//物

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMonitorType() {
            return monitorType;
        }

        public void setMonitorType(String monitorType) {
            this.monitorType = monitorType;
        }

        public String getTargetType() {
            return targetType;
        }

        public void setTargetType(String targetType) {
            this.targetType = targetType;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getInvolveCase() {
            return involveCase;
        }

        public void setInvolveCase(String involveCase) {
            this.involveCase = involveCase;
        }

        public String getCharacterDescription() {
            return characterDescription;
        }

        public void setCharacterDescription(String characterDescription) {
            this.characterDescription = characterDescription;
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
    }
}
