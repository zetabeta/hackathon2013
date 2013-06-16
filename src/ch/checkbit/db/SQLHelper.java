package ch.checkbit.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import ch.checkbit.db.DBContract.ActionType;

public class SQLHelper {

    private ActionsDBHelper dbHelper;

    public SQLHelper(Context context) {
        this.dbHelper = new ActionsDBHelper(context);
    }

    public long put(DBContract.ActionType type, Date time) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBContract.Action.COLUMN_NAME_TYPE, type.name());
        values.put(DBContract.Action.COLUMN_NAME_TIME, time.getTime());
        long newRowId = db.insert(DBContract.Action.TABLE_NAME, null, values);
        return newRowId;
    }

    public List<ActionDTO> getAllFrom(ActionType type) {

        Log.i("tag", "get all from called");
        List<ActionDTO> list = new ArrayList<ActionDTO>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT  * FROM " + DBContract.Action.TABLE_NAME + " WHERE "
                + DBContract.Action.COLUMN_NAME_TYPE + "='" + type.name() + "' ORDER BY "
                + DBContract.Action.COLUMN_NAME_TIME + " DESC";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                ActionDTO action = new ActionDTO();
                action.setType(ActionType.valueOf(cursor.getString(1)));
                action.setTime(cursor.getLong(2));
                list.add(action);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public ActionDTO getLastUpdateFrom(ActionType type) {
        List<ActionDTO> list = getAllFrom(type);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
