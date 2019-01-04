package com.android.msm.samplerangedatepicker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.msm.rangedatepicker.DateRangePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements DateRangePickerFragment.OnDateRangeSelectedListener {

    private TextView txt_inform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        txt_inform = (TextView)findViewById(R.id.txt_inform);
        txt_inform.setText("click the calendar");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cd = Calendar.getInstance();
                Date hj = new Date();
                cd.setTime(hj);
                cd.add(Calendar.DATE, -6);
                DateRangePickerFragment rangePickerFragment = new DateRangePickerFragment();
                rangePickerFragment.initialize(MainActivity.this,true);
                rangePickerFragment.setNameTabHost("Data Início", "Data Fim");
                rangePickerFragment.showPerido(true);
                rangePickerFragment.setMaxDate(cd.getTime(), new Date());
                rangePickerFragment.show(getSupportFragmentManager(), "MainActivity");
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateRangeSelected(int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear) {
       //      work your method here
        /* DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
        Calendar  c = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c.set(startYear,startMonth,startDay);
        c2.set(endYear, endMonth,endDay);

        String dataInicio =  "Start "    + df.format(c.getTime()) + "\n";
        String dataFim =  " End " +  df.format(c2.getTime()) ;

        txt_inform.setText(dataInicio);
        txt_inform.append(dataFim);*/

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
