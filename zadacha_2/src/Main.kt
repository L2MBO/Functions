fun main() {
    // Запрашиваем у пользователя исходное сообщение
    print("Введите исходное сообщение:")
    val message = readln().replace(" ", "").uppercase() // Убираем пробелы и приводим к верхнему регистру

    // Запрашиваем вспомогательный символ
    print("Введите вспомогательный символ:")
    val helperChar = readln().uppercase().first() // Считываем символ и приводим к верхнему регистру

    // Создаем таблицу шифрования
    val table = createTable()

    // Создаем пары символов для шифрования
    val pairs = createPairs(message, helperChar)

    // Шифруем сообщение на основе пар и таблицы
    val encryptedMessage = encryptMessage(pairs, table)

    // Выводим исходное сообщение в виде пар
    println("\nИсходное сообщение по парам:")
    println(pairs.joinToString(" ")) // Преобразуем список пар в строку

    // Выводим зашифрованное сообщение
    println("\nЗашифрованное сообщение:")
    println(encryptedMessage.joinToString(" ")) // Преобразуем список зашифрованных пар в строку

    // Выводим шифровальную таблицу
    println("\nШифровальная таблица:")
    printTable(table) // Вызываем функцию для печати таблицы
}

fun createTable(): Array<Array<Char>> {
    // Определяем алфавит для шифрования
    val alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ0123456789!@#$%^&*()_+-=<>?"

    val size = 10 // Размер таблицы 10x10
    val table = Array(size) { Array(size) { ' ' } } // Создаем двумерный массив для таблицы

    var index = 0 // Индекс для заполнения таблицы
    for (i in 0..< size) { // Проходим по строкам таблицы
        for (j in 0..< size) { // Проходим по столбцам таблицы
            if (index < alphabet.length) { // Проверяем, не вышли ли мы за пределы алфавита
                table[i][j] = alphabet[index] // Заполняем текущую ячейку символом из алфавита
                index++ // Увеличиваем индекс для следующего символа
            } else {
                table[i][j] = ' ' // Заполняем пустыми символами, если не хватает букв
            }
        }
    }
    return table // Возвращаем заполненную таблицу
}

fun createPairs(message: String, helperChar: Char): List<String> {
    val pairs = mutableListOf<String>() // Создаем список для хранения пар символов
    var msg = message

    // Если длина сообщения нечетная, добавляем вспомогательный символ
    if (msg.length % 2 != 0) {
        msg += helperChar // Добавляем вспомогательный символ в конец сообщения
    }

    // Формируем пары символов из сообщения
    for (i in msg.indices step 2) {
        pairs.add("${msg[i]}${msg[i + 1]}") // Добавляем пару символов в список
    }

    return pairs // Возвращаем список пар
}

fun encryptMessage(pairs: List<String>, table: Array<Array<Char>>): List<String> {
    val encryptedMessage = mutableListOf<String>() // Создаем список для хранения зашифрованного сообщения

    for (pair in pairs) { // Проходим по всем парам символов
        val firstChar = pair[0] // Первый символ пары
        val secondChar = pair[1] // Второй символ пары

        // Находим координаты первого и второго символов в таблице
        val firstCoords = findCoordinates(firstChar, table)
        val secondCoords = findCoordinates(secondChar, table)

        if (firstCoords != null && secondCoords != null) {
            // Если координаты найдены, добавляем их в зашифрованное сообщение
            encryptedMessage.add("${firstCoords.first}${firstCoords.second} ${secondCoords.first}${secondCoords.second}")
        }
    }

    return encryptedMessage // Возвращаем зашифрованное сообщение
}

fun findCoordinates(char: Char, table: Array<Array<Char>>): Pair<Int, Int>? {
    for (i in table.indices) { // Проходим по строкам таблицы
        for (j in table[i].indices) { // Проходим по столбцам текущей строки
            if (table[i][j] == char) { // Если нашли нужный символ
                return Pair(i + 1, j + 1) // Возвращаем координаты (строка, столбец), увеличенные на 1 для удобства
            }
        }
    }
    return null // Если символ не найден, возвращаем null
}

fun printTable(table: Array<Array<Char>>) {
    for (row in table) { // Проходим по каждой строке таблицы
        println(row.joinToString(" ")) // Печатаем строку, разделяя символы пробелами
    }
}
