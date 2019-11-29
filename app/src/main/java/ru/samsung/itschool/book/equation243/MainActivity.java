package ru.samsung.itschool.book.equation243;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void solveEquation(View view) {
        double a = Double.parseDouble(((EditText)
                findViewById(R.id.coefficient_a)).getText().toString());
        double b = Double.parseDouble(((EditText)
                findViewById(R.id.coefficient_b)).getText().toString());
        double c = Double.parseDouble(((EditText)
                findViewById(R.id.coefficient_c)).getText().toString());
        TextView result = (TextView) findViewById(R.id.result);
        if (a == 0) {
            if (b == 0) {
                result.setText("(-inf; +inf)");
            } else if (c == 0) {
                result.setText("0");
            } else {
                result.setText("" + (-c/b));
            }
        } else if (b == 0) {
            if (c == 0) {
                result.setText("0");
            } else {
                if (-c/a >= 0) {
                    result.setText( (Math.sqrt(-c/a)) + " и " + String.valueOf(-Math.sqrt(-c/a)));
                } else {
                    result.setText("Нет решения");
                }
            }
        } else if(c == 0) {
            result.setText("0 и " +(-b/a));
        } else {
            double d = Math.sqrt(Math.pow(b,2) - 4*a*c);
            if (d >= 0) {
                double x1 = (-b + d) / 2 * a;
                double x2 = (-b - d) / 2 * a;
                result.setText((x1) + " " +  (x2));
            } else {
                result.setText("Нет решения");
            }
        }
    }
}