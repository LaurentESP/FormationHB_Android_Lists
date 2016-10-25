package laurentesp.list_exercice.flickr.business;

import java.util.ArrayList;

import laurentesp.list_exercice.flickr.model.PhotoSimple;

/**
 * Created by SOEOSSA on 17/10/2016.
 */

public interface OnFlickrResponseListener {
    public void onFlickrResponse(ArrayList<PhotoSimple> listPhotoSimple);
}
