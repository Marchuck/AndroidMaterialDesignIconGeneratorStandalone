package pl.marchuck.android.material.design.icon.generator;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * Created by lukasz on 09.01.17.
 */
public class Column extends VBox {

    public void add(Node node) {
        getChildren().add(node);
    }

    public void addAll(Node... node) {
        getChildren().addAll(node);
    }
}
