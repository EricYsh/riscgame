//package edu.duke.ece651.riscgame.game;
//
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Scanner;
//
//public class BoardTextViewTest {
//    // @Test
//    // public void test_getBoardText() {
//    //     BoardMap boardMap = new BoardMap();
//    //     Territory t1 = new Territory("t1", "Avalon", 1, 1);
//    //     Territory t2 = new Territory("t2", "Avalon", 1, 2);
//    //     Territory t3 = new Territory("t2", "Braglavia", 2, 2);
//    //     t1.addNeighbor(t2);
//    //     t2.addNeighbor(t1);
//    //     t1.addNeighbor(t3);
//    //     t3.addNeighbor(t1);
//    //     ArrayList<Territory> t = new ArrayList<Territory>();
//    //     t.add(t1);
//    //     t.add(t2);
//    //     t.add(t3);
//    //     boardMap.setTerritories(t);
//
//    //     BoardTextView b = new BoardTextView(boardMap);
//    //     String s = "Red player:\n" +
//    //             "-----------\n" +
//    //             "1  units in t1 (next to: t2, t3)\n" +
//    //             "2  units in t2 (next to: t1)\n";
//    // }
//    @Disabled
//    @Test
//    public void test_getInput() {
//        BoardMap boardMap = new BoardMap();
//        Territory t1 = new Territory("t1", "Avalon", 1, 1);
//        Territory t2 = new Territory("t2", "Avalon", 1, 2);
//        Territory t3 = new Territory("t2", "Braglavia", 2, 2);
//        ArrayList<Territory> t = new ArrayList<Territory>();
//        t.add(t1);
//        t.add(t2);
//        t.add(t3);
//        boardMap.setTerritories(t);
//        Scanner scanner = new Scanner(System.in);
//        String input = "";
//        boolean isValidInput = false;
//        while(!isValidInput) {
//            input = scanner.nextLine();
//            if(boardMap.getTerritoryByName(input) != null) {
//                isValidInput = true;
//            } else {
//                System.out.println("Please enter a valid territory name");
//            }
//        }
//        scanner.close();
//        System.out.println(input);
//    }
//
//    public static void main(String[] args) {
//        BoardTextViewTest b = new BoardTextViewTest();
//        b.test_getInput();
//    }
//}
