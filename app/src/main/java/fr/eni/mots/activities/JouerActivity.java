package fr.eni.mots.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import fr.eni.mots.R;
import fr.eni.mots.model.Liste;
import fr.eni.mots.model.Mot;
import fr.eni.mots.repository.mot.IMotRepository;
import fr.eni.mots.repository.mot.MotBddRepository;
import fr.eni.mots.viewModel.JouerViewModel;

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
        ImageButton btnPasser = findViewById(R.id.btn_passer);

        //TODO GAME BUG

        JouerViewModel jouerViewModel = ViewModelProviders.of(this).get(JouerViewModel.class);
        LiveData<List<Mot>> observateur = jouerViewModel.getMotsListNivObserver(liste.getId(), liste.getIdNiveau());

        observateur.observe(this, new Observer<List<Mot>>() {

            @Override
            public void onChanged(List<Mot> mots) {

                    lstMot = mots;
                    motEnCours = lstMot.get(JouerActivity.this.numMot);
                    TenterMot(motEnCours);

                    //Mot mot = mots.get(numMot);
                    //Toast.makeText(JouerActivity.this, ""+mot.toString(), Toast.LENGTH_LONG).show();
//                    textProp.setText(mot.getMotCorrect());
//                    String motCorrect = mot.getMotCorrect();
//                    String motMelange = melangeMoiCe(mot.getMotCorrect());
//                    textProp.setText(motMelange);

                btnPasser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        numMot++;
                        //wipe de la vue
                        setContentView(R.layout.activity_jouer);

                        ImageView image = findViewById(R.id.iv_jeux_img);
                        TextView textProp = findViewById(R.id.tv_jeux_mot_prop);

                        int resID = JouerActivity.this.getResources().getIdentifier(motEnCours.getImage(), "drawable", getPackageName());
                        image.setImageResource(resID);
                        textProp.setText(melangeMoiCe(motEnCours.getMotCorrect()));

                    }
                });
            }
        });
    }

    private void TenterMot(Mot motEnCours) {
        ImageButton btnValider = findViewById(R.id.btn_valider);

        //Set des champs textview et Imageview
        ImageView image = findViewById(R.id.iv_jeux_img);
        TextView textProp = findViewById(R.id.tv_jeux_mot_prop);

        int resID = JouerActivity.this.getResources().getIdentifier(motEnCours.getImage(), "drawable", getPackageName());
        image.setImageResource(resID);

        textProp.setText(melangeMoiCe(motEnCours.getMotCorrect()));


        btnValider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //changement de mot
                numMot++;
                Mot mot = lstMot.get(numMot);

                //wipe de la vue
                setContentView(R.layout.activity_jouer);

                ImageView image = findViewById(R.id.iv_jeux_img);
                TextView textProp = findViewById(R.id.tv_jeux_mot_prop);

                int resID = JouerActivity.this.getResources().getIdentifier(mot.getImage(), "drawable", getPackageName());
                image.setImageResource(resID);
                textProp.setText(melangeMoiCe(mot.getMotCorrect()));
            }


        });
    }


    /**
     * Fonction qui renvoie vrai si le mot proposé correspond au mot à trouver
     * @param mot
     * @param motProp
     * @return
     */
    private Boolean VerifMot(String mot, String motProp) {
        return motProp.toLowerCase().equals(mot.toLowerCase());
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
        String motEnvoi = null;

        do {
            Collections.shuffle(list);
            list.forEach(b::append);
            motEnvoi = list.toString();
        } while (mot.toLowerCase().equals(motEnvoi.toLowerCase()));

        return b.toString();
    }
}
