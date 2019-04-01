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
import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author Barış
 */
public class thread2 implements Runnable {
    private int[][] sudoku = new int[9][9];
    private long cozumSuresi;
    public thread2(int[][] sudoku){
    this.sudoku = sudoku; }
       
    @Override
    public void run(){
        long baslangicSuresi = System.currentTimeMillis();
        sudokuCoz(sudoku, 0, 8);
        long bitisSuresi = System.currentTimeMillis();
        this.cozumSuresi = bitisSuresi - baslangicSuresi;
        if(JavaSudoku.kontrol2==1) {
        System.out.println("\nthread2 çözüm süresi : "+cozumSuresi + "\n_______________________________");
        }
    }

    public long getCozumSuresi() {
        return cozumSuresi;
    }

    private static void texteMatrisYaz(String fileName, int[][] sudoku) {
        try {
            File file0 = new File(fileName); 
                if (!file0.exists()) {   
                    file0.createNewFile(); 
                }
            FileWriter fw0 = new FileWriter(file0.getAbsoluteFile(), true);
            BufferedWriter bw0 = new BufferedWriter(fw0);
            for (int i = 0; i < sudoku.length; i++) {
                for (int j = 0; j < sudoku[i].length; j++) {
                    if(sudoku[i][j]==0){
                        bw0.write("X");
                    }else{
                        bw0.write(sudoku[i][j]+"");
                    }
                }
                bw0.newLine();
            }
            bw0.newLine();
            bw0.flush();
        }catch(IOException e) {
        }
    }
    
    private static Set<Integer> getUygunSayilar(int[][] sudoku, int satir, int sutun) {
        final Set<Integer> yasakSayilar = new HashSet<>();
        for (int i = 0; i < sudoku[0].length; i++) {
            if (sudoku[satir][i] > 0) {
                yasakSayilar.add(sudoku[satir][i]);
            }
        }

        for (int i = 0; i < sudoku.length; i++) {
            if (sudoku[i][sutun] > 0) {
                yasakSayilar.add(sudoku[i][sutun]);
            }
        }

        int satirKutuGrubu = (satir / 3) * 3;
        int sutunKutuGrubu =  (sutun / 3) * 3;

        for (int i = satirKutuGrubu; i < satirKutuGrubu + 3; i++) {
            for (int j = sutunKutuGrubu; j < sutunKutuGrubu + 3; j++) {
                if (sudoku[i][j] > 0) {
                    yasakSayilar.add(sudoku[i][j]);
                }
            }
        }

        final Set<Integer> uygunSayilar = new HashSet<>();
        for (int i = 1; i <= sudoku.length; i++) {
            if (!yasakSayilar.contains(i)) { 
                uygunSayilar.add(i);
            }
        }
        return uygunSayilar;
    }

   private static boolean sudokuCoz(int[][] sudoku, int satir, int sutun) {
       
        if(JavaSudoku.kontrol0==0 && JavaSudoku.kontrol1==0){
            if(sutun == -1){
                satir++;
                if(satir == sudoku.length){
                    if(JavaSudoku.kontrol0==0 &&JavaSudoku.kontrol1==0 &&JavaSudoku.kontrol2==0){
                        JavaSudoku.kontrol2=1;
                        System.out.println("_______________________________  \nthread2  çözüme ulaştı ");
                        JavaSudoku.cevapYazdir(sudoku);
                    }
                    return true;
                }
                sutun = 8;
            }

            if(sudoku[satir][sutun] > 0){
                return sudokuCoz(sudoku, satir, sutun - 1);
            }else{
                for(int i : getUygunSayilar(sudoku, satir, sutun)){
                    sudoku[satir][sutun] = i;
                    texteMatrisYaz("C:\\Users\\User\\Desktop\\Yazılım Lab\\Ornekler\\SudokuCoz2Adimlari.txt",sudoku);
                    if (sudokuCoz(sudoku, satir, sutun - 1)){
                        return true;
                    }
                    sudoku[satir][sutun] = 0;
                }
            }
            return false; 
        }else{
            return true;
        }
    }
}
