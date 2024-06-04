package at.fhv.sysarch.lab3.pipeline.pull;
public abstract class Pull<I, O> implements IPull<O> {

    protected final IPull<I> source;

    protected Pull(IPull<I> source) {
        this.source = source;
    }

    @Override
    public boolean hasNext() {
        return source.hasNext();
    }
}
