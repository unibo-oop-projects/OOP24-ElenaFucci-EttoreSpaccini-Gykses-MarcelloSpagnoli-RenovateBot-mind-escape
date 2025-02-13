package mindescape.view.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.Timer;

import mindescape.controller.core.api.KeyMapper;
import mindescape.controller.core.api.UserInput;
import mindescape.model.world.core.api.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class that represents the player view.
 */
public final class PlayerView {

    private static final int SPRITE_SIZE = 16;
    private static final int SPRITE_SHEET_COLUMNS = 8;
    private static final int SPRITE_SHEET_WIDTH = SPRITE_SIZE * SPRITE_SHEET_COLUMNS;
    private static final int SPRITE_SHEET_HEIGHT = SPRITE_SIZE;
    private static final int SPRITES_PER_MOVEMENT = 2;
    private static final int TIMER_DELAY = 300;
    private static final int DOWN_POSITION = 0;
    private static final int UP_POSITION = 2;
    private static final int RIGHT_POSITION = 4;
    private static final int LEFT_POSITION = 6;


    private int spriteIndex;
    private final Map<UserInput, List<BufferedImage>> spriteMapper = new EnumMap<>(UserInput.class);
    private BufferedImage currentSprite;
    private final Timer timer;
    private int x;
    private int y;
    private final Map<Integer, UserInput> keyMapper = KeyMapper.getKeyMap();

    /**
     * Constructor for PlayerView, initializing position and loading sprites.
     * 
     * @param pos The initial position of the player
     */
    public PlayerView(final Point2D pos) {
        this.spriteIndex = 0;
        this.x = (int) pos.x();
        this.y = (int) pos.y();
        keyMapper.remove(KeyEvent.VK_I);
        keyMapper.remove(KeyEvent.VK_E);
        BufferedImage image;
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("playertiles/player.png")) {
            image = ImageIO.read(is);
        } catch (final IOException e) {
            image = new BufferedImage(SPRITE_SHEET_WIDTH, SPRITE_SHEET_HEIGHT, BufferedImage.TYPE_INT_ARGB);
            final Graphics g = image.createGraphics();
            g.setColor(Color.RED);
            g.fillRect(0, 0, SPRITE_SHEET_WIDTH, SPRITE_SHEET_HEIGHT);
            g.dispose();
        }
        spriteMapper.put(UserInput.DOWN, List.of(
            image.getSubimage(DOWN_POSITION, 0, SPRITE_SIZE, SPRITE_SIZE),
            image.getSubimage((DOWN_POSITION + 1) * SPRITE_SIZE, 0, SPRITE_SIZE, SPRITE_SIZE))
        );
        spriteMapper.put(UserInput.UP, List.of(
            image.getSubimage(UP_POSITION * SPRITE_SIZE, 0, SPRITE_SIZE, SPRITE_SIZE),
            image.getSubimage((UP_POSITION + 1) * SPRITE_SIZE, 0, SPRITE_SIZE, SPRITE_SIZE))
        );
        spriteMapper.put(UserInput.RIGHT, List.of(
            image.getSubimage(RIGHT_POSITION * SPRITE_SIZE, 0, SPRITE_SIZE, SPRITE_SIZE),
            image.getSubimage((RIGHT_POSITION + 1) * SPRITE_SIZE, 0, SPRITE_SIZE, SPRITE_SIZE))
        );
        spriteMapper.put(UserInput.LEFT, List.of(
            image.getSubimage(LEFT_POSITION * SPRITE_SIZE, 0, SPRITE_SIZE, SPRITE_SIZE),
            image.getSubimage((LEFT_POSITION + 1) * SPRITE_SIZE, 0, SPRITE_SIZE, SPRITE_SIZE))
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
            if (keyMapper.get(entry.getKey()) != null && entry.getValue()) {
                currentSprite = spriteMapper.get(keyMapper.get(entry.getKey())).get(spriteIndex);
            }
        }
    }
}
