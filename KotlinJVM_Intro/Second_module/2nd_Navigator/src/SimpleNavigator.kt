/*
import kotlin.math.abs

class SimpleNavigator : Navigator(50) {
    fun checkPath(path: Array<Direction>, radius: Int): Boolean {
        var x = 0
        var y = 0
        for(i in 0 until path.size) {
            if (abs(x) > radius && abs(y) > radius) return false
            else if (path[i] == Direction.UP) y++
            else if (path[i] == Direction.DOWN) y--
            else if (path[i] == Direction.LEFT) x--
            else if (path[i] == Direction.RIGHT) x++
        }
        return abs(x) <= radius && abs(y) <= radius
    }
}
*/
