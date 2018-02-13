package io.ponchitaz.comicsdowry;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This is the class for describing how the "activity_main.xml" should work.
 * Gets the information from DB according to the model,
 * sorts it alphabetically,
 * operates it with adapter and shows the list of books
 * (all books in the DB) as cards.
 */

public class MainActivity extends AppCompatActivity implements NavFragment.OnFragmentInteractionListener {

    FirebaseDatabase myFirebase;
    DatabaseReference myFirebaseRef;
    List<ComicsBook> comicsList;
    RecyclerView mainRecyclerView;
    CardView mainCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainRecyclerView = (RecyclerView) findViewById(R.id.mainRecyclerViewContainer);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        mainCardView = (CardView) findViewById(R.id.main_comics_card);
        myFirebase = FirebaseDatabase.getInstance();
        myFirebaseRef = myFirebase.getReference("ComicsBook");

        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
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
                    model.setBookTitle(bookTitle);
                    model.setBookAuthors(bookAuthors);
                    model.setBookPicUrl(bookPicUrl);
                    model.setBookDescription(bookDescription);
                    model.setBookPublishing(bookPublishing);
                    model.setBookTags(bookTags);
                    model.setBookId(bookId);
                    comicsList.add(model);
                }

                Collections.sort(comicsList, new Comparator<ComicsBook>() {
                    @Override
                    public int compare(ComicsBook o1, ComicsBook o2) {
                        return o1.bookTitle.compareTo(o2.bookTitle);
                    }
                });

                MainPageRecyclerAdapter mainPageRecyclerAdapter = new MainPageRecyclerAdapter(comicsList, MainActivity.this);
                mainRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mainRecyclerView.setAdapter(mainPageRecyclerAdapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("Failed to read value", error.toException());
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

/**
 * Code to make further functionality work - it's all a draft now, don't judge.
 */

//        Button addToShelfButton = (Button) findViewById(R.id.addToShelfButton);
//        Button addToWishListButton = (Button) findViewById(R.id.addToWishlistButton);

//        addToShelfButton.setOnClickListener(AddToListsListener);
//        addToWishListButton.setOnClickListener(AddToListsListener);

//    private View.OnClickListener AddToListsListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            switch (view.getId()) {
//                case R.id.addToShelfButton:
//                    myFirebaseRef = FirebaseDatabase.getInstance().getReference().child("books/book000001/onShelf");
//                    // TODO: проверка на наличие комикса на полке
//                    // если есть:
//                    // ...
//                    // если нет:
//                    HashMap<String, String> putItOnYourShelf = new HashMap<String, String>();
//                    putItOnYourShelf.put("userComment", "");
//                    putItOnYourShelf.put("userRate", "");
//
//                    myFirebaseRef.child("user0000042").setValue(putItOnYourShelf).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()) {
//                                Toast.makeText(MainActivity.this, "Комикс добавлен на полку!", Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(MainActivity.this, "Что-то пошло не так. \nПовторите попытку", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                    break;
//                case R.id.addToWishlistButton:
//                    myFirebaseRef = FirebaseDatabase.getInstance().getReference().child("books/book000001/wished");
//                    // TODO: проверка на наличие комикса в вишлисте
//                    // если есть:
//                    // ...
//                    // если нет:
//                    HashMap<String, String> putItInYourWishlist = new HashMap<String, String>();
//                    putItInYourWishlist.put("willBuy", "false");
//
//                    myFirebaseRef.child("user0000042").setValue(putItInYourWishlist).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()) {
//                                Toast.makeText(MainActivity.this, "Комикс добавлен в вишлист!", Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(MainActivity.this, "Что-то пошло не так. \nПовторите попытку", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                    break;
//                default:
//                    break;
//
//            }
//        }
//    };
