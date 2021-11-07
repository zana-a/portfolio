package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class OverviewSelectionPane extends VBox {

	private TextArea txtAreaDetails;
	private TextArea txtAreaSelectedModules;
	private TextArea txtAreaReservedModules;
	private Button btnSaveOverview;

	public OverviewSelectionPane() {

		txtAreaDetails = new TextArea();
		txtAreaDetails.setEditable(false);

		HBox container1 = new HBox();
		container1.getChildren().add(txtAreaDetails);
		container1.setPadding(new Insets(0, 0, 10, 0));

		txtAreaSelectedModules = new TextArea();
		txtAreaSelectedModules.setEditable(false);

		txtAreaReservedModules = new TextArea();
		txtAreaReservedModules.setEditable(false);

		HBox container2 = new HBox(10);
		container2.getChildren().addAll(txtAreaSelectedModules, txtAreaReservedModules);

		btnSaveOverview = new Button("Save Overview");

		HBox container3 = new HBox();
		container3.getChildren().add(btnSaveOverview);
		container3.setAlignment(Pos.BASELINE_CENTER);
		container3.setPadding(new Insets(10, 0, 0, 0));

		HBox.setHgrow(txtAreaDetails, Priority.ALWAYS);
		HBox.setHgrow(txtAreaSelectedModules, Priority.ALWAYS);
		HBox.setHgrow(txtAreaReservedModules, Priority.ALWAYS);

		VBox.setVgrow(container2, Priority.ALWAYS);
		VBox.setVgrow(container1, Priority.ALWAYS);

		this.getChildren().addAll(container1, container2, container3);
		this.setPadding(new Insets(10));
	}

	public void setSelectedModulesTextArea(String data) {
		this.txtAreaSelectedModules.setText(data);
	}

	public void setReservedModulesTextArea(String data) {
		this.txtAreaReservedModules.setText(data);
	}

	public void setDetailsTextArea(String data) {
		this.txtAreaDetails.setText(data);
	}

	public void cleanView() {
		this.txtAreaDetails.clear();
		this.txtAreaReservedModules.clear();
		this.txtAreaSelectedModules.clear();
	}
}
