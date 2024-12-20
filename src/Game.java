public class Game {
    private GameParameters gamesParams;

    public void startGame(String[] args) {
        ExecuteGOL optionExecute = new ExecuteGOL();
        gamesParams = optionExecute.createMatrixWithParams(args);

        if(gamesParams != null && gamesParams.getMatrix() != null) {
            System.out.println("***** Matriz de Inicio *****");
            gamesParams.getMatrix().showResult();
            runGenerations();
        } else {
            System.out.println("Error al inicializar el juego. Verifica tus parametros.");
        }

    }

    private void runGenerations() {
        int maxGenerations = gamesParams.getGenerations() == 0 ? Integer.MAX_VALUE : gamesParams.getGenerations();

        for (int i = 0; i < maxGenerations; i++) {
            try {
                Thread.sleep(gamesParams.getSpeed());
                applyRulesGame(gamesParams.getMatrix());

                System.out.println("Generacion " + (i + 1) + ":");
                gamesParams.getMatrix().showResult();

                if(matrixAllZeros(gamesParams.getMatrix())) {
                    System.out.println("¡ATENCIÓN! - Todas las celulas estan muertas. Fin del juego!");
                    break;
                }

                if (i % 10 == 0) {
                    regenerateCells();
                }

            } catch (InterruptedException e) {
                System.out.println("Error en la generacion entre generaciones: " + e.getMessage());
            }
        }
    }

    public void applyRulesGame(Matrix matrix) {
        int rows = matrix.getRows();
        int cols = matrix.getCols();

        int[][] board = matrix.getBoard();
        int[][] nextGeneration = new int[rows][cols];

        matrix.neighborSelected(gamesParams.getNeighborhood());

        // Recorro las matriz para aplicar las reglas del juego
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int liveNeighbors = matrix.countNeighbors(i, j, gamesParams.getNeighborhood()); // Conteo de celulas vivas de la celda actual

                // Aplico las reglas del juego según el estado de la celda actual
                if (board[i][j] == 1) {  // Célula viva
                    // Regla 1: Una célula viva con menos de 2 vecinos vivos muere
                    if (liveNeighbors < 2) {
                        nextGeneration[i][j] = 0;  // Muere por subpoblación
                        // Regla 3: Una célula viva con más de 3 vecinos vivos muere
                    } else if (liveNeighbors > 3) {
                        nextGeneration[i][j] = 0;  // Muere por sobrepoblación
                    } else {
                        nextGeneration[i][j] = 1;  // Sobrevive (2 o 3 vecinos vivos)
                    }
                } else {  // Célula muerta
                    // Regla 4: Una célula muerta con exactamente 3 vecinos vivos se convierte en viva
                    if (liveNeighbors == 3) {
                        nextGeneration[i][j] = 1;  // Nace una nueva célula
                    } else {
                        nextGeneration[i][j] = 0;  // Sigue muerta
                    }
                }
            }
        }

        // Actualizo la matriz original con los valores de la siguiente generacion
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix.setValue(i, j, nextGeneration[i][j]);
            }
        }
    }

    private void regenerateCells() {
        int rows = gamesParams.getMatrix().getRows();
        int cols = gamesParams.getMatrix().getCols();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (Math.random() < 0.05) { // 5% de probabilidad de regeneración
                    gamesParams.getMatrix().setValue(row, col, 1);
                }
            }
        }
    }

    public static boolean matrixAllZeros(Matrix matrix) {
        int rows = matrix.getRows();
        int cols = matrix.getCols();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(matrix.getValue(i, j) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
