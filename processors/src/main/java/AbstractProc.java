import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by lucas on 04/03/16.
 */
public abstract class AbstractProc extends AbstractProcessor<CtElement> {

    private static final int K_LIKELIHOOD = 100;
    private Random rand;
    private static final String K_PATH_FILE = "./python/report/logMutation.txt";
    private Set<String> allModifiedFiles = new HashSet<>();

    /**
     * check if the mutation'll be done
     * @return
     */
    protected boolean checkSelector(){
        rand = new Random();
        return (K_LIKELIHOOD < rand.nextInt(101));
    }

    @Override
    public void process(CtElement candidate){
        printLogMutation(candidate.getPosition().toString());
    }

    protected void printLogMutation(String fileName){
        try {
            allModifiedFiles.add(processFileName(fileName));
            PrintWriter pw = new PrintWriter(new FileWriter(K_PATH_FILE));
            for(String str : allModifiedFiles){
                pw.println(str);
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String processFileName(String filename){
        String nameWithoutParent = filename.substring(1, filename.length() - 1);
        return nameWithoutParent.split(":")[0];
    }
}
