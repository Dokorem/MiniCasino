package Fun.Casino;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;

public class Casinio {
    private static int balance;
    private static int bid = 0;
    private static String arcadeChoose;
    private static int winnings = 0;
    private static boolean isWin;

    private static void setBalance(){
        try{
            Path path = Path.of("c:/Users/User/IdeaProjects/untitled2/src/Fun/Casino/balance.txt");
            String line = Files.readString(path);
            balance = Integer.parseInt(line);
        }catch (IOException e){
            throw new RuntimeException("Что то пошло не так при считывании кошелька");
        }
    }
    public static int getBalance(){
        return balance;
    }

    public static void main(String[] args) {
        setBalance();
        System.out.println("Ваш баланс - " + getBalance());
        getAnswer();
        setLogs();
        setBalanceAfterGame();
    }

    public static void getAnswer(){
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        while(true) {
            System.out.print("Введите сумму ставки - ");
            bid = scanner.nextInt();
            if(bid <= balance && bid != 0)
                break;
            else if(bid == 0)
                System.out.println("Нельзя поставить ничего, попробуйте еще раз");
            else{
                System.out.println("Ваша больше, чем ваш баланс, попробуйте еще раз!");
            }
        }
        while(true) {
            System.out.print("Введите в какой автомат вы хотите сыграть(Слоты, Рулетка, 21) - ");
            arcadeChoose = scanner1.nextLine();
            if (arcadeChoose.equalsIgnoreCase("Слоты")) {
                newSlots();
                arcadeChoose = "Слоты";
                break;
            } else if (arcadeChoose.equalsIgnoreCase("Рулетка")) {
                roulette();
                arcadeChoose = "Рулетка";
                break;
            } else if (arcadeChoose.equalsIgnoreCase("21")) {
                twentyOnePoint();
                arcadeChoose = "21 очко";
                break;
            } else {
                System.out.println("Такого ответа не существует, попробуйте что то другое");

            }
        }
    }
    private void setBid(int bid){this.bid = bid;}
    public int getBid(){return bid;}
    private void setArcadeChoose(String arcadeChoose){this.arcadeChoose = arcadeChoose;}
    public String getArcadeChoose(){return arcadeChoose;}
    public boolean getIsWin(){return isWin;}

    public static void roulette(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите на какое число хотите поставить - ");
        int value1 = scan.nextInt();
        int random = (int) (Math.random() * 14 + 1);
        if(random > 14){
            System.out.println("Вы ввели число больше 14! Крутите снова, ваша ставка аннулируется!");
        }else {
            if (value1 == random) {
                if (random == 14) {
                    winnings = bid * 10;
                    System.out.println("Выпало число 14, Вы соравли куш! Ваш выигрышь составляет - " + (winnings));
                    isWin = true;
                } else if (random % 2 != 0) {
                    winnings = bid * 2;
                    System.out.println("Выпало черное число " + random + " Ваша ставка удваивается! Ваш выигрышь составляет - " + (winnings));
                    isWin = true;
                } else {
                    winnings = bid * 2;
                    System.out.println("Выпало красное число " + random + " Ваша ставка удваивается! Ваш выигрышь составляет - " + (winnings));
                    isWin = true;
                }
            } else {
                System.out.print("Вам не повезло, вы проиграли! Выпало ");
                if (random % 2 != 0) {
                    System.out.print("черное число под номером - " + random);
                } else if (random == 14) {
                    System.out.print("зеленое число " + random);
                } else System.out.print("красное число под номером - " + random);
                isWin = false;
                winnings = 0;
            }
        }
    }

