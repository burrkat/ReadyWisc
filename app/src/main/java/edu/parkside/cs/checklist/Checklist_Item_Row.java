package edu.parkside.cs.checklist;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by krawchukd on 2/15/15.
 */
public class Checklist_Item_Row implements Parcelable {

    private int entryid;
    private String name;
    private int qty;
    private boolean isChecked;
    private int checklist_entryid;

    public Checklist_Item_Row(){
    }

    public Checklist_Item_Row(Parcel in){
        readFromParcel(in);
    };

    public Checklist_Item_Row(String title, boolean isChecked){
        this.name = title;
        this.isChecked = isChecked;
    }

    public String getName() {
        if (name == null)
        {
            name = "Empty";
        }
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

    public int getChecked(){
        return (isChecked == true) ? 1 : 0;
    }

    public int getEntryid() {
        return entryid;
    }

    public void setEntryid(int entryid) {
        this.entryid = entryid;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getChecklist_entryid() {
        return checklist_entryid;
    }

    public void setChecklist_entryid(int checklist_entryid) {
        this.checklist_entryid = checklist_entryid;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getEntryid());
        dest.writeString(getName());
        dest.writeInt(getQty());
        dest.writeInt(getChecked());
        dest.writeInt(getChecklist_entryid());
    }

    private void readFromParcel(Parcel source){
        setEntryid(source.readInt());
        setName(source.readString());
        setQty(source.readInt());
        setChecked(source.readInt());
        setChecklist_entryid(source.readInt());
    }

    public static final Parcelable.Creator<Checklist_Item_Row> CREATOR
            = new Parcelable.Creator<Checklist_Item_Row>() {
        public Checklist_Item_Row createFromParcel(Parcel in) {
            return new Checklist_Item_Row(in);
        }

        public Checklist_Item_Row[] newArray(int size) {
            return new Checklist_Item_Row[size];
        }
    };
}
