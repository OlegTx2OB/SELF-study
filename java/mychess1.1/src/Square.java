public class Square
{
    private int x;
    private int y;
    private Figure figure;

    public Square(int x, int y, Figure figure)
    {
        this.x = x;
        this.y = y;
        this.figure = figure;
    }

    public Figure getFigure() {return figure;}

    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public void setFigure(Figure figure){this.figure = figure;}
}