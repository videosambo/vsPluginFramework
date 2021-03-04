package fi.videosambo.pluginFramework.core.database;

/**
 * Variable for DatabaseQuery
 * Holds a value that has been specified in creation
 * @param <E> is String, Integer, Boolean, Blob, InputStram, Time or DateTime
 */
public class DBVar<E> {

    private E val;

    public DBVar(E val) {
        this.val = val;
    }

    /**
     * @return value that has been stored for this
     */
    public E getVal() {
        return val;
    }

    public void setVal(E val) {
        this.val = val;
    }
}
