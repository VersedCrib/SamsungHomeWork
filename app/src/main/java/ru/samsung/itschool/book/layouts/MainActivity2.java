package ru.samsung.itschool.book.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity2 extends AppCompatActivity {

    String password = "", login = "";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        EditText editText_login = (EditText) findViewById(R.id.et_login);
        EditText editText_password = (EditText) findViewById(R.id.et_password);
        final Editable pas_temp = editText_password.getText(), log_temp = editText_login.getText();
        Button button = (Button) findViewById(R.id.bt);
        View.OnClickListener listener = (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = pas_temp.toString();
                login = log_temp.toString();
                Intent i = new Intent(MainActivity2.this, MainActivity.class);
                i.putExtra("ps", password);
                i.putExtra("lg", login);
                setResult(RESULT_OK, i);
                finish();
                //startActivityForResult(i, 0);

            }
        });

        button.setOnClickListener(listener);
    }
}