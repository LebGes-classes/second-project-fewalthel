package project;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


//класс с массивом игрока
public class PlayerSpace {

    //метод который возвращает массив игрока
    public static String[][] getPlayerArray() {
        try {
            // Загрузка изображения
            BufferedImage image = ImageIO.read(new File("C:\\Users\\User\\Documents\\GitHub\\second-project-fewalthel\\Project\\src\\project\\mario.jpg"));

            // Получение ширины и высоты изображения
            int width = image.getWidth(); //ширина
            int height = image.getHeight(); //высота

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
            for (int x = 0; x < height; x++) {
                for (int y = 0; y < width; y++) {
                    Color color = new Color(pixels[x][y]);
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

}