package xyz.kainotomia.examenu3.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import xyz.kainotomia.examenu3.model.Paciente;

@Dao
public interface PacienteDao {

    @Query("SELECT COUNT(*) FROM PACIENTES")
    int count();

    @Query("SELECT * FROM PACIENTES")
    List<Paciente> getAll();

    @Query("SELECT * FROM PACIENTES WHERE UID = :id")
    Paciente findById(int id);

    @Insert
    void insert(Paciente paciente);

    @Delete
    void delete(Paciente paciente);

    @Update
    void update(Paciente paciente);

}
