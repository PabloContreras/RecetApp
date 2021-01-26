package xyz.kainotomia.examenu3.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import xyz.kainotomia.examenu3.model.Paciente;
import xyz.kainotomia.examenu3.model.Receta;
import xyz.kainotomia.examenu3.model.dao.PacienteDao;
import xyz.kainotomia.examenu3.model.dao.RecetaDao;

@Database(entities = {Paciente.class, Receta.class}, version = 7)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PacienteDao pacienteDao();

    public abstract RecetaDao peliculaDao();

    public static AppDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "examenU3").allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

}
