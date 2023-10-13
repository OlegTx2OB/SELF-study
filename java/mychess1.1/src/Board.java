public class Board
{
    Square[][] squares;

    public Board()
    {
        squares = new Square[8][8];

        for(int x = 0; x < squares.length; x++)
        {
            for(int y = 0; y < squares[x].length; y++)
            {
                squares[x][y] = new Square(x, y, null);
            }
        }
    }

    public Square getSquares(int x, int y) {return squares[x][y];}

    public void initializeChessFigures(Board board)
    {
//короли
        board.getSquares(4, 7).setFigure(new King(true));
        board.getSquares(4, 0).setFigure(new King(false));
//ферзи
        board.getSquares(3, 7).setFigure(new Queen(true));
        board.getSquares(3, 0).setFigure(new Queen(false));
//ладьи
        board.getSquares(0, 7).setFigure(new Rook(true));
        board.getSquares(7, 7).setFigure(new Rook(true));
        board.getSquares(0, 0).setFigure(new Rook(false));
        board.getSquares(7, 0).setFigure(new Rook(false));
//слоны
        board.getSquares(2, 7).setFigure(new Bishop(true));
        board.getSquares(5, 7).setFigure(new Bishop(true));
        board.getSquares(2, 0).setFigure(new Bishop(false));
        board.getSquares(5, 0).setFigure(new Bishop(false));
//кони
        board.getSquares(1, 7).setFigure(new Knight(true));
        board.getSquares(6, 7).setFigure(new Knight(true));
        board.getSquares(1, 0).setFigure(new Knight(false));
        board.getSquares(6, 0).setFigure(new Knight(false));
//пешки
        for(int x = 0; x < 8; x++)
        {
            board.getSquares(x, 6).setFigure(new Pawn(true));
            board.getSquares(x, 1).setFigure(new Pawn(false));
        }
    }

    public void chessboardOutput(Board board)
    {
        System.out.printf("_________________________________________________\n|");
        for(int y = 0; y < 8; y++)
        {
            for(int x = 0; x < 8; x++)
            {
                if(board.getSquares(x, y).getFigure() == null) System.out.printf("     |");
//пешка
                else if (board.getSquares(x, y).getFigure() instanceof Pawn &&
                        !board.getSquares(x, y).getFigure().getIsWhite()) System.out.printf("  п  |");
                else if (board.getSquares(x, y).getFigure() instanceof Pawn &&
                        board.getSquares(x, y).getFigure().getIsWhite()) System.out.printf("  П  |");
//конь
                else if (board.getSquares(x, y).getFigure() instanceof Knight &&
                        !board.getSquares(x, y).getFigure().getIsWhite()) System.out.printf(" кн  |");
                else if (board.getSquares(x, y).getFigure() instanceof Knight &&
                        board.getSquares(x, y).getFigure().getIsWhite()) System.out.printf(" КН  |");
//слон
                else if (board.getSquares(x, y).getFigure() instanceof Bishop &&
                        !board.getSquares(x, y).getFigure().getIsWhite()) System.out.printf(" слн |");
                else if (board.getSquares(x, y).getFigure() instanceof Bishop &&
                        board.getSquares(x, y).getFigure().getIsWhite()) System.out.printf(" СЛН |");
//ладья
                else if (board.getSquares(x, y).getFigure() instanceof Rook &&
                        !board.getSquares(x, y).getFigure().getIsWhite()) System.out.printf("ладя |");
                else if (board.getSquares(x, y).getFigure() instanceof Rook &&
                        board.getSquares(x, y).getFigure().getIsWhite()) System.out.printf("ЛАДЯ |");
//ферзь
                else if (board.getSquares(x, y).getFigure() instanceof Queen &&
                        !board.getSquares(x, y).getFigure().getIsWhite()) System.out.printf("ферзь|");
                else if (board.getSquares(x, y).getFigure() instanceof Queen &&
                        board.getSquares(x, y).getFigure().getIsWhite()) System.out.printf("ФЕРЗЬ|");
//король
                else if (board.getSquares(x, y).getFigure() instanceof King &&
                        !board.getSquares(x, y).getFigure().getIsWhite()) System.out.printf("корол|");
                else if (board.getSquares(x, y).getFigure() instanceof King &&
                        board.getSquares(x, y).getFigure().getIsWhite()) System.out.printf("КОРОЛ|");
                if(x == 7) System.out.printf(" %d", 9 - (y + 1));
            }
            System.out.printf("\n|_____|_____|_____|_____|_____|_____|_____|_____|\n|");
        }
        System.out.printf("  A     B     C     D     E     F     G     H  |\n");
    }
}