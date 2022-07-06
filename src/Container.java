class Container {
    protected static int number = 1;
    protected int id;
    protected int lowerMass, upperMass; // kg
    protected String[] contents;
    protected int length, width, height; // cm

    protected String content;
    protected int mass;


    public Container(int lowerMass, int upperMass, String[] contents, int length, int width, int height) {

        this.id = number;
        number++;

        this.lowerMass = lowerMass;
        this.upperMass = upperMass;
        this.contents = contents;
        this.length = length;
        this.width = width;
        this.height = height;

        this.mass = setRandomMass(); // konstruktor randomizuje zawartość i masę w zależności od podanych progów
        this.content = getRandomContent();
    }

    public int getMass() {
        return mass;
    }

    protected int setRandomMass() {
        int variation = upperMass - lowerMass;
        return (int) (Math.random() * variation + lowerMass);
    }

    protected String getRandomContent() {
        int index = (int) (Math.random() * contents.length);
        return contents[index];
    }

    public String getInformation(int x, int y, int z) {
        char tab = '\t';
        return ("ID " + id)
                + tab + ("Floor " + z)
                + tab + ("Pallet # " + y)
                + tab + ("Container # " + x)
                + tab + ("Contents " + content)
                + tab + ("Mass (kg) " + mass)
                + "\r\n";
    }
}

class GeneralPurposeContainer
        extends Container  {

    public GeneralPurposeContainer() {
        super(
                18000, 22000,
                new String[] {"quartz", "coffee", "salt"},
                590, 233, 236
        );
    }
}

class OpenSideContainer
        extends Container  {

    public OpenSideContainer() {
        super(
                12000, 24000,
                new String[] {"machinery", "pallets"},
                605, 244, 259
        );
    }
}

class FlatRackContainer
        extends Container  {

    public FlatRackContainer() {
        super(
                15000, 21100,
                new String[] {"timber", "pipes", "buses", "boats"},
                590, 234, 230
        );
    }
}

class PlatformContainer
        extends Container  {

    public PlatformContainer() {
        super(
                30000, 34000,
                new String[] {"oil", "gas", "machinery"},
                606, 244, 0
        );
    }
}

class TankContainer
        extends Container  {

    public TankContainer() {
        super(
                17000, 26000,
                new String[] {"water", "milk", "plant oil"},
                244, 610, 259
        );
    }
}

class RefrigeratedContainer
        extends Container  {

    public RefrigeratedContainer() {
        super(
                10000, 18100,
                new String[] {"bananas", "meat", "dairy"},
                568, 223, 208
        );
    }
}
