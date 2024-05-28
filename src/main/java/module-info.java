module app.tastewizard {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires javafx.media;

    opens app.tastewizard to javafx.fxml;
    exports app.tastewizard;
    opens app.AppIntro to javafx.fxml;
    exports app.AppIntro;
    opens app.Register to javafx.fxml;
    exports app.Register;
    opens app.HomePage to javafx.fxml;
    exports app.HomePage;
}