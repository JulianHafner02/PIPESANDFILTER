package at.fhv.sysarch.lab3.pipeline.filters;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.obj.Model;
import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Vec4;

public class SourceSingle implements IFilter<Model, Face> {

    private IFilter<Face, ?> successor;

    private Mat4 viewTransMat;


    public void setTransMatrix(Mat4 transMatrix) {
        this.viewTransMat = transMatrix;
    }

    public void setSuccessor(IFilter<Face, ?> r) {
        this.successor = r;
    }

    public void write(Model model) {

        model.getFaces().forEach(face -> {
            Vec4 v1new = viewTransMat.multiply(face.getV1());
            Vec4 v2new = viewTransMat.multiply(face.getV2());
            Vec4 v3new = viewTransMat.multiply(face.getV3());

            Vec4 v1NormalNew = viewTransMat.multiply(face.getN1());
            Vec4 v2NormalNew = viewTransMat.multiply(face.getN2());
            Vec4 v3NormalNew = viewTransMat.multiply(face.getN3());

            Face transFace = new Face(v1new, v2new, v3new, v1NormalNew, v2NormalNew, v3NormalNew);



            successor.write(transFace);
        });

        //model.getFaces().forEach(face -> successor.write(face));
    }
}
