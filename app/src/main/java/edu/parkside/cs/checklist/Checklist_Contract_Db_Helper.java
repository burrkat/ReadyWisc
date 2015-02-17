package edu.parkside.cs.checklist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by krawchukd on 2/14/15.
 */
public class Checklist_Contract_Db_Helper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ChecklistContract.db";
    public static final int SUCCESS = 0;
    public static final int FAILURE = 1;
    public static Checklist_Contract_Db_Helper db_helper;

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use to open or create the database
     * @param name    of the database file, or null for an in-memory database
     * @param factory to use for creating cursor objects, or null for the default
     * @param version number of the database (starting at 1); if the database is older,
     *                {@link #onUpgrade} will be used to upgrade the database; if the database is
     *                newer, {@link #onDowngrade} will be used to downgrade the database
     */
    public Checklist_Contract_Db_Helper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Create a helper object to create, open, and/or manage a database.
     * The database is not actually created or opened until one of
     * {@link #getWritableDatabase} or {@link #getReadableDatabase} is called.
     * <p/>
     * <p>Accepts input param: a concrete instance of {@link android.database.DatabaseErrorHandler} to be
     * used to handle corruption when sqlite reports database corruption.</p>
     *
     * @param context      to use to open or create the database
     * @param name         of the database file, or null for an in-memory database
     * @param factory      to use for creating cursor objects, or null for the default
     * @param version      number of the database (starting at 1); if the database is older,
     *                     {@link #onUpgrade} will be used to upgrade the database; if the database is
     *                     newer, {@link #onDowngrade} will be used to downgrade the database
     * @param errorHandler the {@link android.database.DatabaseErrorHandler} to be used when sqlite reports database
     */
    public Checklist_Contract_Db_Helper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public static Checklist_Contract_Db_Helper getDb_helper(Context context)
    {
        if (db_helper == null){
            db_helper = new Checklist_Contract_Db_Helper(context, null, null, Checklist_Contract.DATABASE_VERSION);
        }
        return db_helper;
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        createChecklistTable(db);
        createItemTable(db);
        createDescriptionTable(db);
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p/>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Called when the database needs to be downgraded. This is strictly similar to
     * {@link #onUpgrade} method, but is called whenever current version is newer than requested one.
     * However, this method is not abstract, so it is not mandatory for a customer to
     * implement it. If not overridden, default implementation will reject downgrade and
     * throws SQLiteException
     * <p/>
     * <p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    /**
     * Description: Called to create the inital Checklist Table.
     */
    private void createChecklistTable(SQLiteDatabase database){

        String CREATE_CHECKLIST_TABLE = "CREATE TABLE " + Checklist_Contract.Checklist.TABLE_NAME +
                "(" + Checklist_Contract.Checklist.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Checklist_Contract.Checklist.COLUMN_NAME_TITLE + " TEXT, " +
                Checklist_Contract.Checklist.COLUMN_NAME_PROGRESS + " INTEGER)";

        database.execSQL(CREATE_CHECKLIST_TABLE);
    }

    private void createItemTable(SQLiteDatabase database){

        String CREATE_ITEM_TABLE = "CREATE TABLE " + Checklist_Contract.Item.TABLE_NAME +
                "(" + Checklist_Contract.Item.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Checklist_Contract.Item.COLUMN_NAME_NAME + " TEXT, " +
                Checklist_Contract.Item.COLUMN_NAME_QTY + " INTEGER, " +
                Checklist_Contract.Item.COLUMN_NAME_COMPLETE + " INTEGER, " +
                Checklist_Contract.Item.COLUMN_NAME_CHECKLIST_ID + " INTEGER )";

        database.execSQL(CREATE_ITEM_TABLE);
    }

    private void createDescriptionTable(SQLiteDatabase database){

        String CREATE_DESCRIPTION_TABLE = "CREATE TABLE " + Checklist_Contract.Description.TABLE_NAME +
                "(" + Checklist_Contract.Description.COLUMN_NAME_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Checklist_Contract.Description.COLUMN_NAME_DESCRIPTION + " TEXT, " +
                Checklist_Contract.Description.COLUMN_NAME_ITEM_ID + " NUMBER)";

        database.execSQL(CREATE_DESCRIPTION_TABLE);
    }

    public ArrayList<Checklist_Row> returnChecklistRows(String[] args){

        ArrayList<Checklist_Row> rowList = new ArrayList<Checklist_Row>();
        Cursor cursor;

        SQLiteDatabase database = this.getReadableDatabase();

        if(args == null)
        {
            cursor = database.rawQuery(Checklist_Contract.Checklist_Queries.ALL_ITEMS, null);

            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                do {
                    Checklist_Row checklist_row = new Checklist_Row();
                    int entryid_index = cursor.getColumnIndex((Checklist_Contract.Checklist.COLUMN_NAME_ENTRY_ID));
                    int titleIndex = cursor.getColumnIndex(Checklist_Contract.Checklist.COLUMN_NAME_TITLE);
                    int progressIndex = cursor.getColumnIndex(Checklist_Contract.Checklist.COLUMN_NAME_PROGRESS);

                    checklist_row.setEntryid(cursor.getInt(entryid_index));
                    checklist_row.setTitle(cursor.getString(titleIndex));
                    checklist_row.setProgress(cursor.getInt(progressIndex));

                    rowList.add(checklist_row);
                } while (cursor.moveToNext());
            } else {
                rowList.add(new Checklist_Row("Empty", 0));
            }
        }

        database.close();
        return rowList;
    }

    public static void addChecklist(Checklist_Row checklist_row, Context context){
        ContentValues contentValues = new ContentValues();

        contentValues.put(Checklist_Contract.Checklist.COLUMN_NAME_TITLE, checklist_row.getTitle());
        contentValues.put(Checklist_Contract.Checklist.COLUMN_NAME_PROGRESS, checklist_row.getProgress());

        SQLiteDatabase database = Checklist_Contract_Db_Helper.getDb_helper(context).getWritableDatabase();
        database.insert(Checklist_Contract.Checklist.TABLE_NAME, null, contentValues);
        database.close();
    }

    public ArrayList<Checklist_Item_Row> returnChecklistItemRows(String[] args){

        ArrayList<Checklist_Item_Row> rowList = new ArrayList<Checklist_Item_Row>();
        Cursor cursor;

        SQLiteDatabase database = this.getReadableDatabase();

        if(args == null)
        {
            cursor = database.rawQuery(Checklist_Contract.Checklist_Item_Queries.ALL_ITEMS, null);

            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                do {
                    Checklist_Item_Row checklist_item_row = new Checklist_Item_Row();
                    int entryid_index = cursor.getColumnIndex(Checklist_Contract.Item.COLUMN_NAME_ENTRY_ID);
                    int nameIndex = cursor.getColumnIndex(Checklist_Contract.Item.COLUMN_NAME_NAME);
                    int qtyIndex = cursor.getColumnIndex(Checklist_Contract.Item.COLUMN_NAME_QTY);
                    int isCheckedIndex = cursor.getColumnIndex(Checklist_Contract.Item.COLUMN_NAME_COMPLETE);
                    int checklist_entry_index = cursor.getColumnIndex(Checklist_Contract.Item.COLUMN_NAME_CHECKLIST_ID);

                    checklist_item_row.setEntryid(cursor.getInt(entryid_index));
                    checklist_item_row.setName(cursor.getString(nameIndex));
                    checklist_item_row.setQty(cursor.getInt(qtyIndex));
                    checklist_item_row.setChecked(cursor.getInt(isCheckedIndex));
                    checklist_item_row.setChecklist_entryid(cursor.getInt(checklist_entry_index));

                    rowList.add(checklist_item_row);
                } while (cursor.moveToNext());
            } else {
                rowList.add(new Checklist_Item_Row());
            }
        }

        database.close();
        return rowList;
    }

    public int insertItem(Checklist_Row checklist, Checklist_Item_Row item, String description){
        SQLiteDatabase database = getWritableDatabase();

        String [] queries = Checklist_Contract.Checklist_Item_Queries.insertRow(checklist, item, description);

        for (int i = 0; i < queries.length; i++) {
            database.rawQuery(queries[i], null);
        }

        // Return condition constant...
        return SUCCESS;
    }

    public int updateItem(Checklist_Item_Row item, String description){
        SQLiteDatabase database = getWritableDatabase();

        String [] queries = Checklist_Contract.Checklist_Item_Queries.updateItem(item, description);

        for (int i = 0; i < queries.length; i++) {
            database.rawQuery(queries[i], null);
        }

        // Return condition constant...
        return SUCCESS;
    }

    public String returnDescriptionFromItem(Checklist_Item_Row item){
        String descriptionText;
        Cursor cursor;

        SQLiteDatabase database = this.getReadableDatabase();

        cursor = database.rawQuery(Checklist_Contract.Checklist_Description_Qureries.getDescriptionWithItemEntryID(item.getEntryid()), null);

        if (cursor.moveToFirst()){
            cursor.moveToFirst();
            do{
                int descriptionIndex = cursor.getColumnIndex(Checklist_Contract.Description.COLUMN_NAME_DESCRIPTION);

                descriptionText = cursor.getString(descriptionIndex);
            }while (cursor.moveToNext());
        }
        else
            descriptionText = "Empty";

        database.close();
        return descriptionText;
    }

}
