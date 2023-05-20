import java.util.logging.Logger;

import jason.environment.Environment;
import world.Simulator;


public class JunctionEnv extends Environment{
	
	private Logger logger = Logger.getLogger("junctionMgmt."+JunctionEnv.class.getName());
	private Simulator simulator;
    
    
	public JunctionEnv() {
		
	}
	
	@Override
    public void init(String[] args) {
        super.init(args);
    }
}