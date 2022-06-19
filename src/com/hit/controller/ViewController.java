package com.hit.controller;

import com.hit.model.Model;
import com.hit.view.View;

import java.util.Observable;

public class ViewController implements Controller {
    private Model model;
    private View view;

    public ViewController(Model model, View view) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void update(Observable o, Object arg) {
//        if (arg instanceof Events){
//            (UserView)view.
//            ((MyModel)model).userGetSongs((String)arg);
//        }
//        else if (o)
    }
}
