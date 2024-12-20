public class ExecuteGOL {

    public GameParameters createMatrixWithParams(String[] args) {
        String pattern = "";
        int width = 0, height = 0, generations = 0, speed = 0, neighborhood = 1;
        var params = new GameParameters();

        for (String arg : args) {
            if (arg.startsWith("w=")) width = parseIntOrDefault(arg.substring(2), 0);

            if (arg.startsWith("h=")) height = parseIntOrDefault(arg.substring(2), 0);

            if (arg.startsWith("g=")) generations = parseIntOrDefault(arg.substring(2), 0);

            if (arg.startsWith("s=")) speed = parseIntOrDefault(arg.substring(2), 0);

            if (arg.startsWith("p=")) pattern = arg.substring(2);

            if (arg.startsWith("n=")) neighborhood = parseIntOrDefault(arg.substring(2), 1);
        }

        String errors = validateWidthAndHeigth(width, height);
        if (!errors.isEmpty() || pattern.isEmpty() || generations < 0 || speed < 250 || speed > 1000) {
            System.out.println("¡ATENCIÓN! - Error en los parámetros:\n" + errors);
            System.out.println("Error en los parámetros. Asegúrese de que los valores sean positivos y el patrón no esté vacío.");

            System.out.println("""
                    1. El patron no debe estar vacio
                    2. El numero de generaciones debe ser un valor positivo
                    3. La velocidad debe estar en el rango de 250 a 1000 ms.
                    4. El alto y ancho de la matriz deben ser validos (Width: 10, 20, 40 o 80 // Height: 10, 20 o 40)
                    Por favor, ajusta los valores y vuelve a intentarlo
            """);
            params = new GameParameters(height, width, generations, speed, pattern, neighborhood, null);

            System.out.print("-".repeat(25));
            printGameArgs(params);
            return null;
        }

        int[][] values = parsePattern(pattern, height, width);
        Matrix matrix = createMatrix(height, width, values);

        params = new GameParameters(height, width, generations, speed, pattern, neighborhood, matrix);
        printGameArgs(params);

        return params;
    }

    public Matrix createMatrix(int rows, int cols, int[][] values) {
        Matrix matrix = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix.setValue(i, j, values[i][j]);
            }
        }
        return matrix;
    }

    public static String validateWidthAndHeigth(int width, int height) {
        StringBuilder errors = new StringBuilder();

        if (!(width == 10 || width == 20 || width == 40 || width == 80)) {
            errors.append("El Width debe ser 10, 20, 40 o 80\n");
        }

        if (!(height == 10 || height == 20 || height == 40)) {
            errors.append("El Height debe ser 10, 20 o 40.\n");
        }

        return errors.toString();
    }

    public static void printGameArgs(GameParameters gameParams) {
        System.out.println("-".repeat(25));
        System.out.println("Estos son tus argumentos para iniciar el juego: ");
        System.out.println("\twidth = [" + (gameParams.getCols() > 0 ? gameParams.getCols() : "Invalido") + "]");
        System.out.println("\theight = [" + (gameParams.getRows() > 0 ? gameParams.getRows() : "Invalido") + "]");
        System.out.println("\tgenerations = [" + (gameParams.getGenerations() > 0 ? gameParams.getGenerations() : "No Presente") + "]");
        System.out.println("\tspeed = [" + (gameParams.getSpeed() > 0 ? gameParams.getSpeed() : "No Presente") + "]");
        System.out.println("\tpopulation = [" + (!gameParams.getPattern().isEmpty() ? gameParams.getPattern() : "No Presente") + "]");
        System.out.println("\tneighborhood = " + (gameParams.getNeighborhood() > 0 ? gameParams.getNeighborhood() : "No Presente"));
        System.out.println("-".repeat(25));
    }

    public static int parseIntOrDefault(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static int[][] parsePattern(String pattern, int height, int width) {
        int[][] board = new int[height][width];

        if(pattern.equals("rnd")) {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    board[i][j] = Math.random() < 0.5 ? 0 : 1;
                }
            }
        } else {
            String[] rowsPattern = pattern.split("#");

            for (int i = 0; i < rowsPattern.length && i < height; i++) {
                String row = rowsPattern[i];
                for (int j = 0; j < row.length() && j < width; j++) {
                    board[i][j] = row.charAt(j) == '1' ? 1 : 0;
                }
            }

            for (int i = rowsPattern.length; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    board[i][j] = 0;
                }
            }
        }
        return board;
    }

}
