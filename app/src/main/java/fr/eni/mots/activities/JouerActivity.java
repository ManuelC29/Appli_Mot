package fr.eni.mots.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import fr.eni.mots.R;
import fr.eni.mots.model.Liste;
import fr.eni.mots.model.Mot;
import fr.eni.mots.viewModel.JouerViewModel;

import static java.util.Arrays.asList;

public class JouerActivity extends AppCompatActivity {

    private int numMot = 0;
    List<Mot> lstMot = new ArrayList<>();
    Mot motEnCours = null;


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

        JouerViewModel jouerViewModel = ViewModelProviders.of(this).get(JouerViewModel.class);
        LiveData<List<Mot>> observateur = jouerViewModel.getMotsListNivObserver(liste.getId(), liste.getIdNiveau());

        observateur.observe(this, new Observer<List<Mot>>() {

            @Override
            public void onChanged(final List<Mot> mots) {


                    Mot mot = mots.get(numMot);
                    //Toast.makeText(JouerActivity.this, ""+mot.toString(), Toast.LENGTH_LONG).show();

                    ImageView image = findViewById(R.id.iv_jeux_img);
                    TextView textProp = findViewById(R.id.tv_jeux_mot_prop);

                    int resID = JouerActivity.this.getResources().getIdentifier(mot.getImage(), "drawable", getPackageName());
                    image.setImageResource(resID);

                    textProp.setText(mot.getMotCorrect());

                    String motCorrect = mot.getMotCorrect();
                    String motMelange = melangeMoiCe(mot.getMotCorrect());

                    textProp.setText(motMelange);

            }
        });

        //boucler jusqu'à ce que la liste soit terminée
        //envoyé sur les résultats avec l'écran résultat

    }

    /**
     * Prend un paramètre un mot et le mélange
     * @param mot
     * @return mot mélangé
     */
    private String melangeMoiCe(String mot) {

        List<Character> list = new ArrayList<>();
        for (char c : mot.toCharArray()) {
            list.add(c);
        }

        StringBuilder b = new StringBuilder();
        String motAsend = null;

        do {
            Collections.shuffle(list);

            list.forEach(b::append);

            motAsend = list.toString();

        } while (mot.toLowerCase().equals(motAsend.toLowerCase()));


        return b.toString();
    }
}
