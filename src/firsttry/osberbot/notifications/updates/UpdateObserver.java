package firsttry.osberbot.notifications.updates;

import firsttry.osberbot.notifications.updates.actions.UpdateAction;

public interface UpdateObserver {

    void onUpdate(UpdateAction updateAction);

}
