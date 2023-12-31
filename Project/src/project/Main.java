package project;

import java.util.Scanner;

//Игра МАРИО
//функционал: игрок может передвигаться по карте влево, вправо, прыгать
//также программа считает количество сделанных шагов и записывает их в текстовый файл

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        PrintHello(); //приветственная надпись для игрока, правила игры

        System.out.println("Поле для ввода: ");
        String l = scan.next();
        if (l.equals("start")) {
            //начинаем игру
            Gamespace.outputPixelArray(); //печатаем карту БЕЗ игрока
            Player player = new Player(); //воздаем фигурку игрока
            player.ShowHpResult();
            Player.OutputPlayerWithUnderground();
            boolean flag = true;
            while (flag) {
                PrintPossible();
                Scanner scann = new Scanner(System.in);
                boolean flag2 = true;
                while (flag2) {
                    String possible = scann.next();
                    switch (possible) {
                        case "D": System.out.println("\033[H\033[2J"); player.stepRight(); flag2 = false; break;
                        case "A": System.out.println("\033[H\033[2J"); player.stepLeft(); flag2 = false; break;
                        case "W": System.out.println("\033[H\033[2J"); player.Jump(); flag2 = false; break;
                        case "E": System.out.println("\033[H\033[2J"); player.JumpRight(); flag2 = false; break;
                        case "Q": System.out.println("\033[H\033[2J"); player.JumpLeft(); flag2 = false; break;
                        case "stop":
                            System.out.println("Игра окончена");
                            player.writeResult(Player.CounterOfRecord); //записываем результат игры в текстовый файл
                            flag = false; flag2 = false; break;
                        default: System.out.println("Такого действия нет, выбери что-то из предложенного:");
                    }
                }
            }

        } else if (l.equals("result")){
            System.out.println("Ваш рекорд: "+Player.getRecord());
        } else {
            System.out.println("Жаль, что ты не хочешь начать игру :(" +
                    "\nвозвращайся в следующий раз!");
        }
    }

    public static void PrintHello(){
        System.out.println("Приветствую тебя, игрок!\n" +
                        "Ты попал в игру Mario\n" +
                        "Хочешь начать новую игру? - введи start\n" +
                        "Хочешь узнать свой рекорд за все предыдущие игры? - введи result\n" +
                        "Хорошей игры <3");
    }

    public static void PrintPossible() {
        System.out.println("Ты можешь совершать следующие действия: \n" +
                "шаг вправо - D \n" +
                "шаг влево - A \n" +
                "прыжок - W \n" +
                "прыжок вправо по диагонали- E \n" +
                "прыжок влево по диагонали - Q \n" +
                "\n" +
                "закончить игру - stop");
    }
}