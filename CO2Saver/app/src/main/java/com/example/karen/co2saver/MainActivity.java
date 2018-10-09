package com.example.karen.co2saver;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public android.support.v4.app.FragmentManager fragmentManager;

    Button button_add;
    EditText login;
    EditText password;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v4.app.FragmentTransaction fragmentTransaction = null;

        fragmentManager = getSupportFragmentManager();

        login = (EditText)findViewById(R.id.login);
        password = (EditText)findViewById(R.id.password);

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        db=(DB)getApplication();
        db.Open();

        button_add = (Button)findViewById(R.id.btn_login);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.Save("insert into clientes values ('"+login.getText().toString().trim()+"','"+password.getText().toString().trim()+"')");
                finish();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,  R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.perfil) {
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Profile f2 = new Profile();
            fragmentTransaction.replace(R.id.frame, f2, "f2_1");
            fragmentTransaction.commit();
            Toast.makeText(this, "teste", Toast.LENGTH_SHORT).show();
            // Handle the camera action
        } else if (id == R.id.payment) {

        } else if (id == R.id.abastecer) {
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            MapsActivity f2 = new MapsActivity();
            fragmentTransaction.replace(R.id.map, f2, "f2_1");
            fragmentTransaction.commit();
            Toast.makeText(this, "teste", Toast.LENGTH_SHORT).show();
            // Handle the camera action

        } else if (id == R.id.cadastro_posto) {

        } else if (id == R.id.alteracao) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
