package laurentesp.list_exercice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import laurentesp.list_exercice.dataBase.PhotoPersistenceManager;
import laurentesp.list_exercice.dataBase.PhotoTable;
import laurentesp.list_exercice.dataBase.PhotoType;
import laurentesp.list_exercice.flickr.model.PhotoSimple;

public class PhotoListPerstActivity extends AppCompatActivity {

    private List<PhotoTable> photoListFromPerst;
    private List<PhotoSimple> photosList;

    private PhotoPersistenceManager photoPersistenceManager;
    private static ListAdapter myAdapter;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_list_perst);

        ListView listView = (ListView) findViewById(R.id.list_persist);

        intent = getIntent();
        String listTypeToGet = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        photoPersistenceManager = new PhotoPersistenceManager();
        photoPersistenceManager.PhotoPersistenceManager(this);
        photosList = new ArrayList<PhotoSimple>();

        if (listTypeToGet.equals(PhotoType.FAVORITE)) {
            photosList = photoPersistenceManager.getPhotoFromFavorites();
        } else {
            photosList = photoPersistenceManager.getPhotoFromHistoric();
        }

        myAdapter = new ListAdapter(this);
        myAdapter.setMyList(photosList);
        listView.setAdapter(myAdapter);

    }
}
