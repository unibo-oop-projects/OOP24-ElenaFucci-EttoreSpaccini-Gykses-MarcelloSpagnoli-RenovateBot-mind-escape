package mindescape.controller.core.impl;

import mindescape.controller.core.api.Controller;
import mindescape.controller.core.api.ControllerName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;


public class ControllerMapImplTest {

    private ControllerMapImpl controllerMap;
    private Controller testController;
    private ControllerName testControllerName;

    @BeforeEach
    public void setUp() {
        controllerMap = new ControllerMapImpl();
        // testController = new TestController("testController");
        // testControllerName = new TestControllerName("testController");
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(controllerMap);
        assertTrue(controllerMap.findController(testControllerName) == null);
    }

    @Test
    public void testConstructorWithMap() {
        Map<String, Controller> initialMap = new HashMap<>();
        initialMap.put("testController", testController);
        ControllerMapImpl controllerMapWithInitial = new ControllerMapImpl(initialMap);

        assertEquals(testController, controllerMapWithInitial.findController(testControllerName));
    }

    @Test
    public void testAddController() {
        controllerMap.addController(testController);
        assertEquals(testController, controllerMap.findController(testControllerName));
    }

    @Test
    public void testRemoveController() {
        controllerMap.addController(testController);
        controllerMap.removeController(testControllerName);
        assertNull(controllerMap.findController(testControllerName));
    }

    @Test
    public void testClear() {
        controllerMap.addController(testController);
        controllerMap.clear();
        assertNull(controllerMap.findController(testControllerName));
    }

    /* private static class TestControllerName implements ControllerName, Controller {
        private final String name;

        public TestControllerName(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    } */
}