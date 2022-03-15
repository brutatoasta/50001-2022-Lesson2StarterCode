package com.example.norman_lee.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    Button buttonConvert;
    Button buttonSetExchangeRate;
    EditText editTextValue;
    TextView textViewResult;
    TextView textViewExchangeRate;
    double exchangeRate;
    public final String TAG = "Logcat";
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.android.mainsharedprefs";
    public static final String RATE_KEY = "Rate_Key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO 4.5 Get a reference to the sharedPreferences object
        mPreferences = getSharedPreferences(sharedPrefFile,
                MODE_PRIVATE);
        //TODO 4.6 Retrieve the value using the key, and set a default when there is none
        String Rate_text = mPreferences.getString(RATE_KEY,"0");
        //TODO 3.13 Get the intent, retrieve the values passed to it, and instantiate the ExchangeRate class
        Intent intent = getIntent();
        String a = intent.getStringExtra("A_KEY");
        String b = intent.getStringExtra("B_KEY");
        ExchangeRate e = new ExchangeRate(a,b);
        //TODO 3.13a See ExchangeRate class --->

        //TODO 2.1 Use findViewById to get references to the widgets in the layout
        editTextValue = findViewById(R.id.editTextValue);
        textViewResult = findViewById(R.id.textViewResult);
        textViewExchangeRate = findViewById(R.id.textViewExchangeRate);
        //TODO 2.2 Assign a default exchange rate of 2.95 to the textView
        exchangeRate  = e.getExchangeRate().doubleValue();
        textViewExchangeRate.setText(String.valueOf(exchangeRate));
        //TODO 2.3 Set up setOnClickListener for the Convert Button
        buttonConvert = findViewById(R.id.buttonConvert);
        buttonConvert.setOnClickListener(view -> {
            //TODO 2.4 Display a Toast & Logcat message if the editTextValue widget contains an empty string
            String foreign = editTextValue.getText().toString().trim();
            if(foreign == "") {
                Toast.makeText(MainActivity.this, "Empty field.",
                        Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Empty field.");
            }
            //TODO 2.5 If not, calculate the units of B with the exchange rate and display it
            //TODO 2.5a See ExchangeRate class --->
            else{
                BigDecimal result = e.calculateAmount(foreign);
                textViewResult.setText(String.valueOf(result));
            }
        });
        //TODO 3.1 Modify the Android Manifest to specify that the parent of SubActivity is MainActivity
        //TODO 3.2 Get a reference to the Set Exchange Rate Button
        buttonSetExchangeRate = findViewById(R.id.buttonSetExchangeRate);
        //TODO 3.3 Set up setOnClickListener for this
        buttonSetExchangeRate.setOnClickListener(view -> {
            //TODO 3.4 Write an Explicit Intent to get to SubActivity
            startActivity(new Intent(MainActivity.this, SubActivity.class));
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //TODO 4.1 Go to res/menu/menu_main.xml and add a menu item Set Exchange Rate
    //TODO 4.2 In onOptionsItemSelected, add a new if-statement and code accordingly

    //TODO 5.1 Go to res/menu/menu_main.xml and add a menu item Open Map App
    //TODO 5.2 In onOptionsItemSelected, add a new if-statement
    //TODO 5.3 code the Uri object and set up the intent

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
        else if (id == R.id.setExchangeRate){
            startActivity(new Intent(MainActivity.this, SubActivity.class));
            return true;
        }
        else if(id == R.id.openMapApp){
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("geo").opaquePart("0.0" ).appendQueryParameter( "q" ,locatio
                    n);
            Uri geoLocation = builder.build();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(geoLocation);
            if ( intent.resolveActivity(getPackageManager()) != null ) startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }

    //TODO 4.3 override the methods in the Android Activity Lifecycle here
    //TODO 4.4 for each of them, write a suitable string to display in the Logcat

    //TODO 4.7 In onPause, get a reference to the SharedPreferences.Editor object
    @Override
    protected void onPause () {
        super .onPause();
        SharedPreferences.Editor preferencesEditor =
                mPreferences.edit();
        preferencesEditor.putString(RATE_KEY, String.valueOf(exchangeRate));
        preferencesEditor.apply();
    }
    //TODO 4.8 store the exchange rate using the putString method with a key

}
