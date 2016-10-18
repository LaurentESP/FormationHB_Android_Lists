package laurentesp.list_exercice;

import android.content.res.Resources;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by SOEOSSA on 17/10/2016.
 */

public interface FlickrInterface {
    @GET("services/rest/?method=flickr.photos.search&safe_search=1&per_page=5&format=json&nojsoncallback=1")
    Call<FlickrPhotoResponse> getPhotos(@Query("tags") String query, @Query("api_key") String apiKey);
}
