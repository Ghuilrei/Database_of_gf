package com.example.sqltest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqltest.manager.AddStudent;

public class Manager extends AppCompatActivity implements View.OnClickListener {

    // 学生部分
    private Button[] buttons= new Button[12];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager);

        // 学生部分
        buttons[0] = findViewById(R.id.bt_add_stu);
        buttons[1] = findViewById(R.id.bt_up_stu);
        buttons[2] = findViewById(R.id.bt_que_stu);
        buttons[3] = findViewById(R.id.bt_del_stu);

        // 教师部分
        buttons[4] = findViewById(R.id.bt_add_th);
        buttons[5] = findViewById(R.id.bt_up_th);
        buttons[6] = findViewById(R.id.bt_que_th);
        buttons[7] = findViewById(R.id.bt_del_th);

        // 课程部分
        buttons[8] = findViewById(R.id.bt_add_c);
        buttons[9] = findViewById(R.id.bt_up_c);
        buttons[10] = findViewById(R.id.bt_que_c);
        buttons[11] = findViewById(R.id.bt_del_c);

        // 设置监听器
        for (int i = 0; i < buttons.length; ++i) {
            buttons[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.bt_add_stu:
                intent = new Intent(this, AddStudent.class);
                startActivity(intent);
                break;
            case R.id.bt_up_stu:

                break;
            case R.id.bt_que_stu:

                break;
            case R.id.bt_del_stu:

                break;
            case R.id.bt_add_th:

                break;
            case R.id.bt_up_th:

                break;
            case R.id.bt_que_th:

                break;
            case R.id.bt_del_th:

                break;
            case R.id.bt_add_c:

                break;
            case R.id.bt_up_c:

                break;
            case R.id.bt_que_c:

                break;
            case R.id.bt_del_c:

                break;
        }
    }
}
