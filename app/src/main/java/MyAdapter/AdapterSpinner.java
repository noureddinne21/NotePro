package MyAdapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.nouroeddinne.notepro.R;
import java.util.ArrayList;
import Model.ModelSpinner;
public class AdapterSpinner extends BaseAdapter implements SpinnerAdapter {

    ArrayList<ModelSpinner> list;
    Context context;

    public AdapterSpinner(Context context,ArrayList<ModelSpinner> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_custom_spinner, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.textView);
        ModelSpinner item = (ModelSpinner) getItem(position);

        if (item != null) {
            textViewName.setText(item.getText());
        }
        return convertView;
    }


















}
