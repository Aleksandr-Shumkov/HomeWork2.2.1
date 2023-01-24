package ru.netology

fun main() {
    //проверка лайков
    var post = Post(authorId = 1, authorName = "Alex", content = "dsgssdgedgbdbed")
    WallService.add(post)
    var post1 = Post(authorId = 35, authorName = "Petrovych", content = "sldg elrbjl trjbn khtkj gskul")
    var post2 = Post(authorId = 12, authorName = "Kolya Usypov", content = "Привет")
    WallService.add(post1)

    WallService.likeById(post1.id)
    WallService.likeById(post1.id)
    WallService.likeById(post2.id)
    WallService.likeById(post1.id)
    WallService.likeById(post1.id)

    println(WallService.getPostString())

    post1 = post1.copy(content = "sdhve kjhbkdfb")
    WallService.update(post1)
    println("----------------------")
    println(WallService.getPostString())

    WallService.add(post2)
    WallService.likeById(post2.id)
    WallService.likeById(post1.id)
    println("----------------------")
    println(WallService.getPostString())
}

object CorrectId {
    private var lastId = 0
    //смотрим в то место где у нас хранятся id постов и выдаём свободный

    fun getNewId(id: Int): Int {
        if (id == 0) {
            lastId += 1
            return lastId
        } else
            return 0
    }

    fun clearId() {
        lastId = 0
    }
}

data class Post(
    val id: Int = CorrectId.getNewId(0),
    val authorId: Int,
    val authorName: String,
    val content: String,
    val likes: Likes = Likes(),
    val friends_only: Boolean = false,
    val can_pin: Boolean = false,
    val can_edit: Boolean = true
)

data class Likes(
    var count: Int = 0,
    var user_likes: Boolean = false,
    var can_like: Boolean = true
)

object WallService {
    private var posts = emptyArray<Post>()

    fun getPostString() {
        for (i in posts) {
            println(i.toString())
        }
    }

    fun clear() {
        CorrectId.clearId()
        posts = emptyArray()
    }

    fun add(post: Post): Post {
        posts += post
        return posts.last()
    }

    fun update(post: Post): Boolean {
       for ((index, i) in posts.withIndex()) {
           if (i.id == post.id) {
               posts[index] = post.copy(authorName = post.authorName, content = post.content, likes = i.likes, friends_only = post.friends_only,
                   can_pin = post.can_pin, can_edit = post.can_edit)
               return true
           }
       }
        return false
    }

    fun likeById(id: Int) {
        for ((index, post) in posts.withIndex()) {
            if (post.id == id) {
                posts[index] = post.copy(likes = Likes(post.likes.count + 1, post.likes.user_likes, post.likes.can_like))
            }
        }
    }

}