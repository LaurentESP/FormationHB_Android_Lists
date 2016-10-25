package laurentesp.list_exercice.dataBase;

import android.content.Context;
import android.util.Log;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import laurentesp.list_exercice.flickr.model.PhotoSimple;
import laurentesp.list_exercice.flickr.model.PhotoSimple_Table;

/**
 * Created by SOEOSSA on 24/10/2016.
 */

public class PhotoPersistenceManager {

    public void PhotoPersistenceManager(Context context) {
        FlowManager.init(new FlowConfig.Builder(context).openDatabasesOnInit(true).build());
    }

    public List<PhotoSimple> getPhotoByTitle(String title) {
        return SQLite.select()
                .from(PhotoSimple.class)
                .where(PhotoSimple_Table.title.eq(title))
                .queryList();
    }

    public List<PhotoSimple> getPhotoByUrl(String url) {
        return SQLite.select()
                .from(PhotoSimple.class)
                .where(PhotoSimple_Table.url.eq(url))
                .queryList();
    }

    public List<PhotoSimple> getPhotoFromHistoric() {
        return SQLite.select()
                .from(PhotoSimple.class)
                .where(PhotoSimple_Table.photoType.is(PhotoType.HISTORIC))
                .queryList();
    }

    public List<PhotoSimple> getPhotoFromFavorites() {
        return SQLite.select()
                .from(PhotoSimple.class)
                .where(PhotoSimple_Table.photoType.is(PhotoType.FAVORITE))
                .queryList();
    }

    public void removePhoto(String url){
        SQLite.delete()
                .from(PhotoSimple.class)
                .where(PhotoSimple_Table.url.eq(url));
    }

    public long getLasIndex(){
        return SQLite.select()
                .from(PhotoSimple.class)
                .count();
    }

    public void save(PhotoSimple photoTable) {
        try {
            //photoTable.setPhotoId(getLasIndex()+1);
            photoTable.save();
            Log.w("SavePhotoTable", photoTable.toString());
        } catch (Exception e) {
            Log.w("SavePhotoTable", e.toString());
        }
    }

    //TODO : CRUD + 1 (Create Read Update Delete + getAll)

}

