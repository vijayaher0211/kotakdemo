package listeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import utils.RetryAnalyzer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


/**
 * RetryListener
 * 
 * Implements TestNG's IAnnotationTransformer to automatically assign
 * the RetryAnalyzer to all test methods at runtime.
 * 
 * This enables retry logic globally without having to specify the retry analyzer
 * in each individual test method annotation.
 */



public class RetryListener implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
