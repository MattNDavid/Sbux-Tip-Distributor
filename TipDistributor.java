import java.util.*;

public class TipDistributor {
    private Partner[] partners = new Partner[0];
    // private double[] hrsWorked;
    // private double[] tipsEarned;
    private double totalTips;
    private double totalHrs;
    private double tipsPerHr;
    private int pennyRolls;
    private int nickelRolls;
    private int dimeRollsAndFives;
    private int quarterRolls; //and tens
    private int ones;
    //private int fives;
    // private int tens;

    public TipDistributor() {
        getPartnerNames();
        getPartnerHrs();
        getTipInfo();
        calculate();
        dataPrinter();
    }
    public TipDistributor(Partner[] partners) {
        for(int i = 0; i < partners.length; i++) {
            addPartner(partners[i]);
        }
        setTotalHrs();
        getTipInfo();
        calculate();
        dataPrinter();
    }
    public void setTotalHrs() {
        for(int i = 0; i < this.partners.length; i++) {
            totalHrs += partners[i].getHrs();
        }
    }
    private void calculate() {
        tipsPerHr = totalTips / totalHrs;
        // this.tipsEarned = new double[hrsWorked.length];
        for (int i = 0; i < partners.length; i++) {
            partners[i].setTipsEarned(partners[i].getHrs() * tipsPerHr);
            partners[i].setRemainder(partners[i].getHrs() * tipsPerHr);
        }

       
        distributeQuartersAndTens();
        distributeFivesAndDimes();
        //distributeDimes();
        distributeNickels();
        distributeOnes();
        distributePennies();
    }

    private void distributePennies() {
        if (pennyRolls == 0)
            return;
        Partner biggestRemainderGuy = partners[0];
        for (int i = 0; i < partners.length; i++) {
            if (partners[i].getRemainder() > biggestRemainderGuy.getRemainder()) {
                biggestRemainderGuy = partners[i];
            }
        }
        biggestRemainderGuy.setPennyRolls(biggestRemainderGuy.getPennyRolls() + 1);
        biggestRemainderGuy.setRemainder(biggestRemainderGuy.getRemainder() - .5);
        pennyRolls--;


        distributePennies();/*
        while(pennyRolls != 0) {
            for(int i = 0; i < partners.length; i++) {
                if(pennyRolls == 0)
                    return;
                partners[i].setPennyRolls(partners[i].getPennyRolls() + 1);
                partners[i].setRemainder(partners[i].getRemainder() - .5);
                pennyRolls--;
            }
        }*/
    }

    private void distributeQuartersAndTens() {
        if(quarterRolls == 0) {
            return;
        }
        Partner largestRemainder = partners[0];
        for(int i = 0; i < partners.length; i++) {
            if(partners[i].getRemainder() > largestRemainder.getRemainder()) {
                largestRemainder = partners[i];
            }
        }
        if(largestRemainder.getRemainder() >= 10) {
            largestRemainder.setRemainder(largestRemainder.getRemainder() - 10);
            largestRemainder.setQuarterRollsAndTens(largestRemainder.getQuarterRollsAndTens() + 1);
            quarterRolls--;
        } else {
            System.out.println("Please split " + quarterRolls + " quarter rolls and tens into " + quarterRolls*2 + " fives or dime rolls.");
            dimeRollsAndFives += quarterRolls * 2;
            quarterRolls = 0;
            /*System.out.println("Continue?");
            Scanner a = new Scanner(System.in);*/
        }
        distributeQuartersAndTens();
    }
    private void distributeFivesAndDimes() {
        if(dimeRollsAndFives == 0) {
            return;
        }
        Partner largestRemainder = partners[0];
        for(int i = 0; i < partners.length; i++) {
            if(partners[i].getRemainder() > largestRemainder.getRemainder()) {
                largestRemainder = partners[i];
            }
        }
        if(largestRemainder.getRemainder() >= 5) {
            largestRemainder.setRemainder(largestRemainder.getRemainder() - 5);
            largestRemainder.setDimeRollsAndFives(largestRemainder.getDimeRollsAndFives() + 1);
            dimeRollsAndFives--;
        } else {
            System.out.println("Please split " + dimeRollsAndFives + " dime rolls and/or fives into " + dimeRollsAndFives*5 + " ones");
            ones += dimeRollsAndFives * 5;
            dimeRollsAndFives = 0;
            /*System.out.println("Continue?");
            Scanner a = new Scanner(System.in);*/
        }
        distributeFivesAndDimes();
    }
    private void distributeNickels() {
        if(nickelRolls == 0) {
            return;
        }
        Partner largestRemainder = partners[0];
        for(int i = 0; i < partners.length; i++) {
            if(partners[i].getRemainder() > largestRemainder.getRemainder()) {
                largestRemainder = partners[i];
            }
        }
        if(largestRemainder.getRemainder() >= 2) {
            largestRemainder.setRemainder(largestRemainder.getRemainder() - 2);
            largestRemainder.setNickelRolls(largestRemainder.getNickelRolls() + 1);
            nickelRolls--;
        } else {
            System.out.println("Please split " + nickelRolls + " nickel rolls into " + nickelRolls*2 + " ones");
            ones += nickelRolls * 2;
            nickelRolls = 0;
            /*System.out.println("Continue?");
            Scanner a = new Scanner(System.in);*/
        }
        distributeNickels();
    }
    private void distributeOnes() {
        if(ones == 0) {
            return;
        }
        Partner largestRemainder = partners[0];
        for(int i = 0; i < partners.length; i++) {
            if(partners[i].getRemainder() > largestRemainder.getRemainder()) {
                largestRemainder = partners[i];
            }
        }
        largestRemainder.setRemainder(largestRemainder.getRemainder() - 1);
        largestRemainder.setOnes(largestRemainder.getOnes() + 1);
        ones--;
        distributeOnes();
    }

