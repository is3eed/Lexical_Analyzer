package lexical_analyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lexical_Analyzer {

    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("input.txt");
        Scanner in = new Scanner(System.in);
        int state = 0;
        char lex[] = new char[32];
        char word[];
        int i = 0, j = 0;
        char la;
        boolean flag = false; 
        boolean unget = false; // true means we found a token so stop adding to char lex
        // state 0 is begining, state 1,2 is id, state 3,4 integer (constant), state 
        char specifiers[] = {'n', 'a', 't', 'r', 'v', 'o', 'f', '?', '"', 'b', 39, 92};
//        if (!input.exists()) {
//            System.out.println("Cannot find file ");
//            System.exit(0);
//        }
        // problem now if analyzer finish a token the next char will be ignored " check final states to add something
        while (in.hasNext()) {
            word = in.next().toCharArray();
            la = word[j++];
            while (j <= word.length) {
                switch (state) {
                    case 0:
                        if (la == '\r' || la == '\t' || la == '\n' || la == ' ') {
                            state = 0;
                            // read 1 character
                        } else if (la == '/') {
                            state = 1;
                            lex[i++] = la;
                            // read 1 character
                        } else if (la == '+') {
                            state = 7;
                            lex[i++] = la;
                        } else if (la == '-') {
                            state = 11;
                            lex[i++] = la;
                        } else if (la == '%') {
                            state = 15;
                            lex[i++] = la;
                        } else if (la == '*') {
                            state = 18;
                        } else if (la == '<') {
                            state = 21;
                            lex[i++] = la;
                        } else if (la == '>') {
                            state = 24;
                            lex[i++] = la;
                        } else if (la == '=') {
                            state = 27;
                            lex[i++] = la;
                        } else if (la == '!') {
                            state = 30;
                            lex[i++] = la;
                        } else if (la == '&') {
                            state = 33;
                            lex[i++] = la;
                        } else if (la == '|') {
                            state = 36;
                            lex[i++] = la;
                        } else if (Character.isAlphabetic(la) || la == '_') {
                            state = 39;
                            lex[i++] = la;
                        } else if (Character.isDigit(la)) {
                            state = 41;
                            lex[i++] = la;
                        }else if(la==';'){
                        state=47;
                        lex[i++]=la;
                        }else if(la==','){
                        state=48;
                        lex[i++]=la;
                        }else if(la=='['){
                        state=49;
                        lex[i++]=la;
                        }else if(la==']'){
                            state=50;
                        lex[i++]=la;
                        }else if(la=='{'){
                            state=51;
                        lex[i++]=la;
                        }else if(la=='}'){
                            state=52;
                        lex[i++]=la;
                        }else if(la=='('){
                            state=53;
                        lex[i++]=la;
                        }else if(la==')'){
                            state=54;
                        lex[i++]=la;
                        }else if(la=='.'){
                            state=55;
                        lex[i++]=la;
                        }else if(la=='"'){
                            state=56;
                        lex[i++]=la;
                        }else if(la=='\''){
                            state=58;
                        lex[i++]=la;
                        }

                        break;
                    case 1:
                        if (la == '/') {
                            state = 2;
                            // read 1 character 
                        } else if (la == '=') {
                            state = 3;
                            lex[i++] = la;
                            flag = true;
                            lex[i] = '\0';

                        } else if (la == '*') {
                            state = 4;

                        } else {
                            state = 6;
                        }
                        break;
                    case 2:
                        if (la == '\n') {
                            state = 0;
                            i = 0;
                        } else {
                            // anything here is content of a comment, so it is ignored
                        }
                        break;
                    case 3:
                        printLexeme(lex);
                        System.out.println("div_assign_operator");
                        i = 0;
                        state = 0;
                        break;
                    case 4:
                        if (la == '*') {
                            state = 5;

                        } else {
                            state = 4;

                        }
                        break;
                    case 5:
                        if (la == '/') {
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
                        if (la == '+') {
                            state = 9;
                            lex[i++] = la;
                            lex[i] = '\0';
                        } else if (la == '=') {
                            state = 8;
                            lex[i++] = la;
                            lex[i] = '\0';
                        } else {
                            state = 10;
                            lex[i] = '\0';
                        }
                        break;
                    case 8:
                        printLexeme(lex);
                        System.out.println("plus_assign_operator");
                        i = 0;
                        state = 0;
                        break;
                    case 9:
                        printLexeme(lex);
                        System.out.println("increment_operator");
                        i = 0;
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
                        if (la == '-') {
                            state = 13;
                            lex[i++] = la;
                            lex[i] = '\0';
                        } else if (la == '=') {
                            state = 12;
                            lex[i++] = la;
                            lex[i] = '\0';
                        } else {
                            state = 14;
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
                        if (la == '=') {
                            state = 16;
                            lex[i++] = la;
                            lex[i] = '\0';
                        } else {
                            state = 17;
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
                        if (la == '=') {
                            state = 19;
                            lex[i++] = la;
                            lex[i] = '\0';

                        } else {
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
                        if (la == '=') {
                            state = 23;
                            lex[i++] = la;
                            lex[i] = '\0';
                        } else {
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
                        if (la == '=') {
                            state = 26;
                            lex[i++] = la;
                            lex[i] = '\0';
                        } else {
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
                        if (la == '=') {
                            state = 29;
                            lex[i++] = la;
                            lex[i] = '\0';
                        } else {
                            state = 28;
                            lex[i] = '\0';
                        }
                        break;
                    case 28:
                        printLexeme(lex);
                        System.out.println("assign_operator");
                        state = 0;
                        i = 0;
                        unget = true;
                        break;
                    case 29:
                        printLexeme(lex);
                        System.out.println("Is_Equal_To");
                        flag = true;
                        state = 0;
                        i = 0;
                        break;
                    case 30:
                        if (la == '=') {
                            state = 31;
                            lex[i++] = la;
                            lex[i] = '\0';
                        } else {
                            state = 32;
                            lex[i] = '\0';
                        }
                        break;
                    case 31:
                        printLexeme(lex);
                        System.out.println("Not_Equal_To");
                        state = 0;
                        i = 0;
                        unget = true;
                        break;
                    case 32:
                        printLexeme(lex);
                        System.out.println("Logical_Not");
                        state = 0;
                        i = 0;
                        unget = true;
                        break;
                    case 33:
                        if (la == '&') {
                            state = 34;
                            lex[i++] = la;
                            lex[i] = '\0';
                        } else {
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
                        unget = true;
                        break;
                    case 36:
                        if (la == '|') {
                            state = 38;
                            lex[i++] = la;
                            lex[i] = '\0';
                        } else {
                            state = 37;
                            lex[i] = '\0';
                        }
                        break;
                    case 37:
                        printLexeme(lex);
                        System.out.println("OR_operator");
                        state = 0;
                        i = 0;
                        unget = true;
                        break;
                    case 38:
                        printLexeme(lex);
                        System.out.println("Logical_OR");
                        state = 0;
                        i = 0;
                        flag = true;
                        break;
                    case 39:
                        if (Character.isLetterOrDigit(la) || la == '_') {
                            lex[i++] = la;
                        } else {
                            state = 40;
                            lex[i] = '\0';
                        }
                        break;
                    case 40:
                        printLexeme(lex);
                        System.out.println("ID");
                        state = 0;
                        i = 0;
                        unget = true;
                        break;
                    case 41:
                        if (Character.isDigit(la)) {
                            lex[i++] = la;
                        } else if (la == '.') {
                            state = 42;
                            lex[i++] = la;
                        } else {
                            state = 43;
                            lex[i] = '\0';
                        }
                        break;
                    case 42:
                        
                        break;
                    case 43:

                        break;
                    case 44:

                        break;
                    case 45:

                        break;
                    case 46:

                        break;
                    case 47:
                        lex[i]='\0';
                        printLexeme(lex);
                        System.out.println("simi_colon");
                        state = 0;
                        i = 0;
                        flag = true;
                        break;
                    case 48:
                        lex[i]='\0';
                        printLexeme(lex);
                        System.out.println("comma");
                        state = 0;
                        i = 0;
                        flag = true;
                        break;
                    case 49:
                        lex[i]='\0';
                        printLexeme(lex);
                        System.out.println("open_sequre_bracket");
                        state = 0;
                        i = 0;
                        flag = true;
                        break;
                    case 50:
                        lex[i]='\0';
                        printLexeme(lex);
                        System.out.println("close_sequre_bracket");
                        state = 0;
                        i = 0;
                        flag = true;
                        break;
                    case 51:
                        lex[i]='\0';
                        printLexeme(lex);
                        System.out.println("open_curly_bracket");
                        state = 0;
                        i = 0;
                        flag = true;
                        break;
                    case 52:
                        lex[i]='\0';
                        printLexeme(lex);
                        System.out.println("close_curly_bracket");
                        state = 0;
                        i = 0;
                        flag = true;
                        break;
                    case 53:
                        lex[i]='\0';
                        printLexeme(lex);
                        System.out.println("open_bracket");
                        state = 0;
                        i = 0;
                        flag = true;
                        break;
                    case 54:
                        lex[i]='\0';
                        printLexeme(lex);
                        System.out.println("close_bracket");
                        state = 0;
                        i = 0;
                        flag = true;
                        break;
                    case 55:
                        lex[i]='\0';
                        printLexeme(lex);
                        System.out.println("dot");
                        state = 0;
                        i = 0;
                        flag = true;
                        break;
                    case 56:
                        if (la=='"'){
                        state=57;
                        lex[i++]=la;
                        }else{
                        state=56;
                        break;
                        }
                        break;
                    case 57:
                        lex[i]='\0';
                        printLexeme(lex);
                        System.out.println("string");
                        state = 0;
                        i = 0;
                        flag = true;
                        break;
                    case 58:
                        if(la=='\\'){
                        state=61;
                        lex[i++]=la;
                        }else{
                        state=59;
                        }

                        break;
                    case 59:
                        lex[i++]=la;
                        state=60;
                        break;
                    case 60:
                        if(la=='\''){
                            lex[i]='\0';
                        printLexeme(lex);
                        System.out.println("charecter");
                        state = 0;
                        i = 0;
                        flag = true;
                        }
                        break;
                    case 61:
                        if(la=='\\'){
                        lex[i++]=la;
                        state=68;
                        }else if(la=='t'){
                        lex[i++]=la;
                        state=64;
                        }else if(la=='n'){
                        lex[i++]=la;
                        state=62;
                        }else if(la=='0'){
                        lex[i++]=la;
                        state=66;
                        }else if(la=='\''){
                        lex[i++]=la;
                        state=70;
                        }
                        break;
                    case 62:
                        if(la=='\''){
                        state=63;
                        lex[i++]=la;
                        }
                        break;
                    case 63:
                        lex[i]='\0';
                        printLexeme(lex);
                        System.out.println("new_line");
                        state = 0;
                        i = 0;
                        flag = true;
                        break;
                    case 64:
                        if(la=='\''){
                        state=63;
                        lex[i++]=la;
                        }
                        break;
                    case 65:
                        lex[i]='\0';
                        printLexeme(lex);
                        System.out.println("tab");
                        state = 0;
                        i = 0;
                        flag = true;
                        break;
                    case 66:
                        if(la=='\''){
                        state=63;
                        lex[i++]=la;
                        }
                        break;
                    case 67:
                        lex[i]='\0';
                        printLexeme(lex);
                        System.out.println("null");
                        state = 0;
                        i = 0;
                        flag = true;
                        break;
                    case 68:
                        if(la=='\''){
                        state=63;
                        lex[i++]=la;
                        }
                        break;
                    case 69:
                        lex[i]='\0';
                        printLexeme(lex);
                        System.out.println("slash");
                        state = 0;
                        i = 0;
                        flag = true;
                        break;
                    case 70:
                        if(la=='\''){
                        state=63;
                        lex[i++]=la;
                        }
                        break;
                    case 71:
                        lex[i]='\0';
                        printLexeme(lex);
                        System.out.println("double_cotation");
                        state = 0;
                        i = 0;
                        flag = true;
                        break;
                    default:
                        break;
                }
                if (j > word.length - 1 || flag || unget) {
                    flag = false;
                    unget = false;
                }else{
                la = word[j++];
                }
            }
            j = 0;

        }
//        while (in.hasNext()) {
//            String word = in.nextLine();
//            while (i < word.length() - 1) {
//                char la = word.charAt(i++);
//                switch (state) {
//                    case 0:
//                        flag = true;
//                        if (la == '\r' || la == '\t' || la == '\n') {
//                            state = 0;
//                        } else if (la == '+' || la == '-' || la == '*' || la == '/' || la == '%') {
//                            state = 1;
//                            lex[j] += la;
//
//                        } else if (Character.isAlphabetic(la) || la == '_') {
//                            state = 4;
//                            lex[j] += la;
//                        }
//                        break;
//                    case 1: // arith operators
//                        // 2
//
//                        break;
//                    case 2:  // arith assign opertators
//                        // 3 
//                        break;
//                    case 3: // 
//
//                        break;
//                    case 4:
//                        if (Character.isLetterOrDigit(la) || la == '_') {
//                            lex[j] += la;
//                        } else {
//                            state = 0;
//                            token[j++] = "ID";
//                            flag = false;
//
//                        }
//                        break;
//                    case 5:
//
//                        break;
//                    case 6:
//
//                        break;
//                    case 7:
//
//                        break;
//
//                }
//                i++;
//                if (flag) {
//                    la = word.charAt(i);
//                }
//            }
//
//        }

    }

    public static String[] getReserved() {
        String java_reserved[] = {"boolean", "byte", "char", "double", "float", "short", "void", "int", "long", "while", "for", "do", "switch",
            "break", "continue", "case", "default", "if", "else", "try", "catch", "finally", "class", "abstract", "extends", "final", "import", "private", "interface", "native",
            "public", "package", "implements", "protected", "return", "static", "super", "synchronized", "this", "throw", "throws", "transient", "volatile"};
        return java_reserved;
    }

    public static void printLexeme(char lex[]) {
        for (int i = 0; i < lex.length; i++) {
            if (lex[i] != '\0') {
                System.out.print(lex[i]);
            } else {
                System.out.print("\t");
                return;
            }
        }
    }
}
