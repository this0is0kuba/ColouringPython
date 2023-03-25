package pl.edu.agh.python_colouring;

import java.io.BufferedReader;
import java.io.IOException;

public class PythonScanner {
    // lastChar = -2 means that there is no last chararcter in memory,
    // lastChar = -1 means that there is no more characters to read
    static int lastChar = -2;

    static Token scan(BufferedReader br) {
        String tokenText = "";
        
        try {
            if(lastChar == -1)
                return null;

            if(lastChar != -2)
                if((char)lastChar != ' ' && (char)lastChar != '\n')
                    if(!Character.isDigit((char)lastChar))
                        tokenText = String.valueOf((char)lastChar);
            
            int charAsInt;
        
            if(lastChar != -2)
                charAsInt = lastChar;
            else
                charAsInt = br.read();

            while(charAsInt != -1) {
                char currentChar = (char)charAsInt;

                //space
                if((currentChar == ' ') && tokenText.isEmpty()) {
                    lastChar = -2;
                    return new Token(TokenType.SPACE, currentChar);
                } 

                //carriage return
                if((currentChar == '\r') && tokenText.isEmpty()) {
                    lastChar = -2;
                    return new Token(TokenType.CARRIAGE_RETURN, currentChar);
                } 

                //new line
                if((currentChar == '\n') && tokenText.isEmpty()) {
                    lastChar = -2;
                    return new Token(TokenType.NEW_LINE, currentChar);
                } 
            
                //string literals
                if((currentChar == '\"' || currentChar == '\'') && tokenText.isEmpty()) {
                    lastChar = -2;
                    return loadString(br, currentChar);
                }

                //operators
                if(PythonTokens.OPERATORS.contains(String.valueOf(currentChar))) {
                    lastChar = -2;
                    return new Token(TokenType.OPERATOR, currentChar);
                }

                //punctuations
                if(PythonTokens.PUNCTUATIONS.contains(currentChar))
                    if(tokenText.isEmpty() || tokenText.equals(String.valueOf(currentChar)))
                        if(currentChar != '_' && currentChar != '#')
                        {
                            lastChar = -2;
                            return new Token(TokenType.PUNCTUATION_MARK, currentChar);
                        }

                //number literals
                if(Character.isDigit(currentChar) && tokenText.isEmpty())
                    return loadNumber(br, currentChar);

                //keywords and identifiers
                if((currentChar == ' ' || currentChar == '\n' || PythonTokens.PUNCTUATIONS.contains(currentChar)))
                    if(!tokenText.isEmpty() && currentChar != '_') 
                    {
                        lastChar = currentChar;
                        return matchToken(tokenText);
                    }

                if(currentChar == '#') 
                    return loadComment(br);
                
                tokenText += currentChar;
                charAsInt = br.read();
            }
        } 
        catch(IOException ioe) {
            ioe.printStackTrace();
        }

        if(tokenText.isEmpty())
            return null;
        
        return matchToken(tokenText);
    }

    static private Token matchToken(String tokenText) {
        if(PythonTokens.KEYWARDS.contains(tokenText))
            return new Token(TokenType.KEYWORD, tokenText);

        return new Token(TokenType.IDENTIFIER, tokenText);
    }

    static private Token loadString(BufferedReader br, char firstChar) {
        try {
            String tokenText = String.valueOf(firstChar);
            char earlierChar = firstChar;
            int charAsInt = br.read();

            char currentChar = (char)charAsInt;
            
            while(charAsInt != -1 && (currentChar != firstChar || earlierChar == '\\')) {
                tokenText += currentChar;
                charAsInt = br.read();

                earlierChar = currentChar;
                currentChar = (char)charAsInt;
            }
            return new Token(TokenType.LITERAL, tokenText + firstChar); 
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }

        return null;
    }

    static private Token loadNumber(BufferedReader br, char firstDigit) {
        try {
            String tokenText = String.valueOf(firstDigit);
            int charAsInt = br.read();
            lastChar = -2;

            if(charAsInt == -1)
                return new Token(TokenType.LITERAL, tokenText);

            char currentChar = (char)charAsInt;

            while(charAsInt != -1 && Character.isDigit(currentChar)) {
                tokenText += currentChar;
                charAsInt = br.read();
                currentChar = (char)charAsInt;
            }

            lastChar = charAsInt;
            return new Token(TokenType.LITERAL, tokenText);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return null;
    }

    static private Token loadComment(BufferedReader br) {
        try {
            String tokenText = "#";
            int charAsInt = br.read();
            lastChar = -2;

            if(charAsInt == -1)
                return new Token(TokenType.COMMENT, tokenText);

            char currentChar = (char)charAsInt;

            while(charAsInt != -1 && currentChar != '\n') {
                tokenText += currentChar;
                charAsInt = br.read();
                currentChar = (char)charAsInt;
            }

            lastChar = charAsInt;
            return new Token(TokenType.COMMENT, tokenText);
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }

        return null;
    }
}

