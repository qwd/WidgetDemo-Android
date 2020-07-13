package com.heweather.widgetdemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.heweather.widget.view.HeWeatherConfig;
import com.heweather.widget.view.HorizonView;
import com.heweather.widget.view.LeftLargeView;
import com.heweather.widget.view.RightLargeView;
import com.heweather.widget.view.VerticalView;

import java.util.ArrayList;

public class ExampleActivity extends AppCompatActivity {

    private HorizonView horizonView;
    private LeftLargeView llView;
    private RightLargeView rlView;
    private VerticalView verticalView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        //初始化
        HeWeatherConfig.init("注册的key", "需要的location");

        //横向布局
        horizonView = findViewById(R.id.horizon_view);
        //左侧大布局
        llView = findViewById(R.id.ll_view);

        rlView = findViewById(R.id.rl_view);
        verticalView = findViewById(R.id.vertical_view);

        Intent intent = getIntent();

        horizonView.setDefaultBack(false);
        horizonView.setViewTextColor(Color.BLACK);

        verticalView.setDefaultBack(false);

        //获取左侧大布局
        LinearLayout leftLayout = llView.getLeftLayout();
        //获取右上布局
        LinearLayout rightTopLayout = llView.getRightTopLayout();
        //获取右下布局
        LinearLayout rightBottomLayout = llView.getRightBottomLayout();
        //设置布局的背景圆角角度，颜色，边框宽度，边框颜色
        llView.setStroke(5, Color.parseColor("#313a44"), 1, Color.BLACK);

        LinearLayout rightLayout = rlView.getRightLayout();
        LinearLayout leftTopLayout = rlView.getLeftTopLayout();
        LinearLayout leftBottomLayout = rlView.getLeftBottomLayout();
        rlView.setDefaultBack(false);
        rlView.setStroke(0, Color.GRAY, 1, Color.WHITE);


