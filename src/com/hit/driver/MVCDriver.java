package com.hit.driver;
import chrriis.dj.nativeswing.NativeSwing;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import com.hit.controller.Controller;
import com.hit.controller.ViewController;
import com.hit.model.Model;
import com.hit.model.Songs;
import com.hit.view.GraphicalView;
import com.hit.view.View;

public class MVCDriver {
    public MVCDriver() {
    }

    public static void main(String [] args)
    {
        Model model = new Songs();
        View view = new GraphicalView();
        Controller controller = new ViewController(model, view);
        ((Songs)model).addObserver(controller);
//        ((GraphicalView)view).addObserver(controller);
        view.start();

    }

}