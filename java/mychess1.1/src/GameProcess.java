import java.util.Scanner;

public class GameProcess
{
    public static void gameProcess(Board board)
    {
        board.chessboardOutput(board);
        boolean isWhiteTurn = true;

        while(true)
        {
            if(isWhiteTurn)System.out.printf("................WHITE PLAYER TURN................\n");
            else System.out.printf("................BLACK PLAYER TURN................\n");



            if(isCheck(board, isWhiteTurn)) System.out.printf("......................CHECK......................\n");
            moveFigure(board, isWhiteTurn);
            board.chessboardOutput(board);

            isWhiteTurn = !isWhiteTurn;
        }
    }

    public static void moveFigure(Board board, boolean isWhiteTurn)
    {
        int[] startXY;
        int[] endXY;
        boolean printWarning = false;
        Figure startSquareFigure;

        do
        {
            if(printWarning) System.out.printf("!!! Your move is impossible. Repeat your move !!!\n");
            printWarning = true;

            startXY = getStartOrEndXY(board, "start", isWhiteTurn);
            endXY = getStartOrEndXY(board,"end", isWhiteTurn);
            startSquareFigure = board.getSquares(startXY[0], startXY[1]).getFigure();

        } while(!startSquareFigure.canMove(board, startXY, endXY) );

        startSquareFigure = checkAndPerformPawnPromotion(board.getSquares(startXY[0], startXY[1]).getFigure(), endXY);

        board.getSquares(startXY[0], startXY[1]).setFigure(null);
        board.getSquares(endXY[0], endXY[1]).setFigure(startSquareFigure);
    }

    public static Figure checkAndPerformPawnPromotion(Figure figure, int[] endXY)
    {
        if(figure instanceof Pawn && (endXY[1] == 0 || endXY[1] == 7 ))
        {
            System.out.printf("Choose new figure: 1.Queen 2.Rook 3.Bishop 4.Knight  ");
            Scanner scanner = new Scanner(System.in);
            int newFigure = scanner.nextInt();

            if (newFigure == 1) figure = new Queen(figure.getIsWhite());
            else if (newFigure == 2) figure = new Rook(figure.getIsWhite());
            else if (newFigure == 3) figure = new Bishop(figure.getIsWhite());
            else if (newFigure == 4) figure = new Knight(figure.getIsWhite());
        }
        return figure;
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

    public static boolean isCheck(Board board, boolean isWhiteTurn)
    {
        int[] kingXY = new int[2];
        boolean isTargetFounded = false;

        for(int x = 0; x < 8; x++)
        {
            for (int y = 0; y < 8; y++)
                if (board.getSquares(x, y).getFigure() != null && board.getSquares(x, y).getFigure() instanceof King &&
                        board.getSquares(x, y).getFigure().getIsWhite() == isWhiteTurn)
                {
                    kingXY[0] = x;
                    kingXY[1] = y;
                    isTargetFounded = true;
                    break;
                }
            if(isTargetFounded) break;
        }

        int[] enemyFigureXY = new int[2];

        for(int x2 = 0; x2 < 8; x2++)
        {
            for(int y2 = 0; y2 < 8; y2++)
            {
                enemyFigureXY[0] = x2;
                enemyFigureXY[1] = y2;

                if(board.getSquares(x2, y2).getFigure() != null &&
                        board.getSquares(x2, y2).getFigure().getIsWhite() != isWhiteTurn &&
                        board.getSquares(x2, y2).getFigure().canMove(board, enemyFigureXY, kingXY)) return true;
            }
        }

        return false;
    }

}