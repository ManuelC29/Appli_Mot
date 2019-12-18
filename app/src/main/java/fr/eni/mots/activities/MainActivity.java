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
import fr.eni.mots.repository.niveau.INiveauRepository;
import fr.eni.mots.repository.niveau.NiveauBddRepository;


public class MainActivity extends AppCompatActivity {

    ImageView imButJouer;
    ImageView imButResult;
    ImageView imButPropos;
    ImageView imButQuit;
    ImageView imButsetGris;
    ImageView imTitre;
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

          ///////////////   INSERTION DE NIVEAU //////////////////////
        // Création de niveau
        Niveau niv1 = new Niveau(null,"Niveau 1 : Niveau des Petits Joueurs");
        Niveau niv2 = new Niveau(null,"Niveau 2 : Normal, On prend pas de risque");
        Niveau niv3 = new Niveau(null, "Niveau 3 : Hard, On se prend pour une star");
        Niveau niv4 = new Niveau(null,"Niveau 4 : Nightmare, Prêt à en baver ?");
        Niveau niv5 = new Niveau(null, "Niveau 5 : Impossible, ne pas choisir");

        //Instanciation du repository
        INiveauRepository repo = new NiveauBddRepository(this);

        // Enregistrement des niveaux
        repo.insert(niv1);
        repo.insert(niv2);
        repo.insert(niv3);
        repo.insert(niv4);
        repo.insert(niv5);
//
          ///////////////   INSERTION DE LISTES //////////////////////
        IListeRepository listRepo = new ListeBddRepository(this);
        Liste list1 = new Liste(null,"Liste 1", 1);
        Liste list2 = new Liste(null,"Liste 2", 1);
        Liste list3 = new Liste(null,"Liste 3", 1);
        Liste list4 = new Liste(null,"Liste 4", 1);
        Liste list5 = new Liste(null,"Liste 5", 1);

        listRepo.insert(list1);
        listRepo.insert(list2);
        listRepo.insert(list3);
        listRepo.insert(list4);
        listRepo.insert(list5);
//

        IMotRepository motRepository = new MotBddRepository(this);
        Mot mot1 = new Mot(null,"ABAT", "abat", "",0,1);
        Mot mot2 = new Mot(null,"ABBE", "abbe", "",0,1);
        Mot mot3 = new Mot(null,"AMIS", "amis", "",0,1);
        Mot mot4 = new Mot(null,"CERF", "cerf", "",0,1);
        Mot mot5 = new Mot(null,"DOOM", "doom", "",0,1);

        motRepository.insert(mot1);
        motRepository.insert(mot2);
        motRepository.insert(mot3);
        motRepository.insert(mot4);
        motRepository.insert(mot5);



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
        overridePendingTransition(R.anim.slide_in_right2, R.anim.slide_out_left2);
    }

    public void about(View view) {
        //TODO ABOUT
    }


    public void proposer(View view) {
        Intent intent = new Intent(this, ProposerActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right2, R.anim.slide_out_left2);
    }
}
