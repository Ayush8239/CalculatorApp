package com.example.firstapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, expTv;
    AppCompatButton buttonC, buttonBrackOpen, buttonBrackClose;
    AppCompatButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    AppCompatButton buttonPlus, buttonMinus, buttonMultiply, buttonDivide, buttonEquals, buttonDot, buttonSign, buttonBackspace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.result_tv);
        expTv = findViewById(R.id.exp_tv);

        buttonBackspace = findViewById(R.id.buttonBackspace);

        assignID();

    }

    void assignID() {
        buttonC = findViewById(R.id.buttonC);
        buttonBrackOpen = findViewById(R.id.buttonBracOpen);
        buttonBrackClose = findViewById(R.id.buttonBrackClose);
        buttonDivide = findViewById(R.id.buttonDivide);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonEquals = findViewById(R.id.buttonEquals);
        buttonDot = findViewById(R.id.buttonDot);
        buttonSign = findViewById(R.id.buttonSign);
//        buttonBackspace = findViewById(R.id.buttonBackspace);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        // Set click listeners for all buttons
        buttonC.setOnClickListener(this);
        buttonBrackOpen.setOnClickListener(this);
        buttonBrackClose.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);
        buttonEquals.setOnClickListener(this);
        buttonDot.setOnClickListener(this);
        buttonSign.setOnClickListener(this);
        buttonBackspace.setOnClickListener(this);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        AppCompatButton button = (AppCompatButton) view;
        String buttonText = button.getText().toString();
        String datatoCalculate = expTv.getText().toString();

        if (buttonText.equals("C")) {
            expTv.setText("");
            resultTv.setText("");
            return;
        }
        if (buttonText.equals("=")) {
            expTv.setText(resultTv.getText());
            return;
        }
        if (buttonText.equals("backspace")) { // Backspace button
            if (!datatoCalculate.isEmpty()) {
                datatoCalculate = datatoCalculate.substring(0, datatoCalculate.length() - 1);
                expTv.setText(datatoCalculate);
            }
        } else {
            datatoCalculate = datatoCalculate + buttonText;
        }
            expTv.setText(datatoCalculate);

            String finalResult = getResult(datatoCalculate);

            if (!finalResult.equals("Err")) {
                resultTv.setText(finalResult);
            }
    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();

            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            return "Err";
        }
    }
}
