package fr.inria.gforge.spoon;

/**
 * Hello world!
 *
 */
public class App 
{
    public static int calc(String s){
        int i = 0;
        while (i < 10)
            i = i + 2;
        return i;
    }
    public static void main( String[] args )
    {
        String s = "l";
        int i = calc(s);
        System.out.println(i);
        System.out.println( "Hello World!" );
    }
}
