package vermillion.productions.global.utils;

/**
 * A class that adds holds an instance of a certain object type and adds a method to get it.
 */
public abstract class Instantiable<T> {
    private final T instance;

    /**
     * Default constructor for bukkit instantiation.
     *
     * @param instance The plugin instance.
     */
    public Instantiable(T instance) {
        this.instance = instance;
    }

    /**
     * A getter that returns the plugin instance.
     *
     * @return The plugin instance.
     */
    protected T instance() {
        return instance;
    }

}
