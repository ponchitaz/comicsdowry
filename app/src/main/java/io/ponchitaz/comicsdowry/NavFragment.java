package io.ponchitaz.comicsdowry;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * This is about the custom navbar. Below is the default description.
 *
 * \/\/\/
 *
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NavFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NavFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class NavFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    OnFragmentInteractionListener mListener;
    private MenuInflater menuInflater;

    // TODO: Rename and change types of parameters

    public NavFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NavFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NavFragment newInstance(String param1, String param2) {
        NavFragment fragment = new NavFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setContentView(R.layout.fragment_nav);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nav, container, false);

        view.bringToFront();

        ImageButton menuBtn = (ImageButton) view.findViewById(R.id.menuButton);
        ImageButton loginBtn = (ImageButton) view.findViewById(R.id.loginButton);

        menuBtn.setOnClickListener(navFragmentClicks);
        loginBtn.setOnClickListener(navFragmentClicks);

        NavigationView navDrawer = (NavigationView) getActivity().findViewById(R.id.navigationView);
//        DrawerLayout navDrawer = (DrawerLayout) getActivity().findViewById(R.id.navigationView);
//        ActionBarDrawerToggle menuBtn = new ActionBarDrawerToggle(getActivity(), navDrawer, R.string.menuOpen, R.string.menuClose);
//        navDrawer.setDrawerListener(menuBtn);
        setupDrawerContent(navDrawer);

        return view;
    }

    private View.OnClickListener navFragmentClicks = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.loginButton:
                    // TODO: описать кейсы
                    // про переход зарегистрированного пользователя или незарегистрированного
                    startActivity(new Intent(getActivity(), TheRoom.class));
                    // про переход на страницу входа/регистрации
                    break;
                case R.id.menuButton:
                    View navigation = getActivity().findViewById(R.id.navigationView);
                    if (navigation.getVisibility() != View.VISIBLE) {
                        navigation.setVisibility(View.VISIBLE);
                        //попытка вывести меню на передний план при открытии - не работает
//                        showPopup(navigation);
                    } else {
                        navigation.setVisibility(View.INVISIBLE);
                    }
                    //попытка вывести меню на передний план при открытии - не работает
//                    navigation.bringToFront();


                    //попытка скрывать меню по клику в "пустоту" - не работает
//                case R.id.parent:
//                    navigation.setVisibility(View.INVISIBLE);
                default:
                    break;
            }
        }
    };

    //попытка вывести меню на передний план при открытии - не работает
//    public void showPopup(View v) {
//        PopupMenu popup = new PopupMenu(getActivity(), v);
//        MenuInflater inflater = popup.getMenuInflater();
//        inflater.inflate(R.menu.navigation_menu, popup.getMenu());
//        popup.show();
//    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.navigation_menu, menu);
//        return true;
//    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_main:
                        startActivity(new Intent(getActivity(), MainActivity.class));
                        break;
                    case R.id.nav_search:
                        startActivity(new Intent(getActivity(), SearchPage.class));
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri id) {
        if (mListener != null) {
            mListener.onFragmentInteraction(id);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public MenuInflater getMenuInflater() {
        return menuInflater;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
