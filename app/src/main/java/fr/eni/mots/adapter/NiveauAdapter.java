package fr.eni.mots.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fr.eni.mots.R;
import fr.eni.mots.model.Niveau;

public class NiveauAdapter extends ArrayAdapter<Niveau> {

    public NiveauAdapter(@NonNull Context context, int resource, @NonNull List<Niveau> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater pompe = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View fichierXmlDecompresse = pompe.inflate(R.layout.style_ligne,parent,false);


        TextView tvLibelle = fichierXmlDecompresse.findViewById(R.id.tv_lst_niv_libelle);


        Niveau niveau = getItem(position);

        tvLibelle.setText(niveau.getLibelle());
        return fichierXmlDecompresse;
    }

}
