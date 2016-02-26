/**
 * @author Lucas MARTINEZ
 * @version 05/02/16
 */
public class Main {

    public int getInt(){
        int i = 0;
        int w , j = 0;
        for(int b = 10 ; i >= 10 ; ++i){
            System.out.println(" ");
            continue;
        }
        i = 10;
        String str = new String();
        while(i++ < 100)
            continue;
        return i = 10;
    }

    public int getIntMutated() {
        int i = 0;
        int w;
        int j = 0;
        {int Round100Loop = 0;
            for (int b = 10 ;  ; ++i) {
                java.lang.System.out.println(" ");
                //continue;
                if(Round100Loop++ == 100)break;
            }
        }//;
        i = 10;
        java.lang.String str = new java.lang.String();
        {int Round100Loop = 0;
            while ((i++) < 100) {
                //continue;
                if(Round100Loop++ == 100)break;
            }
        }//;
        return i = 10;
    }

}
