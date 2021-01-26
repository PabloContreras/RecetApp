package xyz.kainotomia.examenu3.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import xyz.kainotomia.examenu3.model.Receta;

@Dao
public interface RecetaDao {

    @Query("SELECT COUNT(*) FROM Recetas")
    int count();

    @Query("SELECT * FROM Recetas")
    List<Receta> getAll();

    @Query("SELECT * FROM Recetas WHERE ACTIVO = 1")
    List<Receta> activas();

    @Query("SELECT * FROM Recetas WHERE ACTIVO = 0")
    List<Receta> inactivas();

    @Query("SELECT * FROM Recetas WHERE ID = :id")
    Receta findById(int id);

    @Insert
    void insert(Receta receta);

    @Delete
    void delete(Receta receta);

    @Update
    void update(Receta receta);

}
