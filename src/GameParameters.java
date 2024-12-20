public class GameParameters {
    public int rows;
    public int cols;
    public int generations;
    public int speed;
    public String pattern;
    public int neighborhood;
    public Matrix matrix;

    public GameParameters() {
    }

    public GameParameters(int rows, int cols, int generations, int speed, String pattern, int neighborhood, Matrix matrix) {
        this.rows = rows;
        this.cols = cols;
        this.generations = generations;
        this.speed = speed;
        this.pattern = pattern;
        this.neighborhood = neighborhood;
        this.matrix = matrix;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getGenerations() {
        return generations;
    }

    public void setGenerations(int generations) {
        if(generations >= 0) {
            this.generations = generations;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if(speed >= 250 && speed <= 1000) {
            this.speed = speed;
        }
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        if(pattern != null && !pattern.isEmpty()) {
            this.pattern = pattern;
        }
    }

    public int getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(int neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public void setMatrix(Matrix matrix) {
        if(matrix != null) {
            this.matrix = matrix;
        }
    }
}
