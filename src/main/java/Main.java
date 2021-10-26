public class Main {
    public static void main(String args[]){
        SquareMatrix squareMatrix1 = SquareMatrix.createRandomMatrix(2);
        squareMatrix1.display();
        System.out.println(" ");
        SquareMatrix squareMatrix2 = SquareMatrix.createRandomMatrix(2);
        squareMatrix2.display();
        System.out.println(" ");
       squareMatrix1.quickProduct(squareMatrix2);






    }
}
