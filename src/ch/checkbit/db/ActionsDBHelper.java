package ch.checkbit.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import ch.checkbit.LogType;

/**
 * helps in managing the creation and update of the db, as well as
 * dropping/creating tables
 * 
 * @author zeta
 * 
 */
public class ActionsDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Bonsai.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String TIMESTAMP_TYPE = " TIMESTAMP";

    private static final String SQL_CREATE_TABLE_ACTION = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s %s, %s %s )", DBContract.Action.TABLE_NAME,
            DBContract.Action.COLUMN_NAME_ACTION_ID, DBContract.Action.COLUMN_NAME_TYPE, TEXT_TYPE,
            DBContract.Action.COLUMN_NAME_TIME, TIMESTAMP_TYPE);

    private static final String SQL_DROP_TABLE_ACTION = String.format("DROP TABLE IF EXISTS %s",
            DBContract.Action.TABLE_NAME);

    public ActionsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        Log.i(LogType.DB.name(), "Creating DB schema.");
        db.execSQL(SQL_CREATE_TABLE_ACTION);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(LogType.DB.name(), "Upgrading DB schema.");
        db.execSQL(SQL_DROP_TABLE_ACTION);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(LogType.DB.name(), "Downgrading DB schema.");
        onUpgrade(db, oldVersion, newVersion);
    }

    public void createTable(SQLiteDatabase db) {
        Log.i(LogType.DB.name(), "Creating action table in the DB.");
        db.execSQL(SQL_CREATE_TABLE_ACTION);
    }

    public void dropTable(SQLiteDatabase db) {
        Log.i(LogType.DB.name(), "Dropping action table from the DB.");
        db.execSQL(SQL_DROP_TABLE_ACTION);
    }

}
