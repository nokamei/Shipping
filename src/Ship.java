import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Ship {

    public static void main(String[] args) {
        final int HEIGHT = 6;
        final int SIDE = 50;
        int number = HEIGHT * SIDE * SIDE;

        Container[] list = getContainers(number); // generuje listę losowych kontenerów
        Container[][][] containerShip = new Container[HEIGHT][SIDE][SIDE];
        shipInOrder(list, containerShip);
        // metoda załadowująca statek skupia ciężar w najniższym środkowym punkcie statku

        try {

            BufferedWriter documentation = new BufferedWriter(new FileWriter("documentation.txt"));
            // zapis informacji o kontenerach do pliku tekstowego
            for (int h=0; h<HEIGHT; h++) {
                for (int i = 0; i < SIDE; i++) {
                    for (int j = 0; j < SIDE; j++) {
                        Container cont = containerShip[h][i][j];
                        if (cont != null) {
                            documentation.write(cont.getInformation(j + 1, i + 1, h));
                        }
                    }
                }
            }

            documentation.flush();
            documentation.close();

        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public static void shipInOrder(Container[] list, Container[][][] ship) {
        for (Container[][] containers : ship) {
            distributeWeight(list, containers);
        }
    }

    public static void distributeWeight(Container[] list, Container[][] floor) {
        int x = floor.length/2 - 1;
        int y = floor.length/2;
        int dx = -1;
        int dy = 1;

        int length = 1;
        int iteration = 1;

        while (length <= floor.length) {
            int tmpdx = 0;
            int tmpdy = 0;
            int temp = length;

            if (iteration % 2 == 0) {
                dx  *= -1;
                tmpdx = dx;
            }
            else {
                dy *= -1;
                tmpdy = dy;
            }

            while (temp > 0) {
                int i = getHeaviestContainerIndex(list);
                Container cont = list[i];
                try {
                    floor[y][x] = cont;
                    list[i] = null;
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
                x += tmpdx;
                y += tmpdy;
                temp--;
            }

            if(iteration % 2 == 0)
                length++;
            iteration++;
        }
    }

    // losuje kontenery różnych typów o rożnych parametrach
    public static Container[] getContainers(int number) {
        Container[] toFill = new Container[number];
        for (int i=0; i<number; i++) {
            toFill[i] = getContainer();
        }
        return toFill;
    }

    public static Container getContainer() {
        int type = (int)(Math.random()*6);

        return switch (type) {
            case 0 -> new GeneralPurposeContainer();
            case 1 -> new OpenSideContainer();
            case 2 -> new FlatRackContainer();
            case 3 -> new PlatformContainer();
            case 4 -> new TankContainer();
            case 5 -> new RefrigeratedContainer();
            default -> null;
        };
    }

    public static int getHeaviestContainerIndex(Container[] list) {
        
        int max = 0;
        while (list[max] == null) {
            max++;
            if(max == 15000) return 14999;
        }

        for(int i=0; i<list.length; i++) {
            if(list[i] != null && list[i].getMass() > list[max].getMass())
                max = i;
        }

        return max;
    }
}


