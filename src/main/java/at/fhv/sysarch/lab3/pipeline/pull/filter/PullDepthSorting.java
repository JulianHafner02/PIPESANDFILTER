package at.fhv.sysarch.lab3.pipeline.pull.filter;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.pull.IPull;
import at.fhv.sysarch.lab3.pipeline.pull.Pull;

import java.util.Comparator;
import java.util.LinkedList;

public class PullDepthSorting<T extends Face> extends Pull<T, Face> {

    private final LinkedList<Face> sortedFaces = new LinkedList<>();
    public PullDepthSorting(IPull<T> source) {
        super(source);
    }

    @Override
    public Face pull() {
        return sortedFaces.removeFirst();
    }

    @Override
    public boolean hasNext() {
        if (source.hasNext() && sortedFaces.isEmpty()) {
            sortNewFaces();
        }
        return !sortedFaces.isEmpty();
    }

    private void sortNewFaces() {
        while(source.hasNext()) {
            sortedFaces.add(source.pull());
        }
        sortedFaces.sort(Comparator.comparing(face -> face.getV1().getZ() + face.getV2().getZ() + face.getV3().getZ()));
    }
}
