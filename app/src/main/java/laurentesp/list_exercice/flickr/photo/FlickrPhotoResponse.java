package laurentesp.list_exercice.flickr.photo;

import laurentesp.list_exercice.flickr.photo.Photos;

/**
 * Created by SOEOSSA on 17/10/2016.
 */

public class FlickrPhotoResponse
{
    private Photos photos;

    private String stat;

    public Photos getPhotos ()
    {
        return photos;
    }

    public void setPhotos (Photos photos)
    {
        this.photos = photos;
    }

    public String getStat ()
    {
        return stat;
    }

    public void setStat (String stat)
    {
        this.stat = stat;
    }

    @Override
    public String toString()
    {
        return "ClassPhoto [photos = "+photos+", stat = "+stat+"]";
    }
}
