package laurentesp.list_exercice.dataBase;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by SOEOSSA on 24/10/2016.
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION, foreignKeysSupported = true)
public class AppDatabase {
    public static final String NAME = "PhotoBase";
    public static final int VERSION = 1;
}
