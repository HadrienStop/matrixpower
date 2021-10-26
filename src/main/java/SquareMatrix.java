import java.util.Random;

public class SquareMatrix {
    private int dimension;
    private int[][] matrix;
    private int addAndSubCount;
    private int multiplicationsCount;
    private int allCount;

    public SquareMatrix(int initDimension) {
        this.dimension = initDimension;
        this.matrix = new int[this.dimension][this.dimension];
        this.multiplicationsCount = 0;
        this.addAndSubCount = 0;
        this.allCount = 0;
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

    public int getAddAndSubCount() {
        return addAndSubCount;
    }

    public int getMultiplicationsCount() {
        return multiplicationsCount;
    }

    public int getAllCount() {
        return allCount;
    }

    public SquareMatrix getSubMatrix(int firstRow, int firstColumn, int dim) {
        SquareMatrix subMatrix = new SquareMatrix(dim);
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                subMatrix.set(i, j, this.get(i + firstRow, j + firstColumn));
            }
        }
        return subMatrix;
    }

    public void setSubMatrix(SquareMatrix matrix, int row, int column) {
        for (int i = row; i < row + matrix.dimension; i++) {
            for (int j = column; j < column + matrix.dimension; j++) {
                this.set(i, j, matrix.get(i, j));
            }
        }
    }

    public SquareMatrix sum(SquareMatrix matrix) {
        this.addAndSubCount += this.dimension*this.dimension;
        this.allCount += this.dimension*this.dimension;
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                this.set(i, j, this.get(i, j) + matrix.get(i, j));
            }
        }
        return this;
    }

    public SquareMatrix subtract(SquareMatrix matrix) {
        this.addAndSubCount += this.dimension*this.dimension;
        this.allCount += this.dimension*this.dimension;
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                this.set(i, j, this.get(i, j) - matrix.get(i, j));
            }
        }
        return this;
    }

    public SquareMatrix product(SquareMatrix matrix) {
        SquareMatrix newProductMatrix = new SquareMatrix(this.dimension);
        for (int i = 0; i < this.dimension ; i++) {
            for (int j = 0; j < this.dimension; j++) {
                int product = 0;
                for (int k = 0; k < this.dimension; k++) {
                    product += this.matrix[i][k] * matrix.matrix[k][j];
                    this.addAndSubCount += 1;
                    this.multiplicationsCount += 1;
                    this.allCount += 1;
                }
                newProductMatrix.set(i, j, product);
            }
        }
        this.matrix = newProductMatrix.matrix;
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
        SquareMatrix newSquareMatrix = new SquareMatrix(this.dimension);
        newSquareMatrix.matrix = this.matrix;
        if (n == 0) {
            return SquareMatrix.identity(this.dimension);
        } else {
            newSquareMatrix.product(newSquareMatrix.power(n - 1));
            this.matrix = newSquareMatrix.matrix;
            return this;
        }
    }

    public SquareMatrix quickPower(int n) {
        if (n % 2 == 0 && n > 1) {
            this.product(this).quickPower(n / 2);
        }
        else if (n % 2 != 0 && n > 1) {
            this.product(this.product(this).quickPower((n-1)/2));
        }
        else {
            SquareMatrix.identity(this.dimension);
        }
        return this;
    }

    public static SquareMatrix copyMatrix(SquareMatrix matrix) {
        SquareMatrix result = new SquareMatrix(matrix.dimension);
        for(int i = 0; i < matrix.dimension ; i++) {
            for(int j = 0; j < matrix.dimension ; j++) {
                result.set(i, j, matrix.get(i, j));
            }
        }
        return result;
    }

    public SquareMatrix quickProduct(SquareMatrix matrix){
        if(matrix.dimension == 1) {
            this.set(0, 0, this.get(0, 0) * matrix.get(0, 0));
            return this;
        }
        else if(this.dimension != matrix.dimension){
            return null;
        }
        else if(this.dimension % 2 != 0 && this.dimension != 1){
            SquareMatrix identityMatrix1 = SquareMatrix.identity(this.dimension  + 1);
            SquareMatrix identityMatrix2 = SquareMatrix.identity(this.dimension  + 1);
            identityMatrix1.setSubMatrix(this, 0, 0);
            identityMatrix1.setSubMatrix(matrix, 0, 0);
            identityMatrix1.quickProduct(identityMatrix2);
            this.setSubMatrix(identityMatrix1,0, 0);
            return this;
        }
        else {
            SquareMatrix A1 = this.getSubMatrix(0, 0, this.dimension / 2);
            SquareMatrix B1 = this.getSubMatrix(0, this.dimension / 2, this.dimension / 2);
            SquareMatrix C1 = this.getSubMatrix(this.dimension / 2, 0, this.dimension / 2);
            SquareMatrix D1 = this.getSubMatrix(this.dimension / 2, this.dimension / 2, this.dimension / 2);

            SquareMatrix A2 = matrix.getSubMatrix(0, 0, this.dimension / 2);
            SquareMatrix B2 = matrix.getSubMatrix(0, this.dimension / 2, this.dimension / 2);
            SquareMatrix C2 = matrix.getSubMatrix(this.dimension / 2, 0, this.dimension / 2);
            SquareMatrix D2 = matrix.getSubMatrix(this.dimension / 2, this.dimension / 2, this.dimension / 2);

            SquareMatrix M1 = (SquareMatrix.copyMatrix(A1).sum(D1)).quickProduct(SquareMatrix.copyMatrix(A2).sum(D2));
            SquareMatrix M2 = (SquareMatrix.copyMatrix(C1).sum(D1)).quickProduct(A2);
            SquareMatrix M3 = SquareMatrix.copyMatrix(A1).quickProduct(SquareMatrix.copyMatrix(B2).subtract(D2));
            SquareMatrix M4 = SquareMatrix.copyMatrix(D1).quickProduct(SquareMatrix.copyMatrix(C2).subtract(A2));
            SquareMatrix M5 = (SquareMatrix.copyMatrix(A1).sum(B1)).quickProduct(D2);
            SquareMatrix M6 = (SquareMatrix.copyMatrix(C1).subtract(A1)).quickProduct(SquareMatrix.copyMatrix(A2).sum(B2));
            SquareMatrix M7 = (SquareMatrix.copyMatrix(B1).subtract(D1)).quickProduct(SquareMatrix.copyMatrix(C2).sum(D2));

            this.setSubMatrix(SquareMatrix.copyMatrix(M1).sum(M4).subtract(M5).sum(M7), 0, 0);
            this.setSubMatrix(SquareMatrix.copyMatrix(M3).sum(M5), 0, dimension / 2);
            this.setSubMatrix(SquareMatrix.copyMatrix(M2).sum(M4), this.dimension / 2, 0);
            this.setSubMatrix(SquareMatrix.copyMatrix(M1).subtract(M2).sum(M3).sum(M6), this.dimension / 2, this.dimension / 2);

            return this;
        }
    }

    public SquareMatrix veryQuickPower(int n) {
        if (n % 2 == 0 && n > 1) {
            this.quickProduct(this).veryQuickPower(n / 2);
        }
        else if (n % 2 != 0 && n > 1) {
            this.quickProduct(this.quickProduct(this).veryQuickPower((n-1)/2));
        }
        else {
            SquareMatrix.identity(this.dimension);
        }
        return this;
    }

    public static SquareMatrix createRandomMatrix(int dimension){
        Random random = new Random();
        SquareMatrix randomSquareMatrix = new SquareMatrix(dimension);
        for(int row = 0 ; row < dimension ; row++){
            for(int col = 0 ; col < dimension ; col++){
                randomSquareMatrix.set(row, col, -10 + random.nextInt(21));
            }
        }
        return randomSquareMatrix;
    }
}
