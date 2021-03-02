package fi.videosambo.pluginFramework.core.database;

public class DBVar<E> {

    private E val;

    public DBVar(E val) {
        this.val = val;
    }

    public E getVal() {
        return val;
    }

    public void setVal(E val) {
        this.val = val;
    }
}
