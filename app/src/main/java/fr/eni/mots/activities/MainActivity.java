package fr.eni.mots.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import fr.eni.mots.R;
import fr.eni.mots.model.Liste;
import fr.eni.mots.model.Niveau;
import fr.eni.mots.repository.niveau.INiveauRepository;
import fr.eni.mots.repository.niveau.NiveauBddRepository;

public class MainActivity extends AppCompatActivity {

    ImageView imButJouer;
    ImageView imButResult;
    ImageView imButPropos;
    ImageView imButQuit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On enlève la barre
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        imButJouer = findViewById(R.id.btn_jouer);
        imButPropos = findViewById(R.id.btn_propos);
        imButResult = findViewById(R.id.btn_result);
        imButQuit = findViewById(R.id.btn_quitter);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        Animation dezoom = AnimationUtils.loadAnimation(this, R.anim.left);
        Animation rescale = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        imButJouer.startAnimation(animation);
        imButPropos.startAnimation(animation);
        imButResult.startAnimation(animation);
        imButQuit.startAnimation(dezoom);
        imButQuit.startAnimation(rescale);

        // Implémentation de Stetho
        Stetho.initializeWithDefaults(this);

        // Création d'un niveau
        //Niveau niv2 = new Niveau(null,"Niveau 2");
        //Instanciation du repository
        INiveauRepository repo = new NiveauBddRepository(this);

        //get un niveau

        //Niveau niveau = repo.get(1);
        //Toast.makeText(this, "Niveau : "+niveau , Toast.LENGTH_LONG).show();

        //un update
        //repo.update(niv3);

        //un delete
        //repo.delete(2);
    }

    public void quitter_app(View view) {
        finish();
        System.exit(0);
    }

    public void Jouer(View view) {

        Intent intent = new Intent(this,JouerActivity.class);
        startActivity(intent);

    }

    public void settings(View view) {
        //TODO SETTINGS
    }

    public void about(View view) {
        //TODO ABOUT
    }
}
