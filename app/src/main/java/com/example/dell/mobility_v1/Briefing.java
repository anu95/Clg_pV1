package com.example.dell.mobility_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Briefing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_briefing);

        Button b=(Button)findViewById(R.id.go);
      final  Intent intent = new Intent(this,Workspace.class);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               startActivity(intent);
            }
        });
    }
}
