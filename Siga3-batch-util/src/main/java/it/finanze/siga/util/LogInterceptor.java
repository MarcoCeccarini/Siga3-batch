package it.finanze.siga.util;

import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.apache.log4j.Logger;

import java.lang.reflect.Parameter;

@Logged
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class LogInterceptor {

    private static final Logger logger = Logger.getLogger("it.finanze.siga");

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        String className = context.getMethod().getDeclaringClass().getSimpleName();
        String methodName = context.getMethod().getName();

        logger.info("[" + className + "." + methodName + "] START" + buildParamLog(context));

        long start = System.currentTimeMillis();
        try {
            Object result = context.proceed();
            long elapsed = System.currentTimeMillis() - start;
            boolean isVoid = context.getMethod().getReturnType() == void.class;
            String returnClause = isVoid ? "" : " return=" + safeToString(result);
            logger.info("[" + className + "." + methodName + "] END (" + elapsed + "ms)" + returnClause);
            return result;
        } catch (Exception e) {
            long elapsed = System.currentTimeMillis() - start;
            logger.error("[" + className + "." + methodName + "] ERROR after " + elapsed + "ms: " + e.getMessage(), e);
            throw e;
        }
    }

    private String buildParamLog(InvocationContext context) {
        Object[] params = context.getParameters();
        if (params == null || params.length == 0) {
            return "";
        }
        Parameter[] paramMeta = context.getMethod().getParameters();
        StringBuilder sb = new StringBuilder(" params=[");
        for (int i = 0; i < params.length; i++) {
            if (i > 0) sb.append(", ");
            String name = (paramMeta != null && i < paramMeta.length) ? paramMeta[i].getName() : "arg" + i;
            sb.append(name).append("=").append(safeToString(params[i]));
        }
        sb.append("]");
        return sb.toString();
    }

    private String safeToString(Object obj) {
        if (obj == null) return "null";
        try {
            String s = obj.toString();
            return s.length() > 500 ? s.substring(0, 500) + "..." : s;
        } catch (Exception e) {
            return obj.getClass().getSimpleName() + "@[toString error]";
        }
    }
}
