package mindescape.model.world.rooms.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.tiledreader.FileSystemTiledReader;
import org.tiledreader.TiledMap;
import org.tiledreader.TiledObjectLayer;

import mindescape.model.enigma.impl.EnigmaFactory;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.GameObject;
import mindescape.model.world.core.api.Pair;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.items.interactable.api.InteractableFactory;
import mindescape.model.world.items.interactable.impl.InteractableFactoryImpl;
import mindescape.model.world.items.interactable.impl.RewardFactory;
import mindescape.model.world.items.noninteractable.impl.NonInteractableImpl;

/**
 * Utility class to extractand create objects from a room file.
 */
public class ObjectsExtractor {
    /**
     * Return the set of GameObject of the room.
     * @param roomPath path to xml file describing the room
     * @return set of game objects
     */
    Set<GameObject> extraxtfrom(String roomPath, String layerName) {
        Set<GameObject> gameObjects = new HashSet<>();
        InteractableFactory factory = new InteractableFactoryImpl();
        EnigmaFactory enigmas = new EnigmaFactory();
        RewardFactory rewards = new RewardFactory();
        TiledMap map = new FileSystemTiledReader().getMap(roomPath);
        TiledObjectLayer layer = getObjectLayers(map).stream()
            .filter(x -> x.getName().equals(layerName))
            .findFirst()
            .get();
        layer.getObjects().forEach(object -> {
            Optional<Point2D> position = Optional.of(new Point2D(object.getX(), object.getY()));
            Dimensions dimensions = new Dimensions(object.getWidth(), object.getHeight());
            switch (object.getType()) {
                case "NonInteractableImpl":
                    gameObjects.add(new NonInteractableImpl(position, object.getName(), dimensions));
                    break;
                case "UnpickableWithEnigma":
                    gameObjects.add(factory.createUnpickableWithEnigma(object.getName(), position, dimensions,
                        enigmas.getEnigma((String) object.getProperties().get("Enigma")),
                        rewards.getReward((String) object.getProperties().get("Reward"))));
                    break;
                case "DoorLockedWithPickable":
                    gameObjects.add(factory.createDoorLockedWithPickable(roomPath, position, dimensions,
                        factory.createPickable(roomPath, Optional.empty(), dimensions, roomPath),
                        new Pair<>(null, null)));
                    break;
                case "DoorLockedWithEnigma":
                    gameObjects.add(factory.createDoorLockedWithEnigma(roomPath, position, dimensions,
                        enigmas.getEnigma((String) object.getProperties().get("Enigma")),
                        new Pair<>(null, null)));
                    break;
                case "Pickable":
                    gameObjects.add(factory.createPickable(roomPath,
                        position,
                        dimensions,
                        (String) object.getProperties().get("Description")));
                    break;
                case "Unpickable":
                    gameObjects.add(factory.createUnpickable(roomPath, position, dimensions, null));
                    break;
                default:
                    break;
            }
        });
        return gameObjects;
    }

    private List<TiledObjectLayer> getObjectLayers(TiledMap map) {
        return map.getNonGroupLayers().stream()
            .filter(layer -> layer instanceof TiledObjectLayer)
            .map(layer -> (TiledObjectLayer) layer)
            .collect(Collectors.toList());
    }
}

