package programs;

import com.battle.heroes.army.Army;
import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.GeneratePreset;

import java.util.*;

public class GeneratePresetImpl implements GeneratePreset {

    @Override
    public Army generate(List<Unit> unitList, int maxPoints) {
        Army army = new Army();
        List<Unit> units = army.getUnits();

        Map<String, Unit> templates = new HashMap<>();
        for (Unit u : unitList) {
            templates.put(u.getUnitType(), u);
        }

        List<String> types = Arrays.asList("всадник", "лучник", "мечник", "копейщик");

        for (String type : types) {
            Unit template = templates.get(type);
            if (template == null) continue;

            int cost = template.getCost();
            int count = Math.min(11, maxPoints / cost);

            for (int i = 0; i < count; i++) {
                Unit newUnit = new Unit();
                newUnit.setUnitType(template.getUnitType());
                newUnit.setName(template.getUnitType() + " " + (i + 1));
                newUnit.setHealth(template.getHealth());
                newUnit.setBaseAttack(template.getBaseAttack());
                newUnit.setCost(template.getCost());
                newUnit.setAttackType(template.getAttackType());
                newUnit.setAlive(true);

                units.add(newUnit);
            }
        }

        return army;
    }
}