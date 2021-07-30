package action.ch3.ex7

/**
 * 코틀린에서는 함수에서 추출한 함수를 원 함수 내부에 중첩시킬 수 있다.
 */

class User(val id: Int, val name: String, val address: String)

/**
 * 코드 중복 예제
 */
fun saveUser(user: User) {
    if (user.name.isEmpty()) {
        throw IllegalArgumentException("Can't save user ${user.id}: empty name")
    }
    if (user.address.isEmpty()) {
        throw IllegalArgumentException("Can't save user ${user.id}: empty address")
    }

    // save user to database
}

/**
 * 로컬 함수를 사용해 코드 중복 줄이기
 */
fun saveUser2(user: User) {
    fun validate(user: User, value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user ${user.id}: empty $fieldName")
        }
    }
    validate(user, user.name, "name")
    validate(user, user.address, "address")

    // save user to database
}

/**
 * 로컬 함수에서 바깥 함수의 파라미터 접근하기
 */
fun saveUser3(user: User) {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user ${user.id}: empty $fieldName")
        }
    }
    validate(user.name, "name")
    validate(user.address, "address")

    // save user to database
}

/**
 * 검증 로직을 확장 함수로 추출하기
 */
fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user ${id}: empty $fieldName")
        }
    }
    validate(name, "name")
    validate(address, "address")

    // save user to database
}

fun saveUser4(user: User) {
    user.validateBeforeSave()
}
