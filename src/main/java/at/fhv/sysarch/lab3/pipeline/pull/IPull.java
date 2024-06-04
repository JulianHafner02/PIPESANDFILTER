package at.fhv.sysarch.lab3.pipeline.pull;

public interface IPull<R> {

    R pull();

    boolean hasNext();

}
