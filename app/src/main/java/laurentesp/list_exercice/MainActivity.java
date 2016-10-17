package laurentesp.list_exercice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private List<String> listStudent;
    private List<String> listTrainer;
    private List<String> listToDisplay;
    private ListAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listStudent = new ArrayList<String>();
        listStudent.add("Jerome");
        listStudent.add("Mourad");
        listStudent.add("Nicolas");
        listStudent.add("Gilles");

        listTrainer = new ArrayList<String>();
        listTrainer.add("Xavier");
        listTrainer.add("Francois");
        listTrainer.add("Carole");
        listTrainer.add("Darren");

        listToDisplay=new ArrayList<String>();

        ListView listView = (ListView) findViewById(R.id.list);

        myAdapter = new ListAdapter(this);
        myAdapter.setMyList(listStudent);
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.row_layout, R.id.text_row, listStudent);
        listView.setAdapter(myAdapter);

        Button buttonListChange = (Button) findViewById(R.id.button_0);
        buttonListChange.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_0:
                if (listToDisplay.equals(listStudent)){
                    listToDisplay = listTrainer;
                } else {
                    listToDisplay = listStudent;
                }
                myAdapter.setMyList(listToDisplay);
                break;
            default:
                myAdapter.setMyList(listToDisplay);
        }
    }
}
