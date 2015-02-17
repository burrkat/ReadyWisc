package edu.parkside.cs.checklist;

import android.content.Context;

/**
 * Created by krawchukd on 2/14/15.
 */
public class PopulateDB {

    public PopulateDB(Context context){
        Checklist_Row checklist_row = new Checklist_Row("Winter",0);
        Checklist_Contract_Db_Helper.getDb_helper(context).addChecklist(checklist_row);
    }
}
