public abstract class Figure
{
    private boolean isWhite;

    public Figure(boolean isWhite) {this.isWhite = isWhite;}

    public abstract boolean canMove(Board board, int[] startXY, int[] endXY);

    public boolean getIsWhite() {return this.isWhite;}
    public void setWhite(boolean isWhite) {this.isWhite = isWhite;}
}

class Checker extends Figure
{
    public Checker(boolean isWhite) {super(isWhite);}

    @Override
    public boolean canMove(Board board, int[] startXY, int[] endXY)
    {
        if(Math.abs(endXY[0] - startXY[0]) == Math.abs(endXY[1] - startXY[1]) &&
                board.getSquares(endXY[0], endXY[1]).getFigure() == null)
        {
//обычный ход
            if(Math.abs(endXY[0] - startXY[0]) == 1)
            {
                if(getIsWhite() &&
                        ((endXY[0] - startXY[0] > 0 && endXY[1] - startXY[1] < 0) ||
                                (endXY[0] - startXY[0] < 0 && endXY[1] - startXY[1] < 0) )) return true;

                else return (endXY[0] - startXY[0] > 0 && endXY[1] - startXY[1] > 0) ||
                        (endXY[0] - startXY[0] < 0 && endXY[1] - startXY[1] > 0);
            }
//через клетку
            else if (Math.abs(endXY[0] - startXY[0]) == 2)
            {
                int[] intermediateXY = GameProcess.getIntermediateXY(board, startXY, endXY);

                return board.getSquares(intermediateXY[0], intermediateXY[1]).getFigure() != null &&
                        board.getSquares(intermediateXY[0], intermediateXY[1]).getFigure().getIsWhite() !=
                                board.getSquares(startXY[0], startXY[1]).getFigure().getIsWhite();
            }
        }
        return false;
    }
}

class King extends Figure
{
    public King(boolean isWhite)
    {
        super(isWhite);
    }

    @Override
    public boolean canMove(Board board, int[] startXY, int[] endXY)
    {//todo
        return true;
    }
}