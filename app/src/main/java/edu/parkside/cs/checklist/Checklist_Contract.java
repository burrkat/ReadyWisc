package edu.parkside.cs.checklist;

import android.provider.BaseColumns;

/**
 * Created by krawchukd on 2/14/15.
 */
public final class Checklist_Contract {

    public static final int DATABASE_VERSION = 1;

    // Stubbed to prevent instantiation.
    public Checklist_Contract() {};

    /* Inner class that defines the table contents */
    public static abstract class Checklist implements BaseColumns {
        public static final String TABLE_NAME = "checklist";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_PROGRESS = "progress";
    }

    /* Inner class that defines the table contents */
    public static abstract class Item implements BaseColumns {
        public static final String TABLE_NAME = "item";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_QTY = "qty";
        public static final String COLUMN_NAME_COMPLETE = "complete";
        public static final String COLUMN_NAME_CHECKLIST_ID = "checklist_entryid";
    }

    /* Inner class that defines the table contents */
    public static abstract class Description implements BaseColumns{
        public static final String TABLE_NAME = "description";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_ITEM_ID = "item_entryid";
    }

    public static abstract class Checklist_Queries{
        public static final String ALL_ITEMS = "SELECT * FROM " +
                Checklist.TABLE_NAME;

        public static final String insertChecklistIntoDatabase(Checklist_Row checklist_row)
        {
            /*
            * INSERT INTO TABLE_NAME (column1, column2, column3,...columnN)]
            * VALUES (value1, value2, value3,...valueN);
             */
            String query = "INSERT INTO " + Checklist.TABLE_NAME +
                    " VALUES (NULL, " + "\'" + checklist_row.getTitle() + "\'" + ", " + "\'" +
                    checklist_row.getProgress() + "\'" + ")";

            return query;
        }

    }

    public static abstract class Checklist_Item_Queries{
        public static final String ALL_ITEMS = "SELECT * FROM " +
                Item.TABLE_NAME;

        public static final String[] updateItem(Checklist_Item_Row item, String description){
            String itemPropertiesQuery = "UPDATE " + Item.TABLE_NAME +
                    " SET " + Item.COLUMN_NAME_NAME + " = " + item.getName() + ", " +
                    Item.COLUMN_NAME_QTY + " = " + item.getQty() + " WHERE " +
                    Item._ID + " = " + item.getEntryid();

            String descriptionQuery = "UPDATE " + Description.TABLE_NAME +
                    " SET " + Description.COLUMN_NAME_DESCRIPTION + " = " +
                    description + " WHERE " + Description._ID +
                    " = " + item.getEntryid();

            String [] queries = {itemPropertiesQuery, descriptionQuery};

            return queries;
        }

        public static final String[] insertRow(Checklist_Item_Row item, String description){
            String query_0 = "INSERT INTO " + Item.TABLE_NAME + " (" +
                    Item.COLUMN_NAME_NAME + ", " + Item.COLUMN_NAME_QTY +
                    ", " + Item.COLUMN_NAME_COMPLETE + ", " +
                    Item.COLUMN_NAME_CHECKLIST_ID + ") VALUES (" +
                    item.getName() + ", " + item.getQty() + ", " +
                    item.getChecked() + ", " + item.getChecklist_entryid() + ")";

             String query_1 = "INSERT INTO " + Description.TABLE_NAME + " (" +
                     Description.COLUMN_NAME_DESCRIPTION + ", " +
                     Description.COLUMN_NAME_ITEM_ID + ") VALUES (" +
                     description + ", " + item.getEntryid() + ")";

            String [] queries = {query_0, query_1};
            return queries;
        }
    }

    public static abstract class Checklist_Description_Qureries{
        public static final String getDescriptionWithItemEntryID(int entryID){
            String queryString = "SELECT description FROM " +
                    Description.TABLE_NAME + " WHERE item_entryid = " +
                    entryID;
            return queryString;
        }
    }
}
