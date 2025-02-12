package mindescape.model.enigma.enigmapuzzle.impl;


import java.awt.*;
import java.io.Serializable;
import java.util.*;
import java.util.List;

import mindescape.model.api.Model;
import mindescape.model.enigma.enigmapuzzle.api.EnigmaPuzzleModel;

public class EnigmaPuzzleModelImpl implements EnigmaPuzzleModel, Serializable, Model {

    private final static long serialVersionUID = 1L;
    private final int rows;
    private final int cols;
    private Integer[][] pieces;
    private String puzzleName;
    private Integer clickedButtonIndex = null;

    /**
     * Constructs an EnigmaPuzzleModelImpl with the specified number of rows and columns,
     * the original image, and the puzzle name.
     *
     * @param rows the number of rows in the puzzle
     * @param cols the number of columns in the puzzle
     * @param image the original image to be divided into puzzle pieces
     * @param puzzleName the name of the puzzle
     */
    public EnigmaPuzzleModelImpl(int rows, int cols, String puzzleName) {
        this.rows = rows;
        this.cols = cols;
        this.puzzleName = puzzleName;
        this.pieces = new Integer[rows][cols];
        for (int i = 0; i < rows * cols; i++) {
            pieces[i / cols][i % cols] = i;
        }
        shufflePieces();
    }
    

    /**
     * Returns the number of rows in the enigma puzzle.
     *
     * @return the number of rows
     */
    public int getRows() {
        return this.rows;
    }

    /**
     * Returns the number of columns in the enigma puzzle.
     *
     * @return the number of columns
     */
    public int getCols() {
        return this.cols;
    }

    /**
     * Retrieves the 2D array of image pieces that make up the enigma puzzle.
     *
     * @return a 2D array of {@link Image} objects representing the pieces of the puzzle.
     */
    public Integer[][] getPieces() {
        return pieces;
    }

    /**
     * Retrieves the image piece located at the specified row and column.
     *
     * @param row the row index of the piece to retrieve
     * @param col the column index of the piece to retrieve
     * @return the image piece at the specified row and column
     */
    public Integer getPiece(int row, int col) {
        return pieces[row][col];
    }

    /**
     * Retrieves the name of the puzzle.
     *
     * @return the name of the puzzle as a String.
     */
    public String getName() {
        return puzzleName;
    }
    /**
     * Shuffles the pieces of the puzzle randomly.
     * This method first collects all pieces into a list, shuffles the list,
     * and then reassigns the shuffled pieces back to the original 2D array.
     */
    public void shufflePieces() {
        List<Integer> shuffledPieces = new ArrayList<>();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                shuffledPieces.add(pieces[i][j]);
            }
        }
        Collections.shuffle(shuffledPieces);
        shuffledPieces.stream().forEach(x -> System.out.println(x));
        int index = 0;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                pieces[i][j] = shuffledPieces.get(index++);
            }
        }
    }

    /**
     * Swaps the positions of two pieces in the puzzle.
     *
     * @param firstRow the row index of the first piece
     * @param firstCol the column index of the first piece
     * @param secondRow the row index of the second piece
     * @param secondCol the column index of the second piece
     */
    public void swapPieces(Integer index1, Integer index2) {
        Integer firstRow = index1 / this.cols;
        Integer firstCol = index1 % this.cols;
        Integer secondRow = index2 / this.cols;
        Integer secondCol = index2 % this.cols;
        Integer temp = pieces[firstRow][firstCol];
        pieces[firstRow][firstCol] = pieces[secondRow][secondCol];
        pieces[secondRow][secondCol] = temp;
    }
    

    /**
     * Checks if the enigma puzzle is solved.
     * 
     * This method iterates through each piece of the puzzle and compares it with the corresponding
     * subimage of the original image. If all pieces match their respective subimages, the puzzle is
     * considered solved.
     *
     * @return true if the puzzle is solved, false otherwise.
     */
    public boolean isSolved() {
        int counter = 0;
        for (int i = 0; i < this.cols; i++) {
            for (int j = 0; j < this.rows; j++) {
                if(pieces[j][i] == counter) {
                    counter++;
                } else {
                    return false;
                }     
            }
        }
        return true;
    }
     
    /**
     * Processes a hit action on the enigma puzzle.
     *
     * @param value the value to be processed by the hit action
     * @return true if the hit action is successful, false otherwise
     * @throws UnsupportedOperationException if the method is not implemented
     */
    @Override
    public boolean hit(Object value) {
        Integer hitIndex = (Integer) value;
        if (clickedButtonIndex == null) {
            clickedButtonIndex = hitIndex;
            return false;
        } else {
            swapPieces(clickedButtonIndex, hitIndex);
            clickedButtonIndex = null;
            return true;
        }
        
    }
}

