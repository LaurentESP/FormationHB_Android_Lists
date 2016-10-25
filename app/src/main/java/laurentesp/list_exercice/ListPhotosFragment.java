package laurentesp.list_exercice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import laurentesp.list_exercice.flickr.business.OnFlickrResponseListener;
import laurentesp.list_exercice.flickr.model.PhotoSimple;


public class ListPhotosFragment extends ListFragment implements View.OnClickListener, OnFlickrResponseListener {

    public final static String EXTRA_MESSAGE = "laurentesp.list_exercice.MESSAGE";

    private static ListAdapter myAdapter;

    private WebCallService toastService;
    private Boolean bound;
    private static ArrayList<PhotoSimple> listPhoto;

    private EditText editText;
    private ListView listView;

    OnHeadlineSelectedListener mCallback;

    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnHeadlineSelectedListener {
        /**
         * Called by HeadlinesFragment when a list item is selected
         */
        public void onPhotoSelected(String photoTitle, String photoUrl);
    }

    public ListPhotosFragment() {
        // Required empty public constructor
    }

    public static ListPhotosFragment newInstance(String param1, String param2) {
        ListPhotosFragment fragment = new ListPhotosFragment();
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
        View view = inflater.inflate(R.layout.fragment_list_photos, container, false);
        if (listPhoto == null) {
            listPhoto = new ArrayList<>();
        }

        myAdapter = new ListAdapter(this.getContext());
        myAdapter.setMyList(listPhoto);
        setListAdapter(myAdapter);

        FloatingActionButton buttonListChange = (FloatingActionButton) view.findViewById(R.id.button_0);
        buttonListChange.setOnClickListener(this);

        editText = (EditText) view.findViewById(R.id.edit_query);

        if (listPhoto != null) {
            myAdapter.setMyList(listPhoto);
        }

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        Context context = getContext();
        try {
            mCallback = (OnHeadlineSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(getContext(), WebCallService.class);
        getActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE);
        // When in two-pane layout, set the listview to highlight the selected list item
        // (We do this during onStart because at the point the listview is available.)
        if (getFragmentManager().findFragmentById(R.id.frag_detail) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (bound) {
            getActivity().unbindService(connection);
            bound = false;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            WebCallService.ServiceBinder binder = (WebCallService.ServiceBinder) service;
            toastService = binder.getService();
            toastService.setInterfaceForResponseListener(ListPhotosFragment.this);
            bound = true;
            toastService.setContext(getContext());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound = false;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_0:
                closeSoftKeyboard();
                toastService.getFlickrPhotos(editText.getText().toString());
                break;
            default:
        }
    }

    @Override
    public void onFlickrResponse(ArrayList<PhotoSimple> listPhotoSimple) {
        listPhoto = listPhotoSimple;
        myAdapter.setMyList(listPhoto);
    }

    public void closeSoftKeyboard() {
        // Check if no view has focus:
        View view = this.getView();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Notify the parent activity of selected item
        String titlePhoto = listPhoto.get(position).getTitle();
        String urlPhoto = listPhoto.get(position).getUrl();
        mCallback.onPhotoSelected(titlePhoto, urlPhoto);

        // Set the item as checked to be highlighted when in two-pane layout
        getListView().setItemChecked(position, true);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
