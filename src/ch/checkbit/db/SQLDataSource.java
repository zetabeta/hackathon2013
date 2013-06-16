package ch.checkbit.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import ch.checkbit.LogType;
import ch.checkbit.db.DBContract.ActionType;

/**
 * encapsulates CRUD DB common operations used in the app, as well as openning
 * and closing the respective database
 * 
 * @author zeta
 * 
 */
public class SQLDataSource {

    private ActionsDBHelper dbHelper;
    private SQLiteDatabase database;

    private static final String SELECT_QUERY_ORDERBY_DESC = "SELECT  * FROM %s  WHERE %s='%s' ORDER BY %s DESC";

    public SQLDataSource(Context context) {
        this.dbHelper = new ActionsDBHelper(context);
    }

    public long put(DBContract.ActionType type) {
        Log.i(LogType.DB.name(), "Saving new action from type " + type);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBContract.Action.COLUMN_NAME_TYPE, type.name());
        values.put(DBContract.Action.COLUMN_NAME_TIME, new Date().getTime());
        long newRowId = db.insert(DBContract.Action.TABLE_NAME, null, values);
        return newRowId;
    }

    public List<Action> getAllActionsFrom(ActionType type) {
        Log.i(LogType.DB.name(), "Getting all actions from type " + type);

        List<Action> list = new ArrayList<Action>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = String.format(SELECT_QUERY_ORDERBY_DESC, DBContract.Action.TABLE_NAME,
                DBContract.Action.COLUMN_NAME_TYPE, type.name(), DBContract.Action.COLUMN_NAME_TIME);
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Action action = new Action();
                action.setType(ActionType.valueOf(cursor.getString(1)));
                action.setTime(cursor.getLong(2));
                list.add(action);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public Action getLastUpdateFrom(ActionType type) {
        Log.i(LogType.DB.name(), "Getting last saved action from type." + type);
        List<Action> list = getAllActionsFrom(type);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public void open() throws SQLException {
        Log.i(LogType.DB.name(), "Opening the database.");
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        Log.i(LogType.DB.name(), "Closing the database.");
        dbHelper.close();
    }

    public void createTable() {
        Log.i(LogType.DB.name(), "Creating the action table");
        dbHelper.createTable(database);
    }

    public void dropTable() {
        Log.i(LogType.DB.name(), "Dropping the action table");
        dbHelper.dropTable(database);
    }

}
