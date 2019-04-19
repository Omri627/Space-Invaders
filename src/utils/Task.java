package utils;

/**
 * a task is something that needs to happen and run.
 * @param <T>
 */
public interface Task<T> {
    /**
     * run the task.
     * @return T
     */
    T run();
}