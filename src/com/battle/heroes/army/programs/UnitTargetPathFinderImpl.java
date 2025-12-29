package programs;

import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.Edge;
import com.battle.heroes.army.programs.UnitTargetPathFinder;

import java.util.*;

public class UnitTargetPathFinderImpl implements UnitTargetPathFinder {

    @Override
    public List<Edge> getTargetPath(Unit attackUnit, Unit targetUnit, List<Unit> existingUnitList) {
        int sx = attackUnit.getX();
        int sy = attackUnit.getY();
        int tx = targetUnit.getX();
        int ty = targetUnit.getY();

        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        Queue<int[]> queue = new ArrayDeque<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();

        String start = sx + "," + sy;
        queue.add(new int[]{sx, sy});
        visited.add(start);
        parent.put(start, null);

        boolean found = false;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            if (x == tx && y == ty) {
                found = true;
                break;
            }

            for (int d = 0; d < 8; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 0 && nx < 27 && ny >= 0 && ny < 21) {
                    String key = nx + "," + ny;

                    boolean occupied = false;
                    for (Unit u : existingUnitList) {
                        if (u.isAlive() && u.getX() == nx && u.getY() == ny) {
                            occupied = true;
                            break;
                        }
                    }

                    if (!visited.contains(key) && !occupied) {
                        visited.add(key);
                        queue.add(new int[]{nx, ny});
                        parent.put(key, x + "," + y);
                    }
                }
            }
        }

        if (!found) return new ArrayList<>();

        List<Edge> path = new ArrayList<>();
        String curr = tx + "," + ty;
        while (curr != null) {
            String[] p = curr.split(",");
            path.add(new Edge(Integer.parseInt(p[0]), Integer.parseInt(p[1])));
            curr = parent.get(curr);
        }
        Collections.reverse(path);
        return path;
    }
}