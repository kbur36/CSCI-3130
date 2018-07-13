import java.text.NumberFormat;

/**
 * Represents a pendulum
 */
public abstract class AbstractPendulum extends GravityConstant {


    /* instance variables - string length, point mass, angular displacement
     * at t=0, constant for local gravitational field in m/s^2 (e.g., 9.81 on Earth)
     */
    private double stringLength, pointMass;
    protected double theta0;
    protected GravityConstant g = new GravityConstant();

    /**
     * Creates a new Pendulum instance using
     * inLength: the string length (>0)
     * inMass: the point mass (>0)
     * inTheta0: angular displacement at t=0 (0<=theta0)
     * inG: gravitational field value to use
     */

    public AbstractPendulum (double inLength, double inMass, double inTheta0, double inG) {

    	if (validStringLength (inLength))
            stringLength = inLength;
    	else throw new IllegalArgumentException
            ("invalid string length: " + inLength);
    	if (validPointMass(inMass)) pointMass = inMass;
    	else throw new IllegalArgumentException
            ("invalid point mass: " + inMass);
    	if (validDisplacement (inTheta0))
            theta0 = inTheta0;
    	else throw new IllegalArgumentException
    		 ("invalid angular displacement: " + inTheta0);
    	if (validGC (inG))
            g.setGravitationalField(inG);
    	else throw new IllegalArgumentException
            ("invalid local gravitational field: " + inG);
    }

    private boolean validDisplacement (double val) { return (val >= 0); }
    private boolean validPointMass (double val) { return (val > 0); }
    private boolean validStringLength (double val) { return (val > 0); }
    private boolean validGC (double val) { return (val >= 0); }
    public double getMaxAngularDisplacement () { return theta0; }
    public double getPointMass () { return pointMass; }
    public double getStringLength () { return stringLength; }
    public double getGravitationalField () { return g.getGravitationalField(); }
    public static void iterate(int iterations, RegularPendulum rp, RegularPendulum rpCoarse, SimplePendulum sp, String name) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits (3);

        System.out.println("**********" + name + "**********");
        System.out.println ("analytical vs. numerical displacement (fine, coarse)");
    	for (int second = 1; second <= 20; second++) {
    	    for (int i = 0; i < iterations; i++) rp.step ();
    	    for (int i = 0; i < 10; i++) rpCoarse.step ();
    	    System.out.println ("t=" + second + "s: \t" +
    				nf.format (Math.toDegrees (sp.getTheta (second)))
    				+ "\t" +
    				nf.format (Math.toDegrees (rp.getLastTheta ()))
    				+ "\t" +
    				nf.format (Math.toDegrees (rpCoarse.getLastTheta ())));
    	}
        System.out.println();
    }

}
