package mindescape.view;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import mindescape.controller.inventory.InventoryControllerImpl;
import mindescape.model.inventory.impl.InventoryImpl;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.items.interactable.api.Pickable;
import mindescape.model.world.player.api.Player;
import java.util.stream.IntStream;

/**
* A test class for the inventory view.
 */
final class InventoryViewTest {

    private static final int NUM_ITEMS = 20;

    private InventoryViewTest() {
    }

    /**
     * The main method of the test class.
     *
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> {
            final InventoryImpl inventory = new InventoryImpl();
            final InventoryControllerImpl controller = new InventoryControllerImpl(inventory, null);

            IntStream.range(0, NUM_ITEMS).forEach(i -> {
                inventory.addItems(new Pickable() {
                    @Override
                    public String getName() {
                        return "Hammer";
                    }

                    @Override
                    public String getDescription() {
                        return "A sturdy hammer.";
                    }

                    @Override
                    public void onAction(final Player player) {
                        throw new UnsupportedOperationException("Unimplemented method 'onAction'");
                    }

                    @Override
                    public Point2D getPosition() {
                        throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
                    }

                    @Override
                    public void setPosition(final Point2D position) {
                        throw new UnsupportedOperationException("Unimplemented method 'setPosition'");
                    }

                    @Override
                    public Dimensions getDimensions() {
                        throw new UnsupportedOperationException("Unimplemented method 'getDimensions'");
                    }

                    @Override
                    public int getId() {
                        throw new UnsupportedOperationException("Unimplemented method 'getId'");
                    }
                });
            });

            controller.start();

            final JFrame frame = new JFrame("Inventory Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(controller.getPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
