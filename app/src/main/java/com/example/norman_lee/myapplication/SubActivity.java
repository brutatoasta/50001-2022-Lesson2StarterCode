package com.example.norman_lee.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigDecimal;

public class SubActivity extends AppCompatActivity {

    Button buttonBackToCalculator;
    EditText editTextSubValueOfA;
    EditText editTextSubValueOfB;
    public final static String INTENT_EXCH_RATE = "Exchange Rate";
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.android.subsharedprefs";
    public final static String A_KEY = "A_KEY";
    public final static String B_KEY = "B_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);


        //TODO 4.9 Implement saving to shared preferences for the contents of the EditText widget

        //TODO 3.5 Get references to the editText widgets
        editTextSubValueOfA = findViewById(R.id.editTextSubValueA);
        editTextSubValueOfB = findViewById(R.id.editTextSubValueB);
        //TODO 3.6 Get a reference to the Back To Calculator Button
        buttonBackToCalculator = findViewById(R.id.buttonBackToCalculator);
        //TODO 3.7 Set up setOnClickListener
        buttonBackToCalculator.setOnClickListener(view -> {
            //TODO 3.8 Obtain the values stored in the editTextWidgets
            String a = editTextSubValueOfA.getText().toString().trim();
            String b = editTextSubValueOfB.getText().toString().trim();
            //TODO 3.9 Check that these values are valid --> See the Utils class
            try{
                Utils.checkInvalidInputs(a);
            }
            catch(NumberFormatException e){
                Log.i(SubActivity.class.toString(), "SubValueOfA is empty");
                Toast.makeText(SubActivity.this, R.string.warning_numberformat_edit_text_sub_a, Toast.LENGTH_SHORT).show();

            }
            catch (IllegalArgumentException e) {
                Log.i(SubActivity.class.toString(), "SubValueOfA is an illegal argument");
                Toast.makeText(SubActivity.this, R.string.warning_illegal_edit_text_sub_a, Toast.LENGTH_SHORT).show();
            }
            try {
                Utils.checkInvalidInputs(b);
            } catch (NumberFormatException e) {
                Log.i(SubActivity.class.toString(), "SubValueOfB is empty");
                Toast.makeText(SubActivity.this, R.string.warning_numberformat_edit_text_sub_b, Toast.LENGTH_SHORT).show();
            } catch (IllegalArgumentException e) {
                Log.i(SubActivity.class.toString(), "SubValueOfB is an illegal argument");
                Toast.makeText(SubActivity.this, R.string.warning_illegal_edit_text_sub_b, Toast.LENGTH_SHORT).show();
            }

            //TODO 3.10 Set up an explicit intent and pass the exchange rate back to MainActivity
            Intent intent = new Intent(SubActivity.this, MainActivity.class);
            intent.putExtra(A_KEY, a);
            intent.putExtra(B_KEY,b);
            startActivity(intent);

            //TODO 3.11 Decide how you are going to handle a divide-by-zero situation or when negative numbers are entered
            //TODO 3.12 Decide how you are going to handle a situation when the editText widgets are empty

        });


    }

    //TODO 4.10 Don't forget to override onPause()

}
