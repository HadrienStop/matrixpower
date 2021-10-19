public class SquareMatrix {
    private int dimension;
    private int[][] matrix;

    public SquareMatrix(int initDimension){
        this.dimension = initDimension;
        this.matrix = new int[this.dimension][this.dimension];
    }

     public int getMatrixDimension(){
        return this.dimension;
     }

     public int get(int row, int col){
        return this.matrix[row][col];
     }

     public void set(int row, int col, int value){
        this.matrix[row][col] = value;
     }

     public SquareMatrix getSubMatrix(int firstRow, int firstColumn, int dim){
        SquareMatrix subMatrix = new SquareMatrix(dim);
        for(int i = firstRow ; i <= firstRow + dim-1 ; i++){
            for(int j = firstColumn ; j <= firstColumn + dim-1 ; j++){
                subMatrix.set(i, j, this.get(i, j));
            }
        }
        return subMatrix;
    }

    public SquareMatrix setSubMatrix(SquareMatrix matrix, int row, int column){
        for(int i = row ; i < row + matrix.dimension-1 ; i++){
            for(int j = column ; i < column + matrix.dimension-1 ; j++){

            }
        }
        return new SquareMatrix();
    }

}
