package laurentesp.list_exercice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnFlickrResponseListener {
    public final static String EXTRA_MESSAGE = "laurentesp.list_exercice.MESSAGE";
    private List<String> listStudent;
    private List<String> listTrainer;
    private List<String> listToDisplay;
    private ListAdapter myAdapter;

    private WebCallService toastService;
    private Boolean bound;
    private ArrayList<PhotoSimple> listPhoto;

    private EditText editText;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        listPhoto = new ArrayList<>();

        myAdapter = new ListAdapter(this);
        myAdapter.setMyList(listPhoto);

        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                String[] message = {listPhoto.get(position).getTitle(), listPhoto.get(position).getUrl()};
                Intent intent = new Intent(MainActivity.this, ActivityPhotoDetail.class);
                intent.putExtra(EXTRA_MESSAGE,message);
                // TODO : create new activity if no fragment B else display in fragment B
                startActivity(intent);
            }
        });

        Button buttonListChange = (Button) findViewById(R.id.button_0);
        buttonListChange.setOnClickListener(this);

        editText = (EditText) findViewById(R.id.edit_query);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, WebCallService.class);
        bindService(intent,connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (bound){
            unbindService(connection);
            bound = false;
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            WebCallService.ServiceBinder binder = (WebCallService.ServiceBinder) service;
            toastService = binder.getService();
            toastService.setInterfaceForResponseListener(MainActivity.this);
            bound = true;
            toastService.setContext(getBaseContext());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound = false;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
