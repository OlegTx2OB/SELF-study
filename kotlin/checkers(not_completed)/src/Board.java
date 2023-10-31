public class Board
{
    Square[][] squares;

    public Board()
    {
        squares = new Square[8][8];

        for (int x = 0; x < 8; x++)
        {
            for (int y = 0; y < 8; y++)
            {
                squares[x][y] = new Square(null, x, y);
            }
        }
    }

    public void initializeCheckersFigures(Board board)
    {
//black
        board.getSquares(1, 0).setFigure(new Checker(false));
        board.getSquares(3, 0).setFigure(new Checker(false));
        board.getSquares(5, 0).setFigure(new Checker(false));
        board.getSquares(7, 0).setFigure(new Checker(false));

        board.getSquares(0, 1).setFigure(new Checker(false));
        board.getSquares(2, 1).setFigure(new Checker(false));
        board.getSquares(4, 1).setFigure(new Checker(false));
        board.getSquares(6, 1).setFigure(new Checker(false));

        board.getSquares(1, 2).setFigure(new Checker(false));
        board.getSquares(3, 2).setFigure(new Checker(false));
        board.getSquares(5, 2).setFigure(new Checker(false));
        board.getSquares(7, 2).setFigure(new Checker(false));

//white
        board.getSquares(0, 5).setFigure(new Checker(true));
        board.getSquares(2, 5).setFigure(new Checker(true));
        board.getSquares(4, 5).setFigure(new Checker(true));
        board.getSquares(6, 5).setFigure(new Checker(true));

        board.getSquares(1, 6).setFigure(new Checker(true));
        board.getSquares(3, 6).setFigure(new Checker(true));
        board.getSquares(5, 6).setFigure(new Checker(true));
        board.getSquares(7, 6).setFigure(new Checker(true));

        board.getSquares(0, 7).setFigure(new Checker(true));
        board.getSquares(2, 7).setFigure(new Checker(true));
        board.getSquares(4, 7).setFigure(new Checker(true));
        board.getSquares(6, 7).setFigure(new Checker(true));
    }

    public void checkersboardOutput(Board board)
    {
        System.out.printf("_________________________________________________\n|");
        for(int y = 0; y < 8; y++)
        {
            for(int x = 0; x < 8; x++)
            {
                if(board.getSquares(x, y).getFigure() == null) System.out.printf("     |");
//шашка
                else if (board.getSquares(x, y).getFigure() instanceof Checker &&
                        !board.getSquares(x, y).getFigure().getIsWhite()) System.out.printf("шашка|");
                else if (board.getSquares(x, y).getFigure() instanceof Checker &&
                        board.getSquares(x, y).getFigure().getIsWhite()) System.out.printf("ШАШКА|");
//дамка
                else if (board.getSquares(x, y).getFigure() instanceof King &&
                        !board.getSquares(x, y).getFigure().getIsWhite()) System.out.printf("дамка|");
                else if (board.getSquares(x, y).getFigure() instanceof King &&
                        board.getSquares(x, y).getFigure().getIsWhite()) System.out.printf("ДАМКА|");
                if(x == 7) System.out.printf(" %d", 9 - (y + 1));
            }
            System.out.printf("\n|_____|_____|_____|_____|_____|_____|_____|_____|\n|");
        }
        System.out.printf("  A     B     C     D     E     F     G     H  |\n");
    }

    public Square getSquares(int x, int y) {return squares[x][y];}
}
