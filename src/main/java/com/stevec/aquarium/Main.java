package com.stevec.aquarium;

/**
 * @authors Steve Clarke
 * @version 1.0
 */

public class Main {
    public static void main(String[] args) {
        Aquarium mainAquarium = new Aquarium("src/main/resources/puzzles/a6_1.txt");
        AquariumViewer mainViewer = new AquariumViewer(mainAquarium);
    }
}
