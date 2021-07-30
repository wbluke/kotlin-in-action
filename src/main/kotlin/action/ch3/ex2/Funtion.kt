package action.ch3.ex2

/**
 * 함수를 호출하기 쉽게 만들기
 */
fun <T> joinToString(
    collection: Collection<T>,
    separator: String,
    prefix: String,
    postfix: String
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

fun main() {
    val list = listOf(1, 2, 3)
    println(action.ch3.ex2.strings.joinToString(list, ";", "(", ")")) // 파라미터 구분이 쉽지 않다.

    // 파라미터 이름 지정
    println(action.ch3.ex2.strings.joinToString(list, separator = " ", prefix = " ", postfix = "."))
    println(action.ch3.ex2.strings.joinToString(list, postfix = ".", prefix = " ", separator = " ")) // 이름을 지정하면 순서를 바꿔도 된다. Amazing !
}
