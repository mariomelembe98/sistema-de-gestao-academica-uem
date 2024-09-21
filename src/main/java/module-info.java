module com.uem.sgnfx {
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

    exports com.uem.sgnfx.Controllers.Admin to javafx.graphics, javafx.fxml;
    exports com.uem.sgnfx.Controllers.Estudante to javafx.graphics, javafx.fxml;
    exports com.uem.sgnfx.Controllers.Professor to javafx.graphics, javafx.fxml;

    opens com.uem.sgnfx to javafx.fxml;
    exports com.uem.sgnfx;
}