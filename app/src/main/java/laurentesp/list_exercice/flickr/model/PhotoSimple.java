package laurentesp.list_exercice.flickr.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import laurentesp.list_exercice.dataBase.AppDatabase;
import laurentesp.list_exercice.dataBase.PhotoType;

/**
 * Created by SOEOSSA on 17/10/2016.
 */
@Table(database = AppDatabase.class)
public class PhotoSimple extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    private long photoId;

    @Column
    private String url;

    @Column
    private String title;

    @Column
    private PhotoType photoType;

    @Column
    private long photoCount;

    @Column
    private String photoSearch;

    @Column
    private long photoLat;

    @Column
    private long photoLong;

    public long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(long photoId) {
        this.photoId = photoId;
    }

    public PhotoType getPhotoType() {
        return photoType;
    }

    public void setPhotoType(PhotoType photoType) {
        this.photoType = photoType;
    }

    public long getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(long photoCount) {
        this.photoCount = photoCount;
    }

    public String getPhotoSearch() {
        return photoSearch;
    }

    public void setPhotoSearch(String photoSearch) {
        this.photoSearch = photoSearch;
    }

    public long getPhotoLat() {
        return photoLat;
    }

    public void setPhotoLat(long photoLat) {
        this.photoLat = photoLat;
    }

    public long getPhotoLong() {
        return photoLong;
    }

    public void setPhotoLong(long photoLong) {
        this.photoLong = photoLong;
    }

    public PhotoSimple(String title, String farm, String server, String id, String secret) {
        this.title = title;
        setUrl(farm, server, id, secret);
    }

    public PhotoSimple(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public PhotoSimple() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrl(String farm, String server, String id, String secret) {
        url = "https://farm" + farm + ".static.flickr.com/" + server + "/" + id + "_" + secret + ".jpg";
    }

}
