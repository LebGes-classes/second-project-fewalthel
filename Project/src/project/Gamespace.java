package project;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

//класс с массивом игрового поля
public class Gamespace {

    //метод который возвращает массив игрового поля
    public static String[][] getPixelArray() {
        try {
            // Загрузка изображения
            BufferedImage image = ImageIO.read(new File("C:\\Users\\User\\Documents\\GitHub\\second-project-fewalthel\\Project\\src\\project\\map.jpg"));
            int width = image.getWidth();
            int height = image.getHeight();

            // Создание двумерного массива для хранения пикселей
            int[][] pixels = new int[height][width];

            // Чтение пикселей из изображения и запись их в двумерный массив
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    pixels[y][x] = image.getRGB(x, y);
                }
            }

            String [][] pix = new String[height][width];

            // перезапись массива
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Color color = new Color(pixels[y][x]);
                    String s = "\u001B[38;2;" + color.getRed() + ";" + color.getGreen() + ";" + color.getBlue() + "m";
                    pix[x][y] = s;
                }
            }
            return pix;
        } catch (Exception e) {
            System.out.println("Ошибка");
            return null;
        }
    }

    /*public static String[][] getPixelArray(String[][] pixel_array) { //создаем дубликат игрового поля
        int x = pixel_array.length;
        int y = pixel_array[0].length;
        String [][] array = new String[x][y];

        for (int i = 0; i < x; i ++) {
            for (int j = 0; j < y; j++) {
                array[i][j] = pixel_array[i][j];
            }
        }
        return array;
    }*/

    //вывод массива в консоль
    public static void outputPixelArray() {
        String [][] pix = getPixelArray();
        int height = pix.length;
        int width = pix[0].length;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(pix[x][y]+"██"+"\u001B[0m");
            }
            System.out.println();
        }
    }
}
