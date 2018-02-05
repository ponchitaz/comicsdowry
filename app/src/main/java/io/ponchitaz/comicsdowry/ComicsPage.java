package io.ponchitaz.comicsdowry;

import android.app.FragmentContainer;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class ComicsPage extends AppCompatActivity implements NavFragment.OnFragmentInteractionListener {

    private DatabaseReference myFirebaseRef;
    private StorageReference comicsPicRef;
//    private FragmentManager navFragMngr = getFragmentManager();

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
//        navFragMngr.beginTransaction().add(R.id.nav, FragmentContainer.class);

//        addToShelfBtn.setOnClickListener(AddToListsListener);
        addToWishlistBtn.setOnClickListener(AddToListsListener);

        myFirebaseRef = FirebaseDatabase.getInstance().getReference().child("books/book000001");
        comicsPicRef = FirebaseStorage.getInstance().getReference().child("comicsPics/book000001.jpg");

        // читаем из базы и отображаем инфу по комиксу

        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot comicsPreview: dataSnapshot.getChildren()) {
                    // TODO: наладить отображение, чтобы не налезало друг на друга
                    // TODO: добавить прокрутку на ВСЕ экраны
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
                }

//                String cPic = (String) dataSnapshot.child("bookPic").getRef().;
//                comicsPic.setImageURI(imageUtil.getImageURI());
//                Uri.fromFile()

//                // берем Uri файла
//                Uri cPic = Uri.fromFile(new File("comicsPics/book000001.jpg"));
//                // загружаем файл по адресу "имя_папки/file.jpg"
//                UploadTask uploadTask = comicsPicRef.putFile(cPic);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        NavFragment fragment = (NavFragment) getFragmentManager().findFragmentById(R.id.nav);
    }

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
//    @Override
//    public void onButtonPressed(FragmentActivity fa, View v, int position, Uri id) {
//        // Send the event to the host activity
//        mListener.onClickListener(id);
//    }

}