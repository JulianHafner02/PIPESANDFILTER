package at.fhv.sysarch.lab3.pipeline.push;

import at.fhv.sysarch.lab3.obj.Face;
import com.hackoeur.jglm.Vec4;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class PushSource extends Push<Face, Face> {
    private Queue<Face> sourceData = new ArrayDeque<>();

    public PushSource(IPush<Face> successor) {
        super(successor);
    }

    public void setSourceData(List<Face> sourceData) {
        this.sourceData.addAll(sourceData);
        Vec4 lastFaceData = new Vec4(69,88,420,-100);
        this.sourceData.add(new Face(
                lastFaceData,
                lastFaceData,
                lastFaceData,
                lastFaceData,
                lastFaceData,
                lastFaceData));
        while (!this.sourceData.isEmpty()) {
            successor.push(this.sourceData.poll());
        }
    }

    @Override
    public void push(Face element) {
        throw new IllegalCallerException("Cannot call directly push() on a PushSource");
    }
}
