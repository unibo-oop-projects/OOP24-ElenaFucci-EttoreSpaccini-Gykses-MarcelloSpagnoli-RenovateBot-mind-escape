package mindescape.view;

import javax.swing.JFrame;

import mindescape.model.world.rooms.api.Room;
import mindescape.model.world.rooms.impl.RoomImpl;
import mindescape.view.world.WorldViewImpl;

public class WorldViewImplTest extends JFrame{

    WorldViewImpl view = new WorldViewImpl(null);
    Room bedroom = RoomImpl.createRooms().stream().filter(x -> x.getName().equals("office")).findAny().get();

    public WorldViewImplTest() {
        setSize(getMaximumSize());
        this.getContentPane().add(view);
        view.draw(bedroom);
        this.setVisible(true);
    }
}