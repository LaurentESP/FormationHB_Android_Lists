package laurentesp.list_exercice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import laurentesp.list_exercice.flickr.business.FlickrInterface;
import laurentesp.list_exercice.flickr.photo.FlickrPhotoResponse;
import laurentesp.list_exercice.flickr.business.OnFlickrResponseListener;
import laurentesp.list_exercice.flickr.photo.Photo;
import laurentesp.list_exercice.flickr.photo.PhotoSimple;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SOEOSSA on 17/10/2016.
 */

public class WebCallService extends Service {
    private static final String URL = "https://www.flickr.com/";
    private Context context;
    private IBinder binder = new ServiceBinder();

    private FlickrInterface flickrServiceInterface;
    private Retrofit retrofit;

    private ArrayList<PhotoSimple> photoSimpleArray;
    private OnFlickrResponseListener interfaceForResponseListener;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setInterfaceForResponseListener(OnFlickrResponseListener interfaceForResponseListener) {
        this.interfaceForResponseListener = interfaceForResponseListener;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        retrofit = new Retrofit.Builder().baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        flickrServiceInterface = retrofit.create(FlickrInterface.class);
        photoSimpleArray = new ArrayList<PhotoSimple>();
        return binder;
    }

    public class ServiceBinder extends Binder {
        WebCallService getService() {
            return WebCallService.this;
        }
    }

    public void showToast() {
        Toast.makeText(context, "Hello Toast works", Toast.LENGTH_LONG).show();
    }

    public void getFlickrPhotos(String queryFlickr) {
        String apiKey = getResources().getString(R.string.api_flickr_key);
        Call<FlickrPhotoResponse> flickrPhotosResponseCall = flickrServiceInterface.getPhotos(queryFlickr, apiKey);
        flickrPhotosResponseCall.enqueue(new Callback<FlickrPhotoResponse>() {
                                             @Override
                                             public void onResponse(Call<FlickrPhotoResponse> call, Response<FlickrPhotoResponse> response) {
                                                 Log.d("Response on call", call.toString());
                                                 Log.d("response", response.body().toString());


                                                 if (response.isSuccessful()) {
                                                     Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();

                                                     ArrayList<Photo> listPhotos = response.body().getPhotos().getPhoto();
                                                     ArrayList<PhotoSimple> listPhotoSimple = new ArrayList<PhotoSimple>();

                                                     for (int i=0;i<listPhotos.size();i++){
                                                         Photo photoI = listPhotos.get(i);
                                                         String title = photoI.getTitle();
                                                         String farm = photoI.getFarm();
                                                         String server = photoI.getServer();
                                                         String id = photoI.getId();
                                                         String secret = photoI.getSecret();
                                                         PhotoSimple newPhotoSimple = new PhotoSimple(title,farm,server,id,secret);
                                                         listPhotoSimple.add(newPhotoSimple);
                                                     }
                                                     interfaceForResponseListener.onFlickrResponse(listPhotoSimple);
                                                 } else {
                                                     Toast.makeText(context, "Not successful", Toast.LENGTH_LONG).show();
                                                 }
                                             }

                                             @Override
                                             public void onFailure(Call<FlickrPhotoResponse> call, Throwable t) {
                                                 Log.e("Failure on Call", call.request().toString());
                                                 Log.e("Exception", t.toString());
                                                 Toast.makeText(context, "Failure on Response", Toast.LENGTH_LONG).show();
                                             }
                                         }
        );


    }

    public void createListSimplePhotos(Response<FlickrPhotoResponse> response){

    }

}
