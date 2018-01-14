package com.example.vaibhav.project1;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class NewUser extends AppCompatActivity implements TextToSpeech.OnInitListener{
    private TextToSpeech tts;
    TextView wu;
    Button c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        wu=(TextView)findViewById(R.id.textView6);
        c=(Button)findViewById(R.id.button);
        Bundle b=getIntent().getExtras();
        wu.setText("WELCOME\n"+b.getString("username"));
        tts=new TextToSpeech(this,this);
        speakOut();
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(NewUser.this,Welcome.class);
                startActivity(i);
                finish();
            }
        });

    }
    @Override
    public void onDestroy()
    {
        if(tts!=null)
        {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }
    private void speakOut() {
        String text = wu.getText().toString();
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
