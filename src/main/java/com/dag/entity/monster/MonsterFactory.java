package com.dag.entity.monster;

import com.dag.ZeldaPlayWindow;
import com.dag.map.Map;
import org.pmw.tinylog.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

@SuppressWarnings("ALL")
public final class MonsterFactory {

    private static final int MAX_FILE_LENGTH = 362;
    private static final int END_OF_TILE_DATA_LINE = 351;
    private static final int START_OF_MONSTER_DATA_LINE = 352;
    private static final int MAX_TILE_DATA = 352;

    private final String[] monString = new String[10];
    private final Random r = new Random();

    public MonsterFactory() {
        Logger.trace("Made a monster factory");
    }

    public void monLoad(final Map theMap, final Monster[] theMonster, final ZeldaPlayWindow playWindow) {
        Logger.trace("Enter modLoad");
        theMap.setIsLoading(true);
        int[] tileData = new int[MAX_TILE_DATA];
        String fileName = "src/main/resources/sprites/maps/" + theMap.x + " " + theMap.y + ".txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            for (int i = 0; i < MAX_FILE_LENGTH; i++) {
                if (i <= END_OF_TILE_DATA_LINE) {
                    tileData[i] = Integer.parseInt(br.readLine());
                    Logger.debug("Loaded some tile data: " + tileData[i]);
                } else {
                    monString[i - START_OF_MONSTER_DATA_LINE] = br.readLine();
                    Logger.debug("Loaded some monster data: " + monString[i - START_OF_MONSTER_DATA_LINE]);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.error("ZPL FileNot Found :" + ex);
        } catch (IOException ex) {
            Logger.error("IOException occurred: " + ex);
        }
        for (int i = 0; i < 10; i++) {
            if (theMonster[i] == null) {
                int x;
                int y;
                do {
                    x = r.nextInt(14) + 1;
                    y = r.nextInt(9) + 1;
                } while (theMap.walkable[(y * 16) + x] != 1);

                theMonster[i] = MonsterFactory.chooseMonster(x, y, monString[i], i, playWindow);
                (new Thread(theMonster[i])).start();
            }
        }
        Logger.trace("Exit monLoad");
        theMap.setIsLoading(false);
    }

    public static final Monster chooseMonster(int monX, int monY, String theMonString, int theNum, ZeldaPlayWindow playWindow) {
        Logger.debug("Loading a monster: " + theMonString);
        switch (theMonString) {
            case "redoctorok":
                return new RedOctorok(monX, monY, playWindow, theNum);
            case "blueoctorok":
                return new BlueOctorok(monX, monY, playWindow, theNum);
            case "reddarknut":
                return new RedDarknut(monX, monY, playWindow, theNum);
            case "bluedarknut":
                return new BlueDarknut(monX, monY, playWindow, theNum);
            case "bluegoriya":
                return new BlueGoriya(monX, monY, playWindow, theNum);
            case "redgoriya":
                return new RedGoriya(monX, monY, playWindow, theNum);
            case "redmoblin":
                return new RedMoblin(monX, monY, playWindow, theNum);
            case "bluemoblin":
                return new BlueMoblin(monX, monY, playWindow, theNum);
            case "bluelynel":
                return new BlueLynel(monX, monY, playWindow, theNum);
            case "redlynel":
                return new RedLynel(monX, monY, playWindow, theNum);
            default:
                return null;
        }
    }
}
