package edu.parkside.cs.checklist;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kiflebk.readywisc.R;

public class Checklist_Item_Detail extends ActionBarActivity {

    Checklist_Item_Row passedItem;
    boolean editTextHasBeenEdited;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist_item_detail);

        ((Button)findViewById(R.id.activity_checklist_item_detail_update_button)).setEnabled(false);

        passedItem = this.getIntent().getParcelableExtra(Checklist_Item_ListView.EXTRA_MESSAGE);

        new Runnable() {
            @Override
            public void run() {
                populateWidgets();
            }
        }.run();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_checklist__item__detail, menu);
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

    private void populateWidgets(){

        new Runnable() {
            @Override
            public void run() {
                populateDescriptionTextField();
            }
        }.run();

        EditText nameTextField = (EditText)findViewById(R.id.activity_checklist_item_detail_name_edittext);
        EditText qtyTextField = (EditText)findViewById(R.id.activity_checklist_item_detail_qty_edittext);

        nameTextField.setText(passedItem.getName());
        qtyTextField.setText("" + passedItem.getQty());
    }

    private void populateDescriptionTextField(){
        EditText descriptionTextField = (EditText)findViewById(R.id.activity_checklist_item_detail_decription_edittext);
        descriptionTextField.setText(Checklist_Contract_Db_Helper.getDb_helper(this).returnDescriptionFromItem(passedItem));
    }

    public void updateButtonPressed(View view){
        int status = Checklist_Contract_Db_Helper.getDb_helper(this).updateItem(passedItem,
                ((EditText)findViewById(R.id.activity_checklist_item_detail_decription_edittext)).toString());
        // Check status and update user.
        // If successful return to previous activity.
        finish();
    }

    public void cancelButtonPressed(View view){
        finish();
    }

    public void editTextViewHasBeenSelected(View view){
        if (editTextHasBeenEdited == false)
        {
            ((Button)findViewById(R.id.activity_checklist_item_detail_update_button)).setEnabled(true);
            editTextHasBeenEdited = true;
        }
    }
}
