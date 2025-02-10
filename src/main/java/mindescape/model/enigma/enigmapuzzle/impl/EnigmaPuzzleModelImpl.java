package mindescape.model.enigma.enigmapuzzle.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mindescape.model.enigma.enigmapuzzle.api.EnigmaPuzzleModel;

public class EnigmaPuzzleModelImpl implements EnigmaPuzzleModel {

    private final int ROWS;
    private final int COLS;
    private Image[][] pieces;
    private Image originalImage;
    private String puzzleName;

    public EnigmaPuzzleModelImpl(int rows, int cols, Image image, String puzzleName) {
        this.ROWS = rows;
        this.COLS = cols;
        this.originalImage = image;
        this.puzzleName = puzzleName;
        this.pieces = new Image[ROWS][COLS];
        divideImageIntoPieces();
        shufflePieces();
    }

    public int getRows() {
        return ROWS;
    }

    public int getCols() {
        return COLS;
    }

    public Image[][] getPieces() {
        return pieces;
    }

    public Image getPiece(int row, int col) {
        return pieces[row][col];
    }

    public String getName() {
        return puzzleName;
    }

    private void divideImageIntoPieces() {
        int width = originalImage.getWidth(null);
        int height = originalImage.getHeight(null);
        int pieceWidth = width / COLS;
        int pieceHeight = height / ROWS;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                pieces[i][j] = ((BufferedImage)originalImage).getSubimage(j * pieceWidth, i * pieceHeight, pieceWidth, pieceHeight);
            }
        }
    }

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

    public void swapPieces(int firstRow, int firstCol, int secondRow, int secondCol) {
        Image temp = pieces[firstRow][firstCol];
        pieces[firstRow][firstCol] = pieces[secondRow][secondCol];
        pieces[secondRow][secondCol] = temp;
    }

    public List<Image> getShuffledPieces() {
        List<Image> shuffledPieces = new ArrayList<>();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                shuffledPieces.add(pieces[i][j]);
            }
        }
        return shuffledPieces;
    }

    public boolean isSolved() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (!pieces[i][j].equals(((BufferedImage)originalImage).getSubimage(j * (originalImage.getWidth(null) / COLS), i * (originalImage.getHeight(null) / ROWS), originalImage.getWidth(null) / COLS, originalImage.getHeight(null) / ROWS))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean hit(Object value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hit'");
    }
}
