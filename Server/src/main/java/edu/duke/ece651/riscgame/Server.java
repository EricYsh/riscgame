package edu.duke.ece651.riscgame;

public class Server {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new Server().getGreeting());
    }
}