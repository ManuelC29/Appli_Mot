package fr.eni.mots.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;

import fr.eni.mots.R;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_setting);

        // Lancer un son sous Android
        Switch switchSon = findViewById(R.id.switch_son);
        String son = getSharedPreferences("setting_app",MODE_PRIVATE).getString("son","ON");
        MediaPlayer player = MediaPlayer.create(this,R.raw.avengers);

        // Vérification des SharedPreference
        if("ON".equals(son)){
            switchSon.setChecked(true);
            if(player == null) {
                player.start();
            }
        }else if("OFF".equals(son)){
            switchSon.setChecked(false);
            player.stop();
        }

        switchSon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    // ON
                    player.start();
                    SharedPreferences settings = getSharedPreferences("setting_app", MODE_PRIVATE);
                    SharedPreferences.Editor prefEditor = settings.edit();
                    prefEditor.putString("son", "ON");
                    prefEditor.putString("vibration", "OFF");
                    prefEditor.apply();
                } else {
                    // OFF
                    SharedPreferences settings = getSharedPreferences("setting_app", MODE_PRIVATE);
                    SharedPreferences.Editor prefEditor = settings.edit();
                    prefEditor.putString("son", "OFF");
                    prefEditor.putString("vibration", "OFF");
                    prefEditor.apply();
                    prefEditor.commit();
                    player.stop();
                }
            }
        });
    }
}
