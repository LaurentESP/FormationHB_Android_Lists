package laurentesp.list_exercice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PhotoDetailsFragment extends Fragment implements PhotoDetailsInterface {
    final static String ARG_URL = "url";
    final static String ARG_TITLE = "title";

    int mCurrentPosition = -1;

    private TextView txtVwPhotoTitle;
    private ImageView imgVw;

    private PhotoDetailFragmentAttachInterface photoDetailFragmentAttachInterface;

    private String photoTitle = "";
    private String photoUrl = "";


    public PhotoDetailsFragment() {
        // Required empty public constructor
    }

    public static PhotoDetailsFragment newInstance(String param1, String param2) {
        PhotoDetailsFragment fragment = new PhotoDetailsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photo_details, container, false);
        txtVwPhotoTitle = (TextView) view.findViewById(R.id.txt_vw_photo_detail_title);
        imgVw = (ImageView) view.findViewById(R.id.img_vw_detail);

        Intent intent = getActivity().getIntent();

        String[] message = intent.getStringArrayExtra(MainActivity.EXTRA_MESSAGE);
        if (message != null) {
            photoTitle = message[0];
            photoUrl = message[1];
        }

        if (savedInstanceState != null) {
            photoTitle = savedInstanceState.getString(ARG_TITLE);
            photoUrl = savedInstanceState.getString(ARG_URL);
            updatePhotoView(photoTitle, photoUrl);
        }

        return view;
    }

    public void onPhotoClickListener(String url, String title) {
        //Save the photo in persistence

        //Display the title of the photo
        txtVwPhotoTitle.setText(title);
        // Display the photo
        Picasso.with(getContext()).load(url)
                .resize(800, 800).centerCrop()
                .into(imgVw);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updatePhotoView(args.getString(ARG_TITLE), args.getString(ARG_URL));

        } else if (!(photoUrl.isEmpty())) {
            // Set article based on saved instance state defined during onCreateView
            updatePhotoView(photoTitle, photoUrl);
        }
    }

    public void updatePhotoView(String photoTitle, String photoUrl) {

        txtVwPhotoTitle.setText(photoTitle);
        if (!(photoUrl.isEmpty())) {
            Picasso.with(getContext()).load(photoUrl)
                    .resize(800, 800).centerCrop()
                    .into(imgVw);
        }

        this.photoUrl = photoUrl;
        this.photoTitle = photoTitle;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Save the current article selection in case we need to recreate the fragment
        super.onSaveInstanceState(outState);
        outState.putString(ARG_URL, photoUrl);
        outState.putString(ARG_TITLE, photoTitle);

    }
}
