package org.wildstang.framework;

/**
 * Contains utility functions used across the framework.
 */
public class CoreUtils {

    /**
     * Throws a NullPointerException if p_param is null.
     * @param p_param Object to check if null.
     * @param p_message Message to use in exception.
     * @throws NullPointerException If p_param is null.
     */
    public static void checkNotNull(Object p_param, String p_message) {
        if (p_param == null) {
            throw new NullPointerException(p_message);
        }
    }

    /**
     * Creates an object from a Class object.
     * @param p_class Class to construct.
     * @return Constructed class.
     */
    public static Object createObject(Class<?> p_class) {
        CoreUtils.checkNotNull(p_class, "p_class is null");

        Object obj = null;

        try {
            obj = p_class.getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }

        return obj;
    }
}