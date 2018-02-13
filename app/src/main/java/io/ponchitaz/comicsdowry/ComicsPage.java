package io.ponchitaz.comicsdowry;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

/**
 * The class describes how teh activity "activity_comics_page.xml" should work.
 * The activity opens after the card with a comic book was clicked -
 * it is where the complete info about the book is shown.
 * Here are also two buttons for adding the book in one of the user lists.
 */

public class ComicsPage extends AppCompatActivity implements NavFragment.OnFragmentInteractionListener {

    private DatabaseReference myFirebaseRef;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics_page);

        final ImageView comicsPic = (ImageView) findViewById(R.id.comicsPicture);
        final TextView comicsTitle = (TextView) findViewById(R.id.comicsTitle);
        final TextView comicsAuthors = (TextView) findViewById(R.id.comicsAuthors);
        final TextView comicsPublishing = (TextView) findViewById(R.id.comicsPublishing);
        final TextView comicsGenre = (TextView) findViewById(R.id.comicsGenre);
        final TextView comicsDescription = (TextView) findViewById(R.id.comicsDescription);
        Button addToShelfBtn = (Button) findViewById(R.id.addToShelfButton);
        Button addToWishlistBtn = (Button) findViewById(R.id.addToWishlistButton);

//        addToShelfBtn.setOnClickListener(AddToListsListener);
//        addToWishlistBtn.setOnClickListener(AddToListsListener);

        /**
         * Here the activity gets the info about the book it has to show.
         * This information is taken from the adapter, so the activity
         * with cards, where the click was made, is not important
         * as they all share one adapter.
         */

        String bookToOpen = getIntent().getExtras().getString("bookInfo");
        myFirebaseRef = FirebaseDatabase.getInstance().getReference().child("ComicsBook/" + bookToOpen);

        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot comicsPreview : dataSnapshot.getChildren()) {
                    String cTitle = (String) dataSnapshot.child("bookTitle").getValue();
                    comicsTitle.setText(cTitle);
                    String cAuth = (String) dataSnapshot.child("bookAuthors").getValue().toString();
                    comicsAuthors.setText(cAuth);
                    String cPubl = (String) dataSnapshot.child("bookPublishing").getValue().toString();
                    comicsPublishing.setText("Издательство: \n" + cPubl);
                    String cGenre = (String) dataSnapshot.child("bookTags").getValue().toString();
                    comicsGenre.setText("Тэги: \n" + cGenre);
                    String cDescr = (String) dataSnapshot.child("bookDescription").getValue();
                    comicsDescription.setText(cDescr);
                    String cPic = (String) dataSnapshot.child("bookPicUrl").getValue().toString();
                    Glide.with(getApplicationContext()).load(cPic).into(comicsPic);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Here ate the listeners for buttons,
     * but by  Feb 14, 2018 they are just drafts as the functionality of
     * lists and users are not available yet.
     */

    private View.OnClickListener AddToListsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.addToShelfButton:
                    myFirebaseRef = FirebaseDatabase.getInstance().getReference().child("books/book000001/onShelf");
                    // TODO: проверка на наличие комикса на полке
                    // если есть:
                    // ...
                    // если нет:
                    HashMap<String, String> putItOnYourShelf = new HashMap<String, String>();
                    putItOnYourShelf.put("userComment", "");
                    putItOnYourShelf.put("userRate", "");

                    myFirebaseRef.child("user0000042").setValue(putItOnYourShelf).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ComicsPage.this, "Комикс добавлен на полку!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ComicsPage.this, "Что-то пошло не так. \nПовторите попытку", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    break;
                case R.id.addToWishlistButton:
                    myFirebaseRef = FirebaseDatabase.getInstance().getReference().child("books/book000001/wished");
                    // TODO: проверка на наличие комикса в вишлисте
                    // если есть:
                    // ...
                    // если нет:
                    HashMap<String, String> putItInYourWishlist = new HashMap<String, String>();
                    putItInYourWishlist.put("willBuy", "false");

                    myFirebaseRef.child("user0000042").setValue(putItInYourWishlist).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ComicsPage.this, "Комикс добавлен в вишлист!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ComicsPage.this, "Что-то пошло не так. \nПовторите попытку", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    break;
                default:
                    break;

            }
        }
    };

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}