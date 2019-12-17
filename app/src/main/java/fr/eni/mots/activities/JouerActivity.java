package fr.eni.mots.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import fr.eni.mots.R;
import fr.eni.mots.model.Liste;

public class JouerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_jouer);

        Intent intent = getIntent();
        Liste liste = intent.getParcelableExtra("liste");

        Log.i("RRR", "Liste" + liste.toString());

        //piste pour les images
        //imageview.setImageURI(Uri.parse("pathofimage"));


        //TODO GAME

        //récupérer tous les mot de la liste choisie

        //setter les champs avec les paramètre du premier mot

        //boucler jusqu'à ce que la liste soit terminée

        //envoyé sur les résultats avec l'écran résultat


    }
}