    public static void newSlots(){
        int firstRandomNumber = (int) (Math.random() * 100);
        int secondRandomNumber = (int) (Math.random() * 100);
        int thirdRandomNumber = (int) (Math.random() * 100);
        String[][] netOfSlots = {
                {" ","―","―","―","―","―"},
                {"│", " ", "│", " ", "│", " ", "│"},
                {" ","―","―","―","―","―"}
        };
        netOfSlots[1][1] = getNumberForSlots(firstRandomNumber);
        netOfSlots[1][3] = getNumberForSlots(secondRandomNumber);
        netOfSlots[1][5] = getNumberForSlots(thirdRandomNumber);

        for(String[] arr : netOfSlots){
            for(String s : arr){
                System.out.print(s);
            }
            System.out.println();
        }
        if(netOfSlots[1][1].equals(netOfSlots[1][3]) && netOfSlots[1][1].equals(netOfSlots[1][5])){
            if(netOfSlots[1][1].equals("\uD83C\uDF4B")){
                winnings = bid * 2;
                isWin = true;
                System.out.print("Поздравляю! Вы выиграли! Ваш выигрышь составляет - " +winnings);
            }else if(netOfSlots[1][1].equals("\uD83C\uDF52")){
                winnings = bid * 2;
                isWin = true;
                System.out.print("Поздравляю! Вы выиграли! Ваш выигрышь составляет - " +winnings);
            }else if(netOfSlots[1][1].equals("\uD83C\uDF51")){
                winnings = bid * 3;
                isWin = true;
                System.out.print("Поздравляю! Вы выиграли! Ваш выигрышь составляет - " +winnings);
            }else if(netOfSlots[1][1].equals("\uD83C\uDF1F")){
                winnings = bid * 10;
                isWin = true;
                System.out.print("Поздравляю! Вы сорвали куш! Ваш выигрышь составляет - " +winnings);
            }
        }else {
            winnings = 0;
            isWin = false;
            System.out.println("Увы, вы проиграли");
        }
    }
    private static String getNumberForSlots(int num){
        String emoji;
        if(num < 35){
            emoji = "\uD83C\uDF4B";
        }else if(num < 70) {
            emoji = "\uD83C\uDF52";
        }else if(num < 90) {
            emoji = "\uD83C\uDF51";
        }else if(num < 100) {
            emoji = "\uD83C\uDF1F";
        }else { emoji = "Хуй соси";}

        return emoji;
    }
    public static void twentyOnePoint(){
        int compSum = 0;
        int playerSum = 0;

        int firstCompCard = (int) (Math.random() * 11 + 1);
        int secondCompCard = (int) (Math.random() * 11 + 1);
        compSum += firstCompCard + secondCompCard;

        int firstPlayerCard = (int) (Math.random() * 11 + 1);
        int secondPlayerCard = (int) (Math.random() * 11 + 1);
        playerSum += firstPlayerCard + secondPlayerCard;

        while (compSum < 13){

            int anotherCompCard = (int) (Math.random() * 11 + 1);
            compSum += anotherCompCard;
        }

        if(playerSum <= 21) {
            Scanner scan = new Scanner(System.in);
            String choose;
            do{
                System.out.print("Вам выпало число " + playerSum + ", возьмете еще одну карту? - ");
                choose = scan.nextLine();
                if (choose.equalsIgnoreCase("Да")) {
                    int anotherPlayerCard = (int) (Math.random() * 11 + 1);
                    playerSum += anotherPlayerCard;
                }
            }while (choose.equalsIgnoreCase("да"));

            if(playerSum == 21)
            {
                winnings = bid * 5;
                System.out.println("Поздравляю! Вам выпало число 21, вы выигрываете " + (winnings));
                System.out.println("Противнику выпало число - " + compSum);
                isWin = true;
            }
            else if(playerSum > 21 && compSum < 21)
            {
                System.out.println("Вы проиграли, ваше число " +playerSum + " у вас перебор!");
                System.out.println("Противнику выпало число - " + compSum);
                isWin = false;
                winnings = 0;
            }
            else if(playerSum > 21 && compSum > 21)
            {
                System.out.println("Увы, ничья! Число противника - " + compSum + ", ваше число - " + playerSum);
                System.out.println("Ваша ставка возвращается к вам!");
                isWin = false;
                winnings = bid;
            }
            else if(playerSum < compSum && compSum < 21)
            {
                System.out.println("Увы, вы проиграли! Противнику выпало число " + compSum);
                System.out.println("Ваше число - " + playerSum);
                isWin = false;
                winnings = 0;
            }

            else if(playerSum > compSum && playerSum < 21)
            {
                winnings = bid * 2;
                System.out.println("Поздравляю, вы выиграли! Ваше число " + playerSum);
                System.out.println("Число противника - " + compSum);
                System.out.println("Ваш выиграшь удваивается и равен = "+ (winnings));
                isWin = true;
            }
            else if(playerSum == compSum){
                System.out.println("Увы, ничья! Число противника - " + compSum + ", ваше число - " + playerSum);
                System.out.println("Ваши ставки возвращаются к вам!");
                isWin = false;
                winnings = bid;
            }
        }else
        {
            System.out.println("Вы проиграли, ваше число " +playerSum + " у вас перебор!");
            System.out.println("Число противника  - " + compSum);
            isWin = false;
        }
    }
    private static void setBalanceAfterGame(){
        try{
            Path path = Path.of("c:/Users/User/IdeaProjects/untitled2/src/Fun/Casino/balance.txt");
            if(isWin){
                balance += winnings - bid;
            }else{
                balance -= bid;
            }

            Files.write(path,String.valueOf(balance).getBytes());
        }catch (IOException e){
            throw new RuntimeException("Что то пошло не так при изменении баланса в методе setBalanceAfterGame()");
        }
    }
    private static void setLogs(){
        try{
            Path path = Path.of("C:\\Users\\User\\IdeaProjects\\untitled2\\src\\Fun\\Casino\\logs.txt");
            String line = "\n\n‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾";
            String log = String.format("Режим игры: %s \nСтавка: %d \nИтог: %s \nВыигрышь составил: %d"+line, arcadeChoose, bid, isWin ? "Победа" : "Поражение",winnings);
            Files.write(path, log.getBytes(), StandardOpenOption.APPEND);
            Files.write(path, new byte[]{'\n'}, StandardOpenOption.APPEND);
        }catch (IOException e){
            System.out.println();
            throw new RuntimeException("Произошла ошибка при написании лога в методе setLogs()");
        }
    }
    public static void getLogs(){
        try{
            System.out.println("Информация об играх:\n");
            List<String> list = Files.readAllLines(Path.of("C:\\Users\\User\\IdeaProjects\\untitled2\\src\\Fun\\Casino\\logs.txt"));
            for(String s : list){
                System.out.println(s);
            }
        }catch(IOException e){
            throw new RuntimeException("Что то пошло не так при распечатке логов в методе getLogs()!");
        }
    }
    //old version of slots
//    public static void slots(){
//        int num1 = (int)(Math.random() * 3 + 1);
//        int num2 = (int)(Math.random() * 3 + 1);
//        int num3 = (int)(Math.random() * 3 + 1);
//
//        String[] table1= new String[]{" ","_","_","_","_","_"};
//        String[] table2= new String[]{"|"," "," "," "," "," ","|"};
//        String[] table3= new String[]{" ","‾","‾","‾","‾","‾"};
//
//        for(int i = 0; i < table1.length; i++)
//            System.out.print(table1[i]);
//        System.out.println();
//
//        for(int i = 0; i < table2.length; i++)
//        {
//            System.out.print(table2[i]);
//            table2[1] = String.valueOf(num1);
//            table2[3] = String.valueOf(num2);
//            table2[5] = String.valueOf(num3);
//        }
//
//        System.out.println();
//        for(int i = 0; i < table3.length; i++)
//            System.out.print(table3[i]);
//        System.out.println();
//
//        if(num1 == num2 && num2 == num3){
//            if(num1 == 1){
//                winnings = bid * 2;
//                System.out.println("Поздравляем, вы выиграли! Вам выпало число - 1, ваш выигрышь удваитвается и равен - " + (winnings));
//                isWin = true;
//            }else if(num1 == 2){
//                winnings = bid * 3;
//                System.out.println("Поздравляем, вы выиграли! Вам выпало число - 2, ваш выигрышь утраитвается и равен - " + (winnings));
//                isWin = true;
//            }else if(num1 == 3){
//                winnings = bid * 10;
//                System.out.println("Поздравляем, вы выиграли! Вам выпало число - 1, вы сорвали куш! Ваш выигрышь равен - " + (winnings));
//                isWin = true;
//            }
//
//        }else{
//            System.out.print("Увы! Вы проиграли!");
//            isWin = false;
//            winnings = 0;
//        }
//    }
}