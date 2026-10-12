package com.example.kalkulator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView tvDisplay;

    private double storedValue = 0;
    private String operation = "";
    private boolean startNewNumber = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay   = findViewById(R.id.tvDisplay);

        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btn6 = findViewById(R.id.btn6);

        btn7.setOnClickListener(v -> append7());
        btn8.setOnClickListener(v -> append8());
        btn9.setOnClickListener(v -> append9());

        btn6.setOnClickListener(v -> appendSymbol("6"));
    }

    private void append9() {
        tvDisplay.setText(tvDisplay.getText().toString() + "9");
    }

    private void append8() {
        tvDisplay.setText(tvDisplay.getText().toString() + "8");
    }

    private void append7() {
        tvDisplay.setText(tvDisplay.getText().toString() + "7");
    }

    private void appendSymbol(String symbol) {
        tvDisplay.setText(tvDisplay.getText().toString() + symbol);
    }
}