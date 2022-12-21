module org.openjfx {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	
	opens desktopUI to javafx.graphics, javafx.fxml;
}