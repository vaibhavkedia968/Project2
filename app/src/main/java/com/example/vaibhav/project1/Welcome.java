package com.example.vaibhav.project1;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class Welcome extends AppCompatActivity {
    Spinner category;
    Button go;
    boolean cat;
    TextView wu;
    int timelimit=60;
    int selected;
    String[] c={"Select your category","Aptitude","Science","Current Affairs"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_xml);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        category=(Spinner)findViewById(R.id.category);
        go=(Button)findViewById(R.id.cont);
        loadCategory();

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected=position;
                if (position > 0)
                {
                    cat=true;
                    Toast.makeText(parent.getContext(), "You selected: " + c[position], Toast.LENGTH_LONG).show();
                }
                else
                    cat = false;
                if (cat)
                    go.setEnabled(true);
                else
                    go.setEnabled(false);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cont();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id)
        {
            case R.id.myprofile:
                myProfile();
                break;

            case R.id.signout:
                signout();
                break;

            case R.id.about:
                Intent i=new Intent(Welcome.this,AboutActivity.class);
                startActivity(i);
                break;

            case R.id.contact:
                contact();
                break;

            case R.id.exit:
                exit();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    public void myProfile()
    {
        Intent i=new Intent(Welcome.this,ProfileActivity.class);
        startActivity(i);
    }
    public void signout()
    {
        AlertDialog.Builder b=new AlertDialog.Builder(this);
        b.setMessage("")
                .setCancelable(false)
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent j=new Intent(Welcome.this,MainActivity.class);
                        DatabaseHandler db=new DatabaseHandler(getApplicationContext());
                        db.signOut(MainActivity.currentuser);
                        startActivity(j);
                        MainActivity.currentuser="";
                        finish();
                    }
                });
        AlertDialog a= b.create();
        a.setTitle("Are you sure you want to sign out?");
        a.show();
    }
    public void exit()
    {
        AlertDialog.Builder b=new AlertDialog.Builder(this);
        b.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        AlertDialog a= b.create();
        a.setTitle("Confirm exit");
        a.show();
    }
    public void contact()
    {
        AlertDialog.Builder b=new AlertDialog.Builder(this);
        b.setMessage("You can email us your queries :\nquizcom@gmail.com")
                .setCancelable(true)
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog a= b.create();
        a.setTitle("Contact Us");
        a.show();
    }
    public void onBackPressed()
    {
        exit();
    }
    public void loadCategory()
    {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, c);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        category.setAdapter(dataAdapter);
    }

    public void cont()
    {
        AlertDialog.Builder b=new AlertDialog.Builder(this);
        b.setMessage("")
                .setCancelable(false)
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(selected)
                        {
                            case 1:
                                Intent i1=new Intent(Welcome.this,AptituteQ.class);
                                startActivity(i1);
                                break;

                            case 2:
                                Intent i2=new Intent(Welcome.this,ScienceQ.class);
                                startActivity(i2);
                                break;

                            case 3:
                                Intent i3=new Intent(Welcome.this,CurrentAffairsQ.class);
                                startActivity(i3);
                                break;
                        }
                    }
                });
        AlertDialog a= b.create();
        a.setTitle("Start the quiz?");
        a.show();
    }
}


