package pl.edu.agh.python_colouring;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            HtmlFileCreator htmlFile = new HtmlFileCreator("index.html");

            BufferedReader br = new BufferedReader(new FileReader("example_data/simplefile.py"));
            Token token = PythonScanner.scan(br);

            int i = 0;
            while(token != null && i < 5000) {
                htmlFile.copyToken(token);
                token = PythonScanner.scan(br);
                i++;
            }

            br.close();
            htmlFile.closeFile();
        } 
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
