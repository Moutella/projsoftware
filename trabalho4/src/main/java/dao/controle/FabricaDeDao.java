package dao.controle;

import net.sf.cglib.proxy.Enhancer;

public class FabricaDeDao {
    
    @SuppressWarnings("unchecked")
    public static <T> T getDao(String classeDoDaoComoString) throws Exception {
	Class<?> classeDoDao = Class.forName(classeDoDaoComoString);

	return (T) Enhancer.create(classeDoDao, new InterceptadorDeDAO());

    }
}