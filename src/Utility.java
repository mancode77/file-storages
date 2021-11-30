import java.io.IOException;
import java.util.Scanner;
import java.lang.Exception;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class Utility{
    //Data Member
    private String mengulang;
    private String dataString;
    private static boolean isExist;
    private int nomorData;

    //Object Scanner
    Scanner scanner = new Scanner(System.in);

    //Method Cek Buku di database
    private boolean cekBukuDiDatabaseSoftware(String[] keywords , boolean isDisplay)throws IOException{
        //Membuka File databaseSoftware
        FileReader fileReader = new FileReader("databaseSoftware.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        this.dataString = bufferedReader.readLine();
        Utility.isExist = false;
        this.nomorData = 0;

        if(isDisplay){
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %s  |  %25s  %25s  %15s %17s %27s %15s %s %2s %22s %11s %23s %18s\n" , "No" , "Pengembang" , "|" , "Rilis" , "|" , "Repositori" , "|" , " Bahasa Pemrograman" , "|" , "System Operasi" , "|" , "Type" , "|");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }

        while(this.dataString != null){
            Utility.isExist = true;

            for(String dataKeyword:keywords){
                isExist = isExist && this.dataString.toLowerCase().contains(dataKeyword.toLowerCase());
            }

            if(isExist){
                if(isDisplay){
                    this.nomorData++;
                    StringTokenizer stringTokenizer = new StringTokenizer(this.dataString,",");

                    System.out.printf("| [%d] |" , this.nomorData);
                    System.out.printf("  %-51s|" , stringTokenizer.nextToken());
                    System.out.printf("  %-30s  |" , stringTokenizer.nextToken());
                    System.out.printf("  %-39s  |" , stringTokenizer.nextToken());
                    System.out.printf("  %-18s  |" , stringTokenizer.nextToken());
                    System.out.printf("  %-30s  |" , stringTokenizer.nextToken());
                    System.out.printf("  %-38s  |\n" , stringTokenizer.nextToken());

                }else{
                    System.err.println("Data Tidak Ditemukan");
                    break;
                }
            }

            this.dataString = bufferedReader.readLine();
        }

        if(isDisplay){
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }

        return isExist;
    }

    //Method untuk pengulangan bila ingin mengulang
    private boolean getYesorNo(String dataArgument){
        System.out.print(dataArgument);
        this.mengulang = scanner.next();

        while(!this.mengulang.equalsIgnoreCase("y") && !this.mengulang.equalsIgnoreCase("n")) {
            System.err.print("Pilihan anda bukan y atau n\n\n");

            System.out.print(dataArgument);
            this.mengulang = scanner.next();
        }

        return this.mengulang.equalsIgnoreCase("y");
    }


    //Method untuk menghapus history
    private void clearScreen()throws IOException , Exception{
        try{
            if(System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            }else{
                System.out.print("\033\143");
            }

        }catch(Exception e){
            System.out.println("Tidak bisa melakukan Clear Screen");
        }
    }

    //Method output get Yes or No
    public boolean outputgetYesorNo(String dataArgument){
        return this.getYesorNo(dataArgument);
    }

    //Method output Clear Screen
    public void outputClearScreen()throws Exception{
        this.clearScreen();
    }

    //Method output cekBukuDiDatabase
    public boolean outputCekBukuDiDatabaseSoftware(String[] dataKeywordsArrays , boolean data) throws IOException{
        return this.cekBukuDiDatabaseSoftware(dataKeywordsArrays  , data);
    }
}