        if (intent != null) {

            int paddingL = intent.getIntExtra("paddingL", 0);
            int paddingT = intent.getIntExtra("paddingT", 0);
            int paddingR = intent.getIntExtra("paddingR", 0);
            int paddingB = intent.getIntExtra("paddingB", 0);

            horizonView.setViewPadding(paddingL, paddingT, paddingR, paddingB);
            rlView.setViewPadding(paddingL, paddingT, paddingR, paddingB);
            llView.setViewPadding(paddingL, paddingT, paddingR, paddingB);
            verticalView.setViewPadding(paddingL, paddingT, paddingR, paddingB);

            String gravity = intent.getStringExtra("gravity");
            llView.setViewGravity(gravity);
            horizonView.setViewGravity(gravity);
            rlView.setViewGravity(gravity);

            boolean defaultBack = intent.getBooleanExtra("defaultBack", false);
            rlView.setDefaultBack(defaultBack);
            verticalView.setDefaultBack(defaultBack);
            llView.setDefaultBack(defaultBack);
            horizonView.setDefaultBack(defaultBack);

            int backColor = intent.getIntExtra("backColor", Color.parseColor("#313a44"));
            int radius = intent.getIntExtra("radius", 0);
            rlView.setStroke(radius, backColor, 1, Color.WHITE);
            llView.setStroke(radius, backColor, 1, Color.WHITE);
            horizonView.setStroke(radius, backColor, 1, Color.WHITE);
            verticalView.setStroke(radius, backColor, 1, Color.WHITE);

            int textColor = intent.getIntExtra("textColor", Color.WHITE);
            horizonView.setViewTextColor(textColor);
            verticalView.setViewTextColor(textColor);
            llView.setViewTextColor(textColor);
            rlView.setViewTextColor(textColor);

            int textSize = intent.getIntExtra("size", 14);
            int largeSize = intent.getIntExtra("largeSize", 40);

            ArrayList<Integer> locList = intent.getIntegerArrayListExtra("地址文字");
            ArrayList<Integer> condList = intent.getIntegerArrayListExtra("天气文字");
            ArrayList<Integer> weatherIconList = intent.getIntegerArrayListExtra("天气图标");
            ArrayList<Integer> tempList = intent.getIntegerArrayListExtra("温度文字");
            ArrayList<Integer> windList = intent.getIntegerArrayListExtra("风力文字");
            ArrayList<Integer> windIconList = intent.getIntegerArrayListExtra("风向图标");
            ArrayList<Integer> aqiList = intent.getIntegerArrayListExtra("AQI");
            ArrayList<Integer> airList = intent.getIntegerArrayListExtra("空气质量文字");
            ArrayList<Integer> airNumList = intent.getIntegerArrayListExtra("空气质量数字");
            ArrayList<Integer> rainIconList = intent.getIntegerArrayListExtra("降雨图标");
            ArrayList<Integer> rainDetailList = intent.getIntegerArrayListExtra("降雨描述文字");
            ArrayList<Integer> alarmIconList = intent.getIntegerArrayListExtra("预警图标");
            ArrayList<Integer> alarmTextList = intent.getIntegerArrayListExtra("预警文字");


            ArrayList<String> data = intent.getStringArrayListExtra("data");
            if (data != null && data.size() > 0)
                for (int i = 0; i < data.size(); i++) {
                    String datum = data.get(i);
                    switch (datum) {
                        case "地址文字":
                            if (locList != null && locList.size() == 4) {
                                horizonView.addLocation(textSize, textColor, locList.get(0), locList.get(1), locList.get(2), locList.get(3));
                                verticalView.addLocation(textSize, textColor, locList.get(0), locList.get(1), locList.get(2), locList.get(3));
                            } else {
                                horizonView.addLocation(textSize, textColor);
                                verticalView.addLocation(textSize, textColor);
                            }
                            break;
                        case "天气文字":
                            if (condList != null && condList.size() == 4) {
                                horizonView.addCond(textSize, textColor, condList.get(0), condList.get(1), condList.get(2), condList.get(3));
                                verticalView.addCond(textSize, textColor, condList.get(0), condList.get(1), condList.get(2), condList.get(3));
                            } else {
                                verticalView.addCond(textSize, textColor);
                                horizonView.addCond(textSize, textColor);
                            }
                            break;
                        case "天气图标":
                            if (weatherIconList != null && weatherIconList.size() == 4) {
                                horizonView.addWeatherIcon(textSize, weatherIconList.get(0), weatherIconList.get(1), weatherIconList.get(2), weatherIconList.get(3));
                                verticalView.addWeatherIcon(textSize, weatherIconList.get(0), weatherIconList.get(1), weatherIconList.get(2), weatherIconList.get(3));
                            } else {
                                verticalView.addWeatherIcon(textSize);
                                horizonView.addWeatherIcon(textSize);
                            }
                            break;
                        case "温度文字":
                            if (tempList != null && tempList.size() == 4) {
                                horizonView.addTemp(textSize, textColor, tempList.get(0), tempList.get(1), tempList.get(2), tempList.get(3));
                                verticalView.addTemp(textSize, textColor, tempList.get(0), tempList.get(1), tempList.get(2), tempList.get(3));
                            } else {
                                verticalView.addTemp(textSize, textColor);
                                horizonView.addTemp(textSize, textColor);
                            }
                            break;
                        case "风力文字":
                            if (windList != null && windList.size() == 4) {
                                horizonView.addWind(textSize, textColor, windList.get(0), windList.get(1), windList.get(2), windList.get(3));
                                verticalView.addWind(textSize, textColor, windList.get(0), windList.get(1), windList.get(2), windList.get(3));
                            } else {
                                verticalView.addWind(textSize, textColor);
                                horizonView.addWind(textSize, textColor);
                            }
                            break;
                        case "风向图标":
                            if (windIconList != null && windIconList.size() == 4) {
                                horizonView.addWindIcon(textSize, windIconList.get(0), windIconList.get(1), windIconList.get(2), windIconList.get(3));
                                verticalView.addWindIcon(textSize, windIconList.get(0), windIconList.get(1), windIconList.get(2), windIconList.get(3));
                            } else {
                                horizonView.addWindIcon(textSize);
                                verticalView.addWindIcon(textSize);
                            }
                            break;
                        case "AQI":
                            if (aqiList != null && aqiList.size() == 4) {
                                horizonView.addAqiText(textSize, textColor, aqiList.get(0), aqiList.get(1), aqiList.get(2), aqiList.get(3));
                                verticalView.addAqiText(textSize, textColor, aqiList.get(0), aqiList.get(1), aqiList.get(2), aqiList.get(3));
                            } else {
                                horizonView.addAqiText(textSize, textColor);
                                verticalView.addAqiText(textSize, textColor);
                            }
                            break;
                        case "空气质量文字":
                            if (airList != null && airList.size() == 4) {
                                horizonView.addAqiQlty(textSize, airList.get(0), airList.get(1), airList.get(2), airList.get(3));
                                verticalView.addAqiQlty(textSize, airList.get(0), airList.get(1), airList.get(2), airList.get(3));
                            } else {
                                horizonView.addAqiQlty(textSize);
                                verticalView.addAqiQlty(textSize);
                            }
                            break;
                        case "空气质量数字":
                            if (airNumList != null && airNumList.size() == 4) {
                                horizonView.addAqiNum(textSize, airNumList.get(0), airNumList.get(1), airNumList.get(2), airNumList.get(3));
                                verticalView.addAqiNum(textSize, airNumList.get(0), airNumList.get(1), airNumList.get(2), airNumList.get(3));
                            } else {
                                verticalView.addAqiNum(textSize);
                                horizonView.addAqiNum(textSize);
                            }
                            break;
                        case "降雨图标":
                            if (rainIconList != null && rainIconList.size() == 4) {
                                horizonView.addRainIcon(textSize, rainIconList.get(0), rainIconList.get(1), rainIconList.get(2), rainIconList.get(3));
                                verticalView.addRainIcon(textSize, rainIconList.get(0), rainIconList.get(1), rainIconList.get(2), rainIconList.get(3));
                            } else {
                                verticalView.addRainIcon(textSize);
                                horizonView.addRainIcon(textSize);
                            }
                            break;
                        case "降雨描述文字":
                            if (rainDetailList != null && rainDetailList.size() == 4) {
                                horizonView.addRainDetail(textSize, textColor, rainDetailList.get(0), rainDetailList.get(1), rainDetailList.get(2), rainDetailList.get(3));
                                verticalView.addRainDetail(textSize, textColor, rainDetailList.get(0), rainDetailList.get(1), rainDetailList.get(2), rainDetailList.get(3));
                            } else {
                                verticalView.addRainDetail(textSize, textColor);
                                horizonView.addRainDetail(textSize, textColor);
                            }
                            break;
                        case "预警图标":
                            if (alarmIconList != null && alarmIconList.size() == 4) {
                                horizonView.addAlarmIcon(textSize, alarmIconList.get(0), alarmIconList.get(1), alarmIconList.get(2), alarmIconList.get(3));
                                verticalView.addAlarmIcon(textSize, alarmIconList.get(0), alarmIconList.get(1), alarmIconList.get(2), alarmIconList.get(3));
                            } else {
                                verticalView.addAlarmIcon(textSize);
                                horizonView.addAlarmIcon(textSize);
                            }
                            break;
                        case "预警文字":
                            if (alarmTextList != null && alarmTextList.size() == 4) {
                                horizonView.addAlarmTxt(textSize, alarmTextList.get(0), alarmTextList.get(1), alarmTextList.get(2), alarmTextList.get(3));
                                verticalView.addAlarmTxt(textSize, alarmTextList.get(0), alarmTextList.get(1), alarmTextList.get(2), alarmTextList.get(3));
                            } else {
                                verticalView.addAlarmTxt(textSize);
                                horizonView.addAlarmTxt(textSize);
                            }
                            break;
                    }

                }


            ArrayList<String> largeData = intent.getStringArrayListExtra("largeData");
            ArrayList<String> topData = intent.getStringArrayListExtra("topData");
            ArrayList<String> bottomData = intent.getStringArrayListExtra("bottomData");

            if (topData != null && topData.size() > 0) {
                for (int i = 0; i < topData.size(); i++) {
                    String datum = topData.get(i);
                    switch (datum) {
                        case "地址文字":
                            if (locList != null && locList.size() == 4) {
                                llView.addLocation(rightTopLayout, textSize, textColor, locList.get(0), locList.get(1), locList.get(2), locList.get(3));
                                rlView.addLocation(leftTopLayout, textSize, textColor, locList.get(0), locList.get(1), locList.get(2), locList.get(3));
                            } else {
                                llView.addLocation(rightTopLayout, textSize, textColor);
                                rlView.addLocation(leftTopLayout, textSize, textColor);
                            }
                            break;
                        case "天气文字":
                            if (condList != null && condList.size() == 4) {
                                rlView.addCond(leftTopLayout, textSize, textColor, condList.get(0), condList.get(1), condList.get(2), condList.get(3));
                                llView.addCond(rightTopLayout, textSize, textColor, condList.get(0), condList.get(1), condList.get(2), condList.get(3));
                            } else {
                                rlView.addCond(leftTopLayout, textSize, textColor);
                                llView.addCond(rightTopLayout, textSize, textColor);
                            }
                            break;
                        case "天气图标":
                            if (weatherIconList != null && weatherIconList.size() == 4) {
                                rlView.addWeatherIcon(leftTopLayout, textSize, weatherIconList.get(0), weatherIconList.get(1), weatherIconList.get(2), weatherIconList.get(3));
                                llView.addWeatherIcon(rightTopLayout, textSize, weatherIconList.get(0), weatherIconList.get(1), weatherIconList.get(2), weatherIconList.get(3));
                            } else {
                                rlView.addWeatherIcon(leftTopLayout, textSize);
                                llView.addWeatherIcon(rightTopLayout, textSize);
                            }
                            break;
                        case "温度文字":
                            if (tempList != null && tempList.size() == 4) {
                                rlView.addTemp(leftTopLayout, textSize, textColor, tempList.get(0), tempList.get(1), tempList.get(2), tempList.get(3));
                                llView.addTemp(rightTopLayout, textSize, textColor, tempList.get(0), tempList.get(1), tempList.get(2), tempList.get(3));
                            } else {
                                rlView.addTemp(leftTopLayout, textSize, textColor);
                                llView.addTemp(rightTopLayout, textSize, textColor);
                            }
                            break;
                        case "风力文字":
                            if (windList != null && windList.size() == 4) {
                                rlView.addWind(leftTopLayout, textSize, textColor, windList.get(0), windList.get(1), windList.get(2), windList.get(3));
                                llView.addWind(rightTopLayout, textSize, textColor, windList.get(0), windList.get(1), windList.get(2), windList.get(3));
                            } else {
                                rlView.addWind(leftTopLayout, textSize, textColor);
                                llView.addWind(rightTopLayout, textSize, textColor);
                            }
                            break;
                        case "风向图标":
                            if (windIconList != null && windIconList.size() == 4) {
                                rlView.addWindIcon(leftTopLayout, textSize, windIconList.get(0), windIconList.get(1), windIconList.get(2), windIconList.get(3));
                                llView.addWindIcon(rightTopLayout, textSize, windIconList.get(0), windIconList.get(1), windIconList.get(2), windIconList.get(3));
                            } else {
                                rlView.addWindIcon(leftTopLayout, textSize);
                                llView.addWindIcon(rightTopLayout, textSize);
                            }
                            break;
                        case "AQI":
                            if (aqiList != null && aqiList.size() == 4) {
                                rlView.addAqiText(rightTopLayout, textSize, aqiList.get(0), aqiList.get(1), aqiList.get(2), aqiList.get(3));
                                llView.addAqiText(rightTopLayout, textSize, aqiList.get(0), aqiList.get(1), aqiList.get(2), aqiList.get(3));
                            } else {
                                llView.addAqiText(rightTopLayout, textSize);
                                rlView.addAqiText(leftTopLayout, textSize);
                            }
                            break;
                        case "空气质量文字":
                            if (airList != null && airList.size() == 4) {
                                rlView.addAqiQlty(leftTopLayout, textSize, airList.get(0), airList.get(1), airList.get(2), airList.get(3));
                                llView.addAqiQlty(rightTopLayout, textSize, airList.get(0), airList.get(1), airList.get(2), airList.get(3));
                            } else {
                                llView.addAqiQlty(rightTopLayout, textSize);
                                rlView.addAqiQlty(leftTopLayout, textSize);
                            }
                            break;
                        case "空气质量数字":
                            if (airNumList != null && airNumList.size() == 4) {
                                llView.addAqiNum(rightTopLayout, textSize, airNumList.get(0), airNumList.get(1), airNumList.get(2), airNumList.get(3));
                                rlView.addAqiNum(leftTopLayout, textSize, airNumList.get(0), airNumList.get(1), airNumList.get(2), airNumList.get(3));
                            } else {
                                llView.addAqiNum(rightTopLayout, textSize);
                                rlView.addAqiNum(leftTopLayout, textSize);
                            }
                            break;
                        case "降雨图标":
                            if (alarmIconList != null && alarmIconList.size() == 4) {
                                rlView.addAlarmIcon(leftTopLayout, textSize, alarmIconList.get(0), alarmIconList.get(1), alarmIconList.get(2), alarmIconList.get(3));
                                llView.addAlarmIcon(rightTopLayout, textSize, alarmIconList.get(0), alarmIconList.get(1), alarmIconList.get(2), alarmIconList.get(3));
                            } else {
                                rlView.addRainIcon(leftTopLayout, textSize);
                                llView.addRainIcon(rightTopLayout, textSize);
                            }
                            break;
                        case "降雨描述文字":
                            if (rainDetailList != null && rainDetailList.size() == 4) {
                                rlView.addRainDetail(leftTopLayout, textSize, textColor, rainDetailList.get(0), rainDetailList.get(1), rainDetailList.get(2), rainDetailList.get(3));
                                llView.addRainDetail(rightLayout, textSize, textColor, rainDetailList.get(0), rainDetailList.get(1), rainDetailList.get(2), rainDetailList.get(3));
                            } else {
                                rlView.addRainDetail(leftTopLayout, textSize, textColor);
                                llView.addRainDetail(rightTopLayout, textSize, textColor);
                            }
                            break;
                        case "预警图标":
                            if (alarmIconList != null && alarmIconList.size() == 4) {
                                rlView.addAlarmIcon(leftTopLayout, textSize, alarmIconList.get(0), alarmIconList.get(1), alarmIconList.get(2), alarmIconList.get(3));
                                llView.addAlarmIcon(rightTopLayout, textSize, alarmIconList.get(0), alarmIconList.get(1), alarmIconList.get(2), alarmIconList.get(3));
                            } else {
                                rlView.addAlarmIcon(leftTopLayout, textSize);
                                llView.addAlarmIcon(rightTopLayout, textSize);
                            }
                            break;
                        case "预警文字":
                            if (alarmTextList != null && alarmTextList.size() == 4) {
                                rlView.addAlarmTxt(leftTopLayout, textSize, alarmTextList.get(0), alarmTextList.get(1), alarmTextList.get(2), alarmTextList.get(3));
                                llView.addAlarmTxt(rightTopLayout, textSize, alarmTextList.get(0), alarmTextList.get(1), alarmTextList.get(2), alarmTextList.get(3));
                            } else {
                                rlView.addAlarmTxt(leftTopLayout, textSize);
                                llView.addAlarmTxt(rightTopLayout, textSize);
                            }
                            break;
                    }

                }
            }
            if (bottomData != null && bottomData.size() > 0) {
                for (int i = 0; i < bottomData.size(); i++) {
                    String datum = bottomData.get(i);
                    switch (datum) {
                        case "地址文字":
                            if (locList != null && locList.size() == 4) {
                                llView.addLocation(rightBottomLayout, textSize, textColor, locList.get(0), locList.get(1), locList.get(2), locList.get(3));
                                rlView.addLocation(leftBottomLayout, textSize, textColor, locList.get(0), locList.get(1), locList.get(2), locList.get(3));
                            } else {
                                llView.addLocation(rightBottomLayout, textSize, textColor);
                                rlView.addLocation(leftBottomLayout, textSize, textColor);
                            }
                            break;
                        case "天气文字":
                            if (condList != null && condList.size() == 4) {
                                rlView.addCond(leftBottomLayout, textSize, textColor, condList.get(0), condList.get(1), condList.get(2), condList.get(3));
                                llView.addCond(rightBottomLayout, textSize, textColor, condList.get(0), condList.get(1), condList.get(2), condList.get(3));
                            } else {
                                rlView.addCond(leftBottomLayout, textSize, textColor);
                                llView.addCond(rightBottomLayout, textSize, textColor);
                            }
                            break;
                        case "天气图标":
                            if (weatherIconList != null && weatherIconList.size() == 4) {
                                rlView.addWeatherIcon(leftBottomLayout, textSize, weatherIconList.get(0), weatherIconList.get(1), weatherIconList.get(2), weatherIconList.get(3));
                                llView.addWeatherIcon(rightBottomLayout, textSize, weatherIconList.get(0), weatherIconList.get(1), weatherIconList.get(2), weatherIconList.get(3));
                            } else {
                                rlView.addWeatherIcon(leftBottomLayout, textSize);
                                llView.addWeatherIcon(rightBottomLayout, textSize);
                            }
                            break;
                        case "温度文字":
                            if (tempList != null && tempList.size() == 4) {
                                rlView.addTemp(leftBottomLayout, textSize, textColor, tempList.get(0), tempList.get(1), tempList.get(2), tempList.get(3));
                                llView.addTemp(rightBottomLayout, textSize, textColor, tempList.get(0), tempList.get(1), tempList.get(2), tempList.get(3));
                            } else {
                                rlView.addTemp(leftBottomLayout, textSize, textColor);
                                llView.addTemp(rightBottomLayout, textSize, textColor);
                            }
                            break;
                        case "风力文字":
                            if (windList != null && windList.size() == 4) {
                                rlView.addWind(leftBottomLayout, textSize, textColor, windList.get(0), windList.get(1), windList.get(2), windList.get(3));
                                llView.addWind(rightBottomLayout, textSize, textColor, windList.get(0), windList.get(1), windList.get(2), windList.get(3));
                            } else {
                                rlView.addWind(leftBottomLayout, textSize, textColor);
                                llView.addWind(rightBottomLayout, textSize, textColor);
                            }
                            break;
                        case "风向图标":
                            if (windIconList != null && windIconList.size() == 4) {
                                rlView.addWindIcon(leftBottomLayout, textSize, windIconList.get(0), windIconList.get(1), windIconList.get(2), windIconList.get(3));
                                llView.addWindIcon(rightBottomLayout, textSize, windIconList.get(0), windIconList.get(1), windIconList.get(2), windIconList.get(3));
                            } else {
                                rlView.addWindIcon(leftBottomLayout, textSize);
                                llView.addWindIcon(rightBottomLayout, textSize);
                            }
                            break;
                        case "AQI":
                            if (aqiList != null && aqiList.size() == 4) {
                                rlView.addAqiText(leftBottomLayout, textSize, aqiList.get(0), aqiList.get(1), aqiList.get(2), aqiList.get(3));
                                llView.addAqiText(rightBottomLayout, textSize, aqiList.get(0), aqiList.get(1), aqiList.get(2), aqiList.get(3));
                            } else {
                                llView.addAqiText(rightBottomLayout, textSize);
                                rlView.addAqiText(leftBottomLayout, textSize);
                            }
                            break;
                        case "空气质量文字":
                            if (airList != null && airList.size() == 4) {
                                rlView.addAqiQlty(leftBottomLayout, textSize, airList.get(0), airList.get(1), airList.get(2), airList.get(3));
                                llView.addAqiQlty(rightBottomLayout, textSize, airList.get(0), airList.get(1), airList.get(2), airList.get(3));
                            } else {
                                llView.addAqiQlty(rightBottomLayout, textSize);
                                rlView.addAqiQlty(leftBottomLayout, textSize);
                            }
                            break;
                        case "空气质量数字":
                            if (airNumList != null && airNumList.size() == 4) {
                                llView.addAqiNum(rightBottomLayout, textSize, airNumList.get(0), airNumList.get(1), airNumList.get(2), airNumList.get(3));
                                rlView.addAqiNum(leftBottomLayout, textSize, airNumList.get(0), airNumList.get(1), airNumList.get(2), airNumList.get(3));
                            } else {
                                llView.addAqiNum(rightBottomLayout, textSize);
                                rlView.addAqiNum(leftBottomLayout, textSize);
                            }
                            break;
                        case "降雨图标":
                            if (alarmIconList != null && alarmIconList.size() == 4) {
                                rlView.addAlarmIcon(leftBottomLayout, textSize, alarmIconList.get(0), alarmIconList.get(1), alarmIconList.get(2), alarmIconList.get(3));
                                llView.addAlarmIcon(rightBottomLayout, textSize, alarmIconList.get(0), alarmIconList.get(1), alarmIconList.get(2), alarmIconList.get(3));
                            } else {
                                rlView.addRainIcon(leftBottomLayout, textSize);
                                llView.addRainIcon(rightBottomLayout, textSize);
                            }
                            break;
                        case "降雨描述文字":
                            if (rainDetailList != null && rainDetailList.size() == 4) {
                                rlView.addRainDetail(leftBottomLayout, textSize, textColor, rainDetailList.get(0), rainDetailList.get(1), rainDetailList.get(2), rainDetailList.get(3));
                                llView.addRainDetail(rightBottomLayout, textSize, textColor, rainDetailList.get(0), rainDetailList.get(1), rainDetailList.get(2), rainDetailList.get(3));
                            } else {
                                rlView.addRainDetail(leftBottomLayout, textSize, textColor);
                                llView.addRainDetail(rightBottomLayout, textSize, textColor);
                            }
                            break;
                        case "预警图标":
                            if (alarmIconList != null && alarmIconList.size() == 4) {
                                rlView.addAlarmIcon(leftBottomLayout, textSize, alarmIconList.get(0), alarmIconList.get(1), alarmIconList.get(2), alarmIconList.get(3));
                                llView.addAlarmIcon(rightBottomLayout, textSize, alarmIconList.get(0), alarmIconList.get(1), alarmIconList.get(2), alarmIconList.get(3));
                            } else {
                                rlView.addAlarmIcon(leftBottomLayout, textSize);
                                llView.addAlarmIcon(rightBottomLayout, textSize);
                            }
                            break;
                        case "预警文字":
                            if (alarmTextList != null && alarmTextList.size() == 4) {
                                rlView.addAlarmTxt(leftBottomLayout, textSize, alarmTextList.get(0), alarmTextList.get(1), alarmTextList.get(2), alarmTextList.get(3));
                                llView.addAlarmTxt(rightBottomLayout, textSize, alarmTextList.get(0), alarmTextList.get(1), alarmTextList.get(2), alarmTextList.get(3));
                            } else {
                                rlView.addAlarmTxt(leftBottomLayout, textSize);
                                llView.addAlarmTxt(rightBottomLayout, textSize);
                            }
                            break;
                    }

                }
            }
            if (largeData != null && largeData.size() > 0) {
                for (int i = 0; i < largeData.size(); i++) {
                    String datum = largeData.get(i);
                    switch (datum) {
                        case "地址文字":
                            if (locList != null && locList.size() == 4) {
                                llView.addLocation(leftLayout, largeSize, textColor, locList.get(0), locList.get(1), locList.get(2), locList.get(3));
                                rlView.addLocation(rightLayout, largeSize, textColor, locList.get(0), locList.get(1), locList.get(2), locList.get(3));
                            } else {
                                llView.addLocation(leftLayout, largeSize, textColor);
                                rlView.addLocation(rightLayout, largeSize, textColor);
                            }
                            break;
                        case "天气文字":
                            if (condList != null && condList.size() == 4) {
                                rlView.addCond(rightLayout, largeSize, textColor, condList.get(0), condList.get(1), condList.get(2), condList.get(3));
                                llView.addCond(leftLayout, largeSize, textColor, condList.get(0), condList.get(1), condList.get(2), condList.get(3));
                            } else {
                                llView.addCond(leftLayout, largeSize, textColor);
                                rlView.addCond(rightLayout, largeSize, textColor);
                            }
                            break;
                        case "天气图标":
                            if (weatherIconList != null && weatherIconList.size() == 4) {
                                rlView.addWeatherIcon(rightLayout, largeSize, weatherIconList.get(0), weatherIconList.get(1), weatherIconList.get(2), weatherIconList.get(3));
                                llView.addWeatherIcon(leftLayout, largeSize, weatherIconList.get(0), weatherIconList.get(1), weatherIconList.get(2), weatherIconList.get(3));
                            } else {
                                rlView.addWeatherIcon(rightLayout, largeSize);
                                llView.addWeatherIcon(leftLayout, largeSize);
                            }
                            break;
                        case "温度文字":
                            if (tempList != null && tempList.size() == 4) {
                                rlView.addTemp(rightLayout, largeSize, textColor, tempList.get(0), tempList.get(1), tempList.get(2), tempList.get(3));
                                llView.addTemp(leftLayout, largeSize, textColor, tempList.get(0), tempList.get(1), tempList.get(2), tempList.get(3));
                            } else {
                                rlView.addTemp(rightLayout, largeSize, textColor);
                                llView.addTemp(leftLayout, largeSize, textColor);
                            }
                            break;
                        case "风力文字":
                            if (windList != null && windList.size() == 4) {
                                rlView.addWind(rightLayout, largeSize, textColor, windList.get(0), windList.get(1), windList.get(2), windList.get(3));
                                llView.addWind(leftLayout, largeSize, textColor, windList.get(0), windList.get(1), windList.get(2), windList.get(3));
                            } else {
                                llView.addWind(leftLayout, largeSize, textColor);
                                rlView.addWind(rightLayout, largeSize, textColor);
                            }
                            break;
                        case "风向图标":
                            if (windIconList != null && windIconList.size() == 4) {
                                rlView.addWindIcon(rightLayout, largeSize, windIconList.get(0), windIconList.get(1), windIconList.get(2), windIconList.get(3));
                                llView.addWindIcon(leftLayout, largeSize, windIconList.get(0), windIconList.get(1), windIconList.get(2), windIconList.get(3));
                            } else {
                                llView.addWindIcon(leftLayout, largeSize);
                                rlView.addWindIcon(rightLayout, largeSize);
                            }
                            break;
                        case "AQI":
                            if (aqiList != null && aqiList.size() == 4) {
                                rlView.addAqiText(rightLayout, largeSize, aqiList.get(0), aqiList.get(1), aqiList.get(2), aqiList.get(3));
                                llView.addAqiText(leftLayout, largeSize, aqiList.get(0), aqiList.get(1), aqiList.get(2), aqiList.get(3));
                            } else {
                                llView.addAqiText(leftLayout, largeSize);
                                rlView.addAqiText(rightLayout, largeSize);
                            }
                            break;
                        case "空气质量文字":
                            if (airList != null && airList.size() == 4) {
                                rlView.addAqiQlty(rightLayout, largeSize, airList.get(0), airList.get(1), airList.get(2), airList.get(3));
                                llView.addAqiQlty(leftLayout, largeSize, airList.get(0), airList.get(1), airList.get(2), airList.get(3));
                            } else {
                                llView.addAqiQlty(leftLayout, largeSize);
                                rlView.addAqiQlty(rightLayout, largeSize);
                            }
                            break;
                        case "空气质量数字":
                            if (airNumList != null && airNumList.size() == 4) {
                                llView.addAqiNum(leftLayout, largeSize, airNumList.get(0), airNumList.get(1), airNumList.get(2), airNumList.get(3));
                                rlView.addAqiNum(rightLayout, largeSize, airNumList.get(0), airNumList.get(1), airNumList.get(2), airNumList.get(3));
                            } else {
                                llView.addAqiNum(leftLayout, largeSize);
                                rlView.addAqiNum(rightLayout, largeSize);
                            }
                            break;
                        case "降雨图标":
                            if (alarmIconList != null && alarmIconList.size() == 4) {
                                rlView.addAlarmIcon(rightLayout, largeSize, alarmIconList.get(0), alarmIconList.get(1), alarmIconList.get(2), alarmIconList.get(3));
                                llView.addAlarmIcon(leftLayout, largeSize, alarmIconList.get(0), alarmIconList.get(1), alarmIconList.get(2), alarmIconList.get(3));
                            } else {
                                rlView.addRainIcon(rightLayout, largeSize);
                                llView.addRainIcon(leftLayout, largeSize);
                            }
                            break;
                        case "降雨描述文字":
                            if (rainDetailList != null && rainDetailList.size() == 4) {
                                rlView.addRainDetail(rightLayout, largeSize, textColor, rainDetailList.get(0), rainDetailList.get(1), rainDetailList.get(2), rainDetailList.get(3));
                                llView.addRainDetail(leftLayout, largeSize, textColor, rainDetailList.get(0), rainDetailList.get(1), rainDetailList.get(2), rainDetailList.get(3));
                            } else {
                                rlView.addRainDetail(rightLayout, largeSize, textColor);
                                llView.addRainDetail(leftLayout, largeSize, textColor);
                            }
                            break;
                        case "预警图标":
                            if (alarmIconList != null && alarmIconList.size() == 4) {
                                rlView.addAlarmIcon(rightLayout, largeSize, alarmIconList.get(0), alarmIconList.get(1), alarmIconList.get(2), alarmIconList.get(3));
                                llView.addAlarmIcon(leftLayout, largeSize, alarmIconList.get(0), alarmIconList.get(1), alarmIconList.get(2), alarmIconList.get(3));
                            } else {
                                rlView.addAlarmIcon(leftLayout, largeSize);
                                llView.addAlarmIcon(rightLayout, largeSize);
                            }
                            break;
                        case "预警文字":
                            if (alarmTextList != null && alarmTextList.size() == 4) {
                                rlView.addAlarmTxt(rightLayout, largeSize, alarmTextList.get(0), alarmTextList.get(1), alarmTextList.get(2), alarmTextList.get(3));
                                llView.addAlarmTxt(leftLayout, largeSize, alarmTextList.get(0), alarmTextList.get(1), alarmTextList.get(2), alarmTextList.get(3));
                            } else {
                                rlView.addAlarmTxt(leftLayout, largeSize);
                                llView.addAlarmTxt(rightLayout, largeSize);
                            }
                            break;
                    }

                }
            }

            String type = intent.getStringExtra("type");
            switch (type) {
                case "h":
                    horizonView.setVisibility(View.VISIBLE);
                    horizonView.show();
                    break;
                case "v":
                    verticalView.setVisibility(View.VISIBLE);
                    verticalView.show();
                    break;
                case "rl":
                    rlView.setVisibility(View.VISIBLE);
                    rlView.show();
                    break;
                case "ll":
                    llView.setVisibility(View.VISIBLE);
                    llView.show();
                    break;
            }

        }

    }
}
