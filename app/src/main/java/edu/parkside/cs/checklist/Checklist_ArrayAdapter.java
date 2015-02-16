package edu.parkside.cs.checklist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kiflebk.readywisc.R;

import java.util.ArrayList;

/**
 * Created by krawchukd on 2/15/15.
 */
public class Checklist_ArrayAdapter extends ArrayAdapter {

    Context context;
    ArrayList<Checklist_Row> list;

    public Checklist_ArrayAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    public Checklist_ArrayAdapter(Context context, int resource, ArrayList<Checklist_Row> objects) {
        super(context, resource, objects);
        this.context = context;
        this.list = objects;
    }

    public void setList(ArrayList<Checklist_Row> list){
        this.list = list;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.activity_checklist_row, parent, false);

        TextView title = (TextView)rowView.findViewById(R.id.activity_checklist_row_textview);
        ProgressBar progressBar = (ProgressBar)rowView.findViewById(R.id.activity_checklist_row_progressbar);

        title.setText(this.list.get(position).getTitle());
        progressBar.setProgress(this.list.get(position).getProgress());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Checklist_Item_ListView.class);
                intent.putExtra(Checklist.EXTRA_MESSAGE, list.get(position));
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
