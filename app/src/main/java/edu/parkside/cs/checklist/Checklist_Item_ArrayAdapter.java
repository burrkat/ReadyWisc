package edu.parkside.cs.checklist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.kiflebk.readywisc.R;

import java.util.ArrayList;

/**
 * Created by krawchukd on 2/15/15.
 */
public class Checklist_Item_ArrayAdapter extends ArrayAdapter<Checklist_Item_Row> {

    ArrayList<Checklist_Item_Row> checklist_item_rowArrayList;
    Context context;
    int resourceID;

    public Checklist_Item_ArrayAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resourceID = resource;
    }

    public Checklist_Item_ArrayAdapter(Context context, int resource, ArrayList<Checklist_Item_Row> objects) {
        super(context, resource, objects);
        this.checklist_item_rowArrayList = objects;
        this.context = context;
        this.resourceID = resource;
    }

    public void setList(ArrayList<Checklist_Item_Row> arrayList)
    {
        this.checklist_item_rowArrayList = arrayList;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.activity_checklist_item_listview_row, parent, false);

        TextView itemName = (TextView)rowView.findViewById(R.id.checklist_item_listview_row_textview);
        final CheckBox checkBox = (CheckBox)rowView.findViewById(R.id.checklist_item_listview_row_checkbox);

        itemName.setText(this.checklist_item_rowArrayList.get(position).getName());
        checkBox.setChecked(this.checklist_item_rowArrayList.get(position).isChecked());

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checklist_item_rowArrayList.get(position).setChecked();
            }
        });

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checklist_item_rowArrayList.get(position).getName().contains("Add Item")){
                    Intent intent = new Intent(context, Checklist_Item_Create.class);
                    intent.putExtra(Checklist_Item_ListView.EXTRA_MESSAGE,checklist_item_rowArrayList.get(position));
                    context.startActivity(intent);
                }
                else {
                    Intent intent = new Intent(context, Checklist_Item_Detail.class);
                    intent.putExtra(Checklist_Item_ListView.EXTRA_MESSAGE, checklist_item_rowArrayList.get(position));
                    context.startActivity(intent);
                }

            }
        });

        return rowView;
    }
}
