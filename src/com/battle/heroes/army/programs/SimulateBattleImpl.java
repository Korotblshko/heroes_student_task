package programs;

import com.battle.heroes.army.Army;
import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.PrintBattleLog;
import com.battle.heroes.army.programs.SimulateBattle;

import java.util.ArrayList;
import java.util.List;

public class SimulateBattleImpl implements SimulateBattle {

    private PrintBattleLog printBattleLog;

    @Override
    public void simulate(Army playerArmy, Army computerArmy) throws InterruptedException {
        List<Unit> allUnits = new ArrayList<>();
        allUnits.addAll(playerArmy.getUnits());
        allUnits.addAll(computerArmy.getUnits());

        while (true) {
            boolean playerAlive = false;
            for (Unit u : playerArmy.getUnits()) {
                if (u.isAlive()) playerAlive = true;
            }
            boolean computerAlive = false;
            for (Unit u : computerArmy.getUnits()) {
                if (u.isAlive()) computerAlive = true;
            }
            if (!playerAlive || !computerAlive) break;

            List<Unit> turnOrder = new ArrayList<>();
            for (Unit u : allUnits) {
                if (u.isAlive()) turnOrder.add(u);
            }

            turnOrder.sort((a, b) -> b.getBaseAttack() - a.getBaseAttack());

            for (Unit unit : turnOrder) {
                if (!unit.isAlive()) continue;

                Unit target = unit.getProgram().attack();

                if (target != null) {
                    printBattleLog.printBattleLog(unit, target);
                }

                Thread.sleep(50);
            }
        }
    }
}