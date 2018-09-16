package com.deepers.vida;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import com.deepers.sandbox.R;

public class BottomTabsActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private CalendarView mCal;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_today:
                    mTextMessage.setText(R.string.title_today);
                    removeProfileFragment();
                    return true;
                case R.id.navigation_journey:
                    mTextMessage.setText(R.string.title_journey);
                    removeProfileFragment();
                    return true;
                case R.id.navigation_profile:
                    //mTextMessage.setText(R.string.title_notifications);
                    addProfileFragment();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tabs);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        mTextMessage = (TextView) findViewById(R.id.message);
        mCal = findViewById(R.id.date_chooser);
        mCal.setVisibility(View.INVISIBLE);
        mCal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                mCal.setVisibility(View.INVISIBLE);
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_overflow, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_calendar) {
            if (mCal != null) {
                boolean visibile = mCal.getVisibility() == View.VISIBLE;
                mCal.setVisibility(visibile ? View.GONE : View.VISIBLE);
            }
            return true;
        }

        if (id == R.id.action_goals) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addProfileFragment() {
        FragmentManager fm = getSupportFragmentManager();
        ProfileFragment frag = (ProfileFragment) fm.findFragmentByTag("profileFragment");
        if (frag == null) {
            frag = ProfileFragment.newInstance("foo", "bar");
        }
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, frag, "profileFragment").commit();
    }

    private void removeProfileFragment() {
        FragmentManager fm = getSupportFragmentManager();
        ProfileFragment frag = (ProfileFragment) fm.findFragmentByTag("profileFragment");
        if (frag != null) {
            Log.d("BottomTabsActivity", "removing ProfileFragment");
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.remove(frag).commit();
        } else {
            Log.d("BottomTabsActivity", "ProfileFragment not found");
        }
    }


}
