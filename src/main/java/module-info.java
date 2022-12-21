module com.example.profinal {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                        
    opens com.example.profinal to javafx.fxml;
    exports com.example.profinal;
}