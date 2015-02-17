package edu.parkside.cs.checklist;

import android.os.Bundle;
import android.view.View;

import com.example.kiflebk.readywisc.R;

public class Checklist_Create_Item_Add extends Checklist_Item_Create {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist_item_create);
    }

    @Override
    public void saveButtonPressed(View view) {
        // Create checklist_item from editText fields.
        // Return that item to the parent activity.
        finish();
    }
}
