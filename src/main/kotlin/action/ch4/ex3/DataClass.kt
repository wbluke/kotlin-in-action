package action.ch4.ex3

/**
 * 코틀린에서 == 연산자는 자바의 equals 와 같다. (equals 보다 == 을 권장)
 * 자바의 참조값 비교인 == 연산자를 사용하고 싶다면 코틀린에서는 === 를 사용하면 된다.
 */

/**
 * data class
 *
 * toString, eq & hc, copy 메서드를 모두 제공해 준다.
 */
data class Client(val name: String, val postalCode: Int)

/**
 * 클래스 위임
 *
 * 데코레이터 패턴과 같이 대부분의 기능은 특정 객체에 위임하고 몇 가지 기능만 추가하려는 경우
 * 추가하고자 하는 기능이 적음에도 상당히 많은 위임 구현 코드를 작성해야 한다.
 *
 * 이런 위임을 언어가 제공하는 일급 시민 기능으로 지원한다는 점이 코틀린의 장점이다.
 * 인터페이스를 구현할 때 by 키워드를 통해 그 인터페이스에 대한 구현을 다른 객체에 위임 중이라는 사실을 명시할 수 있다.
 */
class DelegatingCollection<T>(
    innerList: Collection<T> = ArrayList()
) : Collection<T> by innerList {}

class CountingSet<T>(
    private val innerSet: MutableCollection<T> = HashSet()
) : MutableCollection<T> by innerSet {
    private var objectsAdded = 0

    override fun add(element: T): Boolean {
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(c: Collection<T>): Boolean {
        objectsAdded += c.size
        return innerSet.addAll(c)
    }
}
