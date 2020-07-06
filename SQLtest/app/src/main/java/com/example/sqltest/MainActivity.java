package com.example.sqltest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText eidttext;
    private EditText eidttext2;
    private Button button;
    private  Button button4;
    private Button button7;
    String input_sno;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化信息
        eidttext = (EditText)findViewById(R.id.eidttext);
        eidttext2 = (EditText)findViewById(R.id.eidttext2);
        button = (Button)findViewById(R.id.button);
        button4=(Button)findViewById(R.id.button4);
        button7=(Button)findViewById(R.id.button7);

        //设置按钮监听器
        button.setOnClickListener(this);

    }


    @Override
    public void onClick(View view){

        //获取账号信息
        input_sno = eidttext.getText().toString();

        if(view.getId()==R.id.button && input_sno.equals("1234")) {
            Intent intent1 = new Intent(MainActivity.this, Student.class);
            startActivity(intent1);
            //setContentView(R.layout.Student);
        }
        else if(view.getId() == R.id.button &&input_sno.equals("2345"))
        {
            Intent intent2 = new Intent(MainActivity.this, Teacher.class);
            startActivity(intent2);
        }

    }
}


