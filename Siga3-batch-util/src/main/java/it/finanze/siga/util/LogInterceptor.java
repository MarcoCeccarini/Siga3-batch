package it.finanze.siga.util;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;
 
public class LogInterceptor{
 
	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {
                // Log to console before executing method
		 
		Logg.getLogger().info("Entering method:" + context.getMethod().getName());
                // Execute method
		Object result = context.proceed();
                // Log to console after executing method
		Logg.getLogger().info("Leaving method: " + context.getMethod().getName() );
 
		return result;
	}
}