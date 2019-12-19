package fr.eni.mots.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.facebook.stetho.Stetho;
import fr.eni.mots.R;
import fr.eni.mots.model.Liste;
import fr.eni.mots.model.Mot;
import fr.eni.mots.model.Niveau;
import fr.eni.mots.repository.liste.IListeRepository;
import fr.eni.mots.repository.liste.ListeBddRepository;
import fr.eni.mots.repository.mot.IMotRepository;
import fr.eni.mots.repository.mot.MotBddRepository;
import fr.eni.mots.repository.niveau.INiveauRepository;
import fr.eni.mots.repository.niveau.NiveauBddRepository;


public class MainActivity extends AppCompatActivity {

    ImageView imButJouer;
    ImageView imButResult;
    ImageView imButPropos;
    ImageView imButQuit;
    ImageView imButsetGris;
    ImageView imTitre;

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On enlève la barre
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        player = MediaPlayer.create(this,R.raw.zelda);
        String son = getSharedPreferences("setting_app",MODE_PRIVATE).getString("son","OFF");
        Log.i("ZZZ",son);
        if("ON".equals(son)){
            player.start();
        }else if("OFF".equals(son)){
            player.stop();
        }

        imButJouer = findViewById(R.id.btn_jouer);
        imButPropos = findViewById(R.id.btn_propos);
        imButResult = findViewById(R.id.btn_result);
        imButQuit = findViewById(R.id.btn_quitter);
        imButsetGris = findViewById(R.id.setting_cran_gris);
        imTitre = findViewById(R.id.tv_accueil_titre);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        Animation dezoom = AnimationUtils.loadAnimation(this, R.anim.left);
        Animation rescale = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        Animation rotate = AnimationUtils.loadAnimation(this,R.anim.together);
        Animation bounce = AnimationUtils.loadAnimation(this,R.anim.sequential);
        Animation slideLeft = AnimationUtils.loadAnimation(this,R.anim.slide_in_left);
        Animation slideRight = AnimationUtils.loadAnimation(this,R.anim.slide_in_right);

        imButJouer.startAnimation(slideRight);
        imButResult.startAnimation(slideLeft);
        imButPropos.startAnimation(slideRight);
        imButQuit.startAnimation(dezoom);
        imButQuit.startAnimation(rescale);
        imButsetGris.startAnimation(rotate);
        imTitre.startAnimation(bounce);

        // Implémentation de Stetho
        Stetho.initializeWithDefaults(this);


    }

    public void quitter_app(View view) {
        finish();
        System.exit(0);
    }

    public void Jouer(View view) {
        Intent intent = new Intent(this, NiveauActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right2, R.anim.slide_out_left2);
    }

    public void resultats(View view) {
        Intent intent = new Intent(this,ResultatActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right2, R.anim.slide_out_left2);
    }

    public void settings(View view) {
        Intent intent = new Intent(this,SettingActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void aPropos(View view) {
        Intent intent = new Intent(this, AproposActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    public void proposer(View view) {
        Intent intent = new Intent(this, ProposerActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void information(View view) {
        Intent intent = new Intent(this, InformationActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
