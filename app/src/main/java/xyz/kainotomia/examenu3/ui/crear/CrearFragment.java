package xyz.kainotomia.examenu3.ui.crear;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import xyz.kainotomia.examenu3.R;
import xyz.kainotomia.examenu3.databinding.FragmentCrearBinding;
import xyz.kainotomia.examenu3.model.Receta;


public class CrearFragment extends Fragment {


    private FragmentCrearBinding binding;
    private Receta receta;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCrearBinding.inflate(inflater, container, false);


        this.makeSpinners();
        this.addEvents();
        this.resolveContext();

        return binding.getRoot();
    }

    private void resolveContext() {

        if (getArguments() != null) {
            String peliculaID = getArguments().getString("peliculaID");
            if (peliculaID != null) {
                receta = Receta.findById(this.getContext(), Integer.parseInt(peliculaID));
                binding.tvTitulo.setText("Editar Receta");
                chargeInfo();
                return;
            }

        }


        this.receta = new Receta();
    }

    private void chargeInfo() {

        binding.posterPelicula.setImageURI(Uri.parse(receta.getPosterPath()));
        binding.etNombre.setText(receta.getNombre());
        binding.etDirector.setText(receta.getDirector());
        binding.etFechaLanzamiento.setText(receta.getFechaLanzamiento());
        binding.etDescripcion.setText(receta.getDescripcion());

        String[] generos = getResources().getStringArray(R.array.genero_array);
        for (int i = 0; i < generos.length; i++) {
            if (generos[i].equals(receta.getGenero())) {
                binding.spGenero.setSelection(i);
                break;
            }
        }



        String[] activo = getResources().getStringArray(R.array.estado_array);
        for (int i = 0; i < activo.length; i++) {
            String aux = receta.isActivo() ? "Activo" : "Inactivo";
            if (activo[i].equals(aux)) {
                binding.spGenero.setSelection(i);
                break;
            }
        }


    }

    private void addEvents() {


        /**
         * Boton guardar
         */
        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrearFragment.this.guardarPelicula();
            }
        });

        /**
         * Boton calendario
         */
        binding.btFechaLanzamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrearFragment.this.obtenerFecha();
            }
        });

        /**
         * Poster peliícula
         */
        binding.posterPelicula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrearFragment.this.tomarPoster();
            }
        });

    }

    private void makeSpinners() {

        /**
         * Spinner genero
         */
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.genero_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.spGenero.setAdapter(adapter);


        /**
         * Spiner estado
         */
        adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.estado_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spActivo.setAdapter(adapter);
    }

    private void guardarPelicula() {

        String nombre = binding.etNombre.getText().toString();
        String director = binding.etDirector.getText().toString();
        String genero = binding.spGenero.getSelectedItem().toString();
        String fechaLanzamiento = binding.etFechaLanzamiento.getText().toString();
        String descripcion = binding.etDescripcion.getText().toString();
        String activo = binding.spActivo.getSelectedItem().toString();

        if (nombre.equals("") || director.equals("") || genero.equals("") ||
                fechaLanzamiento.equals("") || descripcion.equals("") ||
                activo.equals("") || receta.getPosterPath() == null || receta.getPosterPath().equals("")) {
            Toast.makeText(this.getContext(), "Por favor llene todos los campos", Toast.LENGTH_LONG).show();
            return;
        }


        receta.setNombre(nombre);
        receta.setDirector(director);
        receta.setGenero(genero);
        receta.setFechaLanzamiento(fechaLanzamiento);
        receta.setDescripcion(descripcion);
        receta.setActivoX(activo);

        receta.save(this.getContext());
        clearFields();
        Toast.makeText(getContext(), "Receta guardada correctamente", Toast.LENGTH_LONG).show();

    }

    private void clearFields() {

        binding.etNombre.setText("");
        binding.etDirector.setText("");
        binding.spGenero.setSelection(0);
        binding.etFechaLanzamiento.setText("");
        binding.etDescripcion.setText("");
        binding.spActivo.setSelection(0);
        binding.posterPelicula.setImageResource(R.drawable.ic_menu_camera);

        receta = new Receta();

    }

    private void obtenerFecha() {

        GregorianCalendar calendar = new GregorianCalendar();

        new DatePickerDialog(this.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                binding.etFechaLanzamiento.setText(i2 + "/" + (i1 + 1) + "/" + i);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)).show();
    }

    private void tomarPoster() {

        String[] permisos = new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        int use_camera_permission = ActivityCompat.checkSelfPermission(this.getContext(), permisos[0]);
        int write_external_permission = ActivityCompat.checkSelfPermission(this.getContext(), permisos[1]);


        if (use_camera_permission == PackageManager.PERMISSION_GRANTED && write_external_permission == PackageManager.PERMISSION_GRANTED) {

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            if (intent.resolveActivity(this.getActivity().getPackageManager()) != null) {


                File photoFile = null;

                try {
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    String imageFileName = "JPEG_" + timeStamp + "_";
                    File storageDir = this.getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    File image = File.createTempFile(
                            imageFileName,  /* prefix */
                            ".jpg",         /* suffix */
                            storageDir      /* directory */
                    );
                    receta.setPosterPath(image.getAbsolutePath());
                    photoFile = image;

                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (photoFile != null) {
                    Uri photoUri = FileProvider.getUriForFile(this.getContext(), "com.example.android.fileprovider", photoFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(intent, 100);
                }
            }
        } else {
            requestPermissions(permisos, 105);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100) {
            binding.posterPelicula.setImageBitmap(BitmapFactory.decodeFile(receta.getPosterPath()));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        this.tomarPoster();
    }
}