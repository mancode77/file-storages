import java.lang.Exception;
import java.io.IOException;

class Main{
    public static void main(String[] args) throws IOException, Exception {
        //Object Input
        Input dataInputUser = new Input();
        //Object Intro
        Intro intro = new Intro(dataInputUser);

        intro.output();
    }
}