package laurentesp.list_exercice.dataBase;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by SOEOSSA on 24/10/2016.
 */
@Table(database = AppDatabase.class)
public class PhotoTable extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    private long photoId;

    @Column
    private String photoName;

    @Column
    private String photoUrl;

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

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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

    public long getPhotoId() {
        return photoId;

    }

    public void setPhotoId(long photoId) {
        this.photoId = photoId;
    }

}
