package action.ch3.ex2.strings

/**
 * 최상위 함수 선언
 * 고로 자바에서 주로 생성하는 유틸 클래스가 필요 없다.
 * 자바에서 사용할 때는 컴파일러가
 */
fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

/**
 * [자바로 변환]
 *
 * package action.ch3.ex2.strings;
 *
 * public class JoinKt {
 *     public static String joinToString(...) { ... }
 * }
 */

/**
 * 최상위 프로퍼티
 * const 선언 -> public static final 로 변환
 * const 변경자는 원시 타입과 String 타입의 프로퍼티에만 선언할 수 있다.
 */
const val UNIX_LINE_SEPARATOR = "\n"
