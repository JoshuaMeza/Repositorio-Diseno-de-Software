package model;

import java.util.ArrayList;

public class GradeUpdater implements GradesObserver {
    @Override
    public boolean update(int id, String subject, int grade, Model model) {
        ArrayList<String> data = model.getFactories()[2].createManager().read( "grades" );
        boolean flag = false;
        String entry = id + "," + subject + "," + grade ;

        for ( String line : data ) {
            if ( line.split(",")[0].equals( "" + id ) ) {
                data.set( data.indexOf( line ), entry );
                flag = true;
                break;
            }
        }

        if ( !flag ) {
            data.clear();
            data.add( entry );
        }

        return model.getFactories()[2].createManager().write( "grades", data, !flag );
    }
}
