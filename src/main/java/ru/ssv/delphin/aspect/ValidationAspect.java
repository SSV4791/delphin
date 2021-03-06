package ru.ssv.delphin.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.ssv.delphin.exception.ModelValidationException;
import ru.ssv.delphin.validator.Validator;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

@Aspect
@Component
public class ValidationAspect {
    private final ApplicationContext applicationContext;

    public ValidationAspect(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Before("execution(* ru.ssv.delphin.controller.*Controller.*(..))")
    public void validate(JoinPoint call) {
        var signature = (MethodSignature) call.getSignature();
        var method = signature.getMethod();
        var parameterAnnotations = method.getParameterAnnotations();
        var args = call.getArgs();
        for (int argIndex = 0; argIndex < parameterAnnotations.length; argIndex++) {
            for (Annotation annotation : parameterAnnotations[argIndex]) {
                if (!(annotation instanceof Validation)) {
                    continue;
                }
                var type = ((Validation) annotation).type();
                Validator bean = getBean(type);
                var errors = new ArrayList<String>();
                if (bean.validate(args[argIndex], errors)) {
                    return;
                }
                throw new ModelValidationException(errors);
            }
        }
    }

    private <T> T getBean(Class<? extends T> clazz, String beanName) {
        if (!applicationContext.containsBean(beanName)) {
            DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(clazz)
                    .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
            beanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        }
        return (T) applicationContext.getBean(beanName);
    }

    private <T> T getBean(Class<? extends T> beanClass) {
        return (T) applicationContext.getBean(beanClass);
    }
}
