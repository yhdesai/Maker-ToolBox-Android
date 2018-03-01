package io.github.yhdesai.devprops;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import io.github.yhdesai.devprops.Fragments.Ideas;
import io.github.yhdesai.devprops.Fragments.general;
import io.github.yhdesai.devprops.Fragments.help;
import io.github.yhdesai.devprops.Fragments.intro;
import io.github.yhdesai.devprops.Fragments.resources;
import io.github.yhdesai.devprops.Fragments.workshop;

public class chat extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FragmentManager general = getFragmentManager();
        general.beginTransaction().replace(R.id.content_chat_frame, new general()).commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent signout = new Intent(chat.this, MainActivity.class);
            startActivity(signout);
            return true;
        } else if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.chat_general) {
            FragmentManager general = getFragmentManager();
            general.beginTransaction().replace(R.id.content_chat_frame, new general()).commit();
        } else if (id == R.id.chat_intro) {
            FragmentManager intro = getFragmentManager();
            intro.beginTransaction().replace(R.id.content_chat_frame, new intro()).commit();
        } else if (id == R.id.chat_ideas) {
            FragmentManager ideas = getFragmentManager();
            ideas.beginTransaction().replace(R.id.content_chat_frame, new Ideas()).commit();
        } else if (id == R.id.chat_help) {
            FragmentManager help = getFragmentManager();
            help.beginTransaction().replace(R.id.content_chat_frame, new help()).commit();
        } else if (id == R.id.chat_resource) {
            FragmentManager res = getFragmentManager();
            res.beginTransaction().replace(R.id.content_chat_frame, new resources()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
