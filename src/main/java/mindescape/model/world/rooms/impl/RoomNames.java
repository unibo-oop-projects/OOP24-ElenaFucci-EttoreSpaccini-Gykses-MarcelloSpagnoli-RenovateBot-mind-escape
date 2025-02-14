package mindescape.model.world.rooms.impl;

public enum RoomNames {

    BEDROOM("bedroom"),

    OFFICE("office"),

    ARCHIVE("archive"),

    FINAL("final"), 

    CANTEEN("canteen");

    private final String name;

    RoomNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
