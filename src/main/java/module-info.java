module no.ntnu.mappe3.marko.zipCodeRegister {
    requires javafx.controls;
    requires java.logging;

    opens no.ntnu.mappe3.marko.zipCodeRegister.model to javafx.base;

    exports no.ntnu.mappe3.marko.zipCodeRegister.view;
}