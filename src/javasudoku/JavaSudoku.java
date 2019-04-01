/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasudoku;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Berkay GÜNGÖR 150202061, Barış MENEKŞE 140202122
 */
public class JavaSudoku {
    
    public synchronized static void cevapYazdir (int[][] sudoku) {
        for (int  i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[0].length; j++) {
                System.out.print(sudoku[i][j] + " : ");
            }
            System.out.println();
        }
       
    }
    public static int kontrol0;
    public static int kontrol1;
    public static int kontrol2;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        kontrol0=0;
        kontrol1=0; 
        kontrol2=0;
        int[][] sudoku0 = new int[9][9];
        int[][] sudoku1 = new int[9][9];
        int[][] sudoku2 = new int[9][9];
        String fileName = "C:\\Users\\User\\Desktop\\Yazılım Lab\\Ornekler\\sudoku.txt";
        File file0, file1, file2;
        FileWriter fw0;
        BufferedWriter bw0 = null;
        Scanner scanner0 = null, scanner1 = null;
        String line0 = "", line1 = "";
        try {
            file0 = new File(fileName);
            file1 = new File("C:\\Users\\User\\Desktop\\Yazılım Lab\\Ornekler\\yeniSudoku.txt"); 
            file2 = new File("C:\\Users\\User\\Desktop\\Yazılım Lab\\Ornekler\\SudokuCoz0Adimlari.txt");
            file2.delete();
            file2 = new File("C:\\Users\\User\\Desktop\\Yazılım Lab\\Ornekler\\SudokuCoz1Adimlari.txt");
            file2.delete();
            file2 = new File("C:\\Users\\User\\Desktop\\Yazılım Lab\\Ornekler\\SudokuCoz2Adimlari.txt");
            file2.delete();
            if (!file1.exists()) {   
                file1.createNewFile(); 
            }
            fw0 = new FileWriter(file1.getAbsoluteFile(), false); 
            bw0 = new BufferedWriter(fw0); 
            scanner0 = new Scanner(file0);
            System.out.println(fileName + " Dosyasindaki sudoku:\n");
            while (scanner0.hasNextLine()){
                line0 = scanner0.nextLine();
                line1 = line0.replaceAll("\\*", "0");
                bw0.write(line1);
                bw0.newLine();
            }
            bw0.close();
            scanner0.close();
            file2 = new File("C:\\Users\\User\\Desktop\\Yazılım Lab\\Ornekler\\yeniSudoku.txt");
            scanner1 = new Scanner(file2);
            int satir = 0;
            while (scanner1.hasNextLine()) {
                line0 = scanner1.nextLine();
                for(int i = 0; i<line0.length();i++){
                    sudoku0[satir][i] =  Character.getNumericValue(line0.charAt(i));
                }
                satir++;
            }
            for(int i=0; i<9;i++){
                for(int j=0; j<9;j++){
                    sudoku1[i][j] = sudoku0[i][j];
                    sudoku2[i][j] = sudoku0[i][j];
                }
            }
            thread0 t0= new thread0(sudoku0);
            thread1 t1= new thread1(sudoku1);
            thread2 t2= new thread2(sudoku2);
            Thread[] threads = new Thread[3];
            threads[0] = new Thread(t0);				
            threads[1] = new Thread(t1);
            threads[2] = new Thread(t2);
            threads[0].start();
            threads[1].start();
            threads[2].start();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Dosya acılamadı.");                
        }
        catch(IOException ex) {
        System.out.println("Dosya okunamadı");                  
        }
        new TextEditor("Thread 0 ın adımları");
        TextEditor.DosyadanOkuma("C:\\Users\\User\\Desktop\\Yazılım Lab\\Ornekler\\SudokuCoz0Adimlari.txt");
        new TextEditor("Thread 1 ın adımları");
        TextEditor.DosyadanOkuma("C:\\Users\\User\\Desktop\\Yazılım Lab\\Ornekler\\SudokuCoz1Adimlari.txt");
        new TextEditor("Thread 2 ın adımları");
        TextEditor.DosyadanOkuma("C:\\Users\\User\\Desktop\\Yazılım Lab\\Ornekler\\SudokuCoz2Adimlari.txt");
    }
}


