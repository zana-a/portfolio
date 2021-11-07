package view;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Module;

public class SelectModulePane extends HBox {

	private Button resetButton;
	private ListView<Module> selectedTerm1Modules;
	private ListView<Module> selectedTerm2Modules;
	private ListView<Module> selectedYearLongModules;
	private Button submitButton;
	private Button term1AddButton;
	private TextField term1CreditField;
	private Button term1RemoveButton;
	private Button term2AddButton;
	private TextField term2CreditField;
	private Button term2RemoveButton;
	private ListView<Module> unselectedTerm1Modules;
	private ListView<Module> unselectedTerm2Modules;

	private Text error;

	public SelectModulePane() {
		error = new Text("");
		error.setStyle("-fx-fill: red;");

		HBox errorscene = new HBox();
		errorscene.getChildren().add(error);

		Label term1ButtonContainerLabel = new Label("Term 1");
		term1AddButton = new Button("Add");
		term1RemoveButton = new Button("Remove");

		HBox term1ButtonsContainer = new HBox(10);
		term1ButtonsContainer.getChildren().addAll(term1ButtonContainerLabel, term1AddButton, term1RemoveButton);
		term1ButtonsContainer.setAlignment(Pos.BASELINE_CENTER);

		Label term2ButtonContainerLabel = new Label("Term 2");
		term2AddButton = new Button("Add");
		term2RemoveButton = new Button("Remove");

		HBox term2ButtonsContainer = new HBox(10);
		term2ButtonsContainer.getChildren().addAll(term2ButtonContainerLabel, term2AddButton, term2RemoveButton);
		term2ButtonsContainer.setAlignment(Pos.BASELINE_CENTER);

		Label unselectedTerm1ModulesLabel = new Label("Unselected Term 1 Modules");
		unselectedTerm1Modules = new ListView<>();
		VBox unselectedTerm1ModulesGroup = new VBox();
		unselectedTerm1ModulesGroup.getChildren().addAll(unselectedTerm1ModulesLabel, unselectedTerm1Modules);

		Label unselectedTerm2ModulesLabel = new Label("Unselected Term 2 Modules");
		unselectedTerm2Modules = new ListView<>();
		VBox unselectedTerm2ModulesGroup = new VBox();
		unselectedTerm2ModulesGroup.getChildren().addAll(unselectedTerm2ModulesLabel, unselectedTerm2Modules);

		Label selectedYearLongModulesLabel = new Label("Selected Year Long Modules");
		selectedYearLongModules = new ListView<>();
		VBox selectedYearLongModulesGroup = new VBox();
		selectedYearLongModulesGroup.getChildren().addAll(selectedYearLongModulesLabel, selectedYearLongModules);

		Label selectedTerm1ModulesLabel = new Label("Selected Term 1 Modules");
		selectedTerm1Modules = new ListView<>();
		VBox selectedTerm1ModulesGroup = new VBox();
		selectedTerm1ModulesGroup.getChildren().addAll(selectedTerm1ModulesLabel, selectedTerm1Modules);

		Label selectedTerm2ModulesLabel = new Label("Selected Term 2 Modules");
		selectedTerm2Modules = new ListView<>();
		VBox selectedTerm2ModulesGroup = new VBox();
		selectedTerm2ModulesGroup.getChildren().addAll(selectedTerm2ModulesLabel, selectedTerm2Modules);

		HBox term1CreditsContainer = new HBox(10);
		term1CreditField = new TextField("0");
		term1CreditField.setEditable(false);
		term1CreditField.setPrefColumnCount(3);
		term1CreditsContainer.getChildren().addAll(new Label("Current Term 1 Credit:"), term1CreditField);
		term1CreditsContainer.setAlignment(Pos.BASELINE_CENTER);

		HBox term2CreditsContainer = new HBox(10);
		term2CreditField = new TextField("0");
		term2CreditField.setEditable(false);
		term2CreditField.setPrefColumnCount(3);
		term2CreditsContainer.getChildren().addAll(new Label("Current Term 2 Credit:"), term2CreditField);
		term2CreditsContainer.setAlignment(Pos.BASELINE_CENTER);

		resetButton = new Button("Reset");
		submitButton = new Button("Submit");

		VBox left = new VBox(10);
		left.getChildren().addAll(unselectedTerm1ModulesGroup, term1ButtonsContainer, unselectedTerm2ModulesGroup,
				term2ButtonsContainer, term1CreditsContainer);

		VBox right = new VBox(10);
		right.getChildren().addAll(selectedYearLongModulesGroup, selectedTerm1ModulesGroup, selectedTerm2ModulesGroup,
				term2CreditsContainer);

		HBox bottomscene = new HBox(10);
		bottomscene.getChildren().addAll(resetButton, submitButton);
		bottomscene.setAlignment(Pos.BASELINE_CENTER);

		HBox mainscene = new HBox(10);
		mainscene.getChildren().addAll(left, right);

		VBox container = new VBox(10);
		container.getChildren().addAll(mainscene, bottomscene, errorscene);

		HBox.setHgrow(container, Priority.ALWAYS);
		HBox.setHgrow(right, Priority.ALWAYS);
		HBox.setHgrow(left, Priority.ALWAYS);
		VBox.setVgrow(container, Priority.ALWAYS);

		this.getChildren().add(container);
		this.setPrefHeight(200);
		this.setPadding(new Insets(10));
	}

	public void addTerm1ModuleHandler(EventHandler<ActionEvent> handler) {
		this.term1AddButton.setOnAction(handler);
	}

	public void addTerm2ModuleHandler(EventHandler<ActionEvent> handler) {
		this.term2AddButton.setOnAction(handler);
	}

	public Module getSelectedSelectedTerm1Module() {
		return this.selectedTerm1Modules.getSelectionModel().getSelectedItem();
	}

