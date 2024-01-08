import java.util.*;
import java.io.*;
import java.awt.Desktop;
import java.io.File; 
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.NoSuchFileException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea; 



public class TipDistributor {
    private Partner[] partners = new Partner[0];
    private double totalTips;
    private double totalHrs;
    private double tipsPerHr;
    private String splits = "";
    private int pennyRolls;
    private int nickelRolls;
    private int dimeRollsAndFives;
    private int quarterRolls; //and tens
    private int ones;
    private int twenties;

    public TipDistributor() {
        //getPartnerNames();
        //getPartnerHrs();
        System.out.println("input distribution pdf path");
        Scanner getPath = new Scanner(System.in);
        String path = getPath.nextLine();
        importDistReport(readPDF(path));
        setTotalHrs();
        getTipInfo();
        calculate();
        outputToFile();
    }
    public TipDistributor(Partner[] partners, int[] valSet) {
        for(int i = 0; i < partners.length; i++) {
            addPartner(partners[i]);
        }
        setTotalHrs();
        this.pennyRolls = valSet[0];
        this.ones = valSet[1];
        this.nickelRolls = valSet[2];
        this.dimeRollsAndFives = valSet[3];
        this.quarterRolls = valSet[4];
        this.twenties = valSet[5];
        this.totalTips = twenties * 20 + quarterRolls * 10 + dimeRollsAndFives * 5 + nickelRolls * 2 + ones + pennyRolls *.5;
        calculate();
        outputToFile();
    }
    public TipDistributor(String distReportPath, int[] valSet) {
        importDistReport(readPDF(distReportPath));
        setTotalHrs();
        this.pennyRolls = valSet[0];
        this.ones = valSet[1];
        this.nickelRolls = valSet[2];
        this.dimeRollsAndFives = valSet[3];
        this.quarterRolls = valSet[4];
        this.twenties = valSet[5];
        this.totalTips = twenties * 20 + quarterRolls * 10 + dimeRollsAndFives * 5 + nickelRolls * 2 + ones + pennyRolls *.5;
        calculate();
        outputToFile();
    }
    public TipDistributor(Partner[] partners) {
        for(int i = 0; i < partners.length; i++) {
            addPartner(partners[i]);
        }
        setTotalHrs();
        getTipInfo();
        calculate();
        outputToFile();
    }
    public void setTotalHrs() {
        for(int i = 0; i < this.partners.length; i++) {
            totalHrs += partners[i].getHrs();
        }
    }
    private void calculate() {
        tipsPerHr = totalTips / totalHrs;
        for (int i = 0; i < partners.length; i++) {
            partners[i].setTipsEarned(partners[i].getHrs() * tipsPerHr);
            partners[i].setRemainder(partners[i].getHrs() * tipsPerHr);
        }

        distributeTwenties();
        distributeQuartersAndTens();
        distributeFivesAndDimes();
        distributeNickels();
        distributeOnes();
        distributePennies();
    }
    private void distributeTwenties() {
        if (twenties == 0) {
            return;
        }
        Partner largestRemainder = partners[0];
        for(int i = 0; i < partners.length; i++) {
            if(partners[i].getRemainder() > largestRemainder.getRemainder()) {
                largestRemainder = partners[i];
            }
        }
        if(largestRemainder.getRemainder() >= 20) {
            largestRemainder.setRemainder(largestRemainder.getRemainder() - 20);
            largestRemainder.setTwenties(largestRemainder.getTwenties() + 1);
            twenties--;
        } else {
            this.splits += ("Please split " + twenties + " twenties into " + twenties*4 + " fives or dime rolls.\n");
            dimeRollsAndFives += twenties * 4;
            twenties = 0;
            /*System.out.println("Continue?");
            Scanner a = new Scanner(System.in);*/
        }
        distributeTwenties();
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


        distributePennies();
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
            this.splits += ("Please split " + quarterRolls + " quarter rolls and tens into " + quarterRolls*2 + " fives or dime rolls.\n");
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
            this.splits += ("Please split " + dimeRollsAndFives + " dime rolls and/or fives into " + dimeRollsAndFives*5 + " ones\n");
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
            this.splits +=("Please split " + nickelRolls + " nickel rolls into " + nickelRolls*2 + " ones\n");
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
        getPennies();
        getOnes();
        getNickels();
        getDimes();
        getQuarters();
        getTwenties();
        this.totalTips = twenties * 20 + quarterRolls * 10 + dimeRollsAndFives * 5 + nickelRolls * 2 + ones + pennyRolls *.5;
    }
    private void getPennies() {
        System.out.println("Please input number of penny rolls.");
        Scanner getPennies = new Scanner(System.in);
        try {
            this.pennyRolls = getPennies.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("That doesn't look like a number... Please try again.");
            getPennies();
        }

    }
    private void getNickels() {
        System.out.println("Please input number of nickel rolls.");
        Scanner getNickels = new Scanner(System.in);
        try {
            this.nickelRolls = getNickels.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("That doesn't look like a number... Please try again.");
            getNickels();
        }
    }
    private void getOnes() {
        System.out.println("Please input number of ones.");
        Scanner getOnes = new Scanner(System.in);
        try {
            this.ones = getOnes.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("That doesn't look like a number... Please try again.");
            getOnes();
        }

    }
    private void getDimes() {
        System.out.println("Please input number of dime rolls and fives.");
        Scanner getDimes = new Scanner(System.in);
        try {
            this.dimeRollsAndFives = getDimes.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("That doesn't look like a number... Please try again.");
            getDimes();
        }        

    }
    private void getQuarters() {
        System.out.println("Please input number of quarter rolls and tens.");
        Scanner getQuarters = new Scanner(System.in);
        try {
            this.quarterRolls = getQuarters.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("That doesn't look like a number... Please try again.");
            getQuarters();
        }        

    }
    private void getTwenties() {
        System.out.println("Please input number of twenties.");
        Scanner getTwenties = new Scanner(System.in);
        try {
            this.twenties = getTwenties.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("That doesn't look like a number... Please try again.");
            getTwenties();
        }        

    }

