package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView display;
    private double num1 = 0, num2 = 0;
    private char operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // Set up button listeners
        int[] buttonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9, R.id.buttonAdd, R.id.buttonSubtract,
                R.id.buttonMultiply, R.id.buttonDivide, R.id.buttonEquals,
                R.id.buttonClear
        };

        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "C":
                display.setText("0");
                num1 = num2 = 0;
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                operator = buttonText.charAt(0);
                num1 = Double.parseDouble(display.getText().toString());
                display.setText("0");
                break;
            case "=":
                num2 = Double.parseDouble(display.getText().toString());
                double result = 0;
                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/':
                        if (num2 != 0) result = num1 / num2;
                        else display.setText("Error");
                        break;
                }
                display.setText(String.valueOf(result));
                break;
            default: // for numbers
                if (display.getText().toString().equals("0"))
                    display.setText(buttonText);
                else
                    display.setText(display.getText().toString() + buttonText);
                break;
        }
    }
}
