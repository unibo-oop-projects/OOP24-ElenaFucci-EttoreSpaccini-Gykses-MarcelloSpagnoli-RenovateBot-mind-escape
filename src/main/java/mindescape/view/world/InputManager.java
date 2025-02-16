package mindescape.view.world;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import mindescape.controller.core.api.KeyMapper;
import mindescape.controller.core.api.UserInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class that manages the input of the of the worldview.
 */
public final class InputManager implements KeyListener {
    private final Map<Integer, Boolean> keyState = new HashMap<>();
    private final Map<Integer, UserInput> keyMapper = KeyMapper.getKeyMap();

    /**
     * Constructor for InputManager.
     */
    public InputManager() {
        initializeKeyState();
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        pressedInput(e.getKeyCode());
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        releasedInput(e.getKeyCode());
    }

    /**
     * Method that sets the key state to true when a key is pressed.
     * @param keyCode that is pressed.
     */
    public void pressedInput(final int keyCode) {
        if (keyMapper.containsKey(keyCode)) {
            keyState.put(keyCode, true);
        }
    }

    /**
     * Method that sets the key state to false when a key is released.
     * @param keyCode that is released.
     */
    public void releasedInput(final int keyCode) {
        if (keyMapper.containsKey(keyCode)) {
            keyState.put(keyCode, false);
        }
    }

    /**
     * Method that clears the input.
     */
    public void clearInput() {
        keyState.clear();
    }

    /**
     * Method that returns the key state.
     * @return map from integer to boolean (true if the key is pressed, false otherwise).
     */
    public Map<Integer, Boolean> getKeyState() {
        return Collections.unmodifiableMap(keyState);
    }

    private void initializeKeyState() {
        keyMapper.forEach((key, value) -> keyState.put(key, false));
    }
}

