import java.lang.*;
import java.lang.reflect.*;
public class SimpleUnitTester {
    
    public int execute(Class clazz) throws Exception {
        int failedCount = 0;
        
        // your code
        Constructor[] cons= clazz.getConstructors();
        Object obj = cons[0].newInstance();

        Method[] methods = clazz.getMethods();
        Boolean bool = Boolean.valueOf("False");
        for(Method method:methods){
            System.out.println(method.getName());
            if(method.getName().startsWith("test")){
                System.out.println("--"+method.getName()+"->"+method.getReturnType().getName());
                if(method.getReturnType().getName().equals("boolean")) {
                    System.out.println("----"+method.getName());
                    Object result = method.invoke(obj);
                    if(result.equals(Boolean.FALSE)){
                     System.out.println("------ ++");
                     ++failedCount;
                    }
                }
            }
        }
        return failedCount;
    }
}