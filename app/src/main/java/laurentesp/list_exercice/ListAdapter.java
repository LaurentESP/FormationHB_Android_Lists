package laurentesp.list_exercice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by SOEOSSA on 17/10/2016.
 */

public class ListAdapter extends BaseAdapter {
    private Context context;
    private List<PhotoSimple> myList;

    public ListAdapter(Context context) {
        this.context = context;
    }

    public void setMyList(List<PhotoSimple> myList) {
        this.myList = myList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Object getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false);
        }

        TextView myTextView = (TextView) convertView.findViewById(R.id.text_row);
        myTextView.setText(myList.get(position).getTitle());

        ImageView myImageView = (ImageView) convertView.findViewById(R.id.img);
        Picasso.with(context) .load(myList.get(position).getUrl())
                .resize(200, 200) .centerCrop()
                .into(myImageView);

        return convertView;
    }
}
