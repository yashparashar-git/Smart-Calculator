package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity {
    TextView  input,output;
    Button btn_ac,btn_c,equal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
           input=findViewById(R.id.input);
           btn_ac=findViewById(R.id.btn_ac);
           btn_c=findViewById(R.id.btn_c);
           output=findViewById(R.id.output);
           equal=findViewById(R.id.btn_equal);
           equal.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   String data=input.getText().toString();

                   Context context=Context.enter();
                   context.setOptimizationLevel(-1);
                   Scriptable scriptable=context.initStandardObjects();
                   String finalResult=context.evaluateString(
                   scriptable,data,"javascript",1,null).toString();
           output.setText(finalResult);



               }

                                    });
          btn_ac.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  input.setText("");
                  output.setText("");
              }
          });
          btn_c.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  String text=input.getText().toString();
                  input.setText(text.substring(0,text.length()-1));
              }
          });



            return insets;
        });
    }
    public void getText(View view){
        Button btn=(Button) view;
        input.setText(input.getText()+btn.getText().toString());

    }
}