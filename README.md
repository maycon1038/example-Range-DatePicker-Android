# SampleRangeDatePicker
 This library allows the selection of two dates for a given period in a RangeDatePicker. You can customize as desired.
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.maycon1038:SampleRangeDatePicker:Tag'
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
       #work your method here
         DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault());
        Calendar  c = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c.set(startYear,startMonth,startDay);
        c2.set(endYear, endMonth,endDay);
        String dataInicio =  "Start "    + df.format(c.getTime()) + "\n";
        String dataFim =  " End " +  df.format(c2.getTime()) ;
        txt_inform.setText(dataInicio);
        txt_inform.append(dataFim);

    }
