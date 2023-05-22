// Environment code for project junctionManagement
package main;

import jason.asSyntax.*;
import jason.environment.*;
import main.junctionframework.JunctionFramework;

import java.util.logging.*;



public class Env extends Environment {

    private Logger logger = Logger.getLogger("junctionManagement."+Env.class.getName());

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);

        JunctionFramework.setEnvironment(this);
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        logger.info("executing: " + agName + "-" + action);
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
