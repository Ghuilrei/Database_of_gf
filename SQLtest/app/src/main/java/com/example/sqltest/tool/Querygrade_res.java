package com.example.sqltest.tool;

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

public class Querygrade_res extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.querygrade_res);
        addWegit();
    }

    public void addWegit() {

        Intent intent = getIntent();
        ArrayList<String> sno = intent.getStringArrayListExtra("sno");
        ArrayList<String> cno = intent.getStringArrayListExtra("cno");
        ArrayList<String> tno = intent.getStringArrayListExtra("tno");
        ArrayList<String> grade = intent.getStringArrayListExtra("grade");

        TableLayout table = findViewById(R.id.tablelayout);
        table.setStretchAllColumns(true);

        TableRow tablerow = new TableRow(Querygrade_res.this);
        tablerow.setBackgroundColor(Color.rgb(222, 220, 210));

        TextView testview1 = new TextView(Querygrade_res.this);
        testview1.setText("学号");
        tablerow.addView(testview1);

        TextView testview2 = new TextView(Querygrade_res.this);
        testview2.setText("课程号");
        tablerow.addView(testview2);

        TextView testview4 = new TextView(Querygrade_res.this);
        testview4.setText("教工号");
        tablerow.addView(testview4);

        TextView testview3 = new TextView(Querygrade_res.this);
        testview3.setText("成绩");
        tablerow.addView(testview3);

        table.addView(tablerow, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        try {
            for (int i = 0; i < sno.size(); i++) {
                TableRow tablerow1 = new TableRow(Querygrade_res.this);
                tablerow1.setBackgroundColor(Color.rgb(222, 220, 210));

                TextView et_sno = new TextView(Querygrade_res.this);
                et_sno.setText(sno.get(i));
                tablerow1.addView(et_sno);

                TextView et_cno = new TextView(Querygrade_res.this);
                et_cno.setText(cno.get(i));
                tablerow1.addView(et_cno);

                TextView et_tno = new TextView(Querygrade_res.this);
                et_tno.setText(tno.get(i));
                tablerow1.addView(et_tno);

                TextView et_grade = new TextView(Querygrade_res.this);
                et_grade.setText(grade.get(i));
                tablerow1.addView(et_grade);

                table.addView(tablerow1, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            }
        } catch (NullPointerException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}