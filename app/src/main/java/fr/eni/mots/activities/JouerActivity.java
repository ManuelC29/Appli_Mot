package fr.eni.mots.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
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

    List<Mot> lstMot = new ArrayList<>();
    Mot motEnCours = null;
    int index = 0;

    ImageButton btnValider = null;
    ImageView image = null;
    TextView textProp = null;
    EditText motEdit = null;
    ImageView messageFond = null;
    TextView messageTexte = null;
    ImageView messageEtoileGauche = null;
    ImageView messageEtoileDroite = null;
    int status = 0;

    final int RATER = 3;
    final int PASSER = 2;
    final int TROUVER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_jouer);

        btnValider = findViewById(R.id.btn_valider);
        image = findViewById(R.id.iv_jeux_img);
        textProp = findViewById(R.id.tv_jeux_mot_prop);
        motEdit = findViewById(R.id.et_jeux);
        messageFond = findViewById(R.id.iv_jeux_img_dialog);
        messageTexte = findViewById(R.id.tv_jeux_reponse);
        messageEtoileGauche = findViewById(R.id.iv_jeux_star_gauche);
        messageEtoileDroite = findViewById(R.id.iv_jeux_star_droite);

        Intent intent = getIntent();
        Liste liste = intent.getParcelableExtra("liste");
        ImageButton btnPasser = findViewById(R.id.btn_passer);

        //TODO GAME BUG

        JouerViewModel jouerViewModel = ViewModelProviders.of(this).get(JouerViewModel.class);
        MutableLiveData<List<Mot>> observateur = jouerViewModel.getMotsListNivObserver(liste.getId());

        observateur.observe(this, new Observer<List<Mot>>() {

            @Override
            public void onChanged(List<Mot> mots) {

                lstMot = mots;
                motEnCours = lstMot.get(index);
                jouer();

//                //TODO PASSER
                btnPasser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ImageView image = findViewById(R.id.iv_jeux_img);
                        TextView textProp = findViewById(R.id.tv_jeux_mot_prop);

                        int resID = JouerActivity.this.getResources().getIdentifier(motEnCours.getImage(), "drawable", getPackageName());
                        image.setImageResource(resID);
                        textProp.setText(melangeMoiCe(motEnCours.getMotCorrect()));

                        afficherMessage("P A S S E R", getColor(R.color.blanc), getColor(R.color.rouge_transparent));
                        String motJoue = "";
                        updateMot(motEnCours,motJoue, PASSER);

                        JouerActivity.this.index++;
                        //Vérification si on est à la fin de la liste de mot
                        finDeTab();

                        JouerActivity.this.motEnCours = lstMot.get(JouerActivity.this.index);
                        bindEcran(JouerActivity.this.motEnCours);


                    }
                });
            }
        });

    }

    /**
     * Vérifie si on est à la fin du tableau
     */
    private void finDeTab() {
        if(index == 5){
            afficherMessage("Fin de Liste", getColor(R.color.blanc), getColor(R.color.noir_transparent));
            Intent intent = new Intent(JouerActivity.this, ResultatActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right2, R.anim.slide_out_left2);
        }
    }

    /**
     * Affichage d'un message permettant d'afficher à l'utilisateur le rendu de son action
     * @param message
     * @param couleur
     * @param couleurFond
     */
    @SuppressLint("StaticFieldLeak")
    private void afficherMessage(String message, int couleur, int couleurFond) {
         //on décache nos affichage

       new AsyncTask<Void,Void,Void>()
        {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                JouerActivity.this.messageFond.setVisibility(View.VISIBLE);
                messageTexte.setVisibility(View.VISIBLE);
                messageTexte.setText(message);
                messageTexte.setTextColor(couleur);
                messageFond.setBackgroundColor(couleurFond);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                messageFond.setVisibility(View.GONE);
                messageTexte.setVisibility(View.GONE);
            }
        }.execute();
    }

    /**
     * Fonction permettant de lancer le jeux sur le mot courant
     */
    private void jouer()
    {
        motEnCours =  lstMot.get(index);

        bindEcran(motEnCours);

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //je récupère le champ joué par l'utilisateur
                String motJoue = motEdit.getText().toString();

                if (verif(motEnCours.getMotCorrect(), motJoue))
                {
                    motEdit.setText("");
                    JouerActivity.this.index++;
                    JouerActivity.this.motEnCours = lstMot.get(JouerActivity.this.index);
                    bindEcran(JouerActivity.this.motEnCours);
                    updateMot(motEnCours,motJoue,TROUVER);

                    afficherMessage("C O R R E C T E", getColor(R.color.blanc), getColor(R.color.vert));

                }else{
                    //Le mot proposé n'était pas le bon
                    afficherMessage("I N C O R R E C T E", getColor(R.color.blanc), getColor(R.color.orange_transparent));
                    motEdit.setText("");
                    updateMot(motEnCours,motJoue,RATER);
                }
            }

        });
    }

    /**
     * Set l'écran avec les valeurs
     * @param newMot
     */
    private void bindEcran(Mot newMot)
    {
        int resID = JouerActivity.this.getResources().getIdentifier(newMot.getImage(), "drawable", getPackageName());
        image.setImageResource(resID);
        textProp.setText(melangeMoiCe(newMot.getMotCorrect()));
    }

    /**
     * Fonction qui va update le mot qui a été joué
     * @param motSet
     * @param motJoue
     * @param stat
     */
    private void updateMot(Mot motSet,String motJoue,int stat) {
        JouerViewModel jouerViewModel = ViewModelProviders.of(this).get(JouerViewModel.class);

        if(stat == PASSER) {
            motSet.setMotPropose("");
            motSet.setEtat(PASSER);
        }

        if(stat == TROUVER) {
            motSet.setMotPropose(motJoue);
            motSet.setEtat(TROUVER);
        }

        if(stat == RATER){
            motSet.setMotPropose(motJoue);
            motSet.setEtat(RATER);
        }

        Log.i("XXX","SAVE : " + motEnCours);
        jouerViewModel.updateMot(motEnCours);

        finDeTab();
    }

    /**
     * Fonction qui renvoie vrai si le mot proposé correspond au mot à trouver
     *
     * @param mot
     * @param motProp
     * @return
     */
    private Boolean verif(String mot, String motProp) {
        return motProp.toLowerCase().equals(mot.toLowerCase());
    }

    /**
     * Prend un paramètre un mot et le mélange
     *
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
