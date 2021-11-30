import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.StringBuilder;

public class Input{
    //Data Member
    public String dataUser;

    //Object Scanner
    Scanner scanner = new Scanner(System.in);

    //Method Input
    private void input()throws IOException{
        System.out.print("Pilih data [Input Number] = ");
        this.dataUser = scanner.next();
    }

    //Method Output Input
    public void outputInput()throws IOException{
        this.input();
    }
}