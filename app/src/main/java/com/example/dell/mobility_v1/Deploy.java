package com.example.dell.mobility_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.dell.mobility_v1.util.OnSwipeTouchListener;

public class Deploy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deploy);
        final Intent in=new Intent(this,Workspace.class);
        RelativeLayout rl=(RelativeLayout)findViewById(R.id.deploy_screen);
        rl.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeRight() {

                startActivity(in);
                overridePendingTransition(R.anim.exit_1, R.anim.exit_2);

            }

        });
    }
}
