package laurentesp.list_exercice;

import android.content.Context;

import java.util.List;

import laurentesp.list_exercice.dataBase.PhotoPersistenceManager;
import laurentesp.list_exercice.dataBase.PhotoTable;
import laurentesp.list_exercice.dataBase.PhotoType;

/**
 * Created by SOEOSSA on 24/10/2016.
 */

public class PhotoPersistenceJob {
    private PhotoPersistenceManager photoPersistenceManager;
    private PhotoTable photoTable;

    public PhotoPersistenceJob(Context context) {
        photoPersistenceManager = new PhotoPersistenceManager();
        photoPersistenceManager.PhotoPersistenceManager(context);
        photoTable = new PhotoTable();
    }

    public void savePhoto(String url, String title) {
        photoTable.setPhotoUrl(url);
        photoTable.setPhotoName(title);
        photoPersistenceManager.save(photoTable);
    }

    public List<PhotoTable> getPhoto(String histOrFav) {
        if (histOrFav.equals(PhotoType.FAVORITE)) {
            return photoPersistenceManager.getPhotoFromFavorites();
        } else {
            return photoPersistenceManager.getPhotoFromHistoric();
        }
    }
}
