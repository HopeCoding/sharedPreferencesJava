package com.hopecoding.storingdatajava;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity {

    EditText numberText;
    TextView resultText;
    SharedPreferences sharedPreferences;
    int storedAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberText=findViewById(R.id.numberText);
        resultText=findViewById(R.id.resultText);


        sharedPreferences=this.getSharedPreferences("com.hopecoding.storingdatajava", Context.MODE_PRIVATE);

        storedAge=sharedPreferences.getInt("storedAge",0);

        if(storedAge==0){
            resultText.setText(MessageFormat.format("You have no registered age",null));
        }else{
            resultText.setText(MessageFormat.format("Your registered age:{0}",storedAge));

        }

    }


    public void save(View view){

        if(!numberText.getText().toString().matches("")){
            int userage=Integer.parseInt(numberText.getText().toString());
            resultText.setText(MessageFormat.format("Your registered age:{0}", userage));

            sharedPreferences.edit().putInt("storedAge",userage).apply();
        }

    }


    public void delete(View view){
        storedAge=sharedPreferences.getInt("storedAge",0);
        if(storedAge!=0){
            sharedPreferences.edit().remove("storedAge").apply();
            resultText.setText(MessageFormat.format("Your Registered Age has been deleted",null));
        }

    }
}