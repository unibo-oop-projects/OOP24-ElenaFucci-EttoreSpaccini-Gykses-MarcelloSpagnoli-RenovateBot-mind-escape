package mindescape.model.world.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import mindescape.model.world.api.World;
import mindescape.model.world.core.api.GameObject;
import mindescape.model.world.core.api.Movement;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.items.api.Interactable;
import mindescape.model.world.player.api.Player;
import mindescape.model.world.rooms.api.Room;

/**
 * Implementation of the World interface.
 */
public class WorldImpl implements World, Serializable {

    private final static long serialVersionUID = 1L;
    private final Player player;
    private final List<Room> rooms;
    private Room currentRoom;
    private boolean hasWon;
    private final transient CollisionDetector collisionDetector;
    private Optional<GameObject> collidingObject;
    
    public WorldImpl(final Player player, final List<Room> rooms, final Room currentRoom, final boolean hasWon) {
        this.player = player;
        this.rooms = rooms;
        this.currentRoom = currentRoom;
        this.hasWon = hasWon;
        this.collisionDetector = new CollisionDetectorImpl();
    }

    @Override
    public List<Room> getRooms() {
        return this.rooms;
    }

    @Override
    public boolean letPlayerInteract() {
        
        var collidingObject = this.collisionDetector.collision(Point2D , this.player.getDimensions(), this.currentRoom.getGameObjects());
        if (collidingObject instanceof Interactable) {
            this.player.interact((Interactable) collidingObject);
            return true;
        } 
        
        return false;
    }

    @Override
    public boolean hasWon() {
        return this.hasWon;
    }

    @Override
    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    @Override
    public void addRoom(final Room room) throws NullPointerException {
        Objects.requireNonNull(room, "Room must not be null");
        this.rooms.add(room);
    }

    @Override
    public void movePlayer(final Movement movement) {
        Objects.requireNonNull(movement, "Movement must not be null");

        // check if the player is colliding with any object in the room before moving   
        var collidingObject = this.collisionDetector.collision(this.player.getPosition() + movement, this.player.getDimensions(), this.currentRoom.getGameObjects());
        if (collidingObject != null) {
            return;
        }
        this.player.move(movement);
    }

    private void setCollidingObject(final Optional<GameObject> collidingObject) {
        this.collidingObject = collidingObject;
    }

    private Optional<GameObject> getCollidingObject() {
        return this.collidingObject;
    }
    
}
