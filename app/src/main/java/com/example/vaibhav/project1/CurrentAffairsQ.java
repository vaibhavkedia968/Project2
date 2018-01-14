package com.example.vaibhav.project1;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by user on 1/14/2018.
 */

public class CurrentAffairsQ extends AppCompatActivity {
    TextView t1, t2, t3, t4, t5;
    RadioButton r1a,r1b,r1c,r1d,r2a,r2b,r2c,r2d,r3a,r3b,r3c,r3d,r4a,r4b,r4c,r4d,r5a,r5b,r5c,r5d;
    Button b;
    int score=0;
    String[] questions = {"1.Which state Government signed a MoU with Central Government for the 2nd phase of Bharat Net project?"
            ,"2.How many Satellites will be launched by ISRO in a single mission on-board its Polar rocket on 10th January 2018?"
            ,"3.Which bank Nod to Start India's 1st Foreign-Owned ARC?"
            ,"4.The 2018 World Hindi Day (WHD) is observed on "
            ,"5.Who is appointed as the chairman of ISRO recently?"
    };
    String[][] option={
            {"Haryana","Bihar","Odisha","Chhatisgah","D"},
            {"28","42","31","50","c"},
            {"DENA BANK","CANARA BANK","STATE BANK OF INDIA","RESERVE BANK OF INDIA","B"},
            {"January 9","January 10","January 11","January 12","C",},
            {"SHRI SHIVA K","SHRI NARESH IYER","SHRI VIDYA KUMAR","SHRI VIKRAM MENON","C"}
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions1);
        TextView heading=(TextView)findViewById(R.id.textView4);
        heading.setText("Current Affairs Quiz");
        t1 = (TextView) findViewById(R.id.q1);
        t2 = (TextView) findViewById(R.id.q2);
        t3 = (TextView) findViewById(R.id.q3);
        t4 = (TextView) findViewById(R.id.q4);
        t5 = (TextView) findViewById(R.id.q5);
        r1a=(RadioButton)findViewById(R.id.q1a);
        r1b=(RadioButton)findViewById(R.id.q1b);
        r1c=(RadioButton)findViewById(R.id.q1c);
        r1d=(RadioButton)findViewById(R.id.q1d);
        r2a=(RadioButton)findViewById(R.id.q2a);
        r2b=(RadioButton)findViewById(R.id.q2b);
        r2c=(RadioButton)findViewById(R.id.q2c);
        r2d=(RadioButton)findViewById(R.id.q2d);
        r3a=(RadioButton)findViewById(R.id.q3a);
        r3b=(RadioButton)findViewById(R.id.q3b);
        r3c=(RadioButton)findViewById(R.id.q3c);
        r3d=(RadioButton)findViewById(R.id.q3d);
        r4a=(RadioButton)findViewById(R.id.q4a);
        r4b=(RadioButton)findViewById(R.id.q4b);
        r4c=(RadioButton)findViewById(R.id.q4c);
        r4d=(RadioButton)findViewById(R.id.q4d);
        r5a=(RadioButton)findViewById(R.id.q5a);
        r5b=(RadioButton)findViewById(R.id.q5b);
        r5c=(RadioButton)findViewById(R.id.q5c);
        r5d=(RadioButton)findViewById(R.id.q5d);
        b=(Button)findViewById(R.id.button2);
        showQuestion();
        showOptions();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b=new AlertDialog.Builder(CurrentAffairsQ.this);
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
                                calScore();
                                DatabaseHandler db=new DatabaseHandler(getApplicationContext());
                                db.update(MainActivity.currentuser,score);
                                showScore();
                            }
                        });
                AlertDialog a= b.create();
                a.setTitle("Submit?");
                a.show();
            }
        });
    }

    public void calScore() {
        if(r1d.isChecked()){
            score+=4;
        }
        if(r2c.isChecked()){
            score+=4;
        }
        if(r3b.isChecked()){
            score+=4;
        }
        if(r4c.isChecked()){
            score+=4;
        }
        if(r5a.isChecked()){
            score+=4;
        }
    }


    public void showQuestion() {
        t1.setText(questions[0]);
        t2.setText(questions[1]);
        t3.setText(questions[2]);
        t4.setText(questions[3]);
        t5.setText(questions[4]);

    }
    public void showOptions(){
        r1a.setText(option[0][0]);
        r1b.setText(option[0][1]);
        r1c.setText(option[0][2]);
        r1d.setText(option[0][3]);
        r2a.setText(option[1][0]);
        r2b.setText(option[1][1]);
        r2c.setText(option[1][2]);
        r2d.setText(option[1][3]);
        r3a.setText(option[2][0]);
        r3b.setText(option[2][1]);
        r3c.setText(option[2][2]);
        r3d.setText(option[2][3]);
        r4a.setText(option[3][0]);
        r4b.setText(option[3][1]);
        r4c.setText(option[3][2]);
        r4d.setText(option[3][3]);
        r5a.setText(option[4][0]);
        r5b.setText(option[4][1]);
        r5c.setText(option[4][2]);
        r5d.setText(option[4][3]);

    }
    public void showScore()
    {
        AlertDialog.Builder b=new AlertDialog.Builder(CurrentAffairsQ.this);
        b.setMessage("You scored "+score)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        AlertDialog a= b.create();
        a.setTitle("");
        a.show();
    }
}
