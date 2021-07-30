package action.ch3.ex3

/**
 * 확장 함수와 확장 프로퍼티
 *
 * String : 수신 객체 타입 (receiver type)
 * 확장 함수가 호출되는 대상(this) : 수신 객체 (receiver object)
 */
fun String.lastChar(): Char = this[this.length - 1]

/**
 * 확장 함수를 사용하기 위해서는 import 해야 한다.
 * 이름이 같아서 충돌이 날 경우 as 키워드로 이름을 바꿔서 import 할 수 있다.
 */

/**
 * 내부적으로 확장 함수는 수신 객체를 첫 번째 인자로 받는 정적 메서드다.
 */

/**
 * joinToString() 의 최종 버전
 */
fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

fun main() {
    println("Kotlin".lastChar()) // n

    val list = arrayListOf(1, 2, 3)
    println(list.joinToString(" ")) // 1 2 3
}

