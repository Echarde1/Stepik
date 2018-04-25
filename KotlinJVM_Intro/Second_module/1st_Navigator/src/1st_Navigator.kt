
import kotlin.math.abs

//val scan = Scanner(System.`in`)
//val scan = Scanner(File("/home/danil/Documents/JavaProjects/Stepik/KotlinJVM_Intro/src/input.txt"))

enum class Direction {
    UP, RIGHT, DOWN, LEFT
}

fun main(args: Array<String>) {
    val path = arrayOf(Direction.UP, Direction.LEFT, Direction.LEFT, Direction.UP, Direction.LEFT, Direction.UP, Direction.RIGHT, Direction.UP, Direction.UP, Direction.UP, Direction.RIGHT)
    val radius = 5
    println(Navigator().checkPath(path, radius))
}

class Navigator {
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
