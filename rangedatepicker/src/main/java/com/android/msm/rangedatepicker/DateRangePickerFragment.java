package com.android.msm.rangedatepicker;


import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TabHost;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * A DialogFragment that show 2 date pickers.
 * Yesid Lazaro @ingyesid
 *
 */
public class DateRangePickerFragment extends DialogFragment implements View.OnClickListener{

    private OnDateRangeSelectedListener onDateRangeSelectedListener;

    private TabHost tabHost;
    private DatePicker startDatePicker;
    private DatePicker endDatePicker;
    private Button butSetDateRange;
    boolean is24HourMode;
    private TabHost.TabSpec endDatePage,startDatePage;
    private Button butCancel;
    private String nameTabstart, nameTabend;
    private Drawable iconStart,iconEnd;
    private String nameBtnNegative,nameBtnPositivo;
    private boolean isShowPeriodo;
    private Date startMax, startMin,endMax,endMin;
    private int startYear = 0,startMonth = -1,startDayOfMonty = 0
            ,endYear = 0,endMonth = -1,endDayOfMonty = 0;

    public DateRangePickerFragment() {
        // Required empty public constructor
    }

    public static DateRangePickerFragment newInstance(OnDateRangeSelectedListener callback, boolean is24HourMode) {
        DateRangePickerFragment dateRangePickerFragment = new DateRangePickerFragment();
        dateRangePickerFragment.initialize(callback, is24HourMode);
        return dateRangePickerFragment;
    }

    public void initialize(OnDateRangeSelectedListener callback,
                           boolean is24HourMode) {
        onDateRangeSelectedListener = callback;
        this.is24HourMode = is24HourMode;
    }
    public void setNameTabHost(String nameTabstart, Drawable iconStart,
                           String nameTabend, Drawable iconEnd) {
        this.nameTabstart = nameTabstart;
       this.nameTabend = nameTabend;
        this.iconStart = iconStart;
        this.iconEnd = iconEnd;
    }
    public void setNameTabHost(String nameTabstart,
                           String nameTabend) {
        this.nameTabstart = nameTabstart;
        this.nameTabend = nameTabend;
    }
    public void setNameButton(String nameBtnNegative,
                              String nameBtnPositivo) {
        this.nameBtnNegative = nameBtnNegative;
        this.nameBtnPositivo = nameBtnPositivo;
    }
    public void setStartDate(int startYear, int startMonth, int startDayOfMonty) {
        this.startYear = startYear;
        this.startMonth = startMonth;
        this.startDayOfMonty = startDayOfMonty;
    }
    public void setEndDate(int endYear, int endMonth, int endDayOfMonty) {
        this.endYear = endYear;
        this.endMonth = endMonth;
        this.endDayOfMonty = endDayOfMonty;
    }


    public void setMaxDate(Date startMax, Date endMax) {
        this.startMax = startMax;
        this.endMax = endMax;
    }
    public void setMinDate(Date startMin, Date endMin) {
        this.startMin = startMin;
        this.endMin = endMin;
    }

    public void showPerido(boolean isShowPeriodo) {
        this.isShowPeriodo = isShowPeriodo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.date_range_picker, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        tabHost = (TabHost) root.findViewById(R.id.tabHost);
        butSetDateRange = (Button) root.findViewById(R.id.but_set_time_range);
        butCancel = (Button) root.findViewById(R.id.but_cancel);
        if(nameBtnNegative != null && nameBtnPositivo != null){
            butSetDateRange.setText(nameBtnPositivo);
            butCancel.setText(nameBtnNegative);
        }
        startDatePicker = (DatePicker) root.findViewById(R.id.start_date_picker);
        endDatePicker = (DatePicker) root.findViewById(R.id.end_date_picker);
       
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        tabHost = (TabHost) root.findViewById(R.id.tabHost);
        butSetDateRange = (Button) root.findViewById(R.id.but_set_time_range);
        butCancel = (Button) root.findViewById(R.id.but_cancel);//txt_periodo
        startDatePicker = (DatePicker) root.findViewById(R.id.start_date_picker);

        if(startMax != null){
            startDatePicker.setMaxDate(startMax.getTime());
        }
        if(endMax != null){
            endDatePicker.setMaxDate(endMax.getTime());
        }
        if(startMin != null){
            startDatePicker.setMinDate(startMin.getTime());
        }
        if(endMin != null){
            endDatePicker.setMinDate(endMin.getTime());
        }
        if(startYear > 1000 && startMonth >= 0 && startDayOfMonty >= 1){
            startDatePicker.updateDate(startYear ,startMonth, startDayOfMonty);
        }
        if(endYear > 1000 && endMonth >= 0 && endDayOfMonty >= 1){
            endDatePicker.updateDate(endYear ,endMonth, endDayOfMonty);
        }


       // startDatePicker.updateDate(year,month,day);
        final TextView txt = (TextView) root.findViewById(R.id.txt_periodo);//txt_periodo


       final  DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());

