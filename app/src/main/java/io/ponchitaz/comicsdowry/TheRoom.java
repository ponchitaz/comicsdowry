package io.ponchitaz.comicsdowry;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

/**
 * It's all about the user room with tabs and lists.
 * By Feb 14, 2018 it is only a demo and doesn't work.
 */

public class TheRoom extends AppCompatActivity implements NavFragment.OnFragmentInteractionListener {

    private DatabaseReference myFirebaseRef;
    private StorageReference myStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_room);

        ImageView userPic = (ImageView) findViewById(R.id.userPicture);
        final TextView userName = (TextView) findViewById(R.id.userName);
        ImageView comicsPic = (ImageView) findViewById(R.id.comicsPicture);
        final TextView comicsTitle = (TextView) findViewById(R.id.comicsTitle);
        final TextView comicsAuthors = (TextView) findViewById(R.id.comicsAuthors);
        Button deleteItemBtn = (Button) findViewById(R.id.deleteTheItemButton);
        Button gotTheItemBtn = (Button) findViewById(R.id.gotTheItemButton);
        Button willBuyBtn = (Button) findViewById(R.id.intentionToBuyButton);

        // описываем отображение комиксов
        myFirebaseRef = FirebaseDatabase.getInstance().getReference().child("books/book000001");
        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot comicsPreview : dataSnapshot.getChildren()) {
                    String cTitle = (String) dataSnapshot.child("bookTitle").getValue();
                    comicsTitle.setText(cTitle);
                    String cAuth = (String) dataSnapshot.child("bookAuthors").getValue().toString();
                    comicsAuthors.setText(cAuth);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Intent i = getIntent();
        int activeTab = i.getIntExtra("activeTab", 0);

        // Работа с вкладками

        final TabHost tabHost = (TabHost) findViewById(R.id.theRoomTabs);
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

//        myFirebaseRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                switch (tabHost.getId()) {
//                    case R.id.room:
//                        myFirebaseRef = FirebaseDatabase.getInstance().getReference().child("users/user000001");
//                        myStorageRef = FirebaseStorage.getInstance().getReference().child("userPics/user000001");
//                        // TODO: описать отображение картинки и имени юзера, взятых из базы и хранилища
//                        myFirebaseRef.addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                for (DataSnapshot comicsPreview: dataSnapshot.getChildren()) {
//                                    // TODO: наладить отображение, чтобы не налезало друг на друга
//                                    // TODO: добавить прокрутку на ВСЕ экраны
//                                    String uName = (String) dataSnapshot.child("name").getValue();
//                                    userName.setText(uName);
//                                }

//                String uPic = (String) dataSnapshot.child("userPic").getRef().;
//                comicsPic.setImageURI(imageUtil.getImageURI());
//                Uri.fromFile()

//                // берем Uri файла
//                Uri cPic = Uri.fromFile(new File("comicsPics/book000001.jpg"));
//                // загружаем файл по адресу "имя_папки/file.jpg"
//                UploadTask uploadTask = comicsPicRef.putFile(cPic);


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        });
//
//                        // TODO: описать, что загрузка файла происходит при клике на картинку; по умолчанию картинка у всех одинаковая - прикрутить и это как-то
//
//
//                        // TODO: тут надо указать единый путь к альбомам на устройстве и как-то переименовать выбранный файл в формате userXXXXXX
//                        // Создаем Uri - ссылку к файлу на устройстве пользователя
//                        Uri uPic = Uri.fromFile(new File(""));
//                        // Создаем ссылку в Хранилище Firebase
//                        StorageReference userPicRef = myStorageRef.child("userPics/"+uPic.getLastPathSegment());
//
//                        // создаем метаданные, где указываем Mime-тип и подтип
//                        StorageMetadata userPicMeta = new StorageMetadata.Builder().setCustomMetadata("name", "userID").setContentType("image/jpg").build();
//
//                        // создаем uploadTask посредством вызова метода putFile(), в качестве аргумента идет созданная нами ранее Uri
//                        UploadTask uploadUserPicTask = userPicRef.putFile(uPic, userPicMeta);
//                        // устанавливаем 1-й слушатель на uploadTask, который среагирует, если произойдет ошибка, а также 2-й слушатель, который сработает в случае успеха операции
//                        uploadUserPicTask.addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception exception) {
//                            // Ошибка
//                                Toast.makeText(TheRoom.this, "Не получилось загрузить изображение. \nПопробуйте еще раз", Toast.LENGTH_SHORT).show();
//                            }
//                        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                            @Override
//                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        // Успешно! Берем ссылку прямую https-ссылку на файл
//                                Uri downloaUserPicdUrl = taskSnapshot.getDownloadUrl();
//                            }
//                        });
//
//                        break;
//                    case R.id.shelf:
//                        myFirebaseRef = FirebaseDatabase.getInstance().getReference().child("books/book000001");
//                        myStorageRef = FirebaseStorage.getInstance().getReference().child("comicsPics/book000001");
//
//                        break;
//                    case R.id.wishlist:
//                        myFirebaseRef = FirebaseDatabase.getInstance().getReference().child("books/book000001");
//                        myStorageRef = FirebaseStorage.getInstance().getReference().child("comicsPics/book000001");
//
//                        break;
//                    default:
//                        break;
//
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(TheRoom.this, "Что-то пошло не так. \nПопробуйте еще раз", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


}