package xyz.kainotomia.examenu3.model;

import android.content.Context;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

import xyz.kainotomia.examenu3.database.AppDatabase;

@Entity(tableName = "recetas")
public class Receta {


    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "posterPath")
    private String posterPath;

    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "fechaLanzamiento")
    private String fechaLanzamiento;
    @ColumnInfo(name = "director")
    private String director;
    @ColumnInfo(name = "genero")
    private String genero;
    @ColumnInfo(name = "descripcion")
    private String descripcion;

    @ColumnInfo(name = "activo")
    private boolean activo;

    public Receta(String posterPath, String nombre, String fechaLanzamiento, String director,
                  String genero, String descripcion) {
        this.posterPath = posterPath;
        this.nombre = nombre;
        this.fechaLanzamiento = fechaLanzamiento;
        this.director = director;
        this.genero = genero;
        this.descripcion = descripcion;
    }
    public Receta(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }


    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setActivoX(String activo) {
        this.activo = activo.equals("Activo");
    }

    /**
     * Save or update operation
     */
    public void save(Context context) {

        if (this.id == 0) {

            AppDatabase.getInstance(context).peliculaDao().insert(this);

        } else {

            AppDatabase.getInstance(context).peliculaDao().update(this);

        }

    }

    public void delete(Context context) {
        AppDatabase.getInstance(context).peliculaDao().delete(this);
    }

    public static List<Receta> getAll(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);

        if (db.peliculaDao().count() != 0) {
            return db.peliculaDao().getAll();
        }

        return new ArrayList<>();
    }

    public static List<Receta> activas(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);

        if (db.peliculaDao().count() != 0) {
            return db.peliculaDao().activas();
        }

        return new ArrayList<>();
    }

    public static List<Receta> inactivas(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);

        if (db.peliculaDao().count() != 0) {
            return db.peliculaDao().inactivas();
        }

        return new ArrayList<>();
    }

    public static Receta findById(Context context, int id) {
        return AppDatabase.getInstance(context).peliculaDao().findById(id);
    }


    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", posterPath='" + posterPath + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaLanzamiento='" + fechaLanzamiento + '\'' +
                ", director='" + director + '\'' +
                ", genero='" + genero + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
