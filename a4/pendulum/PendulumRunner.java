
/**
 * compares the values of a simple pendulum using the harmonic motion equation
 * versus the Euler algorithm approximation
 */
public class PendulumRunner {
    public static void main (String [] args) {
        double delta = (args.length == 0) ? .1 : Double.parseDouble (args[0]);
        double sLen = 10, pMass = 10, theta0 = Math.PI/30;
        int iterations = (int) (1/delta);

        GravityConstant earthGravity = new GravityConstant(9.80665);
        GravityConstant jupiterGravity = new GravityConstant(25.0);

        RegularPendulum rp = new RegularPendulum (sLen, pMass, theta0, delta, earthGravity.getGravitationalField());
        SimplePendulum sp = new SimplePendulum (sLen, pMass, theta0, earthGravity.getGravitationalField());
        RegularPendulum rpCoarse = new RegularPendulum (sLen, pMass, theta0, .1, earthGravity.getGravitationalField());

        AbstractPendulum.iterate(iterations, rp, rpCoarse, sp, "Earth");

        RegularPendulum rpJupiter = new RegularPendulum (sLen, pMass, theta0, delta, jupiterGravity.getGravitationalField());
        SimplePendulum spJupiter = new SimplePendulum (sLen, pMass, theta0, jupiterGravity.getGravitationalField());
        RegularPendulum rpCoarseJupiter = new RegularPendulum (sLen, pMass, theta0, .1, jupiterGravity.getGravitationalField());

        AbstractPendulum.iterate(iterations, rpJupiter, rpCoarseJupiter, spJupiter, "Jupiter");
    }
}
