package mindescape.controller.core.api;

public interface ControllerBuilder {

    void buildMenu();

    void buildWorld();

    void buildPuzzle();

    void buildEnigmaFirstDoor();

    void buildCalendar();

    void buildDrawer();

    void buildComputer();

    void buildWardrobe();

    ControllerMap getResult();
}
