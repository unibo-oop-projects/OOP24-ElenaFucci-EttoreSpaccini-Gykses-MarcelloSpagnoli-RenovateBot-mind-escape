package mindescape.model.enigma.enigmapuzzle.impl;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

import mindescape.model.enigma.enigmapuzzle.api.EnigmaPuzzleModel;

public class EnigmaPuzzleModelImpl implements EnigmaPuzzleModel {

    private final int ROWS;
    private final int COLS;
    private Image[][] pieces;
    private Image originalImage;
    private String puzzleName;

    /**
     * Constructs an EnigmaPuzzleModelImpl with the specified number of rows and columns,
     * the original image, and the puzzle name.
     *
     * @param rows the number of rows in the puzzle
     * @param cols the number of columns in the puzzle
     * @param image the original image to be divided into puzzle pieces
     * @param puzzleName the name of the puzzle
     */
    public EnigmaPuzzleModelImpl(int rows, int cols, Image image, String puzzleName) {
        this.ROWS = rows;
        this.COLS = cols;
        this.originalImage = image;
        this.puzzleName = puzzleName;
        this.pieces = new Image[ROWS][COLS];
    
        divideImageIntoPieces();
        shufflePieces();
    }
    

    /**
     * Returns the number of rows in the enigma puzzle.
     *
     * @return the number of rows
     */
    public int getRows() {
        return ROWS;
    }

    /**
     * Returns the number of columns in the enigma puzzle.
     *
     * @return the number of columns
     */
    public int getCols() {
        return COLS;
    }

    /**
     * Retrieves the 2D array of image pieces that make up the enigma puzzle.
     *
     * @return a 2D array of {@link Image} objects representing the pieces of the puzzle.
     */
    public Image[][] getPieces() {
        return pieces;
    }

    /**
     * Retrieves the image piece located at the specified row and column.
     *
     * @param row the row index of the piece to retrieve
     * @param col the column index of the piece to retrieve
     * @return the image piece at the specified row and column
     */
    public Image getPiece(int row, int col) {
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
     * Divides the original image into smaller pieces based on the specified number of rows and columns.
     * The method first creates a BufferedImage from the original image, then divides this BufferedImage
     * into smaller sub-images (pieces) and stores them in the pieces array.
     *
     * The width and height of each piece are calculated by dividing the original image's width and height
     * by the number of columns (COLS) and rows (ROWS) respectively.
     *
     * Preconditions:
     * - The originalImage must be initialized and not null.
     * - The COLS and ROWS constants must be defined and greater than 0.
     * - The pieces array must be initialized with dimensions [ROWS][COLS].
     *
     * Postconditions:
     * - The pieces array will be populated with sub-images of the original image.
     */
    private void divideImageIntoPieces() {
        int width = originalImage.getWidth(null);
        int height = originalImage.getHeight(null);
        int pieceWidth = width / COLS;
        int pieceHeight = height / ROWS;
    
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bufferedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, null);
        g.dispose();
    
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                pieces[i][j] = bufferedImage.getSubimage(j * pieceWidth, i * pieceHeight, pieceWidth, pieceHeight);
            }
        }
    }
    

    /**
     * Shuffles the pieces of the puzzle randomly.
     * This method first collects all pieces into a list, shuffles the list,
     * and then reassigns the shuffled pieces back to the original 2D array.
     */
    public void shufflePieces() {
        List<Image> shuffledPieces = new ArrayList<>();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                shuffledPieces.add(pieces[i][j]);
            }
        }
        Collections.shuffle(shuffledPieces);

        int index = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
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
    public void swapPieces(int firstRow, int firstCol, int secondRow, int secondCol) {
        Image temp = pieces[firstRow][firstCol];
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
        // Itera su tutte le righe (ROWS) e colonne (COLS)
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                // Estrai il pezzo della GUI e il corrispondente pezzo dell'immagine originale
                Image pieceFromGUI = pieces[i][j];
                BufferedImage pieceFromOriginalImage = ((BufferedImage)originalImage).getSubimage(
                    j * (originalImage.getWidth(null) / COLS),
                    i * (originalImage.getHeight(null) / ROWS),
                    originalImage.getWidth(null) / COLS,
                    originalImage.getHeight(null) / ROWS
                );
                
                // Verifica che il pezzo della GUI corrisponda esattamente a quello originale
                if (!imagesAreEqual((BufferedImage)pieceFromGUI, pieceFromOriginalImage)) {
                    return false;  // Se anche uno dei pezzi non corrisponde, ritorna false
                }
            }
        }
        return true;  // Se tutti i pezzi corrispondono, ritorna true
    }
    
    // Metodo per confrontare due BufferedImage pixel per pixel
    private boolean imagesAreEqual(BufferedImage img1, BufferedImage img2) {
        // Verifica se le immagini hanno la stessa dimensione
        if (img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight()) {
            return false;
        }
    
        // Confronta pixel per pixel
        for (int x = 0; x < img1.getWidth(); x++) {
            for (int y = 0; y < img1.getHeight(); y++) {
                if (img1.getRGB(x, y) != img2.getRGB(x, y)) {
                    return false;  // Se un pixel non corrisponde, ritorna false
                }
            }
        }
        return true;  // Se tutti i pixel corrispondono, ritorna true
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hit'");
    }
}

