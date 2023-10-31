import java.util.Scanner;

public class GameProcess
{
    public static void gameProcess(Board board)
    {
        board.checkersboardOutput(board);
        boolean isWhiteTurn = true;

        while (true)
        {
            if (isWhiteTurn) System.out.printf("................WHITE PLAYER TURN................\n");
            else System.out.printf("................BLACK PLAYER TURN................\n");


            moveFigure(board, isWhiteTurn);
            board.checkersboardOutput(board);

            isWhiteTurn = !isWhiteTurn;

        }
    }

    public static void moveFigure(Board board, boolean isWhiteTurn)
    {
        int[] startXY;
        int[] endXY;
        boolean printWarning = false;
        Square startSquare;
        Square endSquare;
        Figure startSquareFigure;

        do
        {
            if(printWarning) System.out.printf("!!! Your move is impossible. Repeat your move !!!\n");
            printWarning = true;

            startXY = getStartOrEndXY(board, "start", isWhiteTurn);
            endXY = getStartOrEndXY(board,"end", isWhiteTurn);

            startSquare = board.getSquares(startXY[0], startXY[1]);
            startSquareFigure = startSquare.getFigure();
            endSquare = board.getSquares(endXY[0], endXY[1]);

        } while(!startSquareFigure.canMove(board, startXY, endXY) );

        startSquare.setFigure(null);
        endSquare.setFigure(startSquareFigure);

        if(Math.abs(endXY[0] - startXY[0]) > 1) deletingTheFigureAlongTheWay(board, startXY, endXY);

        if(checkerCanBecomeKing(board, endXY)) endSquare.setFigure(new King(startSquareFigure.getIsWhite()));
    }

    public static int[] getStartOrEndXY(Board board, String startOrEndString, boolean isWhiteTurn)
    {
        int[] intXY;
        boolean printWarning = false;
        do
        {
            if(printWarning)
            {
                if (startOrEndString.equals("start")) System.out.printf("You choose coordinates with not your figure\n");
                else System.out.printf("You choose coordinates with your figure\n");
            }
            printWarning = true;

            System.out.printf("Enter %s coordinates: ", startOrEndString);
            String coordinate = getStringXY();
            intXY = new int[2];
            intXY[0] = (int) coordinate.charAt(0) - 65;
            intXY[1] = 8 - ((int) coordinate.charAt(1) - 48);

        } while(!coordinatesXYAreCorrect(board, intXY, startOrEndString, isWhiteTurn));

        return intXY;
    }

    public static int[] getIntermediateXY(Board board, int[] startXY, int[] endXY)
    {
        int[] intermediateXY = new int[2];

        intermediateXY[0] = (startXY[0] + endXY[0]) / 2;
        intermediateXY[1] = (startXY[1] + endXY[1]) / 2;

        return intermediateXY;
    }

    public static boolean coordinatesXYAreCorrect(Board board, int[] intXY, String startOrEndString, boolean isWhiteTurn)
    {
        if(startOrEndString.equals("start") && ( board.getSquares(intXY[0], intXY[1]).getFigure() != null &&
                board.getSquares(intXY[0], intXY[1]).getFigure().getIsWhite() == isWhiteTurn)) return true;
        return startOrEndString.equals("end") && (board.getSquares(intXY[0], intXY[1]).getFigure() == null ||
                board.getSquares(intXY[0], intXY[1]).getFigure().getIsWhite() != isWhiteTurn);
    }
    public static String getStringXY()
    {
        Scanner scanner = new Scanner(System.in);
        String stringXY = scanner.nextLine();

        while(stringXY.length() != 2 || (stringXY.charAt(0) < 'A' || stringXY.charAt(0) > 'H') ||
                (stringXY.charAt(1) < '1' || stringXY.charAt(1) > '8') )
        {
            System.out.printf("invalid input. Enter coordinates again: ");
            stringXY = scanner.nextLine();
        }
        return stringXY;
    }

    public static void deletingTheFigureAlongTheWay(Board board, int[] startXY, int[] endXY)
    {
//вверх-вправо
        if (endXY[0] - startXY[0] > 0 && endXY[1] - startXY[1] < 0)
        {
        for(int intermediateX = startXY[0] + 1, intermediateY = startXY[1] - 1;
            intermediateX < endXY[0]; intermediateX++, intermediateY--)
            board.getSquares(intermediateX, intermediateY).setFigure(null);
        }
//вверх-влево
        else if (endXY[0] - startXY[0] < 0 && endXY[1] - startXY[1] < 0)
        {
            for(int intermediateX = startXY[0] - 1, intermediateY = startXY[1] - 1;
                intermediateX > endXY[0]; intermediateX--, intermediateY--)
                board.getSquares(intermediateX, intermediateY).setFigure(null);
        }
//вниз-вправо
        else if(endXY[0] - startXY[0] > 0 && endXY[1] - startXY[1] > 0)
        {
            for(int intermediateX = startXY[0] + 1, intermediateY = startXY[1] + 1;
                intermediateX < endXY[0]; intermediateX++, intermediateY++)
                board.getSquares(intermediateX, intermediateY).setFigure(null);
        }
//вниз-влево
        else if(endXY[0] - startXY[0] < 0 && endXY[1] - startXY[1] > 0)
        {
            for(int intermediateX = startXY[0] - 1, intermediateY = startXY[1] + 1;
                intermediateX > endXY[0]; intermediateX--, intermediateY++)
                board.getSquares(intermediateX, intermediateY).setFigure(null);
        }

    }

    public static boolean checkerCanBecomeKing(Board board, int[] endXY)
    {
        Figure figure = board.getSquares(endXY[0], endXY[1]).getFigure();
        if(figure instanceof Checker)
        {
            if(figure.getIsWhite() && endXY[1] == 0) return true;
            else return !figure.getIsWhite() && endXY[1] == 7;
        }
        return false;
    }
}
