package io.ponchitaz.comicsdowry;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
        mainCardView = (CardView) findViewById(R.id.main_comics_card);
        myFirebase = FirebaseDatabase.getInstance();
        myFirebaseRef = myFirebase.getReference("books");
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        MainPageRecyclerAdapter mainPageRecyclerAdapter = new MainPageRecyclerAdapter(comicsList, MainActivity.this);
        mainRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mainRecyclerView.setAdapter(mainPageRecyclerAdapter);

        ValueEventListener valueEventListener = myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                comicsList = new ArrayList<ComicsBook>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String bookId = dataSnapshot1.getKey();
                    ComicsBook value = dataSnapshot1.getValue(ComicsBook.class);
                    ComicsBook book = new ComicsBook();
                    String comicsTitle = value.getBookTitle();
                    String comicsAuthors = value.getBookAuthors();
                    book.setBookTitle(comicsTitle);
                    book.setBookAuthors(comicsAuthors);
                    comicsList.add(book);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("Failed to read value", error.toException());
            }
        });


//        ImageButton menuBtn = (ImageButton) findViewById(R.id.menuButton);
//        ImageButton loginBtn = (ImageButton) findViewById(R.id.loginButton);

//        Button addToShelfButton = (Button) findViewById(R.id.addToShelfButton);
//        Button addToWishListButton = (Button) findViewById(R.id.addToWishlistButton);
//        ConstraintLayout comicsListingForMainList = (ConstraintLayout) findViewById(R.id.comicsListingForMainList);
//        final TextView bookTitle = (TextView) findViewById(R.id.bookTitle);
//        final TextView bookAuthors = (TextView) findViewById(R.id.bookAuthors);

//        RecyclerView mainRecyclerView = (RecyclerView) findViewById(R.id.mainRecyclerView);
//        mainLayoutManager = new LinearLayoutManager(this);
//        mainRecyclerView.setLayoutManager(mainLayoutManager);
////        myFirebaseRef = FirebaseDatabase.getInstance().getReference("books");
//        Query comicsQuery = FirebaseDatabase.getInstance().getReference().child("books");
//        FirebaseRecyclerOptions<ComicsBook> comicsOptions = new FirebaseRecyclerOptions.Builder<ComicsBook>().setQuery(comicsQuery, ComicsBook.class).build();
//        mainAdapter = new FirebaseRecyclerAdapter<ComicsBook, ComicsBookHolder>(comicsOptions) {
//            @Override
//            public ComicsBookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_comics_listing_for_main_list, parent, false);
//                return new ComicsBookHolder(view);
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull ComicsBookHolder viewHolder, int position, @NonNull ComicsBook item) {
//                viewHolder.setBookPic(item.getBookPic(), getApplicationContext());
//                viewHolder.setBookTitle(item.getBookTitle());
//                viewHolder.setBookAuthors(item.getComicsAuthrs());
//            }
//        };
//        mainRecyclerView.setAdapter(mainAdapter);


//        loginBtn.setOnClickListener(StartActivityListener);
//        menuBtn.setOnClickListener(OnClickMenuListener);

//        comicsListingForMainList.setOnClickListener(StartActivityListener);
//        addToShelfButton.setOnClickListener(AddToListsListener);
//        addToWishListButton.setOnClickListener(AddToListsListener);


        // описываем отображение комиксов
//        myFirebaseRef = FirebaseDatabase.getInstance().getReference().child("books/book000001");
//
//
//
////                String cPic = dataSnapshot.getValue().toString();
////                comicsPic.setImageURI(cPic);
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mainAdapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        mainAdapter.stopListening();
//    }

//    private View.OnClickListener StartActivityListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//            switch (view.getId()) {
////                case R.id.loginButton:
////                    // TODO: описать кейсы
////                    // про переход зарегистрированного пользователя или незарегистрированного
////                    startActivity(new Intent(MainActivity.this, TheRoom.class));
////                    // про переход на страницу входа/регистрации
////                    // startActivity(new Intent(MainActivity.this, LoginPage.class))
////                    break;
//                case R.id.comicsListingForMainList:
//                    // TODO: передавать айдишник комикса, про который нужна подробная информация
//                    startActivity(new Intent(MainActivity.this, ComicsPage.class));
//                default:
//                    break;
//            }
//        }
//    };

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

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}


//    private View.OnClickListener OnClickMenuListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//            View navigation = findViewById(R.id.navigationView);
//            if (navigation.getVisibility() != View.VISIBLE) {
//                navigation.setVisibility(View.VISIBLE);
//            } else {
//                navigation.setVisibility(View.INVISIBLE);
//            }
//        }
//    };

    // не работает
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item){
//        int id = item.getItemId();
//        if (id == R.id.nav_main) {
//            startActivity(new Intent(MainActivity.this, MainActivity.class));
//        } else if (id == R.id.nav_search) {
//            startActivity(new Intent(MainActivity.this, SearchPage.class));
//        }
//        return false;
//    }


    // немного про отображение списка комиксов
//        ListView comicBooksList = (ListView) findViewById(R.id.comicBooksList); // создадим на месте отображения фрагментов с комиксами
//        final ArrayList<String> comicBooks = new ArrayList<String>();
//        ArrayAdapter<String> comicsList= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, comicBooks); // тут ссылка на фрагмент для отображения комикса???
//        comicBooksList.setAdapter(comicsList);
//        myFirebaseRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                String value = dataSnapshot.getValue(String.class);
//                comicBooks.add(value);
//                ArrayAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
