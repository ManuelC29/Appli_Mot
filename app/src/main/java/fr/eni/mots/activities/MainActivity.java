package fr.eni.mots.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
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
        Animation rotate = AnimationUtils.loadAnimation(this,R.anim.rotate);
        imButJouer.startAnimation(animation);
        imButPropos.startAnimation(animation);
        imButResult.startAnimation(animation);
        imButQuit.startAnimation(dezoom);
        imButQuit.startAnimation(rescale);


        // Implémentation de Stetho
        Stetho.initializeWithDefaults(this);

          ///////////////   INSERTION DE NIVEAU //////////////////////
//        // Création de niveau
//        Niveau niv1 = new Niveau(null,"Niveau 1");
//        Niveau niv2 = new Niveau(null,"Niveau 2");
//        Niveau niv3 = new Niveau(null, "Niveau 3");
//        Niveau niv4 = new Niveau(null,"Niveau 4");
//        Niveau niv5 = new Niveau(null, "Niveau 5");
//
//        //Instanciation du repository
//        INiveauRepository repo = new NiveauBddRepository(this);
//
//        // Enregistrement des niveaux
//        repo.insert(niv1);
//        repo.insert(niv2);
//        repo.insert(niv3);
//        repo.insert(niv4);
//        repo.insert(niv5);
//
          ///////////////   INSERTION DE LISTES //////////////////////
//        IListeRepository listRepo = new ListeBddRepository(this);
//        Liste list1 = new Liste(null,"Liste 1", 1);
//        Liste list2 = new Liste(null,"Liste 2", 1);
//        Liste list3 = new Liste(null,"Liste 3", 1);
//        Liste list4 = new Liste(null,"Liste 4", 1);
//        Liste list5 = new Liste(null,"Liste 5", 1);
//
//        listRepo.insert(list1);
//        listRepo.insert(list2);
//        listRepo.insert(list3);
//        listRepo.insert(list4);
//        listRepo.insert(list5);
//

//        IMotRepository motRepository = new MotBddRepository(this);
//        Mot mot1 = new Mot(null,"ABAT", "ABBE", "",0,1);
//        Mot mot2 = new Mot(null,"ABBE", "ABBE", "",0,1);
//        Mot mot3 = new Mot(null,"ABAT", "ABEE", "",0,1);
//        Mot mot4 = new Mot(null,"ABAT", "ABER", "",0,1);
//        Mot mot5 = new Mot(null,"ABAT", "ABLE", "",0,1);
//
//        motRepository.insert(mot1);
//        motRepository.insert(mot2);
//        motRepository.insert(mot3);
//        motRepository.insert(mot4);
//        motRepository.insert(mot5);

    }

    public void quitter_app(View view) {
        finish();
        System.exit(0);
    }

    public void Jouer(View view) {
        Intent intent = new Intent(this, NiveauActivity.class);
        startActivity(intent);
    }

    public void resultats(View view) {
        Intent intent = new Intent(this,ResultatActivity.class);
        startActivity(intent);
    }

    public void settings(View view) {
        Intent intent = new Intent(this,SettingActivity.class);
        startActivity(intent);
    }

    public void about(View view) {
        //TODO ABOUT
    }


    public void proposer(View view) {
        Intent intent = new Intent(this, ProposerActivity.class);
        startActivity(intent);
    }
}
