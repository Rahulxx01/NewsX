package in.connectitude.newsx.sync;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class NewsReminderIntentService extends IntentService {



    public NewsReminderIntentService() {
        super("NewsReminderIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getAction();
        ReminderTasks.executeTask(this, action);

    }
}
