package mindescape.model.world.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
public final class WorldImpl implements World, Serializable {

    private static final long serialVersionUID = 1L;
    private final Player player;
    private final List<Room> rooms;

    @SuppressFBWarnings(value = "SE_TRANSIENT_FIELD_NOT_RESTORED", justification = "Deserialization is handled by other classes")
    private final transient CollisionDetector collisionDetector;

    @SuppressFBWarnings(value = "SE_TRANSIENT_FIELD_NOT_RESTORED", justification = "Deserialization is handled by other classes")
    private transient Optional<GameObject> collidingObject;
    private final Point2D playerPosition = new Point2D(110, 170);

    /**
     * Constructs a new WorldImpl instance.
     *
     * @param username the username of the player
     */
    public WorldImpl(final String username) {
        this.rooms = RoomImpl.createRooms();
        final var currentRoom = rooms.stream()
            .filter(x -> "bedroom".equals(x.getName()))
            .findFirst()
            .get();
        this.player = new PlayerImpl(playerPosition, username, Dimensions.TILE, currentRoom);
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
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "The rooms and the player are mutable")
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
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "The rooms are mutable")
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
        final LockedUnpickable mirror = (LockedUnpickable) this.getRooms()
            .stream()
            .filter(room -> "final".equals(room.getName()))
            .findFirst()
            .get()
            .getGameObjects()
            .stream()
            .filter(x -> "Mirror".equals(x.getName()))
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
    public void addRoom(final Room room) {
        Objects.requireNonNull(room, "Room must not be null");
        this.rooms.add(room);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void movePlayer(final Movement movement) {
        Objects.requireNonNull(movement, "Movement must not be null");

        final var playerPosition = this.player.getPosition();
        final var position = new Point2D(playerPosition.x() + movement.getX(), playerPosition.y() + movement.getY());
        final var collidingObject = this.collisionDetector.collisions(
            position, this.player.getDimensions(), 
            this.getCurrentRoom().getGameObjects()
        );

        if (collidingObject.isEmpty()) {
            this.player.move(movement);
        } 
        this.setCollidingObject(collidingObject);
    }

    private void setCollidingObject(final Optional<GameObject> collidingObject) {
        this.collidingObject = collidingObject;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "The player is mutable")
    public Player getPlayer() {
        return this.player;
    }
}
