package edu.duke.ece651.riscgame;

public class ServerApp {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new ServerApp().getGreeting());
    }
}
