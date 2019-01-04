# Range DatePicker
   <img src="SampleRangeDatePicker.jpg" height="400" width="300" title="SampleRangeDatePicker">
 The DatePicker Range is a library that lets you select two dates for a given date range. You can customize as desired.

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.maycon1038:SampleRangeDatePicker:1.0'
	}

implement DateRangePickerFragment.OnDateRangeSelectedListener in your MainActivity

in your onCreate instance DateRangePickerFragment
     
     @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
                DateRangePickerFragment rangePickerFragment = new DateRangePickerFragment();
                rangePickerFragment.initialize(MainActivity.this,true);
                //optional,set names for tabHost
                rangePickerFragment.setNameTabHost("Data Início", "Data Fim");
                 //optional,to display the selected dates in the RangeDatePicker
                rangePickerFragment.showPerido(true);
                //optional, max dates
                rangePickerFragment.setMaxDate(cd.getTime(), new Date());
                rangePickerFragment.show(getSupportFragmentManager(), "MainActivity");
             }


      @Override
    public void onDateRangeSelected(int startDay, int startMonth, int startYear, int endDay, int endMonth, int endYear) {
       //work your method here
         DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
        Calendar  c = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c.set(startYear,startMonth,startDay);
        c2.set(endYear, endMonth,endDay);
        txt_inform.setText(df.format(c.getTime()) + "\n");
        txt_inform.append( df.format(c2.getTime());

    }
