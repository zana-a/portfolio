package view;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Module;

public class ReserveModulePane extends VBox {

	private ListView<Module> unselectedTerm1Modules;
	private ListView<Module> reservedTerm1Modules;
	private ListView<Module> unselectedTerm2Modules;
	private ListView<Module> reservedTerm2Modules;
	private Button term1AddButton;
	private Button term1RemoveButton;
	private Button term1ConfirmButton;
	private Button term2AddButton;
	private Button term2RemoveButton;
	private Button term2ConfirmButton;

	private Text error;

	TitledPane term2Modules;
	TitledPane term1Modules;

	public ReserveModulePane() {
		error = new Text();
		error.setStyle("-fx-fill: red;");

		HBox errorscene = new HBox();
		errorscene.getChildren().add(error);
		errorscene.setPadding(new Insets(10, 0, 0, 0));

		VBox unselectedTerm1ModulesContainer = new VBox();
		unselectedTerm1Modules = new ListView<>();
		unselectedTerm1ModulesContainer.getChildren().addAll(new Label("Unselected Term 1 Modules"),
				unselectedTerm1Modules);

		VBox reservedTerm1ModulesContainer = new VBox();
		reservedTerm1Modules = new ListView<>();
		reservedTerm1ModulesContainer.getChildren().addAll(new Label("Reserved Term 1 Modules"), reservedTerm1Modules);

		HBox term1ModulesContainerTop = new HBox(10);
		term1ModulesContainerTop.getChildren().addAll(unselectedTerm1ModulesContainer, reservedTerm1ModulesContainer);

		HBox term1ModulesButtons = new HBox(10);
		term1AddButton = new Button("Add");
		term1RemoveButton = new Button("Remove");
		term1ConfirmButton = new Button("Confirm");
		term1ModulesButtons.getChildren().addAll(new Label("Reserve 30 Credits Worth of Term 1 Modules"),
				term1AddButton, term1RemoveButton, term1ConfirmButton);
		term1ModulesButtons.setAlignment(Pos.BASELINE_CENTER);

		VBox term1ModulesContainer = new VBox(10);
		term1ModulesContainer.getChildren().addAll(term1ModulesContainerTop, term1ModulesButtons);

		VBox unselectedTerm2ModulesContainer = new VBox();
		unselectedTerm2Modules = new ListView<>();
		unselectedTerm2ModulesContainer.getChildren().addAll(new Label("Unselected Term 2 Modules"),
				unselectedTerm2Modules);

		VBox reservedTerm2ModulesContainer = new VBox();
		reservedTerm2Modules = new ListView<>();
		reservedTerm2ModulesContainer.getChildren().addAll(new Label("Reserved Term 2 Modules"), reservedTerm2Modules);

		HBox term2ModulesContainerTop = new HBox(10);
		term2ModulesContainerTop.getChildren().addAll(unselectedTerm2ModulesContainer, reservedTerm2ModulesContainer);
		HBox.setHgrow(unselectedTerm2ModulesContainer, Priority.ALWAYS);
		HBox.setHgrow(reservedTerm1ModulesContainer, Priority.ALWAYS);

		HBox term2ModulesButtons = new HBox(10);
		term2AddButton = new Button("Add");
		term2RemoveButton = new Button("Remove");
		term2ConfirmButton = new Button("Confirm");
		term2ModulesButtons.getChildren().addAll(new Label("Reserve 30 Credits Worth of Term 2 Modules"),
				term2AddButton, term2RemoveButton, term2ConfirmButton);
		term2ModulesButtons.setAlignment(Pos.BASELINE_CENTER);

		VBox term2ModulesContainer = new VBox(10);
		term2ModulesContainer.getChildren().addAll(term2ModulesContainerTop, term2ModulesButtons);

		term1Modules = new TitledPane();
		term1Modules.setText("Term 1 Modules");
		term1Modules.setContent(term1ModulesContainer);

		term2Modules = new TitledPane();
		term2Modules.setText("Term 2 Modules");
		term2Modules.setContent(term2ModulesContainer);

		Accordion accordion = new Accordion();
		accordion.getPanes().setAll(term1Modules, term2Modules);
		accordion.setExpandedPane(term1Modules);

		VBox.setVgrow(unselectedTerm1Modules, Priority.ALWAYS);
		VBox.setVgrow(reservedTerm1Modules, Priority.ALWAYS);
		VBox.setVgrow(term1ModulesContainerTop, Priority.ALWAYS);
		VBox.setVgrow(unselectedTerm2Modules, Priority.ALWAYS);
		VBox.setVgrow(reservedTerm2Modules, Priority.ALWAYS);
		VBox.setVgrow(term2ModulesContainerTop, Priority.ALWAYS);
		VBox.setVgrow(accordion, Priority.ALWAYS);
		HBox.setHgrow(unselectedTerm1ModulesContainer, Priority.ALWAYS);
		HBox.setHgrow(reservedTerm1ModulesContainer, Priority.ALWAYS);
		HBox.setHgrow(unselectedTerm2ModulesContainer, Priority.ALWAYS);
		HBox.setHgrow(reservedTerm2ModulesContainer, Priority.ALWAYS);

		this.getChildren().addAll(accordion, errorscene);
		this.setPadding(new Insets(10));
	}

