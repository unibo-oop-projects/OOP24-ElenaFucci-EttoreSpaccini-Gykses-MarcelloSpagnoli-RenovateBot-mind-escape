package mindescape.model.world.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import mindescape.model.enigma.api.Enigma;
import mindescape.model.world.api.World;
import mindescape.model.world.core.api.CollisionDetector;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.GameObject;
import mindescape.model.world.core.api.Movement;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.core.impl.CollisionDetectorImpl;
import mindescape.model.world.items.interactable.api.Interactable;
import mindescape.model.world.items.interactable.api.UnpickableWithEnigma;
import mindescape.model.world.player.api.Player;
import mindescape.model.world.player.impl.PlayerImpl;
import mindescape.model.world.rooms.api.Room;
import mindescape.model.world.rooms.impl.RoomImpl;

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
    
    public WorldImpl(final String username) {
        this.rooms = RoomImpl.createRooms();
        this.currentRoom = rooms.stream().filter(x -> x.getName().equals("bedroom")).findFirst().get();
        this.player = new PlayerImpl(Optional.of(new Point2D(120, 120)), username, Dimensions.TILE, currentRoom);
        this.hasWon = false;
        this.collisionDetector = new CollisionDetectorImpl();
        this.collidingObject = Optional.empty();
    }

    @Override
    public List<Room> getRooms() {
        return this.rooms;
    }

    @Override
    public Optional<Enigma> letPlayerInteract() {
        Optional<Enigma> enigma = Optional.empty();
        
        if (this.collidingObject.get() instanceof UnpickableWithEnigma) {
            enigma = Optional.of(((UnpickableWithEnigma) this.collidingObject.get()).getEnigma());
        }
        if (this.collidingObject.get() instanceof Interactable) {
            this.player.interact((Interactable) this.collidingObject.get());
        } 
        return enigma;
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
    public void movePlayer(final Movement movement) throws NullPointerException {
        Objects.requireNonNull(movement, "Movement must not be null");

        // check if the player is colliding with any object in the room before moving
        var playerPosition = this.player.getPosition().get();
        var position = new Point2D(playerPosition.x() + movement.getX(), playerPosition.y() + movement.getY());
        var collidingObject = this.collisionDetector.collisions(position, this.player.getDimensions(), this.currentRoom.getGameObjects());

        if (collidingObject.isEmpty()) {
            this.player.move(movement);
        } else {
            this.setCollidingObject(collidingObject);
        }
    }

    private void setCollidingObject(final Optional<GameObject> collidingObject) {
        this.collidingObject = collidingObject;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }
}
