package mindescape.view.world;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Map;
import javax.swing.JPanel;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.player.api.Player;
import mindescape.model.world.rooms.api.Room;
import mindescape.view.api.AnimatedPlayerRenderer;
import mindescape.view.api.RoomRenderer;
import mindescape.view.api.WorldView;
import mindescape.view.utils.ImageTransformer;
import mindescape.view.utils.ViewUtils;

/**
 * Implementation of the WorldView.
 */
public final class WorldViewImpl implements WorldView, KeyListener {

    private static final int TILE_DIMENSION = (int) Dimensions.TILE.width();
    private final AnimatedPlayerRenderer player;
    private final ImageTransformer transformer = new ImageTransformer();
    private final JPanel panel;
    private final InputManager inputManager = new InputManager();
    private final RoomRenderer renderer;

    /**
     * Constructor for WorldViewImpl.
     *
     * @param currentRoom the current room
     */
    public WorldViewImpl(final Room currentRoom) {
        this.panel = createPanel();
        player = new PlayerRendererImpl(getPlayer(currentRoom).getPosition());
        renderer = new RoomRendererImpl(currentRoom);
    }

    @Override
    public void draw(final Room currentRoom) {
        player.setPosition(getPlayer(currentRoom).getPosition());
        this.panel.repaint();
    }

    @SuppressFBWarnings(value = "EI_EXPOSE_REP",
        justification = "it is intended, since roomrenderer writes on it")
    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        inputManager.pressedInput(e.getKeyCode());
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        inputManager.releasedInput(e.getKeyCode());
    }

    @Override
    public Map<Integer, Boolean> getKeyState() {
        return inputManager.getKeyState();
    }

    @Override
    public void clearInput() {
        inputManager.clearInput();
    }

    private double getScalingFactor() {
        final double tileScaledDim = this.panel.getHeight() 
            / ((double) renderer.getRoomImage().getHeight() / TILE_DIMENSION);
        return tileScaledDim / TILE_DIMENSION;
    }

    private Player getPlayer(final Room currentRoom) {
        return (Player) currentRoom.getGameObjects().stream().filter(x -> x instanceof Player).findAny().get();
    }


    private JPanel createPanel() {
        final JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(final Graphics g) {
                super.paintComponent(g);
                final double scaling = getScalingFactor();
                final BufferedImage image = transformer.adapt(renderer.getRoomImage(), scaling);
                final int offset = (this.getWidth() - image.getWidth()) / 2;
                g.drawImage(image, offset, 0, this);
                player.draw(g, offset, scaling, inputManager.getKeyState());
            }
        };
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.addKeyListener(this);
        panel.setBackground(ViewUtils.Style.PANEL_COLOR);
        return panel;
    }

    @Override
    public void updateRoomImage(final Room currentRoom) {
        renderer.updateRoomImage(currentRoom);
    }

}

