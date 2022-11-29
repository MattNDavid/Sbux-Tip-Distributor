public class Partner implements Comparable{
    private String name;
    private double hrs;
    private double tipsEarned;
    private double remainder;
    private int pennyRolls;
    private int nickelRolls;
    private int dimeRollsAndFives;
    private int quarterRollsAndTens;
    private int ones;
    private int twenties;
    //private int fives;

    public Partner(String name) {
        this.name = name;
    }
    public Partner(String name, double hrs) {
        setName(name);
        setHrs(hrs);
    }
    //copy constructor
    public Partner(Partner otherPartner) {
        setName(otherPartner.getName());
        setHrs(otherPartner.getHrs());
        setTipsEarned(otherPartner.getTipsEarned());
        setRemainder(otherPartner.getRemainder());
        setPennyRolls(otherPartner.getPennyRolls());
        setNickelRolls(otherPartner.getNickelRolls());
        setDimeRollsAndFives(otherPartner.getDimeRollsAndFives());
        setQuarterRollsAndTens(otherPartner.getQuarterRollsAndTens());
        setOnes(otherPartner.getOnes());
    }
    public void setHrs(double hrs) {
        this.hrs = hrs;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTipsEarned(double tipsEarned) {
        this.tipsEarned = tipsEarned;
    }
    public void setRemainder(double remainder) {
        this.remainder = remainder;
    }
    public void setDimeRollsAndFives(int dimeRolls) {
        this.dimeRollsAndFives = dimeRolls;
    }

    public void setNickelRolls(int nickelRolls) {
        this.nickelRolls = nickelRolls;
    }
    public void setOnes(int ones) {
        this.ones = ones;
    }
    public void setPennyRolls(int pennyRolls) {
        this.pennyRolls = pennyRolls;
    }
    public void setQuarterRollsAndTens(int quarterRollsAndTens) {
        this.quarterRollsAndTens = quarterRollsAndTens;
    }
    public void setTwenties(int twenties) {
        this.twenties = twenties;
    }
    public double getHrs() {
        return hrs;
    }
    public String getName() {
        return name;
    }
    public double getTipsEarned() {
        return tipsEarned;
    }
    public double getRemainder() {
        return remainder;
    }
    public int getDimeRollsAndFives() {
        return dimeRollsAndFives;
    }

    public int getNickelRolls() {
        return nickelRolls;
    }
    public int getOnes() {
        return ones;
    }
    public int getPennyRolls() {
        return pennyRolls;
    }
    public int getQuarterRollsAndTens() {
        return quarterRollsAndTens;
    }
    public int getTwenties() {
        return twenties;
    }
    @Override
    public int compareTo(Object that) {
        if (!(that instanceof Partner)) {
            if (that == null) {
                throw new NullPointerException();
            } else {
                throw new ClassCastException();
            }
        }
        Partner other = (Partner) that;
        return name.compareTo(other.getName());
    }
    @Override
    public String toString() {
        return name;
    }
}
