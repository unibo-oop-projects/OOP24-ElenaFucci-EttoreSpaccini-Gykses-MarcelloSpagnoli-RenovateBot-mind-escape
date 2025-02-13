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
import mindescape.model.world.items.interactable.impl.LockedUnpickable;
import mindescape.model.world.player.api.Player;
import mindescape.model.world.player.impl.PlayerImpl;
import mindescape.model.world.rooms.api.Room;
import mindescape.model.world.rooms.impl.RoomImpl;

/**
 * Implementation of the World interface.
 */
public class WorldImpl implements World, Serializable {

    private final Point2D playerStartPosition = new Point2D(110, 170);

    private static final long serialVersionUID = 1L;
    private final Player player;
    private final List<Room> rooms;
    private final transient CollisionDetector collisionDetector;
    private transient Optional<GameObject> collidingObject;

    /**
     * Constructs a new WorldImpl instance.
     *
     * @param username the username of the player
     */
    public WorldImpl(final String username) {
        this.rooms = RoomImpl.createRooms();
        final var currentRoom = rooms.stream().filter(x -> x.getName().equals("bedroom")).findFirst().get();
        this.player = new PlayerImpl(this.playerStartPosition, username, Dimensions.TILE, currentRoom);
        currentRoom.addGameObject(player);
        this.collisionDetector = new CollisionDetectorImpl();
        this.collidingObject = Optional.empty();
    }

    /**
     * Constructs a new WorldImpl instance for file loading.
     * 
     * @param rooms the list of rooms
     * @param player the player
     */
    public WorldImpl(final List<Room> rooms, final Player player) {
        this.rooms = rooms;
        this.player = player;
        this.collisionDetector = new CollisionDetectorImpl();
        this.collidingObject = Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Room> getRooms() {
        return this.rooms;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Enigma> letPlayerInteract() {
        Optional<Enigma> enigma = Optional.empty();

        if (collidingObject.isPresent()) {
            if (this.collidingObject.get() instanceof UnpickableWithEnigma) {
                enigma = Optional.of(((UnpickableWithEnigma) this.collidingObject.get()).getEnigma());
            } 
            if (this.collidingObject.get() instanceof Interactable) {
                this.player.interact((Interactable) this.collidingObject.get());
            }
        }

        if (enigma.isPresent() && enigma.get().isSolved()) {
            return Optional.empty();
        }

        return enigma;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasWon() {
        LockedUnpickable mirror = (LockedUnpickable) this.getRooms()
            .stream()
            .filter(room -> room.getName().equals("final"))
            .findFirst()
            .get()
            .getGameObjects()
            .stream()
            .filter(x -> x.getName().equals("Mirror"))
            .findFirst()
            .get();

        return mirror.isUnlocked();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Room getCurrentRoom() {
        return this.player.getCurrentRoom();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addRoom(final Room room) throws NullPointerException {
        Objects.requireNonNull(room, "Room must not be null");
        this.rooms.add(room);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void movePlayer(final Movement movement) throws NullPointerException {
        Objects.requireNonNull(movement, "Movement must not be null");

        var playerPosition = this.player.getPosition();
        var position = new Point2D(playerPosition.x() + movement.getX(), playerPosition.y() + movement.getY());
        var collidingObject = this.collisionDetector.collisions(
            position, 
            this.player.getDimensions(), 
            this.getCurrentRoom().getGameObjects()
        );

        if (collidingObject.isEmpty()) {
            this.player.move(movement);
            collidingObject = Optional.empty();
        } else {
            this.setCollidingObject(collidingObject);
        }
    }

    /**
     * Sets the colliding object for the current world.
     *
     * @param collidingObject an Optional containing the GameObject that is colliding, 
     *                        or an empty Optional if there is no collision.
     */
    private void setCollidingObject(final Optional<GameObject> collidingObject) {
        this.collidingObject = collidingObject;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        return this.player;
    }
}
