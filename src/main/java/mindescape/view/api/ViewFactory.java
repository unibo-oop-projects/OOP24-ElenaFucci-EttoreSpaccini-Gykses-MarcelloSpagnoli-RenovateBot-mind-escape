package mindescape.view.api;

import mindescape.controller.api.Controller;

public interface ViewFactory {

    View createWorldView(Controller controller);

    View createMenuView(Controller controller);

    View createInventoryView(Controller controller);

    View createPuzzleView(Controller controller);

    View createCalendarView(Controller controller);

    View createDrawerView(Controller controller);

    View createComputerView(Controller controller);

    View createWardrobeView(Controller controller);

    View createMirrorView(Controller controller);

}