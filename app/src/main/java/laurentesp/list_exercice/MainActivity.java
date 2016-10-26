package laurentesp.list_exercice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.raizlabs.android.dbflow.config.FlowLog;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;
import org.honorato.multistatetogglebutton.ToggleButton;

import laurentesp.list_exercice.dataBase.PhotoType;
import laurentesp.list_exercice.flickr.model.PhotoSimple;

public class MainActivity extends AppCompatActivity implements ListPhotosFragment.OnHeadlineSelectedListener {
    static String mCurrentUrl = "";
    static String mCurrentTitle = "";
    public final static String EXTRA_MESSAGE = "laurentesp.list_exercice.MESSAGE";

    private PhotoPersistenceJob photoPersistenceJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photoPersistenceJob = new PhotoPersistenceJob(getBaseContext());
        FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);
    }

    // On click callback on Photo List
    @Override
    public void onPhotoSelected(String photoTitle, String photoUrl, PhotoSimple photoSimple) {
        // The user selected a photo from the photo list

        // Save in persistence the photo details
        photoPersistenceJob.savePhoto(photoUrl, photoTitle, PhotoType.HISTORIC,photoSimple);

        // Capture the photo detail fragment from the activity layout
        PhotoDetailsFragment photoDetailsFragment = (PhotoDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.frag_detail);

        if ((photoDetailsFragment != null) && (photoDetailsFragment.isInLayout())) {
            // If article frag is available, we're in two-pane layout...
            // Call a method in the ArticleFragment to update its content
            Intent intent = new Intent(MainActivity.this, PhotoDetailsFragment.class);
            String[] message = {photoTitle, photoUrl};
            intent.putExtra(EXTRA_MESSAGE, message);

            photoDetailsFragment.updatePhotoView(photoTitle, photoUrl);

        } else {
            // If the frag is not available, we're in the one-pane layout and must swap frags...
            // Create fragment and give it an argument for the selected article

            Intent intent = new Intent(MainActivity.this, ActivityPhotoDetail.class);
            String[] message = {photoTitle, photoUrl};
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }
    }



}
