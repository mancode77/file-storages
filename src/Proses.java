import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Proses{
    //Object Utility
    Utility utility = new Utility();
    //Object Scanner
    Scanner scanner = new Scanner(System.in);

    //Object StringTokenizer
    StringTokenizer stringTokenizer = null;
    //Data Member
    String keywordInput;
    //Data Member Array
    String[] keywordsArray;

    //Method akses Database
    private void aksesFile()throws IOException{
        //Deklarasi Class
        FileReader         fileReader = null;
        BufferedReader bufferedReader = null;

        try{
            //Membuka File
            fileReader = new FileReader("databaseSoftware.txt");
            bufferedReader = new BufferedReader(fileReader);

            String data = bufferedReader.readLine();
            int number = 0;

            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %s  |  %25s  %25s  %15s %17s %27s %15s %s %2s %22s %11s %23s %18s\n" , "No" , "Pengembang" , "|" , "Rilis" , "|" , "Repositori" , "|" , " Bahasa Pemrograman" , "|" , "System Operasi" , "|" , "Type" , "|");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            while(data != null){
                number++;
                StringTokenizer stringTokenizer = new StringTokenizer(data , ",");

                System.out.printf("| [%d] |" , number);
                System.out.printf("  %-51s|" , stringTokenizer.nextToken());
                System.out.printf("  %-30s  |" , stringTokenizer.nextToken());
                System.out.printf("  %-39s  |" , stringTokenizer.nextToken());
                System.out.printf("  %-18s  |" , stringTokenizer.nextToken());
                System.out.printf("  %-30s  |" , stringTokenizer.nextToken());
                System.out.printf("  %-38s  |\n" , stringTokenizer.nextToken());

                data = bufferedReader.readLine();
            }
        }catch(IOException e){
            System.err.println(e);
        }

        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }

    //Method Cari database
    private void cariDatabase()throws IOException{
        //Input Data User
        System.out.print("Masukan Keyword Database = ");
        this.keywordInput = scanner.nextLine();

        //Inputkan ke dalam array String[]
        keywordsArray = this.keywordInput.split("\\s+");

        utility.outputCekBukuDiDatabaseSoftware(keywordsArray , true);
    }

    //Method Tambah Database
    private void tambahDatabase()throws IOException{
        //Deklarasi
        String pengembang;
        String rilis;
        String repositori;
        String bahasapemrograman;
        String systemoperasi;
        String type;

        //Membuka File
        FileWriter fileWriter         = new FileWriter("databaseSoftware.txt" , true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        //Input User Untuk menambah database
        System.out.print("Pengembang         = ");
        pengembang = scanner.nextLine();
        System.out.print("Rilis              = ");
        rilis = scanner.nextLine();
        System.out.print("Repositori         = ");
        repositori = scanner.nextLine();
        System.out.print("Bahasa Pemrograman = ");
        bahasapemrograman = scanner.nextLine();
        System.out.print("System Operasi     = ");
        systemoperasi = scanner.nextLine();
        System.out.print("Type               = ");
        type = scanner.nextLine();

        //Struktur data Primitiv dimasukan ke dalam Struktur data Array
        String[] keyword = {pengembang+ "," +rilis+ "," +repositori+ "," +bahasapemrograman+ "," +systemoperasi+ "," +type };

        //Cek buku di Database
        boolean isExist =  utility.outputCekBukuDiDatabaseSoftware(keyword, false);

        //Kalau tidak ada maka tulis
        if(!isExist){
            System.out.println("\n# Data Yang akan anda Tambah #");
            System.out.println("Pengembang         = " + pengembang);
            System.out.println("Rilis              = " + rilis);
            System.out.println("Repositori         = " + repositori);
            System.out.println("Bahasa Pemrograman = " + bahasapemrograman);
            System.out.println("System Operasi     = " + systemoperasi);
            System.out.println("Type               = " + type);

            boolean isTambah = utility.outputgetYesorNo("\nApakah anda ingin menambahkan Database tersebut? (y/n) ");

            if(isTambah){
                bufferedWriter.write(pengembang+ "," +rilis+ "," +repositori+ "," +bahasapemrograman+ "," +systemoperasi+ "," +type);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }else{
            System.err.println("File Sudah Tersedia");
            utility.outputCekBukuDiDatabaseSoftware(keyword, true);
        }

        fileWriter.close();
        bufferedWriter.close();
    }

    //Method Update Database
    private void updateDatabase()throws IOException{
        //Mengecek dan Membuka Database Ori
        File fileOri                   = new File("databaseSoftware.txt");
        FileReader fileRaeder          = new FileReader(fileOri);
        BufferedReader bufferedReader  = new BufferedReader(fileRaeder);

        //Mengecek dan Membuka Data Sementara
        File fileSementara             = new File("databaseSoftwareSementara.txt");
        FileWriter fileWriter          = new FileWriter(fileSementara);
        BufferedWriter bufferedWriter  = new BufferedWriter(fileWriter);

        //Output Seluruh Data pada File databaseSoftware.txt
        this.aksesFile();

        //Input User Pilih Data
        System.out.print("\n Masukan nomor Software IDE yang ingin anda Update = ");
        int updateNum = scanner.nextInt();

        //Membaca data Software yang ingin di Update
        String data =  bufferedReader.readLine();
        int entryCounts = 0;

        while(data != null){
            entryCounts++;

            stringTokenizer = new StringTokenizer(data , ",");

            //Output entryCounts == updateNum
            if(updateNum == entryCounts){
                System.out.println("\nData yang ingin anda update adalah:");
                System.out.println("---------------------------------------");
                System.out.println("Pengembang                   : " + stringTokenizer.nextToken());
                System.out.println("Rilis                        : " + stringTokenizer.nextToken());
                System.out.println("Repositori                   : " + stringTokenizer.nextToken());
                System.out.println("Bahasa Pemrograman           : " + stringTokenizer.nextToken());
                System.out.println("Sistem Operasi               : " + stringTokenizer.nextToken());
                System.out.println("Type                         : " + stringTokenizer.nextToken());

                //Struktur Data Array
                String[] fieldData = {"Pengembang" , "Rilis" , "Repositori" , "Bahasa Pemrograman" , "Sistem Operasi" , "Type"};
                String[] tempData = new String[fieldData.length];

                //Membaca Ulang
                stringTokenizer = new StringTokenizer(data, ",");

                for(int i = 0 ; i < fieldData.length; i++){
                    boolean isUpdate = utility.outputgetYesorNo("\nApakah anda ingin Update " + fieldData[i] + " (y/n)? ");
                    //Alternatif bila user tidak jadi Update
                    String originalData = stringTokenizer.nextToken();

                    if(isUpdate){
                        //Input User untuk Update Data
                        System.out.print("\nMasukan " + fieldData[i] + " baru = ");
                        tempData[i] = scanner.next();
                    }else{
                        tempData[i] = originalData;
                    }
                }

                //Output Data Input Useer (Data Baru)
                //Baca Ulang
                stringTokenizer = new StringTokenizer(data, ",");
                System.out.println("\n# Data Baru anda #");
                System.out.println("---------------------------------------");
                System.out.println("Pengembang                   : " + stringTokenizer.nextToken() + " ---> "  + tempData[0]);
                System.out.println("Rilis                        : " + stringTokenizer.nextToken() + " ---> "  + tempData[1]);
                System.out.println("Repositori                   : " + stringTokenizer.nextToken() + " ---> "  + tempData[2]);
                System.out.println("Bahasa Pemrograman           : " + stringTokenizer.nextToken() + " ---> "  + tempData[3]);
                System.out.println("Sistem Operasi               : " + stringTokenizer.nextToken() + " ---> "  + tempData[4]);
                System.out.println("Type                         : " + stringTokenizer.nextToken() + " ---> "  + tempData[5]);

                //Kepastian apakah User ingin Update databasee
                boolean isUpdate = utility.outputgetYesorNo("Apakah anda yakin ingin Update Database? (y/n)");

                if(isUpdate){
                    boolean isExist = utility.outputCekBukuDiDatabaseSoftware(tempData, false);

                    if(isExist){
                        System.err.println("Database Software IDE sudah tersedia , proses Update di batalkan\n Silahkan delete Datbase yang bersangkutan");

                        //Tulis database ori atau copy databaseyang tersedia
                        bufferedWriter.write(data);
                    }else{
                        //Format Database Baru Ke dalam String untuk di masukan ke dalam Database
                        String pengembang        = tempData[0];
                        String rilis             = tempData[1];
                        String repositori        = tempData[2];
                        String bahasaPemrograman = tempData[3];
                        String sistemOperasi     = tempData[4];
                        String type              = tempData[5];

                        //Tulis Ke dalam Database Sementara
                        bufferedWriter.write(pengembang  + " , " + rilis + " , " + repositori + " , " + bahasaPemrograman + " , " + sistemOperasi + " , " + type);
                    }
                }else{
                    //Menulis Database Ori ke dalam Database Sementara atau Copy Database
                    bufferedWriter.write(data);
                }
            }else {
                //Menulis Database Ori ke dalam Database Sementara atau Copy Database
                bufferedWriter.write(data);
            }
            bufferedWriter.newLine();
            data = bufferedReader.readLine();
        }

        //Menulis data ke file
        bufferedWriter.flush();
        //Delete Database ori
        fileOri.delete();
        //Rename file Sementarat menjadi Database Ori
        fileSementara.renameTo(fileOri);

        //close file
        fileRaeder.close();
        bufferedReader.close();
        fileWriter.close();
        bufferedWriter.close();
    }

    //Method Delete Database
    private void deleteDatabase()throws IOException{
        //Mengecek dan Membuka File Database Ori
        File fileOri = new File("databaseSoftware.txt");
        FileReader fileReader = new FileReader(fileOri);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        //Mengecek dan Membuka Database Sementara
        File fileSementara = new File("databaseSoftwareSementara.txt");
        FileWriter fileWriter = new FileWriter(fileSementara);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        //Output Seluruh Database Ori
        this.aksesFile();

        //Input User untuk memilih data mana yang akan dihapus
        System.out.print("\nPilih Database yang akan di hapus = ");
        int deleteNum = scanner.nextInt();

        boolean isFound = false;
        int entryCounts = 0;

        //Baca 1 baris File ori
        String data = bufferedReader.readLine();

        //Lopping data
        while(data != null){
            entryCounts++;
            boolean isDelete = false;

            stringTokenizer = new StringTokenizer(data,",");

            if(deleteNum ==  entryCounts){
                System.out.println("\tDatabase yang akan anda hapus");
                System.out.println("-------------------------------");
                System.out.println("Pengembang         : " + stringTokenizer.nextToken());
                System.out.println("Rilis              : " + stringTokenizer.nextToken());
                System.out.println("Repositori         : " + stringTokenizer.nextToken());
                System.out.println("Bahasa Pemrograman : " + stringTokenizer.nextToken());
                System.out.println("Sistem Operasi     : " + stringTokenizer.nextToken());
                System.out.println("Type               : " + stringTokenizer.nextToken());

                isDelete = utility.outputgetYesorNo("Apakah anda yakin akan menhapus Database tersebut? (y/n) ");
                isFound = true;
            }

            if(isDelete){
                //skip pindahkan data dari original ke sementara
                System.out.println("Data berhasil dihapus");
            }else{
                bufferedWriter.write(data);
                bufferedWriter.newLine();
            }

            data = bufferedReader.readLine();
        }

        if(!isFound){
            System.err.println("Database Tidak di temukan");
        }

        //Menulis data ke file
        bufferedWriter.flush();
        //Delete File Original
        fileOri.delete();
        //Rename File Sementara Menjadi Ori
        fileSementara.renameTo(fileOri);
    }

    //Method output Akses File
    public void outputAksesFile() throws IOException{
        this.aksesFile();
    }

    //Method Output Cari Database
    public void outputCariDatabase()throws IOException{
        this.cariDatabase();
    }

    //Method Output Tambah Database
    public void outputTambahDatabase()throws IOException{
        this.tambahDatabase();
    }

    //Method Output Update Database
    public void outputUpdateDatabase()throws IOException{
        this.updateDatabase();
    }

    //Method Output Delete Database
    public void outputDeleteDatabase()throws IOException{
        this.deleteDatabase();
    }
}