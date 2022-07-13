import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static final int week[] = new int[10];

    public int[] findPlanned(int[] grossRequirements, int[] scheduledReceipts, int[] amountOnHand, int leadTime, String lotSizingRule, int[] netRequirements, int[] timePhased, int[] plannedRelease, int[] plannedDelivery) {
        for (int i = 0; i < week.length; i++) {
            if (grossRequirements[i] == 0) {
                if (i < 9) {
                    amountOnHand[i + 1] = scheduledReceipts[i] + amountOnHand[i];
                }
            } else if (grossRequirements[i] > (amountOnHand[i] + scheduledReceipts[i])) {
                netRequirements[i] = grossRequirements[i] - (amountOnHand[i] + scheduledReceipts[i]);
                if (i != 9) {
                    amountOnHand[i + 1] = 0;
                }
            } else if (grossRequirements[i] < amountOnHand[i] + scheduledReceipts[i]) {
                if (i != 9) {
                    amountOnHand[i + 1] = (amountOnHand[i] + scheduledReceipts[i]) - grossRequirements[i];
                }
            }
            if (netRequirements[i] != 0) {
                if (i >= leadTime) {
                    timePhased[i - leadTime] = netRequirements[i];
                    if (lotSizingRule.equals("L4L")) {
                        plannedRelease[i - leadTime] = netRequirements[i];
                        plannedDelivery[i] = netRequirements[i];
                    } else if (netRequirements[i] <= Integer.parseInt(lotSizingRule)) {
                        plannedRelease[i - leadTime] = Integer.parseInt(lotSizingRule);
                        plannedDelivery[i] = Integer.parseInt(lotSizingRule);
                        if ((i + 1) < 10) {
                            amountOnHand[i + 1] = plannedRelease[i - leadTime] - netRequirements[i];
                        }
                    } else if (netRequirements[i] > Integer.parseInt(lotSizingRule)) {
                        if (netRequirements[i] % Integer.parseInt(lotSizingRule) == 0) {
                            int y = (netRequirements[i] / Integer.parseInt(lotSizingRule));
                            plannedRelease[i - leadTime] = (y * Integer.parseInt(lotSizingRule));
                            plannedDelivery[i] = (y * Integer.parseInt(lotSizingRule));
                        }
                        if (netRequirements[i] % Integer.parseInt(lotSizingRule) != 0) {
                            int y = (netRequirements[i] / Integer.parseInt(lotSizingRule)) + 1;
                            plannedRelease[i - leadTime] = (y * Integer.parseInt(lotSizingRule));
                            plannedDelivery[i] = (y * Integer.parseInt(lotSizingRule));
                        }


                        if ((i + 1) < 10) {
                            amountOnHand[i + 1] = plannedRelease[i - leadTime] - netRequirements[i];
                        }
                    }
                }
            }


        }
        System.out.println("Gross requirements :           " + Arrays.toString(grossRequirements));
        System.out.println("Scheduled receipts :           " + Arrays.toString(scheduledReceipts));
        System.out.println("On hand from prior period :    " + Arrays.toString(amountOnHand));
        System.out.println("Net requirements :             " + Arrays.toString(netRequirements));
        System.out.println("Time-phased net requirements : " + Arrays.toString(timePhased));
        System.out.println("Planned order releases :       " + Arrays.toString(plannedRelease));
        System.out.println("Planned order delivery :       " + Arrays.toString(plannedDelivery));
        return plannedRelease;
    }

    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader in = new BufferedReader(new FileReader("src/items.txt"));
        String str = null;
        ArrayList<String> lines = new ArrayList<String>();
        while (true) {
            try {
                if ((str = in.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            lines.add(str);
        }
        String[][] strings = new String[13][5];
        String[] linesArray = lines.toArray(new String[lines.size()]);
        for (int i = 0; i < 13; i++) {
            strings[i] = linesArray[i].split(" ");
        }

        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        int[] test = new int[]{0, 50, 100, 0, 0, 0, 50, 0, 0, 0};
        Scanner scanner1 = new Scanner(System.in);
        ShovelComplete1605 shovelComplete1605 = new ShovelComplete1605();
        TopHandle13122 topHandle13122 = new TopHandle13122();
        Shift118 shift118 = new Shift118();
        ScoopShaft048 scoopShaft048 = new ScoopShaft048();
        Nail062 nail062 = new Nail062();
        Rivot14127 rivot14127 = new Rivot14127();
        ScoopAssembly314 scoopAssembly314 = new ScoopAssembly314();
        TopHandle457 topHandle457 = new TopHandle457();
        Bracelet11495 bracelet11495 = new Bracelet11495();
        TopHandle129 topHandle129 = new TopHandle129();
        TopHandle1118 topHandle1118 = new TopHandle1118();
        Scoop2142 scoop2142 = new Scoop2142();
        Blade019 blade019 = new Blade019();

        shovelComplete1605.amountOnHand[0] = Integer.parseInt(strings[0][0]);
        shovelComplete1605.scheduledReceipts[(Integer.parseInt(strings[0][2]))] = Integer.parseInt(strings[0][1]);
        shovelComplete1605.leadTime = Integer.parseInt(strings[0][3]);
        shovelComplete1605.lotSizingRule = strings[0][4];

        topHandle13122.amountOnHand[0] = Integer.parseInt(strings[1][0]);
        topHandle13122.scheduledReceipts[(Integer.parseInt(strings[1][2])-1)] = Integer.parseInt(strings[1][1]);
        topHandle13122.leadTime = Integer.parseInt(strings[1][3]);
        topHandle13122.lotSizingRule = strings[1][4];

        scoopShaft048.amountOnHand[0] = Integer.parseInt(strings[2][0]);
        scoopShaft048.scheduledReceipts[Integer.parseInt(strings[2][2])] = Integer.parseInt(strings[2][1]);
        scoopShaft048.leadTime = Integer.parseInt(strings[2][3]);
        scoopShaft048.lotSizingRule = strings[2][4];

        shift118.amountOnHand[0] = Integer.parseInt(strings[3][0]);
        shift118.scheduledReceipts[(Integer.parseInt(strings[3][2])-1)] = Integer.parseInt(strings[3][1]);
        shift118.leadTime = Integer.parseInt(strings[3][3]);
        shift118.lotSizingRule = strings[3][4];

        nail062.amountOnHand[0] = Integer.parseInt(strings[4][0]);
        nail062.scheduledReceipts[(Integer.parseInt(strings[4][2])-1)] = Integer.parseInt(strings[4][1]);
        nail062.leadTime = Integer.parseInt(strings[4][3]);
        nail062.lotSizingRule = strings[4][4];

        rivot14127.amountOnHand[0] = Integer.parseInt(strings[5][0]);
        rivot14127.scheduledReceipts[Integer.parseInt(strings[5][2])] = Integer.parseInt(strings[0][1]);
        rivot14127.leadTime = Integer.parseInt(strings[5][3]);
        rivot14127.lotSizingRule = strings[5][4];

        scoopAssembly314.amountOnHand[0] = Integer.parseInt(strings[6][0]);
        scoopAssembly314.scheduledReceipts[(Integer.parseInt(strings[6][2])-1)] = Integer.parseInt(strings[6][1]);
        scoopAssembly314.leadTime = Integer.parseInt(strings[6][3]);
        scoopAssembly314.lotSizingRule = strings[6][4];

        topHandle457.amountOnHand[0] = Integer.parseInt(strings[7][0]);
        topHandle457.scheduledReceipts[(Integer.parseInt(strings[7][2])-1)] = Integer.parseInt(strings[7][1]);
        topHandle457.leadTime = Integer.parseInt(strings[7][3]);
        topHandle457.lotSizingRule = strings[7][4];

        bracelet11495.amountOnHand[0] = Integer.parseInt(strings[8][0]);
        bracelet11495.scheduledReceipts[Integer.parseInt(strings[8][2])] = Integer.parseInt(strings[8][1]);
        bracelet11495.leadTime = Integer.parseInt(strings[8][3]);
        bracelet11495.lotSizingRule = strings[8][4];

        topHandle129.amountOnHand[0] = Integer.parseInt(strings[9][0]);
        topHandle129.scheduledReceipts[(Integer.parseInt(strings[9][2])-1)] = Integer.parseInt(strings[9][1]);
        topHandle129.leadTime = Integer.parseInt(strings[9][3]);
        topHandle129.lotSizingRule = strings[9][4];

        topHandle1118.amountOnHand[0] = Integer.parseInt(strings[10][0]);
        topHandle1118.scheduledReceipts[Integer.parseInt(strings[10][2])] = Integer.parseInt(strings[10][1]);
        topHandle1118.leadTime = Integer.parseInt(strings[10][3]);
        topHandle1118.lotSizingRule = strings[10][4];

        scoop2142.amountOnHand[0] = Integer.parseInt(strings[11][0]);
        scoop2142.scheduledReceipts[Integer.parseInt(strings[11][2])] = Integer.parseInt(strings[11][1]);
        scoop2142.leadTime = Integer.parseInt(strings[11][3]);
        scoop2142.lotSizingRule = strings[11][4];

        blade019.amountOnHand[0] = Integer.parseInt(strings[12][0]);
        blade019.scheduledReceipts[(Integer.parseInt(strings[12][2])-1)] = Integer.parseInt(strings[12][1]);
        blade019.leadTime = Integer.parseInt(strings[12][3]);
        blade019.lotSizingRule = strings[12][4];

        boolean flag = true;
        while (flag) {
            try {
                System.out.println("\nWelcome! You must enter the id of the product you want. Then, program will show you how many of which sub-products you need to order over the 10-week period.");
                System.out.println("Here is the id numbers of our products:\n**************************************\nShovel Complete: 1605\nTop Handle 13122: 13122\nScoop-Shaft: 048\nShaft: 118\nNail: 062\nRivot: 14127\nScoop-Assembly: 314\nScoop: 2142\nBlade: 019\nTop Handle 457: 457\nBracelet: 11495\nTop Handle 129: 129\nTop Handle 1118: 1118");
                System.out.println("\n(P.S: If you want to log out, you should type 'EXIT'. Now, you can choose the product you want.)\n");
                System.out.println("Enter product id:");
                String input = scanner1.nextLine();

                int[] demand = new int[week.length];
                for (int i = 0; i < week.length; i++) {
                    System.out.println("Please enter the demand for week: " + (i + 1));
                    demand[i] = scanner.nextInt();
                }
                switch (input) {
                    case "EXIT":
                        //we need a command to exit the loop and terminate the application
                        flag = false;
                        break;
                    case "1605":
                        System.out.println("You selected shovel complete 1605:\n");
                        System.out.println("To produce the product 1605, you need the products with id 13122, 048 ,118, 062, 314, 14127, 457, 11495, 129, 1118, 2142 and 019.\n");

                        for (int i = 0; i < 10; i++) {
                            shovelComplete1605.grossRequirements[i] = demand[i];
                        }
                        System.out.println("------------------------------------------------");
                        System.out.println("Table of 1605 shovel complete");
                        int[] planned1605 = main.findPlanned(demand, shovelComplete1605.scheduledReceipts, shovelComplete1605.amountOnHand, shovelComplete1605.leadTime, shovelComplete1605.lotSizingRule, shovelComplete1605.netRequirements, shovelComplete1605.timePhased, shovelComplete1605.plannedRelease, shovelComplete1605.plannedDelivery);
                        System.out.println("------------------------------------------------");
                        System.out.println("Table of 13122 top handle: ");
                        System.out.println("------------------------------------------------");
                        int[] planned13122 = main.findPlanned(planned1605, topHandle13122.scheduledReceipts, topHandle13122.amountOnHand, topHandle13122.leadTime, topHandle13122.lotSizingRule, topHandle13122.netRequirements, topHandle13122.timePhased, topHandle13122.plannedRelease, topHandle13122.plannedDelivery);
                        System.out.println("------------------------------------------------");
                        System.out.println("Table for 048 shaft: ");
                        System.out.println("------------------------------------------------");
                        main.findPlanned(planned1605, scoopShaft048.scheduledReceipts, scoopShaft048.amountOnHand, scoopShaft048.leadTime, scoopShaft048.lotSizingRule, scoopShaft048.netRequirements, scoopShaft048.timePhased, scoopShaft048.plannedRelease, scoopShaft048.plannedDelivery);
                        System.out.println("------------------------------------------------");
                        System.out.println("Table for 118 shift: ");
                        System.out.println("------------------------------------------------");
                        main.findPlanned(planned1605, shift118.scheduledReceipts, shift118.amountOnHand, shift118.leadTime, shift118.lotSizingRule, shift118.netRequirements, shift118.timePhased, shift118.plannedRelease, shift118.plannedDelivery);
                        ;
                        System.out.println("------------------------------------------------");
                        System.out.println("Table for 062 nail: ");
                        System.out.println("------------------------------------------------");
                        int[] newArray = new int[10];
                        for (int i = 0; i < week.length; i++) {
                            newArray[i] = (planned13122[i] * 2) + (planned1605[i] * 4);
                        }
                        main.findPlanned(newArray, nail062.scheduledReceipts, nail062.amountOnHand, nail062.leadTime, nail062.lotSizingRule, nail062.netRequirements, nail062.timePhased, nail062.plannedRelease, nail062.plannedDelivery);
                        System.out.println("-----------------------------------------------");
                        System.out.println("Table for 314 scoop-assembly: ");
                        System.out.println("-----------------------------------------------");
                        int[] planned314 = main.findPlanned(planned1605, scoopAssembly314.scheduledReceipts, scoopAssembly314.amountOnHand, scoopAssembly314.leadTime, scoopAssembly314.lotSizingRule, scoopAssembly314.netRequirements, scoopAssembly314.timePhased, scoopAssembly314.plannedRelease, scoopAssembly314.plannedDelivery);
                        System.out.println("-----------------------------------------------");
                        System.out.println("Table for 14127 rivot: ");
                        System.out.println("-----------------------------------------------");
                        int[] newArray2 = new int[week.length];
                        for (int i = 0; i < week.length; i++) {
                            newArray2[i] = (planned1605[i] * 4) + (planned314[i] * 6);
                        }
                        main.findPlanned(newArray2, rivot14127.scheduledReceipts, rivot14127.amountOnHand, rivot14127.leadTime, rivot14127.lotSizingRule, rivot14127.netRequirements, rivot14127.timePhased, rivot14127.plannedRelease, rivot14127.plannedDelivery);
                        System.out.println("------------------------------------------------");
                        System.out.println("Table for 457 top handle: ");
                        System.out.println("------------------------------------------------");
                        main.findPlanned(planned13122, topHandle457.scheduledReceipts, topHandle457.amountOnHand, topHandle457.leadTime, topHandle457.lotSizingRule, topHandle457.netRequirements, topHandle457.timePhased, topHandle457.plannedRelease, topHandle457.plannedDelivery);
                        System.out.println("------------------------------------------------");
                        System.out.println("Table for 11495 bracelet: ");
                        System.out.println("------------------------------------------------");
                        int[] planned11495 = main.findPlanned(planned13122, bracelet11495.scheduledReceipts, bracelet11495.amountOnHand, bracelet11495.leadTime, bracelet11495.lotSizingRule, bracelet11495.netRequirements, bracelet11495.timePhased, bracelet11495.plannedRelease, bracelet11495.plannedDelivery);
                        System.out.println("------------------------------------------------");
                        System.out.println("Table for 129 top handle: ");
                        System.out.println("------------------------------------------------");
                        main.findPlanned(planned11495, topHandle129.scheduledReceipts, topHandle129.amountOnHand, topHandle129.leadTime, topHandle129.lotSizingRule, topHandle129.netRequirements, topHandle129.timePhased, topHandle129.plannedRelease, topHandle129.plannedDelivery);
                        System.out.println("------------------------------------------------");
                        System.out.println("Table for 1118 top handle: ");
                        System.out.println("------------------------------------------------");
                        main.findPlanned(planned11495, topHandle1118.scheduledReceipts, topHandle1118.amountOnHand, topHandle1118.leadTime, topHandle1118.lotSizingRule, topHandle1118.netRequirements, topHandle1118.timePhased, topHandle1118.plannedRelease, topHandle1118.plannedDelivery);
                        System.out.println("------------------------------------------------");
                        System.out.println("Table for 2142 scoop:");
                        System.out.println("------------------------------------------------");
                        main.findPlanned(planned314, scoop2142.scheduledReceipts, scoop2142.amountOnHand, scoop2142.leadTime, scoop2142.lotSizingRule, scoop2142.netRequirements, scoop2142.timePhased, scoop2142.plannedRelease, scoop2142.plannedDelivery);
                        System.out.println("------------------------------------------------");
                        System.out.println("Table for 019 blade:");
                        System.out.println("------------------------------------------------");
                        main.findPlanned(planned314, blade019.scheduledReceipts, blade019.amountOnHand, blade019.leadTime, blade019.lotSizingRule, blade019.netRequirements, blade019.timePhased, blade019.plannedRelease, blade019.plannedDelivery);
                        System.out.println("------------------------------------------------");
                        break;

                    case "13122":
                        System.out.println("You selected the top handle 13122:\n");
                        for (int i = 0; i < 10; i++) {
                            topHandle13122.grossRequirements[i] = demand[i];
                        }
                        System.out.println("To produce the product 13122, you need the products with the id 457, 062, 11495, 129 and 1118.\n");
                        System.out.println("Table of 13122 top handle: ");
                        System.out.println("-----------------------------------------------");
                        int[] planned13122x = main.findPlanned(demand, topHandle13122.scheduledReceipts, topHandle13122.amountOnHand, topHandle13122.leadTime, topHandle13122.lotSizingRule, topHandle13122.netRequirements, topHandle13122.timePhased, topHandle13122.plannedRelease, topHandle13122.plannedDelivery);
                        System.out.println("-----------------------------------------------");
                        System.out.println("Table for 457 top handle: ");
                        System.out.println("------------------------------------------------");
                        main.findPlanned(planned13122x, topHandle457.scheduledReceipts, topHandle457.amountOnHand, topHandle457.leadTime, topHandle457.lotSizingRule, topHandle457.netRequirements, topHandle457.timePhased, topHandle457.plannedRelease, topHandle457.plannedDelivery);
                        System.out.println("------------------------------------------------");
                        System.out.println("Table for 062 nail: ");
                        System.out.println("------------------------------------------------");
                        int[] newArray1 = new int[10];
                        for (int i = 0; i < week.length; i++) {
                            newArray1[i] = planned13122x[i] * 2;
                        }
                        main.findPlanned(newArray1, nail062.scheduledReceipts, nail062.amountOnHand, nail062.leadTime, nail062.lotSizingRule, nail062.netRequirements, nail062.timePhased, nail062.plannedRelease, nail062.plannedDelivery);
                        System.out.println("-----------------------------------------------");
                        System.out.println("Table for 11495 bracelet: ");
                        System.out.println("------------------------------------------------");
                        int[] planned11495x = main.findPlanned(planned13122x, bracelet11495.scheduledReceipts, bracelet11495.amountOnHand, bracelet11495.leadTime, bracelet11495.lotSizingRule, bracelet11495.netRequirements, bracelet11495.timePhased, bracelet11495.plannedRelease, bracelet11495.plannedDelivery);
                        System.out.println("------------------------------------------------");
                        System.out.println("Table for 129 top handle: ");
                        System.out.println("------------------------------------------------");
                        main.findPlanned(planned11495x, topHandle129.scheduledReceipts, topHandle129.amountOnHand, topHandle129.leadTime, topHandle129.lotSizingRule, topHandle129.netRequirements, topHandle129.timePhased, topHandle129.plannedRelease, topHandle129.plannedDelivery);
                        System.out.println("------------------------------------------------");
                        System.out.println("Table for 1118 top handle");
                        System.out.println("------------------------------------------------");
                        main.findPlanned(planned11495x, topHandle1118.scheduledReceipts, topHandle1118.amountOnHand, topHandle1118.leadTime, topHandle1118.lotSizingRule, topHandle1118.netRequirements, topHandle1118.timePhased, topHandle1118.plannedRelease, topHandle1118.plannedDelivery);
                        break;

                    case "048":
                        System.out.println("You selected Scoop-Shaft 048: ");
                        for (int i = 0; i < 10; i++) {
                            scoopShaft048.grossRequirements[i] = demand[i];
                        }
                        System.out.println("----------------------------------------------");
                        System.out.println("Table for 048 scoop-shaft: ");
                        System.out.println("----------------------------------------------");
                        main.findPlanned(demand, scoopShaft048.scheduledReceipts, scoopShaft048.amountOnHand, scoopShaft048.leadTime, scoopShaft048.lotSizingRule, scoopShaft048.netRequirements, scoopShaft048.timePhased, scoopShaft048.plannedRelease, scoopShaft048.plannedDelivery);
                        break;

                    case "118":
                        System.out.println("You selected the shaft:");
                        for (int i = 0; i < 10; i++) {
                            shift118.grossRequirements[i] = demand[i];
                        }
                        System.out.println("---------------------------------------------");
                        System.out.println("Table for 118 shaft: ");
                        System.out.println("---------------------------------------------");
                        main.findPlanned(demand, shift118.scheduledReceipts, shift118.amountOnHand, shift118.leadTime, shift118.lotSizingRule, shift118.netRequirements, shift118.timePhased, shift118.plannedRelease, shift118.plannedDelivery);

                        break;
                    case "062":
                        System.out.println("You selected the nail:");
                        for (int i = 0; i < 10; i++) {
                            nail062.grossRequirements[i] = demand[i];
                        }
                        System.out.println("---------------------------------------------");
                        System.out.println("Table for 062 nail: ");
                        System.out.println("---------------------------------------------");
                        main.findPlanned(demand, nail062.scheduledReceipts, nail062.amountOnHand, nail062.leadTime, nail062.lotSizingRule, nail062.netRequirements, nail062.timePhased, nail062.plannedRelease, nail062.plannedDelivery);
                        break;

                    case "14127":
                        System.out.println("You selected the rivot: ");
                        for (int i = 0; i < 10; i++) {
                            rivot14127.grossRequirements[i] = demand[i];
                        }
                        System.out.println("---------------------------------------------");
                        System.out.println("Table for 14127 rivot: ");
                        System.out.println("---------------------------------------------");
                        main.findPlanned(demand, rivot14127.scheduledReceipts, rivot14127.amountOnHand, rivot14127.leadTime, rivot14127.lotSizingRule, rivot14127.netRequirements, rivot14127.timePhased, rivot14127.plannedRelease, rivot14127.plannedDelivery);
                        break;
                    case "314":
                        System.out.println("You selected scoop-assembly 314:\n");
                        System.out.println("To produce the product 314, you need the products with the id 2142, 019 and 14127.\n");
                        for (int i = 0; i < 10; i++) {
                            scoopAssembly314.grossRequirements[i] = demand[i];
                        }
                        System.out.println("---------------------------------------------");
                        System.out.println("Table for 314 scoop assembly: ");
                        System.out.println("---------------------------------------------");
                        int[] planned314x = main.findPlanned(demand, scoopAssembly314.scheduledReceipts, scoopAssembly314.amountOnHand, scoopAssembly314.leadTime, scoopAssembly314.lotSizingRule, scoopAssembly314.netRequirements, scoopAssembly314.timePhased, scoopAssembly314.plannedRelease, scoopAssembly314.plannedDelivery);
                        System.out.println("---------------------------------------------");
                        System.out.println("Table for 2142 scoop: ");
                        System.out.println("---------------------------------------------");
                        main.findPlanned(planned314x, scoop2142.scheduledReceipts, scoop2142.amountOnHand, scoop2142.leadTime, scoop2142.lotSizingRule, scoop2142.netRequirements, scoop2142.timePhased, scoop2142.plannedRelease, scoop2142.plannedDelivery);
                        System.out.println("---------------------------------------------");
                        System.out.println("Table for 019 blade: ");
                        System.out.println("---------------------------------------------");
                        main.findPlanned(planned314x, blade019.scheduledReceipts, blade019.amountOnHand, blade019.leadTime, blade019.lotSizingRule, blade019.netRequirements, blade019.timePhased, blade019.plannedRelease, blade019.plannedDelivery);
                        System.out.println("---------------------------------------------");
                        System.out.println("Table for 14127 rivot: ");
                        System.out.println("---------------------------------------------");
                        int[] testArray = new int[week.length];
                        for (int i = 0; i < week.length; i++) {
                            testArray[i] = (planned314x[i] * 6);
                        }
                        main.findPlanned(testArray, rivot14127.scheduledReceipts, rivot14127.amountOnHand, rivot14127.leadTime, rivot14127.lotSizingRule, rivot14127.netRequirements, rivot14127.timePhased, rivot14127.plannedRelease, rivot14127.plannedDelivery);
                        System.out.println("---------------------------------------------");
                        break;
                    case "457":
                        System.out.println("You selected the top handle 457: ");
                        for (int i = 0; i < 10; i++) {
                            topHandle457.grossRequirements[i] = demand[i];
                        }
                        System.out.println("---------------------------------------------");
                        System.out.println("Table for 457 top handle: ");
                        System.out.println("---------------------------------------------");
                        main.findPlanned(demand, topHandle457.scheduledReceipts, topHandle457.amountOnHand, topHandle457.leadTime, topHandle457.lotSizingRule, topHandle457.netRequirements, topHandle457.timePhased, topHandle457.plannedRelease, topHandle457.plannedDelivery);
                        break;
                    case "11495":
                        System.out.println("You selected the bracelet 11495: ");
                        System.out.println("To produce the product 11495, you need the products with the id 129 and 1118.");
                        for (int i = 0; i < 10; i++) {
                            bracelet11495.grossRequirements[i] = demand[i];
                        }
                        System.out.println("---------------------------------------------");
                        System.out.println("Table for 11495 bracelet: ");
                        System.out.println("---------------------------------------------");
                        int[] planned11495y = main.findPlanned(demand, bracelet11495.scheduledReceipts, bracelet11495.amountOnHand, bracelet11495.leadTime, bracelet11495.lotSizingRule, bracelet11495.netRequirements, bracelet11495.timePhased, bracelet11495.plannedRelease, bracelet11495.plannedDelivery);
                        System.out.println("---------------------------------------------");
                        System.out.println("Table for 129 top handle: ");
                        System.out.println("---------------------------------------------");
                        main.findPlanned(planned11495y, topHandle129.scheduledReceipts, topHandle129.amountOnHand, topHandle129.leadTime, topHandle129.lotSizingRule, topHandle129.netRequirements, topHandle129.timePhased, topHandle129.plannedRelease, topHandle129.plannedDelivery);
                        System.out.println("---------------------------------------------");
                        System.out.println("Table for 1118 top handle: ");
                        System.out.println("---------------------------------------------");
                        main.findPlanned(planned11495y, topHandle1118.scheduledReceipts, topHandle1118.amountOnHand, topHandle1118.leadTime, topHandle1118.lotSizingRule, topHandle1118.netRequirements, topHandle1118.timePhased, topHandle1118.plannedRelease, topHandle1118.plannedDelivery);
                        System.out.println("---------------------------------------------");
                        break;
                    case "129":
                        System.out.println("You selected top handle 129: ");
                        for (int i = 0; i < 10; i++) {
                            topHandle129.grossRequirements[i] = demand[i];
                        }
                        System.out.println("---------------------------------------------");
                        System.out.println("Table for 129 top handle: ");
                        System.out.println("---------------------------------------------");
                        main.findPlanned(demand, topHandle129.scheduledReceipts, topHandle129.amountOnHand, topHandle129.leadTime, topHandle129.lotSizingRule, topHandle129.netRequirements, topHandle129.timePhased, topHandle129.plannedRelease, topHandle129.plannedDelivery);
                        break;
                    case "1118":
                        System.out.println("You selected the top handle 1118: ");
                        for (int i = 0; i < 10; i++) {
                            topHandle1118.grossRequirements[i] = demand[i];
                        }
                        System.out.println("---------------------------------------------");
                        System.out.println("Table for 1118 top handle: ");
                        System.out.println("---------------------------------------------");
                        main.findPlanned(demand, topHandle1118.scheduledReceipts, topHandle1118.amountOnHand, topHandle1118.leadTime, topHandle1118.lotSizingRule, topHandle1118.netRequirements, topHandle1118.timePhased, topHandle1118.plannedRelease, topHandle1118.plannedDelivery);
                        break;
                    case "2142":
                        System.out.println("You selected the scoop 2142: ");
                        for (int i = 0; i < 10; i++) {
                            scoop2142.grossRequirements[i] = demand[i];
                        }
                        System.out.println("---------------------------------------------");
                        System.out.println("Table for 2142 scoop: ");
                        System.out.println("---------------------------------------------");
                        main.findPlanned(demand, scoop2142.scheduledReceipts, scoop2142.amountOnHand, scoop2142.leadTime, scoop2142.lotSizingRule, scoop2142.netRequirements, scoop2142.timePhased, scoop2142.plannedRelease, scoop2142.plannedDelivery);
                        break;
                    case "019":
                        System.out.println("You selected the blade 019: ");
                        for (int i = 0; i < 10; i++) {
                            blade019.grossRequirements[i] = demand[i];
                        }
                        System.out.println("---------------------------------------------");
                        System.out.println("Table for 019 blade: ");
                        System.out.println("---------------------------------------------");
                        main.findPlanned(demand, blade019.scheduledReceipts, blade019.amountOnHand, blade019.leadTime, blade019.lotSizingRule, blade019.netRequirements, blade019.timePhased, blade019.plannedRelease, blade019.plannedDelivery);
                        break;
                    default:
                        System.out.println("Unsupported command. Please enter one of the required commands.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static class ShovelComplete1605 {
        int[] grossRequirements = new int[10];
        int[] amountOnHand = new int[10];
        int[] scheduledReceipts = new int[10];
        int leadTime = 0;
        String lotSizingRule = null;
        int[] netRequirements = new int[10];
        int[] timePhased = new int[10];
        int[] plannedRelease = new int[10];
        int[] plannedDelivery = new int[10];
    }

    public static class TopHandle13122 {
        int[] grossRequirements = new int[10];
        int[] amountOnHand = new int[10];
        int[] scheduledReceipts = new int[10];
        int leadTime = 0;
        String lotSizingRule = null;
        int[] netRequirements = new int[10];
        int[] timePhased = new int[10];
        int[] plannedRelease = new int[10];
        int[] plannedDelivery = new int[10];
    }

    public static class ScoopShaft048 {
        int[] grossRequirements = new int[10];
        int[] amountOnHand = new int[10];
        int[] scheduledReceipts = new int[10];
        int leadTime = 0;
        String lotSizingRule = null;
        int[] netRequirements = new int[10];
        int[] timePhased = new int[10];
        int[] plannedRelease = new int[10];
        int[] plannedDelivery = new int[10];
    }

    public static class Shift118 {
        int[] grossRequirements = new int[10];
        int[] amountOnHand = new int[10];
        int[] scheduledReceipts = new int[10];
        int leadTime = 0;
        String lotSizingRule = null;
        int[] netRequirements = new int[10];
        int[] timePhased = new int[10];
        int[] plannedRelease = new int[10];
        int[] plannedDelivery = new int[10];
    }

    public static class Nail062 {
        int[] grossRequirements = new int[10];
        int[] amountOnHand = new int[10];
        int[] scheduledReceipts = new int[10];
        int leadTime = 0;
        String lotSizingRule = null;
        int[] netRequirements = new int[10];
        int[] timePhased = new int[10];
        int[] plannedRelease = new int[10];
        int[] plannedDelivery = new int[10];
    }

    public static class Rivot14127 {
        int[] grossRequirements = new int[10];
        int[] amountOnHand = new int[10];
        int[] scheduledReceipts = new int[10];
        int leadTime = 0;
        String lotSizingRule = null;
        int[] netRequirements = new int[10];
        int[] timePhased = new int[10];
        int[] plannedRelease = new int[10];
        int[] plannedDelivery = new int[10];
    }

    public static class ScoopAssembly314 {
        int[] grossRequirements = new int[10];
        int[] amountOnHand = new int[10];
        int[] scheduledReceipts = new int[10];
        int leadTime = 0;
        String lotSizingRule = null;
        int[] netRequirements = new int[10];
        int[] timePhased = new int[10];
        int[] plannedRelease = new int[10];
        int[] plannedDelivery = new int[10];
    }

    public static class TopHandle457 {
        int[] grossRequirements = new int[10];
        int[] amountOnHand = new int[10];
        int[] scheduledReceipts = new int[10];
        int leadTime = 0;
        String lotSizingRule = null;
        int[] netRequirements = new int[10];
        int[] timePhased = new int[10];
        int[] plannedRelease = new int[10];
        int[] plannedDelivery = new int[10];
    }

    public static class Bracelet11495 {
        int[] grossRequirements = new int[10];
        int[] amountOnHand = new int[10];
        int[] scheduledReceipts = new int[10];
        int leadTime = 0;
        String lotSizingRule = null;
        int[] netRequirements = new int[10];
        int[] timePhased = new int[10];
        int[] plannedRelease = new int[10];
        int[] plannedDelivery = new int[10];
    }

    public static class TopHandle129 {
        int[] grossRequirements = new int[10];
        int[] amountOnHand = new int[10];
        int[] scheduledReceipts = new int[10];
        int leadTime = 0;
        String lotSizingRule = null;
        int[] netRequirements = new int[10];
        int[] timePhased = new int[10];
        int[] plannedRelease = new int[10];
        int[] plannedDelivery = new int[10];
    }

    public static class TopHandle1118 {
        int[] grossRequirements = new int[10];
        int[] amountOnHand = new int[10];
        int[] scheduledReceipts = new int[10];
        int leadTime = 0;
        String lotSizingRule = null;
        int[] netRequirements = new int[10];
        int[] timePhased = new int[10];
        int[] plannedRelease = new int[10];
        int[] plannedDelivery = new int[10];
    }

    public static class Scoop2142 {
        int[] grossRequirements = new int[10];
        int[] amountOnHand = new int[10];
        int[] scheduledReceipts = new int[10];
        int leadTime = 0;
        String lotSizingRule = null;
        int[] netRequirements = new int[10];
        int[] timePhased = new int[10];
        int[] plannedRelease = new int[10];
        int[] plannedDelivery = new int[10];
    }

    public static class Blade019 {
        int[] grossRequirements = new int[10];
        int[] amountOnHand = new int[10];
        int[] scheduledReceipts = new int[10];
        int leadTime = 0;
        String lotSizingRule = null;
        int[] netRequirements = new int[10];
        int[] timePhased = new int[10];
        int[] plannedRelease = new int[10];
        int[] plannedDelivery = new int[10];
    }
}
