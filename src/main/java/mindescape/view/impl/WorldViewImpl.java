package mindescape.view.impl;

import java.awt.event.KeyEvent;
import java.util.Map;

import mindescape.controller.api.Controller;
import mindescape.controller.api.UserInput;
import mindescape.view.api.View;

public class WorldViewImpl implements View {

    private static final Map<Integer, UserInput> KEY_MAPPER = Map.of(
        KeyEvent.VK_W, UserInput.UP,
        KeyEvent.VK_S, UserInput.DOWN,
        KeyEvent.VK_A, UserInput.LEFT,
        KeyEvent.VK_D, UserInput.RIGHT,
        KeyEvent.VK_E, UserInput.INTERACT,
        KeyEvent.VK_I, UserInput.OPEN_INVENTORY
    );
    private final Controller controller;

    public WorldViewImpl(final Controller controller) {
        this.controller = controller;
    }
    

    @Override
    public void start() {
        
        
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }



}
