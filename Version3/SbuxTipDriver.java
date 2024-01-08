public class SbuxTipDriver {
    public static void main(String[] args) {
        /*TipDistributor base = new TipDistributor("C:/Users/matth/OneDrive/Desktop/Tips7-03.pdf", new int[] {
            3, //pennies
            67,//ones
            1,//twos
            1,//fives
            6,//tens
            0});//twenties
        */
        Partner[] partners =  {
         new Partner("Allen, Max", 22.02),
         new Partner("Alvis, Amaya", 25.1),
         new Partner("Bothwell, Hayden", 36.67),
         new Partner("Burton, Kit", 20.38),
         //new Partner("Butz, Ashley", 12.77),
         new Partner("Caylor, May", 0),
         new Partner("Chavez, Ximena", 12.77),
         new Partner("Chiang, Ryan", 4),
         new Partner("D'souza, Kait", 24.37),
         new Partner("David, Matthew", 25.97),
         //new Partner("Gerdes, Ellexa", 10.02),
         new Partner("Harris, Hallie", 28.15),
         new Partner("Hawk, Megan", 15.43),
         new Partner("Hern, Pamela", 37.18),
         new Partner("Hutt, Jenna", 7.18),
         new Partner("James, Sophiana", 17.42),
         new Partner("Lamb, Lisa", 8.95),
         //new Partner("Lamp, Elin", 39.17),
         //new Partner("Nelson, Rilie", 31.9),
         new Partner("Poulin, Deanna", 30.5),
         new Partner("Pushkar, Jazmin", 4.63),
         //new Partner("Ryan, Lara", 20.33),
         new Partner("Sakamoto, Carly", 19.47),
         new Partner("Segesta-Spencer, Charlotte", 4.08),
         new Partner("Ulrich, Chris", 21)
        };
        /**
         * 120 2 penny rolls
         * 58 1 nickel rolls
         * 59 1 dime rolls
         * 117 2 quarter rolls
         * 51 ones
         * 1 two
         * 2 fives
         * 0 ten
         * 1 twenty
         * 
         */
        TipDistributor base = new TipDistributor(partners, new int[] {2, 51, 2, 3, 2, 1});
        //TipDistributor base = new TipDistributor(); //Takes money values via scanner instead of array
    }
}


