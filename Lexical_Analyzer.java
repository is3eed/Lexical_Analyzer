package lexical_analyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lexical_Analyzer {

    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("input.txt"); // create file object 
        Scanner in = new Scanner(input); // a scanne object to read input from file
        int state = 0; // current state or next state 
        char lex[] = new char[32]; // array of char to save lexems
        char word[]; // to split each line to characters
        int i = 0, j = 0; // i is an index int to go through lex array, j is an index int to go through word array
        char lookahead; // lookahead to read next character in input file 
        boolean flag = false; // true means we found a token so stop reading next till we evaulate current lookahead
        if (!input.exists()) {
            System.out.println("Cannot find file ");
            System.exit(0);
        }
        System.out.println("--------------------------------");
        System.out.println("lexeme\t\t|token");
        System.out.println("--------------------------------");
        while (in.hasNext()) { // as long as there are more input in the file 
            word = (in.nextLine() + "✪").toCharArray();
            lookahead = word[j++];
            while (j <= word.length) {
                switch (state) {
                    case 0:
                        if (lookahead == '\r' || lookahead == '\t' || lookahead == '\n' || lookahead == ' ') {
                            state = 0;
                        } else if (lookahead == '/') {
                            state = 1;
                            lex[i++] = lookahead;
                        } else if (lookahead == '+') {
                            state = 7;
                            lex[i++] = lookahead;
                        } else if (lookahead == '-') {
                            state = 11;
                            lex[i++] = lookahead;
                        } else if (lookahead == '%') {
                            state = 15;
                            lex[i++] = lookahead;
                        } else if (lookahead == '*') {
                            state = 18;
                            lex[i++] = lookahead;
                        } else if (lookahead == '<') {
                            state = 21;
                            lex[i++] = lookahead;
                        } else if (lookahead == '>') {
                            state = 24;
                            lex[i++] = lookahead;
                        } else if (lookahead == '=') {
                            state = 27;
                            lex[i++] = lookahead;
                        } else if (lookahead == '!') {
                            state = 30;
                            lex[i++] = lookahead;
                        } else if (lookahead == '&') {
                            state = 33;
                            lex[i++] = lookahead;
                        } else if (lookahead == '|') {
                            state = 36;
                            lex[i++] = lookahead;
                        } else if (Character.isLetter(lookahead) || lookahead == '_') {
                            state = 39;
                            lex[i++] = lookahead;
                        } else if (Character.isDigit(lookahead)) {
                            state = 41;
                            lex[i++] = lookahead;
                        } else if (lookahead == ';') {
                            state = 47;
                            lex[i++] = lookahead;
                            flag = true;
                        } else if (lookahead == ',') {
                            state = 48;
                            lex[i++] = lookahead;
                        } else if (lookahead == '[') {
                            state = 49;
                            lex[i++] = lookahead;
                            flag = true;
                        } else if (lookahead == ']') {
                            flag = true;
                            state = 50;
                            lex[i++] = lookahead;
                        } else if (lookahead == '{') {
                            state = 51;
                            lex[i++] = lookahead;
                            flag = true;
                        } else if (lookahead == '}') {
                            state = 52;
                            lex[i++] = lookahead;
                            flag = true;
                        } else if (lookahead == '(') {
                            state = 53;
                            lex[i++] = lookahead;
                            flag = true;
                        } else if (lookahead == ')') {
                            state = 54;
                            lex[i++] = lookahead;
                            flag = true;
                        } else if (lookahead == '.') {
                            state = 55;
                            lex[i++] = lookahead;
                            flag = true;
                        } else if (lookahead == '"') {
                            state = 56;
                        } else if (lookahead == '\'') {
                            state = 58;
                        }
                        break;
                    case 1:
                        if (lookahead == '/') {
                            state = 2;
                        } else if (lookahead == '=') {
                            state = 3;
                            lex[i++] = lookahead;
                            flag = true;
                        } else if (lookahead == '*') {
                            state = 4;
                        } else {
                            state = 6;
                            flag = true;
                        }
                        break;
                    case 2:
                        j = word.length + 1;
                        state = 0;
                        i = 0;
                        flag = true;

                        break;
                    case 3:
                        lex[i] = '\0';
                        printLexeme(lex);
                        System.out.println("div_assign_operator");
                        i = 0;
                        state = 0;
                        break;
                    case 4:
                        if (lookahead == '*') {
                            state = 5;

                        } else {
                            state = 4;

                        }
                        break;
                    case 5:
                        if (lookahead == '/') {
                            state = 0;
                            i = 0;
                        } else {
                            state = 4;
                        }
                        break;
                    case 6:
                        printLexeme(lex);
                        System.out.println("div_operator");
                        i = 0;
                        state = 0;

                        break;
                    case 7:
                        if (lookahead == '+') {
                            state = 9;
                            lex[i++] = lookahead;
                            lex[i] = '\0';
                        } else if (lookahead == '=') {
                            state = 8;
                            lex[i++] = lookahead;
                            lex[i] = '\0';
                        } else {
                            state = 10;
                            flag = true;
                            lex[i] = '\0';
                        }
                        break;
                    case 8:
                        printLexeme(lex);
                        System.out.println("plus_assign_operator");
                        flag = true;
                        i = 0;
                        state = 0;

                        break;
                    case 9:
                        printLexeme(lex);
                        System.out.println("increment_operator");
                        i = 0;
                        flag = true;
                        state = 0;

                        break;
                    case 10:
                        printLexeme(lex);
                        System.out.println("plus_operator");
                        flag = true;
                        state = 0;
                        i = 0;

                        break;
                    case 11:
                        if (lookahead == '-') {
                            state = 13;
                            lex[i++] = lookahead;
                            lex[i] = '\0';
                        } else if (lookahead == '=') {
                            state = 12;
                            lex[i++] = lookahead;
                            lex[i] = '\0';
                        } else {
                            state = 14;
                            flag = true;
                            lex[i] = '\0';
                        }
                        break;
                    case 12:
                        printLexeme(lex);
                        System.out.println("minus_assign_operator");
                        i = 0;
                        state = 0;
                        break;
                    case 13:
                        printLexeme(lex);
                        System.out.println("decrement_operator");
                        i = 0;
                        state = 0;
                        break;
                    case 14:
                        printLexeme(lex);
                        System.out.println("minus_opertator");
                        flag = true;
                        i = 0;
                        state = 0;

                        break;
                    case 15:
                        if (lookahead == '=') {
                            state = 16;
                            lex[i++] = lookahead;
                            lex[i] = '\0';
                        } else {
                            state = 17;
                            flag = true;
                            lex[i] = '\0';
                        }
                        break;
                    case 16:
                        printLexeme(lex);
                        System.out.println("remainder_assign_operator");
                        i = 0;
                        state = 0;
                        break;
                    case 17:
                        printLexeme(lex);
                        System.out.println("modulus_operator");
                        flag = true;
                        i = 0;
                        state = 0;
                        break;
                    case 18:
                        if (lookahead == '=') {
                            state = 19;
                            lex[i++] = lookahead;
                            lex[i] = '\0';

                        } else {
                            flag = true;
                            state = 20;
                            lex[i] = '\0';
                        }
                        break;
                    case 19:
                        printLexeme(lex);
                        System.out.println("multiplication_assign_operator");
                        i = 0;
                        state = 0;
                        break;
                    case 20:
                        printLexeme(lex);
                        System.out.println("multiplication_operator");
                        i = 0;
                        state = 0;
                        flag = true;

                        break;
                    case 21:
                        if (lookahead == '=') {
                            state = 23;
                            lex[i++] = lookahead;
                            lex[i] = '\0';
                        } else {
                            flag = true;
                            state = 22;
                            lex[i] = '\0';
                        }
                        break;
                    case 22:
                        printLexeme(lex);
                        System.out.println("less_than");
                        state = 0;
                        i = 0;
                        flag = true;

                        break;
                    case 23:
                        printLexeme(lex);
                        System.out.println("less_orEqual");
                        state = 0;
                        i = 0;
                        break;
                    case 24:
                        if (lookahead == '=') {
                            state = 26;
                            lex[i++] = lookahead;
                            lex[i] = '\0';
                        } else {
                            flag = true;
                            state = 25;
                            lex[i] = '\0';
                        }
                        break;
                    case 25:
                        printLexeme(lex);
                        System.out.println("great_than");
                        state = 0;
                        i = 0;
                        flag = true;
                        break;
                    case 26:
                        printLexeme(lex);
                        System.out.println("great_orEuqal");
                        state = 0;
                        i = 0;
                        break;
                    case 27:
                        if (lookahead == '=') {
                            state = 29;
                            lex[i++] = lookahead;
                            lex[i] = '\0';
                        } else {
                            flag = true;
                            state = 28;
                            lex[i] = '\0';
                        }
                        break;
                    case 28:
                        printLexeme(lex);
                        System.out.println("assign_operator");
                        state = 0;
                        i = 0;
                        flag = true;

                        break;
                    case 29:
                        printLexeme(lex);
                        System.out.println("Is_Equal_To");
                        flag = true;
                        state = 0;
                        i = 0;

                        break;
                    case 30:
                        if (lookahead == '=') {
                            state = 31;
                            lex[i++] = lookahead;
                            lex[i] = '\0';
                        } else {
                            flag = true;
                            state = 32;
                            lex[i] = '\0';
                        }
                        break;
                    case 31:
                        printLexeme(lex);
                        System.out.println("Not_Equal_To");
                        state = 0;
                        i = 0;
                        flag = true;

                        break;
                    case 32:
                        printLexeme(lex);
                        System.out.println("Logical_Not");
                        state = 0;
                        i = 0;
                        flag = true;

                        break;
                    case 33:
                        if (lookahead == '&') {
                            state = 34;
                            lex[i++] = lookahead;
                            lex[i] = '\0';
                        } else {
                            flag = true;
                            state = 35;
                            lex[i] = '\0';
                        }
                        break;
                    case 34:
                        printLexeme(lex);
                        System.out.println("Logical_AND");
                        state = 0;
                        i = 0;
                        flag = true;

                        break;
                    case 35:
                        printLexeme(lex);
                        System.out.println("AND_operator");
                        state = 0;
                        i = 0;
                        flag = true;
                        break;
                    case 36:
                        if (lookahead == '|') {
                            state = 38;
                            lex[i++] = lookahead;
                            lex[i] = '\0';
                        } else {
                            flag = true;
                            state = 37;
                            lex[i] = '\0';
                        }
                        break;
                    case 37:
                        printLexeme(lex);
                        System.out.println("OR_operator");
                        state = 0;
                        i = 0;
                        flag = true;

                        break;
                    case 38:
                        printLexeme(lex);
                        System.out.println("Logical_OR");
                        state = 0;
                        i = 0;
                        flag = true;

                        break;
                    case 39:
                        if (Character.isLetterOrDigit(lookahead) || lookahead == '_') {
                            lex[i++] = lookahead;
                            state = 39;

                        } else {
                            flag = true;
                            state = 40;
                            lex[i] = '\0';
                        }
                        break;
                    case 40:
                        printLexeme(lex);
                        System.out.println(isReserved(lex));
                        state = 0;
                        i = 0;
                        flag = true;

                        break;
                    case 41:
                        if (Character.isDigit(lookahead)) {
                            lex[i++] = lookahead;
                        } else if (lookahead == '.') {
                            state = 43;
                            lex[i++] = lookahead;
                        } else {
                            flag = true;
                            state = 42;
                            lex[i] = '\0';
                        }
                        break;
                    case 42:
                        lex[i] = '\0';
                        printLexeme(lex);
                        System.out.println("int_literal");
                        state = 0;
                        i = 0;
                        flag = true;
                        break;
                    case 43:
                        if (Character.isDigit(lookahead)) {
                            lex[i++] = lookahead;
                            state = 44;
                        } else {
                            System.err.println("ERROR()");
                            System.exit(0);
                        }

                        break;
                    case 44:
                        if (Character.isDigit(lookahead)) {
                            lex[i++] = lookahead;
                        } else {
                            flag = true;
                            lex[i] = '\0';
                            state = 45;

                        }

                        break;
                    case 45:
                        printLexeme(lex);
                        System.out.println("real_number_literal");
                        state = 0;
                        i = 0;

                        break;
                    case 47:
                        lex[i] = '\0';
                        printLexeme(lex);
                        System.out.println("simi_colon");
                        state = 0;
                        i = 0;

                        break;
                    case 48:
                        lex[i] = '\0';
                        printLexeme(lex);
                        System.out.println("comma");
                        state = 0;
                        i = 0;
                        break;
                    case 49:
                        lex[i] = '\0';
                        printLexeme(lex);
                        System.out.println("open_sequre_bracket");
                        state = 0;
                        i = 0;

                        break;
                    case 50:
                        lex[i] = '\0';
                        printLexeme(lex);
                        System.out.println("close_sequre_bracket");
                        state = 0;
                        i = 0;

                        break;
                    case 51:
                        lex[i] = '\0';
                        printLexeme(lex);
                        System.out.println("open_curly_bracket");
                        state = 0;
                        i = 0;

                        break;
                    case 52:
                        lex[i] = '\0';
                        printLexeme(lex);
                        System.out.println("close_curly_bracket");
                        state = 0;
                        i = 0;

                        break;
                    case 53:
                        lex[i] = '\0';
                        printLexeme(lex);
                        System.out.println("open_bracket");
                        state = 0;
                        i = 0;

                        break;
                    case 54:
                        lex[i] = '\0';
                        printLexeme(lex);
                        System.out.println("close_bracket");
                        state = 0;
                        i = 0;

                        break;
                    case 55:
                        lex[i] = '\0';
                        printLexeme(lex);
                        System.out.println("dot");
                        state = 0;
                        i = 0;

                        break;
                    case 56:
                        if (lookahead == '"') {
                            state = 57;
                        } else {
                            lex[i++] = lookahead;
                            break;
                        }
                        break;
                    case 57:
                        lex[i] = '\0';
                        printLexeme(lex);
                        System.out.println("string_literal");
                        state = 0;
                        i = 0;
                        flag = true;
                        break;
                    case 58:
                        if (lookahead == '\\') {
                            state = 61;
                            lex[i++] = lookahead;
                        } else {
                            lex[i++] = lookahead;
                            state = 59;
                        }

                        break;
                    case 59:
                        if (lookahead == '\'') {
                            flag = true;
                            state = 60;
                        } else {
                            System.err.println("ERROR()");
                            System.exit(0);
                        }
                        break;

                    case 60:

                        lex[i] = '\0';
                        printLexeme(lex);
                        System.out.println("character_literal");
                        state = 0;
                        i = 0;

                        break;
                    case 61:
                        if (lookahead == '\\') {
                            lex[i++] = lookahead;
                            state = 68;
                        } else if (lookahead == 't') {
                            lex[i++] = lookahead;
                            state = 64;
                        } else if (lookahead == 'n') {
                            lex[i++] = lookahead;
                            state = 62;
                        } else if (lookahead == '0') {
                            lex[i++] = lookahead;
                            state = 66;
                        } else if (lookahead == '\'') {
                            lex[i++] = lookahead;
                            state = 70;
                        }
                        break;
                    case 62:
                        if (lookahead == '\'') {
                            state = 63;
                            lex[i++] = lookahead;
                        }
                        break;
                    case 63:
                        lex[i] = '\0';
                        printLexeme(lex);
                        System.out.println("new_line");
                        state = 0;
                        i = 0;

                        break;
                    case 64:
                        if (lookahead == '\'') {
                            state = 65;
                            lex[i++] = lookahead;
                        }
                        break;
                    case 65:
                        lex[i] = '\0';
                        printLexeme(lex);
                        System.out.println("tab");
                        state = 0;
                        i = 0;

                        break;
                    case 66:
                        if (lookahead == '\'') {
                            state = 67;
                            lex[i++] = lookahead;
                        }
                        break;
                    case 67:
                        lex[i] = '\0';
                        printLexeme(lex);
                        System.out.println("null");
                        state = 0;
                        i = 0;

                        break;
                    case 68:
                        if (lookahead == '\'') {
                            state = 69;
                            lex[i++] = lookahead;
                        }
                        break;
                    case 69:
                        lex[i] = '\0';
                        printLexeme(lex);
                        System.out.println("slash");
                        state = 0;
                        i = 0;

                        break;
                    case 70:
                        if (lookahead == '\'') {
                            state = 71;
                            lex[i++] = lookahead;
                        }
                        break;
                    case 71:
                        lex[i] = '\0';
                        printLexeme(lex);
                        System.out.println("single_cotation");
                        state = 0;
                        i = 0;

                        break;
                    default:
                        break;
                }
                if (flag) {
                    flag = false;
                    continue;
                } else if (j < word.length) {
                    lookahead = word[j++];
                } else if (j == word.length) {
                    break;
                }

            }
            j = 0;
        }
    }

    public static String isReserved(char[] lex) {
        String java_reserved[] = {"boolean", "byte", "char", "double", "float", "short", "void", "int", "long", "while", "for", "do", "switch",
            "break", "continue", "case", "default", "if", "else", "try", "catch", "finally", "class", "abstract", "extends", "final", "import", "private", "interface", "native",
            "public", "package", "implements", "protected", "return", "static", "super", "synchronized", "this", "throw", "throws", "transient", "volatile"};
        String temp = "";
        for (int i = 0; i < lex.length; i++) {
            if (lex[i] == '\0') {
                break;
            } else {
                temp += lex[i];
            }
        }
        for (int i = 0; i < java_reserved.length; i++) {
            if (temp.equals(java_reserved[i])) {
                return java_reserved[i];
            }
        }
        return "ID";
    }

    public static void printLexeme(char lex[]) {
        for (int i = 0; i < lex.length; i++) {
            if (lex[i] != '\0') {
                System.out.print(lex[i]);
            } else {
                System.out.print("\t\t|");
                return;
            }
        }
    }
}
