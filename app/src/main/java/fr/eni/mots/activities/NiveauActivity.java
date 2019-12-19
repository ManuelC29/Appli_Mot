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
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.List;
import fr.eni.mots.R;
import fr.eni.mots.adapter.NiveauAdapter;
import fr.eni.mots.model.Niveau;
import fr.eni.mots.repository.niveau.INiveauRepository;
import fr.eni.mots.repository.niveau.NiveauBddRepository;
import fr.eni.mots.viewModel.NiveauViewModel;

public class NiveauActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_niveau);

        NiveauViewModel niveauViewModel = ViewModelProviders.of(this).get(NiveauViewModel.class);
        LiveData<List<Niveau>> observateur = niveauViewModel.get();

        observateur.observe(this, new Observer<List<Niveau>>() {

            @Override
            public void onChanged(final List<Niveau> niveaux) {

                //création
                NiveauAdapter adapter = new NiveauAdapter(NiveauActivity.this,R.layout.style_ligne_niv, niveaux);

                //find la listview
                ListView listview = findViewById(R.id.ma_liste_niveau);
                listview.setAdapter(adapter);

                //set des données dans la listeview
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(NiveauActivity.this,ListeActivity.class);
                        intent.putExtra("niveau",niveaux.get(position));
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right2, R.anim.slide_out_left2);
                    }
                });

            }
        });
    }
}
