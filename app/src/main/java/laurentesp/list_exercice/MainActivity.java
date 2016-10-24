package laurentesp.list_exercice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import laurentesp.list_exercice.flickr.business.OnFlickrResponseListener;
import laurentesp.list_exercice.flickr.photo.PhotoSimple;

public class MainActivity extends AppCompatActivity implements ListPhotosFragment.OnHeadlineSelectedListener{
    static String mCurrentUrl = "";
    static String mCurrentTitle = "";
    public final static String EXTRA_MESSAGE = "laurentesp.list_exercice.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onPhotoSelected(String photoTitle, String photoUrl) {
        // The user selected a photo from the photo list

        // Capture the photo detail fragment from the activity layout
        PhotoDetailsFragment photoDetailsFragment = (PhotoDetailsFragment)
                getSupportFragmentManager().findFragmentById(R.id.frag_detail);

        if (photoDetailsFragment != null) {
            // If article frag is available, we're in two-pane layout...
            // Call a method in the ArticleFragment to update its content
            Intent intent = new Intent(MainActivity.this, PhotoDetailsFragment.class);
            String[] message = {photoTitle,photoUrl};
            intent.putExtra(EXTRA_MESSAGE,message);

            photoDetailsFragment.updatePhotoView(photoTitle,photoUrl);

        } else {
            // If the frag is not available, we're in the one-pane layout and must swap frags...
            // Create fragment and give it an argument for the selected article

            Intent intent = new Intent(MainActivity.this, ActivityPhotoDetail.class);
            String[] message = {photoTitle,photoUrl};
            intent.putExtra(EXTRA_MESSAGE,message);
            startActivity(intent);
            /*
            PhotoDetailsFragment newFragment = new PhotoDetailsFragment();
            Bundle args = new Bundle();
            args.putString(PhotoDetailsFragment.ARG_TITLE, photoTitle);
            args.putString(PhotoDetailsFragment.ARG_URL, photoUrl);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.frag_container, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit(); */
        }
    }

}
