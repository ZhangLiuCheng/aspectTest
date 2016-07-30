package com.zlc.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;

@Aspect
public class DebugTraceAspect {
	private static final String POINTCUT_METHOD = "execution(@com.zlc.annotation.DebugTrace * *(..))";
	private static final String POINTCUT_CONSTRUCTOR = "execution(@com.zlc.annotation.DebugTrace *.new(..))";

	@Pointcut(POINTCUT_METHOD)
	public void methodAnnotatedWithDebugTrace() {
	}

	@Pointcut(POINTCUT_CONSTRUCTOR)
	public void constructorAnnotatedDebugTrace() {
	}

	@Around("methodAnnotatedWithDebugTrace() || constructorAnnotatedDebugTrace()")
	public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
		CodeSignature methodSignature = (CodeSignature) joinPoint.getSignature();
		String className = methodSignature.getDeclaringType().getSimpleName();
		String methodName = methodSignature.getName();

		final StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object result = joinPoint.proceed();
		stopWatch.stop();

		log(className, buildLogMessage(methodName, stopWatch.getTotalTimeMillis()));
		return result;
	}

	private static String buildLogMessage(String methodName, long methodDuration) {
		StringBuilder message = new StringBuilder();
		message.append(methodName);
		message.append(" --> ");
		message.append("[");
		message.append(methodDuration);
		message.append("ms");
		message.append("]");
		return message.toString();
	}
	
	public static void log(String tag, String message) {
		System.out.println(tag + " --> " + message);
	}
}
