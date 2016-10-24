package laurentesp.list_exercice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;
import org.honorato.multistatetogglebutton.ToggleButton;

public class MainActivity extends AppCompatActivity implements ListPhotosFragment.OnHeadlineSelectedListener{
    static String mCurrentUrl = "";
    static String mCurrentTitle = "";
    public final static String EXTRA_MESSAGE = "laurentesp.list_exercice.MESSAGE";
    private final String TAG = "Toggle_Button";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MultiStateToggleButton buttonToggle = (MultiStateToggleButton) this.findViewById(R.id.mstb_multi_id);
        buttonToggle.setOnValueChangedListener(new ToggleButton.OnValueChangedListener()
        {
            @Override
            public void onValueChanged(int position) {
                Log.d(TAG, "Position: " + position);
            }
        });
    }



    public void onPhotoSelected(String photoTitle, String photoUrl) {
        // The user selected a photo from the photo list

        // Capture the photo detail fragment from the activity layout
        PhotoDetailsFragment photoDetailsFragment = (PhotoDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.frag_detail);
        
        if ((photoDetailsFragment != null) && (photoDetailsFragment.isInLayout())) {
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
        }
    }

}
