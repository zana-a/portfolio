package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;

public class ModuleSelectionRootPane extends BorderPane {

	private CreateProfilePane cpp;
	private SelectModulePane smp;
	private ReserveModulePane rmp;
	private OverviewSelectionPane osp;
	private ModuleSelectionMenuBar msmb;
	private TabPane tp;

	public ModuleSelectionRootPane() {
		// create tab pane and disable tabs from being closed
		tp = new TabPane();
		tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		// create panes
		cpp = new CreateProfilePane();
		smp = new SelectModulePane();
		rmp = new ReserveModulePane();
		osp = new OverviewSelectionPane();

		// create tabs with panes added
		Tab t1 = new Tab("Create Profile", cpp);
		Tab t2 = new Tab("Select Module", smp);
		Tab t3 = new Tab("Reserve Module", rmp);
		Tab t4 = new Tab("Overview Selection", osp);

		// add tabs to tab pane
		tp.getTabs().addAll(t1, t2, t3, t4);

		// create menu bar
		msmb = new ModuleSelectionMenuBar();

		// add menu bar and tab pane to this root pane
		this.setTop(msmb);
		this.setCenter(tp);

	}

	// methods allowing sub-containers to be accessed by the controller.
	public CreateProfilePane getCreateProfilePane() {
		return cpp;
	}

	public ModuleSelectionMenuBar getModuleSelectionMenuBar() {
		return msmb;
	}

	public SelectModulePane getSelectModulePane() {
		return smp;
	}

	public ReserveModulePane getReserveModulePane() {
		return rmp;
	}

	public OverviewSelectionPane getOverviewSelectionPane() {
		return osp;
	}

	public ModuleSelectionRootPane getModuleSelectionRootPane() {
		return this;
	}

	// method to allow the controller to change tabs
	public void changeTab(int index) {
		tp.getSelectionModel().select(index);
	}
}
