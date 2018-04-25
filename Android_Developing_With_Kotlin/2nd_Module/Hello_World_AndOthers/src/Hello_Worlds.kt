import java.util.*

val scan = Scanner(System.`in`)

fun main(args : Array<String>) {
    println(getYearEra(2047) == "after (XXI century)")
    println(getYearEra(1952) == "before")
    println(getYearEra(2108) == "distant future")
    println(getYearEra(1913) == "before")
    println(getYearEra(1986) == "after (XX century)")
    println()
    println(calculateEvenDigits("660308340") == 24)
    println()
    val str: String = "KLPQYM BUG KHQR KLPQYM JVOJTD JVOJTD BUG BUG KHQR BUG KLPQYM JVOJTD KLPQYM JVOJTD QLSPA KHQR JVOJTD KLPQYM JVOJTD KLPQYM KLPQYM JVOJTD JVOJTD BUG KLPQYM KHQR JVOJTD KHQR KLPQYM KLPQYM JVOJTD KLPQYM BUG BUG BUG"
    val list = str.split(" ")
    println(calculateBugMentions(list) == 8)
}

fun getYearEra(year: Int) = when {
    year < 1970 -> "before"
    year == 1970 -> "equals"
    year in 1971..1999 -> "after (XX century)"
    year in 2000..2099 -> "after (XXI century)"
    else -> "distant future"
}

fun calculateEvenDigits(input:String): Int{
    var count = 0
    for(c in input) {
        val temp = c.toString().toInt()
        if (temp % 2 == 0) count += temp
    }
    return count
}

fun calculateBugMentions(input:List<String>): Int{
    var count = 0
    input.forEach { str -> if (str == "BUG") count++ }
    return count
}
