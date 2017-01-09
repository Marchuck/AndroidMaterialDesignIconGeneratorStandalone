package pl.marchuck.android.material.design.icon.generator;

import com.sun.istack.internal.Nullable;

import java.io.File;

/**
 * Created by lukasz on 09.01.17.
 */
public class AppPresenter {

    final AppCallbacks appCallbacks;

    @Nullable
    IconModel model;

    public AppPresenter(AppCallbacks appCallbacks) {
        this.appCallbacks = appCallbacks;
    }


    public void stop() {

    }

    public void onFileSelected(File image) {
        if (image != null) {
            String filePath = image.getAbsolutePath();
//            model = new IconModel("icon.png","#000000","#000000",);
        }
    }
}
