/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.try12334444444444;

import java.util.Scanner;

/**
 *
 * @author YairF
 */
public class Minesee {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mineswe m = new Mineswe();
        Scanner sc = new Scanner(System.in);
        int row;
        int column;
        int booms;
        try {
            System.out.println("The length of the array");
            row = sc.nextInt();
            System.out.println("Array width");
            column = sc.nextInt();
            System.out.println("Some bombs?");
            booms = sc.nextInt();
            m.board(row, column, booms);            //Construction of the array
            //int[][] A = m.getUpdatedBoard();

            m.Executions(row, column, booms);       //Checks Exceptions
            m.printArray();                         //Printing an array to the user
            System.out.println("Number 9 is an open space:");

            m.begin();           //Printing an array to the user

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
