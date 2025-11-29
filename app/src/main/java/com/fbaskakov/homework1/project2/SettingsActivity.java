package com.fbaskakov.homework1.project2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SettingsActivity extends AppCompatActivity {

    private EditText InputText;
    private SeekBar seekBar;
    private TextView seekBarValue;
    private String value;
    private RadioGroup radioGroup;
    private RadioButton radioRedColor, radioGreenColor, radioBlueColor;
    private CheckBox checkBoxBold, checkBoxItalic;
    private Button buttonNext;
    private int num_color = 0;
    private int num_style = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        setupListeners();

    }

    private void init() {
        InputText = findViewById(R.id.editText);

        seekBar = findViewById(R.id.seekbar_size_text);
        seekBarValue = findViewById(R.id.seekbar_value);
        seekBar.setMax(48);
        seekBar.setMin(12);

        radioGroup = findViewById(R.id.radio_group);

        checkBoxBold = findViewById(R.id.checkBox_bold);
        checkBoxItalic = findViewById(R.id.checkBox_italic);

        buttonNext = findViewById(R.id.button);

        setTextSize();
    }
    private void setupListeners() {
        seekBarMove();
        setTextColor();
        setupTextStyle();
        clickButton();
    }
    private void clickButton () {
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPrewie();
            }
        });
    }
    private void seekBarMove() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setTextSize();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
    private void setTextSize() {
        int size = seekBar.getProgress();
        seekBarValue.setText(size + " sp");
    }
    private void setTextColor() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioBtn_red_color) {
                    num_color = 1;
                } else if (checkedId == R.id.radioBtn_green_color) {
                    num_color = 2;
                } else if (checkedId == R.id.radioBtn_blue_color) {
                    num_color = 3;
                } else {
                    num_color = 0;
                }
            }
        });
    }
    private void setupTextStyle() {
        CompoundButton.OnCheckedChangeListener styleListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                setTypeface();
            }
        };
        checkBoxBold.setOnCheckedChangeListener(styleListener);
        checkBoxItalic.setOnCheckedChangeListener(styleListener);
    }
    private void setTypeface() {
        boolean isBold = checkBoxBold.isChecked();
        boolean isItalic = checkBoxItalic.isChecked();

        if (isBold && isItalic) {
            num_style = 1;
        } else if (isBold) {
            num_style = 2;
        } else if (isItalic) {
            num_style = 3;
        } else {
            num_style = 0;
        }

    }
    private void startPrewie() {
        Intent intent = new Intent(SettingsActivity.this, PreviewActivity.class);
        String userText = InputText.getText().toString().trim();
        intent.putExtra("TEXT", userText);

        int size = seekBar.getProgress();
        intent.putExtra("TEXT_SIZE", size);
        intent.putExtra("COLOR", num_color);
        intent.putExtra("STYLE", num_style);

        startActivity(intent);
    }
}