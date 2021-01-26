package xyz.kainotomia.examenu3.model;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

import xyz.kainotomia.examenu3.database.AppDatabase;

@Entity(tableName = "pacientes")
public class Paciente {

    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "uuid")
    public String uuid;


    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "genero")
    private String genero;
    @ColumnInfo(name = "area")
    private String area;
    @ColumnInfo(name = "doctor")
    private String doctor;
    @ColumnInfo(name = "fecha_ingreso")
    private String fechaIngreso;
    @ColumnInfo(name = "photo_path")
    @Nullable
    private String photoPath;
    @ColumnInfo(name = "edad")
    private int edad;
    @ColumnInfo(name = "estatura")
    private float estatura;
    @ColumnInfo(name = "peso")
    private float peso;

    public Paciente(String photoPath, String nombre, String genero, String area, String doctor, String fechaIngreso, int edad, float estatura, float peso) {
        this.photoPath = photoPath;
        this.nombre = nombre;
        this.genero = genero;
        this.area = area;
        this.doctor = doctor;
        this.fechaIngreso = fechaIngreso;
        this.edad = edad;
        this.estatura = estatura;
        this.peso = peso;
    }

    public Paciente() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getEstatura() {
        return estatura;
    }

    public void setEstatura(float estatura) {
        this.estatura = estatura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public boolean isValid() {
        return nombre != null && area != null && doctor != null && genero != null && edad != 0 && estatura != 0 && peso != 0;
    }


    public void insert(Context context) {
        AppDatabase.getInstance(context).pacienteDao().insert(this);
    }

    public void delete(Context context) {
        AppDatabase.getInstance(context).pacienteDao().delete(this);
    }

    public void update(Context context) {
        AppDatabase.getInstance(context).pacienteDao().update(this);
    }


    public static List<Paciente> getAll(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);

        if (db.pacienteDao().count() != 0) {
            return db.pacienteDao().getAll();
        }

        return new ArrayList<>();
    }


    public static Paciente findById(Context context, int id) {
        return AppDatabase.getInstance(context).pacienteDao().findById(id);
    }
}
