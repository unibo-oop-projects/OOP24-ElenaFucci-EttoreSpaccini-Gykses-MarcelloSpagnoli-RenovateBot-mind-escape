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
import java.io.File;
import java.io.IOException;

/**
 * Class that represents the player view.
 */
public class PlayerView {
    private static final Map<Integer, UserInput> KEY_MAPPER = Map.of(
        KeyEvent.VK_W, UserInput.UP,
        KeyEvent.VK_S, UserInput.DOWN,
        KeyEvent.VK_A, UserInput.LEFT,
        KeyEvent.VK_D, UserInput.RIGHT
    );
    private final static int SPRITE_SHEET_HEIGHT = 16;
    private final static int SPRITE_SHEET_WIDTH = 16*8;
    private final static int SPRITES_PER_MOVEMENT = 2;
    private int spriteIndex = 0;
    private final Map<UserInput, List<BufferedImage>> spriteMapper = new HashMap<>();
    private BufferedImage currentSprite;
    private final Timer timer;
    private int x;
    private int y;

    public PlayerView(final Point2D pos) {
        x = (int) pos.x();
        y = (int) pos.y();
        BufferedImage image;
        try {
            //TODO: use class loader
            image = ImageIO.read(new File("playertiles/player.png"));
        } catch (IOException e) {
            image = new BufferedImage(SPRITE_SHEET_WIDTH, SPRITE_SHEET_HEIGHT, BufferedImage.TYPE_INT_ARGB);
            Graphics g = image.createGraphics();
            g.setColor(Color.RED);
            g.fillRect(0, 0, SPRITE_SHEET_WIDTH, SPRITE_SHEET_HEIGHT);
            g.dispose();
        }
        spriteMapper.put(UserInput.DOWN, List.of(
            image.getSubimage(0, 0, 16, 16),
            image.getSubimage(16, 0, 16, 16))
        );
        spriteMapper.put(UserInput.UP, List.of(
            image.getSubimage(32, 0, 16, 16),
            image.getSubimage(48, 0, 16, 16))
        );
        spriteMapper.put(UserInput.RIGHT, List.of(
            image.getSubimage(64, 0, 16, 16),
            image.getSubimage(80, 0, 16, 16))
        );
        spriteMapper.put(UserInput.LEFT, List.of(
            image.getSubimage(96, 0, 16, 16),
            image.getSubimage(112, 0, 16, 16))
        );
        currentSprite = spriteMapper.get(UserInput.DOWN).get(spriteIndex);
        timer = new Timer(300, x -> {
            spriteIndex = (spriteIndex + 1) % SPRITES_PER_MOVEMENT;
        });
        timer.start();
    }

    public void setPosition(Point2D pos) {
        x = (int) pos.x();
        y = (int) pos.y();
    }

    public void draw(final Graphics g, final int offset, final double scaling, final Map<Integer, Boolean> keys) {
        setCurrentSprite(keys);
        g.drawImage(currentSprite, (int)((x * scaling) + offset),
            (int) (y * scaling),
            (int)(scaling * 16),
            (int)(scaling * 16),
            null);
    }

    private void setCurrentSprite(final Map<Integer, Boolean> keys){
        for (Map.Entry<Integer, Boolean> entry : keys.entrySet()) {
            if (KEY_MAPPER.get(entry.getKey()) != null && entry.getValue()) { // Se il tasto è premuto
                currentSprite = spriteMapper.get(KEY_MAPPER.get(entry.getKey())).get(spriteIndex);
            }
        }
    }
}
