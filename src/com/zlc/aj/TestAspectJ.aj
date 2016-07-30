package com.zlc.aj;

public aspect TestAspectJ {
	
	pointcut sayHelloPointCut() : execution(* com.zlc.aj.Test.sayHello(..));
	before() : sayHelloPointCut() {
		System.out.println("PointCut开始事务 ...");
	}
	/*
	void around() : call(void com.zlc.aj.Test.sayHello()) {
		System.out.println("开始事务 ...");
		proceed();
		System.out.println("事务结束 ...");
	}
	*/
	
	/*
	pointcut services(): target(Test) && call(public * *(..)) && args(..);
	before(): services() {
		System.out.println("PointCut开始事务---->   " + thisJoinPoint);
	}
	*/
	
	/*
    before(Test test): target(test) && execution(public void say*(..)) {
		System.out.println("PointCut开始事务---->   " + thisJoinPoint);
    }
    */
	
	/*
    before(): call(* com.zlc.aj.Test.say*(..)) {
		System.out.println("PointCut开始事务---->   " + thisJoinPoint);
    }
    */
}
