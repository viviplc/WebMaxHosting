package com.example.webymaxhostingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {

    //Customer name definition
    private EditText EdTxtName;

    //radio buttons attributes definition
    private RadioGroup radioHostingPlan;
    private RadioButton radioGrowBig;
    private RadioButton radioGrowGeek;
    private RadioGroup radioHostingFeatures;
    private RadioButton radioOption1;
    private RadioButton radioOption2;

    //checkbox attributes definition
    private CheckBox checkUnlimited;
    private CheckBox checkStaging;

    //WebSpace size list
    private String sizes[] = {"Select a size","10 GB","20 GB","40 GB"};
    //spinner Size definition
    private Spinner spnSize;
    private String sizeSelected;

    //countries list
    private String countries[] = {"Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua & Deps", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Central African Rep", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Congo {Democratic Rep}", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland {Republic}", "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea North", "Korea South", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar, {Burma}", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russian Federation", "Rwanda", "St Kitts & Nevis", "St Lucia", "Saint Vincent & the Grenadines", "Samoa", "San Marino", "Sao Tome & Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad & Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"};
    //autocomplete text field country definition
    private AutoCompleteTextView autoCountry;

    //registration date picker definition
    DatePickerDialog registrationDialog;
    EditText registrationDate;

    //Calculate button definition
    Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Customer name instantiation
        EdTxtName = (EditText)findViewById(R.id.EdTxtName);

        //Radio buttons hosting plan type instantiation
        radioHostingPlan=(RadioGroup) findViewById(R.id.RadioHostingPlan);
        radioGrowBig=(RadioButton) findViewById(R.id.radioBig);
        radioGrowGeek=(RadioButton) findViewById(R.id.radioGeek);

        //Radio buttons hosting plan features instantiation
        radioHostingFeatures=(RadioGroup) findViewById(R.id.RadioHostingFeatures);
        radioOption1=(RadioButton) findViewById(R.id.radioOption1);
        radioOption2=(RadioButton) findViewById(R.id.radioOption2);

        //radio group event handler to update the view depending on the hosting plan type selected
        radioHostingPlan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                //grow big option selected
                if(radioGrowBig.isChecked()){
                    //redefining options for grow big hosting plan
                    radioOption1.setText(getResources().getString(R.string.featureStaging));
                    radioOption2.setText(getResources().getString(R.string.featurePriority));
                    radioHostingFeatures.setVisibility(View.VISIBLE);
                }
                //grow geek option selected
                else if (radioGrowGeek.isChecked()){
                    //redefining options for grow big hosting plan
                    radioOption1.setText(getResources().getString(R.string.featureBackup));
                    radioOption2.setText(getResources().getString(R.string.featureUltrafast));
                    radioHostingFeatures.setVisibility(View.VISIBLE);
                }
            }
        });


        //Checkboxes for additional Services instantiation
        checkUnlimited = (CheckBox)findViewById(R.id.checkUnlimited);
        checkStaging = (CheckBox)findViewById(R.id.checkStaging);

        //Size spinner setting
        spnSize = (Spinner) findViewById(R.id.spnWebSpace);
        //link the list of sizes to the adapter
        ArrayAdapter adptSize = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, sizes);
        adptSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //linking the adapter to the spinner dropdown type
        spnSize.setAdapter(adptSize);

        spnSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //save size selected
                sizeSelected = sizes[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        //Autocomplete text field setting
        autoCountry = (AutoCompleteTextView) findViewById(R.id.AutoTxtCountry);
        //link the list of countries to the adapter
        ArrayAdapter adptCountries = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,countries);
        //linking the adapter to the autocomplete field
        autoCountry.setAdapter(adptCountries);


        //Registration Date Picker and Dialog Setting
        registrationDate = (EditText) findViewById(R.id.TxtDateRegistration);
        registrationDate.setInputType(InputType.TYPE_NULL);
        //setting for Dialog opening on click on textfield
        registrationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set current date
                final Calendar cal = Calendar.getInstance();
                int dayReg = cal.get(Calendar.DAY_OF_MONTH);
                int monthReg = cal.get(Calendar.MONTH);
                int yearReg = cal.get(Calendar.YEAR);

                //Dialog setting
                registrationDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //set text on text field for registration date
                        registrationDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },yearReg,monthReg,dayReg);

                //show dialog for date picker
                registrationDialog.show();
            }
        });


        //Calculation button setting
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Clean previous warnings from data validation
                cleanPrevValidations();
                if(validateData()){
                    double total = 0.0;

                    //Save Selection Data
                    String customerName = EdTxtName.getText().toString();

                    //Save hosting Data selection
                    Hashtable<String,String> hostingData = new Hashtable<String, String>();
                    hostingData = readHostingType();

                    //Save additional services Data selection
                    Hashtable<String,String> additionalData = new Hashtable<String, String>();
                    additionalData = readAdditionalServices();

                    //Save Webspace Size Data selection
                    Hashtable<String,String> sizeData = new Hashtable<String, String>();
                    sizeData = readWebspaceSize();

                    //Save Country Data selection
                    String country = autoCountry.getText().toString();

                    //Save RegistrationDate Data selection
                    String regDate = registrationDate.getText().toString();

                    //calculate Hosting Plan type price
                    total = calculateHostingPlanPrice(hostingData,additionalData,sizeData);
                    total = calculateTaxes(total);

                    //round to 2 digits
                    DecimalFormat format = new DecimalFormat("##.00");
                    String totalSt = format.format(total);

                    //define output String
                    String outputSt = defineOutput(regDate,customerName,country,hostingData,additionalData,sizeData,totalSt);

                    //set output toast
                    toastMsg(outputSt);
                }else{
                    toastMsg("Please complete the form");
                }
            }

            private String defineOutput(String regDate, String customerName, String country, Hashtable<String, String> hostingData, Hashtable<String,
                    String> additionalData, Hashtable<String, String> sizeData, String totalSt) {
                String out = "";

                out = "On "+regDate+", for "+customerName+" from "+country+", plan "+hostingData.get("HostingPlan")+", ";

                //string definition for additional services
                if (additionalData.get("Unlimited Service") != null && additionalData.get("Staging Service") != null){
                    out += "with " + additionalData.get("Unlimited Service") + " and " + additionalData.get("Staging Service")+ ", ";
                }
                if (additionalData.get("Unlimited Service") != null && additionalData.get("Staging Service") == null){
                    out += "with " + additionalData.get("Unlimited Service")+ ", ";
                }
                if (additionalData.get("Unlimited Service") == null && additionalData.get("Staging Service") != null){
                    out += "with " + additionalData.get("Staging Service")+ ", ";
                }

                //String definition for webspace size
                out += sizeData.get("webspaceSize") + " webspace, and ";

                //String definition for hosting plan features
                out += hostingData.get("HostingFeature")+", ";

                //String definition for total cost
                out += "cost: $"+totalSt;

                return out;
            }

            private double calculateTaxes(double total) {
                return total * 1.13;
            }

            private double calculateHostingPlanPrice(Hashtable<String, String> hostingData, Hashtable<String, String> additionalData, Hashtable<String, String> sizeData) {
                double total = 0.0;

                //getting hosting plan price
                String hostingPlanPriceSt = hostingData.get("HostingPlanPrice");
                double hostPlan = Double.parseDouble(hostingPlanPriceSt);
                total+= hostPlan;

                //getting hosting plan feature price
                String hostingFeaturePriceSt = hostingData.get("HostingFeaturePrice");
                double hostFeature = Double.parseDouble(hostingFeaturePriceSt);
                total+= hostFeature;

                //getting additional services prices
                if (additionalData.get("Unlimited price") != null){
                    //unlimited websites additional service selected
                    String UnlimitedPriceSt = additionalData.get("Unlimited price");
                    double unlimited = Double.parseDouble(UnlimitedPriceSt);
                    total+= unlimited;
                }
                if (additionalData.get("Staging price") != null){
                    //staging additional service selected
                    String StagingPriceSt = additionalData.get("Staging price");
                    double staging = Double.parseDouble(StagingPriceSt);
                    total+= staging;
                }

                //getting webspace size price
                String webspaceSizePriceSt = sizeData.get("webspacePrice");
                double sizePrice = Double.parseDouble(webspaceSizePriceSt);
                total+= sizePrice;

                return total;
            }

            private Hashtable<String, String> readWebspaceSize() {
                Hashtable<String,String> sizeData = new Hashtable<String, String>();

                //Validate Selection on Spinner
                if (sizeSelected.equals("10 GB")){
                    sizeData.put("webspaceSize","10 GB");
                    sizeData.put("webspacePrice","3.99");
                }
                if (sizeSelected.equals("20 GB")){
                    sizeData.put("webspaceSize","20 GB");
                    sizeData.put("webspacePrice","6.99");
                }
                if (sizeSelected.equals("40 GB")){
                    sizeData.put("webspaceSize","40 GB");
                    sizeData.put("webspacePrice","12.99");
                }
                return  sizeData;
            }

            //method to read additional services information from customer selection
            private Hashtable<String, String> readAdditionalServices() {
                Hashtable<String, String> servicesData = new Hashtable<String, String>();

                //validate checkboxes input
                if (checkUnlimited.isChecked()){
                   servicesData.put("Unlimited Service","Unlimited Websites");
                   servicesData.put("Unlimited price","9.25");
                }
                if (checkStaging.isChecked()){
                    servicesData.put("Staging Service","Staging");
                    servicesData.put("Staging price","7.49");
                }
                return servicesData;
            }

            //method to read hosting type and features information from customer selection
            private Hashtable<String,String> readHostingType() {
                Hashtable<String,String> hostingData = new Hashtable<String, String>();
                //setting for hosting plan grow big
                if(radioGrowBig.isChecked()){
                    hostingData.put("HostingPlan", "Grow Big");
                    hostingData.put("HostingPlanPrice", "36.82");
                    //Features for grow big hosting type
                    //Option 1 = Staging + git
                    if(radioOption1.isChecked()){
                        hostingData.put("HostingFeature", "Staging + git");
                        hostingData.put("HostingFeaturePrice", "5.99");
                    }
                    //option 2 = Priority support
                    else if(radioOption2.isChecked()){
                        hostingData.put("HostingFeature", "Priority Support");
                        hostingData.put("HostingFeaturePrice", "8.99");
                    }
                }
                //setting for hosting plan grow geek
                else if (radioGrowGeek.isChecked()){
                    hostingData.put("HostingPlan", "Grow Geek");
                    hostingData.put("HostingPlanPrice", "47.88");
                    //Features for grow geek hosting type
                    //Option 1 = on-demand backup
                    if(radioOption1.isChecked()){
                        hostingData.put("HostingFeature", "On-demand Backup");
                        hostingData.put("HostingFeaturePrice", "3.99");
                    }
                    //option 2 = Ultrafast PHP
                    else if(radioOption2.isChecked()){
                        hostingData.put("HostingFeature", "Ultrafast PHP");
                        hostingData.put("HostingFeaturePrice", "7.99");
                    }
                }
                return hostingData;
            }

            //method to clean previous error warnings on data validation
            private void cleanPrevValidations() {
                EdTxtName.setError(null);
                radioGrowBig.setError(null);
                radioGrowGeek.setError(null);
                radioOption1.setError(null);
                radioOption2.setError(null);
                autoCountry.setError(null);
                registrationDate.setError(null);
            }

            //method to validate customer inputs on view
            private boolean validateData() {
                boolean validation = true;
                if(EdTxtName.getText().toString().length() == 0){
                    EdTxtName.setError("Customer Name is required!");
                    validation = false;
                }
                if(radioHostingPlan.getCheckedRadioButtonId() == -1){
                    radioGrowBig.setError("");
                    radioGrowGeek.setError("");
                    validation = false;
                }
                if(radioHostingFeatures.getCheckedRadioButtonId() == -1){
                    radioOption1.setError("");
                    radioOption2.setError("");
                    validation = false;
                }
                if(sizeSelected.equals("Select a size")){
                    //adding a error message to the spinner
                    TextView errorText = (TextView)spnSize.getSelectedView();
                    errorText.setError("");
                    validation= false;
                }
                if(autoCountry.getText().toString().length() <= 2){
                    autoCountry.setError("");
                    validation = false;
                }
                if(registrationDate.getText().toString().length() == 0){
                    registrationDate.setError("Registration Date id required!");
                    validation = false;
                }

                return validation;
            }


            //method to set and display toast message
            public void toastMsg(String msg) {
                Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
                toast.show();
            }

        });

    }
}