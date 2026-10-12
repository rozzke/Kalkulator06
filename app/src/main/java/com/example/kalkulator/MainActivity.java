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

        tvDisplay = findViewById(R.id.tvDisplay);
        View.OnClickListener symbolListener =
                v -> appendSymbol(((Button) v).getText().toString());

        int[] symbolIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btncomma
        };
        for (int id : symbolIds) {
            findViewById(id).setOnClickListener(symbolListener);
        }

        View.OnClickListener operationListener = v -> {
            if (v.getId() == R.id.btnadd) {
                setOperation("+");
            } else if (v.getId() == R.id.btnsub) {
                setOperation("-");
            } else if (v.getId() == R.id.btnx) {
                setOperation("*");
            } else {
                setOperation("/");
            }
        };
        findViewById(R.id.btnadd).setOnClickListener(operationListener);
        findViewById(R.id.btnsub).setOnClickListener(operationListener);
        findViewById(R.id.btnx).setOnClickListener(operationListener);
        findViewById(R.id.btndiv).setOnClickListener(operationListener);

        // pojedyncze przyciski - własne lambdy
        findViewById(R.id.btnequals).setOnClickListener(v -> calculate());
        findViewById(R.id.btnc).setOnClickListener(v -> clearAll());
    }

    private void appendSymbol(String symbol) {
        String current = tvDisplay.getText().toString();

        if (startNewNumber) {
            tvDisplay.setText(symbol.equals(",") ? "0," : symbol);
            startNewNumber = false;
            return;
        }
        if (symbol.equals(",") && current.contains(",")) {
            return;                        
        }
        tvDisplay.setText(current + symbol);
    }

    private void setOperation(String newOperation) {
        storedValue = readDisplay();
        operation = newOperation;
        startNewNumber = true;
    }

    private void calculate() {
        if (operation.isEmpty()) {
            return;
        }

        double second = readDisplay();

        if (operation.equals("/") && second == 0) {
            Toast.makeText(this, R.string.error_divide_zero, Toast.LENGTH_SHORT).show();
            clearAll();
            return;
        }

        double result;
        if (operation.equals("+")) {
            result = storedValue + second;
        } else if (operation.equals("-")) {
            result = storedValue - second;
        } else if (operation.equals("*")) {
            result = storedValue * second;
        } else {
            result = storedValue / second;
        }

        showResult(result);
        operation = "";
        startNewNumber = true;
    }

    private double readDisplay() {
        String text = tvDisplay.getText().toString().replace(',', '.');
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void showResult(double result) {
        if (result == Math.rint(result) && Math.abs(result) < 1e15) {
            tvDisplay.setText(String.valueOf((long) result));
        } else {
            String text = String.format(Locale.US, "%.2f", result);
            tvDisplay.setText(text.replace('.', ','));
        }
    }

    private void clearAll() {
        tvDisplay.setText(R.string.display_start);
        storedValue = 0;
        operation = "";
        startNewNumber = true;

    }
}