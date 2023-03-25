package pl.edu.agh.python_colouring;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class HtmlFileCreator{

    BufferedWriter bw;
    String path;

    static Map<TokenType, String> colours = Map.of(
        TokenType.KEYWORD, "mediumorchid",
        TokenType.IDENTIFIER, "powderblue",
        TokenType.LITERAL, "lightcoral",
        TokenType.OPERATOR, "white",
        TokenType.PUNCTUATION_MARK, "yellow",
        TokenType.SPACE, "",
        TokenType.NEW_LINE, "",
        TokenType.CARRIAGE_RETURN, "",
        TokenType.COMMENT, "green"
    );

    static Map<TokenType, String> syntax = Map.of(
        TokenType.SPACE, "&nbsp",
        TokenType.NEW_LINE, "\n<br>",
        TokenType.CARRIAGE_RETURN, ""
    ); 

    HtmlFileCreator(String path) {
        try {
            this.path = path;
            bw = new BufferedWriter(new FileWriter(path));
            bw.write("<!DOCTYPE html>\n" +
                      "<html>\n" +
                      "<body bgcolor=\"252525\">\n");
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void copyToken(Token token) {
        try {

            if(colours.get(token.tokenType).isEmpty()) {
                String correctSyntax = syntax.get(token.tokenType);
                bw.write(correctSyntax);
            }

            else {
                String colour = colours.get(token.tokenType);
                bw.write("<span style=\"color:" + colour + "\">" + token.text + "</span>");
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void closeFile() {
        try {
            bw.write("</body>\n" +
                     "</html>");
            bw.close();
        } 
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
