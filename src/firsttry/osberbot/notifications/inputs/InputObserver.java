package firsttry.osberbot.notifications.inputs;

import firsttry.osberbot.notifications.inputs.actions.InputAction;

public interface InputObserver {

    void onInput(InputAction inputAction);

}
