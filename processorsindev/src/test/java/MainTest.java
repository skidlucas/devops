import junit.framework.Assert;
import mutation.*;
import org.junit.Test;
import spoon.Launcher;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.visitor.filter.NameFilter;

import java.util.List;

/**
 * @author Lucas MARTINEZ
 * @version 05/02/16
 */
public class MainTest {

    @Test
    public void testGetInt(){
        Main main = new Main();
        int var = main.getInt();
        Assert.assertEquals(10, var);
    }

    @Test
    public void testGenerateMutant(){
        String codeToBeMutated = "src/main/java/Main.java";

       /* BinaryOperatorMutator mutationOperator = new BinaryOperatorMutator();
        OtherMutation mutationFreestyle = new OtherMutation();
        MutationTester<Main> mutationTester = new MutationTester<Main>(codeToBeMutated, mutationFreestyle);

        mutationTester.generateMutants();
        List<CtClass> mutants = mutationTester.getMutants();
        for(CtClass mut : mutants)
            System.out.println(mut.toString());*/
        Launcher l = new Launcher();
        l.addProcessor(new LoopIterationProcessor());
        l.addInputResource(codeToBeMutated);
        l.run();
        CtClass c = (CtClass) l.getFactory().Package().getRootPackage().getElements(new NameFilter("Main")).get(0);

        // printing the metaprogram
        System.out.println("// Metaprogram: ");
        System.out.println(c.toString());
    }
}
