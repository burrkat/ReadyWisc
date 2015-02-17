package edu.parkside.cs.checklist;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kiflebk.readywisc.R;

public class Checklist_Item_Create extends ActionBarActivity {
    Checklist_Row checklist_row;
    boolean editTextHasBeenEdited;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist_item_create);

        checklist_row = getIntent().getParcelableExtra(Checklist.EXTRA_MESSAGE);
        ((Button)findViewById(R.id.activity_checklist_item_create_save_button)).setEnabled(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_checklist__item__create, menu);
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

    public void saveButtonPressed(View view){
        // Retrieve input values from editText fields.
        EditText name = (EditText)findViewById(R.id.activity_checklist_item_create_name_edittext);
        EditText qty = (EditText)findViewById(R.id.activity_checklist_item_create_qty_edittext);
        EditText description = (EditText)findViewById(R.id.activity_checklist_item_create_description_edittext);

        // Create item from editText fields.
        Checklist_Item_Row item = new Checklist_Item_Row();
        item.setName(name.toString());
        item.setQty(new Integer(qty.toString()).intValue());


        int status = Checklist_Contract_Db_Helper.getDb_helper(this).insertItem(checklist_row, item, description.toString());
        // Check status and update user.
        // If successful return to previous activity.
        if (status == Checklist_Contract_Db_Helper.SUCCESS)
        {
            finish();
        }
    }

    public void cancelButtonPressed(View view){
        finish();
    }

    public void editTextViewHasBeenSelected(View view){
        if (editTextHasBeenEdited == false)
        {
            ((Button)findViewById(R.id.activity_checklist_item_create_save_button)).setEnabled(true);
            editTextHasBeenEdited = true;
        }
    }
}
