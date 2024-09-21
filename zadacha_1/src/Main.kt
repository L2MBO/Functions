fun main() {
    println("Введите число: 1 - Камень, 2 - ножницы, 3 - бумага")
    val action = readln()

    val computer = (1..3).random().toString()
    println(computer)

    val combine = action + computer;

    game(combine)
}

private fun game(combine: String) {
    when (combine) {
        "11", "22", "33" -> println("Ничья")
        "12", "23", "31" -> println("Вы победили")
        else -> println("Вы проиграли")
    }
}