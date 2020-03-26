package com.sunlands.intl.yingshi.groovy;

/**
 * 创 建 人: xueh
 * 创建日期: 2019/2/25 15:31
 * 备注：
 */
//@Aspect
//public class BehaviorAscept {
//
//    @Pointcut("execution(@com.sunlands.intl.yingshi.groovy.CheckNet * *(. .))")
//    public void checkNetBehavior() {
//    }
//    @Around("checkNetBehavior()")
//    public Object dealPoint(ProceedingJoinPoint point) throws Throwable {
//        MethodSignature mSignature = (MethodSignature) point.getSignature();
//        CheckNet mCheckNet = mSignature.getMethod().getAnnotation(CheckNet.class);
//        if (mCheckNet != null) {
//            if (!CommonUtils.hasNetWorkConection()) {
//                ToastUtils.showShort("请检查网络设置");
//                return null;
//            }
//        }
//        return point.proceed();
//    }
//}