import java.util.*;

public class Hangman {
    String[] dict = { "ABSTRACT", "ASSERT", "BOOLEAN", "BREAK", "BYTE", "CASE", "CATCH", "CHAR", "CLASS", "CONST",
            "CONTINUE", "DEFAULT", "DOUBLE", "DO", "ELSE", "ENUM", "EXTENDS", "FALSE", "FINAL", "FINALLY", "FLOAT",
            "FOR", "GOTO", "IF", "IMPLEMENTS", "IMPORT", "INSTANCEOF", "INT", "INTERFACE", "LONG", "NATIVE", "NEW",
            "NULL", "PACKAGE", "PRIVATE", "PROTECTED", "PUBLIC", "RETURN", "SHORT", "STATIC", "STRICTFP", "SUPER",
            "SWITCH", "SYNCHRONIZED", "THIS", "THROW", "THROWS", "TRANSIENT", "TRUE", "TRY", "VOID", "VOLATILE",
            "WHILE" };
    static String s;
    static char sol[];
    static int len;
    static int numOfTries;
    static boolean gameOver;

    Hangman() {
        s = dict[new Random().nextInt(dict.length)];
        gameOver = false;
        len = s.length();
        sol = new char[len];
        for (int i = 0; i < len; i++) {
            if (isVowel(s.charAt(i)))
                sol[i] = s.charAt(i);
            else
                sol[i] = '_';
        }
        numOfTries = len + 2;
    }

    void display() {
        for (int i = 0; i < len; i++)
            System.out.print(sol[i]);
        System.out.println();
    }

    String Gen() {
        String k = "";
        for (int i = 0; i < len; i++)
            k += sol[i];
        return k;
    }

    boolean isVowel(char k) {
        if (k == 'A' || k == 'E' || k == 'I' || k == 'O' || k == 'U')
            return true;
        else
            return false;
    }

    public static void main(String args[]) {
        Hangman obj = new Hangman();
        Scanner sc = new Scanner(System.in);
        System.out.println("-----GAME BEGINS-----");
        obj.display();
        do {

            System.out.println("Enter letter");
            char ch = sc.next().charAt(0);
            System.out.println("Enter position");
            int a = sc.nextInt();
            if (ch == s.charAt(a - 1)) {
                sol[a - 1] = ch;
                obj.display();
                if (obj.Gen().equals(s)) {
                    System.out.println("YOU HAVE GUESSED THE WORD!");
                    System.exit(0);
                }
            } else {
                System.out.println("Number of Tries Left: " + (--numOfTries));
                if (numOfTries <= 0) {
                    System.out.println("YOU HAVE RUN OUT OF TRIES!");
                    System.exit(0);
                } else {
                    System.out.println("INCORRECT. TRY AGAIN");
                    System.out.println("DO YOU WANT A HINT?(y/n)");
                    char c = sc.next().charAt(0);
                    if (c == 'y') {
                        for (int i = 0; i < s.length(); i++) {
                            char p = s.charAt(i);
                            if (obj.isVowel(p) == false && sol[i] == '_') {
                                sol[i] = p;
                                break;
                            }
                        }
                        obj.display();
                    }
                }
            }
        } while (numOfTries > 0);

    }
}