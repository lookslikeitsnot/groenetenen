package be.vdab.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
class PointcutExpressions {
	@Pointcut("execution(* be.vdab.services.*.*(..))")
	void services() {
		System.out.println("coucou la famille");
	}

	@Pointcut("execution(* be.vdab.services.*.*(..)) " + "|| execution(* org.springframework.transaction.*.*(..))")
	void servicesEnTransacties() {
		System.out.println("coucou la famille 2");
	}
}
