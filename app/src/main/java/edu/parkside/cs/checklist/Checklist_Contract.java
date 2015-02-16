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
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_PROGRESS = "progress";
    }

    /* Inner class that defines the table contents */
    public static abstract class Item implements BaseColumns {
        public static final String TABLE_NAME = "item";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_QTY = "qty";
        public static final String COLUMN_NAME_COMPLETE = "complete";
        public static final String COLUMN_NAME_CHECKLIST_ID = "checklist_entryid";
    }

    /* Inner class that defines the table contents */
    public static abstract class Description implements BaseColumns{
        public static final String TABLE_NAME = "description";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_ITEM_ID = "item_entryid";
    }

    public static abstract class Checklist_Queries{
        public static final String ALL_ITEMS = "SELECT * FROM " +
                Checklist.TABLE_NAME;
    }
}
