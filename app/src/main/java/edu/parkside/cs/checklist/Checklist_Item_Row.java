package edu.parkside.cs.checklist;

/**
 * Created by krawchukd on 2/15/15.
 */
public class Checklist_Item_Row {

    private String name;
    private boolean isChecked;

    public Checklist_Item_Row(){
        this.name = "Empty";
        this.isChecked = false;
    }

    public Checklist_Item_Row(String title, boolean isChecked){
        this.name = title;
        this.isChecked = isChecked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // 1. Check for valid input.
        // 2. Is the new setting reasonable?
        // 3. If so then set name.
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked() {
        // 1. Check for valid input.
        // 2. Is the new setting reasonable?
        // 3. If so then set checked.

        this.isChecked = (this.isChecked == true) ? false : true;
    }

    public void setChecked(int condition)
    {
        // 1. Check for valid input.
        // 2. Is the new setting reasonable?
        // 3. If so then set checked.
        this.isChecked = (condition == 1) ? true : false;
    }
}
