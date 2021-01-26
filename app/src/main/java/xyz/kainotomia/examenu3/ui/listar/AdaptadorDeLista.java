package xyz.kainotomia.examenu3.ui.listar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import java.util.List;

import xyz.kainotomia.examenu3.R;
import xyz.kainotomia.examenu3.databinding.ListaItemPacientesBinding;
import xyz.kainotomia.examenu3.model.Receta;

public class AdaptadorDeLista extends ArrayAdapter<Receta> {

    public AdaptadorDeLista(Context context, List<Receta> recetas) {
        super(context, 0, recetas);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Receta receta = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lista_item_pacientes, parent, false);
        }

        ListaItemPacientesBinding lip = ListaItemPacientesBinding.bind(convertView);

        lip.tvNombre.setText(receta.getNombre());
        lip.tvGenero.setText(lip.tvGenero.getText().toString().replace(":genero", receta.getGenero()));
        lip.tvFecha.setText(lip.tvFecha.getText().toString().replace(":fecha", receta.getFechaLanzamiento()));
        lip.tvDirector.setText(lip.tvDirector.getText().toString().replace(":director", receta.getDirector()));
        lip.tvDesc.setText(receta.getDescripcion());
        lip.tvActivo.setText(receta.isActivo() ? "Activo" : "Inactivo");
        lip.imgPoster.setImageURI(Uri.parse(receta.getPosterPath()));
        lip.tvId.setText(receta.getId() + "");

        lip.btnActivo.setText(receta.isActivo() ? "Desactivar" : "Activar");


        lip.btnActivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                receta.setActivo(!receta.isActivo());
                receta.save(getContext());
                AdaptadorDeLista.this.remove(receta);
            }
        });

        lip.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("¿Desea eliminar la receta?");
                dialog.setMessage("Para eliminar la receta presione confirmar");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        receta.delete(getContext());
                        AdaptadorDeLista.this.remove(receta);
                        Toast.makeText(getContext(), "Receta eliminada", Toast.LENGTH_LONG).show();
                    }
                });

                dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                dialog.show();
            }
        });

        lip.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("peliculaID", receta.getId() + "");
                Navigation.findNavController(parent).navigate(R.id.action_nav_listar_to_nav_crear, bundle);
            }
        });


        return convertView;


    }
}
