package xyz.kainotomia.examenu3;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.kainotomia.examenu3.databinding.FragmentBuscarBinding;
import xyz.kainotomia.examenu3.model.Receta;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuscarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuscarFragment extends Fragment {

    FragmentBuscarBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BuscarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuscarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuscarFragment newInstance(String param1, String param2) {
        BuscarFragment fragment = new BuscarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentBuscarBinding.inflate(inflater, container, false);


        binding.btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.etIdPelicula.getText().toString().equals("")) {
                    return;
                }

                Receta receta = Receta.findById(getContext(), Integer.parseInt(binding.etIdPelicula.getText().toString()));

                if (receta != null) {
                    binding.tvNombre.setText(receta.getNombre());
                    binding.tvGenero.setText(binding.tvGenero.getText().toString().replace(":tipo", receta.getGenero()));
                    binding.tvFecha.setText(binding.tvFecha.getText().toString().replace(":fecha", receta.getFechaLanzamiento()));
                    binding.tvDirector.setText(binding.tvDirector.getText().toString().replace(":ingredientes", receta.getDirector()));
                    binding.tvDesc.setText(receta.getDescripcion());
                    binding.tvActivo.setText(receta.isActivo() ? "Activo" : "Inactivo");
                    binding.imgPoster.setImageURI(Uri.parse(receta.getPosterPath()));
                    //binding.tvId.setText(receta.getId() + "");
                }

            }
        });


        return binding.getRoot();
    }
}