module com.example.data_collection {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.data_collection to javafx.fxml;
    exports com.example.data_collection;
}