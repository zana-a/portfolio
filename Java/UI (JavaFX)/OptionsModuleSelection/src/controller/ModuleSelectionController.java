package controller;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Course;
import model.Delivery;
import model.Module;
import model.StudentProfile;
import view.CreateProfilePane;
import view.ModuleSelectionMenuBar;
import view.ModuleSelectionRootPane;
import view.OverviewSelectionPane;
import view.ReserveModulePane;
import view.SelectModulePane;

public class ModuleSelectionController {

	private CreateProfilePane cpp;
	private ModuleSelectionMenuBar msmb;
	private OverviewSelectionPane osp;
	private ReserveModulePane rmp;
	private SelectModulePane smp;
	private ModuleSelectionRootPane view;

	private ArrayList<Module> term1MandatoryModules;
	private ArrayList<Module> term2MandatoryModules;

	private int term1Credit;
	private int term2Credit;

	private final static int MAX_TOTAL_CREDIT_TERM_1 = 60;
	private final static int MAX_TOTAL_CREDIT_TERM_2 = 60;

	private int RMPterm1Credit;
	private int RMPterm2Credit;

	// setupandsetmodules
	private StudentProfile model;
	private ArrayList<Module> term1Modules;
	private ArrayList<Module> term2Modules;
	private ArrayList<Module> yearLongModules;

	private String smpError = "";
	private String cppError = "";
	private String rmpError = "";

	public ModuleSelectionController(StudentProfile model, ModuleSelectionRootPane view) {
		RMPterm2Credit = 0;
		RMPterm1Credit = 0;

		// initialise model and view fields
		this.model = model;
		this.view = view;

		// initialise view subcontainer fields
		cpp = view.getCreateProfilePane();
		msmb = view.getModuleSelectionMenuBar();
		osp = view.getOverviewSelectionPane();
		rmp = view.getReserveModulePane();
		smp = view.getSelectModulePane();

		// populate combobox in create profile pane with courses using the
		// setupAndGetCourses method below
		cpp.populateCourseComboBox(setupAndGetCourses());

		// attach event handlers to view using private helper method
		this.attachEventHandlers();
	}

	// a helper method used to attach event handlers
	private void attachEventHandlers() {
		// attach an event handler to the create profile pane
		cpp.addCreateProfileHandler(new CreateProfileHandler());

		// attach event handler to reset pane
		smp.resetEventHandler(new ResetHandler());
		smp.addTerm1ModuleHandler(new AddTerm1ButtonHandler());
		smp.removeTerm1ModuleHandler(new RemoveTerm1ButtonHandler());
		smp.addTerm2ModuleHandler(new AddTerm2ButtonHandler());
		smp.removeTerm2ModuleHandler(new RemoveTerm2ButtonHandler());
		smp.submitEventHandler(new SubmitHandler());

		//
		rmp.addTerm1ModuleHandler(new RMPAddTerm1ButtonHandler());
		rmp.removeTerm1ModuleHandler(new RMPRemoveTerm1ButtonHandler());
		rmp.addTerm2ModuleHandler(new RMPAddTerm2ButtonHandler());
		rmp.removeTerm2ModuleHandler(new RMPRemoveTerm2ButtonHandler());
		rmp.confirmTerm1Handler(new RMPConfirmTerm1ButtonHandler());
		rmp.confirmTerm2Handler(new RMPConfirmTerm2ButtonHandler());

		// attach an event handler to the menu bar that closes the application
		msmb.addExitHandler(e -> System.exit(0));
	}

	// empty event handler, which can be used for creating a profile
	private class CreateProfileHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			smp.cleanView();
			rmp.cleanView();
			osp.cleanView();
			rmp.expandTerm1Pane();
			term1Credit = 0;
			term2Credit = 0;

			smpError = "";
			cppError = "";
			cpp.setError(cppError);
			smp.setError(smpError);

