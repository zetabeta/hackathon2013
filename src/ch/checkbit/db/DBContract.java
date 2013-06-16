package ch.checkbit.db;

import android.provider.BaseColumns;

public class DBContract {

    private DBContract() {
    }

    public class Action implements BaseColumns {
        public static final String TABLE_NAME = "action";
        public static final String COLUMN_NAME_ACTION_ID = "action_id";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_TIME = "time";
    }

    public enum ActionType {
        WATER, CUT, MUSIC, START
    }

}
