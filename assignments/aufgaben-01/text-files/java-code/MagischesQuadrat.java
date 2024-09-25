public class MagischesQuadrat {

    /**
     * Soll prüfen, ob das gegebene 2D-Array ein korrektes Magisches
     * Quadrat mit der gegebenen Summe ist.
     *
     * Ihre Implementation muss nur mit quadratischen Arrays
     * zurechtkommen, also Arrays, wo die Längen in beide Dimensionen
     * gleich gross sind.
     */
    public static boolean check(int[][] board, int wert){
        if (board.length==0){
            return false;
        }
        int diagonal= 0;
        int[] sumRow= new int[board.length];
        int[] sumCol= new int[board.length];
        for (int i= 0; i<board.length; i++){
            for (int j=0; j<board[i].length; j++){
                if(i==j){
                    diagonal += board[i][j];
                }
                sumRow[i] += board[i][j];
                sumCol[i] += board[j][i];
            }
        }
        for (int x=0; x<board.length; x++){
            if (sumRow[x] != wert || sumCol[x]!= wert){
                return false;
            }
        }
        if (diagonal != wert){
            System.out.println("Diagonal "+diagonal);
            return false;
        }
        return true;
    }

}
