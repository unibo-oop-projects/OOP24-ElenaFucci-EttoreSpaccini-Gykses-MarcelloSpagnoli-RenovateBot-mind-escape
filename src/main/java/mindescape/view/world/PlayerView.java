package mindescape.view.world;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import mindescape.controller.core.api.UserInput;
import mindescape.model.world.core.api.Point2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class that represents the player view.
 */
public final class PlayerView {
    private static final Map<Integer, UserInput> KEY_MAPPER = Map.of(
        KeyEvent.VK_W, UserInput.UP,
        KeyEvent.VK_S, UserInput.DOWN,
        KeyEvent.VK_A, UserInput.LEFT,
        KeyEvent.VK_D, UserInput.RIGHT
    );
    private static final int SPRITE_SIZE = 16;
    private static final int SPRITE_SHEET_COLUMNS = 8;
    private static final int SPRITE_SHEET_WIDTH = SPRITE_SIZE * SPRITE_SHEET_COLUMNS;
    private static final int SPRITE_SHEET_HEIGHT = SPRITE_SIZE;
    private static final int SPRITES_PER_MOVEMENT = 2;
    private static final int TIMER_DELAY = 300;

    private int spriteIndex;
    private final Map<UserInput, List<BufferedImage>> spriteMapper = new HashMap<>();
    private BufferedImage currentSprite;
    private final Timer timer;
    private int x;
    private int y;

    /**
     * Constructor for PlayerView, initializing position and loading sprites.
     * 
     * @param pos The initial position of the player
     */
    public PlayerView(final Point2D pos) {
        x = (int) pos.x();
        y = (int) pos.y();
        BufferedImage image;
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("playertiles/player.png")) {
            if (is == null) {
                throw new IOException("Resource not found: playertiles/player.png");
            }
            image = ImageIO.read(is);
        } catch (final IOException e) {
            image = new BufferedImage(SPRITE_SHEET_WIDTH, SPRITE_SHEET_HEIGHT, BufferedImage.TYPE_INT_ARGB);
            Graphics g = image.createGraphics();
            g.setColor(Color.RED);
            g.fillRect(0, 0, SPRITE_SHEET_WIDTH, SPRITE_SHEET_HEIGHT);
            g.dispose();
        }
        spriteMapper.put(UserInput.DOWN, List.of(
            image.getSubimage(0, 0, SPRITE_SIZE, SPRITE_SIZE),
            image.getSubimage(SPRITE_SIZE, 0, SPRITE_SIZE, SPRITE_SIZE))
        );
        spriteMapper.put(UserInput.UP, List.of(
            image.getSubimage(SPRITE_SIZE * 2, 0, SPRITE_SIZE, SPRITE_SIZE),
            image.getSubimage(SPRITE_SIZE * 3, 0, SPRITE_SIZE, SPRITE_SIZE))
        );
        spriteMapper.put(UserInput.RIGHT, List.of(
            image.getSubimage(SPRITE_SIZE * 4, 0, SPRITE_SIZE, SPRITE_SIZE),
            image.getSubimage(SPRITE_SIZE * 5, 0, SPRITE_SIZE, SPRITE_SIZE))
        );
        spriteMapper.put(UserInput.LEFT, List.of(
            image.getSubimage(SPRITE_SIZE * 6, 0, SPRITE_SIZE, SPRITE_SIZE),
            image.getSubimage(SPRITE_SIZE * 7, 0, SPRITE_SIZE, SPRITE_SIZE))
        );
        currentSprite = spriteMapper.get(UserInput.DOWN).get(spriteIndex);
        timer = new Timer(TIMER_DELAY, x -> {
            spriteIndex = (spriteIndex + 1) % SPRITES_PER_MOVEMENT;
        });
        timer.start();
    }

    /**
     * Updates the player's position.
     * 
     * @param pos The new position of the player
     */
    public void setPosition(final Point2D pos) {
        x = (int) pos.x();
        y = (int) pos.y();
    }

    /**
     * Draws the player sprite at the given position.
     * 
     * @param g The graphics context
     * @param offset The offset value for rendering
     * @param scaling The scaling factor
     * @param keys The map of pressed keys
     */
    public void draw(final Graphics g, final int offset, final double scaling, final Map<Integer, Boolean> keys) {
        setCurrentSprite(keys);
        g.drawImage(currentSprite, (int) ((x * scaling) + offset),
            (int) (y * scaling),
            (int) (scaling * SPRITE_SIZE),
            (int) (scaling * SPRITE_SIZE),
            null);
    }

    private void setCurrentSprite(final Map<Integer, Boolean> keys) {
        for (final Map.Entry<Integer, Boolean> entry : keys.entrySet()) {
            if (KEY_MAPPER.get(entry.getKey()) != null && entry.getValue()) {
                currentSprite = spriteMapper.get(KEY_MAPPER.get(entry.getKey())).get(spriteIndex);
            }
        }
    }
}
