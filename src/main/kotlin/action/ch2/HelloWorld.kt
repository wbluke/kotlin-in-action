package action.ch2

import action.ch2.Color.*
import java.io.BufferedReader
import java.util.*

/**
 * 함수를 최상위 수준에 정의할 수 있다.
 * 배열도 일반적인 클래스다.
 * 자바 표준 라이브러리를 래핑한 코틀린 표준 라이브러리를 제공한다.
 * 세미콜론이 필요없다.
 */
fun main(args: Array<String>) {
    println("Hello, world!")
}

/**
 * 문(statement)
 * - 자신을 둘러싸고 있는 가장 안쪽 블록의 최상위 요소로 존재하며 아무런 값을 만들어내지 않는다.
 *
 * 식(expression)
 * - 값을 만들어 내며 다른 식의 하위 요소로 계산에 참여할 수 있다.
 *
 * 코틀린에서 if는 식이다.
 *
 *
 * 블록이 본문인 함수
 * - 본문이 중괄호로 둘러싸인 함수
 * 식이 본문인 함수
 * - 등호와 식으로 이뤄진 함수
 * - 식이 본문인 함수의 반환 타입만 생략 가능하다.
 *
 * IDE 에서 서로 변환하는 기능을 제공한다.
 */
fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

fun max2(a: Int, b: Int): Int = if (a > b) a else b

/**
 * 값 객체
 */
class Person(
    val name: String, // 비공개 필드, getter
    var isMarried: Boolean // 비공개 필드, getter, setter
)

fun printPersonName() {
    val person = Person("nick", false)
    println(person.name)
}

/**
 * 커스텀 접근자
 */
class Rectangle(private val height: Int, private val width: Int) { // 프로퍼티를 private으로 만들면 getter도 없는 형태다.
    val isSquare: Boolean
        get() = height == width
}

/**
 * Enum
 */
enum class Color {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}

fun getWarmth(color: Color) {
    when (color) {
        RED, ORANGE, YELLOW -> "warm"
        GREEN -> "neutral"
        BLUE, INDIGO, VIOLET -> "cold"
    }
}

fun mix(c1: Color, c2: Color) {
    when (setOf(c1, c2)) {
        setOf(RED, YELLOW) -> ORANGE
        setOf(YELLOW, BLUE) -> GREEN
        setOf(BLUE, VIOLET) -> INDIGO
        else -> throw Exception("Dirty color")
    }
}

/**
 * 스마트 캐스트
 */
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int =
    if (e is Num) {
        e.value
    } else if (e is Sum) {
        eval(e.right) + eval(e.left)
    } else {
        throw IllegalArgumentException("Unknown expression")
    }

fun eval2(e: Expr): Int =
    when (e) {
        is Num -> e.value
        is Sum -> eval(e.right) + eval(e.left)
        else -> throw IllegalArgumentException("Unknown expression")
    }

/**
 * 범위와 수열
 */
fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz"
    i % 3 == 0 -> "Fizz"
    i % 5 == 0 -> "Buzz"
    else -> "$i"
}

fun FizzBuzz100() {
    for (i in 1..100) {
        print(fizzBuzz(i))
    }
}

fun downFizzBuzz() {
    for (i in 100 downTo 1 step 2) {
        print(fizzBuzz(i))
    }
}

/**
 * 맵 이터레이션
 */
fun iterBinaryMap() {
    val binaryReps = TreeMap<Char, String>()
    for (c in 'A'..'F') {
        val binary = Integer.toBinaryString(c.toInt())
        binaryReps[c] = binary
    }
    for ((letter, binary) in binaryReps) {
        println("$letter = $binary")
    }
}

/**
 * try도 식이다.
 * 다만, try의 본문을 반드시 중괄호로 감싸야 한다.
 */
fun readNumber(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        null
    }

    println(number)
}
