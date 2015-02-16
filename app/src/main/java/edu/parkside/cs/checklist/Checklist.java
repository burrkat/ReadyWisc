package edu.parkside.cs.checklist;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.kiflebk.readywisc.R;

import java.util.ArrayList;

public class Checklist extends ActionBarActivity {

    Checklist_ArrayAdapter arrayAdapter;
    ListView checklistListView;

    private ListView getChecklistListView(){
        if (checklistListView == null){
            checklistListView = (ListView)findViewById(R.id.checklist_listview);
        }

        return checklistListView;
    }

    private Checklist_ArrayAdapter getArrayAdapter()
    {
        if (arrayAdapter == null)
        {
            arrayAdapter = new Checklist_ArrayAdapter(this, R.layout.activity_checklist_row, new ArrayList<Checklist_Row>());
        }
        return arrayAdapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);

        // Populate the listView with the contents of the Checklist table.
        populateListView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_checklist, menu);
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

    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
    }
    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
    }
    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
    }

    private void populateListView(){
        ArrayList<Checklist_Row> rowArrayList = Checklist_Contract_Db_Helper.getDb_helper(this).returnChecklistRows(null);
        getChecklistListView().setAdapter(getArrayAdapter());
        getArrayAdapter().addAll(rowArrayList);
        getChecklistListView().requestLayout();
    }


}
