package laurentesp.list_exercice.dataBase;

import android.content.Context;
import android.util.Log;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by SOEOSSA on 24/10/2016.
 */

public class PhotoPersistenceManager {

    public void PhotoPersistenceManager(Context context) {
        FlowManager.init(new FlowConfig.Builder(context).openDatabasesOnInit(true).build());
    }

    public List<PhotoTable> getPhotoByTitle(String title) {
        return SQLite.select()
                .from(PhotoTable.class)
                .where(PhotoTable_Table.photoName.like(title + "%"))
                .or(PhotoTable_Table.photoName.like("%" + title + "%"))
                .queryList();
    }

    public List<PhotoTable> getPhotoByUrl(String url) {
        return SQLite.select()
                .from(PhotoTable.class)
                .where(PhotoTable_Table.photoUrl.like(url + "%"))
                .or(PhotoTable_Table.photoUrl.like("%" + url + "%"))
                .queryList();
    }

    public List<PhotoTable> getPhotoFromHistoric() {
        return SQLite.select()
                .from(PhotoTable.class)
                .where(PhotoTable_Table.photoType.is(PhotoType.HISTORIC))
                .queryList();
    }

    public List<PhotoTable> getPhotoFromFavorites() {
        return SQLite.select()
                .from(PhotoTable.class)
                .where(PhotoTable_Table.photoType.is(PhotoType.FAVORITE))
                .queryList();
    }

    public void removePhoto(String url){
        SQLite.delete()
                .from(PhotoTable.class)
                .where(PhotoTable_Table.photoUrl.like(url));
    }

    public void save(PhotoTable photoTable) {
        try {
            photoTable.save();
            Log.w("SavePhotoTable", photoTable.toString());
        } catch (Exception e) {
            Log.w("SavePhotoTable", e.toString());
        }
    }

}

