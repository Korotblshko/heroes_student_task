package programs;

import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.SuitableForAttackUnitsFinder;

import java.util.ArrayList;
import java.util.List;

public class SuitableForAttackUnitsFinderImpl implements SuitableForAttackUnitsFinder {

    @Override
    public List<Unit> getSuitableUnits(List<List<Unit>> unitsByRow, boolean isLeftArmyTarget) {
        List<Unit> suitable = new ArrayList<>();

        for (List<Unit> row : unitsByRow) {
            if (row == null || row.isEmpty()) continue;

            Unit candidate = null;

            if (isLeftArmyTarget) {
                for (Unit unit : row) {
                    if (unit.isAlive()) {
                        if (candidate == null || unit.getY() < candidate.getY()) {
                            candidate = unit;
                        }
                    }
                }
            } else {
                for (Unit unit : row) {
                    if (unit.isAlive()) {
                        if (candidate == null || unit.getY() > candidate.getY()) {
                            candidate = unit;
                        }
                    }
                }
            }

            if (candidate != null) {
                suitable.add(candidate);
            }
        }

        return suitable;
    }
}