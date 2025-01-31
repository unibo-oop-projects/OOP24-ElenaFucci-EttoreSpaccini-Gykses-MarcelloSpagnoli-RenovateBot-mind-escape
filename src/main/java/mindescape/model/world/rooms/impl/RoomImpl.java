package mindescape.model.world.rooms.impl;

import java.util.Set;

import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.GameObject;
import mindescape.model.world.rooms.api.Room;

public class RoomImpl implements Room {

    private final Dimensions dimensions;

    private final Set<GameObject> gameObjects;


    @Override
    public boolean isPlayerPresent() {
        return true;
    }

    @Override
    public void addGameObject(GameObject gameObject) {
        if (gameObject.) {
            
        }
    }

    @Override
    public void removeGameObject(GameObject gameObject) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeGameObject'");
    }

    @Override
    public Set<GameObject> getGameObjects() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGameObjects'");
    }
}
