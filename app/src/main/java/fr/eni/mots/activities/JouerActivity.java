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

        //TODO GAME


//        ImageView image = findViewById(R.id.iv_image);
//        TextView motMel = findViewById(R.id.tv_motmel);
//
//        //On recupere id de l'image grace a son nom de ressource, this est le context
//        int resID = this.getResources().getIdentifier(currentMot.getLienImage(), "drawable", getPackageName());
//        image.setImageResource(resID);                  "nomble de l'image"





        //récupérer tous les mot de la liste choisie

        //setter les champs avec les paramètre du premier mot

        //boucler jusqu'à ce que la liste soit terminée

        //envoyé sur les résultats avec l'écran résultat


    }
}
