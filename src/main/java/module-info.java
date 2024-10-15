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
    requires java.desktop;
    requires com.jfoenix;
    requires java.sql;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    
    exports com.uem.sgnfx.Controllers.Admin to javafx.graphics, javafx.fxml;
    exports com.uem.sgnfx.Controllers.Estudante to javafx.graphics, javafx.fxml;
    exports com.uem.sgnfx.Controllers.Professor to javafx.graphics, javafx.fxml;

    opens com.uem.sgnfx to javafx.fxml;
    opens com.uem.sgnfx.Controllers.Admin to javafx.fxml;
    opens com.uem.sgnfx.Controllers.Estudante to javafx.fxml;
    opens com.uem.sgnfx.Controllers.Professor to javafx.fxml;
    opens com.uem.sgnfx.Models to org.hibernate.orm.core, javafx.base;

    exports com.uem.sgnfx;
}