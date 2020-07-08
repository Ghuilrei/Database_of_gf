package com.example.sqltest.tool.Student;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqltest.R;

import java.util.ArrayList;

public class Querystudent_res extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.querygrade_res);
        addWegit();
    }

    public void addWegit() {

        Intent intent = getIntent();
        ArrayList<String> sno = intent.getStringArrayListExtra("sno");
        ArrayList<String> sname = intent.getStringArrayListExtra("sname");
        ArrayList<String> ssex = intent.getStringArrayListExtra("ssex");
        ArrayList<String> sage = intent.getStringArrayListExtra("sage");
        ArrayList<String> sphone = intent.getStringArrayListExtra("sphone");


        TableLayout table = findViewById(R.id.tablelayout);
        table.setStretchAllColumns(true);

        TableRow tablerow = new TableRow(Querystudent_res.this);
        tablerow.setBackgroundColor(Color.rgb(222, 220, 210));

        TextView testview1 = new TextView(Querystudent_res.this);
        testview1.setText("学号");
        tablerow.addView(testview1);

        TextView testview2 = new TextView(Querystudent_res.this);
        testview2.setText("姓名");
        tablerow.addView(testview2);

        TextView testview4 = new TextView(Querystudent_res.this);
        testview4.setText("性别");
        tablerow.addView(testview4);

        TextView testview3 = new TextView(Querystudent_res.this);
        testview3.setText("年龄");
        tablerow.addView(testview3);

        TextView testview5 = new TextView(Querystudent_res.this);
        testview5.setText("电话");
        tablerow.addView(testview5);

        table.addView(tablerow, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        try {
            for (int i = 0; i < sno.size(); i++) {
                TableRow tablerow1 = new TableRow(Querystudent_res.this);
                tablerow1.setBackgroundColor(Color.rgb(222, 220, 210));

                TextView et_sno = new TextView(Querystudent_res.this);
                et_sno.setText(sno.get(i));
                tablerow1.addView(et_sno);

                TextView et_sname = new TextView(Querystudent_res.this);
                et_sname.setText(sname.get(i));
                tablerow1.addView(et_sname);

                TextView et_ssex = new TextView(Querystudent_res.this);
                et_ssex.setText(ssex.get(i));
                tablerow1.addView(et_ssex);

                TextView et_sage = new TextView(Querystudent_res.this);
                et_sage.setText(sage.get(i));
                tablerow1.addView(et_sage);

                TextView et_sphone = new TextView(Querystudent_res.this);
                et_sphone.setText(sphone.get(i));
                tablerow1.addView(et_sphone);

                table.addView(tablerow1, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            }
        } catch (NullPointerException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}