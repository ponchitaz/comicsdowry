package io.ponchitaz.comicsdowry;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


/**
 * This is the class for login activity.
 */

public class LoginPage extends AppCompatActivity implements NavFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}