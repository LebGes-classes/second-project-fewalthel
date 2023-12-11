package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Player {
    public int hp; //значение здоровья игрока
    public static String [][] position; //массив в координатами игрока
    public int result; //результаты катки

    public static int CounterOfRecord = 0; //счётчик, который считает шаги и по окончании игры записывает количество пройденных шагов в текстовый файл

    //заполняем массив, в каждой ячейке которого содержится координата пикселя массива игрока в начальный момент игры
    public String[][] ZapolnitMassiv() {
        String[][] array = new String[16][12];
        int cnt_i = 12;
        int cnt_j = 2;
        for (int i = 0; i < 16; i ++) { //цикл будет 16 раз - именно столько пикселей в высоту изображение игрока
            for (int j = 0; j < 12; j++) { //цикл будет 12 раз - именно столько пикселей в ширину изображение игрока
                String s1 = Integer.toString(cnt_i);
                String s2 = Integer.toString(cnt_j);
                String index =s1  +" "+s2;
                array[i][j] = index;
                cnt_j++;
            }
            cnt_i++;
            cnt_j = 2;
        }
        return array;
    }


    //гетеры
    public int getHp () {return hp;}
    public static String[][] getPosition() {return position;}

    public int getResult() {return result;}


    //конструктор экземпляра класса игрока
    Player () {
        this.hp = 10;
        this.result = 0;
        this.position = ZapolnitMassiv();
    }


    public static int getKoordinataI(String[][] array, int x, int y){ //метод который возвращает значение по i в массиве
        //x и y - индексы, по которым находится координата в массиве
        String s = array[x][y];
        String[] words = s.split(" ");
        String ch = words[0];
        int i = Integer.parseInt(ch);
        return i;
    }

    public static int getKoordinataJ(String[][] array, int x, int y){ //метод который возвращает значение по j в массиве
        //x и y - индексы, по которым находится координата в массиве
        String s = array[x][y];
        String[] words = s.split(" ");
        int j = Integer.parseInt(words[1]);
        return j;
    }

    //методы для передвижения игрока(изменяем координаты)

    public void Jump() { //метод для прыжка игрока (двигаем вверх на 3 пикселя, затем вниз на 3 пикселя)
        CounterOfRecord++;
        String [][] player_array  = PlayerSpace.getPlayerArray();
        String [][] player_pose = getPosition();

        for (int count = 0; count < 3; count++) { //3 раза вверх

            String[][] map_array = new String[Gamespace.getPixelArray().length][Gamespace.getPixelArray()[0].length];

            for (int i = 0; i < Gamespace.getPixelArray().length; i++) {
                for (int j = 0; j < Gamespace.getPixelArray()[0].length; j++) {
                    map_array[i][j] = Gamespace.getPixelArray()[i][j];     //создаем дубликат массива карты, чтобы не изменялся исходный массив карты
                }
            }

            //меняем координаты игрока(по i - уменьшаем на 1, по j - оставляем прежним)
            //идем по массиву с координатами
            for (int i = 0; i < player_pose.length; i++) {
                for (int j = 0; j < player_pose[0].length; j++) {
                    int x = getKoordinataI(player_pose, i, j);
                    x--; //меняем значение координаты на 1
                    int y = getKoordinataJ(player_pose, i, j); //оставляем без изменений
                    String s1 = Integer.toString(x);
                    String s2 = Integer.toString(y);
                    player_pose[i][j] = s1 + " " + s2; //перезаписываем полученное значение в тот же массив
                }
            }


            //внедряем массив игрока с изменёнными кординатами в массив карты
            int cnt_i = 0;
            int cnt_j = 0;
            for (int i = 0; i < map_array.length; i++) {
                for (int j = 0; j < map_array[0].length; j++) {
                    if (cnt_j == 12) {
                        cnt_j = 0;
                        cnt_i++;
                        if (cnt_i == 16) {  //тут костыль, потому что без прописания этого индекс может быть 16 и выйти за рамки массива. как это фиксить я хз(
                            cnt_i = 15;
                        }
                    }
                    //если так получилось, что координаты игрока == координатам на карте: внедряем массив игрока в массив карты
                    if (getKoordinataI(player_pose, cnt_i, cnt_j) == i && getKoordinataJ(player_pose, cnt_i, cnt_j) == j) {
                        map_array[i][j] = player_array[cnt_i][cnt_j];
                        cnt_j++;
                    }

                }
            }

            //выводим итоговый массив на экран
            for (int i = 0; i < map_array.length; i++) {
                for (int j = 0; j < map_array[0].length; j++) {
                    System.out.print(map_array[i][j] + "██" + "\u001B[0m");
                }
                System.out.print("\n");
            }
        }

        for (int count = 0; count < 3; count++) { //3 раза вниз

            String[][] map_array = new String[Gamespace.getPixelArray().length][Gamespace.getPixelArray()[0].length];

            for (int i = 0; i < Gamespace.getPixelArray().length; i++) {
                for (int j = 0; j < Gamespace.getPixelArray()[0].length; j++) {
                    map_array[i][j] = Gamespace.getPixelArray()[i][j];     //создаем дубликат массива карты, чтобы не изменялся исходный массив карты
                }
            }

            //меняем координаты игрока(по i - уменьшаем на 1, по j - оставляем прежним)
            //идем по массиву с координатами
            for (int i = 0; i < player_pose.length; i++) {
                for (int j = 0; j < player_pose[0].length; j++) {
                    int x = getKoordinataI(player_pose, i, j);
                    x++; //меняем значение координаты на 1
                    int y = getKoordinataJ(player_pose, i, j); //оставляем без изменений
                    String s1 = Integer.toString(x);
                    String s2 = Integer.toString(y);
                    player_pose[i][j] = s1 + " " + s2; //перезаписываем полученное значение в тот же массив
                }
            }


            //внедряем массив игрока с изменёнными кординатами в массив карты
            int cnt_i = 0;
            int cnt_j = 0;
            for (int i = 0; i < map_array.length; i++) {
                for (int j = 0; j < map_array[0].length; j++) {
                    if (cnt_j == 12) {
                        cnt_j = 0;
                        cnt_i++;
                        if (cnt_i == 16) {
                            cnt_i = 15;
                        }
                    }
                    //если так получилось, что координаты игрока == координатам на карте: внедряем массив игрока в массив карты
                    if (getKoordinataI(player_pose, cnt_i, cnt_j) == i && getKoordinataJ(player_pose, cnt_i, cnt_j) == j) {
                        map_array[i][j] = player_array[cnt_i][cnt_j];
                        cnt_j++;
                    }

                }
            }

            //выводим итоговый массив на экран
            for (int i = 0; i < map_array.length; i++) {
                for (int j = 0; j < map_array[0].length; j++) {
                    System.out.print(map_array[i][j] + "██" + "\u001B[0m");
                }
                System.out.print("\n");
            }
        }
    }

    public void stepRight() { //метод для движения вправо игрока
        CounterOfRecord++;
        String[][] player_array = PlayerSpace.getPlayerArray();
        String[][] player_pose = getPosition();

        for (int count = 0; count < 3; count++) { //3 раза вправо
            String[][] map_array = new String[Gamespace.getPixelArray().length][Gamespace.getPixelArray()[0].length];

            for (int i = 0; i < Gamespace.getPixelArray().length; i++) {
                for (int j = 0; j < Gamespace.getPixelArray()[0].length; j++) {
                    map_array[i][j] = Gamespace.getPixelArray()[i][j];     //создаем дубликат массива карты, чтобы не изменялся исходный массив карты
                }
            }

            //меняем координаты игрока(по i - оставляем прежним, по j - увеличиваем на 1)
            //идем по массиву с координатами
            for (int i = 0; i < player_pose.length; i++) {
                for (int j = 0; j < player_pose[0].length; j++) {
                    int x = getKoordinataI(player_pose, i, j); // оставляем без изменений
                    int y = getKoordinataJ(player_pose, i, j);
                    //меняем значение координаты на 1
                    y++;
                    String s1 = Integer.toString(x);
                    String s2 = Integer.toString(y);
                    player_pose[i][j] = s1 + " " + s2; //перезаписываем полученное значение в тот же массив
                }
            }


            //внедряем массив игрока с изменёнными кординатами в массив карты
            int cnt_i = 0;
            int cnt_j = 0;
            for (int i = 0; i < map_array.length; i++) {
                for (int j = 0; j < map_array[0].length; j++) {
                    if (cnt_j == 12) {
                        cnt_j = 0;
                        cnt_i++;
                        if (cnt_i == 16) {
                            cnt_i = 15;
                        }
                    }
                    //если так получилось, что координаты игрока == координатам на карте: внедряем массив игрока в массив карты
                    if (getKoordinataI(player_pose, cnt_i, cnt_j) == i && getKoordinataJ(player_pose, cnt_i, cnt_j) == j) {
                        map_array[i][j] = player_array[cnt_i][cnt_j];
                        cnt_j++;
                    }

                }
            }

            //выводим итоговый массив на экран
            for (int i = 0; i < map_array.length; i++) {
                for (int j = 0; j < map_array[0].length; j++) {
                    System.out.print(map_array[i][j] + "██" + "\u001B[0m");
                }
                System.out.print("\n");
            }
        }
    }


    public void stepLeft() { //метод для движения игрока влево
        CounterOfRecord++;
        String[][] player_array = PlayerSpace.getPlayerArray();
        String[][] player_pose = getPosition();

        for (int count = 0; count < 3; count++) { //3 раза вправо
            String[][] map_array = new String[Gamespace.getPixelArray().length][Gamespace.getPixelArray()[0].length];

            for (int i = 0; i < Gamespace.getPixelArray().length; i++) {
                for (int j = 0; j < Gamespace.getPixelArray()[0].length; j++) {
                    map_array[i][j] = Gamespace.getPixelArray()[i][j];     //создаем дубликат массива карты, чтобы не изменялся исходный массив карты
                }
            }

            //меняем координаты игрока(по i - оставляем прежним, по j - уменьшаем на 1)
            //идем по массиву с координатами
            for (int i = 0; i < player_pose.length; i++) {
                for (int j = 0; j < player_pose[0].length; j++) {
                    int x = getKoordinataI(player_pose, i, j); // оставляем без изменений
                    int y = getKoordinataJ(player_pose, i, j);
                    //меняем значение координаты на 1
                    y--;
                    String s1 = Integer.toString(x);
                    String s2 = Integer.toString(y);
                    player_pose[i][j] = s1 + " " + s2; //перезаписываем полученное значение в тот же массив
                }
            }


            //внедряем массив игрока с изменёнными кординатами в массив карты
            int cnt_i = 0;
            int cnt_j = 0;
            for (int i = 0; i < map_array.length; i++) {
                for (int j = 0; j < map_array[0].length; j++) {
                    if (cnt_j == 12) {
                        cnt_j = 0;
                        cnt_i++;
                        if (cnt_i == 16) {
                            cnt_i = 15;
                        }
                    }
                    //если так получилось, что координаты игрока == координатам на карте: внедряем массив игрока в массив карты
                    if (getKoordinataI(player_pose, cnt_i, cnt_j) == i && getKoordinataJ(player_pose, cnt_i, cnt_j) == j) {
                        map_array[i][j] = player_array[cnt_i][cnt_j];
                        cnt_j++;
                    }

                }
            }

            //выводим итоговый массив на экран
            for (int i = 0; i < map_array.length; i++) {
                for (int j = 0; j < map_array[0].length; j++) {
                    System.out.print(map_array[i][j] + "██" + "\u001B[0m");
                }
                System.out.print("\n");
            }
        }
    }

    //метод для прыжка вправо по диагонали игрока
    public void JumpRight() {
        CounterOfRecord++;
        String [][] player_array  = PlayerSpace.getPlayerArray();
        //String [][] map_array = Gamespace.getPixelArray();
        String [][] player_pose = getPosition();

    }

    //метод для прыжка вправо по диагонали игрока
    public void JumpLeft() {
        CounterOfRecord++;
        String [][] player_array  = PlayerSpace.getPlayerArray();
        String [][] map_array = Gamespace.getPixelArray();
        String [][] player_pose = getPosition();

    }

    public static void OutputPlayerWithUnderground() {
        String [][] player_array  = PlayerSpace.getPlayerArray();
        String [][] map_array = Gamespace.getPixelArray();
        String [][] player_pose = getPosition(); //массив с координатами игрока
        //внедряем массив игрока в массив карты
        int cnt_i = 0;
        int cnt_j = 0;
        for (int i = 0; i < map_array.length; i++) {
            for (int j = 0; j < map_array[0].length; j++) {
                if (cnt_j == 12) {
                    cnt_j = 0;
                    cnt_i++;
                    if (cnt_i == 16) {
                        cnt_i = 15;
                    }
                }
                //если так получилось, что координаты игрока == координатам на карте: внедряем массив игрока в массив карты
                if (getKoordinataI(player_pose, cnt_i, cnt_j) == i && getKoordinataJ(player_pose, cnt_i, cnt_j) == j) {
                    map_array[i][j] = player_array[cnt_i][cnt_j];
                    cnt_j++;
                }

            }
        }

        //выводим итоговый массив на экран
        for (int i = 0; i < map_array.length; i++) {
            for (int j = 0; j < map_array[0].length; j++) {
                System.out.print(map_array[i][j] + "██" + "\u001B[0m");
            }
            System.out.print("\n");
        }
    }

    //метод для вывода в консоль показателей игрока
    public void ShowHpResult() {
        System.out.println("HP: "+ getHp());
        System.out.println("Your result: "+ getResult());
        System.out.println("Best result: "+getRecord());

    }

    //метод для записи результатов игры в файл
    public void writeResult(int result) { //метод принимает на вход результат игры
        try {
            FileWriter writer = new FileWriter("C:\\Users\\User\\Documents\\GitHub\\second-project-fewalthel\\Project\\src\\project\\text.txt", true);
            //записываем результат катки с новой строки
            writer.write("\n"+result);
            writer.close();

        } catch (IOException e) {
            System.out.print("Ошибка");
        }
    }

    //метод который считывает файл и возвращает лучший результат
    public static int getRecord() {
        try {
            // Создание объекта FileReader
            FileReader fr = new FileReader("C:\\Users\\User\\Documents\\GitHub\\second-project-fewalthel\\Project\\src\\project\\text.txt");

            // Создание объекта BufferedReader
            BufferedReader br = new BufferedReader(fr);

            // Инициализация переменной для хранения наибольшего числа
            int maxNumber = Integer.MIN_VALUE;

            // Чтение файла и поиск наибольшего числа
            String line;
            while ((line = br.readLine()) != null) {
                int number = Integer.parseInt(line);
                if (number > maxNumber) {
                    maxNumber = number;
                }
            }

            fr.close();
            // Вывод наибольшего числа
            return maxNumber;

        } catch (IOException e) {
            return 0;
        }
    }
}