package com.qiu.move_examine.presenter.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.qiu.move_examine.R;
import com.qiu.move_examine.common.base.BaseActivity;
import com.satsoftec.frame.executer.BaseExecuter;

/**
 * @author Mr.Qiu
 */
public class SearchActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private static final String TAG = "SearchActivity";
    private RadioGroup target_radioGroup, monitor_radioGroup;
    private RadioButton rb_person, rb_car, rb_thing, rb_arrest, rb_intercept, rb_notice;
    private LinearLayout layout_type_person, layout_type_car, layout_type_thing;
    private EditText person_et, personNum_et, carNum_et, thingName_et, thingShape_et,
            car_name, car_color, thing_color;

    private Spinner spinner_sex, spinner_figure;
    private ArrayAdapter<CharSequence> adapterSex, adapterFigure, adapterCarName, adapterCarColor, adapterThingColor;

    @Override
    public BaseExecuter initExecutor() {
        return null;
    }

    @Override
    protected void init() {
        setContent(R.layout.activity_search);
    }

    @Override
    protected void initView() {
        setTitle("高级搜索");
        target_radioGroup = findViewById(R.id.target_radioGroup);
        monitor_radioGroup = findViewById(R.id.monitor_radioGroup);
        rb_person = findViewById(R.id.rb_person);
        rb_car = findViewById(R.id.rb_car);
        rb_thing = findViewById(R.id.rb_thing);
        rb_arrest = findViewById(R.id.rb_arrest);
        rb_intercept = findViewById(R.id.rb_intercept);
        rb_notice = findViewById(R.id.rb_notice);
        layout_type_person = findViewById(R.id.layout_type_person);
        layout_type_car = findViewById(R.id.layout_type_car);
        layout_type_thing = findViewById(R.id.layout_type_thing);
        person_et = findViewById(R.id.person_et);
        personNum_et = findViewById(R.id.personNum_et);
        carNum_et = findViewById(R.id.carNum_et);
        thingName_et = findViewById(R.id.thingName_et);
        thingShape_et = findViewById(R.id.thingShape_et);

        spinner_sex = findViewById(R.id.spinner_sex);
        spinner_figure = findViewById(R.id.spinner_figure);
        car_name = findViewById(R.id.car_name);
        car_color = findViewById(R.id.car_color);
        thing_color = findViewById(R.id.thing_color);

        target_radioGroup.setOnCheckedChangeListener(this);
        rb_person.setChecked(true);
        rb_arrest.setChecked(true);
        findViewById(R.id.bt_search).setOnClickListener(this);

        adapterSex = ArrayAdapter.createFromResource(this, R.array.sex_array, R.layout.spinner_default);
        adapterFigure = ArrayAdapter.createFromResource(this, R.array.figure_array, R.layout.spinner_default);
        adapterCarName = ArrayAdapter.createFromResource(this, R.array.car_name_array, R.layout.spinner_default);
        adapterCarColor = ArrayAdapter.createFromResource(this, R.array.car_name_color, R.layout.spinner_default);
        adapterThingColor = ArrayAdapter.createFromResource(this, R.array.thing_name_color, R.layout.spinner_default);
        adapterSex.setDropDownViewResource(R.layout.spinner_dropdown);
        adapterFigure.setDropDownViewResource(R.layout.spinner_dropdown);
        adapterCarName.setDropDownViewResource(R.layout.spinner_dropdown);
        adapterCarColor.setDropDownViewResource(R.layout.spinner_dropdown);
        adapterThingColor.setDropDownViewResource(R.layout.spinner_dropdown);
        spinner_sex.setAdapter(adapterSex);
        spinner_figure.setAdapter(adapterFigure);
        spinner_sex.setSelection(0);
        spinner_figure.setSelection(0);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_person:
                layout_type_person.setVisibility(View.VISIBLE);
                layout_type_car.setVisibility(View.GONE);
                layout_type_thing.setVisibility(View.GONE);
                break;
            case R.id.rb_car:
                layout_type_person.setVisibility(View.GONE);
                layout_type_car.setVisibility(View.VISIBLE);
                layout_type_thing.setVisibility(View.GONE);
                break;
            case R.id.rb_thing:
                layout_type_person.setVisibility(View.GONE);
                layout_type_car.setVisibility(View.GONE);
                layout_type_thing.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_search:
                toSearch();
                break;
            default:
                break;
        }
    }

    private void toSearch() {
        String condition = "";
        String targetType = "01";
        String monitorType = "01";

        switch (target_radioGroup.getCheckedRadioButtonId()) {
            case R.id.rb_person:
                targetType = "01";
                break;
            case R.id.rb_car:
                targetType = "02";
                break;
            case R.id.rb_thing:
                targetType = "03";
                break;
            default:
                break;
        }
        switch (monitor_radioGroup.getCheckedRadioButtonId()) {
            case R.id.rb_arrest:
                monitorType = "01";
                break;
            case R.id.rb_intercept:
                monitorType = "02";
                break;
            case R.id.rb_notice:
                monitorType = "03";
                break;
            default:
                break;
        }

        switch (targetType) {
            case "01":
                String perName = person_et.getText().toString();
                String perIdNo = personNum_et.getText().toString();
                String perSex = "";
                switch (spinner_sex.getSelectedItemPosition()) {
                    case 0:
                        perSex = "01";
                        break;
                    case 1:
                        perSex = "02";
                        break;
                    case 2:
                        perSex = "03";
                        break;
                    default:
                        break;
                }
                String perFigure = "";
                switch (spinner_figure.getSelectedItemPosition()) {
                    case 0:
                        perFigure = "01";
                        break;
                    case 1:
                        perFigure = "02";
                        break;
                    default:
                        break;
                }
                condition = "target_type = '" + targetType + "' and monitor_type = '" + monitorType +
                        (TextUtils.isEmpty(perName) ? "" : "' and per_name like '%" + perName + "%") +
                        (TextUtils.isEmpty(perIdNo) ? "" : "' and per_id_no = '" + perIdNo) +
                        "' and per_sex = '" + perSex + "' and per_figure = '" + perFigure + "'";
                break;
            case "02":
                String carNo = carNum_et.getText().toString();
                String brand = car_name.getText().toString();
                String carColor = car_color.getText().toString();

                condition = "target_type = '" + targetType + "' and monitor_type = '" + monitorType +
                        (TextUtils.isEmpty(carNo) ? "" : "' and car_no = '" + carNo) +
                        (TextUtils.isEmpty(brand) ? "" : "' and brand like '%" + brand + "%") +
                        (TextUtils.isEmpty(carColor) ? "" : "' and color = '" + carColor) + "'";
                break;
            case "03":
                String thingName = thingName_et.getText().toString();
                String thingShape = thingShape_et.getText().toString();
                String thingColor = thing_color.getText().toString();

                condition = "target_type = '" + targetType + "' and monitor_type = '" + monitorType +
                        (TextUtils.isEmpty(thingName) ? "" : "' and items like '%" + thingName + "%") +
                        (TextUtils.isEmpty(thingShape) ? "" : "' and shape = '" + thingShape) +
                        (TextUtils.isEmpty(thingColor) ? "" : "' and color = '" + thingColor) + "'";
                break;
            default:
                break;
        }
        Intent intent = new Intent(mContext, SearchResultActivity.class);
        intent.putExtra("condition", condition);
        startActivity(intent);
        finish();
    }
}
