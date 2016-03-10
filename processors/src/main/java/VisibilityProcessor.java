import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.ModifierKind;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by lucas on 04/03/16.
 */
public class VisibilityProcessor extends AbstractProc{
    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        return candidate instanceof CtMethod;
    }

    public void process(CtElement candidate) {
        if (!(candidate instanceof CtMethod)) {
            return;
        }
        if(checkSelector())
            return;
        CtMethod method = (CtMethod) candidate;
        Set<ModifierKind> allModifier = method.getModifiers();
        Iterator i = allModifier.iterator();
        while(i.hasNext()){
            if(i.next().equals(ModifierKind.PUBLIC)){
                i.remove(); //met la visibilité en package
            }
        }
        super.printLogMutation(method.getPosition().toString());
    }

}
