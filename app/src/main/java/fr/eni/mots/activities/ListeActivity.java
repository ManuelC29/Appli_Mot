package fr.eni.mots.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import fr.eni.mots.R;
import fr.eni.mots.adapter.ListeAdapter;
import fr.eni.mots.model.Liste;
import fr.eni.mots.model.Niveau;
import fr.eni.mots.repository.liste.IListeRepository;
import fr.eni.mots.repository.liste.ListeBddRepository;
import fr.eni.mots.viewModel.ListeViewModel;


public class ListeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_liste);


        Intent intent = getIntent();
        Niveau niveau = intent.getParcelableExtra("niveau");

        ListeViewModel listeViewModel = ViewModelProviders.of(this).get(ListeViewModel.class);
        LiveData<List<Liste>> observateur = listeViewModel.getListByIdNivObserver(niveau.getId());

        observateur.observe(this, new Observer<List<Liste>>() {
            @Override
            public void onChanged(final List<Liste> listes) {

                //création
                ListeAdapter adapter = new ListeAdapter(ListeActivity.this, R.layout.style_ligne_liste, listes);

                //find la listview et la set
                ListView listview = findViewById(R.id.ma_liste_liste);
                listview.setAdapter(adapter);

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ListeActivity.this, JouerActivity.class);
                        intent.putExtra("liste", listes.get(position));
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right2, R.anim.slide_out_left2);
                    }
                });
            }
        });
    }
}
