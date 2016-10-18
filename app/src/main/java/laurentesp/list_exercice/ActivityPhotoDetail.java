package laurentesp.list_exercice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ActivityPhotoDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);

        TextView txtVwPhotoTitle = (TextView) findViewById(R.id.txt_vw_photo_detail_title);
        ImageView imgVw = (ImageView) findViewById(R.id.img_vw_detail);

        Intent intent = getIntent();
        String[] message = intent.getStringArrayExtra(MainActivity.EXTRA_MESSAGE);

        txtVwPhotoTitle.setText(message[0]);
        Picasso.with(getBaseContext()) .load(message[1])
                .resize(500, 500) .centerCrop()
                .into(imgVw);
    }
}
