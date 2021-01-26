package xyz.kainotomia.examenu3.ui.listar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.List;

import xyz.kainotomia.examenu3.R;
import xyz.kainotomia.examenu3.databinding.FragmentListarBinding;
import xyz.kainotomia.examenu3.model.Receta;

public class ListarFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    FragmentListarBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentListarBinding.inflate(inflater, container, false);

        /**
         * Create spinner
         */
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.estado_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spActivo.setAdapter(adapter);
        binding.spActivo.setOnItemSelectedListener(this);

        return binding.getRoot();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        List<Receta> recetas;
        if (i == 0) {
            recetas = Receta.activas(this.getContext());
        } else {
            recetas = Receta.inactivas(this.getContext());
        }

        binding.list.setAdapter(new AdaptadorDeLista(ListarFragment.this.getContext(), recetas));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}