package laurentesp.list_exercice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import laurentesp.list_exercice.flickr.photo.PhotoSimple;

import static android.R.id.message;

public class ActivityPhotoDetail extends AppCompatActivity{
    private TextView txtVwPhotoTitle;
    private ImageView imgVw;
    private PhotoDetailsInterface photoDetailsInterface;

    private String photoUrl;
    private String photoTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);




        /*
        bundle.putString("imgUrl", photoUrl);
        bundle.putString("imgTtl", photoTitle);
        PhotoDetailsFragment fragobj = new PhotoDetailsFragment();
        fragobj.setArguments(bundle);

        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frag_layout, fragobj).commit(); */
    }

    public String getImageUrl(){
        return photoUrl;
    }

    public String getImageTitle(){
        return photoTitle;
    }
}
