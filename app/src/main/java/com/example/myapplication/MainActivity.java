package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText name, number, email, password, address;
    Button button, main_showData;
    Spinner city;
    RadioGroup gender;
    RadioButton male, female;
    CheckBox android1, java, php, python, ios;
    ArrayList arrayList = new ArrayList();
    ArrayAdapter arrayAdapter;
    String getCityName, getGender;
    StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_showData = findViewById(R.id.main_showData);
        name = findViewById(R.id.main_name);
        number = findViewById(R.id.main_number);
        email = findViewById(R.id.main_email);
        password = findViewById(R.id.main_password);
        address = findViewById(R.id.main_address);
        button = findViewById(R.id.main_submit);
        city = findViewById(R.id.city);
        city.setPrompt("Select City");
        gender = findViewById(R.id.radioGender);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        android1 = findViewById(R.id.android);
        java = findViewById(R.id.java);
        python = findViewById(R.id.python);
        ios = findViewById(R.id.ios);
        php = findViewById(R.id.php);


        arrayList.add("Ahemdabad");
        arrayList.add("Vadodara");
        arrayList.add("Surat");
        arrayList.add("Bharuch");
        arrayList.add("Godhra");
        arrayList.add("Gandhinagar");
        arrayList.add("Nadiad");
        arrayList.add("Bhavnagar");
        arrayList.add("Rajkot");
        arrayList.add("Jamnagar");
        arrayList.add("Anand");

        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayList);
        city.setAdapter(arrayAdapter);

        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getCityName = (String) city.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        if (gender.getCheckedRadioButtonId() == male.getId()) {
            getGender = "Male";
        } else {
            getGender = "Female";
        }
        main_showData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowDataViaRetro.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                stringBuilder = new StringBuilder();
                if (android1.isChecked()) {
                    stringBuilder.append("Android,");
                }
                if (java.isChecked()) {
                    stringBuilder.append("Java,");
                }
                if (php.isChecked()) {

                    stringBuilder.append("PHP,");
                }
                if (python.isChecked()) {
                    stringBuilder.append("Python,");
                }
                if (ios.isChecked()) {
                    stringBuilder.append("IOS,");
                }


                if (new ConnectionCall(MainActivity.this).isConnectingToInternet()) {
                    new InsertMydata().execute();


                } else new ConnectionCall(MainActivity.this).connectiondetect();
            }

        });


    }

    private class InsertMydata extends AsyncTask<String, String, String> {

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Wait Sec...");
            pd.setCancelable(true);
            pd.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String, String> hashMap = new HashMap<>();

            hashMap.put("name", name.getText().toString());
            hashMap.put("number", number.getText().toString());
            hashMap.put("email", email.getText().toString());
            hashMap.put("password", password.getText().toString());
            hashMap.put("address", address.getText().toString());
            hashMap.put("city", getCityName);
            hashMap.put("gender", getGender);
            hashMap.put("technology", (stringBuilder.toString()));


            return new MakeServiceCall().MakeServiceCall("https://demoappdatamy.000webhostapp.com/Register.php", MakeServiceCall.POST, hashMap);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (pd.isShowing()) {
                pd.dismiss();
            }

            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("Status").equalsIgnoreCase("true")) {
                    Toast.makeText(MainActivity.this, "Register Successfully Done", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
