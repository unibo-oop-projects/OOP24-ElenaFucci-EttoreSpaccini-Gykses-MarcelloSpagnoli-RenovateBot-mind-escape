package mindescape.controller.core.api;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Scope of this is to keep the bindings beetwen keys and UserInputs.
 */
public enum KeyMapper {
    /**
     * Binding for W.
     */
    W(KeyEvent.VK_W, UserInput.UP),
    /**
     * Binding for S.
     */
    S(KeyEvent.VK_S, UserInput.DOWN),
    /**
     * Binding for A.
     */
    A(KeyEvent.VK_A, UserInput.LEFT),
    /**
     * Binding for D.
     */
    D(KeyEvent.VK_D, UserInput.RIGHT),
    /**
     * Binding for E.
     */
    E(KeyEvent.VK_E, UserInput.INTERACT),
    /**
     * Binding for I.
     */
    I(KeyEvent.VK_I, UserInput.INVENTORY);

    private final int keyCode;
    private final UserInput userInput;

    KeyMapper(final int keyCode, final UserInput userInput) {
        this.keyCode = keyCode;
        this.userInput = userInput;
    }

    /**
     * Returns a map beetwen keyborard keys and UserInputs.
     * @return Map<Integer, UserInput> containing the bindings
     */
    public static Map<Integer, UserInput> getKeyMap() {
        final Map<Integer, UserInput> map = new HashMap<>();
        for (final KeyMapper key : values()) {
            map.put(key.keyCode, key.userInput);
        }
        return map;
    }
}

