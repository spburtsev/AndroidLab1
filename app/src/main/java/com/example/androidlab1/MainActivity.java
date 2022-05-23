package com.example.androidlab1;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView title;
    private TableLayout dataTable;
    private int defaultTableTextColor;
    private int defaultTableBackgroundColor;

    private static final String TITLE__KEY = "title";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView headerTextView = findViewById(R.id.headerView);
        defaultTableTextColor = headerTextView
                .getTextColors()
                .getDefaultColor();
        defaultTableBackgroundColor = ((ColorDrawable) headerTextView.getBackground()).getColor();

        title = findViewById(R.id.textView);
        Button toggleTitleButton = findViewById(R.id.button);
        toggleTitleButton.setOnClickListener(l -> {
            if (getString(R.string.greeting).equals(title.getText().toString())) {
                title.setText(R.string.textViewTitle);
                return;
            }
            title.setText(R.string.greeting);
        });

        dataTable = findViewById(R.id.tableLayout);
        CheckBox toggleTableAppearanceCheckbox = findViewById(R.id.checkBox);
        toggleTableAppearanceCheckbox.setOnCheckedChangeListener((CompoundButton compoundButton,
                                                                  boolean isChecked) -> {
            TableRow tableHeader = (TableRow) dataTable.getChildAt(0);
            final int columnsCount = tableHeader.getChildCount();
            int textColorToSet = defaultTableTextColor;
            int bgColorToSet = defaultTableBackgroundColor;

            if (isChecked) {
                textColorToSet = getResources().getColor(R.color.colorPrimaryLight);
                bgColorToSet = getResources().getColor(R.color.colorAccentLighter);
            }

            for (int i = 0; i < columnsCount; ++i) {
                handleColorChange((TextView) tableHeader.getChildAt(i),
                        textColorToSet,
                        bgColorToSet);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TITLE__KEY, title.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        title.setText(savedInstanceState.getString(TITLE__KEY));
    }

    private void handleColorChange(TextView textView, int textColor, int bgColor) {
        textView.setTextColor(textColor);
        textView.setBackgroundColor(bgColor);
    }
}