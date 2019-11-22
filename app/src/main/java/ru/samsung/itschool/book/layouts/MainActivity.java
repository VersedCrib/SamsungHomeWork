package ru.samsung.itschool.book.layouts;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText editText_login = (EditText) findViewById(R.id.editText_login);
        EditText editText_password = (EditText) findViewById(R.id.editText_password);
        Button button = (Button) findViewById(R.id.button);

        final String password = "1", login = "i";
        final Editable pas_temp = editText_password.getText(), log_temp=editText_login.getText();
        final int colorEr = ContextCompat.getColor(this, R.color.colorError);
        final int colorSec  = ContextCompat.getColor(this, R.color.colorSec);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView textView = (TextView)  findViewById(R.id.Text_sost);
                if( password.equals( pas_temp.toString())  && login.equals(log_temp.toString()) ){
                    textView.setText("Верно");
                    textView.setBackgroundColor(colorSec);

                } else{
                    textView.setText("Не верно");
                    textView.setBackgroundColor(colorEr);
                }
            }
        });

    }
}
