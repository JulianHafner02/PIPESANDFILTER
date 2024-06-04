package at.fhv.sysarch.lab3.pipeline.push;
public abstract class Push<T, N> implements IPush<T>{

    protected final IPush<N> successor;

    protected Push(IPush<N> successor) {
        this.successor = successor;
    }
}
