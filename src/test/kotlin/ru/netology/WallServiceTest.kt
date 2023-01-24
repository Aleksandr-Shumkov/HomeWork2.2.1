package ru.netology

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {


    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add(){
        val post = WallService.add(Post(authorId = 35, authorName = "Petrovych", content = "sldg elrbjl trjbn khtkj gskul"))
        val post1 = WallService.add(Post(authorId = 1, authorName = "Alex", content = "dsgssdgedgbdbed"))
        assertEquals(1, post.id)
        assertEquals(2, post1.id)
    }

    @Test
    fun update() {
        val post = WallService.add(Post(authorId = 35, authorName = "Petrovych", content = "sldg elrbjl trjbn khtkj gskul"))
        assertTrue(WallService.update(post)) //изменили ли мы уже существующую запись P.S. запись добавлена
        assertFalse(WallService.update(Post(authorId = 12, authorName = "Kolya Usypov", content = "Привет")))// Проверка на изменение несуществующей записи
    }
}