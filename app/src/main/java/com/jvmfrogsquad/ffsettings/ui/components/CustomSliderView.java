package com.jvmfrogsquad.ffsettings.ui.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.google.android.material.slider.Slider;
import com.google.android.material.textview.MaterialTextView;
import com.jvmfrogsquad.ffsettings.R;

public class CustomSliderView extends LinearLayout {

    private MaterialTextView titleTextView;
    private MaterialTextView valueTextView;
    private Slider materialSlider;

    public CustomSliderView(Context context) {
        this(context, null);
    }

    public CustomSliderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSliderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);

        // Инфлейтим макет
        LayoutInflater.from(context).inflate(R.layout.custom_slider_view, this, true);

        titleTextView = findViewById(R.id.titleTextView);
        valueTextView = findViewById(R.id.valueTextView);
        materialSlider = findViewById(R.id.materialSlider);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomSliderView);
        String titleText = typedArray.getString(R.styleable.CustomSliderView_titleText);
        float value = typedArray.getFloat(R.styleable.CustomSliderView_value, 0.0f);
        boolean enabled = typedArray.getBoolean(R.styleable.CustomSliderView_enabled, true);

        typedArray.recycle();

        setTitle(titleText);
        setValue(value);
        setEnabled(enabled);
    }

    public void setTitle(String title) {
        titleTextView.setText(title);
    }

    public void setValue(float value) {
        int intValue = (int) value;
        valueTextView.setText(String.valueOf(intValue));
        materialSlider.setValue((float) value);
    }

    public float getValue() {
        return materialSlider.getValue();
    }

    public void setEnabled(boolean enabled) {
        materialSlider.setEnabled(enabled);
    }
}