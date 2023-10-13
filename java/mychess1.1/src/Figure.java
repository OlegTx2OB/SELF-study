public abstract class Figure
{
    private boolean isWhite;

    public Figure(boolean isWhite)
    {
        this.isWhite = isWhite;
    }

    public boolean getIsWhite(){return this.isWhite;}
    public void setIsWhite(boolean isWhite) {this.isWhite = isWhite;}

    public abstract boolean canMove(Board board, int[] startXY, int[] endXY);

    public boolean rookMovePossible(Board board, int[] startXY, int[] endXY)
    {
        if( ( (endXY[0] == startXY[0]) && !(endXY[1] == startXY[1]) ) ||
                ( !(endXY[0] == startXY[0] ) && ( endXY[1] == startXY[1] ) ) )
        {
//1 клетка
            if(Math.abs(endXY[1] - startXY[1]) == 1 || Math.abs(endXY[0] - startXY[0]) == 1) return true;
//вверх
            else if(endXY[1] - startXY[1] < 0)
            {
                for(int intermediateY = endXY[1] + 1; intermediateY < startXY[1]; intermediateY++)
                    if(board.getSquares(endXY[0], intermediateY).getFigure() != null) return false;
            }
//вниз
            else if(endXY[1] - startXY[1] > 0)
            {
                for (int intermediateY = startXY[1] + 1; intermediateY < endXY[1]; intermediateY++)
                    if (board.getSquares(endXY[0], intermediateY).getFigure() != null) return false;
            }
//влево
            else if(endXY[0] - startXY[0] < 0)
            {
                for(int intermediateX = endXY[0] + 1; intermediateX < startXY[0]; intermediateX++)
                    if(board.getSquares(intermediateX, endXY[1]).getFigure() != null) return false;
            }
//вправо
            else if(endXY[0] - startXY[0] > 0)
            {
                for(int intermediateX = startXY[0] + 1; intermediateX < endXY[0]; intermediateX++)
                    if(board.getSquares(intermediateX, endXY[1]).getFigure() != null) return false;
            }
            return true;
        }
        else return false;
    }
    public boolean bishopMovePossible(Board board, int[] startXY, int[] endXY)
    {
        if(Math.abs(endXY[0] - startXY[0]) == Math.abs(endXY[1] - startXY[1]))
        {
//1 клетка
            if(Math.abs(endXY[0] - startXY[0]) == 1) return true;
//вверх-вправо
            else if (endXY[0] - startXY[0] > 0 && endXY[1] - startXY[1] < 0)
            {
                for(int intermediateX = startXY[0] + 1, intermediateY = startXY[1] - 1;
                    intermediateX < endXY[0]; intermediateX++, intermediateY--)
                    if(board.getSquares(intermediateX, intermediateY).getFigure() != null) return false;
            }
//вверх-влево
            else if (endXY[0] - startXY[0] < 0 && endXY[1] - startXY[1] < 0)
            {
                for(int intermediateX = startXY[0] - 1, intermediateY = startXY[1] - 1;
                    intermediateX > endXY[0]; intermediateX--, intermediateY--)
                    if(board.getSquares(intermediateX, intermediateY).getFigure() != null) return false;
            }
//вниз-вправо
            else if(endXY[0] - startXY[0] > 0 && endXY[1] - startXY[1] > 0)
            {
                for(int intermediateX = startXY[0] + 1, intermediateY = startXY[1] + 1;
                    intermediateX < endXY[0]; intermediateX++, intermediateY++)
                    if(board.getSquares(intermediateX, intermediateY).getFigure() != null) return false;
            }
//вниз-влево
            else if(endXY[0] - startXY[0] < 0 && endXY[1] - startXY[1] > 0)
            {
                for(int intermediateX = startXY[0] - 1, intermediateY = startXY[1] + 1;
                    intermediateX > endXY[0]; intermediateX--, intermediateY++)
                    if(board.getSquares(intermediateX, intermediateY).getFigure() != null) return false;
            }
            return true;
        }
        else return false;
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
    {
        return Math.abs(endXY[0] - startXY[0]) <= 1 && Math.abs(endXY[1] - startXY[1]) <= 1;
    }
}

class Queen extends Figure
{
    public Queen(boolean isWhite)
    {
        super(isWhite);
    }
    @Override
    public boolean canMove(Board board, int[] startXY, int[] endXY)
    {
        return rookMovePossible(board, startXY, endXY) || bishopMovePossible(board, startXY, endXY);
    }
}

class Rook extends Figure
{
    public Rook(boolean isWhite)
    {
        super(isWhite);
    }
    @Override
    public boolean canMove(Board board, int[] startXY, int[] endXY)
    {
        return rookMovePossible(board, startXY, endXY);
    }
}

class Bishop extends Figure
{
    public Bishop(boolean isWhite)
    {
        super(isWhite);
    }
    @Override
    public boolean canMove(Board board, int[] startXY, int[] endXY)
    {
        return bishopMovePossible(board, startXY, endXY);
    }
}

class Knight extends Figure
{
    public Knight(boolean isWhite)
    {
        super(isWhite);
    }
    @Override
    public boolean canMove(Board board, int[] startXY, int[] endXY)
    {
        return ((endXY[0] - startXY[0] == -1 && endXY[1] - startXY[1] == -2) ||
                (endXY[0] - startXY[0] == -1 && endXY[1] - startXY[1] == 2) ||
                (endXY[0] - startXY[0] == 1 && endXY[1] - startXY[1] == -2) ||
                (endXY[0] - startXY[0] == 1 && endXY[1] - startXY[1] == 2) ||
                (endXY[0] - startXY[0] == -2 && endXY[1] - startXY[1] == -1) ||
                (endXY[0] - startXY[0] == -2 && endXY[1] - startXY[1] == 1) ||
                (endXY[0] - startXY[0] == 2 && endXY[1] - startXY[1] == -1) ||
                (endXY[0] - startXY[0] == 2 && endXY[1] - startXY[1] == 1));
    }
}

class Pawn extends Figure
{
    public Pawn(boolean isWhite)
    {
        super(isWhite);
    }
    @Override
    public boolean canMove(Board board, int[] startXY, int[] endXY)
    {
//WHITE
        if(getIsWhite())
        {
            if(endXY[0] == startXY[0] && endXY[1] - startXY[1] == -1 &&
                    board.getSquares(endXY[0], endXY[1]).getFigure() == null) return true;
            if(endXY[0] == startXY[0] && endXY[1] - startXY[1] == -2 && startXY[1] == 6 &&
                    board.getSquares(endXY[0], endXY[1]).getFigure() == null) return true;
            return (endXY[0] - startXY[0] == 1 || endXY[0] - startXY[0] == -1) &&
                    endXY[1] - startXY[1] == -1 &&
                    !(board.getSquares(endXY[0], endXY[1]).getFigure() == null) &&
                    !board.getSquares(endXY[0], endXY[1]).getFigure().getIsWhite();
        }
//BLACK
        else
        {
            if(endXY[0] == startXY[0] && endXY[1] - startXY[1] == 1 &&
                    board.getSquares(endXY[0], endXY[1]).getFigure() == null) return true;
            if(endXY[0] == startXY[0] && endXY[1] - startXY[1] == 2 && startXY[1] == 1 &&
                    board.getSquares(endXY[0], endXY[1]).getFigure() == null) return true;
            return (endXY[0] - startXY[0] == 1 || endXY[0] - startXY[0] == -1) &&
                    endXY[1] - startXY[1] == 1 &&
                    board.getSquares(endXY[0], endXY[1]).getFigure() != null &&
                    board.getSquares(endXY[0], endXY[1]).getFigure().getIsWhite();
        }
    }
}