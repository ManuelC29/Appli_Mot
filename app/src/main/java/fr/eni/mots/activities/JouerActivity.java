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
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import fr.eni.mots.R;
import fr.eni.mots.model.Liste;
import fr.eni.mots.model.Mot;
import fr.eni.mots.viewModel.JouerViewModel;

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

        JouerViewModel jouerViewModel = ViewModelProviders.of(this).get(JouerViewModel.class);
        LiveData<List<Mot>> observateur = jouerViewModel.getMotsListNivObserver(liste.getId(),liste.getIdNiveau());

        observateur.observe(this, new Observer<List<Mot>>() {

            @Override
            public void onChanged(final List<Mot> mots) {

                for (int i = 0; i< mots.size() ; i++ ) {
                    Mot mot = mots.get(i);
                    //Toast.makeText(JouerActivity.this, ""+mot.toString(), Toast.LENGTH_LONG).show();

                }


//                //création
//                NiveauAdapter adapter = new NiveauAdapter(NiveauActivity.this,R.layout.style_ligne_niv, niveaux);
//
//                //find la listview
//                ListView listview = findViewById(R.id.ma_liste_niveau);
//                listview.setAdapter(adapter);
//
//                //set des données dans la listeview
//                listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
//                {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Intent intent = new Intent(NiveauActivity.this,ListeActivity.class);
//                        intent.putExtra("niveau",niveaux.get(position));
//                        startActivity(intent);
//                        overridePendingTransition(R.anim.slide_in_right2, R.anim.slide_out_left2);
//                    }
//                });

            }
        });
















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
