package ch.checkbit.services;

import android.app.IntentService;
import android.content.Intent;

public class StateManagerService extends IntentService {

    public StateManagerService() {
        super("StateManagerService");
    }

    @Override
    protected void onHandleIntent(Intent arg0) {
        // TODO Auto-generated method stub
        // the database foo will be moved here, so the db access will be only
        // through service
    }

}
