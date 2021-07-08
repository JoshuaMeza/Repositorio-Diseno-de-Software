package controller;

public class PartiallyGraded implements State {
    @Override
    public void action(Controller controller) {
        int gradesAmount = controller.getModel().getGradesAmount();
        int studentsAmount = controller.getModel().getStudentsAmount();

        if ( gradesAmount < studentsAmount ) {
            // Action
            controller.getList().updateStateLabel("Parcialmente calificado");
            controller.getList().updateProgressBar(gradesAmount,studentsAmount);
            controller.getList().changeSaveButtonAccess(true);
        } else if ( gradesAmount == studentsAmount ) {
            // Go forward
            controller.setState( new Graded() );
            controller.getState().action( controller );
        } else {
            // Go back
            controller.setState( new NotGraded() );
            controller.getState().action( controller );
        }
    }
}
