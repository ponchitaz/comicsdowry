package io.ponchitaz.comicsdowry;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SearchPage extends AppCompatActivity implements NavFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

//        FirebaseDatabase myFirestore;
//        myFirestore = FirebaseDatabase.getInstance();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}