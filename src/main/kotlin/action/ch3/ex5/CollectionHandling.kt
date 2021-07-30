package action.ch3.ex5

/**
 * 컬렉션 처리 1
 *
 * 가변 인자 함수 : 인자의 개수가 달라질 수 있는 함수 정의
 * 자바처럼 타입 뒤에 ...를 붙이는 것 대신 vararg 변경자를 붙인다.
 * fun listOf<T>(vararg values: T): List<T> { ... }
 *
 * 이미 배열에 들어있는 원소를 가변 길이 인자로 넘길 때도
 * 자바에서는 배열을 그냥 넘기면 되지만 코틀린에서는 배열을 명시적으로 풀어서 배열의 각 원소가 인자로 전달되게 해야 한다.
 * 스프레드 연산자(*) 를 사용하면 된다.
 */
fun printList(args: Array<String>) {
    val list = listOf("args: ", *args)
    println(list)
}

/**
 * 컬렉션 처리 2
 *
 * 값의 쌍 다루기 : 중위 호출과 구조 분해 선언
 * map을 만들 때 봤던 to는 코틀린 키워드가 아니라 중위 호출이라는 특별한 방식으로 호출한 to 일반 메서드이다.
 * 중위 호출 시에는 수신 객체와 유일한 메서드 인자 사이에 메서드 이름을 넣는다.
 */
val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")

/**
 * 인자가 하나뿐인 일반 메서드나 인자가 하나뿐인 확장 함수에 중위 호출을 사용할 수 있다.
 * 함수를 중위 호출에 사용하게 허용하고 싶으면 infix 변경자를 함수 선언 앞에 추가해야 한다.
 *
 * infix fun Any.to(other: Any) = Pair(this, other) // Pair 는 코틀린 표준 라이브러리 클래스로, 두 순서쌍을 표현한다.
 */
fun main() {
    val (number, name) = 1 to "one" // 구조 분해 선언
}
