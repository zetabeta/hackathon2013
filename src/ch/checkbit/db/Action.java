package ch.checkbit.db;

import ch.checkbit.db.DBContract.ActionType;

/**
 * represents a db information of a single row in the action table
 * 
 * @author zeta
 * 
 */
public class Action {

    private ActionType type;
    private long time;

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

}
