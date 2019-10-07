import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

public class Triangle {
    //Координаты вершин треугольника
    private BigInteger coordinateAX;
    private BigInteger coordinateAY;

    private BigInteger coordinateBX;
    private BigInteger coordinateBY;

    private BigInteger coordinateCX;
    private BigInteger coordinateCY;

    //Длины сторон треугольника
    private BigDecimal lengthAB;
    private BigDecimal lengthBC;
    private BigDecimal lengthCA;

    private BigDecimal square;
    private boolean isIsosceles;

    public Triangle(BigInteger coordinateAX, BigInteger coordinateAY
            , BigInteger coordinateBX, BigInteger coordinateBY
            , BigInteger coordinateCX, BigInteger coordinateCY) {
        this.coordinateAX = coordinateAX;
        this.coordinateAY = coordinateAY;
        this.coordinateBX = coordinateBX;
        this.coordinateBY = coordinateBY;
        this.coordinateCX = coordinateCX;
        this.coordinateCY = coordinateCY;
        lengthAB = length(coordinateAX, coordinateAY, coordinateBX, coordinateBY);
        lengthBC = length(coordinateBX, coordinateBY, coordinateCX, coordinateCY);
        lengthCA = length(coordinateCX, coordinateCY, coordinateAX, coordinateAY);

        //Проверка на треугольник
        if (!((lengthAB.compareTo(lengthBC.add(lengthCA)) < 0)
                && (lengthBC.compareTo(lengthCA.add(lengthAB)) < 0)
                && (lengthCA.compareTo(lengthAB.add(lengthBC)) < 0))){
            throw new RuntimeException();
        }

        // Проверка на равнобедренность/равносторонность
        Set<BigDecimal> tmp = new HashSet<>();
        tmp.add(lengthAB);
        tmp.add(lengthBC);
        tmp.add(lengthCA);
        if (tmp.size() < 3) {
            isIsosceles = true;
            square = square();
        }
    }

    public BigDecimal getSquare() {
        return square;
    }

    public boolean isIsosceles() {
        return isIsosceles;
    }

    private BigDecimal length(BigInteger x1, BigInteger y1, BigInteger x2, BigInteger y2){
        return new BigDecimal(x1.subtract(x2)).pow(2).add(new BigDecimal(y1.subtract(y2)).pow(2)).sqrt(MathContext.DECIMAL32);
    }

    private BigDecimal square(){
        BigDecimal p = (lengthAB.add(lengthBC).add(lengthCA)).divide(new BigDecimal(2),RoundingMode.HALF_DOWN);
        return p.multiply(p.subtract(lengthAB)).multiply(p.subtract(lengthBC)).multiply(p.subtract(lengthCA)).sqrt(MathContext.DECIMAL32);
    }

    @Override
    public String toString() {
        return coordinateAX + " " + coordinateAY + " "
                + coordinateBX + " " + coordinateBY + " "
                + coordinateCX + " " + coordinateCY;
    }
}
