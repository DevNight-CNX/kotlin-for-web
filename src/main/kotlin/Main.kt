import org.jetbrains.ktor.host.embeddedServer
import org.jetbrains.ktor.netty.Netty
import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.routing.get
import org.jetbrains.ktor.routing.routing

fun main(args: Array<String>) {
    val server = embeddedServer(Netty, 9000) {
        routing {
            get("/") {
                call.respondText("Hello World!")
            }
        }
    }
    server.start(wait = true)
}
