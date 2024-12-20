public class Matrix {
    private final int[][] board;
    private final int rows;
    private final int cols;
    private int[][] directions;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new int[rows][cols];
        this.directions = new int[][] {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},        {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int[][] getBoard() {
        return board;
    }

    public int getValue(int row, int col) {
        return board[row][col];
    }

    public void setValue(int row, int col, int value) {
        board[row][col] = value;
    }

    public void showResult() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j] == 1 ? '*' : '.');
            }
            System.out.println();
        }
    }

    public int countNeighbors(int i, int j, int neighborOption) {
        int count = 0;
        neighborSelected(neighborOption);

        // Iteramos sobre todas las direcciones posibles
        for (int[] direction : directions) {
            // Calculamos las coordenadas del vecino
            int row = i + direction[0]; // Calcula la fila del vecino
            int col = j + direction[1]; // Calcula la columna del vecino

            // Verificar que los vecinos estén dentro de los limites del tablero
            if (row >= 0 && row < rows && col >= 0 && col < cols) {
                count += board[row][col] == 1 ? 1 : 0; // Contar si la celula vecina esta viva
            }
        }
        // Devuelve el número total de vecinos vivos
        return count;
    }

    public void neighborSelected(int option) {
        switch (option) {
            case 1:
                directions = new int[][] {
                        {-1, -1}, {-1, 0}, {-1, 1}, // Vecinos de la fila superior
                        {0, -1},        {0, 1},     // Vecinos del lado izquierdo y derecho
                        {1, -1}, {1, 0}, {1, 1}     // Vecinos de la fila inferior
                };
            case 2:
                directions = new int[][] {
                        {-1, 0}, // Arriba
                        {1, 0},  // Abajo
                        {0, -1}, // Izquierda
                        {0, 1}   // Derecha
                };
            case 3:
                directions = new int[][] {
                        {-1, -1}, {-1, 1}, // Diagonales superiores
                        {1, -1},  {1, 1}   // Diagonales inferiores
                };
            case 4:
                directions = new int[][] {
                        {-1, 0}, // Arriba
                        {1, 0},  // Abajo
                        {-1, -1}, // Diagonal izquierda arriba
                        {1, 1}   // Diagonal derecha abajo
                };
            case 5:
                directions = new int[][] {
                        {-1, 0},   // Arriba
                        {1, 0},    // Abajo
                        {-1, 1},   // Arriba derecha
                        {1, -1},   // Abajo izquierda
                        {-1, -1},  // Arriba izquierda
                        {1, 1}     // Abajo derecha
                };
            default:
                directions = new int[][] {
                        {-1, -1}, {-1, 0}, {-1, 1},
                        {0, -1},        {0, 1},
                        {1, -1}, {1, 0}
                };
        }
    }
}
