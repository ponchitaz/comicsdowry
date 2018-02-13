package io.ponchitaz.comicsdowry;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This is the class for describing how the "activity_search_page.xml" should work.
 * Gets the information from DB according to the model AND
 * considering the user input,
 * sorts it alphabetically,
 * operates it with adapter and shows the list of books
 * (only matching the user query) as cards.
 */

public class SearchPage extends AppCompatActivity implements NavFragment.OnFragmentInteractionListener {

    FirebaseDatabase myFirebase;
    DatabaseReference myFirebaseRef;
    List<ComicsBook> comicsList;
    RecyclerView mainRecyclerView;
    CardView mainCardView;

    EditText searchInput;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        mainRecyclerView = (RecyclerView) findViewById(R.id.mainRecyclerViewContainer);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(SearchPage.this));

        searchButton = (Button) findViewById(R.id.searchButton);
        searchInput = (EditText) findViewById(R.id.searchInput);

        searchButton.setOnClickListener(searchClick);

        mainCardView = (CardView) findViewById(R.id.main_comics_card);
        myFirebase = FirebaseDatabase.getInstance();
        myFirebaseRef = myFirebase.getReference("ComicsBook");

        //не работает - должно скрывать клаву
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert inputManager != null;
        inputManager.hideSoftInputFromWindow(searchInput.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private View.OnClickListener searchClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String textInput = searchInput.getText().toString();
            if (!textInput.isEmpty()) {
                setAdapter(textInput);
            } else {
                mainRecyclerView.removeAllViews();
            }
        }
    };

    private void setAdapter(final String searchedString) {

        myFirebaseRef.child("ComicsBook");
        myFirebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mainRecyclerView.removeAllViews();
                comicsList = new ArrayList<ComicsBook>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String bookId = dataSnapshot1.getKey();
                    ComicsBook model = new ComicsBook();
                    String bookTitle = (String) dataSnapshot1.child("bookTitle").getValue();
                    String bookAuthors = (String) dataSnapshot1.child("bookAuthors").getValue().toString();
                    String bookPicUrl = (String) dataSnapshot1.child("bookPicUrl").getValue().toString();
                    String bookPublishing = (String) dataSnapshot1.child("bookPublishing").getValue().toString();
                    String bookTags = (String) dataSnapshot1.child("bookTags").getValue().toString();
                    String bookDescription = (String) dataSnapshot1.child("bookDescription").getValue().toString();
                    if (bookTitle.toLowerCase().contains(searchedString.toLowerCase())) {
                        model.setBookTitle(bookTitle);
                        model.setBookAuthors(bookAuthors);
                        model.setBookPicUrl(bookPicUrl);
                        model.setBookDescription(bookDescription);
                        model.setBookPublishing(bookPublishing);
                        model.setBookTags(bookTags);
                        model.setBookId(bookId);
                        comicsList.add(model);
                    } else if (bookAuthors.toLowerCase().contains(searchedString.toLowerCase())) {
                        model.setBookTitle(bookTitle);
                        model.setBookAuthors(bookAuthors);
                        model.setBookPicUrl(bookPicUrl);
                        model.setBookDescription(bookDescription);
                        model.setBookPublishing(bookPublishing);
                        model.setBookTags(bookTags);
                        model.setBookId(bookId);
                        comicsList.add(model);
                    }
                }

                Collections.sort(comicsList, new Comparator<ComicsBook>() {
                    @Override
                    public int compare(ComicsBook o1, ComicsBook o2) {
                        return o1.bookTitle.compareTo(o2.bookTitle);
                    }
                });
                MainPageRecyclerAdapter mainPageRecyclerAdapter = new MainPageRecyclerAdapter(comicsList, SearchPage.this);
                mainRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mainRecyclerView.setAdapter(mainPageRecyclerAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
