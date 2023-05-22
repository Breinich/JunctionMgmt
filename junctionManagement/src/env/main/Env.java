// Environment code for project junctionManagement

import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;

import java.util.logging.*;

import world.*;

public class Env extends Environment {

    private Logger logger = Logger.getLogger("junctionManagement."+Env.class.getName());

    private Simulator simulator;

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);
        
        simulator = new Simulator();

        //new Thread(simulator).start();
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        logger.info("executing: "+agName+"-"+action);
        if (true) {
             informAgsEnvironmentChanged();
        }
        return true; // the action was executed with success
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}