    private void getTipInfo() {
        /*System.out.println("Please input total amount of tips in $.");
        Scanner getTotalTips = new Scanner(System.in);
        this.totalTips = getTotalTips.nextDouble();*/

        System.out.println("Please input number of penny rolls.");
        Scanner getPennies = new Scanner(System.in);
        this.pennyRolls = getPennies.nextInt();

        System.out.println("Please input number of nickel rolls.");
        Scanner getNickels = new Scanner(System.in);
        this.nickelRolls = getNickels.nextInt();

        System.out.println("Please input number of dime rolls and fives.");
        Scanner getDimes = new Scanner(System.in);
        this.dimeRollsAndFives = getDimes.nextInt();

        System.out.println("Please input number of quarter rolls and tens.");
        Scanner getQuarters = new Scanner(System.in);
        this.quarterRolls = getQuarters.nextInt();

        System.out.println("Please input number of ones.");
        Scanner getOnes = new Scanner(System.in);
        this.ones = getOnes.nextInt();

        this.totalTips = quarterRolls * 10 + dimeRollsAndFives * 5 + nickelRolls * 2 + ones + pennyRolls *.5;
        /*System.out.println("Please input number of fives.");
        Scanner getFives = new Scanner(System.in);
        this.fives = getFives.nextInt();*/

        // System.out.println("Please input number of tens.");
        // Scanner getTens = new Scanner(System.in);
        // this.tens = getTens.nextInt();
    }

    private void getPartnerNames() {
        System.out.println("Please input all partner names, hit enter after each one. Submit a blank line to finish");
        boolean areNewPartners = true;
        while (areNewPartners) {
            Scanner getPartnerNames = new Scanner(System.in);
            String newPartner = getPartnerNames.nextLine();

            if (newPartner.equals("")) {
                areNewPartners = false;
            } else {
                addPartner(newPartner);
            }
        }
    }

    private void getPartnerHrs() {
        // hrsWorked = new double[partners.length];
        double totalHrs = 0;
        for (int i = 0; i < partners.length; i++) {
            System.out.println("Input " + partners[i].getName() + "'s hours.");
            Scanner getPartnerHrs = new Scanner(System.in);
            partners[i].setHrs(getPartnerHrs.nextDouble());
            totalHrs += partners[i].getHrs();
        }
        this.totalHrs = totalHrs;
    }

    private void sortPartners() {
        for (int i = 0; i < partners.length; i++) {
            for (int j = i; j < partners.length; j++) {
                if (partners[i]
                        .compareTo /* Change this > to < to sort from greatest to least */ (partners[j]) > 0) {
                    Partner temp = partners[i];
                    partners[i] = partners[j];
                    partners[j] = temp;
                }
            }
        }
        //System.out.println(Arrays.toString(partners));
    }
    private void dataPrinter() {
        System.out.println("Total hrs: " + totalHrs + "\nTotal tips: " + totalTips + "\nTips per hr: " + totalTips/totalHrs);
        for(int i = 0; i < partners.length; i++) {
            double pctOfTotalTips = (partners[i].getQuarterRollsAndTens() * 10 + partners[i].getDimeRollsAndFives() * 5 + partners[i].getNickelRolls() * 2 + partners[i].getOnes() + partners[i].getPennyRolls() *.5) / totalTips;
            
            //double tipsRecieved = partners[i].getQuarterRollsAndTens() * 10 + partners[i].getDimeRollsAndFives() * 5 + partners[i].getNickelRolls() * 2 + partners[i].getOnes() + partners[i].getPennyRolls() *.5;
            System.out.println(partners[i] + "\n     Hours: " + partners[i].getHrs() + "\n     Pct of total hrs: " + partners[i].getHrs()/totalHrs + "\n     Pct of total tips: " + pctOfTotalTips + "\n     Tens/Quarter Rolls: " + partners[i].getQuarterRollsAndTens() + "\n     Fives/Dime Rolls: " + partners[i].getDimeRollsAndFives() + "\n     Nickel Rolls: " + partners[i].getNickelRolls() + "\n     Ones: " + partners[i].getOnes() + "\n     Penny Rolls: " + partners[i].getPennyRolls());
            System.out.println("     Tips recieved: " + (partners[i].getTipsEarned() - partners[i].getRemainder()));
        }
    }
    public void addPartner(String partner) {
        partners = Arrays.copyOf(partners, partners.length + 1);
        partners[partners.length - 1] = new Partner(partner);
        sortPartners();
    }
    public void addPartner(Partner partner) {
        partners = Arrays.copyOf(partners, partners.length + 1);
        partners[partners.length - 1] = new Partner(partner);
        sortPartners();
    }

    /*public double[] getHrsWorked() {
        return hrsWorked;
    }*/

    /*public Partner[] getPartners() {
        return partners;
    }*/

}