			if (cppIsValid() && cppError.isEmpty()) {
				cppError = "";
				cpp.setError(cppError);
				setupAndSetModules();
				view.changeTab(1);
			} else {
				cpp.setError(cppError);
				cppError = "";
			}
		}
	}

	private class ResetHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			smp.cleanView();
			rmp.cleanView();
			osp.cleanView();
			rmp.expandTerm1Pane();
			term1Credit = 0;
			term2Credit = 0;
			RMPterm1Credit = 0;
			RMPterm2Credit = 0;

			smpError = "";
			cppError = "";
			rmpError = "";

			cpp.setError(cppError);
			smp.setError(smpError);
			rmp.setError(rmpError);

			if (cppIsValid()) {
				setupAndSetModules();
			}
		}
	}

	private class AddTerm1ButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			rmp.cleanView();

			if (smp.getSelectedUnselectedTerm1Module() != null) {

				if (term1Credit < MAX_TOTAL_CREDIT_TERM_1) {
					smpError = "";
					smp.setError(smpError);

					Module m = smp.getSelectedUnselectedTerm1Module();
					term1Credit += m.getCredits();
					smp.removeSelectedUnselectedTerm1Module();
					smp.setSelectedTerm1Module(m);
					smp.setTerm1CreditScore(term1Credit);
				} else {
					smpError = "";
					smpError += "Term 1 credit score is 60.\n";
					smp.setError(smpError);
				}

			} else {
				smpError = "";
				smpError += "Please select an item in unselectected term 1 modules list.\n";
				smp.setError(smpError);
			}
		}
	}

	private class RemoveTerm1ButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			smpError = "";
			smp.setError(smpError);

			rmp.cleanView();
			RMPterm1Credit = 0;
			RMPterm2Credit = 0;

			if (!smp.term1SelectedModulesIsEmpty()) {
				Module m = smp.getSelectedSelectedTerm1Module();

				if (!(m.isMandatory())) {
					smpError = "";
					smp.setError(smpError);

					smp.removeSelectedSelectedTerm1Module();
					smp.setUnselectedTerm1Module(m);

					term1Credit -= m.getCredits();
					smp.setTerm1CreditScore(term1Credit);

					smpError = "";
					smp.setError(smpError);
				} else {
					smpError = "";
					smpError += "Cannot remove (" + m + ") because it is mandatory.\n";
					smp.setError(smpError);
				}
			} else {
				Module m = smp.getSelectedSelectedTerm1Module();

				if (m == null) {
					smpError = "";
					smpError += "Please select an item in selectected term 1 modules list.\n";
					smp.setError(smpError);
				}
			}
		}
	}

	private class AddTerm2ButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			rmp.cleanView();

			if (smp.getSelectedUnselectedTerm2Module() != null) {
				if (term2Credit < MAX_TOTAL_CREDIT_TERM_2) {
					smpError = "";
					smp.setError(smpError);

					Module m = smp.getSelectedUnselectedTerm2Module();
					term2Credit += m.getCredits();
					smp.removeSelectedUnselectedTerm2Module();
					smp.setSelectedTerm2Module(m);
					smp.setTerm2CreditScore(term2Credit);
				} else {
					smpError = "";
					smpError += "Term 2 credit score is 60.\n";
					smp.setError(smpError);
				}
			} else {
				smpError = "";
				smpError += "Please select an item in unselectected term 2 modules list.\n";
				smp.setError(smpError);
			}
		}
	}

	private class RemoveTerm2ButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			rmp.cleanView();
			RMPterm1Credit = 0;
			RMPterm2Credit = 0;

			if (!smp.term2SelectedModulesIsEmpty()) {
				Module m = smp.getSelectedSelectedTerm2Module();

				if (!(m.isMandatory())) {
					smpError = "";
					smp.setError(smpError);

					smp.removeSelectedSelectedTerm2Module();
					smp.setUnselectedTerm2Module(m);

					term2Credit -= m.getCredits();
					smp.setTerm2CreditScore(term2Credit);

					smpError = "";
					smp.setError(smpError);
				} else {
					smpError = "";
					smpError += "Cannot remove (" + m + ") because it is mandatory.\n";
					smp.setError(smpError);
				}
			} else {
				Module m = smp.getSelectedSelectedTerm2Module();

				if (m == null) {
					smpError = "";
					smpError += "Please select an item in selectected term 2 modules list.\n";
					smp.setError(smpError);
				}
			}
		}
	}

	private class SubmitHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			smpError = "";
			cppError = "";
			cpp.setError(cppError);
			smp.setError(smpError);

			rmp.cleanView();

			if (term1Credit == 60) {
				ArrayList<Module> unselectedTerm1Modules = smp.getUnselectedTerm1Modules();
				rmp.setUnselectedTerm1Modules(unselectedTerm1Modules);
			} else {
				if (term2Credit == 60) {
					smpError = "";
				}

				smpError += "Total credit for term 1 should be 60. Only " + term1Credit
						+ " credits worth is selected.\n";
				smp.setError(smpError);
			}

			if (term2Credit == 60) {
				ArrayList<Module> unselectedTerm2Modules = smp.getUnselectedTerm2Modules();
				rmp.setUnselectedTerm2Modules(unselectedTerm2Modules);
			} else {
				if (term1Credit == 60) {
					smpError = "";
				}

				smpError += "Total credit for term 2 should be 60. Only " + term2Credit
						+ " credits worth is selected.\n";
				smp.setError(smpError);
			}

			if (term1Credit == MAX_TOTAL_CREDIT_TERM_1 && term2Credit == MAX_TOTAL_CREDIT_TERM_2 && smpError == "") {
				view.changeTab(2);
				rmp.expandTerm1Pane();
			}
		}
	}

	// generates all module and course data and returns courses within an array
	private Course[] setupAndGetCourses() {
		Module imat3423 = new Module("IMAT3423", "Systems Building: Methods", 15, true, Delivery.TERM_1);
		Module ctec3451 = new Module("CTEC3451", "Development Project", 30, true, Delivery.YEAR_LONG);
		Module ctec3902_SoftEng = new Module("CTEC3902", "Rigorous Systems", 15, true, Delivery.TERM_2);
		Module ctec3902_CompSci = new Module("CTEC3902", "Rigorous Systems", 15, false, Delivery.TERM_2);
		Module ctec3110 = new Module("CTEC3110", "Secure Web Application Development", 15, false, Delivery.TERM_1);
		Module ctec3605 = new Module("CTEC3605", "Multi-service Networks 1", 15, false, Delivery.TERM_1);
		Module ctec3606 = new Module("CTEC3606", "Multi-service Networks 2", 15, false, Delivery.TERM_2);
		Module ctec3410 = new Module("CTEC3410", "Web Application Penetration Testing", 15, false, Delivery.TERM_2);
		Module ctec3904 = new Module("CTEC3904", "Functional Software Development", 15, false, Delivery.TERM_2);
		Module ctec3905 = new Module("CTEC3905", "Front-End Web Development", 15, false, Delivery.TERM_2);
		Module ctec3906 = new Module("CTEC3906", "Interaction Design", 15, false, Delivery.TERM_1);
		Module imat3410 = new Module("IMAT3104", "Database Management and Programming", 15, false, Delivery.TERM_2);
		Module imat3406 = new Module("IMAT3406", "Fuzzy Logic and Knowledge Based Systems", 15, false, Delivery.TERM_1);
		Module imat3611 = new Module("IMAT3611", "Computer Ethics and Privacy", 15, false, Delivery.TERM_1);
		Module imat3613 = new Module("IMAT3613", "Data Mining", 15, false, Delivery.TERM_1);
		Module imat3614 = new Module("IMAT3614", "Big Data and Business Models", 15, false, Delivery.TERM_2);
		Module imat3428_CompSci = new Module("IMAT3428", "Information Technology Services Practice", 15, false,
				Delivery.TERM_2);

		Course compSci = new Course("Computer Science");
		compSci.addModule(imat3423);
		compSci.addModule(ctec3451);
		compSci.addModule(ctec3902_CompSci);
		compSci.addModule(ctec3110);
		compSci.addModule(ctec3605);
		compSci.addModule(ctec3606);
		compSci.addModule(ctec3410);
		compSci.addModule(ctec3904);
		compSci.addModule(ctec3905);
		compSci.addModule(ctec3906);
		compSci.addModule(imat3410);
		compSci.addModule(imat3406);
		compSci.addModule(imat3611);
		compSci.addModule(imat3613);
		compSci.addModule(imat3614);
		compSci.addModule(imat3428_CompSci);

		Course softEng = new Course("Software Engineering");
		softEng.addModule(imat3423);
		softEng.addModule(ctec3451);
		softEng.addModule(ctec3902_SoftEng);
		softEng.addModule(ctec3110);
		softEng.addModule(ctec3605);
		softEng.addModule(ctec3606);
		softEng.addModule(ctec3410);
		softEng.addModule(ctec3904);
		softEng.addModule(ctec3905);
		softEng.addModule(ctec3906);
		softEng.addModule(imat3410);
		softEng.addModule(imat3406);
		softEng.addModule(imat3611);
		softEng.addModule(imat3613);
		softEng.addModule(imat3614);

		Course[] courses = new Course[2];
		courses[0] = compSci;
		courses[1] = softEng;

		return courses;
	}

	private void setupAndSetModules() {
		model = new StudentProfile();
		model.setEmail(cpp.getEmail());
		model.setpNumber(cpp.getPnumberInput());
		model.setCourse(cpp.getSelectedCourse());
		model.setStudentName(cpp.getName());
		model.setSubmissionDate(cpp.getDate());

		Course[] courses = setupAndGetCourses();

		term1Modules = new ArrayList<>();
		term1MandatoryModules = new ArrayList<>();

		term2Modules = new ArrayList<>();
		term2MandatoryModules = new ArrayList<>();

		yearLongModules = new ArrayList<>();

		for (Course course : courses) {
			if (course.getCourseName() == model.getCourse().getCourseName()) {
				for (Module module : course.getAllModulesOnCourse()) {
					if (module.getRunPlan() == Delivery.TERM_1) {
						if (module.isMandatory()) {
							term1MandatoryModules.add(module);
						} else {
							term1Modules.add(module);
						}
					} else if (module.getRunPlan() == Delivery.TERM_2) {
						if (module.isMandatory()) {
							term2MandatoryModules.add(module);
						} else {
							term2Modules.add(module);
						}
					} else if (module.getRunPlan() == Delivery.YEAR_LONG) {
						yearLongModules.add(module);
					}
				}
			}
		}

		smp.setUnselectedTerm1Modules(term1Modules);
		for (Module m : term1MandatoryModules) {
			term1Credit += m.getCredits();
			smp.setSelectedTerm1Modules(term1MandatoryModules);
			smp.setTerm1CreditScore(term1Credit);
		}

		smp.setUnselectedTerm2Modules(term2Modules);
		for (Module m : term2MandatoryModules) {
			term2Credit += m.getCredits();
			smp.setSelectedTerm2Modules(term2MandatoryModules);
			smp.setTerm2CreditScore(term2Credit);
		}

		smp.setSelectedYearLongModules(yearLongModules);

		ArrayList<Integer> tempYearLongList = new ArrayList<>();
		for (Module m : yearLongModules) {
			int half = m.getCredits() / 2;
			tempYearLongList.add(half);
		}

		for (int c : tempYearLongList) {
			smp.setTerm1CreditScore(term1Credit += c);
		}

		for (int c : tempYearLongList) {
			smp.setTerm2CreditScore(term2Credit += c);
		}
	}

	public boolean cppIsValid() {
		boolean isValid = true;

		if (cpp.getPnumberInput().isEmpty()) {
			cppError += "PNumber should not be empty.\n";
			isValid = false;
		}

		if (cpp.getName().getFirstName().isEmpty()) {
			cppError += "First name should not be empty.\n";
			isValid = false;
		}

		if (cpp.getName().getFamilyName().isEmpty()) {
			cppError += "Family name should not be empty.\n";
			isValid = false;
		}

		if (cpp.getEmail().isEmpty()) {
			cppError += "Email should not be empty.\n";
			isValid = false;
		}

		if (cpp.getDate() == null) {
			cppError += "Date should not be empty.\n";
			isValid = false;
		}

		return isValid;
	}

	private class RMPAddTerm1ButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if (rmp.getSelectedUnselectedTerm1Module() != null) {

				if (RMPterm1Credit < 30) {
					rmpError = "";
					rmp.setError(rmpError);

					Module m = rmp.getSelectedUnselectedTerm1Module();

					RMPterm1Credit += m.getCredits();
					rmp.removeSelectedUnselectedTerm1Module();
					rmp.addToReservedTerm1Module(m);
				} else {
					rmpError = "";
					rmp.setError(rmpError);

					rmpError += "You cannot reserve more than 30 credits.\n";
					rmp.setError(rmpError);
				}
			} else {
				rmpError = "";
				rmp.setError(rmpError);

				rmpError += "Please select an item in the unselected term 1 modules list.\n";
				rmp.setError(rmpError);
			}
		}
	}

	private class RMPRemoveTerm1ButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if (rmp.getSelectedReservedTerm1Module() != null) {
				rmpError = "";
				rmp.setError(rmpError);

				Module m = rmp.getSelectedReservedTerm1Module();

				RMPterm1Credit -= m.getCredits();
				rmp.removeSelectedReservedTerm1Module();
				rmp.addToUnselectedTerm1Module(m);
			} else {
				rmpError = "";
				rmp.setError(rmpError);

				rmpError += "Please select an item in term 1 reserved modules list.\n";
				rmp.setError(rmpError);
			}
		}
	}

	private class RMPConfirmTerm1ButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			rmpError = "";
			rmp.setError(rmpError);

			int term1Credit = 0;

			for (Module m : rmp.getReservedTerm1Modules()) {
				term1Credit += m.getCredits();
			}

			if (!rmp.term1PaneIsEmpty()) {
				if (term1Credit == 30) {
					rmp.expandTerm2Pane();
				} else {
					rmpError = "";
					rmp.setError(rmpError);

					rmpError += "Please reserve 30 credits of term 1 modules.";
					rmp.setError(rmpError);
				}
			} else {
				rmpError = "";
				rmp.setError(rmpError);

				rmpError += "Please select a total amount of 30 credits for term 1 reserved modules.";
				rmp.setError(rmpError);
			}
		}
	}

	private class RMPAddTerm2ButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if (rmp.getSelectedUnselectedTerm2Module() != null) {

				if (RMPterm2Credit < 30) {
					rmpError = "";
					rmp.setError(rmpError);

					Module m = rmp.getSelectedUnselectedTerm2Module();

					RMPterm2Credit += m.getCredits();
					rmp.removeSelectedUnselectedTerm2Module();
					rmp.addToReservedTerm2Module(m);
				} else {
					rmpError = "";
					rmp.setError(rmpError);

					rmpError += "You cannot reserve more than 30 credits.\n";
					rmp.setError(rmpError);
				}
			} else {
				rmpError = "";
				rmp.setError(rmpError);

				rmpError += "Please select an item in the unselected term 2 modules list.\n";
				rmp.setError(rmpError);
			}
		}
	}

	private class RMPRemoveTerm2ButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if (rmp.getSelectedReservedTerm2Module() != null) {
				rmpError = "";
				rmp.setError(rmpError);

				Module m = rmp.getSelectedReservedTerm2Module();

				RMPterm2Credit -= m.getCredits();
				rmp.removeSelectedReservedTerm2Module();
				rmp.addToUnselectedTerm2Module(m);
			} else {
				rmpError = "";
				rmp.setError(rmpError);

				rmpError += "Please select an item in term 2 reserved modules list.\n";
				rmp.setError(rmpError);
			}
		}
	}

	private class RMPConfirmTerm2ButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			rmpError = "";
			rmp.setError(rmpError);

			int term2Credit = 0;

			for (Module m : rmp.getReservedTerm2Modules()) {
				term2Credit += m.getCredits();
			}

			if (!rmp.term2PaneIsEmpty()) {

				if (term2Credit >= 30) {
					for (Module m : smp.getSelectedTerm1Modules())
						model.addSelectedModule(m);

					for (Module m : smp.getSelectedTerm2Modules())
						model.addSelectedModule(m);

					for (Module m : smp.getYearLongModules())
						model.addSelectedModule(m);

					for (Module m : rmp.getReservedTerm1Modules())
						model.addReservedModule(m);

					for (Module m : rmp.getReservedTerm2Modules())
						model.addReservedModule(m);

					view.changeTab(3);

					StringBuilder userDetails = new StringBuilder();
					userDetails.append("Name: " + model.getStudentName().getFirstName() + " "
							+ model.getStudentName().getFamilyName() + "\n");
					userDetails.append("PNo: " + model.getpNumber() + "\n");
					userDetails.append("Email: " + model.getEmail() + "\n");
					userDetails.append("Date: " + model.getSubmissionDate() + "\n");
					userDetails.append("Course: " + model.getCourse().getCourseName());

					osp.setDetailsTextArea(userDetails.toString());

					StringBuilder selectedModulesDetails = new StringBuilder();
					selectedModulesDetails.append("Selected Modules" + "\n");
					selectedModulesDetails.append("=========" + "\n");

					for (Module m : model.getAllSelectedModules()) {
						selectedModulesDetails.append("Module Code: " + m.getModuleCode() + ", ");
						selectedModulesDetails.append("Module Name: " + m.getModuleName() + ",\n");
						selectedModulesDetails.append("Credits: " + m.getCredits() + ", ");
						selectedModulesDetails
								.append("Mandatory on your course? " + isMandatoryYesNo(m.isMandatory()) + ", ");
						selectedModulesDetails.append("Delivery: " + m.getRunPlan() + "\n\n");
					}

					osp.setSelectedModulesTextArea(selectedModulesDetails.toString());

					StringBuilder reservedModulesDetails = new StringBuilder();
					reservedModulesDetails.append("Reserved Modules" + "\n");
					reservedModulesDetails.append("=========" + "\n");

					for (Module m : model.getAllReservedModules()) {
						reservedModulesDetails.append("Module Code: " + m.getModuleCode() + ", ");
						reservedModulesDetails.append("Module Name: " + m.getModuleName() + ",\n");
						reservedModulesDetails.append("Credits: " + m.getCredits() + ", ");
						reservedModulesDetails.append("Delivery: " + m.getRunPlan() + "\n\n");
					}

					osp.setReservedModulesTextArea(reservedModulesDetails.toString());

				} else {
					rmpError = "";
					rmp.setError(rmpError);

					rmpError += "Please select a total amount of 30 credits for term 2 reserved modules.";
					rmp.setError(rmpError);
				}
			} else {
				rmpError = "";
				rmp.setError(rmpError);

				rmpError += "Please select a total amount of 30 credits for term 2 reserved modules.";
				rmp.setError(rmpError);
			}
		}

		public String isMandatoryYesNo(boolean bool) {
			if (bool)
				return "yes";
			else
				return "no";
		}
	}
}