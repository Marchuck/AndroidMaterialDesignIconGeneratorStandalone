package pl.marchuck.android.material.design.icon.generator;

import io.reactivex.Observable;
import javafx.scene.control.TextField;

/**
 * Created by lukasz on 09.01.17.
 */
public class RxCommons {


    public static Observable<String> sequences(TextField field) {
        return Observable.create(observableEmitter ->
                field.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!oldValue.contentEquals(newValue))
                        observableEmitter.onNext(newValue);
                }));
    }
}
