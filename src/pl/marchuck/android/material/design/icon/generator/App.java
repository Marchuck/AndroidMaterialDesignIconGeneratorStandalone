package pl.marchuck.android.material.design.icon.generator;/**
 * Created by lukasz on 09.01.17.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class App extends Application implements AppCallbacks {

    public static void main(String[] args) {
        launch(args);
    }

    AppPresenter presenter;

    Label filePreview;

    @Override
    public void start(Stage primaryStage) {

        presenter = new AppPresenter(this);

        Column rootView = new Column();

        /**
         * FIRST ROW
         */
        Row firstRow = new Row();

        Button inputFileButton = new Button("Select image");
        inputFileButton.setOnAction((c) -> {
            FileChooser chooser = new FileChooser();

            File image = chooser.showOpenDialog(primaryStage);
            presenter.onFileSelected(image);
        });

        firstRow.addAll(inputFileButton);
        /**
         * second row
         */
        Row second = new Row();
        TextField nameField = new TextField("filename.png");

        second.add(nameField);
        filePreview = new Label();
        second.add(filePreview);


        rootView.addAll(firstRow);

        /**
         * SECOND ROW
         */
        Row row2 = new Row();
        Label outputIconName = new Label("icon name");
        TextField iconNameTextField = new TextField("filename.png");

        row2.addAll(outputIconName, iconNameTextField);

        primaryStage.setScene(new Scene(rootView, 300, 250));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
        System.out.println("init");

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        presenter.stop();
        presenter = null;
        System.out.println("stop");

    }

    @Override
    public void loadIcon(WritableImage icon) {
        System.out.println("loadIcon");

        filePreview.setGraphic(new ImageView(icon));
    }
}
