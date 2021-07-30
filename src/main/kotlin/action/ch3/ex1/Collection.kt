package action.ch3.ex1

/**
 * 컬렉션
 */
val set = hashSetOf(1, 7, 53)
val list = arrayListOf(1, 7, 53)
val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three") // to 는 언어가 제공하는 키워드가 아니라 일반 함수다.

fun printClass() {
    println(set.javaClass)
    println(list.javaClass)
    println(map.javaClass)
    // javaClass 는 getClass()에 해당하는 코틀린 코드
}

fun collectionFeature() {
    println(set.last())
    println(list.maxOrNull())
}
