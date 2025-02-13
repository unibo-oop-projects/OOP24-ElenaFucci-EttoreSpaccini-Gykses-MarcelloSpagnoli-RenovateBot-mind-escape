package mindescape.controller.core.api;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public enum KeyMapper {
    W(KeyEvent.VK_W, UserInput.UP),
    S(KeyEvent.VK_S, UserInput.DOWN),
    A(KeyEvent.VK_A, UserInput.LEFT),
    D(KeyEvent.VK_D, UserInput.RIGHT),
    E(KeyEvent.VK_E, UserInput.INTERACT),
    I(KeyEvent.VK_I, UserInput.INVENTORY);

    private final int keyCode;
    private final UserInput userInput;

    KeyMapper(final int keyCode, final UserInput userInput) {
        this.keyCode = keyCode;
        this.userInput = userInput;
    }

    public static Map<Integer, UserInput> getKeyMap() {
        final Map<Integer, UserInput> map = new HashMap<>();
        for (final KeyMapper key : values()) {
            map.put(key.keyCode, key.userInput);
        }
        return map;
    }

    
}
