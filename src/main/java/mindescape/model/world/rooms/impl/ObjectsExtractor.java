package mindescape.model.world.rooms.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.tiledreader.FileSystemTiledReader;
import org.tiledreader.TiledMap;
import org.tiledreader.TiledObjectLayer;

import mindescape.model.enigma.api.EnigmaFactory;
import mindescape.model.world.core.api.Dimensions;
import mindescape.model.world.core.api.GameObject;
import mindescape.model.world.core.api.Point2D;
import mindescape.model.world.items.interactable.api.InteractableFactory;
import mindescape.model.world.items.interactable.impl.InteractableFactoryImpl;
import mindescape.model.world.items.interactable.impl.RewardFactory;
import mindescape.model.world.items.noninteractable.impl.NonInteractableImpl;
import mindescape.model.world.rooms.api.Room;

/**
 * Utility class to extract and create objects from a room file.
 */
public class ObjectsExtractor {
    /**
     * Return the set of GameObject of the room.
     * @param roomPath path to xml file describing the room
     * @return set of game objects
     */
    Set<GameObject> extractfrom(String roomPath) {
        Set<GameObject> gameObjects = new HashSet<>();
        InteractableFactory factory = new InteractableFactoryImpl();
        EnigmaFactory enigmas = new EnigmaFactory();
        RewardFactory rewards = new RewardFactory();
        TiledMap map = new FileSystemTiledReader().getMap(roomPath);
        List<TiledObjectLayer> layers = getObjectLayers(map)
            .stream().filter(layer -> !layer.getName().equals("Doors"))
            .toList();
        for (TiledObjectLayer layer : layers) {
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
                    case "Pickable":
                        gameObjects.add(factory.createPickable(object.getName(),
                            position,
                            dimensions,
                            (String) object.getProperties().get("Description"),
                            (Integer) object.getProperties().get("ID")));
                        break;
                    case "Unpickable":
                        gameObjects.add(factory.createUnpickable(object.getName(), position, dimensions,
                            rewards.getReward((String) object.getProperties().get("Reward"))));
                        break;
                    default:
                        break;
                }
            }); 
        }
        return gameObjects;
    }

    Set<GameObject> addDoors(String roomPath, Set<Room> rooms) {
        Set<GameObject> doors = new HashSet<>();
        EnigmaFactory enigmas = new EnigmaFactory();
        InteractableFactory factory = new InteractableFactoryImpl();
        TiledMap map = new FileSystemTiledReader().getMap(roomPath);
        List<TiledObjectLayer> doorLayers = getObjectLayers(map)
            .stream().filter(layer -> layer.getName().equals("Doors"))
            .toList();
        for (TiledObjectLayer doorLayer : doorLayers) {
            doorLayer.getObjects().forEach(object -> {
                Optional<Point2D> position = Optional.of(new Point2D(object.getX(), object.getY()));
                Dimensions dimensions = new Dimensions(object.getWidth(), object.getHeight());
                switch (object.getType()) {   
                    case "DoorLockedWithEnigma":
                        doors.add(factory.createDoorLockedWithEnigma(object.getName(), position, dimensions,
                            enigmas.getEnigma((String) object.getProperties().get("Enigma")),
                            rooms.stream()
                            .filter(x -> x.getName().equals((String) object.getProperties().get("Destination")))
                            .findFirst()
                            .get()));
                        break;
                    case "DoorLockedWithPickable":
                    doors.add(factory.createDoorLockedWithPickable(object.getName(), position, dimensions,
                        (Integer) object.getProperties().get("keyItem_id"),
                        rooms.stream()
                        .filter(x -> x.getName().equals((String) object.getProperties().get("Destination")))
                        .findFirst()
                        .get()));
                        break;
                    case "SimpleDoor":
                        factory.createSimpleDoor(object.getName(), position, dimensions, 
                        rooms.stream()
                        .filter(x -> x.getName().equals((String) object.getProperties().get("Destination")))
                        .findFirst()
                        .get());
                    default:
                        break;
                }
            });
        }
        return doors;
    }

    private List<TiledObjectLayer> getObjectLayers(TiledMap map) {
        return map.getNonGroupLayers().stream()
            .filter(layer -> layer instanceof TiledObjectLayer)
            .map(layer -> (TiledObjectLayer) layer)
            .collect(Collectors.toList());
    }

}

