package com.stevec.aquarium;
import java.awt.*;
import java.awt.event.*;

/**
 * AquariumViewer represents an interface for playing a game of Aquarium.
 *
 * @authors Steve Clarke
 * @version 1.0
 */

public class AquariumViewer implements MouseListener
{
    private final int BOXSIZE = 40;          // the size of each square
    private final int OFFSET  = BOXSIZE * 2; // the gap around the board
    private       int WINDOWSIZE;            // dependent on size of puzzle

    private Aquarium puzzle; // the internal representation of the puzzle
    private int        size; // the puzzle is size x size
    private SimpleCanvas sc; // the display window

    private final Color bgColor       = Color.white;
    private final Color gridColor     = Color.black;
    private final Color textColor     = Color.black;
    private final Color aquariumColor = Color.red;
    private final Color waterColor    = Color.cyan;
    private final Color airColor      = Color.pink;
    /**
     * Constructor.
     * Sets all fields, and displays the initial puzzle.
     */
    public AquariumViewer(Aquarium puzzle)
    {
        this.puzzle = puzzle;
        size = puzzle.getSize();
        WINDOWSIZE = (size * BOXSIZE) + OFFSET*2;
        sc = new SimpleCanvas("Aquarium Puzzle", WINDOWSIZE, WINDOWSIZE, bgColor);
        sc.addMouseListener(this);
        displayPuzzle();
    }

    /**
     * Returns the current state of the puzzle.
     */
    public Aquarium getPuzzle()
    {
        return puzzle;
    }

    /**
     * Returns the size of the puzzle.
     */
    public int getSize()
    {
        return size;
    }

    /**
     * Returns the current state of the canvas.
     */
    public SimpleCanvas getCanvas()
    {
        return sc;
    }

    /**
     * Displays the initial puzzle; see the LMS page for the format.
     */
    private void displayPuzzle()
    {
        displayGrid();
        displayNumbers();
        displayAquariums();
        displayButtons();
    }

    /**
     * Displays the grid in the middle of the window.
     */
    public void displayGrid()
    {
        for (int i = 0; i <= size; i++){
            sc.drawLine(OFFSET, OFFSET + (BOXSIZE * i), OFFSET + (size * BOXSIZE), OFFSET + (BOXSIZE * i), gridColor);
            sc.drawLine(OFFSET + (BOXSIZE * i), OFFSET, OFFSET + (BOXSIZE * i), OFFSET + (size * BOXSIZE), gridColor);
        }
    }

    /**
     * Displays the numbers around the grid.
     */
    public void displayNumbers()
    {
        int adjustment = BOXSIZE/2; // used to centre the numbers
        for(int i = 0; i < size; i ++){
            sc.drawString(puzzle.getColumnTotals()[i], i * BOXSIZE + OFFSET + adjustment, OFFSET - adjustment, textColor); // the column totals
            sc.drawString(puzzle.getRowTotals()[i], OFFSET - adjustment, i * BOXSIZE + OFFSET + adjustment, textColor); // the row totals
        }
    }

    /**
     * Displays the aquariums.
     */
    public void displayAquariums()
    {
        int adjustment = BOXSIZE/30;
        // These draw the outside border
        sc.drawRectangle(OFFSET, OFFSET - adjustment, OFFSET + (BOXSIZE * size), OFFSET + adjustment, aquariumColor);
        sc.drawRectangle(OFFSET - adjustment, OFFSET, OFFSET + adjustment, OFFSET + (BOXSIZE * size), aquariumColor);
        sc.drawRectangle(OFFSET + (BOXSIZE * size) - adjustment, OFFSET, OFFSET + (BOXSIZE * size) + adjustment, OFFSET + (BOXSIZE * size), aquariumColor);
        sc.drawRectangle(OFFSET, OFFSET + (BOXSIZE*size) - adjustment, OFFSET + (BOXSIZE * size), OFFSET + (BOXSIZE * size) + adjustment, aquariumColor);

        for(int x = 0;x < size;x++){
            for(int y = 0; y < size;y++){
                if(x != size - 1){ // Draws right line
                    if(puzzle.getAquariums()[y][x + 1] != puzzle.getAquariums()[y][x]){
                        sc.drawRectangle(x*BOXSIZE + OFFSET + BOXSIZE - adjustment, OFFSET + BOXSIZE * y,
                                x*BOXSIZE + OFFSET + BOXSIZE + adjustment, OFFSET + BOXSIZE + (BOXSIZE * y), aquariumColor);
                    }
                }

                if(y != size - 1){ // Draws bottom line
                    if (puzzle.getAquariums()[y+1][x] != puzzle.getAquariums()[y][x]){
                        sc.drawRectangle(OFFSET + (BOXSIZE * x), OFFSET + BOXSIZE + (BOXSIZE * y) - adjustment,
                                x*BOXSIZE + OFFSET + BOXSIZE, OFFSET + BOXSIZE + (BOXSIZE * y) + adjustment, aquariumColor);
                    }
                }

                if( x != 0){ // Draws left line
                    if(puzzle.getAquariums()[y][x - 1] != puzzle.getAquariums()[y][x]){
                        sc.drawRectangle(OFFSET + (BOXSIZE * x)- adjustment, OFFSET + (BOXSIZE * y),
                                OFFSET + (BOXSIZE * x)+ adjustment, OFFSET + BOXSIZE + (BOXSIZE * y), aquariumColor);
                    }
                }

                if( y != 0){ // Draws top line
                    if(puzzle.getAquariums()[y-1][x] != puzzle.getAquariums()[y][x]){
                        sc.drawRectangle(OFFSET + (BOXSIZE * x), OFFSET + (BOXSIZE * y) - adjustment,
                                x*BOXSIZE + OFFSET + BOXSIZE, OFFSET + BOXSIZE * y + adjustment, aquariumColor);
                    }
                }
            }
        }
    }

