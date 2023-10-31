public class Main
{
    public static void main(String[] args)
    {
        Board board = new Board();
        board.initializeCheckersFigures(board);
        GameProcess.gameProcess(board);
    }
}