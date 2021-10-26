import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class SquareMatrixTest {

    @Test
    public void testProduct(){
        SquareMatrix squareMatrix1 = new SquareMatrix(2);
        SquareMatrix squareMatrix2 = new SquareMatrix(2);
        SquareMatrix resultMatrix = new SquareMatrix(2);
        squareMatrix1.set(0,0,1);
        squareMatrix1.set(0,1,2);
        squareMatrix1.set(1,0,3);
        squareMatrix1.set(1,1,4);
        squareMatrix2.set(0,0,5);
        squareMatrix2.set(0,1,6);
        squareMatrix2.set(1,0,7);
        squareMatrix2.set(1,1,8);
        resultMatrix.set(0,0,19);
        resultMatrix.set(0,1,22);
        resultMatrix.set(1,0,43);
        resultMatrix.set(1,1,50);
        squareMatrix1.product(squareMatrix2);
        assertThat(squareMatrix1.get(0,0)).isEqualTo(resultMatrix.get(0,0));
        assertThat(squareMatrix1.get(0,1)).isEqualTo(resultMatrix.get(0,1));
        assertThat(squareMatrix1.get(1,0)).isEqualTo(resultMatrix.get(1,0));
        assertThat(squareMatrix1.get(1,1)).isEqualTo(resultMatrix.get(1,1));
    }

    @Test
    public void testPower(){
        SquareMatrix squareMatrix1 = new SquareMatrix(2);
        squareMatrix1.set(0,0,1);
        squareMatrix1.set(0,1,2);
        squareMatrix1.set(1,0,3);
        squareMatrix1.set(1,1,4);
        assertThat(squareMatrix1.power(3)).isEqualTo(squareMatrix1.product(squareMatrix1.product(squareMatrix1)));
    }

    @Test
    public void testQuickPower(){
        SquareMatrix squareMatrix1 = new SquareMatrix(2);
        squareMatrix1.set(0,0,1);
        squareMatrix1.set(0,1,2);
        squareMatrix1.set(1,0,3);
        squareMatrix1.set(1,1,4);
        assertThat(squareMatrix1.quickPower(2)).isEqualTo(squareMatrix1.product(squareMatrix1.product(squareMatrix1)));
    }

}
