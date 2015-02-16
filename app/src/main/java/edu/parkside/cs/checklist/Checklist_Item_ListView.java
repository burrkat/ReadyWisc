package edu.parkside.cs.checklist;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.kiflebk.readywisc.R;

import java.util.ArrayList;

public class Checklist_Item_ListView extends ActionBarActivity {

    Checklist_Item_ArrayAdapter checklist_item_arrayAdapter;
    ListView checklist_item_listView;

    private ListView getChecklist_item_listView(){
        if (checklist_item_listView == null)
        {
            checklist_item_listView = (ListView)findViewById(R.id.activity_checklist_item_listview);
        }
        return checklist_item_listView;
    }

    private Checklist_Item_ArrayAdapter getChecklist_item_arrayAdapter(){
        if (checklist_item_arrayAdapter == null){
            checklist_item_arrayAdapter = new Checklist_Item_ArrayAdapter(this, R.layout.activity_checklist_item_listview_row, new ArrayList<Checklist_Item_Row>());
        }
        return checklist_item_arrayAdapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist_item_listview);

        // Populate the listView with the contents of the Checklist table.
        populateListView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_checklist__item_list_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void populateListView()
    {
        ArrayList<Checklist_Item_Row> rowArrayList = Checklist_Contract_Db_Helper.getDb_helper(this).returnChecklistItemRows(null);
        // Set the adapter to be used by the listView.
        getChecklist_item_listView().setAdapter(getChecklist_item_arrayAdapter());
        getChecklist_item_arrayAdapter().addAll(rowArrayList);
        getChecklist_item_listView().requestLayout();
    }
}
