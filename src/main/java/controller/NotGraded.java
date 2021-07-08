package controller;

public class NotGraded implements State {
    @Override
    public void action(Controller controller) {
        int gradesAmount = controller.getModel().getGradesAmount();
        int studentsAmount = controller.getModel().getStudentsAmount();

        if ( gradesAmount == 0 ) {
            // Action
            controller.getList().updateStateLabel("Sin calificar");
            controller.getList().updateProgressBar(gradesAmount,studentsAmount);
            controller.getList().changeSaveButtonAccess(false);
        } else {
            // Go forward
            controller.setState( new PartiallyGraded() );
            controller.getState().action( controller );
        }
    }
}
