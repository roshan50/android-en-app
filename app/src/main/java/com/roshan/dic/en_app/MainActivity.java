package com.roshan.dic.en_app;

import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private boolean isFristRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final SharedPreferences shared = getSharedPreferences("prefse",0);
        final Boolean theme = shared.getBoolean("theme?",false);

        if(!theme){
            setTheme(R.style.AppTheme);
        }else {
            setTheme(R.style.myAppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        isFristRun = getSharedPreferences("appInfo",MODE_PRIVATE).getBoolean("isFirstRun",true);
        if(isFristRun){
            final NiftyDialogBuilder IntrodialogBuilder = NiftyDialogBuilder.getInstance(MainActivity.this);
            IntrodialogBuilder.withTitle(MainActivity.this.getString(R.string.IntroTitle))
                    .withMessage(MainActivity.this.getString(R.string.IntroMsg))
                    .withDuration(700)
                    .withEffect(Effectstype.Fall)
                    .withButton1Text("OK")
                    .setButton1Click(new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            IntrodialogBuilder.cancel();
                        }
                    });
            IntrodialogBuilder.show();

            getSharedPreferences("appInfo",MODE_PRIVATE).edit().putBoolean("isFirstRun",false).commit();
        }

        tabLayout = (TabLayout) findViewById(R.id.tabMain);

        viewPager = (ViewPager) findViewById(R.id.vpMain);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addfragment(new Word504Fragment(),"dictionary");
        viewPagerAdapter.addfragment(new WordToffelFragment(),"game");
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);


        NavFrag navFrag = (NavFrag) getSupportFragmentManager().findFragmentById(R.id.navMain);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navFrag.setUp(drawerLayout,toolbar);
    }

    @Override
    public void onBackPressed() {
        final NiftyDialogBuilder ExitdialogBuilder = NiftyDialogBuilder.getInstance(MainActivity.this);
        ExitdialogBuilder.withTitle(getString(R.string.txtNavDialogExit))
                .withMessage(getString(R.string.txtNavDialogExitMsg))
                .withDuration(700)
                .withEffect(Effectstype.Shake)
                .withButton1Text("No")
                .setButton1Click(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        ExitdialogBuilder.cancel();
                    }
                })
                .withButton2Text("Yes")
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.exit(0);
                    }
                });
        ExitdialogBuilder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.setting){

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
