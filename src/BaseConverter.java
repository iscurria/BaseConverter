import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author 24scurria
 * @version 12/2/22
 * Base converter that takes a number with a certain base and converts it to a number with a different base
 */

public class BaseConverter {
    private final String DIGITS = "0123456789ABCDEF";
    /**
     * Convert a String num in fromBase to base-10 int.
     * @param num the original number
     * @param fromBase the original from base
     * @return a base-10 int of num base fromBase
     */
    public int strToInt(String num, String fromBase)    {
        // "FF" "16"
        int value = 0, exp = 0;
        for(int i = num.length()-1; i >=0; i--) {
            value += DIGITS.indexOf(num.charAt(i)) * Math.pow(Integer.parseInt(fromBase), exp);
            exp++;
        }
        return value;
    }

    /**
     * Converting an int num in base 10 to a string in a new base
     * @param num the original base-10 number
     * @param toBase the base you want to convert the number to
     * @return the converted number or 0
     */
    public String intToStr(int num, int toBase) {
        String toNum = new String();
        //int index = -1;
        while(num > 0) {
            toNum = DIGITS.charAt(num % toBase) + toNum;
            num /= toBase;
        }
        return (toNum.equals(" ")) ? "0" : toNum;
    }

    /**
     * It takes an input number, then converts it to a new base,
     * prints that new number to the screen, and then writes it to a file
     */
    public void inputConvertPrintWrite()    {
        Scanner in = null;
        PrintWriter out = null;
        try {
            in = new Scanner(new File("datafiles/values10.dat"));
            out = new PrintWriter(new File("datafiles/converted.dat"));
            String[] line;
            String output;
            while(in.hasNext()) {
                line = in.nextLine().split("\t");
                if (Integer.parseInt(line[1]) < 2 || Integer.parseInt(line[1]) > 16)
                    System.out.println("Invalid input base " + line[1]);
                else if (Integer.parseInt(line[2]) < 2 || Integer.parseInt(line[2]) > 16)
                    System.out.println("Invalid output base " + line[2]);
                else {
                    output = intToStr(strToInt(line[0], line[1]), Integer.parseInt(line[2]));
                    out.println(line[0] + "\t" + line[1] + "\t" + output  + "\t" + line[2]);
                    System.out.println(line[0] + " base " + line[1] + " = " + output + " base " + line[2]);
                    //System.out.println("DEBUG: " + strToInt(line[0], line[1]));
                }
            }
                /*
                System.out.println(line[0]); // String num
                System.out.println(line[1]); // String fromBase
                System.out.println(line[2]); // String toBase
                 */

            if(out != null)
                out.close();
            if(in != null)
                in.close();

        }
        catch(Exception e) {
            System.out.println("Something bad happened. Details here: " + e.toString());
        }
    }

    /**
     * Main method of class BaseConverter
     * @param args command line arguments if needed
     */
    public static void main(String[] args) {
        BaseConverter bc = new BaseConverter();
        bc.inputConvertPrintWrite();
    }
}
