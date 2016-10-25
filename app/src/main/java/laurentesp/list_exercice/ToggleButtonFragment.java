package laurentesp.list_exercice;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;
import org.honorato.multistatetogglebutton.ToggleButton;


public class ToggleButtonFragment extends Fragment implements ToggleButton.OnValueChangedListener{
    public final static String EXTRA_MESSAGE= "laurentesp.list_exercice.MESSAGE";
    private final String TAG = "Toggle_Button";

    public ToggleButtonFragment() {
        // Required empty public constructor
    }

    public static ToggleButtonFragment newInstance(String param1, String param2) {
        ToggleButtonFragment fragment = new ToggleButtonFragment();
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
        View view = inflater.inflate(R.layout.fragment_toggle_button, container, false);
        MultiStateToggleButton buttonToggle = (MultiStateToggleButton) view.findViewById(R.id.mstb_multi_id);
        //buttonToggle.setElements(R.array.actions_array, 0);
        buttonToggle.setOnValueChangedListener(this);
        return view;
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
    public void onValueChanged(int position) {

        Log.d(TAG, "Position: " + position);
        Intent intent;
        switch (position) {
            case 0:
                intent = new Intent(getContext(),MainActivity.class);
                break;
            case 1:
                intent = new Intent(getContext(),PhotoListPerstActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "HISTORIC");
                break;
            case 2:
                intent = new Intent(getContext(),PhotoListPerstActivity.class);
                intent.putExtra(EXTRA_MESSAGE,"FAVORITE");
                break;
            default:
                intent = new Intent(getContext(),MainActivity.class);
        }
        startActivity(intent);
    }
}
