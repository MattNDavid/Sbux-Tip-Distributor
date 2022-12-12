//Basic test file
public class SbuxTipTester {
    public static void main(String[] args) {
        Partner[] PLPartners = {new Partner("Adkins, Emma", 12.08),
                                new Partner("Allen, Max", 34.72),
                                new Partner("Bothwell, Hayden", 6.48),
                                new Partner("Buhner, Carla", 0),
                                new Partner("Burton, Kit", 20.72),
                                new Partner("Caylor, May", 21.28),
                                new Partner("Chavda, Ansh", 16.63),
                                new Partner("David, Matt", 28.15),
                                new Partner("Frederick, Faith", 0),
                                new Partner("Gerdes, Ellexa", 12.58),
                                new Partner("Harris, Hallie", 1.5),
                                new Partner("Hern, Pam", 29.85),
                                new Partner("James, Sophie", 8.28),
                                new Partner("Lamb, Lisa", 5.48),
                                new Partner("Lamp, Elin", 16.15),
                                new Partner("Maloney Mana", 0),
                                new Partner("Nelson, Rilie", 19.63),
                                new Partner("Nelson, Sammy", 0),
                                new Partner("Poulin, Deanna", 32.05),
                                new Partner("Ryan, Lara", 34.98),
                                new Partner("Sakamoto, Carly", 16.38),
                                new Partner("Ulrich, Chris", 18.63),
                                /*new Partner("Wieberneit, Kate", 0)*/};
        TipDistributor base = new TipDistributor(PLPartners, new int[] {2, 78, 1, 4, 2, 0});
    }
}
