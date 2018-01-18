package io.ponchitaz.comicsdowry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        ImageButton menuBtn = (ImageButton) findViewById(R.id.menuButton);
        ImageButton loginBtn = (ImageButton) findViewById(R.id.loginButton);

        menuBtn.setOnClickListener(OnClickMenuListener);
        loginBtn.setOnClickListener(StartActivityListener);
    }

    public View.OnClickListener StartActivityListener =
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (view.getId()) {
                        case R.id.loginButton:
                            startActivity(new Intent(BaseActivity.this, TheRoom.class));
                            break;
                        default:
                            break;
                    }
                }
            };


    public View.OnClickListener OnClickMenuListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            View navigation = findViewById(R.id.navigationView);
            if (navigation.getVisibility() != View.VISIBLE) {
                navigation.setVisibility(View.VISIBLE);
            } else {
                navigation.setVisibility(View.INVISIBLE);
            }
        }
    };
}
