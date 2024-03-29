package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv, solutionTv;
    MaterialButton buttonOpenBracket, buttonClosedBracket, buttonDivide, button7,
    button8, button9, buttonMultiply, button4, button5, button6, buttonPlus, button1, button2,
    button3, buttonMinus, buttonAc, button0, buttonDot, buttonEquals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);


        assignId(buttonOpenBracket, R.id.button_open_bracket);
        assignId(buttonClosedBracket, R.id.button_closed_bracket);
        assignId(buttonDivide, R.id.button_divide);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
        assignId(buttonMultiply, R.id.button_multiply);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(buttonPlus, R.id.button_plus);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(buttonMinus, R.id.button_minus);
        assignId(buttonAc, R.id.button_ac);
        assignId(button0, R.id.button_0);
        assignId(buttonDot, R.id.button_dot);
        assignId(buttonEquals, R.id.button_equals);
    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if (buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }

        if (buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }

        dataToCalculate = dataToCalculate + buttonText;

        solutionTv.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Err")){
            resultTv.setText((finalResult));
        }
    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            DecimalFormat decimalFormat = new DecimalFormat("#.######");
            String finalResult = decimalFormat.format(context.evaluateString(scriptable, data, "Javascript", 1, null));
            if(finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }
        catch (Exception e) {
            return "Err";
        }

    }
}