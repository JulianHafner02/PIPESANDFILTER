package at.fhv.sysarch.lab3.pipeline;

import at.fhv.sysarch.lab3.animation.AnimationRenderer;
import at.fhv.sysarch.lab3.obj.Model;
import at.fhv.sysarch.lab3.pipeline.filters.Renderer;
import at.fhv.sysarch.lab3.pipeline.filters.ResizeFilter;
import at.fhv.sysarch.lab3.pipeline.filters.Source;
import at.fhv.sysarch.lab3.pipeline.filters.SourceSingle;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;


public class PushPipelineFactory {
    public static AnimationTimer createPipeline(PipelineData pd) {
        SourceSingle source = new SourceSingle();
        ResizeFilter filter = new ResizeFilter();
        Renderer r = new Renderer(pd.getGraphicsContext(), pd.getRenderingMode(),pd.getModelColor());

        filter.setSuccessor(r);
        source.setSuccessor(filter);
        // TODO: push from the source (model)

        // TODO 1. perform model-view transformation from model to VIEW SPACE coordinates

        // TODO 2. perform backface culling in VIEW SPACE

        // TODO 3. perform depth sorting in VIEW SPACE

        // TODO 4. add coloring (space unimportant)

        // lighting can be switched on/off
        if (pd.isPerformLighting()) {
            // 4a. TODO perform lighting in VIEW SPACE

            // 5. TODO perform projection transformation on VIEW SPACE coordinates
        } else {
            // 5. TODO perform projection transformation
        }

        // TODO 6. perform perspective division to screen coordinates

        // TODO 7. feed into the sink (renderer)

        // returning an animation renderer which handles clearing of the
        // viewport and computation of the praction
        return new AnimationRenderer(pd) {
            // TODO rotation variable goes in here
            // int pos = (int)Math.random() * 350;
            private int pos = (int)(Math.random()*350);
            //private int pos = 0;

            /** This method is called for every frame from the JavaFX Animation
             * system (using an AnimationTimer, see AnimationRenderer).
             * @param fraction the time which has passed since the last render call in a fraction of a second
             * @param model    the model to render
             */
            @Override
            protected void render(float fraction, Model model) {
                pd.getGraphicsContext().setStroke(Color.RED);
                pd.getGraphicsContext().strokeLine(pos,pos,pos+100,pos+100);
                pos++;

                /*model.getFaces().forEach(face -> {
                    pd.getGraphicsContext().strokeLine(face.getV1().getX()*100, face.getV1().getY()*100, face.getV2().getX()*100, face.getV2().getY()*100);
                    pd.getGraphicsContext().strokeLine(face.getV2().getX()*100, face.getV2().getY()*100, face.getV3().getX()*100, face.getV3().getY()*100);
                    pd.getGraphicsContext().strokeLine(face.getV1().getX()*100, face.getV1().getY()*100, face.getV3().getX()*100, face.getV3().getY()*100);
                });*/

                source.write(model);
                // TODO compute rotation in radians

                // TODO create new model rotation matrix using pd.modelRotAxis

                // TODO compute updated model-view tranformation

                // TODO update model-view filter

                // TODO trigger rendering of the pipeline

            }
        };
    }
}