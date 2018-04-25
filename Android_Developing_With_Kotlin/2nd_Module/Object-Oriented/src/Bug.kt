import java.util.*

fun main(args: Array<String>) {
    print(calculateWordStat(readLine().toString()))
}

fun calculateWordStat(input:String): String{
    /*val words = input.split(" ")
    val map = mutableMapOf<String, Int>()
    for(i in words) {
        if (map.containsKey(i)) map[i] = map[i]!!.inc()
        else map[i] = 1
    }
    return map.maxBy { entry -> entry.value }!!.key*/
    return input.split(" ")
            .groupBy { it }
            .mapValues { it.value.size }
            .maxBy { it.value }
            ?.key!!
}
