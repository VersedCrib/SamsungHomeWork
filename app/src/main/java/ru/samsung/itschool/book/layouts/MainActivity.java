package ru.samsung.itschool.book.layouts;

//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
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

    String password = "1234", login = "ivan";


    public void setPassword(String s){
        this.password = s;
    }

    public void setLogin(String s){
        this.login = s;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView textView = (TextView)  findViewById(R.id.Text_sost);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                setPassword(data.getStringExtra("ps"));
                setLogin(data.getStringExtra("lg"));
                textView.setTextSize(20);
                textView.setBackgroundColor(Color.WHITE);
                textView.setTextColor(Color.BLACK);
                textView.setText(login);
                /*TextView textView = (TextView)  findViewById(R.id.Text_sost);
                textView.setText(getIntent().getStringExtra("lg"));*/
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText_login = (EditText) findViewById(R.id.editText_login);
        EditText editText_password = (EditText) findViewById(R.id.editText_password);
        Button button = (Button) findViewById(R.id.button);
        final int colorEr = ContextCompat.getColor(this, R.color.colorError);
        final int colorSec  = ContextCompat.getColor(this, R.color.colorSec);

        final Editable pas_temp = editText_password.getText(), log_temp = editText_login.getText();


        View.OnClickListener listener = (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView)  findViewById(R.id.Text_sost);
                if( password.equals( pas_temp.toString())  && login.equals(log_temp.toString()) ){
                    textView.setText("Верно");
                    textView.setBackgroundColor(colorSec);
                    textView.setTextSize(20);
                    textView.setTextColor(Color.BLACK);

                } else{
                    /*textView.setText("Не верно");
                    textView.setBackgroundColor(colorEr);
                    textView.setTextSize(20);*/
                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    startActivityForResult(i, 1);
                    //textView.setText(getIntent().getStringExtra("lg"));
                    //textView.setText("Не верно");
                    //textView.setTextColor(Color.BLACK);

                }
                //Intent i = new Intent(MainActivity.this, MainActivity2.class);
                //startActivity(i);
            }
        });

        button.setOnClickListener(listener);

    }
}
