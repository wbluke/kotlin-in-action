package action.ch7.ex1

/**
 * 자바에서는 표준 라이브러리와 밀접하게 연관된 언어 기능이 몇 가지 있다.
 * for ... in 루프에 Iterable, try문에 AutoCloseable 을 사용하는 것처럼.
 *
 * 코틀린에서는 이런 언어 기능이 어떤 타입과 연관되기보다는 특정 함수 이름과 연관된다.
 * 이를 관례(convention)라고 부른다.
 */

/**
 * 이항 산술 연산 오버로딩
 *
 * 코틀린에서 관례를 사용하는 가장 단순한 예는 산술 연산자다.
 * 자바에서는 원시타입, String에 대해서만 산술 연산자를 사용할 수 있지만, 코틀린에서는 다른 클래스에서도 사용 가능하다.
 */
data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point): Point { // plus 함수 앞에 operator
        return Point(x + other.x, y + other.y)
    }
}

operator fun Point.plus(other: Point): Point { // 확장 함수로 정의할 수도 있다.
    return Point(x + other.x, y + other.y)
}

/**
 * 코틀린에서는 프로그래머가 직접 연산자를 만들어 사용할 수 없고 언어에서 미리 정해둔 연산자만 오버로딩할 수 있으며,
 * 관례에 따르기 위해 클래스에서 정의해야 하는 이름이 연산자별로 정해져 있다.
 *
 * 식      | 함수 이름
 * ----------------------------
 * a * b   | times
 * a / b   | div
 * a % b   | mod (1.1부터 rem)
 * a + b   | plus
 * a - b   | minus
 *
 * 연산자 우선순위는 표준 숫자 타입에 대한 우선순위와 같다.
 */

/**
 * 코틀린 연산자가 자동으로 교환 법칙을 지원하지는 않음에 유의하자.
 * point * 1.5 와 1.5 * point 는 다르다. (Double 쪽에도 동일한 개념의 확장함수가 정의되어 있어야 한다.)
 */

/**
 * 복합 대입 연산자 오버로딩
 *
 * plus와 같은 연산자를 오버로딩하면 += 도 자동으로 함께 지원된다. 이를 복합 대입 연산자라고 한다.
 * 반환 타입이 Unit인 plusAssign 함수를 정의하면 코틀린은 += 연산자에 그 함수를 사용한다.
 * minusAssign, timesAssign
 *
 * 이론적으로는 코드에 있는 +=를 plus와 plusAssign 양쪽으로 컴파일할 수 있는데, 오류가 발생하므로
 * 두 연산을 동시에 정의하지 말자.
 */

/**
 * 단항 연산자 오버로딩
 *
 * 식         | 함수 이름
 * ----------------------------
 * +a         | unaryPlus
 * -a         | unaryMinus
 * !a         | not
 * ++a, a++   | inc
 * --a, a--   | dec
 */

/**
 * equals
 *
 * == 연산자 호출은 equals 메서드 호출로 컴파일한다. !=도 마찬가지.
 * a == b 라는 비교를 처리할 때 코틀린은 a가 null인지 판단해서 null이 아닌 경우에만 a.equals(b)를 호출한다.
 * a가 null이라면 b도 null인 경우에만 true다.
 */

/**
 * compareTo
 *
 * Comparable 인터페이스의 compareTo 메서드를 호출하는 관례는 비교 연산자이다. (<, >, <=, >=)
 */

/**
 * 컬렉션 인덱스
 *
 * 인덱스를 사용해 원소를 설정하거나 가져오고 싶을 때는 a[b] 라는 식을 사용한다. 이를 인덱스 연산자라고 한다.
 * 인덱스 연산자를 사용해 원소를 읽는 연산은 get 메서드로 변환되고, 원소를 쓰는 연산은 set 메서드로 변환된다.
 */

/**
 * 컬렉션 in
 *
 * in은 객체가 컬렉션에 들어있는지 검사한다. 대응 함수는 contains 이다.
 */

/**
 * 컬렉션 rangeTo
 *
 * .. 연산자는 rangeTo 함수를 간략하게 표현하는 방법이다.
 */

/**
 * 컬렉션 iterator
 *
 * for 루프는 범위 검사와 똑같이 in 연산자를 사용한다. 하지만 이 경우 in의 의미는 다르다.
 * iterator 메서드와 대응하는 관례로 확장 함수를 정의할 수 있다.
 */

/**
 * 구조 분해 선언
 *
 * 구조 분해 선언도 관례고, componentN이라는 함수를 사용한다.
 * N은 구조 분해 선언에 있는 변수 위치에 따라 붙는 번호다.
 *
 * val (x, y) = point
 * -------------------------
 * val x = point.component1()
 * val y = point.component2()
 *
 * data 클래스의 주 생성자에 들어있는 프로퍼티에 대해서는 컴파일러가 자동으로 componentN 함수를 만들어준다.
 *
 * 코틀린 표준 라이브러리에서는 맨 앞의 다섯 원소에 대한 componentN을 제공한다.
 */

/**
 * 위임 프로퍼티
 *
 * 위임 프로퍼티(delegated property)를 사용하면 값을 뒷받침하는 필드에 단순히 저장하는 것보다
 * 더 복잡한 방식으로 작동하는 프로퍼티를 쉽게 구현할 수 있다.
 * 또한 그 과정에서 접근자 로직을 매번 재구현할 필요도 없다.
 * 예를 들어 프로퍼티는 위임을 사용해 자신의 값을 필드가 아니라 DB 테이블이나 브라우저 세션, 맵 등에 저장할 수 있다.
 *
 * class Foo {
 *     var p: Type by Delegate()
 * }
 *
 * Delegate 위임 객체의 관례는 getValue, setValue 메서드에 대응된다.
 */

/**
 * 위임 프로퍼티 사용 : 프로퍼티 초기화 지연
 *
 * 지연 초기화(lazy initialization)는 객체의 일부분을 초기화하지 않고 남겨뒀다가
 * 실제로 그 부분의 값이 필요할 경우 초기화할 때 흔히 쓰이는 패턴이다.
 *
 * lazy 함수는 코틀린 관례에 맞는 시그니처의 getValue 메서드가 들어있는 객체를 반환한다.
 * 따라서 lazy를 by 키워드와 함께 사용해 위임 프로퍼티를 만들 수 있다.
 *
 * lazy 함수는 기본적으로 Thread-Safe 하다.
 */
fun loadEmails(person: Person) {
    // 복잡한 초기화 로직
}

class Person(val name: String) {
    val emails by lazy { loadEmails(this) } // 지연 초기화
}
