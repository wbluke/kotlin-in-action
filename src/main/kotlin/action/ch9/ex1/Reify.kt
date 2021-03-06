package action.ch9.ex1

/**
 * JVM의 제네릭스는 보통 타입 소거를 사용해서 구현된다.
 * 이는 실행 시점에 제네릭 클래스의 인스턴스에 타입 인자 정보가 들어있지 않다는 뜻이다.
 *
 * 함수 inline으로 선언함으로써 이런 제약을 우회하여 타입 인자가 지워지지 않게 할 수 있다.
 * 이를 실체화(reify)라고 한다.
 */

/**
 * 타입 소거로 인해 생기는 한계
 *
 * 타입 인자를 저장하지 않기 때문에 실행 시점에 인자를 검사할 수 없다.
 * = is 검사에서 타입 인자로 지정한 타입을 검사할 수는 없다.
 * if (value is List<String>) { ... }
 *
 * 타입 인자를 명시하지 않은 리스트를 검사하려면 '스타 프로젝션'을 사용할 수 있다.
 * if (value is List<*>) { ... }
 * (자바의 List<?>와 비슷하다)
 */

/**
 *  실체화한 타입 파라미터를 사용한 함수 선언
 *
 *  fun <T> isA(value: Any) = value is T
 *  Error: Cannot check for instance of erased type: T
 *
 *  제네릭 타입의 타입 인자 정보는 실행 시점에 지워지기 때문에 위와 같은 에러가 발생한다.
 *
 *  inline 함수로 만들면, 실행 시점에 함수 본문이 인라이닝되기 때문에 타입 정보가 지워지지 않는다.
 *  reified 키워드와 함께 사용할 수 있다.
 */
inline fun <reified T> isA(value: Any) = value is T // reified 키워드는 타입 파라미터가 실행 시점에 지워지지 않음을 표시한다.

/**
 * 실체화한 타입 파라미터를 사용할 수 있는 경우
 *
 * - 타입 검사와 캐스팅 (is, !is, as, as?)
 * - 코틀린 리플렉션 API(::class)
 * - 코틀린 타입에 대응하는 java.lang.Class를 얻기(::class.java)
 * - 다른 함수를 호출할 때 타입 인자로 사용
 */

/**
 * 실체화한 타입 파라미터를 사용할 수 없는 경우
 *
 * - 타입 파라미터 클래스의 인스턴스 생성하기
 * - 타입 파라미터 클래스의 동반 객체 메서드 호출하기
 * - 실체화한 타입 파라미터를 요구하는 함수를 호출하면서 실체화되지 않은 타입 파라미터로 받은 타입을 타입 인자로 넘기기
 * - 클래스, 프로퍼티, 인라인 함수가 아닌 함수의 타입 파라미터를 reified로 지정하기
 */
