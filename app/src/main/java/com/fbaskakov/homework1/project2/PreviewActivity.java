package com.fbaskakov.homework1.project2;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;

public class PreviewActivity extends SettingsActivity {
    private TextView text;
    private int size, color, style;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        setTextValue();
    }

    private void setTextValue() {
        text = findViewById(R.id.textView);

        Intent intent = getIntent();
        text.setText(intent.getStringExtra("TEXT"));

        size = intent.getIntExtra("TEXT_SIZE", 12);
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);

        color = intent.getIntExtra("COLOR",0);
        if (color == 1) {
            text.setTextColor(Color.RED);
        } else if (color == 2) {
            text.setTextColor(Color.GREEN);
        } else if (color == 3) {
            text.setTextColor(Color.BLUE);
        } else {
            text.setTextColor(Color.BLACK);
        }

        style = intent.getIntExtra("STYLE", 0);
        if (style == 1) {
            text.setTypeface(null, Typeface.BOLD_ITALIC);
        } else if (style == 2) {
            text.setTypeface(null, Typeface.BOLD);
        } else if (style == 3) {
            text.setTypeface(null, Typeface.ITALIC);
        }
    }
}
