package action.ch9.ex2

/**
 * List<Any> 타입의 파라미터를 받는 함수에 List<String>을 넘겨도 안전한가?
 *
 * - 어떤 함수가 리스트의 원소를 추가하거나 변경한다면 타입 불일치가 발생할 수 있으므로 불가능 (mutableList)
 * - 원소 추가/변경이 없는 경우 가능
 */

/**
 * 하위 타입 (subtype)
 *
 * 타입 A의 값이 필요한 모든 장소에 어떤 타입 B의 값을 넣어도 아무 문제가 없다면 타입 B는 타입 A의 하위 타입이다.
 * 상위 타입(supertype)은 그 반대다.
 *
 * 간단한 경우 하위 타입은 하위 클래스(subclass)와 근본적으로 같다.
 *
 * null이 될 수 없는 타입은 null이 될 수 있는 타입의 하위 타입이다.
 *
 * 제네릭 타입을 인스턴스화할 때 타입 인자로 서로 다른 타입이 들어가면 인스턴스 타입 사이의 하위 타입 관계가 성립하지 않으면
 * 그 제네릭 타입을 무공변(invariant)이라고 한다.
 * (A와 B가 다르기만 하면 MutableList<A>는 항상 MutableList<B>의 하위 타입이 아니다.)
 * 자바에서는 모든 클래스가 무공변이다.
 *
 * 읽기 전용 컬렉션인 리스트에서는, A가 B의 하위 타입이면 List<A>는 List<B>의 하위 타입이다.
 * 이런 클래스나 인터페이스를 공변적(covariant)이라 한다.
 */

/**
 * 공변성 : 하위 타입 관계를 유지
 *
 * 제네릭 클래스가 타입 파라미터에 대해 공변적임을 표시하려면 타입 파라미터 이름 앞에 out을 넣어야 한다.
 */
interface Producer<out T> {
    fun produce(): T
}

/**
 * 클래스의 타입 파라미터를 공변적으로 만들면 함수 정의에 사용한 파라미터 타입과 타입 인자의 타입이 정확히 일치하지 않더라도
 * 그 클래스의 인스턴스를 함수 인자나 반환값으로 사용할 수 있다.
 */
open class Animal {
    fun feed() {
        println("feed!")
    }
}

class Herd<T : Animal>(private val animals: List<T>) {
    val size: Int get() = animals.size
    operator fun get(i: Int): T {
        return animals[i]
    }
}

fun feedAll(animals: Herd<Animal>) {
    for (i in 0 until animals.size) {
        animals[i].feed()
    }
}

class Cat : Animal() {
    fun cleanLitter() {
        println("clean!")
    }
}

fun takeCareOfCats(cats: Herd<Cat>) {
    for (i in 0 until cats.size) {
        cats[i].cleanLitter()
//        feedAll(cats)
        // 불가능
    }
}

// ============================================================================

fun feedAll2(animals: Herd2<Animal>) {
    for (i in 0 until animals.size) {
        animals[i].feed()
    }
}

class Herd2<out T : Animal>(private val animals: List<T>) {
    val size: Int get() = animals.size
    operator fun get(i: Int): T {
        return animals[i]
    }
}

fun takeCareOfCats2(cats: Herd2<Cat>) {
    for (i in 0 until cats.size) {
        cats[i].cleanLitter()
        feedAll2(cats) // 가능
    }
}

/**
 * out : 클래스가 T 타입의 값을 생산할 수는 있지만 T 타입의 값을 소비할 수는 없다.
 *
 * out 키워드는 다음 두 가지를 함께 의미한다.
 * - 공변성 : 하위 타입 관계가 유지된다. (Producer<Cat>은 Producer<Animal>의 하위 타입이다.)
 * - 사용 제한 : T를 out 위치에서만 사용할 수 있다.
 *
 * List는 T에 대해 공변적이다.
 *
 * 생성자 파라미터는 in이나 out 그 어느 쪽도 아니다.
 * 생성자는 나중에 호출할 수 있는 메서드가 아니기 때문에 안전하다.
 *
 * private 메서드의 파라미터는 in도 아니고 out도 아닌 위치다.
 * 변성 규칙은 클래스 외부의 사용자가 클래스를 잘못 사용하는 일을 막기 위한 것이므로 클래스 내부 구현에는 적용되지 않는다.
 */

/**
 * 반공변성 : 뒤집힌 하위 타입 관계
 *
 * T 타입의 값을 소비하기만 한다.
 *
 * 타입 B가 타입 A의 하위 타입인 경우 Consumer<A>가 Consumer<B>의 하위 타입인 관계가 성립하면
 * 제네릭 클래스 Consumer<T>는 타입 인자 T에 대해 반공변이다. (A와 B의 위치가 바뀌는 것에 주의)
 */
interface Comparator<in T> {
    fun compare(el: T, e2: T): Int {
        return el.hashCode() - e2.hashCode()
    }
}

/**
 * 공변성                             | 반공변성                          | 무공변성
 * --------------------------------------------------------------------------------------------------------------
 * Producer<out T>                    | Consumer<in T>                    | MutableList<T>
 *                                    |                                   |
 * 타입 인자의 하위 타입 관계가       | 타입 인자의 하위 타입 관계가      | 하위 타입 관계가 성립하지 않는다.
 * 제네릭 타입에서도 유지             | 제네릭 타입에서 뒤집힌다.         |
 *                                    |                                   |
 * T를 out 위치에서만 사용할 수 있다. | T를 in 위치에서만 사용할 수 있다. | T를 아무 위치에서나 사용할 수 있다.
 */

/**
 * 클래스 정의에 변성을 직접 기술하면 그 클래스를 사용하는 모든 장소에 그 변성이 적용된다.
 * 자바는 이를 지원하지 않는다.
 *
 * MutableList<out T> : out 키워드를 타입을 사용하는 위치 앞에 붙이면 T 타입을 in 위치에 사용하는 메서드를 호출하지 않는다는 뜻이다.
 * 이때 타입 프로젝션(type projection)이 일어난다.
 * MutableList를 프로젝션을 한(제약을 가한) 타입으로 만든다.
 */

/**
 * MutableList<*>는 MutableList<Any?>와 같지 않다.
 * MutableList<Any?>는 모든 타입의 원소를 담을 수 있다는 뜻이고,
 * MutableList<*>는 어떤 정해진 구체적인 타입의 원소만을 담는 리스트지만 그 원소의 타입을 정확히 모른다는 뜻이다.
 */
