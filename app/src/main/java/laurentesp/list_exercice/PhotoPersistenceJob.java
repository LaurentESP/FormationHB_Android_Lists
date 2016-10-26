package laurentesp.list_exercice;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import laurentesp.list_exercice.dataBase.PhotoPersistenceManager;
import laurentesp.list_exercice.dataBase.PhotoType;
import laurentesp.list_exercice.flickr.model.PhotoSimple;

/**
 * Created by SOEOSSA on 24/10/2016.
 */

public class PhotoPersistenceJob {
    private PhotoPersistenceManager photoPersistenceManager;
    private PhotoSimple photoTable;

    public PhotoPersistenceJob(Context context) {
        photoPersistenceManager = new PhotoPersistenceManager();
        photoPersistenceManager.PhotoPersistenceManager(context);
        photoTable = new PhotoSimple();
    }

    public void savePhoto(String url, String title, PhotoType photoType, PhotoSimple photoSimple) {
        List<PhotoSimple> photoInDataBase = new ArrayList<>();
        photoInDataBase = photoPersistenceManager.getPhotoByUrl(url);
        if (photoInDataBase.isEmpty()) {
            photoTable = new PhotoSimple();
        } else {
            photoTable = photoInDataBase.get(0);
        }
        photoTable.setUrl(photoSimple.getUrl());
        photoTable.setTitle(photoSimple.getTitle());
        photoTable.setPhotoType(photoType);
        photoTable.setPhotoLat(photoSimple.getPhotoLat());
        photoTable.setPhotoLong(photoSimple.getPhotoLong());
        photoPersistenceManager.save(photoTable);
    }

    public List<PhotoSimple> getPhoto(String histOrFav) {
        if (histOrFav.equals(PhotoType.FAVORITE)) {
            return photoPersistenceManager.getPhotoFromFavorites();
        } else {
            return photoPersistenceManager.getPhotoFromHistoric();
        }
    }
}
