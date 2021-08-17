package action.ch8.ex2

import java.util.concurrent.locks.Lock

/**
 * 인라인 함수
 *
 * 람다를 활용한 코드의 성능은?
 * 람다 식을 사용할 때마다 새로운 클래스가 생성되지는 않고,
 * 람다가 변수를 포획한 경우에만 새로운 무명 클래스 객체가 생성된다.
 *
 * inline 변경자를 함수에 붙이면
 * 컴파일러는 그 함수를 호출하는 모든 문장을 함수 본문에 해당하는 바이트코드로 바꿔치기 해준다.
 */
inline fun <T> synchronized(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action() // action 앞 뒤의 코드가 호출 부분에 바이트코드로 들어간다.
    } finally {
        lock.unlock()
    }
}

/**
 * 인라인 함수의 한계
 *
 * 파라미터로 받은 람다를 다른 변수에 저장하고 나중에 그 변수를 사용한다면
 * 람다를 표현하는 객체가 어딘가는 존재해야 하기 때문에 람다를 인라이닝할 수 없다.
 *
 * 일반적으로 인라인 함수의 본문에서 람다 식을 바로 호출하거나
 * 람다 식을 인자로 전달받아 바로 호출하는 경우 그 람다를 인라이닝할 수 있다.
 *
 * 인라이닝하면 안 되는 람다를 파라미터를 받을 경우에는 noinline 변경자를 사용할 수 있다.
 */

/**
 * 컬렉션 연산 인라이닝
 *
 * filter, map 등은 인라인 함수다.
 * 이를 사용하면 걸러낸 결과를 저장하는 중간 리스트를 만든다.
 *
 * 시퀀스를 사용하면 중간 리스트로 인한 부가 비용은 줄어든다.
 * 다만 시퀀스는 람다를 저장해야 하므로 람다를 인라인하지 않는다.
 * 따라서 지연 계산을 통해 성능을 향상시키려는 이유로 모든 컬렉션 연산에 asSequence를 붙여서는 안 된다.
 * 크기가 작은 컬렉션은 오히려 일반 컬렉션 연산이 더 성능이 나을 수도 있다.
 */

/**
 * 함수를 인라인으로 선언해야 하는 경우
 *
 * inline 키워드를 사용해도 람다를 인자로 받는 함수만 성능이 좋아질 가능성이 높다.
 *
 * 일반 함수의 경우 JVM은 이미 강력하게 인라이닝을 지원한다.
 * JVM의 최적화를 활용한다면 바이트코드에서는 각 함수 구현이 정확히 한 번만 있으면 되고,
 * 그 함수를 호출하는 부분에서 따로 함수 코드를 중복할 필요가 없다.
 * 반면 코틀린 인라인 함수는 바이트코드에서 각 함수 호출 지점을 함수 본문으로 대체하기 때문에 코드 중복이 생긴다.
 * 게다가 함수를 직접 호출하면 스택 트레이스가 더 깔끔하다.
 *
 * 람다를 인자로 받는 함수를 인라이닝하면 이익이 더 많다.
 * 1. 함수 호출 비용, 람다를 표현하는 클래스, 람다 인스턴스 객체 생성 비용이 없어진다.
 * 2. 현재의 JVM은 함수 호출과 람다를 인라이닝해 줄 정도로 똑똑하지는 못하다.
 * 3. 일반 람다에서는 사용할 수 없는 몇 가지 기능(ex. non-local 반환)을 지원한다.
 *
 * 단, 인라이닝하는 함수가 큰 경우 바이트코드가 전체적으로 아주 커질 수 있기 때문에 주의해야 한다.
 */

/**
 * 인라인된 람다를 사용한 자원 관리
 *
 * try 에서 자원을 획득, finally 에서 자원을 해제
 * withLock() 함수
 * use() 함수 - 닫을 수 있는(Closeable) 자원에 대한 확장 함수, 람다를 인자로 받는다.
 */

/**
 * 고차 함수 안에서 흐름 제어
 *
 * 람다 안에서 return을 사용하면 람다로부터만 반환되는 게 아니라 그 람다를 호출하는 함수가 실행을 끝내고 반환된다.
 * 이를 non-local return이라 한다.
 * return이 바깥쪽 함수를 반환시킬 수 있는 때는 람다를 인자로 받는 함수가 인라인 함수인 경우뿐이다.
 */
class Person(val name: String)

fun lookForAlice(people: List<Person>) {
    people.forEach {
        if (it.name == "Alice") {
            println("Found!")
            return // forEach 뿐만 아니라 외부 함수 종료
        }
    }
    println("Alice is not found")
}

/**
 * 레이블을 사용한 return
 *
 * 람다 식에서도 로컬 return을 사용할 수 있다.
 * 레이블을 사용하면 된다.
 */
fun lookForAlice2(people: List<Person>) {
    people.forEach label@{
        if (it.name == "Alice") return@label // 앞에서 정의한 레이블 참조. local return
    }
    println("Alice might be somewhere")
}

/**
 * 함수의 이름을 레이블로 사용할 수도 있다.
 */
fun lookForAlice3(people: List<Person>) {
    people.forEach {
        if (it.name == "Alice") return@forEach
    }
    println("Alice might be somewhere")
}

/**
 * 무명 함수는 기본적으로 local return이다.
 *
 * 사실 return에 적용되는 규칙은 단순히 fun 키워드를 사용해 정의된 가장 안쪽 함수를 반환시킨다는 점이다.
 */
fun lookForAlice4(people: List<Person>) {
    people.forEach(fun(person) { // 무명 함수
        if (person.name == "Alice") return // 기본 local return
        println("${person.name} is not Alice")
    })
}
