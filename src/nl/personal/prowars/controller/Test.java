package nl.personal.prowars.controller;

import org.lwjgl.Sys;

/**
 * Created by Nathan on 29/12/2014.
 */
public class Test {
    public static void main(String[] args) {
        int size = 4;
        for(int i = 0; i < size; i++){
            for(int j = 0; j <= i; j++){
                int x = i - j;
                int y = j;
                System.out.print("" + x + "," + y + " ");
            }
            System.out.println();
        }
//        for(int i = 1; i < size; i++){
//            for(int j = size-1; j >= i; j--){
//                int x = j;
//                int y = size-1-(j-i);
//                System.out.print("" + x + "," + y + " ");
//            }
//            System.out.println();
//        }
        for(int i = 1; i < size; i++){
            for(int j = i; j < size; j++){
                int y = j;
                int x = size-1-(j-i);
                System.out.print("" + x + "," + y + " ");
            }
            System.out.println();
        }

    }
}
