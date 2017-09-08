
import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.features.CallLogging
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.gson.GsonSupport
import org.jetbrains.ktor.host.embeddedServer
import org.jetbrains.ktor.netty.Netty
import org.jetbrains.ktor.response.respond
import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.routing.Routing
import org.jetbrains.ktor.routing.get

data class Movie(val id: String, val name: String,val imageUrl: String)

fun Application.module() {

    val data = initData()

    install(GsonSupport)
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/") {
            call.respondText("Hello World! 555")
        }

        get("/api") {
            call.respond(data)
        }

        get("/api/movies") {
            call.respond(data)
        }

        get("/api/moviews/{id}") {
            call.respond(data)
        }
    }
}

fun main(args: Array<String>) {
    initData()

    val server = embeddedServer(Netty, 9000, watchPaths = listOf("MainKt"), module = Application::module)
    server.start(wait = true)
}

fun initData(): List<Movie> {
   return listOf(
           Movie("1", "Test", "url"),
           Movie("2", "Test2", "url2"),
           Movie("3", "test3", "url3")
   )
}
