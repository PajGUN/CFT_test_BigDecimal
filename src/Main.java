import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        BigDecimal square = BigDecimal.ZERO;
        Triangle biggest = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))){
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]))) {
                String line;
                while ((line = reader.readLine()) != null){
                    String[] xy = line.split(" ");
                    if (xy.length == 6){
                        try {
                            Triangle triangle = new Triangle(new BigInteger(xy[0]), new BigInteger(xy[1]), new BigInteger(xy[2])
                                    ,new BigInteger(xy[3]), new BigInteger(xy[4]), new BigInteger(xy[5]));
                            if (triangle.isIsosceles() && triangle.getSquare().compareTo(square) > 0) {
                                biggest = triangle;
                                square = triangle.getSquare();
                            }
                        } catch (RuntimeException ignored){}
                    }
                }
                if (biggest != null) {
                    writer.write(biggest.toString());
                } else writer.write("");

            } catch (ArrayIndexOutOfBoundsException  e) {
                System.out.println("Не указан путь для файла с результатом");
            } catch (IOException e){
                System.out.println("Ошибка записи в файл!");
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Не указан путь входного файла с координатами");
        } catch (FileNotFoundException e){
            System.out.println("Входноый файл не найден, проверьте путь");
        } catch (IOException e) {
            System.out.println("Ошибка чтения из файла!");
        }
    }
}
