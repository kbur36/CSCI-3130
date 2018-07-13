
public class GravityConstant implements GravityModel {
    protected double gravity;

    public GravityConstant(double inG) { this.gravity = inG; }
    public double getGravitationalField() { return this.gravity; }
    public void setGravitationalField(double inG) { this.gravity = inG; }

}
