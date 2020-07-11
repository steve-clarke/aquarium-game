package com.stevec.aquarium;

/**
 * CheckSolution is a utility class which can check if
 * a board position in an Aquarium puzzle is a valid solution.
 *
 * @authors Steve Clarke
 * @version 1.0
 */

public class CheckSolution
{
    /**
     * Non-constructor.
     */
    private CheckSolution(){}

    /**
     * Returns the number of water squares in each row of Aquarium puzzle p, top down.
     */
    public static int[] rowCounts(Aquarium p)
    {
        int[] rowCounter = new int[p.getSize()];
        for (int r = 0; r < p.getSize(); r++)
            for (int c = 0; c < p.getSize(); c++)
                if (p.getSpaces()[r][c] == Space.WATER)
                    rowCounter[r]++;
        return rowCounter;
    }

    /**
     * Returns the number of water squares in each column of Aquarium puzzle p, left to right.
     */
    public static int[] columnCounts(Aquarium p)
    {
        int[] columnCounter = new int[p.getSize()];
        for (int c = 0; c < p.getSize(); c++)
            for (int r = 0; r < p.getSize(); r++)
                if (p.getSpaces()[r][c] == Space.WATER)
                    columnCounter[c]++;
        return columnCounter;
    }

    /**
     * Returns a 2-int array denoting the collective status of the spaces
     * in the aquarium numbered t on Row r of Aquarium puzzle p.
     * The first element will be:
     * 0 if there are no spaces in t on Row r;
     * 1 if they're all water;
     * 2 if they're all not-water; or
     * 3 if they're a mixture of water and not-water.
     * The second element will be the column index c of any space r,c which is in t, or -1 if there is none.
     */
    public static int[] rowStatus(Aquarium p, int t, int r)
    {
        int[] status = new int[2];
        status[1] = -1;
        int rowCount = 0;
        int waterCount = 0;
        int otherCount = 0;
        for (int c = 0; c < p.getSize(); c++){
            if (p.getAquariums()[r][c] == t){
                rowCount++;
                status[1] = c;
                if (p.getSpaces()[r][c] == Space.WATER)
                    waterCount++;
                else
                    otherCount++;
            }
        }

        if (rowCount == 0){
            status[0] = 0;
        } else if (waterCount == rowCount){
            status [0] = 1;
        } else if (otherCount == rowCount){
            status[0] = 2;
        } else if (waterCount + otherCount == rowCount && otherCount > 0 && waterCount > 0){
            status[0] = 3;
        }
        return status;
    }

    /**
     * Returns a statement on whether the aquarium numbered t in Aquarium puzzle p is OK.
     * Every row must be either all water or all not-water,
     * and all water must be below all not-water.
     * Returns "" if the aquarium is ok; otherwise
     * returns the indices of any square in the aquarium, in the format "r,c".
     *
     */
    public static String isAquariumOK(Aquarium p, int t)
    {
        String status = "";
        for (int r = 0; r < p.getSize(); r++){
            if (rowStatus(p, t, r)[0] == 1){
                if ((r+1) < p.getSize() && (rowStatus(p, t, (r+1))[0] == 2 || rowStatus(p, t, (r+1))[0] == 3)){
                    status += ((r+1) + "," + rowStatus(p, t, (r+1))[1]);
                    break;
                }
            } else if (rowStatus(p, t, r)[0] == 3){
                status += (r + "," + rowStatus(p, t, r)[1]);
                break;
            }
        }
        return status;
    }

    /**
     * Returns a statement on whether we have a correct solution to Aquarium puzzle p.
     * Every row and column must have the correct number of water squares,
     * and all aquariums must be OK.
     * Returns three ticks if the solution is correct;
     * Displays error under puzzle with suggestions otherwise.
     */
    public static String isSolution(Aquarium p)
    {
        int numberOfAquariums = p.getAquariums()[p.getSize()-1][p.getSize()-1];
        String output = "";
        boolean correct = true;

        for (int x = 0; x < p.getSize(); x++){
            if (rowCounts(p)[x] != p.getRowTotals()[x]){
                output = "Row " + x + " is wrong";
                correct = false;
            } else if (columnCounts(p)[x] != p.getColumnTotals()[x]) {
                output = "Column " + x + " is wrong";
                correct = false;
            }
        }

        for (int x = 1; x <= numberOfAquariums; x++){
            if (isAquariumOK(p, x) != "") {
                output = "The aquarium at " + isAquariumOK(p, x) + " is wrong";
                correct = false;
            }
        }

        if (correct == true) {
            output = "\u2713\u2713\u2713";
        }

        return output;
    }
}
