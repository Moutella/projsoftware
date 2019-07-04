package aspectos;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import anotacao.Perfil;
import perfis.SingletonPerfis;
import excecao.NaoTemOUsuarioException;
@Aspect
public class ChecaLoginAspect {
	 private static final Exception Exception = null;
	@Pointcut("execution(* * (..)) && within(servico..*)")
	 public void checaLogin() {
	    }
	 @Around("checaLogin()")
	public Object checaLoginAntes(ProceedingJoinPoint joinPoint) throws Throwable {
		String[] perfis = SingletonPerfis.getSingletonPerfis().getPerfis();
	
		System.out.println("Vai  tentar executar o metodo " + joinPoint.getSignature().getName());
		Class c = joinPoint.getTarget().getClass();
		Class<?>[] methodArgumentTypes = null;
		for(int j = 0; j<c.getDeclaredMethods().length; j++) {
			Method metodo = c.getDeclaredMethods()[j];
			metodo.getAnnotatedExceptionTypes();
			if(metodo.getName().equals(joinPoint.getSignature().getName())) {
				Parameter[] p = metodo.getParameters();
				int numParam = metodo.getParameters().length;
				methodArgumentTypes = new Class<?>[numParam];
				for(int i = 0; i < metodo.getParameters().length; i++) {
					methodArgumentTypes[i] = metodo.getParameters()[i].getType();
				}				
			}
		}
	
		Method executado = c.getDeclaredMethod(joinPoint.getSignature().getName(), methodArgumentTypes);
		if(executado.getAnnotation(Perfil.class)!=null) {
			boolean temUser = false;
			for(String nome:executado.getAnnotation(Perfil.class).nomes()) {
				for(int j = 0; j < perfis.length; j++) {
					if(nome.equals(perfis[j])) {
						temUser = true;
					}
				}
			}
			if(temUser) {

				System.out.println("Vai executar o método");
				return joinPoint.proceed();
			}
			else {
				System.out.println("Não vai executar o método pois não tem o usuário");
				throw new NaoTemOUsuarioException("Não tem o usuário necessário");
			}

			
		}
		else {
			return joinPoint.proceed();
		}
	}
}
