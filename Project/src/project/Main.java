package project;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner scan = new Scanner(System.in);

        //PrintHello(); //приветственная надпись для игрока, правила игры
        //PlayerSpace.outputPlayerArray();


        System.out.print("Поле для ввода: ");
        String l = scan.next();
        if (l.equals("start")) {
            //начинаем игру
            Player player = new Player(); //воздаем фигурку игрока
            player.ShowHpResult();

            //player.writeResult(9);
            //Gamespace.outputPixelArray(); //вывод в консоль игрового поля
            //player.Jump();
        }
    }

    public static void PrintHello(){
        System.out.println("Приветствую тебя, игрок! "+"\n"+
                "Ты попал в игру Mario"+"\n"+
                "Хочешь начать новую игру? - введи start"+"\n"+
                "Хочешь узнать свой рекорд за все предыдущие игры? - введи result"+"\n"+
                "Хорошей игры <3");
    }
}