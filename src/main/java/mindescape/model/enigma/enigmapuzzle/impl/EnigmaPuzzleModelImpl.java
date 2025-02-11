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
    
        // Creiamo un BufferedImage a partire dall'immagine originale
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bufferedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, null);
        g.dispose();
    
        // Ora possiamo dividere l'immagine in pezzi
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                pieces[i][j] = bufferedImage.getSubimage(j * pieceWidth, i * pieceHeight, pieceWidth, pieceHeight);
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
        // Log per vedere i pezzi che stiamo cercando di scambiare
        System.out.println("Scambiando: (" + firstRow + ", " + firstCol + ") con (" + secondRow + ", " + secondCol + ")");
        Image temp = pieces[firstRow][firstCol];
        pieces[firstRow][firstCol] = pieces[secondRow][secondCol];
        pieces[secondRow][secondCol] = temp;
        
        // Log per confermare lo scambio
        System.out.println("Nuove posizioni: (" + firstRow + ", " + firstCol + ") con " + pieces[firstRow][firstCol]);
        System.out.println("Nuove posizioni: (" + secondRow + ", " + secondCol + ") con " + pieces[secondRow][secondCol]);
    }
    

    // Metodo per verificare se il puzzle è risolto
    public boolean isSolved() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                // Confronta la posizione di ogni pezzo con la sua posizione originale
                if (!pieces[i][j].equals(((BufferedImage)originalImage).getSubimage(j * (originalImage.getWidth(null) / COLS), i * (originalImage.getHeight(null) / ROWS), originalImage.getWidth(null) / COLS, originalImage.getHeight(null) / ROWS))) {
                    return false; // Se trovi un pezzo che non è nella posizione giusta, il puzzle non è risolto
                }
            }
        }
        return true; // Se tutti i pezzi sono al posto giusto, il puzzle è risolto
    }


    @Override
    public boolean hit(Object value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hit'");
    }
}

