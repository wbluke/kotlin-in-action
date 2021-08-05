package action.ch6.ex1

/**
 * 최신 언어에서는 NPE를 방지하기 위해, 가능한 한 null을 다루는 시점을 런타임 시점에서 컴파일 시점으로 옮기는 것이다.
 */

/**
 * 코틀린은 기본적으로 모든 타입에 null을 허용하지 않는다.
 * 타입 뒤에 ?를 붙이면 null을 받을 수 있는 타입이 된다.
 */
fun strLenSafe(s: String?) {
    // ...
}

/**
 * ?.은 null 검사와 메서드 호출을 한 번의 연산으로 수행한다.
 * 호출하려는 값이 null이면 이 호출은 무시되고 null이 결과 값이 된다.
 *
 * if (foo != null) foo.bar() else null
 * =====================================
 * foo?.bar()
 */
fun printAllCaps(s: String?) {
    val allCaps: String? = s?.toUpperCase()
    println(allCaps) // "abc" -> ABC, null -> null
}

/**
 * 코틀린은 null 대신 사용할 디폴트 값을 지정할 때 편리하게 사용할 수 있는 연산자를 제공한다.
 * 이를 ?: 엘비스 연산자라고 한다.
 */
fun foo(s: String?) {
    val t: String = s ?: "" // s가 null이면 결과는 ""이다.
}

/**
 * 코틀린에서는 return이나 throw 등의 연산도 식이다.
 * 따라서 엘비스 연산자의 우항에 return, throw 등의 연산을 넣을 수 있고, 엘비스 연산자를 더욱 편하게 사용할 수 있다.
 */
class Address(val streetAddress: String, val zipCode: Int, val city: String, val country: String)
class Company(val name: String, val address: Address?)
class Person(val name: String, val company: Company?)

fun printShippingLabel(person: Person) {
    val address = person.company?.address
        ?: throw IllegalArgumentException("No address")
    with(address) {
        println(streetAddress)
        println("$zipCode $city, $country")
    }
}

/**
 * as? 연산자는 어떤 값을 지정한 타입으로 캐스트한다.
 * as?는 값을 대상 타입으로 변환할 수 없으면 null을 반환한다.
 *
 * 안전한 캐스트를 사용할 때 일반적인 패턴은 캐스트를 수행한 뒤에 엘비스 연산자를 사용하는 것이다.
 */
class Person2(val firstName: String, val lastName: String) {
    override fun equals(other: Any?): Boolean {
        val otherPerson = other as? Person2 ?: return false
        return otherPerson.firstName == firstName &&
                otherPerson.lastName == lastName
    }
}

/**
 * 널 아님 단언(not-null assertion)은 느낌표를 이중(!!)으로 사용하여 어떤 값이든 null이 될 수 없는 타입으로 바꾼다.
 *
 * 사용 시 컴파일러에게 소리를 지르는 듯 해 약간 무례해보이는데, 이는 컴파일러가 검증할 수 없는 assertion을 사용하기보다는
 * 더 나은 방법을 찾아보라는 코틀린 설계자들의 의도다.
 */
fun ignoreNulls(s: String?) {
    val sNotNull: String = s!!
    println(sNotNull.length)
}

/**
 * let 함수를 안전한 호출 연산자와 함께 사용하면 원하는 식을 평가해서 결과가 null인지 검사한 다음에
 * 그 결과를 변수에 넣는 작업을 간단한 식을 사용해 한꺼번에 처리할 수 있다.
 * 흔한 용례는 null이 될 수 있는 값을 null이 아닌 값만 인자로 받는 함수에 넘기는 경우다.
 *
 * let 함수는 자신의 수신 객체를 전달받은 람다에게 넘긴다.
 * null이 될 수 있는 값에 대해 안전한 호출 구문을 사용해 let을 호출하되 null이 될 수 없는 타입을 인자로 받는 람다를 let에 전달한다.
 * 이렇게 하면 null이 될 수 있는 타입의 값을 null이 될 수 없는 타입의 값으로 바꿔서 람다에 전달하게 된다.
 */
fun sendEmailTo(email: String) {
    // ...
}

fun let(email: String?) {
    email?.let { sendEmailTo(it) }
}

/**
 * 코틀린에서 클래스 안의 null이 될 수 없는 프로퍼티를 생성자 안에서 초기화하지 않고 특별한 메서드 안에서 초기화할 수는 없다.
 * 게다가 프로퍼티 타입이 null이 될 수 없는 타입이라면 반드시 null이 아닌 값으로 초기화해야 한다.
 * 그런 초기화 값을 제공할 수 없으면 null이 될 수 있는 타입을 사용할 수밖에 없다.
 *
 * lateinit 변경자를 붙이면 프로퍼티를 나중에 초기화할 수 있다.
 * 나중에 초기화하는 프로퍼티는 항상 var여야 한다.
 */
class MyService {
    fun performAction(): String = "foo"
}

class MyTest {
    private lateinit var myService: MyService
}

/**
 * null이 될 수 있는 타입에 대한 확장 함수를 정의하면 null 값을 다루는 강력한 도구로 활용할 수 있다.
 * String? 타입의 isNullOrEmpty, isNullOrBlank 와 같은 예가 있다.
 */

/**
 * 코틀린에서는 함수나 클래스의 모든 타입 파라미터는 기본적으로 null이 될 수 있다.
 * 따라서 타입 파라미터 T를 타입 이름으로 사용하면 이름 끝에 ?가 없더라도 null이 될 수 있는 타입이다.
 * ?를 붙여야 한다는 규칙의 유일한 예외이다.
 */
fun <T : Any> printHashCode(t: T) { // Any 상한을 지정하면 null이 될 수 없도록 선언할 수 있다.
    // ...
}

/**
 * Any, Any? 는 자바의 Object에 대응하는 최상위 타입이다.
 * toString, equals, hashCode 가 여기서 기인한다.
 */

/**
 * 코틀린 Unit 타입은 자바 void와 같은 기능을 한다.
 * 다른 점은, Unit은 모든 기능을 갖는 일반적인 타입이며, void와 달리 Unit을 타입 인자로 쓸 수 있다는 것이다.
 */
interface Processor<T> {
    fun process(): T
}

class NoResultProcessor : Processor<Unit> {
    override fun process() {
        // ...
    }
}

/**
 * 코틀린에는 결코 성공적으로 값을 돌려주는 일이 없으므로 '반환 값'이라는 개념 자체가 의미 없는 함수가 일부 존재한다.
 * 테스트 라이브러리의 fail() 처럼 예외를 던지거나 무한 루프를 도는 경우가 그 예다.
 *
 * 이렇게 함수가 정상적으로 끝나지 않는다는 사실을 명시하는 Nothing 타입이 있다.
 * val address = company.address ?: fail("No address")
 */
