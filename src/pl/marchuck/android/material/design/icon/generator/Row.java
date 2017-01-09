package pl.marchuck.android.material.design.icon.generator;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

/**
 * Created by lukasz on 09.01.17.
 */
public class Row extends HBox {


    public void add(Node node) {
        getChildren().add(node);
    }

    public void addAll(Node... nodes) {
        getChildren().addAll(nodes);
    }
}