    //deprecated
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
    //deprecated
    private void getPartnerHrs() {
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
    }
    //deprecated
    private void dataPrinter() {
        System.out.println("Total hrs: " + totalHrs + "\nTotal tips: " + totalTips + "\nTips per hr: " + totalTips/totalHrs);
        for(int i = 0; i < partners.length; i++) {
            double pctOfTotalTips = (partners[i].getTwenties() * 20 + partners[i].getQuarterRollsAndTens() * 10 + partners[i].getDimeRollsAndFives() * 5 + partners[i].getNickelRolls() * 2 + partners[i].getOnes() + partners[i].getPennyRolls() *.5) / totalTips;
            System.out.println(partners[i] + "\n     Hours: " + partners[i].getHrs() + "\n     Pct of total hrs: " + partners[i].getHrs()/totalHrs + "\n     Pct of total tips: " + pctOfTotalTips + "\n     Twenties: " + partners[i].getTwenties() + "\n     Tens/Quarter Rolls: " + partners[i].getQuarterRollsAndTens() + "\n     Fives/Dime Rolls: " + partners[i].getDimeRollsAndFives() + "\n     Nickel Rolls: " + partners[i].getNickelRolls() + "\n     Ones: " + partners[i].getOnes() + "\n     Penny Rolls: " + partners[i].getPennyRolls());
            System.out.println("     Tips recieved: " + (partners[i].getTipsEarned() - partners[i].getRemainder()));
        }
    }
    public String readPDF(String path) {
        String text = "";
        try {
            PDDocument document = Loader.loadPDF(new File(path));
            if (!document.isEncrypted()) {
                PDFTextStripper stripper = new PDFTextStripper();
                text = stripper.getText(document);
                //System.out.println("Text:" + text);
            }
            document.close(); 
        } catch (NoSuchFileException | InvalidPathException e) {
            System.out.println("That doesn't look like the right path, please double check and try again!\nEnter new path below.");
            Scanner getPath = new Scanner(System.in);
            String path2 = getPath.nextLine();
            return readPDF(path2);
        }  catch (IOException e) {
            System.out.println(e.toString());
        } 

        
        return text;
        
    }
    private void outputToFile() {
        String text = ("Total hrs: " + totalHrs + "\nTotal tips: " + totalTips + "\nTips per hr: " + totalTips/totalHrs + "\n\n" + splits + "\n");
        for(int i = 0; i < partners.length; i++) {
            double pctOfTotalTips = (partners[i].getTwenties() * 20 + partners[i].getQuarterRollsAndTens() * 10 + partners[i].getDimeRollsAndFives() * 5 + partners[i].getNickelRolls() * 2 + partners[i].getOnes() + partners[i].getPennyRolls() *.5) / totalTips;            
            text += (partners[i] + "\n     Hours: " + partners[i].getHrs() + "\n     Pct of total hrs: " + partners[i].getHrs()/totalHrs + "\n     Pct of total tips: " + pctOfTotalTips + "\n     Twenties: " + partners[i].getTwenties() + "\n     Tens/Quarter Rolls: " + partners[i].getQuarterRollsAndTens() + "\n     Fives/Dime Rolls: " + partners[i].getDimeRollsAndFives() + "\n     Nickel Rolls: " + partners[i].getNickelRolls() + "\n     Ones: " + partners[i].getOnes() + "\n     Penny Rolls: " + partners[i].getPennyRolls() + "\n");
            text += ("     Tips recieved: " + (partners[i].getTipsEarned() - partners[i].getRemainder()) + "\n\n");
        }
        try {
            String date = Calendar.getInstance().getTime().toString();
            date = date.replace(':', '_');
            date = date.replace(' ', '_');
            File distFile = new File("C:/Users/matth/CSS/SbuxTips/Past Distributions/TipDist" + date + ".txt");
            boolean isCreated = distFile.createNewFile();
            PrintWriter out = new PrintWriter("C:/Users/matth/CSS/SbuxTips/Past Distributions/TipDist" + date + ".txt");
            out.println(text);
            out.close();
            Desktop desktop = Desktop.getDesktop();
            desktop.open(distFile);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    //deprecated
    public void addPartner(String partner) {
        partners = Arrays.copyOf(partners, partners.length + 1);
        partners[partners.length - 1] = new Partner(partner);
        sortPartners();
    }
    public void importDistReport(String report) {
        try { 
            report = report.split("Hours")[1];
            String[] part1 = report.split("\\r?\\n");
            String[] part2 = Arrays.copyOfRange(part1, 1, part1.length - 1);
            partners = new Partner[part2.length];
            for(int i = 0; i < part2.length; i++) {
                String[] partnerAtts = part2[i].split(" ");
                partners[i] = new Partner(partnerAtts[1] + " " + partnerAtts[2] + " " + partnerAtts[3], Double.parseDouble(partnerAtts[partnerAtts.length - 1]));
            } 
        } catch (Exception e) {
            System.out.println("Looks like this isn't a distribution report... please try a new file path.");
            partners = null;
            importDistReport(readPDF(" "));
        }
    }
    //deprecated
    public void addPartner(Partner partner) {
        partners = Arrays.copyOf(partners, partners.length + 1);
        partners[partners.length - 1] = new Partner(partner);
        sortPartners();
    }

}
