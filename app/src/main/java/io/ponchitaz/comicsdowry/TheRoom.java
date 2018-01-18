package io.ponchitaz.comicsdowry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TabHost;

public class TheRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_room);

        Intent i = getIntent();
        int activeTab = i.getIntExtra("activeTab", 0);

// Работа с вкладками

        TabHost tabHost = (TabHost) findViewById(R.id.theRoomTabs);
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("room");
        tabSpec.setContent(R.id.room);
        tabSpec.setIndicator("Комната");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("shelf");
        tabSpec.setContent(R.id.shelf);
        tabSpec.setIndicator("Полка");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("wishlist");
        tabSpec.setContent(R.id.wishlist);
        tabSpec.setIndicator("Вишлист");
        tabHost.addTab(tabSpec);

        tabHost.setCurrentTab(activeTab);

    }
}