	public Module getSelectedSelectedTerm2Module() {
		return this.selectedTerm2Modules.getSelectionModel().getSelectedItem();
	}

	public ArrayList<Module> getSelectedTerm1Modules() {
		ArrayList<Module> items = new ArrayList<>();
		for (Object m : this.selectedTerm1Modules.getItems().toArray())
			items.add((Module) m);

		return items;
	}

	public ArrayList<Module> getSelectedTerm2Modules() {
		ArrayList<Module> items = new ArrayList<>();
		for (Object m : this.selectedTerm2Modules.getItems().toArray())
			items.add((Module) m);

		return items;
	}

	public Module getSelectedUnselectedTerm1Module() {
		return this.unselectedTerm1Modules.getSelectionModel().getSelectedItem();
	}

	public Module getSelectedUnselectedTerm2Module() {
		return this.unselectedTerm2Modules.getSelectionModel().getSelectedItem();
	}

	public void removeSelectedSelectedTerm1Module() {
		this.selectedTerm1Modules.getItems().remove(this.getSelectedSelectedTerm1Module());
		this.selectedTerm1Modules.getSelectionModel().clearSelection();
	}

	public void removeSelectedSelectedTerm2Module() {
		this.selectedTerm2Modules.getItems().remove(this.getSelectedSelectedTerm2Module());
		this.selectedTerm2Modules.getSelectionModel().clearSelection();
	}

	public void cleanView() {
		this.unselectedTerm1Modules.getItems().clear();
		this.unselectedTerm2Modules.getItems().clear();
		this.selectedTerm1Modules.getItems().clear();
		this.selectedTerm2Modules.getItems().clear();
		this.selectedYearLongModules.getItems().clear();
		this.term1CreditField.setText(String.valueOf(0));
		this.term2CreditField.setText(String.valueOf(0));
	}

	public void removeSelectedUnselectedTerm1Module() {
		this.unselectedTerm1Modules.getItems().remove(this.getSelectedUnselectedTerm1Module());
		this.unselectedTerm1Modules.getSelectionModel().clearSelection();
	}

	public void removeSelectedUnselectedTerm2Module() {
		this.unselectedTerm2Modules.getItems().remove(this.getSelectedUnselectedTerm2Module());
		this.unselectedTerm2Modules.getSelectionModel().clearSelection();
	}

	public void removeTerm1ModuleHandler(EventHandler<ActionEvent> handler) {
		this.term1RemoveButton.setOnAction(handler);
	}

	public void removeTerm2ModuleHandler(EventHandler<ActionEvent> handler) {
		this.term2RemoveButton.setOnAction(handler);
	}

	public void resetEventHandler(EventHandler<ActionEvent> handler) {
		this.resetButton.setOnAction(handler);
	}

	public void setSelectedTerm1Module(Module module) {
		this.selectedTerm1Modules.getItems().add(module);
	}

	public void setSelectedTerm2Module(Module module) {
		this.selectedTerm2Modules.getItems().add(module);
	}

	public void setSelectedTerm2Modules(ArrayList<Module> selectedTerm2Modules) {
		this.selectedTerm2Modules.getItems().addAll(selectedTerm2Modules);
	}

	public void setSelectedTerm1Modules(ArrayList<Module> selectedTerm1Modules) {
		this.selectedTerm1Modules.getItems().addAll(selectedTerm1Modules);
	}

	public void setSelectedYearLongModules(ArrayList<Module> yearLongModules) {
		this.selectedYearLongModules.getItems().addAll(yearLongModules);
	}

	public void setUnselectedTerm1Module(Module m) {
		this.unselectedTerm1Modules.getItems().add(m);
	}

	public void setUnselectedTerm1Modules(ArrayList<Module> term1Modules) {
		this.unselectedTerm1Modules.getItems().addAll(term1Modules);
	}

	public void setUnselectedTerm2Module(Module m) {
		this.unselectedTerm2Modules.getItems().add(m);
	}

	public void setUnselectedTerm2Modules(ArrayList<Module> unselectedTerm2Modules) {
		this.unselectedTerm2Modules.getItems().addAll(unselectedTerm2Modules);
	}

	public int getTerm1CreditScore() {
		return Integer.parseInt(this.term1CreditField.getText());
	}

	public int getTerm2CreditScore() {
		return Integer.parseInt(this.term1CreditField.getText());
	}

	public void setTerm1CreditScore(int n) {
		this.term1CreditField.setText(String.valueOf(n));
	}

	public boolean term1SelectedModulesIsEmpty() {
		return this.selectedTerm1Modules.getSelectionModel().isEmpty();
	}

	public void setTerm2CreditScore(int n) {
		this.term2CreditField.setText(String.valueOf(n));
	}

	public boolean term2SelectedModulesIsEmpty() {
		return this.selectedTerm2Modules.getSelectionModel().isEmpty();
	}

	public void submitEventHandler(EventHandler<ActionEvent> handler) {
		this.submitButton.setOnAction(handler);
	}

	public ArrayList<Module> getUnselectedTerm1Modules() {
		ArrayList<Module> items = new ArrayList<>();
		for (Object m : this.unselectedTerm1Modules.getItems().toArray())
			items.add((Module) m);

		return items;
	}

	public ArrayList<Module> getUnselectedTerm2Modules() {
		ArrayList<Module> items = new ArrayList<>();
		for (Object m : this.unselectedTerm2Modules.getItems().toArray())
			items.add((Module) m);

		return items;
	}

	public ArrayList<Module> getYearLongModules() {
		ArrayList<Module> items = new ArrayList<>();
		for (Object m : this.selectedYearLongModules.getItems().toArray())
			items.add((Module) m);

		return items;
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