    /**
     * Displays the buttons below the grid.
     */
    public void displayButtons()
    {
        sc.drawString("SOLVED?", OFFSET, OFFSET + (BOXSIZE * (size+1)), textColor);
        sc.drawString("CLEAR ", OFFSET + (BOXSIZE * (size-1)), OFFSET + (BOXSIZE * (size+1)), textColor);
    }

    /**
     * Updates the display of Square r,c.
     * Sets the display of this square to whatever is in the squares array.
     */
    public void updateSquare(int r, int c)
    {
        // Below variables are the (x,y) co-ordinates for the rectangles drawn in-game.
        int topCnrX = (OFFSET + (r * BOXSIZE)) + 1;
        int topCnrY = (OFFSET + (c * BOXSIZE)) + 1;
        int bottomCnrX = (OFFSET + ((r+1) * BOXSIZE)) - 1;
        int bottomCnrY = (OFFSET + ((c+1) * BOXSIZE)) - 1;
        if (puzzle.getSpaces()[r][c] == Space.EMPTY){
            sc.drawRectangle(topCnrY, topCnrX, bottomCnrY, bottomCnrX, bgColor);
        } else if (puzzle.getSpaces()[r][c] == Space.WATER) {
            sc.drawRectangle(topCnrY, topCnrX, bottomCnrY, bottomCnrX, waterColor);
        } else if (puzzle.getSpaces()[r][c] == Space.AIR) {
            sc.drawRectangle(topCnrY, topCnrX, bottomCnrY, bottomCnrX, bgColor);
            sc.drawDisc(c * BOXSIZE + OFFSET + (BOXSIZE/2), r * BOXSIZE + OFFSET + (BOXSIZE/2), 10, airColor);
        }
    }

    /**
     * Responds to a mouse click.
     * If it's on the board, make the appropriate move and update the screen display.
     * If it's on SOLVED?,   check the solution and display the result.
     * If it's on CLEAR,     clear the puzzle and update the screen display.
     */
    public void mousePressed(MouseEvent e)
    {
        int puzzleSize = OFFSET + (size * BOXSIZE);
        if (e.getY() > OFFSET &&
                e.getX() > OFFSET &&
                e.getY() < puzzleSize &&
                e.getX() < puzzleSize &&
                e.getButton() == MouseEvent.BUTTON1) {
            puzzle.leftClick((e.getY()-OFFSET) / BOXSIZE, (e.getX()-OFFSET) / BOXSIZE);
            updateSquare(    (e.getY()-OFFSET) / BOXSIZE, (e.getX()-OFFSET) / BOXSIZE);
        } else if (e.getY() > OFFSET &&
                e.getX() > OFFSET &&
                e.getY() < puzzleSize &&
                e.getX() < puzzleSize &&
                e.getButton() == MouseEvent.BUTTON3) {
            puzzle.rightClick((e.getY()-OFFSET) / BOXSIZE, (e.getX()-OFFSET) / BOXSIZE);
            updateSquare(     (e.getY()-OFFSET) / BOXSIZE, (e.getX()-OFFSET) / BOXSIZE);
        } else if (e.getY() > OFFSET + (BOXSIZE * size) + 30 &&
                e.getX() < OFFSET + (BOXSIZE * size) &&
                e.getY() < OFFSET + (BOXSIZE * (size+1) + 3) &&
                e.getX() > OFFSET + (BOXSIZE * (size-1))) { // "CLEAR" Button
            puzzle.clear();
            for(int x = 0; x < size; x++){
                for(int y = 0; y < size; y++){
                    updateSquare(y, x);
                }
            }
            sc.drawRectangle(OFFSET, OFFSET + (size * BOXSIZE) + 40, OFFSET + (5 * BOXSIZE), OFFSET + (size * BOXSIZE) + 70, bgColor);
        } else if (e.getY() > OFFSET + (size * BOXSIZE) + 30 &&
                e.getX() < OFFSET + 55 &&
                e.getY() < OFFSET + (BOXSIZE * (size+1) + 3) &&
                e.getX() > OFFSET){ // "SOLVED?" Button
            sc.drawRectangle(OFFSET, OFFSET + (size * BOXSIZE) + 40, OFFSET + (5 * BOXSIZE), OFFSET + (size * BOXSIZE) + 70, bgColor);
            sc.drawString(CheckSolution.isSolution(puzzle), OFFSET, OFFSET + (size * BOXSIZE) + 60, textColor);
        }
    }
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
