package io.ponchitaz.comicsdowry;

import android.app.Activity;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton menuBtn = (ImageButton) findViewById(R.id.menuButton);
        ImageButton loginBtn = (ImageButton) findViewById(R.id.loginButton);
        Button addToShelfButton = (Button) findViewById(R.id.addToShelfButton);
        Button addToWishListButton = (Button) findViewById(R.id.addToWishlistButton);
        ConstraintLayout comicsListingForMainList = (ConstraintLayout) findViewById(R.id.comicsListingForMainList);

        menuBtn.setOnClickListener(OnClickMenuListener);
        loginBtn.setOnClickListener(StartActivityListener);
        addToShelfButton.setOnClickListener(StartActivityListener);
        addToWishListButton.setOnClickListener(StartActivityListener);
        comicsListingForMainList.setOnClickListener(StartActivityListener);
    }

    private View.OnClickListener StartActivityListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.loginButton:
                    startActivity(new Intent(MainActivity.this, TheRoom.class));
                    break;
                case R.id.addToShelfButton:
                    Intent roomShelfIntent = new Intent(MainActivity.this, TheRoom.class);
                    roomShelfIntent.putExtra("activeTab", 1);
                    startActivity(roomShelfIntent);
                    break;
                case R.id.addToWishlistButton:
                    Intent roomWishListIntent = new Intent(MainActivity.this, TheRoom.class);
                    roomWishListIntent.putExtra("activeTab", 2);
                    startActivity(roomWishListIntent);
                    break;
                case R.id.comicsListingForMainList:
                    startActivity(new Intent(MainActivity.this, ComicsPage.class));
                default:
                    break;
            }
        }
    };

    private View.OnClickListener OnClickMenuListener = new View.OnClickListener() {
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