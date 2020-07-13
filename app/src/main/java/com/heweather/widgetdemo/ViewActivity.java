package com.heweather.widgetdemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class ViewActivity extends AppCompatActivity implements OnClickListener {

    private RecyclerView rcvAdd;
    private RecyclerView rcvAdded;
    private RecyclerView rcvAddedTop;
    private RecyclerView rcvAddedBottom;
    private ArrayList<String> addDatas = new ArrayList<>();
    private ArrayList<String> addedDatas = new ArrayList<>();
    private ArrayList<String> addedTopDatas = new ArrayList<>();
    private ArrayList<String> addedBottomDatas = new ArrayList<>();

    private ArrayList<Integer> paddings = new ArrayList<>();
    private AddViewAdapter addViewAdapter;
    private AddedViewAdapter addedViewAdapter;
    private RadioButton rbLeft;
    private RadioButton rbRight;
    private RadioButton rbDefaultBack;
    private EditText etTextSize;
    private EditText etRadius;
    private RadioButton rbWhite;
    private String type = "h";
    private AddedTopViewAdapter addedTopViewAdapter;
    private AddedBottomViewAdapter addedBottomViewAdapter;
    private TextView tvTop;
    private TextView tvBottom;
    private String nowData = "";
    private EditText etLeft;
    private EditText etTop;
    private EditText etRight;
    private EditText etBottom;
    private EditText etMLeft;
    private EditText etMTop;
    private EditText etMRight;
    private EditText etMBottom;
    private EditText etLargeSize;
    private LinearLayout llLarge;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        intent = new Intent(this, ExampleActivity.class);

        Intent getIntent = getIntent();
        type = getIntent.getStringExtra("type");
        rcvAdd = findViewById(R.id.rcv_add);
        rcvAdded = findViewById(R.id.rcv_added);
        rcvAddedTop = findViewById(R.id.rcv_added_top);
        rcvAddedBottom = findViewById(R.id.rcv_added_bottom);

        tvTop = findViewById(R.id.tv_added_top);
        tvBottom = findViewById(R.id.tv_added_bottom);

        rbLeft = findViewById(R.id.rb_left);
        rbRight = findViewById(R.id.rb_right);
        rbDefaultBack = findViewById(R.id.rb_default_back);
        rbWhite = findViewById(R.id.rb_white);

        etTextSize = findViewById(R.id.et_text_size);
        etLargeSize = findViewById(R.id.et_large_text_size);
        etRadius = findViewById(R.id.et_radius);

        llLarge = findViewById(R.id.ll_large);

        etLeft = findViewById(R.id.et_left);
        etTop = findViewById(R.id.et_top);
        etRight = findViewById(R.id.et_right);
        etBottom = findViewById(R.id.et_bottom);

        etMLeft = findViewById(R.id.et_m_left);
        etMTop = findViewById(R.id.et_m_top);
        etMRight = findViewById(R.id.et_m_right);
        etMBottom = findViewById(R.id.et_m_bottom);

        String loc = "地址文字";
        String weather = "天气文字";
        String weatherIcon = "天气图标";
        String temp = "温度文字";
        String windSc = "风力文字";
        String windDir = "风向图标";
        String aqi = "AQI";
        String airQuality = "空气质量文字";
        String airNum = "空气质量数字";
        String rainIcon = "降雨图标";
        String rainDetail = "降雨描述文字";
        String alarmIcon = "预警图标";
        String alarm = "预警文字";

        addDatas.add(loc);
        addDatas.add(weather);
        addDatas.add(weatherIcon);
        addDatas.add(temp);
        addDatas.add(windSc);
        addDatas.add(windDir);
        addDatas.add(aqi);
        addDatas.add(airQuality);
        addDatas.add(airNum);
        addDatas.add(rainIcon);
        addDatas.add(rainDetail);
        addDatas.add(alarmIcon);
        addDatas.add(alarm);

        addViewAdapter = new AddViewAdapter(this, addDatas);

        addedViewAdapter = new AddedViewAdapter(this, addedDatas);

        addedTopViewAdapter = new AddedTopViewAdapter(this, addedTopDatas);

        addedBottomViewAdapter = new AddedBottomViewAdapter(this, addedBottomDatas);

        GridLayoutManager addViewManager = new GridLayoutManager(this, 3);
        GridLayoutManager addedViewManager = new GridLayoutManager(this, 2);
        GridLayoutManager addedTopViewManager = new GridLayoutManager(this, 2);
        GridLayoutManager addedBottomViewManager = new GridLayoutManager(this, 2);

        rcvAdd.setAdapter(addViewAdapter);
        rcvAdded.setAdapter(addedViewAdapter);
        rcvAddedTop.setAdapter(addedTopViewAdapter);
        rcvAddedBottom.setAdapter(addedBottomViewAdapter);

        rcvAdd.setLayoutManager(addViewManager);
        rcvAdded.setLayoutManager(addedViewManager);
        rcvAddedTop.setLayoutManager(addedTopViewManager);
        rcvAddedBottom.setLayoutManager(addedBottomViewManager);

        rcvAdd.setItemAnimator(new DefaultItemAnimator());
        rcvAdded.setItemAnimator(new DefaultItemAnimator());
        rcvAddedTop.setItemAnimator(new DefaultItemAnimator());
        rcvAddedBottom.setItemAnimator(new DefaultItemAnimator());
        rcvAdd.setNestedScrollingEnabled(false);
        rcvAdded.setNestedScrollingEnabled(false);
        rcvAddedTop.setNestedScrollingEnabled(false);
        rcvAddedBottom.setNestedScrollingEnabled(false);

        switch (type) {
            case "h":
                tvBottom.setVisibility(View.GONE);
                rcvAddedBottom.setVisibility(View.GONE);
                tvTop.setVisibility(View.GONE);
                rcvAddedTop.setVisibility(View.GONE);
                llLarge.setVisibility(View.GONE);
                break;
            case "ll":
                break;
            case "rl":
                break;
            case "v":
                tvBottom.setVisibility(View.GONE);
                llLarge.setVisibility(View.GONE);
                rcvAddedBottom.setVisibility(View.GONE);
                tvTop.setVisibility(View.GONE);
                rcvAddedTop.setVisibility(View.GONE);
                break;
        }

        addViewAdapter.setClickItemListener(new AddViewAdapter.ClickItemListener() {
            @Override
            public void onItemClick(int position, String data) {
                paddings = new ArrayList<>();
                int l = 3;
                int t = 3;
                int r = 3;
                int b = 3;
                if (!TextUtils.isEmpty(etMLeft.getText().toString())) {
                    l = Integer.parseInt(etMLeft.getText().toString());

                }
                if (!TextUtils.isEmpty(etMTop.getText().toString())) {
                    t = Integer.parseInt(etMTop.getText().toString());

                }
                if (!TextUtils.isEmpty(etMRight.getText().toString())) {
                    r = Integer.parseInt(etMRight.getText().toString());

                }
                if (!TextUtils.isEmpty(etMBottom.getText().toString())) {
                    b = Integer.parseInt(etMBottom.getText().toString());

                }
                paddings.add(l);
                paddings.add(t);
                paddings.add(r);
                paddings.add(b);
                intent.putIntegerArrayListExtra(data, paddings);

//                etMLeft.setText("");
//                etMTop.setText("");
//                etMRight.setText("");
//                etMBottom.setText("");

                addDatas.remove(position);
                addViewAdapter.refreshData(addDatas);
                nowData = data;
                if ("h".equalsIgnoreCase(type) || "v".equalsIgnoreCase(type)) {
                    addedDatas.add(data);
                    addedViewAdapter.refreshData(addedDatas);
                } else {
                    rbLeft.post(runnable);
                }
            }
        });

        addedViewAdapter.setClickItemListener(new AddedViewAdapter.ClickItemListener() {
            @Override
            public void onItemClick(int position, String data) {
                addedDatas.remove(position);
                addDatas.add(data);
                addViewAdapter.refreshData(addDatas);
                addedViewAdapter.refreshData(addedDatas);
            }
        });

        addedTopViewAdapter.setClickItemListener(new AddedTopViewAdapter.ClickItemListener() {
            @Override
            public void onItemClick(int position, String data) {
                addedTopDatas.remove(position);
                addDatas.add(data);
                addViewAdapter.refreshData(addDatas);
                addedTopViewAdapter.refreshData(addedTopDatas);
            }
        });

        addedBottomViewAdapter.setClickItemListener(new AddedBottomViewAdapter.ClickItemListener() {
            @Override
            public void onItemClick(int position, String data) {
                addedBottomDatas.remove(position);
                addDatas.add(data);
                addViewAdapter.refreshData(addDatas);
                addedBottomViewAdapter.refreshData(addedBottomDatas);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                final int swipeFlags = 0;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                //得到当拖拽的viewHolder的Position
                int fromPosition = viewHolder.getAdapterPosition();
                //拿到当前拖拽到的item的viewHolder
                int toPosition = target.getAdapterPosition();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(addedDatas, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(addedDatas, i, i - 1);
                    }
                }
                addedViewAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

            }

            @Override
            public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE && viewHolder != null) {
                    viewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.light_click_color));
                }
                super.onSelectedChanged(viewHolder, actionState);

            }

            @Override
            public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(0);
                //重置改变，防止由于复用而导致的显示问题
                viewHolder.itemView.setScrollX(0);
            }

        });
        itemTouchHelper.attachToRecyclerView(rcvAdded);


        Button btEnter = findViewById(R.id.bt_enter);
        btEnter.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        if ("rl".equalsIgnoreCase(type) || "ll".equalsIgnoreCase(type)) {
            intent.putExtra("largeData", addedDatas);
            intent.putExtra("topData", addedTopDatas);
            intent.putExtra("bottomData", addedBottomDatas);
        } else {
            intent.putExtra("data", addedDatas);
        }

        String gravity;
        if (rbRight.isChecked()) {
            gravity = "right";
        } else if (rbLeft.isChecked()) {
            gravity = "left";
        } else {
            gravity = "center";
        }
        boolean isDefaultBack = false;
        if (rbDefaultBack.isChecked()) {
            isDefaultBack = true;
        }
        intent.putExtra("defaultBack", isDefaultBack);
        intent.putExtra("gravity", gravity);

        intent.putExtra("radius", 0);
        if (!TextUtils.isEmpty(etRadius.getText().toString())) {
            int radius = Integer.parseInt(etRadius.getText().toString());
            intent.putExtra("radius", radius);
        }

        intent.putExtra("backColor", Color.parseColor("#313a44"));
        if (!TextUtils.isEmpty(etTextSize.getText().toString())) {
            int size = Integer.parseInt(etTextSize.getText().toString());
            intent.putExtra("size", size);
        }
        if (!TextUtils.isEmpty(etLargeSize.getText().toString())) {
            int size = Integer.parseInt(etLargeSize.getText().toString());
            intent.putExtra("largeSize", size);
        }


        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;

        if (!TextUtils.isEmpty(etLeft.getText().toString())) {
            left = Integer.parseInt(etLeft.getText().toString());
        }
        if (!TextUtils.isEmpty(etTop.getText().toString())) {
            top = Integer.parseInt(etTop.getText().toString());
        }
        if (!TextUtils.isEmpty(etRight.getText().toString())) {
            right = Integer.parseInt(etRight.getText().toString());
        }
        if (!TextUtils.isEmpty(etBottom.getText().toString())) {
            bottom = Integer.parseInt(etBottom.getText().toString());
        }

        intent.putExtra("paddingL", left);
        intent.putExtra("paddingT", top);
        intent.putExtra("paddingR", right);
        intent.putExtra("paddingB", bottom);

        int textColor = Color.BLACK;
        if (rbWhite.isChecked()) {
            textColor = Color.WHITE;
        }
        intent.putExtra("textColor", textColor);


        switch (view.getId()) {
            case R.id.bt_enter:
                intent.putExtra("type", type);
                break;
        }
        startActivity(intent);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            View forecastView = LayoutInflater.from(ViewActivity.this).inflate(R.layout.pop_add, null);
            final PopupWindow popupWindow = new PopupWindow(forecastView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            Button btLarge = forecastView.findViewById(R.id.bt_large);
            Button btTop = forecastView.findViewById(R.id.bt_top);
            Button btBottom = forecastView.findViewById(R.id.bt_bottom);

            btLarge.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    addedDatas.add(nowData);
                    addedViewAdapter.refreshData(addedDatas);
                    popupWindow.dismiss();
                }
            });
            btTop.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    addedTopDatas.add(nowData);
                    addedTopViewAdapter.refreshData(addedTopDatas);
                    popupWindow.dismiss();
                }
            });
            btBottom.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    addedBottomDatas.add(nowData);
                    addedBottomViewAdapter.refreshData(addedBottomDatas);
                    popupWindow.dismiss();
                }
            });


            popupWindow.showAtLocation(rbLeft, Gravity.END, 0, 0);
        }
    };


}
