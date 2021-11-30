import java.io.IOException;
import java.lang.Exception;

public class Intro{
    //Data Member boolean untuk perulangan
    private static boolean value = true;
    //Object Input
    Input input;
    //Object Input

    Input dataInputUser = new Input();
    //Object Proses
    Proses proses = new Proses();
    //Object Utility
    Utility utility = new Utility();

    public Intro(Input input){
        this.input = input;
    }

    //Method Proses Data User
    private void prosesDataUser() throws IOException, Exception{
        while(Intro.value){
            System.out.println("\n");
            utility.outputClearScreen();
            System.out.println("\t\t==============================");
            System.out.println("\t\t~ Database Software IDE Java ~");
            System.out.println("\t\t==============================\n\n");

            System.out.println("[1] Lihat Seluruh Database");
            System.out.println("[2] Cari Database");
            System.out.println("[3] Tambah Database");
            System.out.println("[4] Ubah Database");
            System.out.println("[5] Hapus Database\n");

            input.outputInput();

            switch(this.input.dataUser){
                case"1":
                    //Menampilkan Semua database
                    System.out.println("");
                    proses.outputAksesFile();
                    value = false;
                    break;
                case"2":
                    //Cari Database
                    System.out.println("");
                    proses.outputCariDatabase();
                    value = false;
                    break;
                case"3":
                    System.out.println("");
                    proses.outputTambahDatabase();
                    value = false;
                    break;
                case"4":
                    System.out.println("");
                    proses.outputUpdateDatabase();
                    value = false;
                    break;
                case"5":
                    System.out.println("");
                    proses.outputDeleteDatabase();
                    value = false;
                    break;
                default:
                    System.err.println("Data Tidak Tersedia, Silahkan Inputkan sesuai data\n\n");
                    this.dataInputUser.outputInput();
            }

            value = utility.outputgetYesorNo("\nApakah anda ingin mengulang? (y/n) ");
        }
    }

    //Method Output Intro
    public void output()throws IOException , Exception{
        this.prosesDataUser();
    }
}