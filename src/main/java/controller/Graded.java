package controller;

public class Graded implements State {
    @Override
    public void action(Controller controller) {
        int gradesAmount = controller.getModel().getGradesAmount();
        int studentsAmount = controller.getModel().getStudentsAmount();

        if ( gradesAmount == studentsAmount ) {
            // Action
            controller.getList().updateStateLabel("Calificado");
            controller.getList().updateProgressBar(gradesAmount,studentsAmount);
            controller.getList().changeSaveButtonAccess(true);
        } else {
            // Go Back
            controller.setState( new PartiallyGraded() );
            controller.getState().action( controller );
        }
    }
}
