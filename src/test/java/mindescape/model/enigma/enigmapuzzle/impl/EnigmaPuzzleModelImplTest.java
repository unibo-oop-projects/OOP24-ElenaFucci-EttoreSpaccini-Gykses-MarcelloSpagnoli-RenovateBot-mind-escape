package mindescape.model.enigma.enigmapuzzle.impl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

/**
 * Unit tests for the EnigmaPuzzleModelImpl class.
 */
final class EnigmaPuzzleModelImplTest {
    private EnigmaPuzzleModelImpl puzzle;

    /**
     * Sets up the test environment before each test method is executed.
     * Initializes an instance of EnigmaPuzzleModelImpl with a 3x3 grid and the name "Test Puzzle".
     */
    @BeforeEach
    void setUp() {
        puzzle = new EnigmaPuzzleModelImpl(3, 3, "Test Puzzle");
    }

    /**
     * Tests the getRows method of the EnigmaPuzzleModelImpl class.
     * Verifies that the number of rows returned by the method is 3.
     */
    @Test
    void testGetRows() {
        assertEquals(3, puzzle.getRows());
    }

    /**
     * Tests the getCols method of the EnigmaPuzzleModelImpl class.
     * Verifies that the number of columns returned by the method is 3.
     */
    @Test
    void testGetCols() {
        assertEquals(3, puzzle.getCols());
    }
    /**
     * Tests the getPiece method of the EnigmaPuzzleModelImpl class.
     * Verifies that the piece at position (0, 0) is 0 and the piece at position (2, 2) is 8.
     */
    @Test
    void testGetPiece() {
        assertEquals(0, puzzle.getPiece(0, 0));
        assertEquals(8, puzzle.getPiece(2, 2));
    }
    /**
     * Tests the getName method of the EnigmaPuzzleModelImpl class.
     * Verifies that the name of the puzzle is "Test Puzzle".
     */
    @Test
    void testGetName() {
        assertEquals("Test Puzzle", puzzle.getName());
    }
    /**
     * Tests the shufflePieces method of the EnigmaPuzzleModelImpl class.
     * Verifies that the pieces are shuffled after calling the method.
     */
    @Test
    void testShufflePieces() {
        final Integer[][] originalPieces = puzzle.getPieces();
        puzzle.shufflePieces();
        final Integer[][] shuffledPieces = puzzle.getPieces();
        assertFalse(Arrays.deepEquals(originalPieces, shuffledPieces));
    }
    /**
     * Tests the getPieces method of the EnigmaPuzzleModelImpl class.
     * Verifies that the pieces are returned correctly.
     */
    @Test
    void testGetPieces() {
        final Integer[][] pieces = puzzle.getPieces();
        assertEquals(0, pieces[0][0]);
        assertEquals(8, pieces[2][2]);
    }
    /**
     * Tests the swapPieces method of the EnigmaPuzzleModelImpl class.
     * Verifies that the pieces at positions (0, 0) and (2, 2) are swapped.
     */
    @Test
    void testSwapPieces() {
        puzzle.swapPieces(0, 8);
        assertEquals(8, puzzle.getPiece(0, 0));
        assertEquals(0, puzzle.getPiece(2, 2));
    }
    /**
     * Tests the isSolved method of the EnigmaPuzzleModelImpl class.
     * Verifies that the puzzle is solved when the pieces are in the correct order.
     */
    @Test
    void testIsSolved() {
        puzzle.swapPieces(0, 1);
        assertFalse(puzzle.isSolved());
    }
    /**
     * Tests the hit method of the EnigmaPuzzleModelImpl class.
     * Verifies that the piece at position (0, 0) is swapped with the piece at position (0, 1).
     */
    @Test
    void testHit() {
        assertFalse(puzzle.hit(0));
        assertTrue(puzzle.hit(1));
        assertEquals(1, puzzle.getPiece(0, 0));
        assertEquals(0, puzzle.getPiece(0, 1));
    }
}