	public void setUnselectedTerm1Modules(ArrayList<Module> modules) {
		this.unselectedTerm1Modules.getItems().addAll(modules);
	}

	public void setUnselectedTerm2Modules(ArrayList<Module> modules) {
		this.unselectedTerm2Modules.getItems().addAll(modules);
	}

	public void addToReservedTerm1Module(Module module) {
		this.reservedTerm1Modules.getItems().add(module);
	}

	public void addToReservedTerm2Module(Module module) {
		this.reservedTerm2Modules.getItems().add(module);
	}

	public void addTerm1ModuleHandler(EventHandler<ActionEvent> handler) {
		this.term1AddButton.setOnAction(handler);
	}

	public Module getSelectedUnselectedTerm1Module() {
		return this.unselectedTerm1Modules.getSelectionModel().getSelectedItem();
	}

	public Module getSelectedUnselectedTerm2Module() {
		return this.unselectedTerm2Modules.getSelectionModel().getSelectedItem();
	}

	public void removeSelectedUnselectedTerm1Module() {
		this.unselectedTerm1Modules.getItems().remove(this.getSelectedUnselectedTerm1Module());
		this.unselectedTerm1Modules.getSelectionModel().clearSelection();
	}

	public void removeSelectedUnselectedTerm2Module() {
		this.unselectedTerm2Modules.getItems().remove(this.getSelectedUnselectedTerm2Module());
		this.unselectedTerm2Modules.getSelectionModel().clearSelection();
	}

	public Module getSelectedReservedTerm1Module() {
		return this.reservedTerm1Modules.getSelectionModel().getSelectedItem();
	}

	public Module getSelectedReservedTerm2Module() {
		return this.reservedTerm2Modules.getSelectionModel().getSelectedItem();
	}

	public void removeSelectedReservedTerm1Module() {
		this.reservedTerm1Modules.getItems().remove(this.getSelectedReservedTerm1Module());
		this.reservedTerm1Modules.getSelectionModel().clearSelection();
	}

	public void removeSelectedReservedTerm2Module() {
		this.reservedTerm2Modules.getItems().remove(this.getSelectedReservedTerm2Module());
		this.reservedTerm2Modules.getSelectionModel().clearSelection();
	}

	public void addToUnselectedTerm1Module(Module m) {
		this.unselectedTerm1Modules.getItems().add(m);
	}

	public void addToUnselectedTerm2Module(Module m) {
		this.unselectedTerm2Modules.getItems().add(m);
	}

	public void removeTerm1ModuleHandler(EventHandler<ActionEvent> handler) {
		this.term1RemoveButton.setOnAction(handler);
	}

	public void removeTerm2ModuleHandler(EventHandler<ActionEvent> handler) {
		this.term2RemoveButton.setOnAction(handler);
	}

	public void addTerm2ModuleHandler(EventHandler<ActionEvent> event) {
		this.term2AddButton.setOnAction(event);
	}

	public void confirmTerm1Handler(EventHandler<ActionEvent> event) {
		this.term1ConfirmButton.setOnAction(event);
	}

	public void confirmTerm2Handler(EventHandler<ActionEvent> event) {
		this.term2ConfirmButton.setOnAction(event);
	}

	public void expandTerm2Pane() {
		this.term2Modules.setExpanded(true);
	}

	public void expandTerm1Pane() {
		this.term1Modules.setExpanded(true);
	}

	public boolean term1PaneIsEmpty() {
		return this.reservedTerm1Modules.getItems().isEmpty();
	}

	public boolean term2PaneIsEmpty() {
		return this.reservedTerm2Modules.getItems().isEmpty();
	}

	public ArrayList<Module> getReservedTerm1Modules() {
		ArrayList<Module> items = new ArrayList<>();
		for (Object m : this.reservedTerm1Modules.getItems().toArray())
			items.add((Module) m);

		return items;
	}

	public ArrayList<Module> getReservedTerm2Modules() {
		ArrayList<Module> items = new ArrayList<>();
		for (Object m : this.reservedTerm2Modules.getItems().toArray())
			items.add((Module) m);

		return items;
	}

	public void cleanView() {
		this.reservedTerm1Modules.getItems().clear();
		this.reservedTerm2Modules.getItems().clear();
		this.unselectedTerm1Modules.getItems().clear();
		this.unselectedTerm2Modules.getItems().clear();
	}

	public void setError(String error) {
		if (error.endsWith("\n")) {
			StringBuilder newError = new StringBuilder(error);
			newError.deleteCharAt(error.length() - 1);
			this.error.setText(newError.toString());
		} else {
			this.error.setText(error);
		}
	}
}
