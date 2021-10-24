public class SquareMatrix {
    private int dimension;
    private int[][] matrix;

    public SquareMatrix(int initDimension) {
        this.dimension = initDimension;
        this.matrix = new int[this.dimension][this.dimension];
    }

    public int getMatrixDimension() {
        return this.dimension;
    }

    public int get(int row, int col) {
        return this.matrix[row][col];
    }

    public void set(int row, int col, int value) {
        this.matrix[row][col] = value;
    }

    public SquareMatrix getSubMatrix(int firstRow, int firstColumn, int dim) {
        SquareMatrix subMatrix = new SquareMatrix(dim);
        for (int i = firstRow; i < firstRow + dim; i++) {
            for (int j = firstColumn; j < firstColumn + dim; j++) {
                subMatrix.set(i, j, this.get(i, j));
            }
        }
        return subMatrix;
    }

    public SquareMatrix setSubMatrix(SquareMatrix matrix, int row, int column) {
        for (int i = row; i < row + matrix.dimension; i++) {
            for (int j = column; j < column + matrix.dimension; j++) {
                this.set(i, j, matrix.get(i, j));
            }
        }
        return this;
    }

    public SquareMatrix sum(SquareMatrix matrix) {
        SquareMatrix sumSquareMatrix = new SquareMatrix(this.dimension);
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                sumSquareMatrix.set(i, j, this.get(i, j) + matrix.get(i, j));
            }
        }
        return sumSquareMatrix;
    }

    public SquareMatrix subtract(SquareMatrix matrix) {
        SquareMatrix subtractSquareMatrix = new SquareMatrix(this.dimension);
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                subtractSquareMatrix.set(i, j, this.get(i, j) - matrix.get(i, j));
            }
        }

        return subtractSquareMatrix;
    }

    public SquareMatrix product(SquareMatrix matrix) {
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                int product = 0;
                for (int k = 0; k < this.dimension; k++) {
                    product += this.get(i, k) * matrix.get(k, j);
                }
                this.set(i, j, product);
            }
        }
        return this;
    }

    public static SquareMatrix identity(int dimension) {
        SquareMatrix identityMatrix = new SquareMatrix(dimension);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (i == j) {
                    identityMatrix.matrix[i][j] = 1;
                }
            }
        }
        return identityMatrix;
    }

    public SquareMatrix power(int n) {
        if (n == 0) {
            return SquareMatrix.identity(this.dimension);
        } else {
            return this.product(this.power(n - 1));
        }
    }

    public SquareMatrix quickPower(int n) {
        if (n == 0) {
            return SquareMatrix.identity(this.dimension);
        } else if (n % 2 == 0 && n > 1) {
            return this.product(this).quickPower(n / 2);
        } else if (n % 2 != 0 && n > 1) {
            return this.product(this.product(this).quickPower((n-1)/2));
        } else {
            return null;
        }
    }
}
