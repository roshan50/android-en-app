package com.roshan.dic.en_app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class NavFrag extends Fragment {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private RecyclerView recyclerView;
    private AdapterNav adapterNav;


    public NavFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nav, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyNav);
        adapterNav = new AdapterNav(getActivity(),post());
        recyclerView.setAdapter(adapterNav);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }



    public void setUp(DrawerLayout draw, final Toolbar toolbar) {
        drawerLayout = draw;
        actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(),draw,toolbar,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if(slideOffset<0.6){
                    toolbar.setAlpha(1-slideOffset);
                }
            }
        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        drawerLayout.post(new Runnable() {
        @Override
        public void run() {
            actionBarDrawerToggle.syncState();
        }
    });
}

    private List<InfoNav> post(){
        List<InfoNav> data = new ArrayList<>();
        String title[] = {"settings","contact us","about","Exit"};
        int iconId[] = {R.drawable.ic_settings,R.drawable.ic_contact_mail,R.drawable.ic_info,R.drawable.ic_exit_to_app};

        for (int i=0; i< title.length && i< iconId.length; i++)
        {
            InfoNav infoNav = new InfoNav();
            infoNav.title = title[i];
            infoNav.iconId = iconId[i];
            data.add(infoNav);
        }

        return data;
    }

}
