package com.example.dell.mobility_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.dell.mobility_v1.util.OnSwipeTouchListener;

public class Briefing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_briefing);

        Button b=(Button)findViewById(R.id.go);
        b.setVisibility(View.GONE);
      final  Intent intent = new Intent(this,Login.class);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//               startActivity(intent);
            }
        });

        LinearLayout homeScr= (LinearLayout)findViewById(R.id.home_scr);
        homeScr.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
        public void  onSwipeLeft()
            {
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out_en);

            }
        });

    }
}
