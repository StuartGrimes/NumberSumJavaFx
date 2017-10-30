package com.groundanchor;

import static javafx.application.Application.launch;

public class App {

    private App(){
        System.out.println("App started...");
    }
    public static void main(String[] args) {
        //create an instance of this object although we will not use its reference
        App App;
        App = new App();
        //launch can be called from any class even if it does not extend Application
        launch(NumberSum.class);
    }
}
