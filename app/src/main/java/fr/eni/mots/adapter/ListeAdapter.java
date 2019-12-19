package fr.eni.mots.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
import fr.eni.mots.R;
import fr.eni.mots.model.Liste;

public class ListeAdapter extends ArrayAdapter<Liste> {

    public ListeAdapter(@NonNull Context context, int resource, @NonNull List<Liste> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater pompe = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View fichierXmlDecompresse = pompe.inflate(R.layout.style_ligne_liste,parent,false);
        TextView tvLibelle = fichierXmlDecompresse.findViewById(R.id.tv_lst_liste_libelle);
        Liste liste = getItem(position);

        tvLibelle.setText(liste.getLibelle());
        return fichierXmlDecompresse;
    }
}
