package mindescape.model.world.rooms.impl;

/**
 * Enum representing the names of different rooms in the application.
 * Each enum constant has an associated string name.
 */
public enum RoomNames {

    /**
     * Enum constant representing the bedroom.
     */
    BEDROOM("bedroom"),

    /**
     * Enum constant representing the office.
     */
    OFFICE("office"),
    /**
     *  Enum constant representing the archive.
     */
    ARCHIVE("archive"),

    /**
     * Enum constant representing the final room.
     */
    FINAL("final"),

    /**
     *  Enum constant representing the canteen.
     */
    CANTEEN("canteen");

    private final String name;

    /**
     * Constructs a RoomNames enum constant with the specified name.
     *
     * @param name the name of the room
     */
    RoomNames(final String name) {
        this.name = name;
    }

    /**
     * Retrieves the name of the room.
     *
     * @return the name of the room as a String.
     */
    public String getName() {
        return name;
    }
}
