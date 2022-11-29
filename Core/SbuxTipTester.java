public class SbuxTipTester {
    public static void main(String[] args) {
        Partner[] PLPartners = {new Partner("Adkins, Emma", 8.1),
                                new Partner("Allen, Max", 27.23),
                                new Partner("Bothwell, Hayden", 36.73),
                                //new Partner("Buhner, Carla", 0),
                                new Partner("Burton, Kit", 19.38),
                                new Partner("Caylor, May", 21.42),
                                new Partner("Chavda, Ansh", 8.05),
                                new Partner("David, Matt", 30.82),
                                new Partner("Gerdes, Ellexa", 12.1),
                                new Partner("Harris, Hallie", 4.1),
                                new Partner("Hern, Pam", 31.6),
                                new Partner("James, Sophie", 18.92),
                                new Partner("Lamb, Lisa", 9.07),
                                new Partner("Lamp, Elin", 21.27),
                                //new Partner("Maloney Mana", 0),
                                new Partner("Nelson, Rilie", 17.6),
                                new Partner("Nelson, Sammy", 5.75),
                                new Partner("Poulin, Deanna", 41.33),
                                new Partner("Ryan, Lara", 22.68),
                                new Partner("Sakamoto, Carly", 16.75),
                                new Partner("Ulrich, Chris", 22.98),
                                new Partner("Wieberneit, Kate", 4.05),};
        TipDistributor base = new TipDistributor(PLPartners, new int[] {
            2, //pennies
            96,//ones
            1,//twos
            8,//fives
            3,//tens
            1});//twenties
    }
}
