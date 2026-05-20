module vn.hcmute.edu.nhom.builderpatterndrinkdemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens vn.hcmute.edu.nhom10.builderpatterndrinkdemo to javafx.fxml;
    exports vn.hcmute.edu.nhom10.builderpatterndrinkdemo;
}