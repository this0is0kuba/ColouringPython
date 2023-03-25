package pl.edu.agh.python_colouring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PythonTokens {
    final static List<String> KEYWARDS = new ArrayList<>(Arrays.asList("False", "None", "True", "and", 
                                                "as", "assert", "async", "await", "break", "class", "continue", 
                                                "def", "del", "elif", "else", "except", "finally", "for", "from", 
                                                "global", "if", "import", "in", "is", "lambda", "nonlocal", "not",
                                                "or", "pass", "raise", "return", "try", "while", "with", "yield" ));

    final static List<String> OPERATORS = new ArrayList<>(Arrays.asList("+", "-", "*", "/", "%", "**", "//",
                                                            "==", "!=", ">", "<", "<=", ">=", "and", "or", 
                                                            "not", "&", "|", "~", "^", ">>", "<<", "+=", "-=", 
                                                            "=", "*=", "/=", "%=", "//=", "**=", "&=", "|=", 
                                                            "^=", "<<=", ">>=", "is", "is not")); 

    final static List<Character> PUNCTUATIONS = new ArrayList<>(Arrays.asList('!', '\"', '#', '$', '%', '&',
                                                             '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':',
                                                             ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^',
                                                             '_', '`', '{', '|', '}', '~'));
}