        final String myDateStart = (nameTabstart != null)?nameTabstart:getString(R.string.title_tab_start_date) ,
                myDateEnd = (nameTabend != null)?nameTabend:getString(R.string.ttile_tab_end_date);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && isShowPeriodo) {
            txt.setVisibility(View.VISIBLE);
            Calendar  c = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c.set(startDatePicker.getYear(),startDatePicker.getMonth(),startDatePicker.getDayOfMonth());
            c2.set(endDatePicker.getYear(), endDatePicker.getMonth(),endDatePicker.getDayOfMonth());
            String dataInicio =  myDateStart  + " " + df.format(c.getTime()) + "\n";
            String dataFim = myDateEnd + " " +  df.format(c2.getTime()) ;
            txt.setText(dataInicio);
            txt.append(dataFim);
            startDatePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar  c = Calendar.getInstance();
                    Calendar c2 = Calendar.getInstance();
                    c.set(year,monthOfYear,dayOfMonth);
                    c2.set(endDatePicker.getYear(), endDatePicker.getMonth(),endDatePicker.getDayOfMonth());

                    String dataInicio =  myDateStart + " "    + df.format(c.getTime()) + "\n";
                    String dataFim = myDateEnd  + " " +  df.format(c2.getTime()) ;
                    txt.setText(dataInicio);
                    txt.append(dataFim);
                }
            });
            endDatePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Calendar  c = Calendar.getInstance();
                    Calendar c2 = Calendar.getInstance();
                    c.set(startDatePicker.getYear(), startDatePicker.getMonth(),startDatePicker.getDayOfMonth());
                    c2.set(year,monthOfYear,dayOfMonth);

                    String dataInicio =  myDateStart + " "+ df.format(c.getTime())  + "\n";
                    String dataFim = myDateEnd+ " " +  df.format(c2.getTime()) ;
                    txt.setText(dataInicio);
                    txt.append(dataFim);
                }
            });
        }


        butSetDateRange.setOnClickListener(this);
        butCancel.setOnClickListener(this);
        tabHost.findViewById(R.id.tabHost);
        tabHost.setup();
        startDatePage = tabHost.newTabSpec("start");
        startDatePage.setContent(R.id.start_date_group);
        endDatePage = tabHost.newTabSpec("end");
        endDatePage.setContent(R.id.end_date_group);

        if(nameTabend != null && nameTabstart != null){
            startDatePage.setIndicator(nameTabstart, iconStart);
            endDatePage.setIndicator(nameTabend, iconEnd);
        }else{
            startDatePage.setIndicator(getString(R.string.title_tab_start_date));
            endDatePage.setIndicator(getString(R.string.ttile_tab_end_date) );
        }



        tabHost.addTab(startDatePage);
        tabHost.addTab(endDatePage);
        return root;

    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() == null)
            return;
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }


    public void setOnDateRangeSelectedListener(OnDateRangeSelectedListener callback) {

        this.onDateRangeSelectedListener = callback;
    }

    @Override
    public void onClick(View v) {
        dismiss();
        if(v == butSetDateRange) {
            onDateRangeSelectedListener.onDateRangeSelected(startDatePicker.getDayOfMonth(), startDatePicker.getMonth(), startDatePicker.getYear(),
                    endDatePicker.getDayOfMonth(), endDatePicker.getMonth(), endDatePicker.getYear());
        }
    }

    public interface OnDateRangeSelectedListener {
        void onDateRangeSelected(int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear);
    }

}
