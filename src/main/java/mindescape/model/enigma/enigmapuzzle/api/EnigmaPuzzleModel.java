package mindescape.model.enigma.enigmapuzzle.api;

import mindescape.model.enigma.api.Enigma;

/**
 * The EnigmaPuzzleModel interface represents a model for an enigma puzzle.
 * It extends the Enigma interface, inheriting its methods and properties.
 * Implementations of this interface should provide the specific logic and 
 * data structures required to represent and manipulate an enigma puzzle.
 */
public interface EnigmaPuzzleModel extends Enigma {
    /**
     * Returns the number of rows in the enigma puzzle.
     *
     * @return the number of rows
     */
    int getRows();
    /**
     * Returns the number of columns in the enigma puzzle.
     *
     * @return the number of columns
     */
    int getCols();
    /**
     * Retrieves the puzzle piece located at the specified row and column.
     *
     * @param row the row index of the puzzle piece
     * @param col the column index of the puzzle piece
     * @return the puzzle piece at the specified row and column, or null if the piece does not exist
     */
    Integer getPiece(int row, int col);
    /**
     * Shuffles the pieces of the enigma puzzle to create a randomized configuration.
     * This method ensures that the puzzle pieces are rearranged in a random order,
     * providing a new challenge each time it is called.
     */
    void shufflePieces();
    /**
     * Retrieves the pieces of the enigma puzzle.
     *
     * @return a 2D array of Integer representing the pieces of the enigma puzzle.
     */
    Integer[][] getPieces();
    /**
     * Swaps the positions of two pieces in the puzzle.
     *
     * @param index1 the index of the first piece to be swapped
     * @param index2 the index of the second piece to be swapped
     */
    void swapPieces(Integer index1, Integer index2); 
}
